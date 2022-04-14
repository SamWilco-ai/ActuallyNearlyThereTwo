package com.wilcock.samuel.film.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wilcock.samuel.film.dao.FilmDAO;
import com.wilcock.samuel.film.model.Film;

/**
 * Servlet implementation class updateFilm
 */
@WebServlet("/updateFilm")
public class updateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FilmDAO dao = new FilmDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		int year = Integer.parseInt(request.getParameter("year"));
		String title = request.getParameter("tit");
		String direc = request.getParameter("dir");
		String rev = request.getParameter("rev");
		String st = request.getParameter("st");
		
		Film f = new Film(id, title, year, direc, rev, st);
		
		try {
			dao.updateFilm(f, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
