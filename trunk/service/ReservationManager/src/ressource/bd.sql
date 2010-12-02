/*
	database : jdbc/turnkeyDB
	pool 	 : turnkeyPool
	attribut : ;user=root;password=root;create=true
	user, mdp: root	
*/

DROP TABLE RESERVATION;
DROP TABLE RESERVATION_HOTEL;
DROP TABLE RESERVATION_MANIF;
DROP TABLE RESERVATION_RESTAU;
DROP TABLE HOTEL;
DROP TABLE RESTAURANT;
DROP TABLE MANIFESTATION;
DROP TABLE TYPE_MANIFESTATION;
DROP TABLE VOYAGE;
DROP TABLE LOCALISATION;
DROP TABLE CLIENT;

create table CLIENT
(
	CLIENT_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	NOM_CLIENT VARCHAR(12),
	PRENOM_CLIENT VARCHAR(12)
);

create table LOCALISATION
(
	LOCALISATION_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY ,
	PAYS VARCHAR(15),
	VILLE VARCHAR(15)
);

create table HOTEL
(
	HOTEL_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY ,
	FK_LOCALISATION_ID INTEGER,
	NOM_HOTEL VARCHAR(20),
	ADRESSE VARCHAR(30),
	NB_CHAMBRE INTEGER,
	RANK_HOTEL INTEGER,
	PRIX DOUBLE
);

create table TYPE_MANIFESTATION
(
	TYPE_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	NOM_TYPE VARCHAR(15)
);

create table MANIFESTATION
(
	MANIFESTATION_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_LOCALISATION_ID INTEGER,
	FK_ID_TYPE INTEGER,
	NB_PLACE INTEGER,
	NOM_MANIFESTATION VARCHAR(20),
	DATE DATE,
	ADRESSE VARCHAR(50),
	DESCRIPTION VARCHAR(80),
	PRIX DOUBLE
);

create table RESTAURANT
(
	RESTAURANT_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_LOCALISATION_ID INTEGER,
	ADRESSE VARCHAR(30),
	NOM_RESTAURANT VARCHAR(20),
	NB_COUVERTS INTEGER,
	RATE_RESTAURANT INTEGER,
	PRIX DOUBLE
);

create table RESERVATION_HOTEL
(
	RESERVATION_HOTEL_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_HOTEL_ID INTEGER,
	FK_ID_CLIENT INTEGER,
	DATE DATE
);

create table RESERVATION_MANIF
(
	RESERVATION_MANIF_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	DATE DATE,
	FK_ID_CLIENT INTEGER,
	FK_ID_MANIF INTEGER
);
create table RESERVATION_RESTAU
(
	RESERVATION_RESTAU_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_RESTAU_ID INTEGER,
	FK_ID_CLIENT INTEGER,
	DATE DATE
);

create table RESERVATION
(
	RESERVATION_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_RESERVE_HOTEL INTEGER,
	FK_RESERVE_RESTAU INTEGER,
	FK_RESERVE_MANIF INTEGER,
	FK_VOYAGE INTEGER,
	FK_CLIENT_ID INTEGER,
	DATE DATE
);

create table VOYAGE
(
	VOYAGE_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_LOCALISATION_DEPART INTEGER,
	FK_LOCALISATION_ARRIVE INTEGER,
	FK_CLIENT_ID INTEGER,
	DATE DATE
);

ALTER TABLE VOYAGE
ADD FOREIGN KEY (FK_CLIENT_ID) REFERENCES CLIENT(CLIENT_ID);

ALTER TABLE VOYAGE
ADD FOREIGN KEY (FK_LOCALISATION_DEPART) REFERENCES LOCALISATION(LOCALISATION_ID);

ALTER TABLE VOYAGE
ADD FOREIGN KEY (FK_LOCALISATION_DEPART) REFERENCES LOCALISATION(LOCALISATION_ID);

ALTER TABLE RESERVATION
ADD FOREIGN KEY (FK_VOYAGE) REFERENCES VOYAGE(VOYAGE_ID);

ALTER TABLE RESERVATION
ADD FOREIGN KEY (FK_RESERVE_HOTEL) REFERENCES RESERVATION_HOTEL(RESERVATION_HOTEL_ID);

ALTER TABLE RESERVATION
ADD FOREIGN KEY (FK_RESERVE_RESTAU) REFERENCES RESERVATION_RESTAU(RESERVATION_RESTAU_ID);

ALTER TABLE RESERVATION
ADD FOREIGN KEY (FK_RESERVE_MANIF) REFERENCES RESERVATION_MANIF(RESERVATION_MANIF_ID);

ALTER TABLE RESERVATION
ADD FOREIGN KEY (FK_CLIENT_ID) REFERENCES CLIENT(CLIENT_ID);

ALTER TABLE MANIFESTATION
ADD FOREIGN KEY (FK_ID_TYPE) REFERENCES TYPE_MANIFESTATION(TYPE_ID);

ALTER TABLE HOTEL
ADD FOREIGN KEY (FK_LOCALISATION_ID) REFERENCES LOCALISATION(LOCALISATION_ID);

ALTER TABLE MANIFESTATION
ADD FOREIGN KEY (FK_LOCALISATION_ID) REFERENCES LOCALISATION(LOCALISATION_ID);

ALTER TABLE MANIFESTATION
ADD FOREIGN KEY (FK_ID_TYPE) REFERENCES TYPE_MANIFESTATION(TYPE_ID);

