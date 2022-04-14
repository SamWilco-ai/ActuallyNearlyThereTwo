package com.wilcock.samuel.film.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class getAllFilms
 */
@WebServlet("/getAllFilms")
public class getAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllFilms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		String format = request.getParameter("format");
		request.setAttribute("films", allFilms);

		if (format == null) {
			format = "json";
		}
		String outputPage = null;
		if (format.equals("xml")) {
			System.out.println("XML Requested");
			response.setContentType("text/xml");
			outputPage = "WEB-INF/results/films-xml.jsp";
			for (int i = 0; i < allFilms.size(); i++) {
				try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);

				// Create Marshaller
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// Required formatting??
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				// Print XML String to Console
				StringWriter sw = new StringWriter();

				// Write XML to StringWriter
				jaxbMarshaller.marshal(allFilms.get(i), sw);

				// Verify XML Content
				String xmlContent = sw.toString();
				System.out.println(xmlContent);
				}
				catch (JAXBException e) {
					
				}
			}
		} else if (format.equals("text")) {
			System.out.println("TEXT Request");
			response.setContentType("text/plain");
			outputPage = "WEB-INF/results/films-text.jsp";
			for(int i = 0; i < allFilms.size(); i++) {
				System.out.println(allFilms.get(i).toString());
			}
		} else if (format.equals("json")) {
			System.out.println("JSON REQUESTED");
			response.setContentType("text/json");
			outputPage = "WEB-INF/results/films.jsp";
			Gson gson = new Gson();
			for (int i = 0; i < allFilms.size(); i++) {
				System.out.println(gson.toJson(allFilms.get(i)));
			}
		}

//		RequestDispatcher disp = request.getRequestDispatcher(outputPage);
//		disp.include(request, response);
		
		
	}

}
