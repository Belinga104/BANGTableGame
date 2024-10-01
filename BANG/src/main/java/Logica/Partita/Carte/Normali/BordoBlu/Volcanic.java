package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Volcanic per il gioco
 */
public class Volcanic extends Armi{
    /**
     * Il costruttore della carta Volcanic
     * @param seme il seme della carta
     */
    public Volcanic(String seme) {
        super(seme, 1);
    }

    /**
     * Applica l'effetto della carta Volcanic
     * @param gioco il gioco Bang
     * @param giocatore il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        if (giocatore.tabellone.getArma().getClass().getSimpleName().equals("Revolver")) {
            giocatore.tabellone.setArma(this);
        } else {
            gioco.scarti.add(giocatore.tabellone.getArma());
            giocatore.tabellone.setArma(this);
        }
    }

    /**
     * Ritorna la descrizione della carta Volcanic
     * @return la descrizione della carta
     */
    @Override
    public String toString() {
        return  " " + getClass().getSimpleName() + " (Arma) " + getSeme() + " Distanza: 1" + " Con questa carta in gioco si può giocare un numero qualsiasi di carte BANG durante il proprio turno.";
    }
}
