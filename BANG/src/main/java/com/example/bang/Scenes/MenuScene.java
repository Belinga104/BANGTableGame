package com.example.bang.Scenes;

import Logica.Controllore.Controllore;
import com.example.bang.Interfaces.BaseScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;


public class MenuScene extends Application implements BaseScene {


    /*
    Scena del menu. Quà viene startato javafx dal main.
     */
    private boolean sceltaP, sceltaI;
    private Scene scene;
    private Stage stage;

    private Button GUIB, CLIB, NuovaPB,CaricaPB, impostPB;

    private VBox root, nordP , buttFil;

    private GridPane buttonPane;
    ImageView logo;

    //private Media mainTheme ;
    //private MediaPlayer mediaPlayer;

    private Controllore controllore;

    //Variabili per lo start della GUI
    public static final CountDownLatch latch1 = new CountDownLatch(1), latchP = new CountDownLatch(1), latchI = new CountDownLatch(1);
    public static MenuScene menuSceneTemp = null;

    public MenuScene(){
        setMenuScene(this);
    }

    /**
     * Funzione per settare il controllore
     * @param controllore il controllore da utilizzare
     */
    public void setControllore(Controllore controllore){
        this.controllore = controllore;
    }

    /**
     * Funzione che aspetta il caricamento della scena
     */
    public static MenuScene waitMenuScene(){
        try {
            latch1.await();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        return menuSceneTemp;
    }

    /**
     * Funzione che setta la scena del menu, e abbassa il latch di 1
     */
    public static void setMenuScene(MenuScene menuScene0){
        menuSceneTemp = menuScene0;
        latch1.countDown();
    }

    /**
     * Funzione che aspetta l'azione del giocatore sui bottoni
     */
    public static MenuScene waitButtonActions(){
        try {
            latchP.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            latchI.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return menuSceneTemp;
    }

    @Override
    public void start(Stage stage) {

        /*FXMLLoader fxmlLoader = new FXMLLoader(MenuScene.class.getResource("MenuScene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());*/

        GUIB = new Button("Gioco con GUI" /*, buttonImage*/);
        CLIB = new Button("Gioco con CLI" /*,  buttonImage*/);
        NuovaPB = new Button("Nuova Partita");
        CaricaPB = new Button("Carica una partita");
        impostPB = new Button();

        nordP = new VBox();
        buttFil = new VBox();

        buttonPane  = new GridPane();

        logo = new ImageView(getClass().getResource("/Images/Graphics/bang-logo.png").toString());

        //mainTheme = new Media(new File("src/main/resources/Music/mainTheme.mp3").toURI().toString());

        //mediaPlayer = new MediaPlayer(mainTheme);

        stage.setMinWidth(minimumScreenWidth);
        stage.setMinHeight(minimumScreenHeight);
        stage.setFullScreen(true);

        //Per far partire la musica
        //mediaPlayer.setAutoPlay(false);

        root = new VBox();

        root.setId("panel");

        root.getChildren().add(nordP);

        root.getChildren().add(buttonPane);

        scene = new Scene(root, minimumScreenWidth, minimumScreenHeight);

        //Caricamento dei file CSS
        scene.getStylesheets().add(getClass().getResource("/Css/MenuScene.css").toString());
        scene.getStylesheets().add(getClass().getResource("/Css/Shared.css").toString());

        stage.setTitle("Bang!");

        //Set close operation
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setScene(scene);

        stage.getIcons().add(new Image(getClass().getResource("/Images/Graphics/bang-logo.png").toString()));

        /*buttonImage.setPreserveRatio(false);
        buttonImage.fitWidthProperty().bind();
        buttonImage.fitHeightProperty().bind(GUIB.heightProperty());*/

        //Button Pane che contiene i due bottoni
        buttonPane.setMinHeight((double) minimumScreenHeight /2);
        buttonPane.setPrefHeight((double) preferredScreenHeight /2);
        //buttonPane.setStyle("-fx-background-color: black ");

        buttonPane.add(NuovaPB, 0,0);

        //Per fare in modo che le dimensioni del bottone siano proporzionate a quelle della finestra in quel momento
        buttFil.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 14.4*2));
        buttFil.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8*2));

        /*buttFil.setPrefHeight(buttonHeight/2);
        buttFil.setMinHeight((buttonHeight/2)/aspectratio);*/

        buttonPane.add(buttFil,0,1);
        buttonPane.add(CaricaPB, 0,2);
        
        buttonPane.setAlignment(Pos.CENTER);

        /*NuovaPB.setPrefSize(buttonWidth, buttonHeight);
        NuovaPB.setMinSize(buttonWidth/aspectratio, buttonHeight/aspectratio);*/
        NuovaPB.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 14.4));
        NuovaPB.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8));

        GUIB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sceltaI = true;
                latchI.countDown();

                //new RegistrationScene(stage, controllore);

            }
        });

        //NuovaPB.setBackground(buttonImage);

        //CaricaPB.setPrefSize(buttonWidth, buttonHeight);
        CaricaPB.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 14.4));
        CaricaPB.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8));

        CLIB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                sceltaI = false;
                latchI.countDown();

            }
        });

        GUIB.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 14.4));
        GUIB.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8));

        NuovaPB.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                sceltaP = true;
                latchP.countDown();
                buttonPane.getChildren().removeAll(NuovaPB, CaricaPB);

                buttonPane.add(GUIB, 0,0);
                //buttonPane.add(buttFil, 0,1);
                buttonPane.add(CLIB, 0,2);
            }
        });

        CaricaPB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceltaP=false;
                latchP.countDown();
                buttonPane.getChildren().removeAll(NuovaPB, CaricaPB);

                buttonPane.add(GUIB, 0,0);
                //buttonPane.add(buttFil, 0,1);
                buttonPane.add(CLIB, 0,2);
            }
        });

        CLIB.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8));
        CLIB.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 14.4));

        CLIB.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.exit();
                sceltaI = false;
                latchI.countDown();

            }
        });
        //buttonPane.setStyle("-fx-background-color: #FFFF00")

        //NORD PANE
        nordP.setMinHeight(minimumScreenHeight /2);
        nordP.setPrefHeight(preferredScreenHeight/2);

        //Aggiungo il logo e lo imposto direttamente proporzionale alle dimensioni del contentitore
        nordP.getChildren().add(logo);
        nordP.setAlignment(Pos.CENTER);
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(nordP.widthProperty());
        logo.fitHeightProperty().bind(nordP.heightProperty());

        //nordP.setStyle("-fx-background-color: #000000");

        stage.show();
        this.stage=stage;

    }

    /**
     * Funzione che controlla se il giocatore ha premuto su Nuova Partita o Carica Partita
     * Se true, Nuova Partita, se false Carica Partita
     */
    public boolean getSceltaP(){
        /*if(!pFlag){
            try {
                wait();

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }*/

        return sceltaP;
    }

    /**
     * Funzione che controlla se il giocatore ha premuto su GUI o CLI
     * Se true GUI, se false CLI
     */
    public boolean getSceltaI(){
        return sceltaI;
    }

    public void changeScene() {
        /*Node source = (Node)actionEvent.getSource();
        stage = (Stage) source.getScene().getWindow();

         */

        if (getSceltaP()) {


        new RegistrationScene(stage, controllore);
        }
        else{
            new TableScene(stage, controllore);
        }

    }

    public Stage getStage(){
        return stage;
    }

    /*public static void main(String[] args) {
        launch();
    }*/


}