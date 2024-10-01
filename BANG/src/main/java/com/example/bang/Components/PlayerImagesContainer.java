package com.example.bang.Components;

import Logica.Partita.Carte.Normali.Carta;
import Logica.Partita.Gestione.Giocatore;
import com.example.bang.Interfaces.BaseScene;
import com.example.bang.Interfaces.ImagesPath;
import com.example.bang.Scenes.TableScene;
import com.example.bang.controllers.CardsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlayerImagesContainer extends BorderPane implements ImagesPath, BaseScene {

    private ArrayList<Carta> carte = new ArrayList<>();
    private Carta tmpCarta;
    private Giocatore giocatore;
    private int index = 0;
    private Stage stage;
    private ImageView imgView1;
    private Button button = new Button();
    private TableScene tableScene;
    private HBox cardsContainer = new HBox();
    private Button closeButton = new Button();

    private boolean usaoDif;

    /**
     * Componente delle carte in possesso dal giocatore in quel momento
     * @param giocatore Il giocatore di cui stampare le carte
     * @param tableScene La Tablescene usata
     * @param usaoDif Se true, stampa tutte le carte, se false stampa le carte per difensersi
     */
    public PlayerImagesContainer(Giocatore giocatore, Stage stage, TableScene tableScene, boolean usaoDif) {
        this.giocatore = giocatore;
        this.index = index;
        this.carte = giocatore.carte;
        this.stage = stage;
        this.tableScene = tableScene;
        this.usaoDif=usaoDif;

        printCards();
    }

    public void printCards(){

        cardsContainer.setAlignment(Pos.CENTER);

        if(usaoDif){
            for(int i = 0; i < carte.size(); i++){
                tmpCarta = carte.get(i);
                button = new GetCardsImages().getPlayerCards(tmpCarta, stage);

                button.setOnAction(new CardsController(tableScene, giocatore, tmpCarta));
                cardsContainer.getChildren().add(button);


            }

            closeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    StackPane stackPane = tableScene.getStackPane();

                    stackPane.getChildren().remove(PlayerImagesContainer.this);
                    tableScene.setStackPane(stackPane);
                }
            });
            this.setRight(closeButton);

        }

        else{

            ArrayList<Carta> difesaC = tableScene.getControllore().difesa(giocatore);
            
            for(int i=0;i<difesaC.size();i++){
                tmpCarta = difesaC.get(i);
                button = new GetCardsImages().getPlayerCards(tmpCarta, stage);
                button.setOnAction(new CardsController(tableScene, giocatore, tmpCarta));
                cardsContainer.getChildren().add(button);

            }
        }


        this.setCenter(cardsContainer);
    }

}
