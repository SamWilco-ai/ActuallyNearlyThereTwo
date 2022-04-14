package com.wilcock.samuel.film.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import com.wilcock.samuel.film.model.Film;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
    PreparedStatement prepstmt = null;
	String user = "wilcocks";
    String password = "yifflebO5";
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;
    private static final String SELECT_FILM_BY_ID = "select * from films where id=?";
    private static final String DELETE_FILM_BY_ID = "delete FROM wilcocks.films where id=?";
    private static final String UPDATE_FILM_BY_ID = "update films set id=?, title=?, year=?, director=?, stars=?, review=? where id=?;";
    private static final String INSERT_FILM_BY_ID = "INSERT INTO films" + " (id, title, year, director, stars, review) VALUES " + "(?,?,?,?,?,?)";
	private static final String SELECT_FILM_BY_TITLE = "SELECT * from films where title like ?";



	public FilmDAO() {}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { 
			System.out.println(se); 
		}    
    }
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertFilm(Film film) {
		System.out.println(INSERT_FILM_BY_ID);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILM_BY_ID)) {
			preparedStatement.setInt(1, film.getId());
			preparedStatement.setString(2, film.getTitle());
			preparedStatement.setInt(3, film.getYear());
			preparedStatement.setString(4, film.getDirector());
			preparedStatement.setString(5, film.getStars());
			preparedStatement.setString(6, film.getReview());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateFilm(Film film, int idForChanging) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FILM_BY_ID);) {
			statement.setInt(1, film.getId());
			statement.setString(2, film.getTitle());
			statement.setInt(3, film.getYear());
			statement.setString(4, film.getDirector());
			statement.setString(5, film.getStars());
			statement.setString(6, film.getReview());
			statement.setInt(7, idForChanging);
			
			System.out.println(statement);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
  public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "SELECT * FROM films;";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
}
  
  
	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
  
  public ArrayList<Film> getFilmByTitle(String title){
	  
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    prepstmt = conn.prepareStatement(SELECT_FILM_BY_TITLE);
		    prepstmt.setString(1, "%"+title+"%");
		    ResultSet rs1 = prepstmt.executeQuery();
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
}
  

	public boolean deleteFilmByID(int id) throws SQLException {
		System.out.println("Delete Function Called");
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FILM_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
  
   
}
