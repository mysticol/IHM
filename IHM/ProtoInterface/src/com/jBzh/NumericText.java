package com.jBzh;

import java.util.ArrayList;

public class NumericText {

	public String nomNumeric;
	public String valeur;
	
	public NumericText(String nomNumeric, String valeur) {
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
	
	public String getValeur() {
		return valeur;
	}
	
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	public static ArrayList<NumericText> getAListOfNumericText() {
		ArrayList<NumericText> listN = new ArrayList<NumericText>();
		
		  listN.add(new NumericText("Nom","Joël"));
		  listN.add(new NumericText("Taille","ToutPetit"));
		
		return listN;
	}
}
