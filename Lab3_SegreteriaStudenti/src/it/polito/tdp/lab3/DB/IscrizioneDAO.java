package it.polito.tdp.lab3.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

}
