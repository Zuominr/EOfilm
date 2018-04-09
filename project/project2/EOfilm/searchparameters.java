package EOfilm;

public class searchparameters 
{

	private String title = "";
	private String year = "";
	private String director= "";
	private String starName ="";
	private String sortType = "";
	private String moviePerPage = "";
	private String currentPage= "";
	private String genre= "";
	private Boolean sortAccending = true;
	private Boolean fromBrowse = false;
	private Boolean byTitle = true;
	/**
	 * @param title
	 * @param year
	 * @param director
	 * @param starName
	 * @param sortType
	 * @param moviePerPage
	 * @param currentPage
	 * @param sortAccending
	 */
	public searchparameters(String title, String year, String director, String starName, String sortType, String moviePerPage, String currentPage, Boolean sortAccending) {
		this.title = title.trim();
		this.year = year.trim();
		this.director = director.trim();
		this.starName = starName.trim();
		this.sortType = sortType.trim();
		this.moviePerPage = moviePerPage.trim();
		this.currentPage = currentPage.trim();
		this.sortAccending = sortAccending;
	}
	public searchparameters() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title == null ? "" : title.trim();
	}
	
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre == null ? "" : genre.trim();
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year == null ? "" : year.trim();
	}
	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director  == null ? "" : director.trim();
	}
	/**
	 * @return the starName
	 */
	public String getStarName() {
		return starName;
	}
	/**
	 * @param starName the starName to set
	 */
	public void setStarName(String starName) {
		this.starName = starName  == null ? "" : starName.trim();
	}
	/**
	 * @return the sortType
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType  == null ? "" : sortType.trim();
	}
	/**
	 * @return the moviePerPage
	 */
	public String getMoviePerPage() {
		return moviePerPage;
	}
	/**
	 * @param moviePerPage the moviePerPage to set
	 */
	public void setMoviePerPage(String moviePerPage) {
		this.moviePerPage = moviePerPage  == null ? "" : moviePerPage.trim();
	}
	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage  == null ? "" : currentPage.trim();
	}
	/**
	 * @return the sortAccending
	 */
	public Boolean getSortAccending() {
		return sortAccending;
	}
	/**
	 * @param sortAccending the sortAccending to set
	 */
	public void setSortAccending(Boolean sortAccending) {
		this.sortAccending = sortAccending  == null ? true : sortAccending;
	}
	public Boolean getFromBrowse() {
		return fromBrowse;
	}
	public void setFromBrowse(Boolean fromBrowse) {
		this.fromBrowse = fromBrowse == null ? true : fromBrowse;
	}
	public Boolean getByTitle() {
		return byTitle;
	}
	public void setByTitle(Boolean byTitle) {
		this.byTitle = byTitle == null ? true : byTitle;
	}
}
