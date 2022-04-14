package com.wilcock.samuel.film.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "FilmList")
public class FilmList {
    @XmlElementWrapper(name = "films")
    @XmlElement(name = "film")
    
    private ArrayList<Film> filmList;
    
	public void setFilmList(ArrayList<Film> filmList) {
        this.filmList = filmList;
    }

    public ArrayList<Film> getFilmsList() {
        return filmList;
    }
    

}