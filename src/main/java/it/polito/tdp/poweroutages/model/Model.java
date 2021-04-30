package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Evento> eventiNercList;
	List<Evento> listaOK;
	List<Evento> best;
	int clienteMiglioreMax;
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Evento> trovaEventi(Integer nerc_id,int maxAnni,int maxOre){
		List<Evento> parziale=new ArrayList<>();
		this.eventiNercList= podao.getListaEventi(nerc_id);
		Collections.sort(eventiNercList,new ComparatorData());
		best= new ArrayList<>();
		this.clienteMiglioreMax=0;
		permuta(parziale,0,maxAnni,maxOre);
		return best;
	}
	
	private void permuta(List<Evento> parziale, int i, int maxAnni, int maxOre) {
		float numeroOreE=calcolaOre(parziale);
		
		if(numeroOreE>maxOre)
		{
			return ;
		}
		else
		{
			int numClienti=calcolaClienti(parziale);
			if(numClienti>clienteMiglioreMax)
			{
				clienteMiglioreMax=numClienti;
				this.best=new ArrayList<>(parziale);
				return;
			}
			
		}
		if(i==eventiNercList.size())
		{
			return;
		}
		
		
		
		for(Evento e:eventiNercList)
		{
			if(!parziale.contains(e))
			{
				parziale.add(e);
				if(annoValido(e,parziale,maxAnni))
				{
					permuta(parziale,i+1,maxAnni,maxOre);
					
				}
				parziale.remove(e);
			}
		}
	}

	private boolean annoValido(Evento e, List<Evento> parziale, int maxAnni) {
		if(parziale.size()>=2)
		{
			int a1=parziale.get(0).getData_inizio().getYear();
			int a2=parziale.get(parziale.size()-1).getData_inizio().getYear();
			if((a2-a1+1)>maxAnni)
				return false;
		}
		return true;
	}

	private int calcolaClienti(List<Evento> parziale) {
		int numeroC=0;
		for(Evento e:parziale)
		{
			numeroC+=e.getCustomers_affected();
		}
		return numeroC;
	}

	private float calcolaOre(List<Evento> parziale) {
		float numeroOre=0;
		for(Evento e:parziale)
		{
			numeroOre+=e.getOreDisservizio();
		}
		return numeroOre;
	}

	
	}


	

