package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PanicoTest {

    @Test
    void applicaEffetto() {
        Giocatore spara = new Giocatore();
        Giocatore bersaglio = new Giocatore();
        Bang bang = new Bang("1");
        bersaglio.carte.add(bang);
        new Panico("0").ApplicaEffetto(spara,bersaglio);
        assertEquals(bang,spara.carte.getLast());
        assertTrue(bersaglio.carte.isEmpty());
    }
}