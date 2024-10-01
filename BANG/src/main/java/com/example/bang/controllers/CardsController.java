package com.example.bang.controllers;

import Logica.Partita.Carte.Normali.Carta;
import Logica.Partita.Gestione.Giocatore;
import com.example.bang.Scenes.TableScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class CardsController implements EventHandler {

    private TableScene tableScene;
    private Button button;
    private Giocatore giocatore;
    private Carta carta;

    /**
     * Controller che gestisce le carte del giocatore che sta giocando in quel momento
     * @param tableScene La TableScene da usare
     * @param giocatore Giocatore di cui visualizzare le carte
     * @param carta La carta da utilizzare
     */
    public CardsController(TableScene tableScene, Giocatore giocatore, Carta carta){
        this.tableScene = tableScene;
        this.giocatore=giocatore;
        this.carta=carta;
    }

    @Override
    public void handle(Event event) {

        this.button =(Button) event.getSource();

        tableScene.useCard(carta, giocatore);

    }
}
