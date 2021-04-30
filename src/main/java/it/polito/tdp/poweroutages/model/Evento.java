package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Evento {
	LocalDateTime data_inizio;
	LocalDateTime data_fine;
	Integer nerc_id;
	Integer event_id;
	Integer customers_affected;
	float oreDisservizio;
	public Evento(LocalDateTime data_Inizio, LocalDateTime data_fine, Integer nerc_id, Integer event_id,Integer customers_affected,float oreDisservizio) {
		super();
		this.data_inizio = data_Inizio;
		this.data_fine = data_fine;
		this.nerc_id = nerc_id;
		this.event_id = event_id;
		this.customers_affected=customers_affected;
		this.oreDisservizio=oreDisservizio;
	}

	
	@Override
	public String toString() {
		return "Evento [data_inizio=" + data_inizio + ", data_fine=" + data_fine + ", nerc_id=" + nerc_id
				+ ", event_id=" + event_id + ", customers_affected=" + customers_affected + ", oreDisservizio="
				+ oreDisservizio + "]";
	}


	public float getOreDisservizio() {
		return oreDisservizio;
	}


	public void setOreDisservizio(float oreDisservizio) {
		this.oreDisservizio = oreDisservizio;
	}


	public Integer getNerc_id() {
		return nerc_id;
	}
	public void setNerc_id(Integer nerc_id) {
		this.nerc_id = nerc_id;
	}
	public Integer getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	
	
	public LocalDateTime getData_inizio() {
		return data_inizio;
	}


	public void setData_inizio(LocalDateTime data_inizio) {
		this.data_inizio = data_inizio;
	}


	public LocalDateTime getData_fine() {
		return data_fine;
	}


	public void setData_fine(LocalDateTime data_fine) {
		this.data_fine = data_fine;
	}


	public Integer getCustomers_affected() {
		return customers_affected;
	}
	public void setCustomers_affected(Integer customers_affected) {
		this.customers_affected = customers_affected;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nerc_id == null) ? 0 : nerc_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (nerc_id == null) {
			if (other.nerc_id != null)
				return false;
		} else if (!nerc_id.equals(other.nerc_id))
			return false;
		return true;
	}
	
	
	

}
