package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolcanicTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        Giocatore giocatore = new Giocatore();
        giocatore.tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        Volcanic volcanic = new Volcanic("0");
        volcanic.ApplicaEffetto(gioco,giocatore);
        assertEquals(volcanic,giocatore.tabellone.getArma());
        Volcanic volcanic1 = new Volcanic("1");
        volcanic1.ApplicaEffetto(gioco, giocatore);
        assertEquals(volcanic,gioco.scarti.getLast());
        assertEquals(volcanic1,giocatore.tabellone.getArma());
    }

    @Test
    void testToString() {
        Volcanic volcanic = new Volcanic("0");
        assertEquals(" Volcanic (Arma) 0 Distanza: 1 Con questa carta in gioco si può giocare un numero qualsiasi di carte BANG durante il proprio turno.",volcanic.toString());
    }
}