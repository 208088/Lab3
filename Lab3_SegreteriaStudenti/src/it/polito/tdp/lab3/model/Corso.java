package it.polito.tdp.lab3.model;

public class Corso {
	private String id;
	private int crediti;
	private String nome;
	private int pd;
	public Corso(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Corso(String id, int crediti, String nome, int pd) {
		super();
		this.id = id;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}

	public int getCrediti() {
		return crediti;
	}

	public int getPd() {
		return pd;
	}

	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

}
