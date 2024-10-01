package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuelloTest {

    @Test
    void applicaEffetto() {
        Giocatore spara = new Giocatore();
        Bang bang = new Bang("1");
        spara.setVita(5);
        Giocatore bersaglio = new Giocatore();
        spara.carte.add(bang);
        bersaglio.setVita(5);
        Gioco gioco = new Gioco();
        Duello duello = new Duello("0");
        boolean flag = duello.ApplicaEffetto(spara, bersaglio,gioco);
        assertFalse(flag);
        assertEquals(4,bersaglio.getVita());
        assertEquals(bang,gioco.scarti.getLast());
        bang = new Bang("2");
        spara.carte.add(bang);
        flag = duello.ApplicaEffetto(spara, bersaglio,gioco);
        assertFalse(flag);
        assertEquals(3,bersaglio.getVita());
        assertEquals(bang,gioco.scarti.getLast());
    }
}