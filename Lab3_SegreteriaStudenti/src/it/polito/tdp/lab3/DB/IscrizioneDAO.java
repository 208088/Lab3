package it.polito.tdp.lab3.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

import java.sql.Connection;

public class IscrizioneDAO {
	String jdbcURL="jdbc:mysql://localhost/iscritticorsi?user=root";
	
	public List<Integer> getMatricole(String id){
		List<Integer> matricole= new ArrayList<Integer>();
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st= conn.createStatement();
			String sql="select matricola from iscrizione where codins=\""+id+"\"";
			ResultSet res= st.executeQuery(sql);
			while(res.next()){
				matricole.add(Integer.parseInt(res.getString("matricola")));
			}
			res.close();
			conn.close();
			return matricole;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean addIscrizione(Studente m, Corso value) {
		try {
			Connection conn= DriverManager.getConnection(jdbcURL);
			Statement st= conn.createStatement();
			String sql="insert into iscrizione values (\""+m.getMatricola()+"\", \""+value.getId()+"\")";
			int ret=st.executeUpdate(sql);
			conn.close();
			if (ret==1) 
				return true;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
