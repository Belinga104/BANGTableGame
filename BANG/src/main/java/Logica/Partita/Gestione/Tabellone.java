package Logica.Partita.Gestione;

import Logica.Partita.Carte.Normali.BordoBlu.Armi;
import Logica.Partita.Carte.Normali.BordoBlu.Revolver;
import Logica.Partita.Carte.Normali.Carta;
import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;

import java.io.Serializable;
import java.util.ArrayList;

public class Tabellone implements Serializable {

    private final Ruolo ruolo;
    private final Personaggio personaggio;
    private Armi arma;
    public final ArrayList<Carta> carteT;

    public Tabellone(Ruolo ruolo,Personaggio personaggio) {
        this.ruolo = ruolo;
        this.personaggio = personaggio;
        arma = new Revolver("0", 1);
        carteT = new ArrayList<>();
    }

    /**
     * Serve per ritornare l'arma
     * @return l'arma che ha in mano un giocatore
     */
    public Armi getArma() {
        return arma;
    }

    /**
     * Serve per settare l'arma
     * @param arma L'arma da settare
     */
    public void setArma(Armi arma) {
        this.arma = arma;
    }

    /**
     * Serve per ritornare il personaggio
     * @return Il personaggio
     */
    public Personaggio getPersonaggio() {
        return personaggio;
    }

    /**
     * Serve per ritornare il ruolo
     * @return Il ruolo
     */
    public Ruolo getRuolo() {
        return ruolo;
    }
}
