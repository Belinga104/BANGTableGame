package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhinchesterTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        Whinchester whinchester = new Whinchester("0");
        whinchester.ApplicaEffetto(gioco,giocatore);
        assertEquals(whinchester,giocatore.tabellone.getArma());
        Whinchester whinchester1 = new Whinchester("1");
        whinchester1.ApplicaEffetto(gioco, giocatore);
        assertEquals(whinchester,gioco.scarti.getLast());
        assertEquals(whinchester1,giocatore.tabellone.getArma());
    }
}