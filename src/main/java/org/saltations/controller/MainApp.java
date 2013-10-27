package org.saltations.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
  
  private Stage primaryStage;
  private BorderPane rootLayout;
  
  @Override
  public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("SELP Tracker");
      

      
      
      try {
          // Load the root layout from the fxml file
          FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/tracker-root.fxml"));
          rootLayout = (BorderPane) loader.load();
          Scene scene = new Scene(rootLayout);
          scene.getStylesheets().add("tiwulfx.css");//load tiwulfx.css
          primaryStage.setScene(scene);
          primaryStage.show();
      } catch (IOException e) {
          // Exception gets thrown if the fxml file could not be loaded
          e.printStackTrace();
      }

  }
  
  /**
  * Returns the main stage.
  * @return
  */
  public Stage getPrimaryStage() {
      return primaryStage;
  }
  
  public static void main(String[] args) {
      launch(args);
  }
}