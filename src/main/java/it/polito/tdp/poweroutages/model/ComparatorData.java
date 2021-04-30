package it.polito.tdp.poweroutages.model;

import java.util.Comparator;

public class ComparatorData implements Comparator<Evento> {

	@Override
	public int compare(Evento o1, Evento o2) {
		// TODO Auto-generated method stub
		return o1.getData_inizio().compareTo(o2.getData_inizio());
	}

}
