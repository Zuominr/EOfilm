package EOfilm;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class dbFunctions 
{
	private Connection connection;

	/**
	 * Establishes connection to database
	 * 
	 * @param path
	 *            - path to db
	 * @param user_name
	 *            - mysql username
	 * @param pass
	 *            - mysql password
	 */
	public void make_connection(String path, String user_name, String pass) throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(path, user_name, pass);
	}

	public void make_connection_without_pooling(String path, String user_name, String pass) throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(path, user_name, pass);
	}

	public Map<String,Map<String,String>> get_metadata() throws Exception 
	{
		DatabaseMetaData dbmd = connection.getMetaData();
		Map<String,Map<String,String>> output = new HashMap<String,Map<String,String>>();
		
		ResultSet tables = dbmd.getTables(null,null,null,null);

		while(tables.next())
		{
			String table_name = tables.getString(3);
			HashMap<String, String> currentTable = new HashMap<String, String>();
			output.put(table_name, currentTable);
			ResultSet cols = dbmd.getColumns(null,null,table_name, null);

			while(cols.next())
			{
				currentTable.put(cols.getString("COLUMN_NAME"),cols.getString("TYPE_NAME"));
			}
			System.out.println("");
		}
		tables.close();
		return output;
	}
	
	public double getMoviePrice(String movie_id)
	{
		return 15.99;
	}

	public void close() 
	{
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Warning:The database connection was not closed properly.");
		}
	}

	
	public user loginToEOfilm(String userName, String password) throws SQLException {
		user logged_in_user = null;
		if (userName == null || password == null)
			return null;
		String statementString = "SELECT * FROM customers WHERE email=\'" + userName + "\' AND password=\'" + password
				+ "\';";
		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		if (results.next()) {
			logged_in_user = new user(results.getInt("id"), results.getString("firstName"),
					results.getString("lastName"), results.getString("address"), results.getString("email"));

		}
		results.close();
		statement.close();
		return logged_in_user;
	}
	
	public boolean auth_creditionals(String email, String pass) throws SQLException
	{
		user u = loginToEOfilm(email, pass);
		if(u != null)
			return true;
		return false;
	}
	

	public LinkedHashMap<String, movie> search_movies(searchparameters curSearch) throws Exception {
		StringBuilder query = new StringBuilder("SELECT DISTINCT movies.id,title,year,director "
				+ "FROM stars "
				+ "INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId "
				+ "LEFT OUTER JOIN movies ON movies.id = stars_in_movies.movieId "
				+ "LEFT OUTER JOIN genres_in_movies ON genres_in_movies.movieId = movies.id WHERE ");

		build_query(query, curSearch);
		
		
		
		LinkedHashMap<String, movie> movie_list = new LinkedHashMap<String, movie>();

		PreparedStatement ps = connection.prepareStatement(query.toString());
		add_ps_parameters(ps, curSearch);
		ResultSet rs = ps.executeQuery();
		populate_list(movie_list, rs);
		rs.close();
		ps.close();
		return movie_list;
	}
	
	private void populate_list(LinkedHashMap<String, movie> ret_movies, ResultSet rs) throws Exception {
		while (rs.next()) {
			String movie_id = rs.getString("id");

			movie cMovie = new movie(movie_id, rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					 getGenreFromMovieId(movie_id),
					getStarFromMovieId(movie_id));

			ret_movies.put(movie_id, cMovie);
		}
	}
	
	public HashSet<String> getGenreFromMovieId(String id) throws SQLException 
	{
		String query = "select name FROM genres INNER JOIN genres_in_movies ON genres.id = genres_in_movies.genreId"
				+ 	" WHERE genres_in_movies.movieId =\'" + id + "\' ";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		HashSet<String> tempOutput = new HashSet<String>();
		while(rs.next())
		{
			tempOutput.add(rs.getString(1));
		}
		rs.close();
		ps.close();
		return tempOutput;
	}
	
	public HashSet<String> getStarFromMovieId(String id) throws SQLException 
	{
		String query = "select name FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId"
				+ 	" WHERE stars_in_movies.movieId =\'" + id + "\' ";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		HashSet<String> tempOutput = new HashSet<String>();
		while(rs.next())
		{
			tempOutput.add(rs.getString(1));
		}
		rs.close();
		ps.close();
		return tempOutput;
	}
	public ArrayList<movie> getmovieByTilte(int start, int limit, String letterOfTitle) throws SQLException {
		String statementString = "SELECT * FROM movies";
		if (!"".equals(letterOfTitle)) {
			statementString += " WHERE  title LIKE \'" + letterOfTitle + "%\' ";
		}
		statementString += " ORDER BY title ";
		if (limit > 0) {
			statementString += " LIMIT " + limit + " OFFSET " + (start > 0 ? start : 0);
		}

		statementString += ";";
		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		ArrayList<movie> output = new ArrayList<movie>();
		while (results.next()) {
			movie newMovie = new movie();
			newMovie.setTitle(results.getString("title"));
			newMovie.setId(results.getString("id"));
			newMovie.setDirector(results.getString("director"));
			newMovie.setYear(results.getInt("year"));
			output.add(newMovie);

		}
		results.close();
		statement.close();
		return output;
	}
	public LinkedHashMap<String, movie> getMoviesByGenre(searchparameters curParams) throws Exception  {
		String statementString = "SELECT * FROM movies WHERE id IN (SELECT movieId FROM genres_in_movies where genreId IN "
					+ " (SELECT id FROM genres WHERE name=\'" + curParams.getGenre() + "\'))";
		int offset = Integer.parseInt(curParams.getMoviePerPage()) * Integer.parseInt(curParams.getCurrentPage());
		statementString += (" ORDER BY " + curParams.getSortType() + " " + (curParams.getSortAccending() ? "ASC" : "DESC"));
		statementString += (" LIMIT " + curParams.getMoviePerPage() + " OFFSET " + offset);
		statementString += ";";
		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		LinkedHashMap<String, movie> output = new LinkedHashMap<String, movie>();

		populate_list(output, results);
		results.close();
		statement.close();
		return output;
	}
	
	public ArrayList<String> getGenreList() throws SQLException {
		String statementString = "SELECT name FROM genres";

		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		ArrayList<String> output = new ArrayList<String>();
		while (results.next()) {
			output.add(results.getString("name"));
		}

		results.close();
		statement.close();
		return output;
	}
	public int countMovieByGenre(String Genre) throws SQLException {
		String statementString = "SELECT COUNT(*) FROM movies";
		if (!"".equals(Genre)) {
			statementString += " WHERE  id IN (SELECT movieId FROM genres_in_movies where genreId IN "
					+ " (SELECT id FROM genres WHERE name=\'" + Genre + "\'))";
		}
		statementString += " ORDER BY title ";
		statementString += ";";
		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		int output = 0;
		if (results.next()) {
			output = results.getInt(1);
		}
		results.close();
		statement.close();
		return output;
	}
	public int countMovieByTilte(String letterOfTitle) throws SQLException {
		String statementString = "SELECT COUNT(*) FROM movies";
		if (!"".equals(letterOfTitle)) {
			statementString += " WHERE  title LIKE \'" + letterOfTitle + "%\' ";
		}
		statementString += ";";
		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		int output = 0;
		if (results.next()) {
			output = results.getInt(1);
		}
		results.close();
		statement.close();
		return output;
	}
	
	public movie returnMovieFromID(String movie_id) throws SQLException 
	{
		movie to_return = null;
		String stmt = "SELECT * from movies WHERE id =\'" + movie_id + "\' ";
		PreparedStatement ps = connection.prepareStatement(stmt);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			to_return  = new movie(movie_id, rs.getString("title"), rs.getInt("year"), rs.getString("director"),
						  getGenreFromMovieId(movie_id),
						getStarFromMovieId(movie_id));
		}
		return to_return;	
	}
	

	private Boolean add_constraint(StringBuilder query, String column, String cond_operator, boolean is_first) {
		if (!is_first) {
			query.append(" " + cond_operator + " ");
		}
		is_first = false;
		query.append(column + " LIKE ?");
		return is_first;
	}

	private void build_query(StringBuilder query, searchparameters curParams) {
		Boolean is_first = true;
		if (!"".equals(curParams.getTitle())) {
			query.append("MATCH(title) AGAINST( ? IN BOOLEAN MODE )");
			is_first = false;
		}
		if (!"".equals(curParams.getYear())) {
			is_first = add_constraint(query, "year", "AND", is_first);
		}
		if (!"".equals(curParams.getDirector())) {
			is_first = add_constraint(query, "director", "AND", is_first);
		}
		if (!"".equals(curParams.getStarName())) {
			is_first = add_constraint(query, "stars.name", "AND", is_first);
		}

		// add ORDER BY, LIMIT, OFFSET
		int offset = Integer.parseInt(curParams.getMoviePerPage()) * Integer.parseInt(curParams.getCurrentPage());
		query.append(" ORDER BY " + curParams.getSortType() + " " + (curParams.getSortAccending() ? "ASC" : "DESC"));
		query.append(" LIMIT " + curParams.getMoviePerPage() + " OFFSET " + offset);
	}
	
	private void add_ps_parameters(PreparedStatement ps, searchparameters curParams) throws SQLException {
		int count = 1;
		if (!"".equals(curParams.getTitle())) {
			ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(curParams.getTitle().split(" ")));
			int i = tokens.size();
			String cvTokens ="";
			while(i --> 0)
			{
				cvTokens += "+" + tokens.get(i).trim() + "*";
			}
			ps.setObject(count,cvTokens);
			++count;
		}
		if (!"".equals(curParams.getYear())) {
			ps.setObject(count, "%" + curParams.getYear()+ "%");
			++count;
		}
		if (!"".equals(curParams.getDirector())) {
			ps.setObject(count,"%" + curParams.getDirector()+ "%");
			++count;

		}
		if (!"".equals(curParams.getStarName())) {
			ps.setObject(count, "%" +curParams.getStarName()+ "%");
			++count;
			
		} 
		
	}
	public star getStarFromMovieIdAndName(String movie_id, String starname) throws SQLException
	{
		String star_id_stmt = "SELECT stars.id, stars.name, stars.birthYear FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId " +
				"INNER JOIN movies ON movies.id = stars_in_movies.movieId WHERE movies.id = ? AND stars.name = ? LIMIT 1";
		String movies_info_stmt = "SELECT movies.id, movies.title FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId "+
			   "INNER JOIN movies ON movies.id = stars_in_movies.movieId WHERE stars.id = ?";
		
		PreparedStatement star_ps = connection.prepareStatement(star_id_stmt);
		star_ps.setObject(1, movie_id);
		star_ps.setObject(2, starname);
		
		
		ResultSet star_rs = star_ps.executeQuery(); 
		String star_id = null;
		star star = null;
		if(star_rs.next())
		{
			star_id = star_rs.getString("id");
		
			PreparedStatement movie_ps = connection.prepareStatement(movies_info_stmt);
			movie_ps.setObject(1, star_id);
			ResultSet movie_rs = movie_ps.executeQuery();
			HashMap<String, String> movies = new HashMap<String, String>();
			
			while(movie_rs.next())
			{
				movies.put(movie_rs.getString("id"), movie_rs.getString("title"));
			}
			star = new star(star_id, star_rs.getString("stars.name"), star_rs.getInt("stars.birthYear"), movies);
		}
		else
		{
			star = new star(star_id, star_rs.getString("stars.name"), star_rs.getInt("stars.birthYear"), new HashMap<String, String>());
		}
		
		return star;
	}
	
	public void insert_sale(cart cart, customercheckout info) throws SQLException
	{
		String insert_stmt = "INSERT INTO sales (customerId, movieId, saleDate) VALUES (?,?,?)";
		java.util.Date date = new java.util.Date();
		PreparedStatement ps = connection.prepareStatement(insert_stmt);
		
		ps.setObject(1, info.getCustomer_id());
		ps.setObject(3, new SimpleDateFormat("yyyy-MM-dd").format(date));
		HashMap<String, cartitem> basket = cart.getBasket();
		for(String movie_id: basket.keySet())
		{
			ps.setObject(2, movie_id);
			ps.executeUpdate();
		}

	}
	public boolean process_sale(cart cart, customercheckout info) throws SQLException
	{
		boolean success = false;
		String stmt = "SELECT COUNT(id) FROM creditcards WHERE id = ? AND firstName = ? "
				+ "AND lastName = ? AND expiration = ?";
		PreparedStatement ps = connection.prepareStatement(stmt);
		ps.setObject(1, info.getCc());
		ps.setObject(2, info.getFirst_name());
		ps.setObject(3, info.getLast_name());
		ps.setObject(4, info.getExp_date());
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		if(rs.getInt(1) > 0) //Grab first column which is the count
		{
			success = true;
			insert_sale(cart, info);
		}
		return success;
		
	}
	Integer agid = 000000001;
	public Boolean insertStar(star newStar) throws SQLException
	{
		
		PreparedStatement update = connection.prepareStatement("INSERT INTO stars (id, name, birthYear) VALUES (?,?,?)");
		String sagid =  String.valueOf(agid);
		agid+=1;
		update.setObject(1, sagid);
		update.setObject(2, newStar.getName().trim());
		update.setObject(3, newStar.getBirthYear());
		
		int results = 0;

		try{
			results  = update.executeUpdate();
		}
		catch(Exception ex){ 
		
			return false;
		}
		update.close();
		return (results > 0 ? true : false);
	}
	public String addMovieViaStoredProceduce(movie newMovie, star starInMovie, String genre) throws SQLException
	{
		CallableStatement cs = connection.prepareCall("{call add_movie(?,?,?,?,?,?,?)}");
		cs.setString("title", newMovie.getTitle().trim());
		cs.setInt("year", newMovie.getYear());
		cs.setString("director",newMovie.getDirector().trim());
		cs.setInt("star_birthYear",starInMovie.getBirthYear());
		cs.setString("star_name",starInMovie.getName().trim());
		cs.setString("genre",genre.trim());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		String out = "";
		out = cs.getString(7);
		return out;
		
	}
	public user employeeLogin(String userName, String password) throws SQLException {
		user logged_in_user = null;
		if (userName == null || password == null)
			return null;
		String statementString = "SELECT * FROM employees WHERE email=\'" + userName + "\' AND password=\'" + password
				+ "\';";
		PreparedStatement statement = connection.prepareStatement(statementString);
		ResultSet results = statement.executeQuery();
		if (results.next()) {
			logged_in_user = new user(0, results.getString("fullname"),"", "Empolyee", results.getString("email"));

		}
		results.close();
		statement.close();
		return logged_in_user;
	}
	
