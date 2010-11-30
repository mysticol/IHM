/*
database : projet
username : admin
password : admin
jndi : jdbc/projet
*/


DROP TABLE RESERVATION    ;
DROP TABLE RESERVATION_HOTEL    ;
DROP TABLE  RESERVATION_MANIF    ;
DROP TABLE RESERVATION_RESTAU    ;

DROP TABLE HOTEL    ;
DROP TABLE RESTAURANT   ;


DROP TABLE MANIFESTATION    ;
DROP TABLE TYPE_MANIFESTATION    ;
DROP TABLE VOYAGE;
DROP TABLE LOCALISATION    ;
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
	RANK_HOTEL NUMERIC(5),
	PRIX NUMERIC(5)
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
	PRIX NUMERIC(5)
);



create table RESTAURANT
(
	RESTAURANT_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY,
	FK_LOCALISATION_ID INTEGER,
	ADRESSE VARCHAR(30),
	NOM_RESTAURANT VARCHAR(20),
	NB_COUVERTS INTEGER,
	RATE_RESTAURANT NUMERIC(5),
	PRIX NUMERIC(5)
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

insert into LOCALISATION values (default,'France','Nantes');
insert into LOCALISATION values (default,'France','Cholet');
insert into LOCALISATION values (default,'France','Paris');
insert into LOCALISATION values (default,'Chine','Pekin');
insert into LOCALISATION values (default,'USA','Washigton');
insert into LOCALISATION values (default,'USA','NY');

insert into TYPE_MANIFESTATION values (default,'concert');
insert into TYPE_MANIFESTATION values (default,'opera');
insert into TYPE_MANIFESTATION values (default,'theatre');

insert into HOTEL values (default,1,'hotel1','rue hotel1',42,5,25);
insert into HOTEL values (default,1,'hotel2','rue hotel2',43,4,35);
insert into HOTEL values (default,6,'hotel3','rue hotel3',44,3,15);
insert into HOTEL values (default,6,'hotel4','rue hotel4',45,2,25);

insert into MANIFESTATION values (default,1,1,100,'concert1','2010-11-26','rue du concert1','desc concert1',40);
insert into MANIFESTATION values (default,1,1,200,'concert2','2010-11-26','rue du concert2','desc concert2',40);
insert into MANIFESTATION values (default,6,1,300,'concert3','2010-11-26','rue du concert3','desc concert3',40);
insert into MANIFESTATION values (default,6,1,400,'concert4','2010-11-26','rue du concert4','desc concert4',40);

insert into RESTAURANT values (default,1,'rue resto1','resto1',42,5,25);
insert into RESTAURANT values (default,1,'rue resto2','resto2',60,4,35);
insert into RESTAURANT values (default,6,'rue resto3','resto3',40,3,15);
insert into RESTAURANT values (default,6,'rue resto4','resto4',18,2,25);


