package com.example.bang.Interfaces;

import javafx.scene.image.ImageView;

public interface BaseScene {

    //Font font01 = Font.loadFont("file:resources/Fonts/font-01.ttf", 45);

    int preferredScreenWidth=1920, preferredScreenHeight=1080, minimumScreenWidth=800, minimumScreenHeight=500;

    double cardWidthRatio= 20.0, cardHeightRatio=10, bottomCardWidthRatio=12.0, bottomCardHeightRatio=5.5,
            confirmationPaneWidth=2, getConfirmationPaneHeight=4, bulletBottomRatio= 14, bulletElseRatio = 23;

    //Aspect ratio: rapporto tra la risoluzione massima(1920x1080) e la dimensione minima(800x500)
    double aspectratio=5.18, buttonWidth=400.0, buttonHeight=75.0;



}
