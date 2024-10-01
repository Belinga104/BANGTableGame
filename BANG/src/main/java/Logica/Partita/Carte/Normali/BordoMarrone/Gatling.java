package Logica.Partita.Carte.Normali.BordoMarrone;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Gatling per il gioco
 */
public class Gatling extends BordoMarrone{
    /**
     * Il costruttore della carta Gatling
     * @param seme il seme della carta
     */
    public Gatling(String seme) {
        super(seme);
    }
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {

    }

    /**
     * Ritorna la descrizione della carta Gatling
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Il Gatling spara “un BANG” a “tutti gli altri giocatori”, indipendentemente dalla distanza.";
    }
}
