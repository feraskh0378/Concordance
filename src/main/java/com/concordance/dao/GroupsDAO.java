package com.concordance.dao;


import com.concordance.model.Group;
import com.concordance.model.SongWord;

import com.concordance.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsDAO 
{
	private Connection conn = null;
	
	public void initConnection()
	{
		try
		{
			conn = DBConnection.getConnection();
		}
		 catch (SQLException e) 
        {
        }		
	}
	
	
	
	public Group getGroup(String groupName)
	{		
    	String sql = " SELECT * FROM \"Groups\" WHERE \"GroupName\"='"+groupName+"'";        		     
     
        try 
        (
        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
             ResultSet rs = stmt.executeQuery()
        ) 
        {
            while (rs.next()) 
            {
            		            	
            	return  new Group(rs.getInt("ID"),rs.getString("GroupName"),0);
            	
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return null;		
	}
	
	
	 public ArrayList<Group> getAllGroups()
	 {
		 ArrayList<Group> Groups = new ArrayList<>();
	    	String sql = " SELECT \"ID\",\"GroupName\" FROM \"Groups\" ";        		     
	     

	        try 
	        (
	        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
	             ResultSet rs = stmt.executeQuery()
	        ) 
	        {

	            while (rs.next()) 
	            {
	            		            	
	            	Group s = new Group(rs.getInt("ID"),rs.getString("GroupName"),0);
	            	Groups.add(s);
	            }

	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }

	        return Groups;
	 }

		
	  public ArrayList<Group> getAllGroups(String inputStartIndex, String maxInPage) 
	    {
		  
	    	ArrayList<Group> Groups = new ArrayList<>();
	    	     
	    	String sql = " SELECT * "+
	       		     "FROM \"Groups\" " +       			
	       			 "WHERE \"ID\" != 0 "+       		    
	       		     "LIMIT "+maxInPage+" OFFSET "+ inputStartIndex;
	        try 
	        (
	        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
	             ResultSet rs = stmt.executeQuery()
	        ) 
	        {

	            while (rs.next()) 
	            {
	            		            	
	            	Group s = new Group(rs.getInt("ID"),rs.getString("GroupName"),0);
	            	Groups.add(s);
	            }

	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	        
	    	String NumberOfWordsSql = " SELECT COUNT(*) AS numberOfInstances " +
       		     " FROM \"Words\" w " +       			        		     
       			 "WHERE w.\"GroupId\" = ?  ";
       		     
       		     
	    	
	    	for(Group group : Groups)
	    	{
	    		try 
		        (
		        	 PreparedStatement NumberOfWordsStmt = conn.prepareStatement(NumberOfWordsSql);	    			 
		        ) 
		        {
	    			NumberOfWordsStmt.setInt(1, group.getId() );
		            ResultSet rs = NumberOfWordsStmt.executeQuery();

		            while (rs.next()) 
		            {
		            	int numberofWordsInt = rs.getInt("numberOfInstances");
		            	group.setNumberOfWords(numberofWordsInt);
		            }

		        } 
		        catch (SQLException e) 
		        {
		            e.printStackTrace();
		        }

	    	
	    	}

	        return Groups;
	    }
	  
	  public int getAllGroupsSize(String inputStartIndex, String maxInPage) 
	    {
		     	String sql = " SELECT COUNT(*)  FROM \"Groups\" w " ;
	        			 
	        try 
	        (
	        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
	             ResultSet rs = stmt.executeQuery()
	        ) 
	        {

	            while (rs.next()) 
	            {
	            		            	
	            	return rs.getInt(1);
	            	
	            }

	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }

	        return 0;
	    }
	  
	  public ArrayList<Group> filterGroups(String inputStartIndex , String inputMaxSize,String inputGroupName , String inputNumberOfWords) 
	    {
		  
	    	ArrayList<Group> tempGroups = new ArrayList<>();
	    	ArrayList<Group> groups = new ArrayList<>();
	    	     
	    	String sql = " SELECT * "+
	       		     "FROM \"Groups\" " +       			
	       			 "WHERE \"ID\" != 0 AND \"GroupName\" ILIKE '%"+ inputGroupName + "%' "  +       		    
	       		     "LIMIT "+inputMaxSize+" OFFSET "+ inputStartIndex;
	        try 
	        (
	        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
	             ResultSet rs = stmt.executeQuery()
	        ) 
	        {

	            while (rs.next()) 
	            {
	            		            	
	            	Group s = new Group(rs.getInt("ID"),rs.getString("GroupName"),0);
	            	tempGroups.add(s);
	            }

	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	        
	    	String NumberOfWordsSql = " SELECT COUNT(*) AS numberOfInstances " +
     		     " FROM \"Words\" w " +       			        		     
     			 "WHERE w.\"GroupId\" = ?  ";
     		     
     		     
	    	
	    	for(Group group : tempGroups)
	    	{
	    		try 
		        (
		        	 PreparedStatement NumberOfWordsStmt = conn.prepareStatement(NumberOfWordsSql);	    			 
		        ) 
		        {
	    			NumberOfWordsStmt.setInt(1, group.getId() );
		            ResultSet rs = NumberOfWordsStmt.executeQuery();

		            while (rs.next()) 
		            {
		            	int numberofWordsInt = rs.getInt("numberOfInstances");
		            	if(inputNumberOfWords.isEmpty() ||  Integer.valueOf(inputNumberOfWords) == numberofWordsInt)
		            	{
		            		group.setNumberOfWords(numberofWordsInt);
		            		groups.add(group);
		            	}		            	
		            }

		        } 
		        catch (SQLException e) 
		        {
		            e.printStackTrace();
		        }

	    	
	    	}

	        return groups;
	    }
	  
	  
    
    public int filterGroupsSize(String inputStartIndex , String inputMaxSize,String inputGroupName , String inputNumberOfWords) 
    {
    	
    	String sql = "SELECT COUNT(*) FROM ( SELECT w.\"GroupId\" , g.\"GroupName\", COUNT(*) AS numberOfInstances " +
   		     " FROM \"Words\" w " +
   			 " JOIN \"Groups\" g "+
   		     " ON w.\"GroupId\" = g.\"ID\" "+
   		     " GROUP BY w.\"GroupId\" , g.\"GroupName\" "+
   		     "WHERE 1=1 ";
        
		  if(!inputGroupName.isEmpty())
		  {
		  	sql = sql + "AND  g.\"GroupName\" LIKE  '%"+inputGroupName+ "%'";
		  }
		      
		  if(!inputNumberOfWords.isEmpty())
		  {
		  	sql = sql + "AND COUNT(*) ="+inputNumberOfWords;
		  }
        
        sql = sql + ")";

        try 
        (
        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
             ResultSet rs = stmt.executeQuery()
        ) 
        {

            while (rs.next()) 
            {
            	//public Word(String word,String group,String songTitle,int verseNumber, int lineNumber, int placeInLine, int numberOfInstancesInSong, int numberOfInstancesInDB )
            	return rs.getInt(1);            	            
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return 0;
    }
    
    public int getGroupWordsSize(String groupId, String StartIndex,String maxInPage) 
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
   	 String searchSql = "SELECT COUNT(*) " +
                "FROM \"Words\" sw " +                
                "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                "WHERE 1=1 AND w.\"ID\" = "+ groupId;
                
   	   	      	  
   	   try 
          (     
       	    PreparedStatement stmt = conn.prepareStatement(searchSql);
               ResultSet rs = stmt.executeQuery()
          ) 
          {

              while (rs.next()) 
              {
           	   return rs.getInt(1);            	   
              }

          } 
          catch (SQLException e) 
          {
              e.printStackTrace();
              return 0;
          }

                
   
   	return 0;
    
    }
    
    public void newGroup(String groupName)
    {
    	String sql = "INSERT INTO \"Groups\" (\"GroupName\") VALUES('"+groupName+"') ";
    	 try 
    	 {
     	    PreparedStatement stmt = conn.prepareStatement(sql);
            int res = stmt.executeUpdate();
    	 }
    	  catch (SQLException e) 
          {
             e.printStackTrace();
             return ;
          }
    		return;
         
        
    }
    
    
    public ArrayList<SongWord> getGroupWords(String groupId, String StartIndex,String maxInPage) 
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
    	 String searchSql = "SELECT * " +
                 "FROM \"Words\" sw " +
                 "JOIN \"Songs\" s ON sw.\"SongId\" = s.\"ID\" " +
                 "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                 "WHERE 1=1 AND w.\"ID\" = "+ groupId + " ";
                 
    	 
    	
    	    searchSql = searchSql + " ORDER BY sw.\"VerseNumber\" ASC, sw.\"LineNumber\" ASC, sw.\"PlaceInLine\" ";
    	    searchSql = searchSql + " LIMIT "+maxInPage+" OFFSET "+ StartIndex;
    	   
    	   try 
           (     
        	    PreparedStatement stmt = conn.prepareStatement(searchSql);
                ResultSet rs = stmt.executeQuery()
           ) 
           {

               while (rs.next()) 
               {
            	   int id = rs.getInt("ID");
            	   String word = rs.getString("Word");
            	   String song = rs.getString("Title");            	   
            	   String group = rs.getString("GroupName");
            	   int verse = rs.getInt("VerseNumber");
            	   int line = rs.getInt("LineNumber");;
            	   int lineplace = rs.getInt("PlaceInLine");
            	   
            	   words.add(new SongWord(id,word,song,group,verse,line,lineplace));            	   
               }

           } 
           catch (SQLException e) 
           {
               e.printStackTrace();
               return null;
           }

                 
    
    	return words;
    	
    }
    
    public SongWord getGroupWord(int WordId)
    {
    	String Sql = "SELECT * FROM \"Words\"  WHERE \"ID\" = ?";
    	try (PreparedStatement sqlStmt = conn.prepareStatement(Sql))
    	{    		    		    		    		
    		 sqlStmt.setInt(1,WordId);
    		 ResultSet rs = sqlStmt.executeQuery(); 
    		 while (rs.next()) 
	         {

    			 return  new SongWord(rs.getInt("ID"),rs.getString("Word"),"","",rs.getInt("VerseNumber"),rs.getInt("LineNumber"),rs.getInt("PlaceInLine"));
	         }
    	}
    	catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }  
    	return null;
    }
    
    public ArrayList<SongWord> filterGroupWords(String GroupId, String StartIndex,String maxInPage,String inputVerse) 
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
    	 String searchSql = "SELECT * " +
                 "FROM \"Words\" sw " +
                 "JOIN \"Songs\" s ON sw.\"SongId\" = s.\"ID\" " +
                 "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                 "WHERE 1=1 AND w.\"ID\" = "+ GroupId + " AND sw.\"VerseNumber\" =  '" + inputVerse + "' ";;
                 
    	 
    	
    	   searchSql = searchSql + " ORDER BY sw.\"VerseNumber\" ASC, sw.\"LineNumber\" ASC, sw.\"PlaceInLine\" ";
    	    searchSql = searchSql + " LIMIT "+maxInPage+" OFFSET "+ StartIndex;;
    	   
    	   try 
           (     
        	    PreparedStatement stmt = conn.prepareStatement(searchSql);
                ResultSet rs = stmt.executeQuery()
           ) 
           {

               while (rs.next()) 
               {
            	   int id = rs.getInt("ID");
            	   String word = rs.getString("Word");
            	   String song = rs.getString("Title");            	   
            	   String group = rs.getString("GroupName");
            	   int verse = rs.getInt("VerseNumber");
            	   int line = rs.getInt("LineNumber");;
            	   int lineplace = rs.getInt("PlaceInLine");
            	   
            	   words.add(new SongWord(id,word,song,group,verse,line,lineplace));            	   
               }

           } 
           catch (SQLException e) 
           {
               e.printStackTrace();
               return null;
           }

                 
    
    	return words;
    	
    }
    
    public int FilterSongWordsSize(String inputStartIndex, String maxInPage,String inputWord,Boolean FilterWord, String inputSong, Boolean FilterSong,  String inputGroup,Boolean FilterGroup,String inputVerse,String inputLine,String inputPlaceInLine)
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
    	 String searchSql = "SELECT COUNT(*) " +
                 "FROM \"Words\" sw " +
                 "JOIN \"Songs\" s ON sw.\"SongId\" = s.\"ID\" " +
                 "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                 "WHERE 1=1 ";
    	 
    	 if(inputSong != "")
    	 {
    		 if(FilterSong)
    		 {
    			 searchSql = searchSql + "AND s.\"Title\" ILIKE '%"+ inputSong + "%'";
    		 }
    		 else
    		 {
    			 searchSql = searchSql + "AND s.\"Title\" = '"+ inputSong + "'";
    		 }
    	 }
    	 
    	 if(inputWord != "")
    	 {
    		 if(FilterWord)
    		 {
    			 searchSql = searchSql + "AND sw.\"Word\" ILIKE  '%" + inputWord + "%' ";
    		 }
    		 else
    		 {
    			 searchSql = searchSql + "AND sw.\"Word\" ILIKE  '" + inputWord + "' ";
    		 }
    	 }
    	 
    	 if(inputGroup != "")
    	 {
    		 if(FilterWord)
    		 {
    			 searchSql = searchSql + "AND w.\"GroupName\" ILIKE  '%" + inputGroup + "%' ";
    		 }
    		 else
    		 {
    			 searchSql = searchSql + "AND w.\"GroupName\" ILIKE  '" + inputGroup + "' ";
    		 }
    	 }
    	
    	 if(inputVerse != "")
    	 {
    		 searchSql = searchSql + "AND sw.\"VerseNumber\" =  '" + inputVerse + "' ";
    	 }
    	 if(inputLine != "")
    	 {
    		 searchSql = searchSql + "AND sw.\"LineNumber\" =  '" + inputLine + "' ";
    	 }
    	 if(inputPlaceInLine != "")
    	 {
    		 searchSql = searchSql + "AND sw.\"PlaceInLine\" =  '" + inputPlaceInLine + "' ";
    	 }
    	     	   

    	   
    	   try 
           (     
        	    PreparedStatement stmt = conn.prepareStatement(searchSql);
                ResultSet rs = stmt.executeQuery()
           ) 
           {

               while (rs.next()) 
               {
            	   //public SongWord(int id,String word, String song,String group,  int verse, int line, int lineplace,int numberOfInstances)
            	   return  rs.getInt(1);
            	               	   
               }

           } 
           catch (SQLException e) 
           {
               e.printStackTrace();
               return 0;
           }

                 
    
    	return 0;
    	
    }
    
    
    public ArrayList<SongWord> FilterGroupWords(String inputStartIndex, String maxInPage,String inputWord,Boolean FilterWord, String inputSong, Boolean FilterSong,  String inputGroup,Boolean FilterGroup,String inputVerse,String inputLine,String inputPlaceInLine)
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
    	 String searchSql = "SELECT * " +
                 "FROM \"Words\" sw " +
                 "JOIN \"Songs\" s ON sw.\"SongId\" = s.\"ID\" " +
                 "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                 "WHERE 1=1 ";
    	 
    	 if(inputSong != "")
    	 {
    		 if(FilterSong)
    		 {
    			 searchSql = searchSql + "AND s.\"Title\" ILIKE '%"+ inputSong + "%'";
    		 }
    		 else
    		 {
    			 searchSql = searchSql + "AND s.\"Title\" = '"+ inputSong + "'";
    		 }
    	 }
    	 
    	 if(inputWord != "")
    	 {
    		 if(FilterWord)
    		 {
    			 searchSql = searchSql + "AND sw.\"Word\" ILIKE  '%" + inputWord + "%' ";
    		 }
    		 else
    		 {
    			 searchSql = searchSql + "AND sw.\"Word\" ILIKE  '" + inputWord + "' ";
    		 }
    	 }
    	 
    	 if(inputGroup != "")
    	 {
    		 if(FilterWord)
    		 {
    			 searchSql = searchSql + "AND w.\"GroupName\" ILIKE  '%" + inputGroup + "%' ";
    		 }
    		 else
    		 {
    			 searchSql = searchSql + "AND w.\"GroupName\" ILIKE  '" + inputGroup + "' ";
    		 }
    	 }
    	
    	 if(inputVerse != "")
    	 {
    		 searchSql = searchSql + "AND sw.\"VerseNumber\" =  '" + inputVerse + "' ";
    	 }
    	 if(inputLine != "")
    	 {
    		 searchSql = searchSql + "AND sw.\"LineNumber\" =  '" + inputLine + "' ";
    	 }
    	 if(inputPlaceInLine != "")
    	 {
    		 searchSql = searchSql + "AND sw.\"PlaceInLine\" =  '" + inputPlaceInLine + "' ";
    	 }
    	 
    	   searchSql = searchSql + "ORDER BY sw.\"VerseNumber\" ASC, sw.\"LineNumber\" ASC, sw.\"PlaceInLine\" ";
    	   
    		searchSql = searchSql+ " LIMIT "+maxInPage+" OFFSET "+ inputStartIndex;
    	   
    	   try 
           (     
        	    PreparedStatement stmt = conn.prepareStatement(searchSql);
                ResultSet rs = stmt.executeQuery()
           ) 
           {

               while (rs.next()) 
               {
            	   //public SongWord(int id,String word, String song,String group,  int verse, int line, int lineplace,int numberOfInstances)
            	   int id = rs.getInt("ID");
            	   String word = rs.getString("Word");
            	   String song = rs.getString("Title");
            	   String group = rs.getString("GroupName");
            	   int verse = rs.getInt("VerseNumber");
            	   int line = rs.getInt("LineNumber");;
            	   int lineplace = rs.getInt("PlaceInLine");
            	   
            	   words.add(new SongWord(id,word,song,group,verse,line,lineplace));            	   
               }

           } 
           catch (SQLException e) 
           {
               e.printStackTrace();
               return null;
           }

                 
    
    	return words;
    	
    }
    
     

}
