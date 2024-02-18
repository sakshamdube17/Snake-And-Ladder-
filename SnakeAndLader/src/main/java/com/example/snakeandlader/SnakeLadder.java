package com.example.snakeandlader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
public static final int tileSize=40 , width=10 , height=10;
public static final int buttonLine=tileSize*height+50, infoLine=buttonLine-30;

private static Dice dice=new Dice();

private Player playerOne , playerTwo;

private boolean playerOneTurn= false, playerTwoTurn= false, gameStarted=false;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize , height*tileSize + 100);
        for (int i = 0; i <height ; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile= new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }



      Image img = new Image("C:\\project for resume\\SnakeAndLader\\src\\main\\images (1).jpeg");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);
        root.getChildren().add(board);


        //Buton
        Button playerOneButton= new Button("Player One");
        Button playerTwoButton= new Button("Player Two");
        Button start = new Button("Start");

        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(buttonLine);

        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);

        start.setTranslateX(175);
        start.setTranslateY(buttonLine);


        root.getChildren().addAll(playerOneButton,playerTwoButton,start);


        //Button Info
        Label playerOneLabel=new Label("Your turn");
        Label playerTwoLabel=new Label("Your turn");
        Label diceLabel=new Label("Start the GAME");

        playerOneLabel.setTranslateX(20);
        playerOneLabel.setTranslateY(infoLine);

        playerTwoLabel.setTranslateX(300);
        playerTwoLabel.setTranslateY(infoLine);

        diceLabel.setTranslateX(155);
        diceLabel.setTranslateY(infoLine);

        root.getChildren().addAll(playerOneLabel,playerTwoLabel,diceLabel);

        playerOne = new Player(tileSize, Color.BLACK,"Amit");
        playerTwo = new Player(tileSize-5, Color.WHITE,"Sumit");

        root.getChildren().addAll(playerOne.getCoin(),playerTwo.getCoin());

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted){
                    if (playerOneTurn){
                        int diceValue=dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerOne.movePlayer(diceValue);
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is " + playerOne.getName());
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

//                            start.setDisable(false);
//                            start.setText("Restart");
                        }
                        else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted){
                    if (playerTwoTurn){
                        int diceValue=dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerTwo.movePlayer(diceValue);

                        if(playerTwo.isWinner()){
                            diceLabel.setText("winner is "+ playerTwo.getName());
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

//                            start.setDisable(false);
//                            start.setText("Restart");

                        }
                        else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }
                    }
                }
            }
        });

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                diceLabel.setText("Game Started");
                start.setDisable(true);
                playerOneTurn=true;
                playerOneLabel.setText("Your Turn " + playerOne.getName());
                playerTwoTurn=false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true) ;
            }
        });



        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}