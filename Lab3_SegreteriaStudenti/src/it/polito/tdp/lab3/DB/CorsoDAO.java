package it.polito.tdp.lab3.DB;

import java.sql.*;
import java.util.*;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class CorsoDAO {
	
	String jdbcURL="jdbc:mysql://localhost/iscritticorsi?user=root";
	List<Corso> corsi= new ArrayList<Corso>();
	
	
	public List<Corso> getCorsi(){
		
	try {
		Connection conn=DriverManager.getConnection(jdbcURL);
		Statement st=conn.createStatement();
		String sql="select * from corso;";
		ResultSet res= st.executeQuery(sql);
		
		while(res.next()){
			corsi.add(new Corso(res.getString("codins"), res.getString("nome")));
		}
		res.close();
		conn.close();
		
		return corsi;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return null;
	}
	}
	
	public String getId(String corso){
		for (Corso c : getCorsi()) {
			if(c.getNome().compareTo(corso)==0) return c.getId();
		}
		return null;
	}

	public Studente studenteInCorso(int matricola, String b) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select s.matricola, s.nome, s.cognome, s.CDS from corso c, studente s, iscrizione i where c.codins=i.codins && i.matricola=s.matricola && s.matricola="+matricola+" && c.nome=\""+b+"\"";
			ResultSet res= st.executeQuery(sql);
			Studente s=null;
			if(res.next()){
				 s= new Studente(Integer.parseInt(res.getString("matricola")), res.getString("nome"), res.getString("cognome"), res.getString("CDS"));
			}
			res.close();
			conn.close();
			return s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Corso getCorso(String value) {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select * from corso where nome=\""+value+"\"";
			ResultSet res= st.executeQuery(sql);
			Corso c=null;
			if(res.next()){
				 c= new Corso(res.getString("codins"),Integer.parseInt(res.getString("crediti")) ,res.getString("nome"), Integer.parseInt(res.getString("pd")));
			}
			res.close();
			conn.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
