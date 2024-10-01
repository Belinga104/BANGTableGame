package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta RevCarabine per il gioco
 */
public class RevCarabine extends Armi{
    /**
     * Il costruttore della carta RevCarabine
     * @param seme il seme della carta
     */
    public RevCarabine(String seme) {
        super(seme, 4);
    }

    /**
     * Applica l'effetto della carta RevCarabine
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
