package EOfilm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
@SuppressWarnings("serial")
public class star implements java.io.Serializable
{
	private String id;
	private String name;
	private Integer birthYear;
	private HashMap<String, String> movies;
	public star(String id, String name, Integer birthYear, HashMap<String, String> movies)
	{
		super();
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
		this.movies = movies;
	}
	public star()
	{
		this.birthYear = 0;
		this.name = "";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
        return date != null;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	
	
	public HashMap<String, String> getMovies() {
		return movies;
	}
	public void setMovies(HashMap<String, String> movies) {
		this.movies = movies;
	}
	
}
	
	