package com.concordance.dao;


import com.concordance.model.Song;
import com.concordance.model.SongWord;
import com.concordance.model.Word;
import com.concordance.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordsDAO 
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
	
	 public int getSongId(int wordID)
	    {
	    	String Sql = "SELECT \"SongId\" FROM \"Words\"  WHERE \"ID\" = ?";
	    	try (PreparedStatement sqlStmt = conn.prepareStatement(Sql))
	    	{    		    		    		    		
	    		 sqlStmt.setInt(1,wordID);
	    		 ResultSet rs = sqlStmt.executeQuery(); 
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

	//public Word(int id,String word,String group, int numberOfInstances, int numberOfLetters)
	
	  public ArrayList<Word> getAllWords(String inputStartIndex, String maxInPage) 
	    {
	    	/*
	    	 * 	   String inputWord     = request.getParameter("Word");
	       String inputGroup     = request.getParameter("Group");       
	       String inputNumberOfInstances     = request.getParameter("NumberOfInstances");           
	       String inputNumberOfChars = request.getParameter("NumberOfChars");
	    	 */
	    	ArrayList<Word> Words = new ArrayList<>();
	    	String sql = " SELECT g.\"GroupName\", w.\"Word\", COUNT(*) AS numberOfInstances , LENGTH(w.\"Word\") AS numberOfChars" +
	        		     " FROM \"Words\" w " +
	        			 " JOIN \"Groups\" g "+
	        		     " ON w.\"GroupId\" = g.\"ID\" "+
	        		     " GROUP BY g.\"GroupName\", w.\"Word\" "+
	        		     "LIMIT "+maxInPage+" OFFSET "+ inputStartIndex;
	        
	     

	        try 
	        (
	        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
	             ResultSet rs = stmt.executeQuery()
	        ) 
	        {

	            while (rs.next()) 
	            {
	            		            	
	            	Word s = new Word(rs.getString("Word"),rs.getString("GroupName"),"",0,0,0,0,rs.getInt("numberOfInstances"));
	            	Words.add(s);
	            }

	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }

	        return Words;
	    }
	  
	  public int getAllWordsSize(String inputStartIndex, String maxInPage) 
	    {
	    	/*
	    	 * 	   String inputWord     = request.getParameter("Word");
	       String inputGroup     = request.getParameter("Group");       
	       String inputNumberOfInstances     = request.getParameter("NumberOfInstances");           
	       String inputNumberOfChars = request.getParameter("NumberOfChars");
	    	 */
	    	ArrayList<Word> Words = new ArrayList<>();
	    	String sql = " SELECT COUNT(*)  FROM \"Words\" ";	         		     
	        
	     

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
	  
    public ArrayList<Word> FilterWords(String inputStartIndex , String inputMaxSize,String inputWord , String inputGroup,String inputNumberOfInstances,String inputNumberOfChars ) 
    {
    	/*
    	 * 	   String inputWord     = request.getParameter("Word");
       String inputGroup     = request.getParameter("Group");       
       String inputNumberOfInstances     = request.getParameter("NumberOfInstances");           
       String inputNumberOfChars = request.getParameter("NumberOfChars");
    	 */
    	ArrayList<Word> Words = new ArrayList<>();
    	String sql = " SELECT g.\"GroupName\", w.\"Word\", COUNT(*) AS numberOfInstances , LENGTH(w.\"Word\") AS numberOfChars" +
        		     " FROM \"Words\" w " +
        			 " JOIN \"Groups\" g "+
        		     " ON w.\"GroupId\" = g.\"ID\" "+
        			 " WHERE 1=1 ";
        
        if(!inputWord.isEmpty())
        {
        	sql = sql + "AND  w.\"Word\" LIKE  '%"+inputWord+ "%'";
        }
        
        if(!inputGroup.isEmpty())
        {
        	sql = sql + "AND  g.\"GroupName\" LIKE '%"+inputGroup+ "%'";        	         	
        }
        
        sql = sql + " GROUP BY g.\"GroupName\", w.\"Word\" ";
        sql = sql + "HAVING COUNT(*) > 0 ";
        		     
        if(!inputNumberOfInstances.isEmpty())
        {
        	sql = sql + "AND COUNT(*) ="+inputNumberOfInstances;
        }
        if(!inputNumberOfChars.isEmpty())
        {
        	sql = sql + "AND LENGTH(w.\"Word\") ="+inputNumberOfChars;
        }
        sql = sql +  " LIMIT "+inputMaxSize+" OFFSET "+ inputStartIndex;

        try 
        (
        	 PreparedStatement stmt = conn.prepareStatement(sql);        		
             ResultSet rs = stmt.executeQuery()
        ) 
        {

            while (rs.next()) 
            {
            	//public Word(String word,String group,String songTitle,int verseNumber, int lineNumber, int placeInLine, int numberOfInstancesInSong, int numberOfInstancesInDB )
            	
            	Word s = new Word(rs.getString("Word"),rs.getString("GroupName"),"",0,0,0,0,rs.getInt("numberOfInstances"));
            	Words.add(s);
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return Words;
    }
    
    public int FilterWordsSize(String inputStartIndex , String inputMaxSize,String inputWord , String inputGroup,String inputNumberOfInstances,String inputNumberOfChars ) 
    {
    	/*
    	 * 	   String inputWord     = request.getParameter("Word");
       String inputGroup     = request.getParameter("Group");       
       String inputNumberOfInstances     = request.getParameter("NumberOfInstances");           
       String inputNumberOfChars = request.getParameter("NumberOfChars");
    	 */
    	ArrayList<Word> Words = new ArrayList<>();
    	String sql = "SELECT COUNT(*) FROM ( SELECT COUNT(*) " +
        		     " FROM \"Words\" w " +
        			 " JOIN \"Groups\" g "+
        		     " ON w.\"GroupId\" = g.\"ID\" "+
        			 " WHERE 1=1 ";
        
        if(!inputWord.isEmpty())
        {
        	sql = sql + "AND  w.\"Word\" LIKE  '%"+inputWord+ "%'";
        }
        
        if(!inputGroup.isEmpty())
        {
        	sql = sql + "AND  g.\"GroupName\" LIKE '%"+inputGroup+ "%'";        	         	
        }
        
        sql = sql + " GROUP BY g.\"GroupName\", w.\"Word\" ";
        sql = sql + "HAVING COUNT(*) > 0 ";
        		     
        if(!inputNumberOfInstances.isEmpty())
        {
        	sql = sql + "AND COUNT(*) ="+inputNumberOfInstances;
        }
        if(!inputNumberOfChars.isEmpty())
        {
        	sql = sql + "AND LENGTH(w.\"Word\") ="+inputNumberOfChars;
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
    
    public int getSongWordsSize(String SongId, String StartIndex,String maxInPage) 
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
   	 String searchSql = "SELECT COUNT(*) " +
                "FROM \"Words\" sw " +
                "JOIN \"Songs\" s ON sw.\"SongId\" = s.\"ID\" " +
                "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                "WHERE 1=1 AND s.\"ID\" = "+ SongId;
                
   	   	      	  
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
    
    
    public ArrayList<SongWord> getSongWords(String SongId, String StartIndex,String maxInPage) 
    {
    	ArrayList<SongWord> words = new ArrayList<SongWord>();
    	
    	 String searchSql = "SELECT * " +
                 "FROM \"Words\" sw " +
                 "JOIN \"Songs\" s ON sw.\"SongId\" = s.\"ID\" " +
                 "JOIN \"Groups\" w ON sw.\"GroupId\" = w.\"ID\" "+
                 "WHERE 1=1 AND s.\"ID\" = "+ SongId + " ";
                 
    	 
    	
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
    
    
    public ArrayList<SongWord> FilterSongWords(String inputStartIndex, String maxInPage,String inputWord,Boolean FilterWord, String inputSong, Boolean FilterSong,  String inputGroup,Boolean FilterGroup,String inputVerse,String inputLine,String inputPlaceInLine)
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
    
     

    
    public int addWord(Word word, int songID) 
    {
    	String insertSql = "INSERT INTO \"Words\" (\"Word\", \"SongId\", \"GroupId\", \"VerseNumber\", \"LineNumber\", \"PlaceInLine\") VALUES (?, ?, ?, ?, ?, ?)";
    	try (PreparedStatement insertStmt = conn.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS))
    	{    		    		
    		insertStmt.setString(1,word.getWord());
    		insertStmt.setInt(2,songID);
    		insertStmt.setInt(3,0);    		
    		insertStmt.setInt(4,word.getVerseNumber());   
    		insertStmt.setInt(5,word.getLineNumber());
    		insertStmt.setInt(6,word.getPlaceInLine());
    		int affectedRows = insertStmt.executeUpdate();     		        
            if(affectedRows == 1)
            {
            	try (ResultSet rs = insertStmt.getGeneratedKeys()) 
            	{
                    if (rs.next()) 
                    {                    	 
                    	return rs.getInt(1);
                    }
            	}
            	catch (SQLException e) 
                {
                    e.printStackTrace();
                    return 0;
                }            
            }
    		return 0;
    	}
    	catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }  
    }
}
