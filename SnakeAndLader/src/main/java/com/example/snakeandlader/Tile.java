package com.example.snakeandlader;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
public Tile(int tileSize){
    setWidth(tileSize);
    setHeight(tileSize);
    setFill(Color.LIGHTGRAY);
    setStroke(Color.BLACK);
}
}
