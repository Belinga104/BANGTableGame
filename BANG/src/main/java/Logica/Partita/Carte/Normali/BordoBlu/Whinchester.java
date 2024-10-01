package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Whinchester per il gioco
 */
public class Whinchester extends Armi{
    /**
     * Il costruttore della carta Whinchester
     * @param seme il seme della carta
     */
    public Whinchester(String seme) {
        super(seme,5);
    }

    /**
     * Applica l'effetto della carta Whinchester
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
}
