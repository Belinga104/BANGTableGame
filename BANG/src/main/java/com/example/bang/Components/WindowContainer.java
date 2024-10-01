package com.example.bang.Components;

import com.example.bang.Interfaces.BaseScene;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class WindowContainer extends BorderPane implements BaseScene {

    public WindowContainer(Stage stage){
        
        
        this.getStylesheets().add(getClass().getResource("/Css/WindowContainer.css").toString());

        this.setId("panelOption");

        this.minHeightProperty().bind(Bindings.divide(stage.heightProperty(), getConfirmationPaneHeight*8));
        this.minWidthProperty().bind(Bindings.divide(stage.widthProperty(), confirmationPaneWidth*8));

        this.maxHeightProperty().bind(Bindings.divide(stage.heightProperty(), getConfirmationPaneHeight) );
        this.maxWidthProperty().bind(Bindings.divide(stage.widthProperty(), confirmationPaneWidth));

    }



}