/*----------------------------------------------------------------------------*/
	
	public ResultSet movie_batch_insert(ArrayList<movie> values) throws SQLException
	{
		connection.setAutoCommit(false);
		Integer egid =000000001;
		String stmt = "INSERT INTO movies (id,title,year,director) VALUES (?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
		for(movie mov : values)
		{
			String segid=String.valueOf(egid);
			egid+=1;
			ps.setString(1, segid);
			ps.setString(2, mov.getTitle());
			ps.setInt(3, mov.getYear());
			ps.setString(4, mov.getDirector());
			ps.addBatch();
			
		}
		ps.executeBatch();
		connection.commit();
		connection.setAutoCommit(true);
		return ps.getGeneratedKeys();
	}
	
	public ResultSet star_batch_insert(ArrayList<star> values) throws SQLException
	{
		connection.setAutoCommit(false);
		Integer egid =000000001;
		
		String stmt = "INSERT INTO stars (id,name,birthYear) VALUES (?,?,?)";
		PreparedStatement ps = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
		for(star star : values)
		{
			String segid=String.valueOf(egid);
			egid+=1;
			ps.setString(1, segid);
			ps.setString(2, star.getName());
			ps.setInt(3, star.getBirthYear());
			ps.addBatch();
			
		}
		ps.executeBatch();
		connection.commit();
		connection.setAutoCommit(true);
		return ps.getGeneratedKeys();
	}
	
	public void starid_movieid_batch(LinkedHashMap<String, String>starid_movieid) throws SQLException 
	{
		String query = "INSERT INTO stars_in_movies (starId, movieId) VALUES (?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		
		for(Map.Entry<String, String> c : starid_movieid.entrySet())
		{
			ps.setString(1, c.getKey());
			ps.setString(2, c.getValue());
			ps.addBatch();
		}
		ps.executeBatch();
	}
	
	public void gim_batch_insert(String final_genres_in_movies_query) throws SQLException
	{
		connection.setAutoCommit(false);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(final_genres_in_movies_query);
		connection.commit();
		connection.setAutoCommit(true);
	}
	
	public Integer getGenreIdFromName(String name) throws SQLException
	{
		Integer id = -1;
		String query = "Select id FROM genres WHERE name= ? LIMIT 1";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setObject(1, name);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			id = rs.getInt("id");
		}
		return id;
	}
	public Integer insert_genre(String genre) throws SQLException
	{
		String query = "INSERT INTO genres (name) VALUES (?)";
		PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setObject(1, genre);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
//-----------------------------------------------------------------------------------------------------------------------	
	public List<String> getTtiles(String search) throws SQLException
	{
		return getTtiles(search, 5);
	}

	public List<String> getTtiles(String search, int numReturn) throws SQLException
	{
		search = search.trim();
		if(search == null || "".equals(search))
			return new ArrayList<String>();
		
		ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(search.split(" ")));

		String query = "SELECT DISTINCT title FROM movies WHERE MATCH(title) AGAINST( ? IN BOOLEAN MODE ) LIMIT ?; ";

		
		int i = tokens.size();
		String cvTokens ="";
		while(i --> 0)
		{
			cvTokens += "+" + tokens.get(i).trim() + "*";
		}
		PreparedStatement ps = connection.prepareStatement( query );
		
		ps.setString(1, cvTokens);
		ps.setInt(2, numReturn);
		
		ResultSet rs = ps.executeQuery();
		List<String> output = new ArrayList<String>();
		while(rs.next())
		{
			output.add(rs.getString(1));
		}
		if(rs != null)
		{
			rs.close();
		}
		if(ps!= null)
		{
			ps.close();
		}
		
		return output;
		
	}
	
	public List<String> getStarNames(String search) throws SQLException
	{
		return getStarNames(search, 5);
	}

	public List<String> getStarNames(String search, int numReturn) throws SQLException
	{
		search = search.trim();
		if(search == null || "".equals(search))
			return new ArrayList<String>();
		
		ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(search.split(" ")));

		String query = "SELECT DISTINCT name FROM stars WHERE MATCH(name) AGAINST( ? IN BOOLEAN MODE ) LIMIT ?; ";

		
		int i = tokens.size();
		String cvTokens ="";
		while(i --> 0)
		{
			cvTokens += "+" + tokens.get(i).trim() + "*";
		}
		PreparedStatement ps = connection.prepareStatement( query );
		
		ps.setString(1, cvTokens);
		ps.setInt(2, numReturn);
		
		ResultSet rs = ps.executeQuery();
		List<String> output = new ArrayList<String>();
		while(rs.next())
		{
			output.add(rs.getString(1));
		}
		if(rs != null)
		{
			rs.close();
		}
		if(ps!= null)
		{
			ps.close();
		}
		
		return output;
		
	}
	
	public LinkedHashMap<String, movie> search_movies_without_PS(searchparameters curSearch) throws Exception {
		
		StringBuilder query = new StringBuilder("SELECT DISTINCT movies.id,title,year,director "
				+ "FROM stars "
				+ "INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId "
				+ "LEFT OUTER JOIN movies ON movies.id = stars_in_movies.movieId "
				+ "LEFT OUTER JOIN genres_in_movies ON genres_in_movies.movieId = movies.id WHERE ");

		if (!"".equals(curSearch.getTitle())) {
			query.append("title LIKE " +  "\'%" + curSearch.getTitle() +  "%\'");
		}

		// add ORDER BY, LIMIT, OFFSET
		int offset = Integer.parseInt(curSearch.getMoviePerPage()) * Integer.parseInt(curSearch.getCurrentPage());
		query.append(" ORDER BY " + curSearch.getSortType() + " " + (curSearch.getSortAccending() ? "ASC" : "DESC"));
		query.append(" LIMIT " + curSearch.getMoviePerPage() + " OFFSET " + offset);

		LinkedHashMap<String, movie> movie_list = new LinkedHashMap<String, movie>();

		Statement ps = connection.createStatement();
		
		ResultSet rs = ps.executeQuery(query.toString());
		populate_list(movie_list, rs);
		rs.close();
		ps.close();
		return movie_list;
	}
	
	
	public movie getmovieinfo(int start, int limit, String letterOfTitle) throws SQLException {
		  String statementString = "SELECT DISTINCT movies.id,title,year,director "
		    + "FROM stars "
		    + "INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId "
		    + "LEFT OUTER JOIN movies ON movies.id = stars_in_movies.movieId "
		    + "LEFT OUTER JOIN genres_in_movies ON genres_in_movies.movieId = movies.id";
		  if (!"".equals(letterOfTitle)) {
		   statementString += " WHERE  title = \'" + letterOfTitle + "\' ";
		  }
		  statementString += " ORDER BY title ";
		  if (limit > 0) {
		   statementString += " LIMIT " + limit + " OFFSET " + (start > 0 ? start : 0);
		  }

		  statementString += ";";
		  PreparedStatement statement = connection.prepareStatement(statementString);
		  ResultSet results = statement.executeQuery();
		  movie newMovie = null;
		  
		  while (results.next()) {
		   String movie_id = results.getString("id");
		   newMovie = new movie(movie_id, results.getString("title"), results.getInt("year"), results.getString("director"),
		      getGenreFromMovieId(movie_id),
		     getStarFromMovieId(movie_id));
		  }
		  results.close();
		  statement.close();
		  return newMovie;
		 }
	

		public star getStarFromName(String starname) throws SQLException
		{
			String star_id_stmt = "SELECT stars.id, stars.name, stars.birthYear FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId " +
					"INNER JOIN movies ON movies.id = stars_in_movies.movieId WHERE stars.name = ? LIMIT 1";
			String movies_info_stmt = "SELECT movies.id, movies.title FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.starId "+
				   "INNER JOIN movies ON movies.id = stars_in_movies.movieId WHERE stars.id = ?";
			
			PreparedStatement star_ps = connection.prepareStatement(star_id_stmt);
			star_ps.setObject(1, starname);
			
			
			ResultSet star_rs = star_ps.executeQuery(); 
			String star_id = null;
			star star = null;
			if(star_rs.next())
			{
				star_id = star_rs.getString("id");
			
				PreparedStatement movie_ps = connection.prepareStatement(movies_info_stmt);
				movie_ps.setObject(1, star_id);
				ResultSet movie_rs = movie_ps.executeQuery();
				HashMap<String, String> movies = new HashMap<String, String>();
				
				while(movie_rs.next())
				{
					movies.put(movie_rs.getString("id"), movie_rs.getString("title"));
				}
				star = new star(star_id, star_rs.getString("stars.name"), star_rs.getInt("stars.birthYear"), movies);
			}
			else
			{
				star = new star(star_id, star_rs.getString("stars.name"), star_rs.getInt("stars.birthYear"), new HashMap<String, String>());
			}
			
			return star;
		}
		 
		public List<String> getbyoffset(String search, int pageReturn) throws SQLException
		{
			search = search.trim();
			if(search == null || "".equals(search))
				return new ArrayList<String>();
			
			ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(search.split(" ")));

			String query = "SELECT DISTINCT title FROM movies WHERE MATCH(title) AGAINST( ? IN BOOLEAN MODE ) LIMIT 5 OFFSET ?; ";

			
			int i = tokens.size();
			String cvTokens ="";
			while(i --> 0)
			{
				cvTokens += "+" + tokens.get(i).trim() + "*";
			}
			PreparedStatement ps = connection.prepareStatement( query );
			
			ps.setString(1, cvTokens);
			ps.setInt(2, 5*(pageReturn-1));
			
			ResultSet rs = ps.executeQuery();
			List<String> output = new ArrayList<String>();
			while(rs.next())
			{
				output.add(rs.getString(1));
			}
			if(rs != null)
			{
				rs.close();
			}
			if(ps!= null)
			{
				ps.close();
			}
			
			return output;
			
		}
		
}

	

