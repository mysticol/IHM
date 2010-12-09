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
                        <webuijsf:textField id="nom" label="Nom : " onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nom');" required="true"
                            style="left: 120px; top: 72px; position: absolute" valueChangeListenerExpression="#{Page1.nom_processValueChange}"/>
                        <webuijsf:textField id="prenom" label="Prénom :" onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'prenom');"
                            required="true" style="left: 312px; top: 72px; position: absolute" valueChangeListenerExpression="#{Page1.prenom_processValueChange}"/>
                        <webuijsf:dropDown id="paysArriveeDropDown" items="#{Page1.paysArriveeDropDownDefaultOptions.options}" label="Pays d'arrivée :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'paysArriveeDropDown');"
                            style="left: 384px; top: 192px; position: absolute" valueChangeListenerExpression="#{Page1.paysArriveeDropDown_processValueChange}"/>
                        <webuijsf:dropDown id="villeArriveeDropDown" items="#{Page1.villeArriveeDropDownDefaultOptions.options}" label="Ville d'arrivée :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'villeArriveeDropDown');"
                            style="left: 384px; top: 240px; position: absolute" valueChangeListenerExpression="#{Page1.villeArriveeDropDown_processValueChange}"/>
                        <webuijsf:textField binding="#{Page1.date}" id="date" immediate="true" label="Date :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'date');" required="true"
                            style="left: 240px; top: 120px; position: absolute" valueChangeListenerExpression="#{Page1.date_processValueChange}"/>
                        <webuijsf:staticText id="formatDate" style="left: 288px; top: 144px; position: absolute" text="(format : YYYY-MM-DD)"/>
                        <webuijsf:staticText id="titrePage"
                            style="font-size: 24px; height: 48px; left: 192px; top: 0px; position: absolute; text-decoration: underline; width: 312px" text="Formulaire de Réservation"/>
                        <webuijsf:pageSeparator id="pageSeparator1" style="height: 24px; left: 96px; top: 288px; position: absolute; width: 480px"/>
                        <webuijsf:dropDown id="nomManifdropDown" items="#{Page1.nomManifdropDownDefaultOptions.options}" label="Nom :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nomManifdropDown');"
                            style="left: 144px; top: 456px; position: absolute" valueChangeListenerExpression="#{Page1.nomManifdropDown_processValueChange}"/>
                        <webuijsf:dropDown id="typeDropDown" items="#{Page1.typeDropDownDefaultOptions.options}" label="Type :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'typeDropDown');"
                            style="left: 144px; top: 408px; position: absolute" valueChangeListenerExpression="#{Page1.typeDropDown_processValueChange}"/>
                        <webuijsf:textArea disabled="true" id="description" immediate="true" style="left: 384px; top: 408px; position: absolute"
                            text="#{Page1.descriptionEvenement}" valueChangeListenerExpression="#{Page1.description_processValueChange}"/>
                        <webuijsf:pageSeparator id="pageSeparator2" style="height: 24px; left: 96px; top: 504px; position: absolute; width: 480px"/>
                        <webuijsf:staticText id="titreConcert" style="font-size: 24px; left: 288px; top: 336px; position: absolute; text-decoration: underline" text="Evénement"/>
                        <webuijsf:staticText id="titreHotel" style="font-size: 24px; left: 168px; top: 552px; position: absolute; text-decoration: underline" text="Hôtel"/>
                        <webuijsf:staticText id="titreRestaurant"
                            style="font-size: 24px; left: 408px; top: 552px; position: absolute; text-decoration: underline" text="Restaurant"/>
                        <webuijsf:dropDown id="nomHotelDropDown" items="#{Page1.nomHotelDropDownDefaultOptions.options}" label="Nom :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nomHotelDropDown');"
                            style="left: 144px; top: 600px; position: absolute" valueChangeListenerExpression="#{Page1.nomHotelDropDown_processValueChange}"/>
                        <webuijsf:dropDown id="nomRestaurantDropDown" items="#{Page1.nomRestaurantDropDownDefaultOptions.options}" label="Nom :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nomRestaurantDropDown');"
                            style="left: 408px; top: 600px; position: absolute" valueChangeListenerExpression="#{Page1.nomRestaurantDropDown_processValueChange}"/>
                        <webuijsf:staticText id="avisHotel" style="left: 216px; top: 648px; position: absolute" text="#{Page1.rankHotel}"/>
                        <webuijsf:staticText id="avisRestaurant" style="left: 480px; top: 648px; position: absolute" text="#{Page1.rankRestaurant}"/>
                        <webuijsf:label id="labelPrixHotel" style="left: 120px; top: 648px; position: absolute" text="Avis moyen : "/>
                        <webuijsf:label id="labelPrixHotel1" style="left: 384px; top: 648px; position: absolute" text="Avis moyen : "/>
                        <webuijsf:button actionExpression="#{Page1.valider_action}" id="valider"
                            style="height: 24px; left: 383px; top: 744px; position: absolute; width: 143px" text="Valider"/>
                        <webuijsf:pageSeparator id="pageSeparator3" style="position: absolute; left: 96px; top: 696px; width: 480px; height: 24px"/>
                        <webuijsf:button actionExpression="#{Page1.chercher_action}" id="chercher" style="left: 287px; top: 288px; position: absolute" text="Chercher un événement"/>
                        <webuijsf:textField id="paysDepartTextField" label="Pays de départ :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'paysDepartTextField');" required="true"
                            style="left: 120px; top: 192px; position: absolute" valueChangeListenerExpression="#{Page1.paysDepartTextField_processValueChange}"/>
                        <webuijsf:textField id="villeDepartTextField" label="Ville de départ :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'villeDepartTextField');" required="true"
                            style="position: absolute; left: 120px; top: 240px" valueChangeListenerExpression="#{Page1.villeDepartTextField_processValueChange}"/>
                        <webuijsf:imageHyperlink id="imageGoogleMap" imageURL="#{Page1.urlGoogleMap}" style="left: 96px; top: 792px; position: absolute"/>
                        <webuijsf:button actionExpression="#{Page1.googleMap_action}" id="googleMap"
                            style="height: 24px; left: 143px; top: 744px; position: absolute" text="Afficher la Goooooogle Map"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
