package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Evento;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Evento> getListaEventi(Integer nerc_id)
	{
		
		String sql = "SELECT date_event_finished AS data_fine, date_event_began AS data_inizio, customers_affected, id AS id_evento, nerc_id "
				+ "FROM poweroutages "
				+ "WHERE nerc_id=? " ;
		List<Evento> listEventi = new ArrayList<>();

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,nerc_id);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				float oreDisservizio=0;
				Evento e = new Evento(res.getTimestamp("data_inizio").toLocalDateTime(),res.getTimestamp("data_fine").toLocalDateTime(),res.getInt("id_evento"),res.getInt("nerc_id"),res.getInt("customers_affected"),oreDisservizio);
				oreDisservizio=Duration.between(e.getData_fine(),e.getData_inizio()).toHours();
				e.setOreDisservizio(oreDisservizio);
				listEventi.add(e);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listEventi;
	}
	

}
