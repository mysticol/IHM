/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvca.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvca.model.Mode;
import mvca.vue.mainFrame;

/**
 *
 * @author Manou
 */
public class ControleurRadio {

    private mainFrame mv;

    public ControleurRadio(mainFrame aThis) {
       mv=aThis;
    }

    public ActionListener getClientListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Client);
            }
        };
    }

    public ActionListener getHotelListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Hotel);
            }
        };
    }

    public ActionListener getRestaurantListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Restaurant);
            }
        };
    }

    public ActionListener getLocalisationListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Localisation);
            }
        };
    }

    public ActionListener getVoyageListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Voyage);
            }
        };
    }

    public ActionListener getTypeManifestationListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.TypeManif);
            }
        };
    }

    public ActionListener getManifestationListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Manifestation);
            }
        };
    }

    public ActionListener getReservationManifestationListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.ReservationManif);
            }
        };
    }

    public ActionListener getReservationHotelListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.ReservationHotel);
            }
        };
    }

    public ActionListener getReservationRestaurantListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.ReservationRestaurant);
            }
        };
    }

    public ActionListener getReservationListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mv.setMode(Mode.Reservation);
            }
        };
    }
}
