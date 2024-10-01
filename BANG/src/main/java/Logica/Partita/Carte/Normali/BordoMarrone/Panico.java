package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Panico per il gioco
 */
public class Panico extends BordoMarrone{
    /**
     * Il costruttore della carta Panico
     * @param seme il seme della carta
     */
    public Panico(String seme) {
        super(seme);
    }

    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
    }

    /**
     * Applica l'effetto della carta Panico
     * @param spara Il giocatore che spara
     * @param bersaglio Il giocatore berdaglaito
     */
    public boolean ApplicaEffetto(Giocatore spara, Giocatore bersaglio) {
        while (true) {
            try {
                spara.carte.add(bersaglio.carte.remove((int) (Math.random() * 10)));
                return true;
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }

    /**
     * Ritorna la descrizione della carta Panico
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Pesca una carta da un giocatore a distanza 1.";
    }

}
