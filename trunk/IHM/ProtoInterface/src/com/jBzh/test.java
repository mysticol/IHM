package com.jBzh;

import java.util.LinkedList;

import bean.Fiche;
import bean.Result;
import bean.Roll;
import bean.Systeme;
import dice.DiceType;

public class test {

	static public Systeme createSysteme(){
		Systeme r = new Systeme();

		r.setName("wod1");
		
		LinkedList<String> le1 = new LinkedList<String>();
		le1.add("caracPrim");
		le1.add("comp");
		r.addRoll("classique", new Roll(DiceType.D10,le1));
		
		LinkedList<String> le2 = new LinkedList<String>();
		le2.add("caracSec");
		r.addRoll("secondaire", new Roll(DiceType.D10,le2));
		
		r.addResult("default", new Result());
		return r;
	}
	
	static public Fiche createFiche(){
		
		
		return null;
	}
	
	
}
