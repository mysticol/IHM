<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : Page1
    Created on : 21 nov. 2010, 16:08:33
    Author     : JulienSambre
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page id="page1">
            <webuijsf:html id="html1">
                <webuijsf:head id="head1">
                    <webuijsf:link id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body id="body1" style="-rave-layout: grid">
                    <webuijsf:form id="form1">
                        <webuijsf:textField id="nom" label="Nom : " style="left: 120px; top: 120px; position: absolute" valueChangeListenerExpression="#{Page1.nom_processValueChange}"/>
                        <webuijsf:textField id="prenom" label="Prénom :" style="left: 360px; top: 120px; position: absolute" valueChangeListenerExpression="#{Page1.prenom_processValueChange}"/>
                        <webuijsf:dropDown id="paysDropDown" immediate="true" items="#{Page1.paysDropDownDefaultOptions.options}" label="Pays :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'paysDropDown');"
                            style="left: 144px; top: 192px; position: absolute" valueChangeListenerExpression="#{Page1.paysDropDown_processValueChange}"/>
                        <webuijsf:dropDown id="villeDropDown" items="#{Page1.villeDropDownDefaultOptions.options}" label="Ville :" style="left: 144px; top: 240px; position: absolute"/>
                        <webuijsf:textField id="date" label="Date :" style="left: 360px; top: 192px; position: absolute"/>
                        <webuijsf:staticText id="formatDate" style="left: 408px; top: 216px; position: absolute" text="(format : YYYY-MM-DD)"/>
                        <webuijsf:staticText id="titrePage"
                            style="font-size: 24px; height: 48px; left: 192px; top: 24px; position: absolute; text-decoration: underline; width: 312px" text="Formulaire de Réservation"/>
                        <webuijsf:pageSeparator id="pageSeparator1" style="height: 24px; left: 96px; top: 288px; position: absolute; width: 480px"/>
                        <webuijsf:dropDown id="nomManifdropDown" items="#{Page1.nomManifdropDownDefaultOptions.options}" label="Nom :" style="left: 144px; top: 456px; position: absolute"/>
                        <webuijsf:dropDown id="typeDropDown" items="#{Page1.typeDropDownDefaultOptions.options}" label="Type :" style="left: 144px; top: 408px; position: absolute"/>
                        <webuijsf:textArea id="description" style="left: 384px; top: 408px; position: absolute" valueChangeListenerExpression="#{Page1.description_processValueChange}"/>
                        <webuijsf:pageSeparator id="pageSeparator2" style="height: 24px; left: 96px; top: 504px; position: absolute; width: 480px"/>
                        <webuijsf:staticText id="titreConcert" style="font-size: 24px; left: 288px; top: 336px; position: absolute; text-decoration: underline" text="Concert"/>
                        <webuijsf:staticText id="titreHotel" style="font-size: 24px; left: 168px; top: 552px; position: absolute; text-decoration: underline" text="Hôtel"/>
                        <webuijsf:staticText id="titreRestaurant"
                            style="font-size: 24px; left: 408px; top: 552px; position: absolute; text-decoration: underline" text="Restaurant"/>
                        <webuijsf:dropDown id="nomHotelDropDown" items="#{Page1.nomHotelDropDownDefaultOptions.options}" label="Nom :" style="left: 144px; top: 600px; position: absolute"/>
                        <webuijsf:dropDown id="nomRestaurantDropDown" items="#{Page1.nomRestaurantDropDownDefaultOptions.options}" label="Nom :" style="left: 408px; top: 600px; position: absolute"/>
                        <webuijsf:staticText id="avisHotel" style="left: 192px; top: 648px; position: absolute"/>
                        <webuijsf:staticText id="avisRestaurant" style="position: absolute; left: 456px; top: 648px"/>
                        <webuijsf:label id="labelPrixHotel" style="left: 96px; top: 648px; position: absolute" text="Avis moyen : "/>
                        <webuijsf:label id="labelPrixHotel1" style="left: 360px; top: 648px; position: absolute" text="Avis moyen : "/>
                        <webuijsf:button id="valider" style="height: 24px; left: 263px; top: 792px; position: absolute; width: 143px" text="Valider"/>
                        <webuijsf:pageSeparator id="pageSeparator3" style="position: absolute; left: 96px; top: 696px; width: 480px; height: 24px"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
