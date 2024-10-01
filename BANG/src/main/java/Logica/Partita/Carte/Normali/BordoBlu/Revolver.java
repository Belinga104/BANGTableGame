package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Revolver per il gioco
 */
public class Revolver extends Armi{
    /**
     * Il costruttore della carta Revolver
     * @param seme il seme della carta
     * @param distanza la distanza che può sparare l'arma
     */
    public Revolver(String seme, int distanza) {
        super(seme, distanza);
    }

    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {

    }

    /**
     * Ritorna la descrizione della carta Revolver
     * @return la descrizione della carta
     */
    @Override
    public String toString() {
        return  " " + getClass().getSimpleName() + " (Arma) " + " Distanza: " + getDistanza();
    }
}
