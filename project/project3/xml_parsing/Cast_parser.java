


import EOfilm.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
 

public class Cast_parser extends DefaultHandler{

	private String tempVal;
	
	private LinkedHashMap<String, String> fid_movie = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> starid_movieid = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> name_starid = new LinkedHashMap<String, String>();
	private String fid;
	private String movie_star;
	private dbFunctions conn = new dbFunctions();
	
	public void parseDocument(LinkedHashMap<String, String> fid_movie, LinkedHashMap<String, String> name_id){
		this.fid_movie = fid_movie;
		this.name_starid = name_id;
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
			sp.parse("casts124.xml", this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		conn.close();

	}

	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
	}

	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if(qName.equalsIgnoreCase("f"))
		{
			fid = tempVal;
		}
		else if(qName.equalsIgnoreCase("a"))
		{
			movie_star = tempVal;
			String starid = name_starid.get(movie_star);
			String movieid = fid_movie.get(fid);

			if(starid != null && movieid != null)
			{
				starid_movieid.put(starid, movieid);
			}
			movie_star = "";
			fid = "";
		}
		else if(qName.equals("casts"))
		{
			execute_batch();
		}
	}
	public void execute_batch()
	{
		try {
			conn.starid_movieid_batch(starid_movieid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void error(SAXParseException e)
	{
		e.getMessage();
	}

	
}


	

