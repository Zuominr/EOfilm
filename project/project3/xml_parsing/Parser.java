


import EOfilm.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
 

public class Parser extends DefaultHandler{

	movie NewMovie;
	star NewStar;
	LinkedHashMap<String, Integer>Title_to_Genre = new LinkedHashMap<String, Integer>();
	LinkedHashMap<Integer, ArrayList<String>>MovieID_GenreID = new LinkedHashMap<Integer, ArrayList<String>>();
	LinkedHashMap<String, String>fid_movieid = new LinkedHashMap<String, String>();
	ArrayList<String> fids = new ArrayList<String>();
	ArrayList<movie> movie_batch_values = new ArrayList<movie>();
	ArrayList<String> genre_in_movie_batch_values = new ArrayList<String>();
	String DirectorName;
	Boolean hasFid = false;
	String tempVal;
	String value_begin = "(";
	String value_end = ")";
	int n=0;
	dbFunctions conn = new dbFunctions();
	

	public void parseDocument(){
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			try{
				conn.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");
				//conn.make_connection("jdbc:mysql://localhost:3306/moviedb_project3_grading", "classta", "classta");
				}catch(Exception e)
				{}
			//parse the file and also register this class for call backs
			sp.parse("mains243.xml", this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}

	
	}

	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("film"))
		{
			NewMovie = new movie();
			NewMovie.setDirector(DirectorName);
		}

	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if(qName.equalsIgnoreCase("dirname"))
		{
			DirectorName = tempVal;
		}
		else if (qName.equalsIgnoreCase("t"))
		{
			String title = tempVal;
			if("".equals(tempVal))
				title = "No Title";
			NewMovie.setTitle(title);
		}
		else if (qName.equalsIgnoreCase("fid"))
		{	
			
			if(fids.contains(tempVal)) {
				String k = Integer.toString(n);
				tempVal=tempVal+k;
				n+=1;
				
			}
			if(!"".equals(tempVal) )
			{
				fids.add(tempVal);
				hasFid = true;
			}
		}
		else if (qName.equalsIgnoreCase("year"))
		{
			Integer year = 0;
			try
			{
				year = Integer.parseInt(tempVal);
			}catch(Exception e)
			{
				//Do Nothin;
				//User year zero if it fails
			}
				NewMovie.setYear(year);
		}
		else if (qName.equalsIgnoreCase("cat"))
		{
			
			if(tempVal==null) {
				NewMovie.addGenre("null");
			}
				else {
			NewMovie.addGenre(tempVal);
				}
		}
		else if (qName.equalsIgnoreCase("film"))
		{
			try {
				if(hasFid){
					append_to_queries();
					hasFid = false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(qName.equalsIgnoreCase("movies"))
		{
			try {
				execute_batch_inserts();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void append_to_queries() throws Exception
	{
		//Make Movie query
		add_movie_query();
		add_genre();
	}
	public void add_movie_query()
	{
		//replace gets rid of single apostrophe which will throw sql statemnt of
		movie_batch_values.add(NewMovie);
	}
	public void add_genre() throws Exception
	{	
		for(String genre: NewMovie.getGenres())
		{
			if(genre != null  && !"".equals(genre))
			{
				Integer genre_id = conn.getGenreIdFromName(genre);
				if(genre_id < 0)
				{
					genre_id = conn.insert_genre(genre);
				}
			Title_to_Genre.put(NewMovie.getTitle(), genre_id);
			}
		}
	}
	
	public void execute_batch_inserts() throws SQLException
	{
		//do batch for movie
		//String final_batch_query = get_final_movie_batch_query();
		ResultSet rs = conn.movie_batch_insert(movie_batch_values);
		
		//do batch for genres_in_movies
		//make_movieID_genreID_map(rs);
		//build_genres_in_movies_values();
		//String final_genres_in_movies_query = get_final_gim_query();
		//conn.gim_batch_insert(final_genres_in_movies_query);
	}
	
	public String get_final_gim_query()
	{
		String ret = "INSERT INTO genres_in_movies (genreId, movieId) VALUES ";
		int count = genre_in_movie_batch_values.size();
		for(int i = 0; i < count; ++i)
		{
			ret += genre_in_movie_batch_values.get(i);
			if(i != count - 1)
			{
				ret += ",";
			}
		}
		return ret;
	}
	public void build_genres_in_movies_values()
	{
		String val;
		for(Map.Entry<Integer, ArrayList<String>> c : MovieID_GenreID.entrySet())
		{
			for(String movie_id: c.getValue())
			{
				val = value_begin + c.getKey() + "," + "'" + movie_id +"'"+ value_end;
				genre_in_movie_batch_values.add(val);
			}
		}
	}
	
	public void make_movieID_genreID_map(ResultSet rs) throws SQLException
	{
		int i = 0;
		while(rs.next()){
			String movie_id = movie_batch_values.get(i).getId();
			String title = movie_batch_values.get(i).getTitle();
			Integer genre_id = Title_to_Genre.get(title);
			//Have to check if the movie has any genres associated with it
			//if it doesnt then it wont be in Title_to_Genre
			
			fid_movieid.put(fids.get(i), movie_id);
			++i;
			
			if(genre_id != null)
			{
				if(MovieID_GenreID.containsKey(genre_id))
					MovieID_GenreID.get(genre_id).add(movie_id);
				else
				{
					ArrayList<String> mIDList = new ArrayList<String>();
					mIDList.add(movie_id);
					MovieID_GenreID.put(genre_id, mIDList);
				}
			}
		}
	}
	
	
	public LinkedHashMap<String, String> fid_mid()
	{
		return fid_movieid;
	}
	
	public void error(SAXParseException e)
	{
		e.getMessage();
	}

	
}


	