ALTER TABLE RESTAURANT
ADD FOREIGN KEY (FK_LOCALISATION_ID) REFERENCES LOCALISATION(LOCALISATION_ID);

ALTER TABLE RESERVATION_HOTEL
ADD FOREIGN KEY (FK_HOTEL_ID) REFERENCES HOTEL(HOTEL_ID);

ALTER TABLE RESERVATION_HOTEL
ADD FOREIGN KEY (FK_ID_CLIENT) REFERENCES CLIENT(CLIENT_ID);

ALTER TABLE RESERVATION_MANIF
ADD FOREIGN KEY (FK_ID_MANIF) REFERENCES MANIFESTATION(MANIFESTATION_ID);

ALTER TABLE RESERVATION_MANIF
ADD FOREIGN KEY (FK_ID_CLIENT) REFERENCES CLIENT(CLIENT_ID);

ALTER TABLE RESERVATION_RESTAU
ADD FOREIGN KEY (FK_RESTAU_ID) REFERENCES RESTAURANT(RESTAURANT_ID);

ALTER TABLE RESERVATION_RESTAU
ADD FOREIGN KEY (FK_ID_CLIENT) REFERENCES CLIENT(CLIENT_ID);


/* quelque insert */

INSERT INTO LOCALISATION(PAYS,VILLE) VALUES('france','Nantes'); 	/* id : 1 */
INSERT INTO LOCALISATION(PAYS,VILLE) VALUES('france','Paris');		/* id : 2 */
INSERT INTO LOCALISATION(PAYS,VILLE) VALUES('angleterre','Cardiz');	/* id : 3 */
INSERT INTO LOCALISATION(PAYS,VILLE) VALUES('allemange','Berlin');	/* id : 4 */
INSERT INTO LOCALISATION(PAYS,VILLE) VALUES('espagne','Barcelone');	/* id : 5 */
INSERT INTO LOCALISATION(PAYS,VILLE) VALUES('mongolie','Oulan-Bator');	/* id : 6 */

INSERT INTO TYPE_MANIFESTATION(NOM_TYPE) VALUES('concert');	/* id : 1 */
INSERT INTO TYPE_MANIFESTATION(NOM_TYPE) VALUES('exposition');	/* id : 2 */
INSERT INTO TYPE_MANIFESTATION(NOM_TYPE) VALUES('spectacle');	/* id : 3 */
INSERT INTO TYPE_MANIFESTATION(NOM_TYPE) VALUES('genocide');	/* id : 4 */

INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(1,'le duchesse anne','pas loin du chateau',50,3,100);	/* id : 1 */
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(2,'le ritz','place vandome',75,4,200);		/* id : 2 */
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(3,'le rieur sanglier','au milieux',15,0,20);		/* id : 3 */	
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(3,'le poney fringuant','un peu a droite',10,0,25);	/* id : 4 */
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(4,'au chleu heureux','loin des juifs',33,1,75);	/* id : 5 */
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(5,'lencorner','a coter de sa queue',2,2,65);		/* id : 6 */
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(6,'yuitro-kiea','ya qune rue',1,0,10);		/* id : 7 */
INSERT INTO HOTEL(FK_LOCALISATION_ID,NOM_HOTEL,ADRESSE,NB_CHAMBRE,RANK_HOTEL,PRIX) VALUES(6,'gutroli','ya qune rue',3,0,15);			/* id : 8 */

INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(1,'la bas','creperie bleu',50,2,10);		/* id : 1 */
INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(2,'la haut','au bon manger',50,2,20);		/* id : 2 */
INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(3,'en bas','au mauvais manger',50,2,30);	/* id : 3 */	
INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(4,'a droite','la douche vapeur',50,2,40);	/* id : 4 */
INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(4,'a gauche','au grand four',50,2,50);	/* id : 5 */
INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(5,'par la','saucisse brochette',50,2,60);	/* id : 6 */
INSERT INTO RESTAURANT(FK_LOCALISATION_ID,ADRESSE,NOM_RESTAURANT,NB_COUVERTS,RATE_RESTAURANT,PRIX) VALUES(6,'ya qune rue','froilytui',50,2,70);		/* id : 7 */

INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(1,1,2000,'johnny','2010-12-12','parc des prince','description',1000);		/* id : 1 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(1,2,30,'carré et rond','2010-12-12','tatatatatatan','descriptiony',10);	/* id : 2 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(2,3,400,'drole','2010-12-12','tititititititi','description',200);		/* id : 3 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(2,3,350,'pas drole','2010-12-12','youpela','description',100);			/* id : 4 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(3,2,50,'ho cest joli','2010-12-12','stoneedge','description',300);		/* id : 5 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(3,1,150,'sous marin jaune','2010-12-12','dans leau','description',150);	/* id : 6 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(4,4,30000,'solution final','2010-12-12','auschwitz','description',0);		/* id : 7 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(5,3,1500,'torromachi','2010-12-12','dans larene','description',166);		/* id : 8 */
INSERT INTO MANIFESTATION(FK_LOCALISATION_ID,FK_ID_TYPE,NB_PLACE,NOM_MANIFESTATION,DATE,ADRESSE,DESCRIPTION,PRIX) VALUES(6,3,30,'le foulard','2010-12-12','ya qune rue','description',1);		/* id : 9 */

