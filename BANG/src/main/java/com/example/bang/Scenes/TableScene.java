package com.example.bang.Scenes;

import Logica.Controllore.Controllore;
import Logica.Partita.Carte.Normali.Carta;
import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import Logica.Partita.Gestione.Giocatore;
import com.example.bang.Components.*;
import com.example.bang.Interfaces.BaseScene;
import com.example.bang.Interfaces.ImagesPath;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TableScene implements BaseScene, ImagesPath {

    private Label pedroLabel = new Label("Da quale mazzo vuoi pescare?"), mazzo1Lab = new Label("Mazzo normale"), mazzo2Lab = new Label("Mazzo scarti");
    private Button usaCartaB  = new Button("Usa una carta") , saltaTurnoB = new Button("Salta il turno");

    private int turno, emp=0;

    private boolean flagCarte = false, primoTurno =true, caricaP = true, difesaTF = false, flagEmporio=false;

    private boolean[] flagM = new boolean[4];
    private Stage stage;
    private Scene scene;

    private BorderPane root = new BorderPane(), centerPane = new BorderPane(), center_center = new BorderPane();
    private VBox center_left = new VBox(), center_right = new VBox();
    private HBox center_top = new HBox(), center_bottom = new HBox(), centerButtonsHB = new HBox();
    private PlayerImagesContainer playerCardsContainer;

    private PlayerCardsContainerLeftRight[] cardsContainerLeftRight = new PlayerCardsContainerLeftRight[2];
    private PlayerCardsContainerTopBottom[] cardsContainerTopBottom = new PlayerCardsContainerTopBottom[2];

    private Controllore controllore;

    private Giocatore giocatoreTemp, mostraG, giocatoreAtt;
    private StackPane stackPane = new StackPane();

    private StatsContainer statsContainer;

    public Ruolo ruolo;

    /**
     * @param stage       Stage
     * @param controllore Controllore
     */
    public TableScene(Stage stage, Controllore controllore) {
        this.controllore = controllore;
        this.stage = stage;
        startScene();
    }

    /**
     * Funzione per creare la scena
     */
    private void startScene() {

        stage.setMinWidth(minimumScreenWidth);
        stage.setMinHeight(minimumScreenHeight);

        scene = new Scene(stackPane, minimumScreenWidth, minimumScreenHeight);
        scene.getStylesheets().add(getClass().getResource("/Css/TableScene.css").toString());
        scene.getStylesheets().add(getClass().getResource("/Css/Shared.css").toString());

        stage.setScene(scene);
        stage.setFullScreen(true);

        stackPane.getChildren().add(root);

        root.setId("panel");

        root.setCenter(centerPane);

        root.maxHeightProperty().bind(stage.heightProperty());
        root.maxWidthProperty().bind(stage.widthProperty());

        centerPane.setId("centerPane");

        centerPane.setCenter(center_center);
        centerPane.setLeft(center_left);
        centerPane.setRight(center_right);
        centerPane.setTop(center_top);
        centerPane.setBottom(center_bottom);
        centerPane.maxHeightProperty().bind(Bindings.divide(stage.heightProperty(), 1.5));
        centerPane.maxWidthProperty().bind(Bindings.divide(stage.widthProperty(), 1.5));

        center_bottom.setAlignment(Pos.BOTTOM_CENTER);

        center_top.setAlignment(Pos.TOP_CENTER);

        center_left.setAlignment(Pos.CENTER_LEFT);

        center_right.setAlignment(Pos.CENTER_RIGHT);

        startGame();

    }

    /**
     * Funzione per startare il gioco e settare i giocatori con le carte nelle posizioni iniziali
     */
    private void startGame() {

        try{
            controllore.getGiocatore(0).tabellone.getRuolo();
            primoTurno=false;
            updateContainers(3);

        }
        catch (NullPointerException e){
            for (int i = 0; i < 4; i++) {

                controllore.setPR(i);
                flagM[i] = false;
            }
        }
        nextTurn();
    }

    /**
     * Funzione per fare il prossimo turno
     */
    private void nextTurn() {

        turno = controllore.getTurno();

        giocatoreTemp = controllore.getGiocatore(turno);

        if (giocatoreTemp.getVita() > 0) {

            if (caricaP) {

                statsContainer = new StatsContainer(controllore);
                statsContainer.setId("statsContainer");

                statsContainer.minHeightProperty().bind(Bindings.divide(stage.heightProperty(), 2));
                statsContainer.maxHeightProperty().bind(Bindings.divide(stage.heightProperty(), 1.5));
                statsContainer.minWidthProperty().bind(Bindings.divide(stage.widthProperty(), 4));

                root.setRight(statsContainer);

                center_center.setBottom(centerButtonsHB);

                if(caricaP){
                    caricaP = false;
                    updateContainers(3);
                }
                else if(primoTurno){
                    primoTurno = false;
                    caricaP = false;
                    updateContainers(0);
                }

                centerButtonsHB.setAlignment(Pos.CENTER);
                centerButtonsHB.getChildren().add(usaCartaB);
                centerButtonsHB.getChildren().add(saltaTurnoB);

                writeTurno(controllore.getGiocatore(turno));

            }
            else {

                try {
                    stackPane.getChildren().remove(1);

                } catch (Exception e) {

                }

                writeTurno(giocatoreTemp);
                root.getChildren().remove(playerCardsContainer);

                updateContainers(1);
            }


            saltaTurnoB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                controllore.getTurno();

                BorderPane skipTurnConfPane = new BorderPane();

                HBox buttonBox = new HBox();
                Label skipTurn = new Label("Sei sicuro di voler saltare il turno? Scarterai le carte in eccesso!");

                Button buttonConferma = new Button("Conferma"), buttonAnnulla = new Button("Annulla");

                buttonBox.getChildren().add(buttonConferma);
                buttonBox.getChildren().add(buttonAnnulla);
                buttonBox.setAlignment(Pos.CENTER);

                skipTurnConfPane.setId("skipTurnPane");

                skipTurnConfPane.setTop(skipTurn);
                skipTurnConfPane.setCenter(buttonBox);

                skipTurnConfPane.maxHeightProperty().bind(Bindings.divide(stage.heightProperty(), getConfirmationPaneHeight));
                skipTurnConfPane.maxWidthProperty().bind(Bindings.divide(stage.widthProperty(), confirmationPaneWidth));

                stackPane.getChildren().add(skipTurnConfPane);

                buttonConferma.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        stackPane.getChildren().remove(skipTurnConfPane);
                        controllore.scarta(controllore.getGiocatore(turno));
                        //giocatoreTemp.setLock(false);
                        controllore.incrementaTurno();
                        controllore.saveGame();
                        giocatoreTemp.setLock(false);

                        nextTurn();

                    }
                });

                buttonAnnulla.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        stackPane.getChildren().remove(skipTurnConfPane);

                    }
                });

            }
            });

            usaCartaB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setPlayerCards(giocatoreTemp);
                    playerCardsContainer.setId("playerCardsContainer");

                }
            });


            if (emp != 0) {

            WindowContainer container = new WindowContainer(this.getStage());

            Button esciB = new Button("X");
            esciB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stackPane.getChildren().remove(container);
                }
            });

            HBox box = new HBox();
            HBox cardBox = new HBox();

            box.setAlignment(Pos.CENTER);

            box.getChildren().add(new Label("Il giocatore " + mostraG.getNickname() + " mostra le seguenti carte"));

            for (int j = 0; j < 3; j++) {

                try {
                    cardBox.getChildren().add(new GetCardsImages().getPlayerCards(mostraG.carte.get(j), stage));

                } catch (Exception e) {

                }
            }

            container.setTop(box);
            container.setCenter(cardBox);
            container.setBottom(esciB);

            stackPane.getChildren().add(container);

            if(emp==1){
                flagEmporio=false;
            }
            emp--;

        }

            if (giocatoreTemp.isLock() && giocatoreTemp.getVita() > 0) {

            Label prigione1 = new Label("Sei in prigione!");
            Label prigione2 = new Label("Se pescherai una carta Cuore, sarai fuori di prigione.");

            VBox labelBox = new VBox();

            Button estraiCarta = new Button("Estrai");
            Button esciB = new Button("Ok");

            WindowContainer container = new WindowContainer(this.getStage());

            labelBox.setAlignment(Pos.CENTER);
            labelBox.getChildren().add(prigione1);
            labelBox.getChildren().add(prigione2);

            container.setTop(labelBox);
            container.setCenter(estraiCarta);

            estraiCarta.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Carta prigTemp1 = controllore.estrai();
                    Carta prigTemp2 = controllore.estrai();

                    boolean fuoriP = false;

                    if (giocatoreTemp.tabellone.getPersonaggio().equals(Personaggio.LUCKY_DUKE)) {

                        if (prigTemp1.getSeme().contains("C") || prigTemp2.getSeme().contains("C")) {
                            giocatoreTemp.setLock(false);
                            fuoriP = true;
                        }

                    } else {
                        if (prigTemp1.getSeme().contains("C")) {
                            giocatoreTemp.setLock(false);
                            fuoriP = true;
                        }
                    }

                    container.getChildren().clear();

                    Label label;
                    if (fuoriP) {
                        label = new Label("Sei fuori di prigione \nContinua il turno normalmente");
                        esciB.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                stackPane.getChildren().remove(container);

                            }
                        });
                    } else {
                        label = new Label("Sei ancora in prigione \nSalterai il turno");

                        esciB.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {

                                stackPane.getChildren().remove(container);
                                controllore.incrementaTurno();
                                giocatoreTemp.setLock(false);
                                nextTurn();
                            }
                        });
                    }
                    container.setTop(label);

                    container.setCenter(esciB);

                }
            });

            stackPane.getChildren().add(container);

        }

            if (!giocatoreTemp.isLock()) {
                if (giocatoreTemp.tabellone.getPersonaggio().equals(Personaggio.PEDRO_RAMIREZ)) {
                    pescaPedro(giocatoreTemp);
                } else if (giocatoreTemp.tabellone.getPersonaggio().equals(Personaggio.JESSE_JONES)) {
                    pescaJesse(giocatoreTemp);
                } else if (giocatoreTemp.tabellone.getPersonaggio().equals(Personaggio.KIT_CARLSON)) {
                    pescaKit(giocatoreTemp);

                }

                else {
                pescaCarta(giocatoreTemp, 2);
                }

            }

        }
        else{
            controllore.incrementaTurno();

            nextTurn();
        }

    }

    /**
     * Funzione per settare le carte che il giocatore ha in quel momento
     * @param giocatore Giocatore a cui settare le carte
     */

    private void setPlayerCards(Giocatore giocatore){

        if(giocatore.tabellone.getPersonaggio()== Personaggio.SUZY_LAFAYETTE && giocatore.carte.isEmpty()){
            pescaCarta(giocatore, 1);
        }

        try{
            root.getChildren().remove(playerCardsContainer);
        }
        catch (Exception e){

        }
        playerCardsContainer = new PlayerImagesContainer(giocatore, stage, this, true);
        playerCardsContainer.setId("playerCardsContainer");
        playerCardsContainer.maxHeightProperty().bind(Bindings.divide(stage.heightProperty(), cardHeightRatio));
        playerCardsContainer.maxWidthProperty().bind(stage.widthProperty());

        root.setBottom(playerCardsContainer);
    }

    /**
     * Funzione che setta le carte del giocatore in basso nel tabellone  (Quello che sta giocando in quel momento)
     */

    private void setBottomCards(Giocatore giocatore) {

        cardsContainerTopBottom[0] = new PlayerCardsContainerTopBottom(giocatore, 0, stage);

        if (flagCarte) {
            center_bottom.getChildren().set(0, cardsContainerTopBottom[0]);

        } else {
            center_bottom.getChildren().add(cardsContainerTopBottom[0]);

        }

    }

    /**
     * Funzione che setta le carte del giocatore a sinistra
     */
    private void setLeftCards(Giocatore giocatore) {
        cardsContainerLeftRight[0] = new PlayerCardsContainerLeftRight(giocatore, stage);

        if (flagCarte) {
            center_left.getChildren().set(0, cardsContainerLeftRight[0]);

        } else {
            center_left.getChildren().add(cardsContainerLeftRight[0]);

        }
    }

    /**
     * Funzione che setta le carte del giocatore in cima
     */
    private void setTopCards(Giocatore giocatore) {
        cardsContainerTopBottom[1] = new PlayerCardsContainerTopBottom(giocatore, 1, stage);

        if (flagCarte) {
            center_top.getChildren().set(0, cardsContainerTopBottom[1]);

        } else {
            center_top.getChildren().add(cardsContainerTopBottom[1]);

        }

    }

    /**
     * Funzione che setta le carte del giocatore a destra
     */
    private void setRightCards(Giocatore giocatore) {
        cardsContainerLeftRight[1] = new PlayerCardsContainerLeftRight(giocatore, stage);

        if (flagCarte) {
            center_right.getChildren().set(0, cardsContainerLeftRight[1]);

        } else {
            center_right.getChildren().add(cardsContainerLeftRight[1]);
            flagCarte = true;
        }

    }

    /**
     * Funzione che setta le carte al centro del tabellone (Quelle del mazzo principale e degli scarti)
     */
    private void setCenterCards() {

        HBox centerCardsContainer = new HBox();
        centerCardsContainer.setAlignment(Pos.CENTER);

        centerCardsContainer.getChildren().add(new GetCardsImages().getRetroCarta(stage, 0, 3));

        if(controllore.ritornaScarti()!=null){
            try{
                centerCardsContainer.getChildren().add(new GetCardsImages().getPlayerCards(controllore.ritornaScarti().getLast(), stage));

            }
            catch (NoSuchElementException e){

            }
        }

        center_center.setCenter(centerCardsContainer);
    }

    /**
     * Funzione per aggiornare i container delle carte dei giocatori
     * @param flag se 0 = primo turno, quindi setto le carte nelle posizioni iniziali. Se 1, tutti i turni successivi al primo, quindi sposto i giocatori in senso orario.
     * Se 2, aggiorno semplicemente i punti vita, se 3 li setto in base al file .ser.
     */
    private void updateContainers(int flag){

        if(flag==0){
            //Bottom
            setBottomCards(controllore.getGiocatore(0));

            //Top
            setTopCards(controllore.getGiocatore(2));

            //Left
            setLeftCards(controllore.getGiocatore(3));

            //Right
            setRightCards(controllore.getGiocatore(1));
        }
        else if(flag==1){


            PlayerCardsContainerTopBottom tempTopBottom0 = cardsContainerTopBottom[0];
            PlayerCardsContainerTopBottom tempTopBottom1 = cardsContainerTopBottom[1];
            PlayerCardsContainerLeftRight tempLeftRight0= cardsContainerLeftRight[0];
            PlayerCardsContainerLeftRight tempLeftRight1 = cardsContainerLeftRight[1];


            if(cardsContainerLeftRight[1].getGiocatore().getVita()<1){
                if(cardsContainerTopBottom[0].getGiocatore().getVita()<1){
                    setBottomCards(tempLeftRight0.getGiocatore());
                    setLeftCards(tempTopBottom0.getGiocatore());
                    setTopCards(tempTopBottom1.getGiocatore());
                    setRightCards(tempTopBottom1.getGiocatore());

                }
                else{
                    setBottomCards(tempTopBottom1.getGiocatore());
                    setLeftCards(cardsContainerLeftRight[1].getGiocatore());
                    setTopCards(tempTopBottom0.getGiocatore());
                    setRightCards(tempLeftRight0.getGiocatore());
                }


            }
            else{
                setBottomCards(cardsContainerLeftRight[1].getGiocatore());
                setRightCards(tempTopBottom1.getGiocatore());
                setLeftCards(tempTopBottom0.getGiocatore());
                setTopCards(tempLeftRight0.getGiocatore());

            }

        }

        else if(flag==2){

            setBottomCards(cardsContainerTopBottom[0].getGiocatore());
            setTopCards(cardsContainerTopBottom[1].getGiocatore());
            setLeftCards(cardsContainerLeftRight[0].getGiocatore());
            setRightCards(cardsContainerLeftRight[1].getGiocatore());
            setPlayerCards(cardsContainerTopBottom[0].getGiocatore());

        }

        else if(flag==3){

            int turno = controllore.getTurno();
            if(turno==0){
                    setBottomCards(controllore.getGiocatore(turno));

                    //Top
                    setTopCards(controllore.getGiocatore(turno+2));

                    //Left
                    setLeftCards(controllore.getGiocatore(turno+3));

                    setRightCards(controllore.getGiocatore(turno+1));

            }
            else if(turno==1){
                setBottomCards(controllore.getGiocatore(turno));

                //Top
                setTopCards(controllore.getGiocatore(turno+2));

                //Left
                setLeftCards(controllore.getGiocatore(turno-1));

                setRightCards(controllore.getGiocatore(turno+1));

            }
            else if(turno==2){

                setBottomCards(controllore.getGiocatore(turno));

                //Top
                setTopCards(controllore.getGiocatore(turno-2));

                //Left
                setLeftCards(controllore.getGiocatore(turno-1));

                setRightCards(controllore.getGiocatore(turno+1));

            }
            else if(turno ==3){
                setBottomCards(controllore.getGiocatore(turno));

                //Top
                setTopCards(controllore.getGiocatore(turno-2));

                //Left
                setLeftCards(controllore.getGiocatore(turno-1));

                //Right
                setRightCards(controllore.getGiocatore(turno-3));

            }

        }
        setCenterCards();

    }

    private void checkMorte(){

        WindowContainer container2 = new WindowContainer(this.getStage());
        Button escib = new Button("Ok");
        int vittoria=-1;

        boolean writePane=true;

        escib.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().remove(container2);
            }
        });


        for(int i=0;i<4;i++){
            if(controllore.getGiocatore(i).getVita()<1 && flagM[i]==false){

                if(writePane){
                    stackPane.getChildren().add(container2);
                    writePane=false;
                }
                flagM[i]=true;
                container2.setTop(new Label("Il giocatore " + controllore.getGiocatore(i).getNickname() + " è morto!"));

                for(int j=0;j<4;j++){
                    if(controllore.getGiocatore(j).tabellone.getPersonaggio() == Personaggio.VULTURE_SAM && controllore.getGiocatore(j)!=controllore.getGiocatore(i)){
                        container2.setCenter(new Label("Il giocatore " + controllore.getGiocatore(j).getNickname() + " si prende le sue carte!"));
                        controllore.vultureSam(j);
                        container2.setBottom(escib);
                    }
                }
            }

        }

        for(int i=0;i<4;i++){

            if(i!=turno && !flagM[i]){
                vittoria++;
            }

        }

        if(vittoria==3){

            Button nuovaP = new Button();
            nuovaP.setOnAction(new EventHandler<ActionEvent>() {


                @Override
                public void handle(ActionEvent actionEvent) {
                    new Controllore();

                }
            });
            escib.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                    System.exit(0);
                }
            });

            container2.getChildren().clear();
            container2.minHeightProperty().bind(Bindings.divide(this.getStage().heightProperty(), getConfirmationPaneHeight));
            container2.minWidthProperty().bind(Bindings.divide(this.getStage().widthProperty(), confirmationPaneWidth));
            container2.setTop(new Label("Vittoria del giocatore " + controllore.getGiocatore(turno)));
            container2.setCenter(escib);


        }

    }

    /**
     * Funzione per scrivere di chi sia il turno di gioco
     * @param giocatore
     */

    private void writeTurno(Giocatore giocatore) {

        Label turnoLbl = new Label("Turno del giocatore " + giocatore.getNickname());

        turnoLbl.setFont(Font.font("Arial", 20));

        stackPane.getChildren().add(turnoLbl);

    }

    /**
     * Funzione per stampare i pannelli di utilizzo delle carte
     * @param carta La carta usata dal giocatore
     * @param giocatore Giocatore che sta usando la carta
     */

    public void useCard(Carta carta, Giocatore giocatore){

        WindowContainer cardContainer = new WindowContainer(this.getStage());
        WindowContainer container = new WindowContainer(this.getStage());

        HBox nameBox = new HBox();
        HBox topBox = new HBox();
        nameBox.setAlignment(Pos.CENTER);

        Button esciB = new Button("X");

        esciB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try{
                    stackPane.getChildren().remove(3);
                }
                catch (Exception e){
                    stackPane.getChildren().remove(cardContainer);

                }
                try {
                    stackPane.getChildren().remove(2);
                }
                catch (Exception e){

                }


                checkMorte();
                statsContainer.updateList();

                updateContainers(2);

            }

        });

        switch (carta.getClass().getSimpleName()){

            case "Bang": {

                Label titoloBang = new Label("A quale giocatore vuoi sparare?");
                titoloBang.setAlignment(Pos.CENTER);

                ArrayList<Giocatore> giocatori = controllore.distanzaGiocatori(turno);

                for (int i = 0; i < giocatori.size(); i++) {

                    if (giocatori.get(i).getVita() > 0){

                        Button button = new Button();
                        button.setText(giocatori.get(i).getNickname());

                    nameBox.getChildren().add(button);

                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            String name = button.getText();

                            for (int j = 0; j < giocatori.size(); j++) {
                                if (giocatori.get(j).getNickname() == name) {
                                    Giocatore giocatore1 = giocatori.get(j);
                                    if (controllore.usacarta(giocatore, giocatore1, carta, true)) {
                                        setPlayerCards(giocatore);

                                        giocatoreAtt = giocatore;


                                        if(giocatore1.tabellone.getPersonaggio() == Personaggio.JOURDONNAIS){
                                            controllore.Jourdonnais(giocatore1);
                                        }
                                        else if (controllore.difesa(giocatore1) != null) {

                                            root.getChildren().remove(playerCardsContainer);

                                            cardContainer.getChildren().clear();
                                            nameBox.getChildren().clear();
                                            topBox.getChildren().clear();

                                            Label difesaBang = new Label(giocatore1.getNickname() + ", scegli la carta con cui difenderti");

                                            Button mostraCarte = new Button("Mostra carte");
                                            Button nonDif = new Button("Non difenderti");

                                            topBox.getChildren().add(difesaBang);

                                            cardContainer.setTop(topBox);
                                            cardContainer.setCenter(mostraCarte);

                                            mostraCarte.setOnAction(new EventHandler<ActionEvent>() {

                                                @Override
                                                public void handle(ActionEvent actionEvent) {

                                                    nameBox.getChildren().add(new PlayerImagesContainer(giocatore1, stage, TableScene.this, false));
                                                    difesaTF = true;

                                                    cardContainer.setCenter(nameBox);
                                                    cardContainer.setBottom(nonDif);

                                                }
                                            });

                                            nonDif.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent actionEvent) {
                                                    difesaTF = false;
                                                    cardContainer.getChildren().clear();
                                                    topBox.getChildren().clear();

                                                    cardContainer.setTop(new Label("Il giocatore " + giocatore1.getNickname() + " non si è difeso!"));

                                                    giocatore1.setVita(giocatore1.getVita() - 1);

                                                    Label stampaVita = new Label("Il giocatore " + giocatore1.getNickname() + " ha perso un punto vita");

                                                    if (giocatore1.tabellone.getPersonaggio() == Personaggio.BART_CASSIDY) {
                                                        pescaBart(giocatore1);
                                                    } else if (giocatore1.tabellone.getPersonaggio() == Personaggio.EL_GRINGO) {
                                                        pescaGringo(giocatore1, giocatoreAtt);
                                                    }

                                                    topBox.getChildren().add(stampaVita);

                                                    cardContainer.setTop(topBox);
                                                    cardContainer.setCenter(esciB);

                                                }
                                            });

                                        } else {
                                            cardContainer.getChildren().clear();
                                            topBox.getChildren().clear();


                                            Label nodifesa1 = new Label("Il giocatore " + giocatore1.getNickname() + " non ha alcuna carta per difendersi!");

                                            cardContainer.setTop(nodifesa1);

                                            giocatore1.setVita(giocatore1.getVita() - 1);

                                            Label stampaVita = new Label("Il giocatore " + giocatore1.getNickname() + " ha perso un punto vita");

                                            if (giocatore1.tabellone.getPersonaggio() == Personaggio.BART_CASSIDY) {
                                                pescaBart(giocatore1);
                                            } else if (giocatore1.tabellone.getPersonaggio() == Personaggio.EL_GRINGO) {
                                                giocatoreAtt = giocatore;
                                                pescaGringo(giocatore1, giocatoreAtt);
                                            }

                                            topBox.getChildren().add(stampaVita);

                                            cardContainer.setTop(topBox);
                                            cardContainer.setCenter(esciB);

                                        }
                                    } else {
                                        cardContainer.setTop(new Label("Hai già giocato un Bang in questo turno!"));
                                        cardContainer.setCenter(esciB);
                                    }
                                }


                            }
                        }
                    });
                }
                }

                topBox.getChildren().add(titoloBang);
                topBox.getChildren().add(esciB);
                cardContainer.setTop(topBox);
                cardContainer.setCenter(nameBox);


                stackPane.getChildren().add(cardContainer);

            }
            break;

            case "Barile" : {
                container = new WindowContainer(this.getStage());


                if(difesaTF){
                    if(controllore.controllaBarile()){
                        container.setTop(new Label("Hai mancato il giocatore " + giocatore.getNickname()));

                    }
                    else{
                        container.setTop(new Label("Non puoi usare il barile!\nIl giocatore " + giocatore.getNickname() + " ha perso un punto vita!"));

                        giocatore.setVita(giocatore.getVita() - 1);


                        if(giocatore.tabellone.getPersonaggio() == Personaggio.BART_CASSIDY){
                            pescaBart(giocatore);
                        }
                        else if(giocatore.tabellone.getPersonaggio() == Personaggio.EL_GRINGO){
                            pescaGringo(giocatore, giocatoreAtt);
                        }

                    }
                    difesaTF=false;

                }
                else {
                    container.setTop(new Label("Puoi usare questa carta solo per difenderti!"));
                }

                container.setCenter(esciB);

                stackPane.getChildren().add(container);

                break;
            }

            case "Birra": {

                container = new WindowContainer(this.getStage());
                if(difesaTF){
                    controllore.scarta(giocatore, carta);
                    //if(controllore.controllaBarile()){

                    Label label = new Label("Il giocatore " + giocatore.getNickname() + " ha usato una birra, quindi la sua vita resta invariata");
                    container.setTop(label);
                    container.setCenter(esciB);

                    difesaTF=false;
                }
                else if(giocatore.getVita()==5){
                    container.setTop(new Label("Non puoi usare questa carta se non hai perso punti vita!"));
                    container.setCenter(esciB);
                }

                else{
                    controllore.usacarta(giocatore, carta);
                    container.setTop(new Label("Hai recuperato un punto vita!"));
                    container.setCenter(esciB);

                }

                stackPane.getChildren().add(container);

            }
            break;

            case "Catbalou": {

                Label catbalou1 = new Label("Scegli il giocatore a cui far scartare una carta");

                for(int i=0;i<4;i++){

                    if(controllore.getGiocatore(i)!=giocatore && controllore.getGiocatore(i).getVita()>0){

                        Giocatore giocatoreD = controllore.getGiocatore(i);
                        cardContainer.setTop(catbalou1);
                        cardContainer.setCenter(nameBox);
                        Button button = new Button();
                        button.setText(giocatoreD.getNickname());

                        nameBox.getChildren().add(button);

                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Giocatore giocatoreRuba=null;

                                for(int j=0;j<controllore.getVivo();j++) {
                                    if (controllore.getGiocatore(j).getNickname() == button.getText()) {
                                        giocatoreRuba = controllore.getGiocatore(j);
                                        j = 10;
                                    }
                                }

                                controllore.usacartaPC(giocatore,giocatoreRuba, carta);

                                cardContainer.getChildren().clear();

                                Label label = new Label("Hai fatto scartare una carta a " + giocatoreRuba.getNickname());
                                cardContainer.setTop(label);
                                cardContainer.setCenter(esciB);

                            }
                        });


                    }

                }
                stackPane.getChildren().add(cardContainer);


            }
            break;

            case "Diligenza": {

                HBox cardBox = new HBox();
                cardBox.setAlignment(Pos.CENTER);

                controllore.usacarta(giocatore, carta);

                int carteSize =giocatore.carte.size();

                cardBox.getChildren().add(new GetCardsImages().getPlayerCards(giocatore.carte.get(carteSize-2), stage));
                cardBox.getChildren().add(new GetCardsImages().getPlayerCards(giocatore.carte.get(carteSize-1), stage));


                cardContainer.setTop(new Label("Hai pescato 2 nuove carte"));
                cardContainer.setCenter(cardBox);
                cardContainer.setBottom(esciB);

                stackPane.getChildren().add(cardContainer);

            }
            break;

            case "Duello": {

                Label duelloL = new Label("Chi vuoi duellare?"), sconfittaL = new Label("Hai perso un punto vita!"), vittoriaL1 = new Label("Hai vinto il duello!");
                duelloL.setAlignment(Pos.CENTER);
                for(int i=0;i<4;i++){
                    if(controllore.getGiocatore(i)!=giocatore && controllore.getGiocatore(i).getVita()>0){

                        Giocatore giocatoreD = controllore.getGiocatore(i);

                        cardContainer.setTop(duelloL);
                        cardContainer.setCenter(nameBox);
                        Button button = new Button();
                        button.setText(giocatoreD.getNickname());

                        nameBox.getChildren().add(button);

                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {

                                Giocatore giocatoreD = null;
                                int indexG=-1;

                                for(int j=0;j<controllore.getVivo();j++) {
                                    if (controllore.getGiocatore(j).getNickname() == button.getText()) {
                                        giocatoreD = controllore.getGiocatore(j);
                                        j = 10;
                                    }
                                }

                                cardContainer.getChildren().clear();

                                //SE PERDE
                                if(controllore.usacarta(giocatore, giocatoreD, carta, false)){
                                    cardContainer.setTop(sconfittaL);
                                    cardContainer.setCenter(esciB);

                                    if(giocatore.tabellone.getPersonaggio() == Personaggio.BART_CASSIDY){
                                        pescaBart(giocatore);
                                    }
                                    else if(giocatore.tabellone.getPersonaggio() == Personaggio.EL_GRINGO){
                                        pescaGringo(giocatoreAtt, giocatoreD);
                                    }
                                }

                                //SE VINCE
                                else{
                                    HBox hbox = new HBox();
                                    hbox.getChildren().add(vittoriaL1);
                                    hbox.getChildren().add(new Label("Il giocatore " + giocatoreD.getNickname() + " ha perso un punto vita!"));

                                    if(giocatoreD.tabellone.getPersonaggio() == Personaggio.BART_CASSIDY){
                                        pescaBart(giocatoreD);
                                    }
                                    else if(giocatore.tabellone.getPersonaggio() == Personaggio.EL_GRINGO){
                                        pescaGringo(giocatoreD, giocatore);
                                    }

                                    hbox.setAlignment(Pos.CENTER);

                                    cardContainer.setTop(hbox);

                                    cardContainer.setCenter(esciB);

                                }

                            }
                        });


                    }

                }
                stackPane.getChildren().add(cardContainer);
            }
            break;

            case "Emporio": {

                cardContainer.getChildren().clear();

                if(flagEmporio){
                    cardContainer.setTop(new Label("Non possono essere usati 2 empori contemporaneamente!"));
                    cardContainer.setCenter(esciB);
                }
                else {

                    controllore.usacarta(giocatore, carta);

                    cardContainer.setTop(new Label("Dovrai mostrare 3 o meno carte agli altri giocatori\nHai pescato questa carta:"));

                    cardContainer.setCenter(new GetCardsImages().getPlayerCards(giocatore.carte.getLast(), stage));

                    cardContainer.setBottom(esciB);

                    emp = controllore.getVivo() - 1;
                    mostraG = giocatore;
                    flagEmporio=true;

                }
                stackPane.getChildren().add(cardContainer);
            }
            break;

            case "Gatling": {

                for(int i=0;i<4;i++){

                    if(controllore.getGiocatore(i)!=giocatore && controllore.getGiocatore(i).getVita()>0){

                        Giocatore difesaG = controllore.getGiocatore(i);
                        cardContainer.getChildren().clear();
                        HBox cardBox = new HBox();

                        if(controllore.difesa(difesaG)!=null){
                            stackPane.getChildren().add(cardContainer);

                            difesaTF=true;

                            if(difesaG.tabellone.getPersonaggio() == Personaggio.JOURDONNAIS){
                                controllore.Jourdonnais(difesaG);
                            }
                            cardContainer.setTop(new Label("Giocatore " + difesaG.getNickname() + " scegli la carta con cui difenderti"));

                            for(int j=0;j<controllore.difesa(difesaG).size();j++){
                                cardBox.getChildren().add(new PlayerImagesContainer(difesaG, stage, this, false));

                            }
                            cardContainer.setCenter(cardBox);

                        }
                        else {
                            stackPane.getChildren().add(cardContainer);
                            cardBox.getChildren().add(new Label("Il giocatore " + difesaG.getNickname() + " non ha carte per difendersi"));
                            cardBox.getChildren().add(new Label("Il giocatore " + difesaG.getNickname() + " ha perso un punto vita"));

                            difesaG.setVita(controllore.getGiocatore(i).getVita()-1);

                            cardContainer.setTop(cardBox);

                            cardContainer.setCenter(esciB);

                        }

                    }
                }

            }
            break;

            case "Indiani": {

                VBox vBox = new VBox();

                for(int i=0;i<4;i++){

                    if(controllore.getGiocatore(i)!=giocatore && controllore.getGiocatore(i).getVita()>0){
                        boolean flagBang = false;

                        Giocatore giocatore1 = controllore.getGiocatore(i);

                        for(int j=0;j<giocatore1.carte.size();j++){
                            Carta carta1 = giocatore1.carte.get(j);
                            if (carta1.getClass().getSimpleName().equals("Bang")) {
                                flagBang = true;
                                break;
                            }
                        }
                        if(flagBang){
                            vBox.getChildren().add(new Label("Il giocatore "+ giocatore1.getNickname() + " aveva una carta Bang e si è difeso"));

                        }
                        else{
                            vBox.getChildren().add(new Label("Il giocatore "+ giocatore1.getNickname() + " non aveva una carta bang e perde un punto vita"));
                            if(giocatore1.tabellone.getPersonaggio() == Personaggio.BART_CASSIDY){
                                pescaBart(controllore.getGiocatore(i));
                            }
                            else if(giocatore1.tabellone.getPersonaggio() == Personaggio.EL_GRINGO){
                                pescaGringo(controllore.getGiocatore(i), giocatore);
                            }
                        }

                    }
                }
                controllore.usacarta(giocatore, carta);

                cardContainer.setTop(vBox);
                cardContainer.setCenter(esciB);

                stackPane.getChildren().add(cardContainer);

            }
            break;

            case "Mancato": {
                container = new WindowContainer(this.getStage());
                if(difesaTF){
                    controllore.scarta(giocatore, carta);
                    container.getChildren().clear();

                    Label label = new Label("Hai mancato il giocatore " + giocatore.getNickname());
                    container.setTop(label);
                    container.setCenter(esciB);

                    difesaTF=false;
                }
                else{
                    container.setTop(new Label("Puoi usare questa carta solo per difenderti!"));
                    container.setCenter(esciB);
                }

                stackPane.getChildren().add(container);


            }
            break;

            case "Panico": {
                ArrayList<Giocatore> giocatori = new ArrayList<>();

                Label catbalou1 = new Label("Scegli il giocatore a cui rubare una carta");


                try{

                    if(controllore.getGiocatore(turno+1).getVita()>0 && controllore.ritornaCarte(controllore.getGiocatore(turno+1))!=null){
                        giocatori.add(controllore.getGiocatore(turno+1));

                    }

                }catch (IndexOutOfBoundsException e){
                    giocatori.add(controllore.getGiocatore(0));
                }

                try {
                    if(controllore.getGiocatore(turno-1).getVita()>0&& controllore.ritornaCarte(controllore.getGiocatore(turno+1))!=null){
                        giocatori.add(controllore.getGiocatore(turno-1));

                    }
                }
                catch (IndexOutOfBoundsException e){
                    giocatori.add(controllore.getGiocatore(3));

                }

                if(!giocatori.isEmpty()){

                    for(int i=0;i<giocatori.size();i++){


                        cardContainer.setTop(catbalou1);
                        cardContainer.setCenter(nameBox);
                        Button button = new Button();
                        button.setText(giocatori.get(i).getNickname());

                        nameBox.getChildren().add(button);

                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Giocatore giocatoreD = null;
                                for (int j = 0; j < controllore.getVivo(); j++) {
                                    if (giocatori.get(j).getNickname() == button.getText()) {
                                        giocatoreD = giocatori.get(j);
                                        j = 10;
                                    }
                                }

                                controllore.usacartaP(giocatore, giocatoreD, carta);

                                cardContainer.getChildren().clear();

                                Label label = new Label("Hai rubato una carta a " + giocatoreD.getNickname());
                                cardContainer.setTop(label);
                                cardContainer.setCenter(esciB);

                                //controllore.usacarta(, carta);
                                setPlayerCards(giocatore);

                            }
                        });

                    }
                }


                stackPane.getChildren().add(cardContainer);

            }
            break;

            case "Saloon": {
                cardContainer.getChildren().clear();

                controllore.usacarta(giocatore, carta);

                cardContainer.setTop(new Label("Ogni giocatore guadagna un punto vita"));

                cardContainer.setCenter(esciB);

                stackPane.getChildren().add(cardContainer);

            }
            break;

            case "Weelsfargo": {

                HBox cardBox = new HBox();

                controllore.usacarta(giocatore, carta);

                int carteSize =giocatore.carte.size();

                cardBox.getChildren().add(new GetCardsImages().getPlayerCards(giocatore.carte.get(carteSize-3), stage));
                cardBox.getChildren().add(new GetCardsImages().getPlayerCards(giocatore.carte.get(carteSize-2), stage));
                cardBox.getChildren().add(new GetCardsImages().getPlayerCards(giocatore.carte.get(carteSize-1), stage));

                cardContainer.setTop(new Label("Hai pescato 3 nuove carte"));
                cardContainer.setCenter(cardBox);
                cardContainer.setBottom(esciB);

                stackPane.getChildren().add(cardContainer);

            }

            break;

            case "Prigione": {

                Label prigione1 = new Label("Chi vuoi mettere in prigione?");
                cardContainer.getChildren().clear();

                for(int i=0;i<4;i++){

                    if(controllore.getGiocatore(i)!=giocatore && controllore.getGiocatore(i).getVita()>0){

                        if(controllore.getGiocatore(i).tabellone.getRuolo() != Ruolo.SCERIFFO && !controllore.getGiocatore(i).isLock()){
                            cardContainer.setTop(prigione1);
                            cardContainer.setCenter(nameBox);
                            Button button = new Button();
                            button.setText(controllore.getGiocatore(i).getNickname());

                            nameBox.getChildren().add(button);

                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    int indexG=-1;
                                    Giocatore giocatore1=null;

                                    for(int j=0;j<controllore.getVivo();j++) {
                                        if (controllore.getGiocatore(j).getNickname() == button.getText()) {
                                            giocatore1 = controllore.getGiocatore(j);
                                            j = 5;
                                        }
                                    }

                                    controllore.usacartaPC(giocatore, giocatore1, carta);
                                    cardContainer.getChildren().clear();
                                    cardContainer.setTop(new Label("Hai messo in prigione il giocatore " + giocatore1.getNickname()));
                                    cardContainer.setCenter(esciB);
                                }
                            });

                        }

                    }

                }

                stackPane.getChildren().add(cardContainer);

            }

            default: {
                controllore.usacarta(giocatore, carta);
                setPlayerCards(giocatore);
                setBottomCards(giocatore);
                setCenterCards();
            }
            break;

        }

    }

    /**
     * Funzione per pescare le carte
     * @param giocatore Giocatore che deve pescare
     * @param n quante carte pescare
     */
    private void pescaCarta(Giocatore giocatore, int n){

        WindowContainer container = new WindowContainer(this.getStage());
        HBox carteBox = new HBox();
        Button esciB = new Button("Ok");

        esciB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().remove(container);
            }
        });


        Carta carta1= controllore.pesca(giocatore, false, false);
        Carta carta2;
        carteBox.getChildren().add(new GetCardsImages().getPlayerCards(carta1, stage));



        if(n==2){
            if(giocatore.tabellone.getPersonaggio().equals(Personaggio.BLACK_JACK)){

                carta2 = controllore.pesca(giocatore, false, true);

            }
            else{
                carta2 = controllore.pesca(giocatore, false, false);

            }
            carteBox.getChildren().add(new GetCardsImages().getPlayerCards(carta2, stage));

        }


        carteBox.setAlignment(Pos.CENTER);

        container.setTop(new Label("Hai pescato le seguenti carte"));

        container.setCenter(carteBox);
        container.setBottom(esciB);

        stackPane.getChildren().add(container);
    }

    /**
     * Funzione che consente a Pedro di pescare le carte
     * @param giocatore Il giocatore con personaggio Pedro
     * @return Il giocatore Pedro con le carte che ha pescato
     */
    private void pescaPedro(Giocatore giocatore){

        WindowContainer pedroContainer = new WindowContainer(this.getStage());
        GridPane mazziBox = new GridPane(2,2);
        Button mazzo1=null, mazzo2=null, okB = new Button("Ok");

        pedroContainer.setTop(pedroLabel);
        pedroContainer.setCenter(mazziBox);

        mazziBox.setAlignment(Pos.CENTER);

        try{
            mazzo1 = new Button();
            mazzo1.setGraphic(new GetCardsImages().getRetroCarta(stage, 0, 3));
            mazzo2= new GetCardsImages().getPlayerCards(controllore.ritornaScarti().getLast(), stage);

            mazziBox.add(mazzo1, 0,0 );
            mazziBox.add(mazzo1Lab, 0,1);
            mazziBox.add(mazzo2,1,0);
            mazziBox.add(mazzo2Lab, 1,1);

            mazzo1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Carta carta1, carta2;
                    carta1 = controllore.pesca(giocatore, false, false);
                    carta2 = controllore.pesca(giocatore, false, false);

                    mazziBox.getChildren().clear();
                    mazziBox.add(new GetCardsImages().getPlayerCards(carta1, stage), 0,0 );
                    mazziBox.add(new GetCardsImages().getPlayerCards(carta2, stage), 1,0 );

                    pedroContainer.setTop(new Label("Hai pescato le seguenti carte"));
                    pedroContainer.setCenter(mazziBox);
                    pedroContainer.setBottom(okB);

                }
            });

            mazzo2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Carta carta1, carta2;
                    carta1 = controllore.pesca(giocatore, true, false);
                    carta2 = controllore.pesca(giocatore, true, false);

                    mazziBox.getChildren().clear();
                    mazziBox.add(new GetCardsImages().getPlayerCards(carta1, stage), 0,0 );
                    mazziBox.add(new GetCardsImages().getPlayerCards(carta2, stage), 1,0 );

                    pedroContainer.setTop(new Label("Hai pescato le seguenti carte"));
                    pedroContainer.setCenter(mazziBox);
                    pedroContainer.setBottom(okB);



                }
            });

        }

        catch (NoSuchElementException ex){

            mazziBox.add(mazzo1, 0,0 );
            mazziBox.add(mazzo1Lab, 0,1);

            mazzo1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Carta carta1, carta2;
                    carta1 = controllore.pesca(giocatore, false, false);
                    carta2 = controllore.pesca(giocatore, false, false);

                    mazziBox.getChildren().clear();
                    mazziBox.add(new GetCardsImages().getPlayerCards(carta1, stage), 0,0 );
                    mazziBox.add(new GetCardsImages().getPlayerCards(carta2, stage), 1,0 );

                    pedroContainer.setTop(new Label("Hai pescato le seguenti carte"));
                    pedroContainer.setCenter(mazziBox);
                    pedroContainer.setBottom(okB);

                }
            });

        }

        okB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().remove(pedroContainer);
                setCenterCards();

            }

        });

        //mazzo1.setGraphic();

        stackPane.getChildren().add(pedroContainer);

    }

    /**
     * Funzione che consente a Jesse di pescare le carte
     * @param giocatore Il giocatore con personaggio Jesse
     * @return Il giocatore Jesse con le nuove carte che ha pescato
     */
    private void pescaJesse(Giocatore giocatore){

        WindowContainer container = new WindowContainer(this.getStage());
        Button mazzoB = new Button("Mazzo"), giocatoreB = new Button("Giocatore"), esciB = new Button("Ok"), rubaB;
        HBox boxB = new HBox();

        boxB.getChildren().add(mazzoB);
        boxB.getChildren().add(giocatoreB);
        boxB.setAlignment(Pos.CENTER);

        container.setTop(new Label("Vuoi pescare dal mazzo o da un giocatore?"));
        container.setCenter(boxB);

        esciB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().remove(container);
            }
        });

        mazzoB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                Carta carta1, carta2;
                carta1 = controllore.pesca(giocatore, false, false);
                carta2 = controllore.pesca(giocatore, false, false);
                HBox mazziBox = new HBox();

                mazziBox.getChildren().clear();
                mazziBox.getChildren().add(new GetCardsImages().getPlayerCards(carta1, stage));
                mazziBox.getChildren().add(new GetCardsImages().getPlayerCards(carta2, stage));

                container.setTop(new Label("Hai pescato le seguenti carte: "));
                container.setCenter(mazziBox);
                container.setBottom(esciB);


            }
        });

        giocatoreB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Carta carta;

                HBox giocBox = new HBox();
                giocBox.setAlignment(Pos.CENTER);

                container.getChildren().clear();
                container.setTop(new Label("A chi vuoi rubare una carta?"));

                for(int i=0;i<controllore.getVivo();i++){
                    if(controllore.getGiocatore(i)!=giocatore){
                        Button rubaB;

                        Giocatore giocatore1 = controllore.getGiocatore(i);
                        rubaB = new Button();
                        rubaB.setText(giocatore1.getNickname());
                        giocBox.getChildren().add(rubaB);

                        rubaB.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Giocatore giocatore2=null;
                                Carta carta1=null, carta2=null;

                                HBox cardContainer = new HBox();

                                for(int j=0;j<controllore.getVivo();j++){
                                    if(controllore.getGiocatore(j).getNickname() == rubaB.getText());
                                    giocatore2 = controllore.getGiocatore(j);
                                    j=5;

                                }

                                try{
                                    carta1 = controllore.jesseJones(giocatore, giocatore2);
                                    cardContainer.getChildren().add(new GetCardsImages().getPlayerCards(carta1, stage));

                                }
                                catch (Exception e){
                                    carta1 = controllore.pesca(giocatore, false, false);
                                }
                                carta2 = controllore.pesca(giocatore, false, false);

                                cardContainer.getChildren().add(new GetCardsImages().getPlayerCards(carta1, stage));

                                cardContainer.getChildren().add(new GetCardsImages().getPlayerCards(carta2, stage));

                                container.getChildren().clear();


                                container.setTop(new Label("Hai rubato le seguenti carte"));
                                container.setCenter(cardContainer);
                                container.setBottom(esciB);

                            }
                        });

                    }
                }

                container.setCenter(giocBox);

            }
        });

        stackPane.getChildren().add(container);

    }

    /**
     * Funzione che consente a Kit di pescare le carte
     * @param giocatore Il giocatore con personaggio Kit
     * @return Il giocatore Kit con le carte che ha pescato
     */
    private void pescaKit(Giocatore giocatore){

        ArrayList<Carta> carteKit = controllore.kitCarlson();

        WindowContainer container = new WindowContainer(this.getStage());
        HBox carteBox = new HBox();

        container.setTop(new Label("Pesca 2 carte fra queste 3"));

        for(int i=0;i<carteKit.size();i++){
            Button button = new Button();
            button.setGraphic(new GetCardsImages().getPlayerCards(carteKit.get(i), stage));
            button.setId(String.valueOf(i));

            carteBox.getChildren().add(button);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    giocatore.carte.add(carteKit.get(Integer.parseInt(button.getId())));
                    carteBox.getChildren().remove(button);

                    if(carteBox.getChildren().size()==1){
                        container.getChildren().clear();
                        stackPane.getChildren().remove(container);
                    }

                }
            });
        }

        container.setCenter(carteBox);

        stackPane.getChildren().add(container);

    }

    private void pescaGringo (Giocatore giocatoreGringo, Giocatore giocatoreRuba){

        try{
            stackPane.getChildren().remove(1);

        }
        catch (Exception e){

        }

        WindowContainer container = new WindowContainer(this.getStage());
        controllore.rubaCarta(giocatoreGringo, giocatoreRuba);
        Carta carta = giocatoreGringo.carte.getLast();
        Button esciB = new Button("Ok");

        esciB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().remove(container);
            }
        });

        container.setTop(new Label("Hai rubato questa carta a " + giocatoreRuba.getNickname()));

        container.setCenter(new GetCardsImages().getPlayerCards(carta, stage));
        container.setBottom(esciB);

        stackPane.getChildren().add(container);

    }

    private void pescaBart(Giocatore giocatore){

        try{
            stackPane.getChildren().remove(1);

        }
        catch (Exception e){

        }

        WindowContainer container = new WindowContainer(this.getStage());
        Carta carta = controllore.pesca(giocatore, false, false);
        Button esciB = new Button("Ok");

        esciB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().remove(container);
            }
        });

        container.setTop(new Label("Hai pescato questa carta"));

        container.setCenter(new GetCardsImages().getPlayerCards(carta, stage));
        container.setBottom(esciB);

        stackPane.getChildren().add(container);

    }

    /**
     * Funzione che ritorna lo stage della Scena
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Funzione che ritorna lo stackPane della Scena
     * @return stackPane
     */
    public StackPane getStackPane() {
        return stackPane;
    }

    /**
     * Funzione che ritorna il controllore della Scena
     * @return controllore
     */
    public Controllore getControllore(){
        return controllore;
    }

    /**
     * Funzione per settare lo stackPane della scena
     */
    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

}