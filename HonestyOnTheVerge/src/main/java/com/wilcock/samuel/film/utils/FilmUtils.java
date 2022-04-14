package com.wilcock.samuel.film.utils;

import java.io.StringWriter;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.wilcock.samuel.film.model.Film;
import com.wilcock.samuel.film.model.FilmList;

public class FilmUtils {
	
	public String formatXML(FilmList fl) {
		try {
			JAXBContext context = JAXBContext.newInstance(FilmList.class);
			Marshaller marshall = context.createMarshaller();
			marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			marshall.marshal(fl, sw);
			String xmlContent = sw.toString();
			return xmlContent;
		} catch (JAXBException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	public String formatJSON(FilmList fl) {
		Gson gson = new Gson();
		return gson.toJson(fl);
	}
	
	public String formatText(FilmList fl) {
		String text = "";
		String uniSep = "|@|";
		for (Entry<String, Object> entry : new Film().variablesForTextFormat().entrySet()) {
			text += entry.getKey() + uniSep;
		}
		
		text += "\n";
		for (Film film : fl.getFilmsList()) {
			for (Entry<String, Object> entry : film.variablesForTextFormat().entrySet()) {
				text += entry.getValue() + uniSep;
			}
			text += "\n";
		}
		
		return text;		
	}

}
