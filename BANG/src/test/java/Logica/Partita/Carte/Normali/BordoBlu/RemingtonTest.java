package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemingtonTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        Remington remington = new Remington("0");
        remington.ApplicaEffetto(gioco,giocatore);
        assertEquals(remington,giocatore.tabellone.getArma());
        Remington remington1 = new Remington("1");
        remington1.ApplicaEffetto(gioco, giocatore);
        assertEquals(remington,gioco.scarti.getLast());
        assertEquals(remington1,giocatore.tabellone.getArma());
    }
}