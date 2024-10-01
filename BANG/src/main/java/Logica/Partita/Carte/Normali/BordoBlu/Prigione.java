package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Prigione per il gioco
 */
public class Prigione extends Estrazione{
    /**
     * Il costruttore di Prigione
     * @param seme il seme della carta
     */
    public Prigione(String seme) {
        super(seme);
    }

    /**
     * Applica l'effetto della carta
     * @param gioco il gioco Bang
     * @param giocatore il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        if (!giocatore.tabellone.getRuolo().equals(Ruolo.SCERIFFO)) {
            giocatore.setLock(true);
        }
    }

    /**
     * Ritorna la descrizione della carta Priogione
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Se siete in prigione, dovete estrarre prima dell'inizio del vostro turno: se pescate una carta di cuori, evadete dalla prigione: scartate la prigione e continuate il vostro turno normalmenti altrimenti scartate la prigione e saltate il vostro turno. La Prigione non può essere giocata sullo Sceriffo.";
    }

}
