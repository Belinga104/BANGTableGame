package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatbalouTest {

    @Test
    void applicaEffetto() {
        Giocatore giocatore = new Giocatore();
        Bang bang = new Bang("1");
        giocatore.carte.add(bang);
        Gioco gioco = new Gioco();
        Catbalou catbalou = new Catbalou("0");
        catbalou.ApplicaEffetto(gioco, giocatore);
        assertEquals(bang,gioco.scarti.getLast());
    }
}