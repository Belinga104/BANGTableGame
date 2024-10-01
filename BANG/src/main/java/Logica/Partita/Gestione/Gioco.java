package Logica.Partita.Gestione;

import Logica.Partita.Carte.Normali.Carta;
import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;

import java.io.Serializable;
import java.util.ArrayList;

public class Gioco implements Serializable {

    public Giocatore[] giocatori = new Giocatore[4];
    public ArrayList<Carta> scarti = new ArrayList<>();
    public ArrayList<Carta> mazzo = new ArrayList<>();
    public ArrayList<Ruolo> ruoli = new ArrayList<>();
    public ArrayList<Personaggio> personaggi = new ArrayList<>();
    public int turno = 0;

}
