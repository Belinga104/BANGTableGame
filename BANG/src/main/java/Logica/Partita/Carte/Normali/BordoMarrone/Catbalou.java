package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Catbalou per questo gioco
 */
public class Catbalou extends BordoMarrone {
    /**
     * Il costruttore della carta Catbalou
     * @param seme il seme della carta
     */
    public Catbalou(String seme) {
        super(seme);
    }

    /**
     * Applica l'effetto della carta Catbalou
     * @param gioco il gioco Bang
     * @param giocatore il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        while (true) {
            try {
                gioco.scarti.add(giocatore.carte.remove((int) (Math.random() * 10)));
                return;
            } catch (IndexOutOfBoundsException ignored) {}
        }
    }

    /**
     * Ritorna la descrizione della carta Catbalou
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Costringere un giocatore qualsiasi a scartare una carta, indipendentemente dalla distanza.";
    }
}
