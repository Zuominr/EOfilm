package EOfilm;


import java.util.HashSet;

@SuppressWarnings("serial")
public class movie implements java.io.Serializable
{
	private String id;
	private String title;
	private int year;
	private String director;
	private HashSet<String> genres = new HashSet<String>();
	private HashSet<String> stars;
	
	/**
	 * @return the id
	 */
	public String getId() 
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() 
	{
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the year
	 */
	public int getYear() 
	{
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) 
	{
		this.year = year;
	}
	/**
	 * @return the director
	 */
	public String getDirector() 
	{
		return director;
	}
	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) 
	{
		this.director = director;
	}
	
	public HashSet<String> getGenres() {
		return genres;
	}
	public void setGenres(HashSet<String> genres) {
		this.genres = genres;
	}
	
	public void addGenre(String genre)
	{
		this.genres.add(genre);
	}
	
	public HashSet<String> getStars() {
		return stars;
	}
	public void setStars(HashSet<String> stars) {
		this.stars = stars;
	}
	/**
	 * @param id
	 * @param title
	 * @param year
	 * @param director
	 * @param genres
	 * @param stars
	 */
	public movie(String id, String title, int year, String director, HashSet<String> genres, HashSet<String> stars) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.genres = genres;
		this.stars = stars;
	}
	public movie() {
		// TODO Auto-generated constructor stub
	}
	

}

