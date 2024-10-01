package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RevCarabineTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        RevCarabine revCarabine = new RevCarabine("0");
        revCarabine.ApplicaEffetto(gioco,giocatore);
        assertEquals(revCarabine,giocatore.tabellone.getArma());
        RevCarabine RevCarabine1 = new RevCarabine("1");
        RevCarabine1.ApplicaEffetto(gioco, giocatore);
        assertEquals(revCarabine,gioco.scarti.getLast());
        assertEquals(RevCarabine1,giocatore.tabellone.getArma());
    }
}