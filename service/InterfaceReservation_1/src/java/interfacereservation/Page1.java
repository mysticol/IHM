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
import org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionService;
import org.netbeans.xml.schema.types.Hotel;
import org.netbeans.xml.schema.types.Manifestation;
import org.netbeans.xml.schema.types.Restaurant;

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
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/Reception.wsdl")
    private ReceptionService service;

    private static String nom = "";
    private static String prenom = "";
    private static String pays = "";
    private static String ville = "";
    private static String dateReservation = "";
    private static String type = "";
    private static Hotel hotel = new Hotel();
    private static Restaurant restaurant = new Restaurant();
    private static Manifestation manif = new Manifestation();
    private static List<Hotel> listHotel = new LinkedList<Hotel>();
    private static List<Restaurant> listRestaurant = new LinkedList<Restaurant>();
    private static List<Manifestation> listManif = new LinkedList<Manifestation>();


    private static Option[] paysOption = { new Option("","") };
    private static Option[] villeOption = { new Option("","") };
    private static Option[] typeOption = { new Option("","") };
    private static Option[] nomHotelOption = { new Option("","") };
    private static Option[] nomRestaurantOption = { new Option("","") };
    private static Option[] nomManifOption = { new Option("","") };


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
        paysDropDownDefaultOptions.setOptions(paysOption);
        typeDropDownDefaultOptions.setOptions(typeOption);
        villeDropDownDefaultOptions.setOptions(villeOption);

    }
    private SingleSelectOptionsList paysDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getPaysDropDownDefaultOptions() {
        return paysDropDownDefaultOptions;
    }

    public void setPaysDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.paysDropDownDefaultOptions = ssol;
    }
    private SingleSelectOptionsList villeDropDownDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getVilleDropDownDefaultOptions() {
        return villeDropDownDefaultOptions;
    }

    public void setVilleDropDownDefaultOptions(SingleSelectOptionsList ssol) {
        this.villeDropDownDefaultOptions = ssol;
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
                org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();
                // TODO process result here
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the country returned by the Reception Web Services

                int taille = result.getPays().size();

                //We always set the first option to the empty string in order to
                //force the user to search a country
                Option[] resultPays = new Option[taille+1];
                resultPays[0] = new Option("","");
                int i = 1;

                System.out.println("Initialisation");

                for(String pays : result.getPays()){
                    System.out.println("    Pays lu : " + pays);
                    resultPays[i++] = new Option(pays,pays);
                }
                System.out.println("-------------------------------------------------");

                paysOption = resultPays;
                paysDropDownDefaultOptions.setOptions(paysOption);

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

    private static String descriptionEvenement;

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

    private static String rankRestaurant;

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


    private static String rankHotel;

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

        System.out.println("On change le type de réservation : " + event.getNewValue());
        System.out.println("Date : " + dateReservation);
        System.out.println("Pays : " + pays);
        System.out.println("Ville : " + ville);
        System.out.println("-------------------------------------------------");

        setDescriptionEvenement(manif.getDescription());

    }

    public void typeDropDown_processValueChange(ValueChangeEvent event) {

        type = (String) event.getNewValue();
        System.out.println("On change le type de réservation : " + type);
        System.out.println("Date : " + dateReservation);
        System.out.println("Pays : " + pays);
        System.out.println("Ville : " + ville);
        System.out.println("-------------------------------------------------");

        if (!type.equalsIgnoreCase("")){

            try { // Call Web Service Operation
                org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

                request.setDate(dateReservation);
                request.setVille(ville);
                request.setPays(pays);
                request.setTypeManifestation(type);

                // TODO process result here
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the name of manifestations, the hotels and the restaurants returned by the Reception Web Services

                //We always set the first option to the empty string in order to
                //force the user to make a choice
                listManif = result.getManifestation();

                int taille = result.getManifestation().size();
                Option[] resultManifs = new Option[taille+1];
                resultManifs[0] = new Option("","");
                int i = 1;

                System.out.println("Debut de la recuperation des manifestations");

                for(Manifestation maniftmp : result.getManifestation()){
                    System.out.println("    Manif lue : " + maniftmp.getNom());
                    resultManifs[i++] = new Option(maniftmp.getNom(),maniftmp.getNom());
                }

                nomManifOption = resultManifs;

                nomManifdropDownDefaultOptions.setOptions(nomManifOption);


                //We always set the first option to the empty string in order to
                //force the user to make a choice
                listHotel = result.getHotel();

                taille = result.getHotel().size();
                Option[] resultHotels = new Option[taille+1];
                resultHotels[0] = new Option("","");
                i = 1;

                System.out.println("Debut de la recuperation des hotels");

                for(Hotel hoteltmp : result.getHotel()){
                    System.out.println("    Hotel lu : " + hoteltmp.getNom());
                    resultHotels[i++] = new Option(hoteltmp.getNom(),hoteltmp.getNom());
                }

                nomHotelOption = resultHotels;

                nomHotelDropDownDefaultOptions.setOptions(nomHotelOption);


                //We always set the first option to the empty string in order to
                //force the user to make a choice
                listRestaurant = result.getRestaurant();

                taille = result.getRestaurant().size();
                Option[] resultRestos = new Option[taille+1];
                resultRestos[0] = new Option("","");
                i = 1;

                System.out.println("Debut de la recuperation des restaurants");

                for(Restaurant restotmp : result.getRestaurant()){
                    System.out.println("    Resto lu : " + restotmp.getNom());
                    resultRestos[i++] = new Option(restotmp.getNom(),restotmp.getNom());
                }

                nomRestaurantOption = resultRestos;

                nomRestaurantDropDownDefaultOptions.setOptions(nomRestaurantOption);


            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        } else {
            resetOptionExceptType();
        }

    }


    public void date_processValueChange(ValueChangeEvent event) {

        dateReservation = (String) event.getNewValue();
        System.out.println("On change la date : " + dateReservation);
        System.out.println("Pays : " + pays);
        System.out.println("Ville : " + ville);
        System.out.println("-------------------------------------------------");

        if(!ville.equalsIgnoreCase("") && !dateReservation.equalsIgnoreCase("")){

            try { // Call Web Service Operation
                org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

                request.setDate(dateReservation);
                request.setVille(ville);
                request.setPays(pays);

                // TODO process result here
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the type of manifestation returned by the Reception Web Services

                //We always set the first option to the empty string in order to
                //force the user to choose a type of manifestation
                int taille = result.getTypeManifestation().size();
                Option[] resultTypes = new Option[taille+1];
                resultTypes[0] = new Option("","");
                int i = 1;

                System.out.println("Debut de la recuperation des types de manifestation");

                for(String typetmp : result.getTypeManifestation()){
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

    }

    public void villeDropDown_processValueChange(ValueChangeEvent event) {

        ville = (String) event.getNewValue();
        System.out.println("On change le nom de la ville : " + ville);
        System.out.println("Pays : " + pays);
        System.out.println("Date : " + dateReservation);
        System.out.println("-------------------------------------------------");

        if(!ville.equalsIgnoreCase("") && !dateReservation.equalsIgnoreCase("")){

            try { // Call Web Service Operation
                org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
                // TODO initialize WS operation arguments here
                org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

                request.setDate(dateReservation);
                request.setVille(ville);
                request.setPays(pays);

                // TODO process result here
                org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);

                //We catch the type of manifestation returned by the Reception Web Services

                //We always set the first option to the empty string in order to
                //force the user to choose a type of manifestation
                int taille = result.getTypeManifestation().size();
                Option[] resultTypes = new Option[taille+1];
                resultTypes[0] = new Option("","");
                int i = 1;

                System.out.println("Debut de la recuperation des types de manifestation");

                for(String typetmp : result.getTypeManifestation()){
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

    }

    public void paysDropDown_processValueChange(ValueChangeEvent event) {

        pays = (String) event.getNewValue();
        System.out.println("On change le nom du pays : " + pays);
        System.out.println("Ville : " + ville);
        System.out.println("Date : " + dateReservation);
        System.out.println("-------------------------------------------------");

        // We request all the cities available, in the country 'pays'
        try { // Call Web Service Operation
            org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
            // TODO initialize WS operation arguments here
            org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();

            request.setPays(pays);

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

            villeOption = resultVilles;

            villeDropDownDefaultOptions.setOptions(villeOption);

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
            org.netbeans.j2ee.wsdl.mockreception.reception.ReceptionPortType port = service.getReceptionPortTypeBindingPort();
            // TODO initialize WS operation arguments here
            org.netbeans.xml.schema.types.InterfaceRequest request = new org.netbeans.xml.schema.types.InterfaceRequest();
            // TODO process result here
            org.netbeans.xml.schema.types.InterfaceResponse result = port.receptionOperation(request);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        return null;
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

    }

}

