package com.example.bang.Components;


import Logica.Controllore.Controllore;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatsContainer extends BorderPane{

    private GridPane gridPane = new GridPane(4, 2);
    Controllore controllore;

    boolean update =false;
    /**
     * Container delle statistiche della partita (Vita)
     * @param controllore controllore
     */
    public StatsContainer(Controllore controllore) {

        this.setCenter(gridPane);
        this.controllore = controllore;

        this.getStylesheets().add(getClass().getResource("/Css/StatsContainer.css").toString());


        if(!update){
            gridPane.add(new Label(controllore.getGiocatore(0).getNickname() + " " + controllore.getGiocatore(0).getVita()), 0,0 );
            gridPane.add(new Label(controllore.getGiocatore(1).getNickname() + " " + controllore.getGiocatore(1).getVita()), 0,1 );
            gridPane.add(new Label(controllore.getGiocatore(2).getNickname() + " " + controllore.getGiocatore(2).getVita()), 0,2 );
            gridPane.add(new Label(controllore.getGiocatore(3).getNickname() + " " + controllore.getGiocatore(3).getVita()), 0,3 );
        }
        else{
            updateList();

        }

    }

    public void updateList(){

        gridPane.getChildren().clear();

        gridPane.add(new Label(controllore.getGiocatore(0).getNickname() + " " + controllore.getGiocatore(0).getVita()), 0,0 );
        gridPane.add(new Label(controllore.getGiocatore(1).getNickname() + " " + controllore.getGiocatore(1).getVita()), 0,1 );
        gridPane.add(new Label(controllore.getGiocatore(2).getNickname() + " " + controllore.getGiocatore(2).getVita()), 0,2 );
        gridPane.add(new Label(controllore.getGiocatore(3).getNickname() + " " + controllore.getGiocatore(3).getVita()), 0,3 );
    }
}
