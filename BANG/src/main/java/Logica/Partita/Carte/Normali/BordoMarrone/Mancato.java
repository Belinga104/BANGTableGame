package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Mancato per il gioco
 */
public class Mancato extends BordoMarrone {
    /**
     * Il costruttore della carta Mancato
     * @param seme il seme della carta
     */
    public Mancato(String seme) {
        super(seme);
    }

    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {

    }

    /**
     * Ritorna la descrizione della carta Mancato
     * @return la descrizione della carta
     *
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Se si viene colpiti da un BANG si può giocare immediatamente un Mancato anche se non è il vostro turno per annullare il colpo. Se non lo si fa, si perde un punto vita.";
    }
}
