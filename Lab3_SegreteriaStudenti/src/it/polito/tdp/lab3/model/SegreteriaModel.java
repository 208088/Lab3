package it.polito.tdp.lab3.model;

import java.util.*;

import it.polito.tdp.lab3.DB.*;

public class SegreteriaModel {
	
	
	public Studente getStudente(int m){
		StudenteDAO s= new StudenteDAO();
		return s.getStudente(m);
	}

	public List<Corso> getCorsi(){
		CorsoDAO c= new CorsoDAO();
		return c.getCorsi();
	}
	
	public List<Studente> getIscritti(String corso){
		List<Studente> studenti= new ArrayList<Studente>();
		StudenteDAO s= new StudenteDAO();
	    studenti.addAll(s.elenco(corso));
		return studenti;
	}

	public List<Corso> corsiFrequentati(String t) {
		StudenteDAO s= new StudenteDAO();
		List<Corso> corsi=s.corsiFrequentati(t);
		return corsi;
	}
}
