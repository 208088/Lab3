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

}
