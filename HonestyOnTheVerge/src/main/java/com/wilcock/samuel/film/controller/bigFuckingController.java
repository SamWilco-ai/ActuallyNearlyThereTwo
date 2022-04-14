package com.wilcock.samuel.film.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.wilcock.samuel.film.dao.FilmDAO;
import com.wilcock.samuel.film.model.Film;
import com.wilcock.samuel.film.model.FilmList;
import com.wilcock.samuel.film.utils.FilmUtils;

/**
 * Servlet implementation class bigFuckingController
 */
@WebServlet("/rest")
public class bigFuckingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static FilmDAO dao = new FilmDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public bigFuckingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		String filmName = request.getParameter("filmName"); //check to see if filmname has been passed through to query db
		ArrayList<Film> filmsLike = new ArrayList<>();
		
		if (filmName != null) {
			filmsLike = dao.getFilmByTitle(filmName); //if filmname present send off query to return possible filmname
		} else {
			filmsLike = dao.getAllFilms(); //else if no filmname present return all films
		}
		
		FilmList fl = new FilmList(); //FilmList class used for formatting the responses
		fl.setFilmList(filmsLike);
		FilmUtils fu = new FilmUtils(); //Utils class for formatting the FilmListClass in the chosen format

		String format = request.getParameter("format"); // check if parameter for format has been entered

		if (format == null) {
			format = "json"; //if no format entered default to JSON
		}
		
		String outputPage = null;
		if (format.equals("xml")) {
			System.out.println("XML Requested"); //Visual console output of XML requested
			response.setContentType("text/xml"); //set content type to xml
			outputPage = "WEB-INF/results/films.jsp";
			request.setAttribute("films", fu.formatXML(fl)); //Create attribute of formatted data for jsp
			response.getWriter().write(fu.formatXML(fl)); //Output response 
		} else if (format.equals("text")) {
			System.out.println("TEXT Request");
			response.setContentType("text/plain");
			outputPage = "WEB-INF/results/films2.jsp";
			response.getWriter().write(fu.formatText(fl));
			request.setAttribute("films", fu.formatText(fl));

		} else if (format.equals("json")) {
			System.out.println("JSON REQUESTED");
			response.setContentType("application/json");
			outputPage = "WEB-INF/results/films3.jsp";
			response.getWriter().write(fu.formatJSON(fl));			
			request.setAttribute("films", fu.formatJSON(fl));

		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		int year = Integer.parseInt(request.getParameter("year"));
		String title = request.getParameter("tit");
		String direc = request.getParameter("dir");
		String rev = request.getParameter("rev");
		String st = request.getParameter("st");

		Film f = new Film(id, title, year, direc, st, rev);

		dao.insertFilm(f);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		FilmDAO dao = new FilmDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			dao.deleteFilmByID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
