package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Birra per il gioco
 */
public class Birra extends BordoMarrone {
    /**
     * Il costruttore della carta Birra
     * @param seme il seme della carta
     */
    public Birra(String seme) {
        super(seme);
    }

    /**
     * Applica l'effetto della carta Birra
     * @param gioco il gioco Bang
     * @param giocatore il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        giocatore.setVita(giocatore.getVita()+1);
    }

    /**
     * Ritorna la descrizione della carta Birra
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Questa carta consente di recuperare un punto vita. Non si possono guadagnare più punti vita di quelli iniziali! ";
    }
}
