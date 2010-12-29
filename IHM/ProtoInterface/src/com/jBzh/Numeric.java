package com.jBzh;

import java.util.ArrayList;

public class Numeric {
	
	public String nomNumeric;
	public int valeur;
	
	public Numeric(String nomNumeric, int valeur) {
		super();
		this.nomNumeric = nomNumeric;
		this.valeur = valeur;
	}
	
	public String getNomNumeric() {
		return nomNumeric;
	}
	
	public void setNomNumeric(String nomNumeric) {
		this.nomNumeric = nomNumeric;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public static ArrayList<Numeric> getAListOfNumeric() {
		ArrayList<Numeric> listN = new ArrayList<Numeric>();
		
		  listN.add(new Numeric("Force",0));
		  listN.add(new Numeric("Agilite",0));
		
		return listN;
	}

	public void incr(){
		this.valeur++;
	}
	
	public void decr(){
		this.valeur--;
	}	
	
	

}
