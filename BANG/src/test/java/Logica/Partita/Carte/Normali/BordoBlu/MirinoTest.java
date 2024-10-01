package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Normali.BordoMarrone.Bang;
import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MirinoTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        Mirino mirino = new Mirino("0");
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        mirino.ApplicaEffetto(gioco,giocatore);
        assertEquals(mirino,giocatore.tabellone.carteT.getLast());
        assertEquals(2,giocatore.tabellone.getArma().getDistanza());
    }
}