package it.polito.tdp.lab3.model;

public class Studente {
	private int matricola;
	private String nome;
	private String cognome;
	private String cds;
	
	public Studente(int matricola, String nome, String cognome, String cds) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds=cds;
	}
	public int getMatricola() {
		return matricola;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}

	public String getCDS(){
		return cds;
	}
}
