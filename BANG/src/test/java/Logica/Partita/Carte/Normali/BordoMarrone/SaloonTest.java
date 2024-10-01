package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaloonTest {

    @Test
    void applicaEffetto() {
        Gioco gioco = new Gioco();
        for (int i = 0; i < 4; i++) {
            gioco.giocatori[i] = new Giocatore();
            gioco.giocatori[i].setVita(4);
        }
        new Saloon("0").ApplicaEffetto(gioco,gioco.giocatori[0]);
        for (int i = 0; i < 4; i++) {
            assertEquals(5,gioco.giocatori[i].getVita());
        }
    }
}