package com.example.bang.Components;

import Logica.Partita.Carte.Normali.BordoBlu.Armi;
import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import com.example.bang.Interfaces.BaseScene;
import com.example.bang.Interfaces.ImagesPath;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerCardsContainerLeftRight extends HBox implements ImagesPath, BaseScene {

    //private VBox container;
    private Giocatore giocatore;
    private ImageView imgView1, imgView2, imgView3;
    private Ruolo ruolo;
    private Personaggio personaggio;
    private Armi arma;
    private Stage stage;
    private String nameArma;

    private Button[] button = new Button[3];
    private int vita, TOPoBOT =1;
    private VBox cardBox = new VBox(), bulletBox = new VBox();


    /**
     * VBox dei giocatori posti a Sinistra e a Destra nel tabellone in quel momento
     * @param giocatore Il giocatore di cui mostrare le carte
     * @param stage Lo stage da cui ereditare le dimensioni delle carte
     */
    public PlayerCardsContainerLeftRight(Giocatore giocatore, Stage stage){
        this.giocatore=giocatore;
        this.stage = stage;

        setBullets();
        setLeft();
        setCenter();
        setRight();

        cardBox.getChildren().add(button[0]);
        cardBox.getChildren().add(button[1]);
        cardBox.getChildren().add(button[2]);

        this.getChildren().add(bulletBox);
        this.getChildren().add(cardBox);

    }

    public void setBullets(){
        vita= giocatore.getVita();

        for(int i=0;i<5;i++){
            if(i>=giocatore.getVita()){
                bulletBox.getChildren().add(new GetCardsImages().getBullets(stage, false, TOPoBOT));

            }
            else{
                bulletBox.getChildren().add(new GetCardsImages().getBullets(stage, true, TOPoBOT));

            }


        }
    }

    /**
     * Classe per settare le carte nel container di sinistra, corrispondente al ruolo del giocatore
     */
    public void setLeft(){

        ruolo = giocatore.tabellone.getRuolo();

        button[0] = new Button();

        if(ruolo == Ruolo.SCERIFFO) {
            button[0].setGraphic(new GetCardsImages().getRuoloImage(ruolo, stage, TOPoBOT));
        }
        else{
            button[0].setGraphic(new GetCardsImages().getRetroCarta(stage, TOPoBOT, 0));
        }
    }

    /**
     * Classe per settare le carte nel container centrale, corrispondente al personaggio del giocatore
     */
    public void setCenter(){

        personaggio = giocatore.tabellone.getPersonaggio();

        button[1] = new Button();
        button[1].setGraphic(new GetCardsImages().getPersonaggioImage(giocatore.tabellone.getPersonaggio(), stage,TOPoBOT));


    }
    /**
     * Classe per settare le carte nel container di destra, corrispondente all'arma del giocatore
     */
    public void setRight(){

        arma = giocatore.tabellone.getArma();

        button[2] = new Button();
        button[2].setGraphic(new GetCardsImages().getArmaImage(giocatore.tabellone.getArma(), stage,TOPoBOT));

    }

    public Giocatore getGiocatore() {
        return giocatore;
    }
}
