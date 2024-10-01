package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Normali.BordoMarrone.Bang;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarileTest {
    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        gioco.mazzo.add(new Bang("9F"));
        gioco.mazzo.add(new Bang("QC"));
        Giocatore giocatore = new Giocatore();
        giocatore.setVita(2);
        Barile barile = new Barile("0");
        barile.ApplicaEffetto(gioco,giocatore);
        assertEquals("9F", gioco.scarti.getLast().getSeme());
        assertEquals(1,giocatore.getVita());
        barile.ApplicaEffetto(gioco,giocatore);
        assertEquals("QC", gioco.scarti.getLast().getSeme());
        assertEquals(1,giocatore.getVita());
    }
}