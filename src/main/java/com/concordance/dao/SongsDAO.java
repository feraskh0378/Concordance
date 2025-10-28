package com.concordance.dao;


import com.concordance.model.Song;
import com.concordance.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SongsDAO 
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
            e.printStackTrace();
        }
		
	}
	
	 public int getSongsSize() 
	    {
	    	ArrayList<Song> Songs = new ArrayList<>();
	        String searchSql = "SELECT COUNT(*) FROM \"Songs\" ";
	        			 
	        
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
	        }

	        return 0;
	    }
	
    public ArrayList<Song> getAllSongs(String inputStartIndex, String maxInPage) 
    {
    	ArrayList<Song> Songs = new ArrayList<>();
        String searchSql = "SELECT * FROM \"Songs\" "+
        			 "LIMIT "+maxInPage+" OFFSET "+ inputStartIndex;
                

        try 
        (        	
             PreparedStatement stmt = conn.prepareStatement(searchSql);
             ResultSet rs = stmt.executeQuery()
        ) 
        {
            while (rs.next()) 
            {
            	Song s = new Song(rs.getInt("ID"),rs.getString("Title"),rs.getString("Poet"),rs.getString("Composer"),rs.getString("Date"),rs.getInt("NumberOfVerses"),rs.getInt("NumberOfLines"),rs.getInt("NumberOfWords"));
            	Songs.add(s);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return Songs;
    }
    
    public int filterSongsSize(String inputStartIndex, String maxInPage,String inputTitle,String inputPoet,String inputComposer,String inputDate,String inputNumberOfVerses,String inputNumberOfLines,String inputNumberOfWords) 
    {
    	ArrayList<Song> Songs = new ArrayList<>();
        String searchSql = "SELECT * FROM \"Songs\" "+
        			 "WHERE 1=1 ";
        
   	 if(!inputTitle.isEmpty())
	 {
   		 searchSql = searchSql + "AND s.\"Title\" ILIKE '%"+ inputTitle + "%'";				
	 }
   	 if(!inputPoet.isEmpty())
	 {
  		 searchSql = searchSql + "AND s.\"Poet\" ILIKE '%"+ inputPoet + "%'";				
	 }
   	 if(!inputComposer.isEmpty())
	 {
  		 searchSql = searchSql + "AND s.\"Composer\" ILIKE '%"+ inputComposer + "%'";				
	 }
   	if(!inputNumberOfVerses.isEmpty())
	 {
  		 searchSql = searchSql + "AND s.\"NumberOfVerses\" = '"+ inputNumberOfVerses + "'";				
	 }
   	if(!inputNumberOfLines.isEmpty())
	 {
  		 searchSql = searchSql + "AND s.\"NumberOfLines\" = '"+ inputNumberOfLines + "'";				
	 }
   	if(!inputNumberOfWords.isEmpty())
	 {
 		 searchSql = searchSql + "AND s.\"NumberOfWords\" = '"+ inputNumberOfWords + "'";				
	 }
   	if(!inputDate.isEmpty())
	 {
		String[] parts = inputDate.split("-");
		if(parts.length == 1)
		{
			searchSql = searchSql + " AND ( EXTRACT(YEAR FROM \"Date\") = '"+ parts[0] + "' "+"OR EXTRACT(MONTH FROM \"Date\") = '"+ parts[0] + "' "+"OR EXTRACT(DAY FROM \"Date\") = '"+ parts[0] + "')";
			
		}
		if(parts.length == 2)
		{
			searchSql = searchSql + " AND ( EXTRACT(YEAR FROM \"Date\") = '"+ parts[0] + "' "+"OR EXTRACT(MONTH FROM \"Date\") = '"+ parts[1] + "' "+"OR EXTRACT(DAY FROM \"Date\") = '"+ parts[1] + "')";
			
		}		
		if(parts.length == 3)
		{
			searchSql = searchSql + " AND ( EXTRACT(YEAR FROM \"Date\") = '"+ parts[0] + "' "+"OR EXTRACT(MONTH FROM \"Date\") = '"+ parts[1] + "' "+"OR EXTRACT(DAY FROM \"Date\") = '"+ parts[2] + "')";
			
		}		
	 }
        

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
        }

        return 0;
    }

    

    public ArrayList<Song> filterSongs(String inputStartIndex, String maxInPage,String inputTitle,String inputPoet,String inputComposer,String inputDate,String inputNumberOfVerses,String inputNumberOfLines,String inputNumberOfWords) 
    {
    	ArrayList<Song> Songs = new ArrayList<>();
        String searchSql = "SELECT * FROM \"Songs\" "+
        			 "WHERE 1=1";
        
   	 if(!inputTitle.isEmpty())
	 {
   		 searchSql = searchSql + " AND \"Title\" ILIKE '%"+ inputTitle + "%'";				
	 }
   	 if(!inputPoet.isEmpty())
	 {
  		 searchSql = searchSql + " AND \"Poet\" ILIKE '%"+ inputPoet + "%'";				
	 }
   	 if(!inputComposer.isEmpty())
	 {
  		 searchSql = searchSql + " AND \"Composer\" ILIKE '%"+ inputComposer + "%'";				
	 }
   	if(!inputNumberOfVerses.isEmpty())
	 {
  		 searchSql = searchSql + " AND \"NumberOfVerses\" = '"+ inputNumberOfVerses + "'";				
	 }
   	if(!inputNumberOfLines.isEmpty())
	 {
  		 searchSql = searchSql + " AND \"NumberOfLines\" = '"+ inputNumberOfLines + "'";				
	 }
   	if(!inputNumberOfWords.isEmpty())
	 {
 		 searchSql = searchSql + " AND \"NumberOfWords\" = '"+ inputNumberOfWords + "'";				
	 }
	if(!inputDate.isEmpty())
	 {
		String[] parts = inputDate.split("-");
		if(parts.length == 1)
		{
			searchSql = searchSql + " AND ( EXTRACT(YEAR FROM \"Date\") = '"+ parts[0] + "' "+"OR EXTRACT(MONTH FROM \"Date\") = '"+ parts[0] + "' "+"OR EXTRACT(DAY FROM \"Date\") = '"+ parts[0] + "')";
			
		}
		if(parts.length == 2)
		{
			searchSql = searchSql + " AND  EXTRACT(YEAR FROM \"Date\") = '"+ parts[0] + "' "+"AND ( EXTRACT(MONTH FROM \"Date\") = '"+ parts[1] + "' "+"OR EXTRACT(DAY FROM \"Date\") = '"+ parts[1] + "')";
			
		}		
		if(parts.length == 3)
		{
			searchSql = searchSql + " AND ( EXTRACT(YEAR FROM \"Date\") = '"+ parts[0] + "' "+"AND EXTRACT(MONTH FROM \"Date\") = '"+ parts[1] + "' "+"AND EXTRACT(DAY FROM \"Date\") = '"+ parts[2] + "')";
			
		}		
	 }
   	
   	searchSql = searchSql+ " LIMIT "+maxInPage+" OFFSET "+ inputStartIndex;
   	 
        

        try 
        (        	
             PreparedStatement stmt = conn.prepareStatement(searchSql);
             ResultSet rs = stmt.executeQuery()
        ) 
        {
            while (rs.next()) 
            {
            	Song s = new Song(rs.getInt("ID"),rs.getString("Title"),rs.getString("Poet"),rs.getString("Composer"),rs.getString("Date"),rs.getInt("NumberOfVerses"),rs.getInt("NumberOfLines"),rs.getInt("NumberOfWords"));
            	Songs.add(s);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return Songs;
    }

    
    public int addSong(Song song) 
    {
    	
        String sql = "INSERT INTO \"Songs\" (\"Title\", \"Poet\", \"Composer\", \"NumberOfVerses\",\"NumberOfLines\",\"NumberOfWords\",\"Date\") VALUES (?, ?, ?, ?, ? , ?,?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getPoet());
            stmt.setString(3, song.getComposer());
            stmt.setInt(4, song.getNumberOfVerses());
            stmt.setInt(5, song.getNumberOfLines());
            stmt.setInt(6, song.getNumberOfWords());
            stmt.setDate(7, Date.valueOf(song.getDate()));
            int affectedRows =   stmt.executeUpdate();
            
            if(affectedRows == 1)
            {
            	try (ResultSet rs = stmt.getGeneratedKeys()) 
            	{
                    if (rs.next()) 
                    {
                        int generatedId = rs.getInt(1);
                        return generatedId;
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
    
    public void updateSong(int newSongID,Song song)
    {
    	String updateSql = "UPDATE \"Songs\" SET \"NumberOfVerses\" = ? , \"NumberOfLines\" = ? , \"NumberOfWords\" = ?  WHERE \"ID\" = ?";
    	try (PreparedStatement updateStmt = conn.prepareStatement(updateSql))
    	{    		    		
    		updateStmt.setInt(1,song.getNumberOfVerses());
    		updateStmt.setInt(2,song.getNumberOfLines());
    		updateStmt.setInt(3,song.getNumberOfWords());    		
    		updateStmt.setInt(4,newSongID);
    		updateStmt.executeUpdate(); 
    		return ;
    	}
    	catch (SQLException e) 
        {
            e.printStackTrace();
            return ;
        }  
    }
    
    public Song getSong(int SongID)
    {
    	String Sql = "SELECT * FROM \"Songs\"  WHERE \"ID\" = ?";
    	try (PreparedStatement sqlStmt = conn.prepareStatement(Sql))
    	{    		    		    		    		
    		 sqlStmt.setInt(1,SongID);
    		 ResultSet rs = sqlStmt.executeQuery(); 
    		 while (rs.next()) 
	         {
    			 return  new Song(rs.getInt("ID"),rs.getString("Title"),rs.getString("Poet"),rs.getString("Composer"),rs.getString("Date"),rs.getInt("NumberOfVerses"),rs.getInt("NumberOfLines"),rs.getInt("NumberOfWords"));
	         }
    	}
    	catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }  
    	return null;
    }
    	
    
}
