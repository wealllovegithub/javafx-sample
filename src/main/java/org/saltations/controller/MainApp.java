package org.saltations.controller;

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

import org.saltations.Happenings;

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

		ChoiceBox<Happenings> choiceBox = new ChoiceBox<Happenings>();
		choiceBox.getItems().addAll(
				Happenings.SIX_WEEKS_OUT,
				Happenings.FIVE_WEEKS_OUT,
				Happenings.FOUR_WEEKS_OUT,
				Happenings.THREE_WEEKS_OUT,
				Happenings.TWO_WEEKS_OUT,
				Happenings.ONE_WEEKS_OUT,
				Happenings.COACHES_TRAINING,
				Happenings.WORK_DAY_I,
				Happenings.CLASSROOM_1,
				Happenings.CLASSROOM_2,
				Happenings.CLASSROOM_3,
				Happenings.CLASSROOM_4,
				Happenings.WORK_DAY_II,
				Happenings.CLASSROOM_5,
				Happenings.CLASSROOM_6,
				Happenings.CLASSROOM_7,
				Happenings.CLASSROOM_8,
				Happenings.FUTURES_MEETING_TRAINING,
				Happenings.WORK_DAY_III,
				Happenings.CLASSROOM_9,
				Happenings.CLASSROOM_10,
				Happenings.CLASSROOM_11,
				Happenings.CLASSROOM_12
				);
		
		choiceBox.getSelectionModel().selectFirst();
		choiceBox.autosize();

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