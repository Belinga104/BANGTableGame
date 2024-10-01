package Logica.Partita.Carte.Normali.BordoBlu;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import Logica.Partita.Gestione.Tabellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MustangTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        for (int i = 0; i < 4; i++) {
            gioco.giocatori[i].tabellone = new Tabellone(Ruolo.SCERIFFO, Personaggio.BART_CASSIDY);
        }
        Mustang mustang = new Mustang("0");
        mustang.ApplicaEffetto(gioco,gioco.giocatori[0]);
        assertEquals(mustang,gioco.giocatori[0].tabellone.carteT.getLast());
        for (int i = 1; i < 4; i++) {
            assertEquals(0, gioco.giocatori[i].tabellone.getArma().getDistanza());
        }
    }
}