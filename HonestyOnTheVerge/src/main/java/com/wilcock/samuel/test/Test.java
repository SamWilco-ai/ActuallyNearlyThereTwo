package com.wilcock.samuel.test;

import java.sql.SQLException;

import com.wilcock.samuel.film.dao.FilmDAO;
import com.wilcock.samuel.film.model.Film;

public class Test {

	public static void main(String[] args) throws SQLException {
		FilmDAO dao = new FilmDAO();
		System.out.println("Testing");
		System.out.println(dao.getFilmByTitle("Star"));
		
		Film film = new Film(1,"Test", 2022, "Sam", "Sam", "SAM");
		dao.insertFilm(film);
		System.out.println(dao.getFilmByTitle("Test"));
		film.setId(2);
		dao.updateFilm(film, 1);
		System.out.println(dao.getFilmByTitle("Test"));
		dao.deleteFilmByID(2);
		System.out.println(dao.getFilmByTitle("Test"));
	}

}
