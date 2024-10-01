package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrigioneTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        Prigione prigione = new Prigione("0");
        prigione.ApplicaEffetto(gioco,giocatore);
        assertFalse(giocatore.isLock());
        giocatore.tabellone = new Tabellone(Ruolo.FUORILEGGE, Personaggio.BART_CASSIDY);
        prigione.ApplicaEffetto(gioco,giocatore);
        assertTrue(giocatore.isLock());
    }
}