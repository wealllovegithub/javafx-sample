package org.saltations.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

		/*
		 * Root Pane.
		 */
		
		BorderPane root = new BorderPane();
		
		/*
		 * Menu Bar
		 */
		
		MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuImport = new Menu("Import");
        Menu menuExport = new Menu("View");

        menuBar.getMenus().addAll(menuFile, menuImport, menuExport);
        
        root.setTop(menuBar);
		
		HBox hbox = new HBox();
		hbox.getChildren().add(btn);
		
		root.setCenter(hbox);
		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}