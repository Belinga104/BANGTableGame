package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BirraTest {

    @Test
    void applicaEffetto() {
        Giocatore giocatore = new Giocatore();
        giocatore.setVita(4);
        Birra birra = new Birra("0");
        birra.ApplicaEffetto(new Gioco(), giocatore);
        assertEquals(5,giocatore.getVita());
    }
}