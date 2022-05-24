// Jorge Aranda
// CS2450
// Assignment 2
// 3/14/2022

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class TaskB extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Image die1 = new Image("file:src/sample/Die1.png", 100, 100, true, true);
        Image die2 = new Image("file:src/sample/Die2.png", 100, 100, true, true);
        Image die3 = new Image("file:src/sample/Die3.png", 100, 100, true, true);
        Image die4 = new Image("file:src/sample/Die4.png", 100, 100, true, true);
        Image die5 = new Image("file:src/sample/Die5.png", 100, 100, true, true);
        Image die6 = new Image("file:src/sample/Die6.png", 100, 100, true, true);
        ImageView dieOneIV = new ImageView(die1);
        ImageView dieTwoIV = new ImageView(die1);


        Button rollDice = new Button("Roll Dice!");
        rollDice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Random random = new Random();
                int dieOne = random.nextInt(6) + 1;
                int dieTwo = random.nextInt(6) + 1;

                switch(dieOne){
                    case 1:
                        dieOneIV.setImage(die1);
                        break;
                    case 2:
                        dieOneIV.setImage(die2);
                        break;
                    case 3:
                        dieOneIV.setImage(die3);
                        break;
                    case 4:
                        dieOneIV.setImage(die4);
                        break;
                    case 5:
                        dieOneIV.setImage(die5);
                        break;
                    case 6:
                        dieOneIV.setImage(die6);
                        break;
                    default:
                        break;
                }

                switch(dieTwo){
                    case 1:
                        dieTwoIV.setImage(die1);
                        break;
                    case 2:
                        dieTwoIV.setImage(die2);
                        break;
                    case 3:
                        dieTwoIV.setImage(die3);
                        break;
                    case 4:
                        dieTwoIV.setImage(die4);
                        break;
                    case 5:
                        dieTwoIV.setImage(die5);
                        break;
                    case 6:
                        dieTwoIV.setImage(die6);
                        break;
                    default:
                        break;
                }
            }
        });

        HBox hbox = new HBox(20, dieOneIV, dieTwoIV);

        GridPane gridPane = new GridPane();
        gridPane.add(hbox, 0, 0);
        gridPane.add(rollDice, 0, 1);
        gridPane.setHgap(30);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(100));

        primaryStage.setTitle("Task B");
        primaryStage.setScene(new Scene(gridPane, 500, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
