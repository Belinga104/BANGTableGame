package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Saloon per il gioco
 */
public class Saloon extends BordoMarrone {
    /**
     * Il costruttore della carta Saloon
     * @param seme il seme della carta
     */
    public Saloon(String seme) {
        super(seme);
    }

    /**
     * Applica l'effetto della carta Saloon
     * @param gioco il gioco Bang
     * @param giocatore il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        for (int i = 0; i < 4; i++) {
            if(gioco.giocatori[i].getVita()>0) {


                gioco.giocatori[i].setVita(gioco.giocatori[i].getVita() + 1);
            }
        }
    }

    /**
     * Ritorna la descrizione della carta Saloon
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " L'effetto è che tutti i giocatori in gioco recuperano un punto vita. ";
    }
}
