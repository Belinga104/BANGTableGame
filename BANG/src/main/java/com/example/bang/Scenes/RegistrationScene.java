package com.example.bang.Scenes;

import Logica.Controllore.Controllore;
import Logica.Partita.Gestione.Giocatore;
import com.example.bang.Components.WindowContainer;
import com.example.bang.Interfaces.BaseScene;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class RegistrationScene implements BaseScene {

    private Stage stage;
    private Scene scene;
    private StackPane pane = new StackPane();

    private BorderPane registrationPane = new BorderPane();
    private GridPane grid = new GridPane(2, 4);
    private VBox bottomPane = new VBox();

    private TextField[] nameTF = new TextField[4];
    private Label regLabel1, regLabel2 = new Label("Registrazione dei giocatori");

    private Button confirmButt = new Button("Conferma"), esciB = new Button("X");

    private String regString = "Inserisci il nome del giocatore ";


    private String[] namesArray = new String[4];
    private Controllore controllore;
    private WindowContainer windowContainer;

    /**
     * @param stage Stage
     * @param controllore Controllore
     */
    public RegistrationScene(Stage stage, Controllore controllore) {
        this.controllore = controllore;
        this.stage = stage;
        newScene();
    }

    public Giocatore giocatore;

    /**
     * Funzione che crea la nuova scena
     */
    public void newScene() {

        esciB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(windowContainer);
            }
        });
        windowContainer= new WindowContainer(stage);
        windowContainer.setTop(new Label("Almeno uno dei nomi non è stato inserito corretamente!"));
        windowContainer.setCenter(esciB);

        pane.setId("panel");
        regLabel2.setId("regLabel2");

        //registrationPane.setAlignment(Pos.CENTER);

        grid.setAlignment(Pos.CENTER);

        //topPane.setStyle("-fx-background-color:black");

        scene = new Scene(pane, minimumScreenWidth, minimumScreenHeight);
        scene.getStylesheets().add(getClass().getResource("/Css/RegistrationScene.css").toString());
        scene.getStylesheets().add(getClass().getResource("/Css/Shared.css").toString());


        stage.setScene(scene);
        stage.setFullScreen(true);

        //regLabel2.setId("regLabel2");

        //confirmButt.setMinSize(buttonWidth, buttonHeight);
        confirmButt.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 14.4));
        confirmButt.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8));

        regLabel2.setFont(new Font("Arial", 20));


        registrationPane.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4.8));

        //registrationBox.setPrefHeight();

        registrationPane.prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 3));
        registrationPane.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 5));

        bottomPane.setSpacing(100);
        bottomPane.getChildren().add(regLabel2);
        bottomPane.getChildren().add(grid);
        bottomPane.getChildren().add(confirmButt);
        registrationPane.setCenter(bottomPane);

        bottomPane.setAlignment(Pos.CENTER);
        confirmButt.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(confirmButt, Pos.CENTER);

        for (int i = 0; i < 4; i++) {

            nameTF[i] = new TextField();
            nameTF[i].prefHeightProperty().bind(Bindings.divide(stage.heightProperty(), 20));
            nameTF[i].prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 7));
            nameTF[i].setId("nameTF");
            grid.add(regLabel1 = new Label(regString + (i + 1)), 0, i);
            regLabel1.setId("regLabel1");
            grid.add(nameTF[i], 1, i);

                /*root.setMargin(nameTF, new Insets(100, 100, 100, 100));
                root.setMargin(regLabel1, new Insets(100, 100, 100, 100));*/

        }

        //root.getChildren().add(2, confirmButt);

        confirmButt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                boolean validName = true;

                for (int i = 0; i < 4; i++) {
                    //System.out.println(nameTF[i].getText());

                    if (nameTF[i].getText().trim().isEmpty()) {

                        validName = false;

                    } else if (!nameTF[i].getText().matches("^[a-zA-Z0-9]*$")) {

                        validName = false;
                    } else {
                        for (int j = 0; j < 4; j++) {
                            if (nameTF[i].getText().equals(namesArray[j])) {
                                validName = false;
                            }

                        }
                    }

                    if (validName) {
                        namesArray[i] = nameTF[i].getText();


                    }

                }

                if (!validName) {
                    namesArray = new String[4];
                    pane.getChildren().add(windowContainer);

                }

                //Se è tutto corretto
                else if (validName) {

                    for (int i = 0; i < 4; i++) {
                        controllore.getGiocatore(i);
                        controllore.getGiocatore(i).setNickname(namesArray[i]);
                    }
                    changeScene();


                }

            }
        });

        pane.getChildren().add(registrationPane);

    }

    /**
     * Funzione per cambiare la scena
     */
    public void changeScene(){
        new TableScene(stage, controllore);

    }

}
