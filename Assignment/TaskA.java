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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TaskA extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TextField foodChargeTextField = new TextField();
        foodChargeTextField.setPromptText("Enter Food Charge");
        foodChargeTextField.setFocusTraversable(false);
        Label dollarSign = new Label("$");
        Label foodChargeLabel = new Label("Food Charge");
        Label foodChargeTotalLabel = new Label("$0.00");
        Label tipLabel = new Label("18% Tip");
        Label tipTotalLabel = new Label("$0.00");
        Label salesTaxLabel = new Label("7% Sales Tax");
        Label salesTaxTotalLabel = new Label("$0.00");
        Label totalLabel = new Label("Total");
        Label actualTotalLabel = new Label("$0.00");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double foodCharge = Double.parseDouble(foodChargeTextField.getText());
                double tip = foodCharge * 0.18;
                double salesTax = foodCharge * 0.07;
                double total = foodCharge + tip + salesTax;
                foodChargeTotalLabel.setText(String.format("$%.2f", foodCharge));
                tipTotalLabel.setText(String.format("$%.2f", tip));
                salesTaxTotalLabel.setText(String.format("$%.2f", salesTax));
                actualTotalLabel.setText(String.format("$%.2f", total));
            }
        });

        HBox hbox = new HBox(5, dollarSign, foodChargeTextField);

        GridPane gridPane = new GridPane();
        gridPane.add(hbox, 0, 0);
        gridPane.add(submitButton, 1, 0);
        gridPane.add(foodChargeLabel, 0, 1);
        gridPane.add(foodChargeTotalLabel, 1, 1);
        gridPane.add(tipLabel, 0, 2);
        gridPane.add(tipTotalLabel, 1, 2);
        gridPane.add(salesTaxLabel, 0, 3);
        gridPane.add(salesTaxTotalLabel, 1, 3);
        gridPane.add(totalLabel, 0, 4);
        gridPane.add(actualTotalLabel, 1, 4);
        gridPane.setHgap(30);
        gridPane.setVgap(10);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(100));

        primaryStage.setTitle("Task A");
        primaryStage.setScene(new Scene(gridPane, 500, 300));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
