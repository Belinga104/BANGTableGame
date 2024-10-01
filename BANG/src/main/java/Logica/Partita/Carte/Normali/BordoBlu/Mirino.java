package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Mirino per il gioco
 */
public class Mirino extends Permanenti {
    /**
     * Il costruttore di Mirino
     * @param seme il seme della carta
     */
    public Mirino(String seme) {
        super(seme);
    }

    /**
     * Applica l'effetto della carta Mirino
     * @param gioco il gioco Bang
     * @param giocatore Il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        giocatore.tabellone.carteT.add(this);
        Armi arma = giocatore.tabellone.getArma();
        arma.setDistanza(arma.getDistanza() + 1);
        giocatore.tabellone.setArma(arma);
    }

    /**
     * Ritorna la descrizione della carta Mirino
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Si vedono tutti gli altri giocatori a una distanza di 1. Tuttavia, gli altri giocatori continuano a vedervi alla distanza normale.";
    }


}
