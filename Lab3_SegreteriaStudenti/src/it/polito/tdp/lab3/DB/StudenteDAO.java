package it.polito.tdp.lab3.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {
	String jdbcURL="jdbc:mysql://localhost/iscritticorsi?user=root";

public Studente getStudente(int m) {
		
		try {
			Connection conn=DriverManager.getConnection(jdbcURL);
			String sql="select matricola, nome, cognome, CDS from studente where matricola=\""+m+"\"";
		//	PreparedStatement st= conn.prepareStatement(sql);
			Statement st= conn.createStatement();
		//	st.setString(1, String.valueOf(m));
			ResultSet res= st.executeQuery(sql);
			if(res.next()){
				String nome=res.getString("nome");
				String cognome= res.getString("cognome");
				String cds=res.getString("CDS");
				Studente s= new Studente(m, nome, cognome, cds);
				conn.close();
				res.close();
				return s;
			}
			else{
			conn.close();
			res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

public List<Studente> elenco(String corso) {
	List<Studente> studenti= new ArrayList<Studente>();
	try {
		Connection conn= DriverManager.getConnection(jdbcURL);
		Statement st=conn.createStatement();
		String sql="select s.matricola, s.nome, s.cognome, s.CDS from corso c, iscrizione i, studente s where c.codins=i.codins && i.matricola=s.matricola && c.nome=\""+corso+"\"";
		ResultSet res= st.executeQuery(sql);
		while(res.next()){
			studenti.add(new Studente(Integer.parseInt(res.getString("matricola")), res.getString("nome"), res.getString("cognome"), res.getString("CDS")));
		}
		res.close();
		conn.close();
		return studenti;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}

public List<Corso> corsiFrequentati(String t) {
	try {
		List<Corso> corsi= new ArrayList<Corso>();
		Connection conn=DriverManager.getConnection(jdbcURL);
		Statement st= conn.createStatement();
		String sql="select c.codins, c.crediti, c.nome, c.pd from corso c, studente s, iscrizione i where c.codins=i.codins && i.matricola=s.matricola && s.matricola=\""+t+"\"";
		ResultSet res= st.executeQuery(sql);
		while(res.next()){
			corsi.add(new Corso(res.getString("codins"), Integer.parseInt(res.getString("crediti")), res.getString("nome"), Integer.parseInt(res.getString("pd"))));
		}
		res.close();
		conn.close();
		return corsi;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}
}
