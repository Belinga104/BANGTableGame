package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiligenzaTest {

    @Test
    void applicaEffetto() {
        Giocatore giocatore = new Giocatore();
        Bang bang = new Bang("1");
        Bang bang1 = new Bang("2");
        Gioco gioco = new Gioco();
        gioco.mazzo.add(bang);
        gioco.mazzo.add(bang1);
        Diligenza diligenza = new Diligenza("0");
        diligenza.ApplicaEffetto(gioco, giocatore);
        assertEquals(bang,giocatore.carte.getFirst());
        assertEquals(bang1,giocatore.carte.getLast());
    }
}