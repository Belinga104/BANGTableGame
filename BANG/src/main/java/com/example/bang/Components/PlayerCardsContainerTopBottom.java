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

public class PlayerCardsContainerTopBottom extends VBox implements ImagesPath, BaseScene {

    //private VBox container;
    private Giocatore giocatore;
    private ImageView imgView1, imgView2, imgView3;
    private Ruolo ruolo;
    private Personaggio personaggio;
    private Armi arma;
    private Stage stage;
    private String nameArma;
    private int vita, TOPoBOT;
    private Button[] button = new Button[3];
    private HBox cardBox = new HBox(), bulletBox = new HBox();

    private double thisCardHeightRatio, thisCardWidthRatio;

    /**
     * HBox dei giocatori in Cima e in Fondo nel tabellone
     * @param giocatore Il giocatore di cui mostrare le carte
     * @param TOPoBOT se top uso le dimensioni delle carte del giocatore in basso, altrimenti uso quelle normali
     * @param stage Lo stage da cui ereditare le dimensioni delle carte
     */
    public PlayerCardsContainerTopBottom(Giocatore giocatore, int TOPoBOT, Stage stage){
        this.giocatore=giocatore;
        this.TOPoBOT=TOPoBOT;
        this.stage = stage;
        //container = new VBox(3);

        if(TOPoBOT ==0){
            thisCardHeightRatio = bottomCardHeightRatio;
            thisCardWidthRatio = bottomCardWidthRatio;
        }
        else{
            thisCardHeightRatio = cardHeightRatio;
            thisCardWidthRatio = cardWidthRatio;
        }

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

    /*public VBox getContainer(Giocatore giocatore){
        this.giocatore = giocatore;
    }*/

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
     * Funzione per settare le carte nel container di sinistra, corrispondente al ruolo del giocatore
     */
    public void setLeft(){

        ruolo = giocatore.tabellone.getRuolo();

        button[0] = new Button();

        if(TOPoBOT ==0){
            button[0].setGraphic(new GetCardsImages().getRuoloImage(ruolo, stage, TOPoBOT));

        }
        else if(ruolo == Ruolo.SCERIFFO){
            button[0].setGraphic(new GetCardsImages().getRuoloImage(ruolo, stage, TOPoBOT));
        }
        else{
            button[0].setGraphic(new GetCardsImages().getRetroCarta(stage, TOPoBOT, 0));

        }

    }

    /**
     * Funzione per settare le carte nel container centrale, corrispondente al personaggio del giocatore
     */
    public void setCenter(){

        personaggio = giocatore.tabellone.getPersonaggio();


        button[1] = new Button();

            button[1].setGraphic(new GetCardsImages().getPersonaggioImage(personaggio, stage, TOPoBOT));


    }
    /**
     * Funzione per settare le carte nel container di destra, corrispondente all'arma del giocatore
     */
    public void setRight(){

        arma = giocatore.tabellone.getArma();
        nameArma = arma.getSeme();

        button[2] = new Button();

            button[2].setGraphic(new GetCardsImages().getArmaImage(arma, stage, TOPoBOT));


    }

    /**
     * Funzione per ritornare il giocatore del box
     * @return Giocatore
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }


}
