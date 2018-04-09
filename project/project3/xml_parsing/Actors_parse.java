

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
 

public class Actors_parse extends DefaultHandler{

	String tempVal;
	star NewStar;
	ArrayList<String> star_full_name = new ArrayList<String>();
	LinkedHashMap<String, String> star_name_to_id = new LinkedHashMap<String, String>();
	ArrayList<star>star_batch_values = new ArrayList<star>();
	String value_begin = "(";
	String value_end = ")";
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
			sp.parse("actors63.xml", this);
			
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
		if(qName.equalsIgnoreCase("actor"))
		{
			NewStar = new star();
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		
		if(qName.equalsIgnoreCase("stagename"))
		{
			
			NewStar.setName(tempVal);
			star_full_name.add(tempVal);
		}
		
		else if(qName.equalsIgnoreCase("dob"))
		{
			Integer syear = 0;
			try
			{
				syear = Integer.parseInt(tempVal);
			}catch(Exception e)
			{
				//Do Nothin;
				//User year zero if it fails
			}
			NewStar.setBirthYear(syear);
		}
		
		else if(qName.equalsIgnoreCase("actor"))
		{
			star_batch_values.add(NewStar);
		}
		else if(qName.equalsIgnoreCase("actors"))
		{
			try{
			execute_star_batch();
			}
			catch(Exception e)
			{
				e.getMessage();
			}
		}
	}
	
	public void execute_star_batch() throws SQLException
	{
		ResultSet rs = conn.star_batch_insert(star_batch_values);
		
		build_name_to_id(rs);
		
		
	}
	
	public void build_name_to_id(ResultSet rs) throws SQLException 
	{
		int i = 0;
		while(rs.next()){
			String star_id = rs.getString(1);
			String full_name = star_full_name.get(i);
			++i;
			//Have to check if the movie has any genres associated with it
			//if it doesnt then it wont be in Title_to_Genre
			star_name_to_id.put(full_name, star_id);
		}
	}
	public LinkedHashMap<String, String> get_star_id_map()
	{
		return star_name_to_id;
	}
	
	public void error(SAXParseException e)
	{
		e.getMessage();
	}

	
}


	

