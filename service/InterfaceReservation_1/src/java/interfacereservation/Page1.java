/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacereservation;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.model.Option;
import com.sun.webui.jsf.model.SingleSelectOptionsList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.event.ValueChangeEvent;
import javax.xml.ws.WebServiceRef;
import org.netbeans.j2ee.wsdl.mockreservation.reservation.ReservationService;
import org.netbeans.j2ee.wsdl.reception.reception.ReceptionService;
import org.netbeans.xml.schema.types.Hotels;
import org.netbeans.xml.schema.types.Manifestations;
import org.netbeans.xml.schema.types.Restaurants;
import org.netbeans.xml.schema.types.Voyage;
import org.jdom.*;
import org.jdom.output.*;
import java.io.*;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version PageClient.java
 * @version Created on 21 nov. 2010, 16:08:33
 * @author JulienSambre
 */

public class Page1 extends AbstractPageBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/Reservation.wsdl")
    private ReservationService service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/Reception.wsdl")
    private ReceptionService service;

    private static String nom = "";
    private static String prenom = "";
    private static String paysDepart = "";
    private static String villeDepart = "";
    private static String paysArrivee = "";
    private static String villeArrivee = "";
    private static String dateReservation = "";
    private static String type = "";
    private static Hotel hotel = new Hotel();
    private static Restaurant restaurant = new Restaurant();
    private static Manifestation manif = new Manifestation();
    private static List<Hotel> listHotel = new LinkedList<Hotel>();
    private static List<Restaurant> listRestaurant = new LinkedList<Restaurant>();
    private static List<Manifestation> listManif = new LinkedList<Manifestation>();


    private static Option[] paysArriveeOption = { new Option("","") };
    private static Option[] villeArriveeOption = { new Option("","") };
    private static Option[] typeOption = { new Option("","") };
    private static Option[] nomHotelOption = { new Option("","") };
    private static Option[] nomRestaurantOption = { new Option("","") };
    private static Option[] nomManifOption = { new Option("","") };

    /*
     * Elements pour fichier XML
     */
    static Element root = new Element("reservation");
    static Document doc = new Document(root);

    private static String pathPdf="/comptes/E032171T/fop-1.0/service/";
    private static String pathFop="/comptes/E032171T/fop-1.0/";


    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {

        // We set all the dropdown list to an empty string
        /*Option[] vide = {
            new Option("","")
        };*/

        nomHotelDropDownDefaultOptions.setOptions(nomHotelOption);
        nomManifdropDownDefaultOptions.setOptions(nomManifOption);
        nomRestaurantDropDownDefaultOptions.setOptions(nomRestaurantOption);
        paysArriveeDropDownDefaultOptions.setOptions(paysArriveeOption);
        typeDropDownDefaultOptions.setOptions(typeOption);
        villeArriveeDropDownDefaultOptions.setOptions(villeArriveeOption);

        setDescriptionEvenement(descriptionEvenement);
        setRankHotel(rankHotel);
        setRankRestaurant(rankRestaurant);

        //setUrlGoogleMap(createGoogleMapUrlImage("France", "Nantes", "Place du Commerce", "17, rue Voltaire", "3 allée Duquesne", "Manif", "Resto", "Hotel"));
    }
    private SingleSelectOptionsList paysArriveeDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getPaysArriveeDropDownDefaultOptions() {
        return paysArriveeDropDownDefaultOptions;
    }

    public void setPaysArriveeDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.paysArriveeDropDownDefaultOptions = ssol;
    }
    private SingleSelectOptionsList villeArriveeDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getVilleArriveeDropDownDefaultOptions() {
        return villeArriveeDropDownDefaultOptions;
    }

    public void setVilleArriveeDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.villeArriveeDropDownDefaultOptions = ssol;
    }
    private SingleSelectOptionsList nomManifdropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getNomManifdropDownDefaultOptions() {
        return nomManifdropDownDefaultOptions;
    }

    public void setNomManifdropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.nomManifdropDownDefaultOptions = ssol;
    }
    private SingleSelectOptionsList typeDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getTypeDropDownDefaultOptions() {
        return typeDropDownDefaultOptions;
    }

    public void setTypeDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.typeDropDownDefaultOptions = ssol;
    }
    private SingleSelectOptionsList nomHotelDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getNomHotelDropDownDefaultOptions() {
        return nomHotelDropDownDefaultOptions;
    }

    public void setNomHotelDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.nomHotelDropDownDefaultOptions = ssol;
    }
    private SingleSelectOptionsList nomRestaurantDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getNomRestaurantDropDownDefaultOptions() {
        return nomRestaurantDropDownDefaultOptions;
    }

    public void setNomRestaurantDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.nomRestaurantDropDownDefaultOptions = ssol;
    }
    private TextField date = new TextField();

    public TextField getDate() {
        return date;
    }

    public void setDate(TextField tf) {
        this.date = tf;
    }

    // </editor-fold>

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Page1() {
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {

            _init();

            // We request all the countries available
            try { // Call Web Service Operation
                org.netbeans.j2ee.wsdl.reception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();
                // TODO process result here
                
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the country returned by the Reception Web Services

                // We remove countries that appear several times
                List<String> paysList = new LinkedList<String>();
                for(String pays : result.getPays()){
                    if(!paysList.contains(pays)){
                        paysList.add(pays);
                    }
                }


                int taille = paysList.size();

                //We always set the first option to the empty string in order to
                //force the user to search a country
                Option[] resultPays = new Option[taille+1];
                resultPays[0] = new Option("","");
                int i = 1;

                System.out.println("Initialisation");

                for(String pays : paysList){
                    System.out.println("    Pays lu : " + pays);
                    resultPays[i++] = new Option(pays,pays);
                }
                System.out.println("-------------------------------------------------");

                paysArriveeOption = resultPays;
                paysArriveeDropDownDefaultOptions.setOptions(paysArriveeOption);

            } catch (Exception ex) {
                System.out.println("Erreur dans la reception d'un message");
                System.out.println(ex);
            }
            
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    @Override
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    @Override
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
     */
    @Override
    public void destroy() {
    }

    private static String descriptionEvenement = "";

    /**
     * Get the value of descriptionEvenement
     *
     * @return the value of descriptionEvenement
     */
    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    /**
     * Set the value of descriptionEvenement
     *
     * @param descriptionEvenement new value of descriptionEvenement
     */
    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    private static String rankRestaurant = "";

    /**
     * Get the value of rankRestaurant
     *
     * @return the value of rankRestaurant
     */
    public String getRankRestaurant() {
        return rankRestaurant;
    }

    /**
     * Set the value of rankRestaurant
     *
     * @param rankRestaurant new value of rankRestaurant
     */
    public void setRankRestaurant(String rankRestaurant) {
        this.rankRestaurant = rankRestaurant;
    }


    private static String rankHotel = "";

    /**
     * Get the value of rankHotel
     *
     * @return the value of rankHotel
     */
    public String getRankHotel() {
        return rankHotel;
    }

    /**
     * Set the value of rankHotel
     *
     * @param rankHotel new value of rankHotel
     */
    public void setRankHotel(String rankHotel) {
        this.rankHotel = rankHotel;
    }

    private static String urlGoogleMap;

    /**
     * Get the value of urlGoogleMap
     *
     * @return the value of urlGoogleMap
     */
    public String getUrlGoogleMap() {
        return urlGoogleMap;
    }

    /**
     * Set the value of urlGoogleMap
     *
     * @param urlGoogleMap new value of urlGoogleMap
     */
    public void setUrlGoogleMap(String urlGoogleMap) {
        this.urlGoogleMap = urlGoogleMap;
    }


    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public void prenom_processValueChange(ValueChangeEvent event) {
        prenom = (String) event.getNewValue();
    }

    public void nom_processValueChange(ValueChangeEvent event) {
        nom = (String) event.getNewValue();
    }

    public void paysDepartTextField_processValueChange(ValueChangeEvent event) {
        paysDepart = (String) event.getNewValue();
    }

    public void villeDepartTextField_processValueChange(ValueChangeEvent event) {
        villeDepart = (String) event.getNewValue();
    }

    public void nomRestaurantDropDown_processValueChange(ValueChangeEvent event) {

        String nomRestaurant = (String) event.getNewValue();

        boolean trouve = false;
        Iterator ir = listRestaurant.iterator();

        while(ir.hasNext() && !trouve){

            Restaurant restauranttmp = (Restaurant) ir.next();

            if(restauranttmp.getNom().equalsIgnoreCase(nomRestaurant)){
                trouve = true;
                restaurant = restauranttmp;
            }

        }

        System.out.println("On change le nom du restaurant : " + event.getNewValue());
        System.out.println("Type manif : " + type);
        System.out.println("Nom manif : " + manif.getNom());
        System.out.println("-------------------------------------------------");


        if(nomRestaurant.equalsIgnoreCase("")) {
            setRankRestaurant("");
        } else {
            setRankRestaurant(restaurant.getPrixMoyen().toString());
        }
    }

    public void nomHotelDropDown_processValueChange(ValueChangeEvent event) {

        String nomHotel = (String) event.getNewValue();

        boolean trouve = false;
        Iterator ir = listHotel.iterator();

        while(ir.hasNext() && !trouve){

            Hotel hoteltmp = (Hotel) ir.next();

            if(hoteltmp.getNom().equalsIgnoreCase(nomHotel)){
                trouve = true;
                hotel = hoteltmp;
            }

        }

        System.out.println("On change le nom de l'Hotel : " + event.getNewValue());
        System.out.println("Type manif : " + type);
        System.out.println("Nom manif : " + manif.getNom());
        System.out.println("-------------------------------------------------");


        if(nomHotel.equalsIgnoreCase("")) {
            setRankHotel("");
        } else {
            setRankHotel(hotel.getPrixMoyen().toString());
        }

    }

    public void nomManifdropDown_processValueChange(ValueChangeEvent event) {

        String nomManif = (String) event.getNewValue();

        boolean trouve = false;
        Iterator ir = listManif.iterator();

        while(ir.hasNext() && !trouve){

            Manifestation maniftmp = (Manifestation) ir.next();

            if(maniftmp.getNom().equalsIgnoreCase(nomManif)){
                trouve = true;
                manif = maniftmp;
            }

        }

        System.out.println("On change le nom de la manif : " + event.getNewValue());
        System.out.println("Date : " + dateReservation);
        System.out.println("Pays : " + paysArrivee);
        System.out.println("Ville : " + villeArrivee);
        System.out.println("Type manif : " + type);
        System.out.println("-------------------------------------------------");


        if(nomManif.equalsIgnoreCase("")) {
            setDescriptionEvenement("");
        } else {
            setDescriptionEvenement(manif.getDescription());
        }

    }

    public void typeDropDown_processValueChange(ValueChangeEvent event) {

        type = (String) event.getNewValue();
        System.out.println("On change le type de réservation : " + type);
        System.out.println("Date : " + dateReservation);
        System.out.println("Pays : " + paysArrivee);
        System.out.println("Ville : " + villeArrivee);
        System.out.println("-------------------------------------------------");

        if (!type.equalsIgnoreCase("")){

            try { // Call Web Service Operation
                org.netbeans.j2ee.wsdl.reception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

                request.setDate(dateReservation);
                request.setVille(villeArrivee);
                request.setPays(paysArrivee);
                request.setTypeManifestation(type);

                // TODO process result here
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the name of manifestations, the hotels and the restaurants returned by the Reception Web Services

                //We always set the first option to the empty string in order to
                //force the user to make a choice
                listManif = toListManifs(result.getManifestations());

                int taille = listManif.size();
                Option[] resultManifs = new Option[taille+1];
                resultManifs[0] = new Option("","");
                int i = 1;

                System.out.println("Debut de la recuperation des manifestations");

                for(Manifestation maniftmp : listManif){
                    System.out.println("    Manif lue : " + maniftmp.getNom());
                    resultManifs[i++] = new Option(maniftmp.getNom(),maniftmp.getNom());
                }

                nomManifOption = resultManifs;

                nomManifdropDownDefaultOptions.setOptions(nomManifOption);


                //We always set the first option to the empty string in order to
                //force the user to make a choice
                listHotel = toListHotels(result.getHotels());

                taille = listHotel.size();
                Option[] resultHotels = new Option[taille+1];
                resultHotels[0] = new Option("","");
                i = 1;

                System.out.println("Debut de la recuperation des hotels");

                for(Hotel hoteltmp : listHotel){
                    System.out.println("    Hotel lu : " + hoteltmp.getNom());
                    resultHotels[i++] = new Option(hoteltmp.getNom(),hoteltmp.getNom());
                }

                nomHotelOption = resultHotels;

                nomHotelDropDownDefaultOptions.setOptions(nomHotelOption);


                //We always set the first option to the empty string in order to
                //force the user to make a choice
                listRestaurant = toListRestaurants(result.getRestaurants());

                taille = listRestaurant.size();
                Option[] resultRestos = new Option[taille+1];
                resultRestos[0] = new Option("","");
                i = 1;

                System.out.println("Debut de la recuperation des restaurants");

                for(Restaurant restotmp : listRestaurant){
                    System.out.println("    Resto lu : " + restotmp.getNom());
                    resultRestos[i++] = new Option(restotmp.getNom(),restotmp.getNom());
                }

                nomRestaurantOption = resultRestos;

                nomRestaurantDropDownDefaultOptions.setOptions(nomRestaurantOption);


            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            resetOptionExceptType();
        }

    }


    public void date_processValueChange(ValueChangeEvent event) {

        dateReservation = (String) event.getNewValue();
        System.out.println("On change la date : " + dateReservation);
        System.out.println("Pays : " + paysArrivee);
        System.out.println("Ville : " + villeArrivee);
        System.out.println("-------------------------------------------------");

        resetOptionFromType();

    }

    public void villeArriveeDropDown_processValueChange(ValueChangeEvent event) {

        villeArrivee = (String) event.getNewValue();
        System.out.println("On change le nom de la ville : " + villeArrivee);
        System.out.println("Pays : " + paysArrivee);
        System.out.println("Date : " + dateReservation);
        System.out.println("-------------------------------------------------");

        resetOptionFromType();

    }

    public void paysArriveeDropDown_processValueChange(ValueChangeEvent event) {

        paysArrivee = (String) event.getNewValue();
        System.out.println("On change le nom du pays : " + paysArrivee);
        System.out.println("Ville : " + villeArrivee);
        System.out.println("Date : " + dateReservation);
        System.out.println("-------------------------------------------------");

        // We request all the cities available, in the country 'pays'
        try { // Call Web Service Operation
            org.netbeans.j2ee.wsdl.reception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
            // TODO initialize WS operation arguments here
            org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

            request.setPays(paysArrivee);

            // TODO process result here
            org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

            //We catch the cities returned by the Reception Web Services

            //We always set the first option to the empty string in order to
            //force the user to choose a city
            int taille = result.getVille().size();
            Option[] resultVilles = new Option[taille+1];
            resultVilles[0] = new Option("","");
            int i = 1;

            System.out.println("Debut de la recuperation des villes");

            for(String villetmp : result.getVille()){
                System.out.println("    Ville lue : " + villetmp);
                resultVilles[i++] = new Option(villetmp,villetmp);
            }

            villeArriveeOption = resultVilles;

            villeArriveeDropDownDefaultOptions.setOptions(villeArriveeOption);

            resetOptionFromType();

        } catch (Exception ex) {
            System.out.println("Erreur dans la reception d'un message");
            System.out.println(ex);
        }

    }

    public String valider_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.


        try { // Call Web Service Operation
            org.netbeans.j2ee.wsdl.mockreservation.reservation.ReservationPortType port = service_1.getReservationPort();
            // TODO initialize WS operation arguments here
            org.netbeans.xml.schema.types.ReservationRequest reservationRequest = new org.netbeans.xml.schema.types.ReservationRequest();

            reservationRequest.setNom(nom);
            reservationRequest.setPrenom(prenom);
            reservationRequest.setDate(dateReservation);

            Voyage voyage = new Voyage();
            voyage.setPaysDepart(paysDepart);
            voyage.setVilleDepart(villeDepart);
            voyage.setPaysArrivee(paysArrivee);
            voyage.setVilleArriver(villeArrivee);

            reservationRequest.setVoyage(voyage);
            reservationRequest.setIdManif(manif.getId());
            reservationRequest.setIdHotel(hotel.getId());
            reservationRequest.setIdRestau(restaurant.getId());

            System.out.println(nom);
            System.out.println(prenom);
            System.out.println(dateReservation);
            System.out.println(paysDepart);
            System.out.println(villeDepart);
            System.out.println(paysArrivee);
            System.out.println(villeArrivee);
            System.out.println(manif.getId());
            System.out.println(hotel.getId());
            System.out.println(restaurant.getId());


            try{
                
                Element infosPerso = new Element("infosPerso");
                Element lieux = new Element("lieux");
                Element manifestation = new Element("manifestation");
                Element infosHotel = new Element("infosHotel");
                Element infosRestaurant = new Element("infosRestaurant");

                root = new Element("reservation");
                doc = new Document(root);

                root.addContent(infosPerso);
                root.addContent(lieux);
                root.addContent(manifestation);
                root.addContent(infosHotel);
                root.addContent(infosRestaurant);

                Element nomR = new Element("nom");
                nomR.setText(nom);
                Element prenomR = new Element("prenom");
                prenomR.setText(prenom);
                infosPerso.addContent(nomR);
                infosPerso.addContent(prenomR);

                Element paysDepartE = new Element("paysDepart");
                paysDepartE.setText(paysDepart);
                Element villeDepartE = new Element("villeDepart");
                villeDepartE.setText(villeDepart);
                Element paysArriveeE = new Element("paysArrivee");
                paysArriveeE.setText(paysArrivee);
                Element villeArriveeE = new Element("villeArrivee");
                villeArriveeE.setText(villeArrivee);
                lieux.addContent(paysDepartE);
                lieux.addContent(villeDepartE);
                lieux.addContent(paysArriveeE);
                lieux.addContent(villeArriveeE);

                Element date = new Element("date");
                date.setText(dateReservation);
                Element manifE = new Element("manif");
                manifE.setText(manif.getNom());
                Element description = new Element("description");
                description.setText(manif.getDescription());
                Element adresseManif = new Element("adresseManif");
                adresseManif.setText(manif.getAdresse());
                Element prixManif = new Element("prixManif");
                prixManif.setText(manif.getPrix().toString());
                manifestation.addContent(date);
                manifestation.addContent(manifE);
                manifestation.addContent(description);
                manifestation.addContent(adresseManif);
                manifestation.addContent(prixManif);


                Element nomHotel = new Element("nomHotel");
                nomHotel.setText(hotel.getNom());
                infosHotel.addContent(nomHotel);
                Element adresseHotel = new Element("adresseHotel");
                adresseHotel.setText(hotel.getAdresse());
                infosHotel.addContent(adresseHotel);
                Element prixHotel = new Element("prixHotel");
                prixHotel.setText(hotel.getPrix().toString());
                infosHotel.addContent(prixHotel);

                Element nomRestaurant = new Element("nomRestaurant");
                nomRestaurant.setText(restaurant.getNom());
                infosRestaurant.addContent(nomRestaurant);
                Element adresseRestaurant = new Element("adresseRestaurant");
                adresseRestaurant.setText(restaurant.getAdresse());
                infosRestaurant.addContent(adresseRestaurant);
                Element prixRestaurant = new Element("prixRestaurant");
                prixRestaurant.setText(restaurant.getPrix().toString());
                infosRestaurant.addContent(prixRestaurant);

                affiche();
                enregistre(pathPdf+"reservation"+nom+".xml");

                String cmd = pathFop+"fop -xml "+pathPdf+"reservation"+nom+".xml -xsl "+pathPdf+"xmlToPdf.xsl -pdf "+pathPdf+"reservation"+nom+".pdf";
                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                try{
                    process = runtime.exec(cmd);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                
            }catch (Throwable e) {
                e.printStackTrace();
            }


            // TODO process result here
            java.lang.String result = port.reservationOperation(reservationRequest);

            if(!result.equalsIgnoreCase("ok")){
                //If there is a problem, we print it in the console

                System.out.println("Erreur : " + result);
                
            }

          } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        return null;
    }

    static void affiche()
    {
       try
       {
          //affichage dans le terminal
          XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
          sortie.output(doc, System.out);
       }
       catch (java.io.IOException e){}
    }

    static void enregistre(String fichier)
    {
       try
       {
          XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
          //enregistrement dans un fichier
          sortie.output(doc, new FileOutputStream(fichier));
       }
       catch (java.io.IOException e){}
    }

    //Librairies

    private void resetOptionFromType() {
        Option[] vide = {
            new Option("","")
        };

        nomHotelOption = vide;
        nomManifOption = vide;
        nomRestaurantOption = vide;
        typeOption= vide;

        nomHotelDropDownDefaultOptions.setOptions(nomHotelOption);
        nomManifdropDownDefaultOptions.setOptions(nomManifOption);
        nomRestaurantDropDownDefaultOptions.setOptions(nomRestaurantOption);
        typeDropDownDefaultOptions.setOptions(typeOption);

        setDescriptionEvenement("");
        setRankHotel("");
        setRankRestaurant("");

    }

    private void resetOptionExceptType() {
        Option[] vide = {
            new Option("","")
        };

        nomHotelOption = vide;
        nomManifOption = vide;
        nomRestaurantOption = vide;

        nomHotelDropDownDefaultOptions.setOptions(nomHotelOption);
        nomManifdropDownDefaultOptions.setOptions(nomManifOption);
        nomRestaurantDropDownDefaultOptions.setOptions(nomRestaurantOption);

        setDescriptionEvenement("");
        setRankHotel("");
        setRankRestaurant("");

    }

    public String chercher_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.

        if(!villeArrivee.equalsIgnoreCase("") && !dateReservation.equalsIgnoreCase("")){

            try { // Call Web Service Operation
                org.netbeans.j2ee.wsdl.reception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

                request.setDate(dateReservation);
                request.setVille(villeArrivee);
                request.setPays(paysArrivee);

                // TODO process result here
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the type of manifestation returned by the Reception Web Services

                //We always set the first option to the empty string in order to
                //force the user to choose a type of manifestation
                List<String> typeManif = new LinkedList<String>();

                for(String type : result.getTypeManifestation()){
                    if(!typeManif.contains(type)){
                        typeManif.add(type);
                    }
                }


                int taille = typeManif.size();
                Option[] resultTypes = new Option[taille+1];
                resultTypes[0] = new Option("","");
                int i = 1;

                System.out.println("Debut de la recuperation des types de manifestation");

                for(String typetmp : typeManif){
                    System.out.println("    Type lu : " + typetmp);
                    resultTypes[i++] = new Option(typetmp,typetmp);
                }

                typeOption = resultTypes;

                typeDropDownDefaultOptions.setOptions(typeOption);

                resetOptionExceptType();

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        } else {
            resetOptionFromType();
        }

        return null;

    }


    private List<Manifestation> toListManifs(Manifestations manifs){
        List<Manifestation> listManifs = new LinkedList<Manifestation>();

        for(int i = 0; i < manifs.getID().size(); i++){
            String id = manifs.getID().get(i);
            String nomManif = manifs.getNom().get(i);
            String adresse = manifs.getAdresse().get(i);
            String description = manifs.getDescription().get(i);
            Float prix = manifs.getPrix().get(i);
            int placesRestantes = manifs.getPlacesRestantes().get(i);

            Manifestation maniftmp = new Manifestation(id, nomManif, adresse, prix, description, placesRestantes);

            listManifs.add(maniftmp);
        }

        return listManifs;
    }

    private List<Hotel> toListHotels(Hotels hotels){
        List<Hotel> listHotels = new LinkedList<Hotel>();

        for(int i = 0; i < hotels.getID().size(); i++){
            String id = hotels.getID().get(i);
            String nomHotel = hotels.getNom().get(i);
            String adresse = hotels.getAdresse().get(i);
            Float prix = hotels.getPrix().get(i);
            int rank = hotels.getRank().get(i);
            Float prixMoyen = hotels.getPrixMoyen().get(i);
            int placesRestantes = hotels.getPlacesRestantes().get(i);
            
            Hotel hoteltmp = new Hotel(id, nomHotel, adresse, prix, rank, prixMoyen, placesRestantes);
            
            listHotels.add(hoteltmp);

        }

        return listHotels;
    }

    private List<Restaurant> toListRestaurants(Restaurants restaurants){
        List<Restaurant> listRestaurants = new LinkedList<Restaurant>();

        for(int i = 0; i < restaurants.getID().size(); i++){
            String id = restaurants.getID().get(i);
            String nomRestaurant = restaurants.getNom().get(i);
            String adresse = restaurants.getAdresse().get(i);
            Float prix = restaurants.getPrix().get(i);
            int rank = restaurants.getRank().get(i);
            Float prixMoyen = restaurants.getPrixMoyen().get(i);
            int placesRestantes = restaurants.getPlacesRestantes().get(i);

            Restaurant restauranttmp = new Restaurant(id, nomRestaurant, adresse, prix, rank, prixMoyen, placesRestantes);

            listRestaurants.add(restauranttmp);

        }

        return listRestaurants;
    }

    private String createGoogleMapUrlImage(String pays, String ville, String adresseManif, String adresseResto, String adresseHotel, String nomManif, String nomResto, String nomHotel){

        /*setUrlGoogleMap("http://maps.google.com/maps/api/staticmap?" +
                "center=Place+du+Commerce,Nantes,France" +
                "&zoom=14&size=512x512" +
                "&maptype=roadmap" +
                "&markers=color:blue|label:Fnac|Place+du+Commerce,Nantes,France" +
                "&sensor=false");*/


        String urlimage = "http://maps.google.com/maps/api/staticmap?";

        urlimage += "center=" + ville + "," + pays;

        urlimage += "&zoom=14&size=512x512";

        urlimage += "&markers=icon:http://chart.apis.google.com/chart%3Fchst%3Dd_map_pin_icon%26chld%3Dwheelchair%257CFFFFFF|" + adresseManif.replace(" ", "+") + "," + ville + "," + pays;
        urlimage += "&markers=icon:http://chart.apis.google.com/chart%3Fchst%3Dd_map_pin_icon%26chld%3Drestaurant%257CFFFFFF|" + adresseResto.replace(" ", "+") + "," + ville + "," + pays;
        urlimage += "&markers=icon:http://chart.apis.google.com/chart%3Fchst%3Dd_map_pin_icon%26chld%3Dhome%257CFFFFFF|" + adresseHotel.replace(" ", "+") + "," + ville + "," + pays;

        urlimage += "&sensor=false";

        return urlimage;
    }

    public String googleMap_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.

        setUrlGoogleMap(createGoogleMapUrlImage(paysArrivee, villeArrivee, manif.getAdresse(), restaurant.getAdresse(), hotel.getAdresse(), manif.getNom(), restaurant.getNom(), hotel.getNom()));

        return null;
    }

}

