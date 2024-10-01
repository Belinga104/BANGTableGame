package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeelsfargoTest {

    @Test
    void applicaEffetto() {
        Giocatore giocatore = new Giocatore();
        Bang bang = new Bang("1");
        Bang bang1 = new Bang("2");
        Bang bang2 = new Bang("3");
        Gioco gioco = new Gioco();
        gioco.mazzo.add(bang);
        gioco.mazzo.add(bang1);
        gioco.mazzo.add(bang2);
        Weelsfargo weelsfargo = new Weelsfargo("0");
        weelsfargo.ApplicaEffetto(gioco, giocatore);
        assertEquals(bang,giocatore.carte.getFirst());
        assertEquals(bang1,giocatore.carte.get(1));
        assertEquals(bang2,giocatore.carte.getLast());
    }
}