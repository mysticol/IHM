package com.jBzh;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProtoInterface extends Activity {
	
	private static final int CODE_DE_MON_ACTIVITE = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // On recupere la racine des fichiers internes de l'application
        //File root = getFilesDir();
        File root = getFilesDir();
                       
        // On cherche les directories racines de l'application.
        // S'ils y sont, on ne cree pas l'arborescence, sinon oui
        int nbDir = 0;
               
        for(File f : root.listFiles()){
        	//System.out.println(f.getName());
        	if(f.getName().equalsIgnoreCase("Fiches")){
        		nbDir++;
        	} else if(f.getName().equalsIgnoreCase("Systeme")) {
        		nbDir++;
        	} else {
        		f.delete();
        	}
        }
        
        if(nbDir!=2){
        	// Il n'y a pas la bonne arborescence, on recree l'arborescence
        	System.out.println("On créé l'arborescence nécessaire au fonctionnement de l'application");
        	File fiches = getFileStreamPath("Fiches");
        	fiches.mkdir();
        	File systeme = getFileStreamPath("Systeme");
        	systeme.mkdir();        	
        }

    	// Pour les besoins du test, on créé des dossiers prédéfinis
        // Dossiers de fiches
    	File dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/L5R");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/Default");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/2ndCampagne");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/2ndCampagne/PJ");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/2ndCampagne/PNJ");
    	dirTmp.mkdir();
    	// Dossiers systeme
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Regles");
    	dirTmp.mkdir();
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles/Vampire");
    	dirTmp.mkdir();    	
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Modeles/L5R");
    	dirTmp.mkdir();    	
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Regles/Vampire");
    	dirTmp.mkdir();    	
    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Systeme/Regles/L5R");
    	dirTmp.mkdir();      	
    	
    	/* Suppression des dossiers incorrect */
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/2ndCampagne/Pjs");
//    	dirTmp.delete();
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/2ndCampagne/Pnjs");
//    	dirTmp.delete();    	
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/null/PJ");
//    	dirTmp.delete();    	
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/null/PNJ");
//    	dirTmp.delete();
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/null");
//    	dirTmp.delete();
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/PJ/.xml");
//    	dirTmp.delete();    	
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/PJ");
//    	System.out.println(dirTmp.listFiles().length);
//    	for(File f : dirTmp.listFiles()){
//    		System.out.println(f.getName());
//    	}   	
//    	dirTmp.delete();    	
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/PNJ");
//    	dirTmp.delete();
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/PJ");
//    	dirTmp.delete();
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/PNJ/pnj1.xml");
//    	dirTmp.delete();
//    	dirTmp = new File(getFilesDir().getAbsolutePath() + "/Fiches/Vampire/PNJ");
//    	dirTmp.delete();    	
    	
        
        final Button buttonMJ = (Button) findViewById(R.id.MJ);
        buttonMJ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixMJ.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);
            }
        });
        
        final Button buttonJoueur = (Button) findViewById(R.id.Joueur);
        buttonJoueur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixJoueur.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);

            }
        });
        
        final Button buttonCreation = (Button) findViewById(R.id.Creation);
        buttonCreation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixCreation.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);

            }
        });        

        final Button buttonEdition = (Button) findViewById(R.id.Edition);
        buttonEdition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
     
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixEdition.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);

            }
        });   
        
        final Button buttonTest = (Button) findViewById(R.id.Test);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//On cr�� un objet Bundle, c'est ce qui va nous permetre d'envoyer des donn�es � l'autre Activity
    			Bundle objetbunble = new Bundle();
    			
    			objetbunble.putSerializable("fiche", test.createFiche());
				objetbunble.putSerializable("systeme", test.createSysteme());
    		
    			//On cr�� l'Intent qui va nous permettre d'afficher l'autre Activity
    			Intent intent = new Intent(ProtoInterface.this, ChoixJet.class);
     
    			//On affecte � l'Intent le Bundle que l'on a cr��
    			intent.putExtras(objetbunble);
     
    			//On d�marre l'autre Activity
    			startActivityForResult(intent, CODE_DE_MON_ACTIVITE);

            }
        });
        //Si on fait un bouton quitter : android.os.Process.killProcess(android.os.Process.myPid());
    }
}