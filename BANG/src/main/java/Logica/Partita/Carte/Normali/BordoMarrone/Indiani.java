package Logica.Partita.Carte.Normali.BordoMarrone;

import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Gestione.Giocatore;
import Logica.Partita.Gestione.Gioco;

/**
 * Questa classe crea la carta Indiani per il gioco
 */
public class Indiani extends BordoMarrone{
    /**
     * Il costruttore della carta Indiani
     * @param seme il seme della carta
     */
    public Indiani(String seme) {
        super(seme);
    }

    /**
     *  Applica l'effetto della carta Indiani
     * @param gioco il gioco Bang
     * @param giocatore il giocatore che ha usato la carta
     */
    @Override
    public void ApplicaEffetto(Gioco gioco, Giocatore giocatore) {
        for (int i = 0; i < 4; i++) {
            if (!gioco.giocatori[i].equals(giocatore) && gioco.giocatori[i].getVita()>0) {
                boolean flag = false;
                for (int y = 0; y < gioco.giocatori[i].carte.size(); y++) {
                    if (gioco.giocatori[i].carte.get(y).getClass().getSimpleName().equals("Bang")) {
                        gioco.scarti.add(gioco.giocatori[i].carte.remove(y));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    gioco.giocatori[i].setVita(giocatore.getVita() - 1);
                }
            }
        }
    }

    /**
     * Ritorna la descrizione della carta Indiani
     * @return la descrizione della carta
     */
    public String toString() {
        return " " +  getClass().getSimpleName() + " " + getSeme() + " Ogni giocatore avversario deve scartare una carta BANG o perdere un punto vita.";
    }
}
