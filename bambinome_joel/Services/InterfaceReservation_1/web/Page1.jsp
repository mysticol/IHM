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
                            style="left: 120px; top: 120px; position: absolute" valueChangeListenerExpression="#{Page1.nom_processValueChange}"/>
                        <webuijsf:textField id="prenom" label="Prénom :" onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'prenom');"
                            required="true" style="left: 336px; top: 120px; position: absolute" valueChangeListenerExpression="#{Page1.prenom_processValueChange}"/>
                        <webuijsf:dropDown id="paysArriveeDropDown" items="#{Page1.paysArriveeDropDownDefaultOptions.options}" label="Pays d'arrivée :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'paysArriveeDropDown');"
                            style="left: 384px; top: 264px; position: absolute" valueChangeListenerExpression="#{Page1.paysArriveeDropDown_processValueChange}"/>
                        <webuijsf:dropDown id="villeArriveeDropDown" items="#{Page1.villeArriveeDropDownDefaultOptions.options}" label="Ville d'arrivée :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'villeArriveeDropDown');"
                            style="left: 384px; top: 288px; position: absolute" valueChangeListenerExpression="#{Page1.villeArriveeDropDown_processValueChange}"/>
                        <webuijsf:textField binding="#{Page1.date}" id="date" immediate="true" label="Date :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'date');" required="true"
                            style="left: 240px; top: 216px; position: absolute" valueChangeListenerExpression="#{Page1.date_processValueChange}"/>
                        <webuijsf:staticText id="formatDate" style="left: 432px; top: 216px; position: absolute" text="(format : YYYY-MM-DD)"/>
                        <webuijsf:staticText id="titrePage"
                            style="font-size: 24px; height: 48px; left: 192px; top: 0px; position: absolute; text-decoration: underline; width: 312px" text="ALMA’ Turn-key Week-End"/>
                        <webuijsf:pageSeparator id="pageSeparator1" style="height: 24px; left: 120px; top: 384px; position: absolute; width: 456px"/>
                        <webuijsf:dropDown id="nomManifdropDown" items="#{Page1.nomManifdropDownDefaultOptions.options}" label="Nom :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nomManifdropDown');"
                            style="left: 216px; top: 504px; position: absolute" valueChangeListenerExpression="#{Page1.nomManifdropDown_processValueChange}"/>
                        <webuijsf:dropDown id="typeDropDown" items="#{Page1.typeDropDownDefaultOptions.options}" label="Type :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'typeDropDown');"
                            style="left: 216px; top: 480px; position: absolute" valueChangeListenerExpression="#{Page1.typeDropDown_processValueChange}"/>
                        <webuijsf:textArea disabled="true" id="description" immediate="true" style="left: 384px; top: 480px; position: absolute"
                            text="#{Page1.descriptionEvenement}" valueChangeListenerExpression="#{Page1.description_processValueChange}"/>
                        <webuijsf:pageSeparator id="pageSeparator2" style="height: 24px; left: 120px; top: 528px; position: absolute; width: 456px"/>
                        <webuijsf:staticText id="titreConcert" style="font-size: 24px; left: 288px; top: 432px; position: absolute; text-decoration: underline" text="Manifestation"/>
                        <webuijsf:staticText id="titreHotel" style="font-size: 24px; left: 312px; top: 576px; position: absolute; text-decoration: underline" text="Hôtel"/>
                        <webuijsf:staticText id="titreRestaurant"
                            style="font-size: 24px; left: 288px; top: 720px; position: absolute; text-decoration: underline" text="Restaurant"/>
                        <webuijsf:dropDown id="nomHotelDropDown" items="#{Page1.nomHotelDropDownDefaultOptions.options}" label="Nom :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nomHotelDropDown');"
                            style="left: 192px; top: 624px; position: absolute" valueChangeListenerExpression="#{Page1.nomHotelDropDown_processValueChange}"/>
                        <webuijsf:dropDown id="nomRestaurantDropDown" items="#{Page1.nomRestaurantDropDownDefaultOptions.options}" label="Nom :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'nomRestaurantDropDown');"
                            style="left: 192px; top: 768px; position: absolute" valueChangeListenerExpression="#{Page1.nomRestaurantDropDown_processValueChange}"/>
                        <webuijsf:staticText id="avisHotel" style="left: 456px; top: 624px; position: absolute" text="#{Page1.rankHotel}"/>
                        <webuijsf:staticText id="avisRestaurant" style="left: 456px; top: 768px; position: absolute" text="#{Page1.rankRestaurant}"/>
                        <webuijsf:label id="labelPrixHotel" style="left: 360px; top: 624px; position: absolute" text="Nbre d'étoiles :"/>
                        <webuijsf:label id="labelPrixHotel1" style="left: 360px; top: 768px; position: absolute" text="Nbre d'étoiles :"/>
                        <webuijsf:button actionExpression="#{Page1.valider_action}" id="valider"
                            style="height: 24px; left: 263px; top: 1464px; position: absolute; width: 143px" text="Valider"/>
                        <webuijsf:pageSeparator id="pageSeparator3" style="height: 24px; left: 120px; top: 672px; position: absolute; width: 456px"/>
                        <webuijsf:button actionExpression="#{Page1.chercher_action}" id="chercher"
                            style="height: 48px; left: 287px; top: 336px; position: absolute; width: 96px" text="Chercher"/>
                        <webuijsf:textField id="paysDepartTextField" label="Pays de départ :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'paysDepartTextField');" required="true"
                            style="left: 144px; top: 264px; position: absolute" valueChangeListenerExpression="#{Page1.paysDepartTextField_processValueChange}"/>
                        <webuijsf:textField id="villeDepartTextField" label="Ville de départ :"
                            onChange="webui.suntheme4_2.common.timeoutSubmitForm(this.form, 'villeDepartTextField');" required="true"
                            style="left: 144px; top: 288px; position: absolute" valueChangeListenerExpression="#{Page1.villeDepartTextField_processValueChange}"/>
                        <webuijsf:imageHyperlink id="imageGoogleMap" imageURL="#{Page1.urlGoogleMap}" style="left: 120px; top: 888px; position: absolute"/>
                        <webuijsf:pageSeparator id="pageSeparator4" style="left: 96px; top: 1416px; position: absolute; width: 480px"/>
                        <webuijsf:button actionExpression="#{Page1.googleMap_action}" id="googleMap" style="left: 287px; top: 864px; position: absolute" text="Afficher la GoogleMap"/>
                        <webuijsf:staticText id="staticText1" style="left: 120px; top: 72px; position: absolute" text="Informations Personnelles"/>
                        <webuijsf:pageSeparator id="pageSeparator5" style="height: 7px; left: 120px; top: 72px; position: absolute; width: 456px"/>
                        <webuijsf:staticText id="staticText2" style="height: 22px; left: 120px; top: 168px; position: absolute; width: 94px" text="Informations TWE"/>
                        <webuijsf:pageSeparator id="pageSeparator6" style="height: 0px; left: 120px; top: 168px; position: absolute; width: 456px"/>
                        <webuijsf:staticText id="staticText3" style="height: 24px; left: 120px; top: 384px; position: absolute; width: 142px" text="Informations Manifestation"/>
                        <webuijsf:staticText id="staticText4" style="height: 24px; left: 120px; top: 528px; position: absolute; width: 96px" text="Informations Hôtel"/>
                        <webuijsf:staticText id="staticText5" style="height: 24px; left: 120px; top: 672px; position: absolute; width: 142px" text="Informations Restaurant"/>
                        <webuijsf:staticText id="staticText6" style="height: 24px; left: 120px; top: 816px; position: absolute; width: 96px" text="Géolocalisation"/>
                        <webuijsf:pageSeparator id="pageSeparator7" style="height: 0px; left: 120px; top: 816px; position: absolute; width: 456px"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
