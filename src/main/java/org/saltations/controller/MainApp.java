package org.saltations.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Possibility Tracker");
		
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		BorderPane root = new BorderPane();
		
		HBox hbox = new HBox();
		hbox.getChildren().add(btn);
		
		root.getChildren().add(hbox);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}