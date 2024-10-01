package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndianiTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        for (int i = 0; i < 4; i++) {
            gioco.giocatori[i] = new Giocatore();
            gioco.giocatori[i].setVita(5);
        }
        Bang bang = new Bang("1");
        gioco.giocatori[0].carte.add(bang);
        new Indiani("0").ApplicaEffetto(gioco, gioco.giocatori[3]);
        assertEquals(bang,gioco.scarti.getLast());
        assertEquals(5,gioco.giocatori[0].getVita());
        assertEquals(4,gioco.giocatori[1].getVita());
        assertEquals(4,gioco.giocatori[2].getVita());
    }
}