package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchofieldTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        Schofield schofield = new Schofield("0");
        schofield.ApplicaEffetto(gioco,giocatore);
        assertEquals(schofield,giocatore.tabellone.getArma());
        Schofield schofield1 = new Schofield("1");
        schofield1.ApplicaEffetto(gioco, giocatore);
        assertEquals(schofield,gioco.scarti.getLast());
        assertEquals(schofield1,giocatore.tabellone.getArma());
    }
}