package com.example.bang.Components;

import Logica.Partita.Carte.Normali.BordoBlu.Armi;
import Logica.Partita.Carte.Normali.Carta;
import Logica.Partita.Carte.Personaggi.Personaggio;
import Logica.Partita.Carte.Ruolo;
import com.example.bang.Interfaces.BaseScene;
import com.example.bang.Interfaces.ImagesPath;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GetCardsImages implements ImagesPath, BaseScene {

    private ImageView imgView1;

    private double thisCardHeightRatio, thisCardWidthRatio;


    public GetCardsImages(){


    }
    /**
     * Funzione per prendere le immagini delle armi
     * @param ruolo La carta di cui stampare l'immagine
     * @param stage Lo stage della scena
     * @return Il bottone con l'immagine della carta e collegato al controllore delle carte
     */
    public ImageView getRuoloImage(Ruolo ruolo, Stage stage, int TOPoBOT){

        if(TOPoBOT ==0){
            thisCardHeightRatio = bottomCardHeightRatio;
            thisCardWidthRatio = bottomCardWidthRatio;
        }
        else{
            thisCardHeightRatio = cardHeightRatio;
            thisCardWidthRatio = cardWidthRatio;
        }
        imgView1 = new ImageView();
        imgView1.setPreserveRatio(true);


        imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), thisCardHeightRatio));
        imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), thisCardWidthRatio));

        switch (ruolo){
            case SCERIFFO -> imgView1.setImage(new Image(getClass().getResource(sceriffo).toString()));
            case RINNEGATO -> imgView1.setImage(new Image(getClass().getResource(rinnegato).toString()));
            case FUORILEGGE -> imgView1.setImage(new Image(getClass().getResource(fuorilegge).toString()));

        }

        return imgView1;
    }

    /**
     * Funzione per prendere le immagini delle armi
     * @param personaggio La carta di cui stampare l'immagine
     * @param stage Lo stage della scena
     * @return Il bottone con l'immagine della carta e collegato al controllore delle carte
     */
    public ImageView getPersonaggioImage(Personaggio personaggio, Stage stage, int TOPoBOT){
        if(TOPoBOT ==0){
            thisCardHeightRatio = bottomCardHeightRatio;
            thisCardWidthRatio = bottomCardWidthRatio;
        }
        else{
            thisCardHeightRatio = cardHeightRatio;
            thisCardWidthRatio = cardWidthRatio;
        }
        imgView1 = new ImageView();
        imgView1.setPreserveRatio(true);


        imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), thisCardHeightRatio));
        imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), thisCardWidthRatio));

        switch (personaggio){
            case LUCKY_DUKE -> imgView1.setImage(new Image(getClass().getResource(luckyduke_01).toString()));
            case ROSE_DOOLAN -> imgView1.setImage(new Image(getClass().getResource(luckyduke_01).toString()));
            case SID_KETCHUM -> imgView1.setImage(new Image(getClass().getResource(sidketchum_01).toString()));
            case SUZY_LAFAYETTE -> imgView1.setImage(new Image(getClass().getResource(suzylafayette_01).toString()));
            case VULTURE_SAM -> imgView1.setImage(new Image(getClass().getResource(vulturesam_01).toString()));
            case WILLY_THE_KID -> imgView1.setImage(new Image(getClass().getResource(willythekid_01).toString()));
            case BART_CASSIDY -> imgView1.setImage(new Image(getClass().getResource(bartcassidy_01).toString()));
            case BLACK_JACK -> imgView1.setImage(new Image(getClass().getResource(blackjack_01).toString()));
            case EL_GRINGO -> imgView1.setImage(new Image(getClass().getResource(elgringo_01).toString()));
            case JESSE_JONES -> imgView1.setImage(new Image(getClass().getResource(jessejones_01).toString()));
            case JOURDONNAIS -> imgView1.setImage(new Image(getClass().getResource(jourdonnais_01).toString()));
            case KIT_CARLSON -> imgView1.setImage(new Image(getClass().getResource(kitcarlson_01).toString()));
            case PAUL_REGRET -> imgView1.setImage(new Image(getClass().getResource(paulregret_01).toString()));
            case PEDRO_RAMIREZ -> imgView1.setImage(new Image(getClass().getResource(pedroramirez_01).toString()));
        }

        return imgView1;

    }
    /**
     * Funzione per prendere le immagini delle armi
     * @param arma La carta di cui stampare l'immagine
     * @param stage Lo stage della scena
     * @return Il bottone con l'immagine della carta e collegato al controllore delle carte
     */
    public ImageView getArmaImage(Armi arma, Stage stage, int TOPoBOT){
        if(TOPoBOT ==0){
            thisCardHeightRatio = bottomCardHeightRatio;
            thisCardWidthRatio = bottomCardWidthRatio;
        }
        else{
            thisCardHeightRatio = cardHeightRatio;
            thisCardWidthRatio = cardWidthRatio;
        }
        imgView1 = new ImageView();
        imgView1.setPreserveRatio(true);


        imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), thisCardHeightRatio));
        imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), thisCardWidthRatio));

        switch (arma.getClass().getSimpleName()){
            case "Barile":{

                switch(arma.getSeme()){
                    case "QP" -> imgView1.setImage(new Image(getClass().getResource(barile_01).toString()));
                    case "KP" -> imgView1.setImage(new Image(getClass().getResource(barile_01).toString()));

                }

            }
            break;

            case "Mirino":{
                imgView1.setImage(new Image(getClass().getResource(mirino_01).toString()));

            }
            break;

            case "Mustang":{
                switch(arma.getSeme()){
                    case "8C" -> imgView1.setImage(new Image(getClass().getResource(mustang_01).toString()));
                    case "9C" -> imgView1.setImage(new Image(getClass().getResource(mustang_01).toString()));
                }
            }
            break;

            case "Prigione":{
                switch(arma.getSeme()){
                    case "JP" -> imgView1.setImage(new Image(getClass().getResource(prigione_01).toString()));
                    case "4C" -> imgView1.setImage(new Image(getClass().getResource(prigione_01).toString()));
                    case "10P" -> imgView1.setImage(new Image(getClass().getResource(prigione_01).toString()));

                }
            }
            break;

            case "Remington":{
                imgView1.setImage(new Image(getClass().getResource(remington_01).toString()));
            }
            break;

            case "Revolver":{
                imgView1.setImage(new Image(getClass().getResource(revolver_01).toString()));
            }

            case "Schofield":{
                switch (arma.getSeme()){
                    case "JF" -> imgView1.setImage(new Image(getClass().getResource(schofield_01).toString()));
                    case "QF" -> imgView1.setImage(new Image(getClass().getResource(schofield_01).toString()));
                    case "KP" -> imgView1.setImage(new Image(getClass().getResource(schofield_01).toString()));
                }

            }
            break;

            case "Volcanic":{
                switch (arma.getSeme()){
                    case "10P" -> imgView1.setImage(new Image(getClass().getResource(volcanic_01).toString()));
                    case "10F" -> imgView1.setImage(new Image(getClass().getResource(volcanic_01).toString()));

                }
            }
            break;

            case "Whinchester":{
                imgView1.setImage(new Image(getClass().getResource(winchester_01).toString()));
            }

            case "RevCarabine":{
                imgView1.setImage(new Image(getClass().getResource(carabine_01).toString()));

            }


        }
        return imgView1;

    }

    /**
     * Funzione per prendere le immagini delle carte del mazzo
     * @param carta La carta di cui stampare l'immagine
     * @param stage Lo stage della scena
     * @return Il bottone con l'immagine della carta e collegato al controllore delle carte
     */
    public Button getPlayerCards(Carta carta, Stage stage){
        Button button = new Button();

        imgView1 = new ImageView();

        imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), bottomCardHeightRatio));
        imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), bottomCardWidthRatio));
        imgView1.setPreserveRatio(true);

        switch (carta.getClass().getSimpleName()){

            //FARE TUTTI I CASI PER SEMI DIVERSI
            case "Bang": {

                    /*switch (tmpCarta.getSeme()){

                    }*/

                button.setId("Bang");
                imgView1.setImage(new Image(getClass().getResource(bang_01).toString()));
            }
            break;

            case "Birra": {
                button.setId("Birra");
                imgView1.setImage(new Image(getClass().getResource(birra_01).toString()));

            }
            break;

            case "Catbalou": {
                button.setId("Catbalou");
                imgView1.setImage(new Image(getClass().getResource(catbalou_01).toString()));

            }
            break;

            case "Diligenza": {
                button.setId("Diligenza");
                imgView1.setImage(new Image(getClass().getResource(diligenza_01).toString()));

            }
            break;

            case "Duello": {
                button.setId("Duello");
                imgView1.setImage(new Image(getClass().getResource(duello_01).toString()));

            }
            break;

            case "Emporio": {
                button.setId("Emporio");
                imgView1.setImage(new Image(getClass().getResource(emporio_01).toString()));

            }
            break;

            case "Gatling": {
                button.setId("Gatling");
                imgView1.setImage(new Image(getClass().getResource(gatling_01).toString()));

            }
            break;

            case "Indiani": {
                button.setId("Indiani");
                imgView1.setImage(new Image(getClass().getResource(indiani_01).toString()));

            }
            break;

            case "Mancato": {
                button.setId("Mancato");
                imgView1.setImage(new Image(getClass().getResource(mancato_01).toString()));

            }
            break;

            case "Panico": {
                button.setId("Panico");
                imgView1.setImage(new Image(getClass().getResource(panico_01).toString()));

            }
            break;

            case "Saloon": {
                button.setId("Saloon");
                imgView1.setImage(new Image(getClass().getResource(saloon_01).toString()));

            }
            break;

            case "Weelsfargo": {
                button.setId("Weelsfargo");
                imgView1.setImage(new Image(getClass().getResource(wellsfargo_01).toString()));

            }
            break;
            case "Barile":{
                button.setId("Barile");
                imgView1.setImage(new Image(getClass().getResource(barile_01).toString()));


            }
            break;

            case "Mirino":{
                button.setId("Mirno");
                imgView1.setImage(new Image(getClass().getResource(mirino_01).toString()));

            }
            break;

            case "Mustang":{
                button.setId("Mustang");
                imgView1.setImage(new Image(getClass().getResource(mustang_01).toString()));

            }
            break;

            case "Prigione":{
                button.setId("Prigione");
                imgView1.setImage(new Image(getClass().getResource(prigione_01).toString()));

            }
            break;

            case "Remington":{
                button.setId("Remington");
                imgView1.setImage(new Image(getClass().getResource(remington_01).toString()));

            }
            break;

            case "Revolver":{
                button.setId("Revolver");
                //METTER LA REVOLVER
                imgView1.setImage(new Image(getClass().getResource(remington_01).toString()));

            }

            case "Schofield":{
                button.setId("Schofield");
                imgView1.setImage(new Image(getClass().getResource(schofield_01).toString()));


            }
            break;

            case "Volcanic":{
                button.setId("Volcanic");
                imgView1.setImage(new Image(getClass().getResource(volcanic_01).toString()));

            }
            break;

            case "Whinchester":{
                button.setId("Whinchester");
                imgView1.setImage(new Image(getClass().getResource(winchester_01).toString()));

            }

            case "RevCarabine":{
                button.setId("RevCarabine");
                imgView1.setImage(new Image(getClass().getResource(carabine_01).toString()));

            }

        }
        button.setGraphic(imgView1);
        return button;

    }

    /**
     * Funzione per ritornare il retro della carta
     * @param stage Lo Stage della scena
     * @param TOPoBOT Se 0 stampo le carte con la dimensione corrispondente a quelle in basso, se 1 corrispondente a quelle in alto
     * @param tipo Quale retro usare. 0 Retro ruolo, 1 retro personaggio, 2 retro arma, 3 mazzo
     * @return ImageView del retro della carta
     */
    public ImageView getRetroCarta(Stage stage, int TOPoBOT, int tipo){

        if(TOPoBOT ==0){
            thisCardHeightRatio = bottomCardHeightRatio;
            thisCardWidthRatio = bottomCardWidthRatio;
        }
        else{
            thisCardHeightRatio = cardHeightRatio;
            thisCardWidthRatio = cardWidthRatio;
        }

        imgView1 = new ImageView();
        imgView1.setPreserveRatio(true);
        imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), thisCardHeightRatio));
        imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), thisCardWidthRatio));

        if(tipo==0){
            imgView1.setImage(new Image(getClass().getResource(retroPers).toString()));

        }
        else if(tipo==1){
            imgView1.setImage(new Image(getClass().getResource(retroPers).toString()));

        }
        else if(tipo==2){
            imgView1.setImage(new Image(getClass().getResource(retroPers).toString()));
        }
        else if(tipo==3){
            imgView1.setImage(new Image(getClass().getResource(retroMazzo).toString()));
        }

        return imgView1;
    }

    /**
     * Funzione per stampare i proiettili corrispondenti alla vita del giocatore
     * @param stage Lo stage della scena
     * @param SoN Se true, stampo il proiettile pieno, se false lo stampo vuoto
     * @param TOPoBOT Se true, stampo i con ratio 14, altrimenti con ratio 20
     * @return ImageView del proiettile
     */
    public ImageView getBullets(Stage stage, boolean SoN, int TOPoBOT){

        imgView1 = new ImageView();
        imgView1.setPreserveRatio(true);

        if(TOPoBOT ==0){
            imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), bulletBottomRatio));
            imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), bulletBottomRatio));

        }
        else{
            imgView1.fitHeightProperty().bind(Bindings.divide(stage.heightProperty(), bulletElseRatio));
            imgView1.fitWidthProperty().bind(Bindings.divide(stage.widthProperty(), bulletElseRatio));

        }

        if(SoN){

            imgView1.setImage(new Image(getClass().getResource(proiettPieni).toString()));
        }
        else{
            imgView1.setImage(new Image(getClass().getResource(proiettVuoti).toString()));

        }

        return imgView1;

    }

}
