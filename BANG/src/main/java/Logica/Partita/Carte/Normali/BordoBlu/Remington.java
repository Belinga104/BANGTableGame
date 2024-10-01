package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Remington per il gioco
 */
public class Remington extends Armi{
    /**
     * Il costruttore della carta Remington
     * @param seme il seme della carta
     */
    public Remington(String seme) {
        super(seme, 3);
    }

    /**
     * Applica l'effeto della carta Remington
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
