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

	public Studente studenteInCorso(int matricola, String b) {
		CorsoDAO s=new CorsoDAO();
		return s.studenteInCorso(matricola, b);
	}

	public boolean inserisci(int m, String value) {
		IscrizioneDAO is= new IscrizioneDAO();
		StudenteDAO d= new StudenteDAO();
		Studente studente=d.getStudente(m);
		if(studente==null) 
			return false;
		for (Studente s : this.getIscritti(value)) {
			if(m==s.getMatricola())return false;
		}
		CorsoDAO co= new CorsoDAO();
		Corso c= co.getCorso(value);
		if (c==null) 
			return false;
		return is.addIscrizione(studente, c);
	}
}
