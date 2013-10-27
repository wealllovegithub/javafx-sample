package org.saltations.controller;

import com.sun.scenario.animation.SplineInterpolator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        
        /*
         * Center Body
         */
		
        SplitPane centerSplit = new SplitPane();
        
		VBox vbox = new VBox();

		ChoiceBox choiceBox = new ChoiceBox<String>();

		vbox.getChildren().add(choiceBox);
		vbox.autosize();
		
		TreeView<String> actionsAndDisplays = new TreeView<String>();
		actionsAndDisplays.autosize();
		vbox.getChildren().add(actionsAndDisplays);
		
		
		HBox hbox = new HBox();
		
		centerSplit.getItems().addAll(vbox, hbox);
		
		root.setCenter(centerSplit);

		/*
		 * Activate.
		 */
		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}