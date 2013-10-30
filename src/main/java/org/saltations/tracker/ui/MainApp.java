package org.saltations.tracker.ui;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.saltations.StockHappening;
import org.saltations.controller.Context;
import org.saltations.tracker.model.Program;
import org.saltations.tracker.ui.tab.CoachesTab;
import org.saltations.tracker.ui.tab.HeadCoachesTab;
import org.saltations.tracker.ui.tab.ParticipantTab;
import org.saltations.tracker.ui.tab.ProductionTab;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Data
@Slf4j
public class MainApp extends Application {
	
	/**
	 * Load configuration
	 */
	
	private Config conf = ConfigFactory.load();
	
	
	public MainApp()
	{
		/*
		 * Get the location of the data stoprage.
		 */
		
		String fileName = conf.getString("app.datastore.location");
		
		/*
		 * Configure the data storage. 
		 */
		
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().activationDepth(30);
		config.common().updateDepth(30);
		
		File dbFile = new File(fileName);
		
		ObjectContainer objStore = Db4oEmbedded.openFile(config,dbFile.getAbsolutePath());
		
		/*
		 * Set it into a Context object as a singleton.
		 */
		
		Context.set(objStore);
		
		/*
		 * Retrieve the Program from the Data Store 
		 */
		
		ObjectSet<Program> programs = objStore.query(Program.class);

		/*
		 * If it doesnt exist, create it.
		 */
		
		if (programs.size() == 0)
		{
			Program program = new Program();
			
			objStore.store(program);
			objStore.commit();
			
			Context.set(program);
		}
		else {
			Context.set(programs.get(0));
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Possibility Tracker");
		
		/*
		 * Root Pane.
		 */
		
		BorderPane root = new BorderPane();
		
		/*
		 * Menu Bar
		 */
		
		MenuBar menuBar = genMenuBar();
        
        root.setTop(menuBar);
        
        /*
         * Center Body
         */
		
        SplitPane centerSplit = genMainSplit();
		
		root.setCenter(centerSplit);

		/*
		 * Activate.
		 */
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	/**
	 * @return
	 */
	
	private MenuBar genMenuBar() {
		
		MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuImport = new Menu("Import");
        Menu menuExport = new Menu("View");

        menuBar.getMenus().addAll(menuFile, menuImport, menuExport);
        
		return menuBar;
	}

	/**
	 * @return
	 */
	private SplitPane genMainSplit() {
		
		/* 
		 * TODO Snug the LHS of the Split Pane so that the LHS is just big enough for the select.
		 */
		
		SplitPane centerSplit = new SplitPane();
        
		VBox lhs = genLHS();
		lhs.setMinWidth(Control.USE_COMPUTED_SIZE);
        lhs.setMaxWidth(Control.USE_COMPUTED_SIZE);
		
		HBox rhs = genRHS();
		
		centerSplit.getItems().addAll(lhs, rhs);
		
		return centerSplit;
	}

	private HBox genRHS() {
		
		HBox rhs = new HBox();

		/*
		 * Generate the Tab Pane. This will have tabs added to it as necessary.
		 */
		
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
		
        /*
         * Default tabs
         */
        
        ParticipantTab tab1 = new ParticipantTab();
        tabPane.getTabs().add(tab1);

        CoachesTab tab2 = new CoachesTab();
        tabPane.getTabs().add(tab2);
        
        HeadCoachesTab tab3 = new HeadCoachesTab();
        tabPane.getTabs().add(tab3);
        
        ProductionTab tab4 = new ProductionTab();
        tabPane.getTabs().add(tab4);
        
        rhs.getChildren().add(tabPane);
        
		rhs.autosize();
		return rhs;
	}

	/**
	 * @return
	 */
	private VBox genLHS() {
		VBox lhs = new VBox();

		ChoiceBox<StockHappening> choiceBox = new ChoiceBox<StockHappening>();
		choiceBox.getItems().addAll(
				StockHappening.SIX_WEEKS_OUT,
				StockHappening.FIVE_WEEKS_OUT,
				StockHappening.FOUR_WEEKS_OUT,
				StockHappening.THREE_WEEKS_OUT,
				StockHappening.TWO_WEEKS_OUT,
				StockHappening.ONE_WEEKS_OUT,
				StockHappening.COACHES_TRAINING,
				StockHappening.WORK_DAY_I,
				StockHappening.CLASSROOM_1,
				StockHappening.CLASSROOM_2,
				StockHappening.CLASSROOM_3,
				StockHappening.CLASSROOM_4,
				StockHappening.WORK_DAY_II,
				StockHappening.CLASSROOM_5,
				StockHappening.CLASSROOM_6,
				StockHappening.CLASSROOM_7,
				StockHappening.CLASSROOM_8,
				StockHappening.FUTURES_MEETING_TRAINING,
				StockHappening.WORK_DAY_III,
				StockHappening.CLASSROOM_9,
				StockHappening.CLASSROOM_10,
				StockHappening.CLASSROOM_11,
				StockHappening.CLASSROOM_12
				);
		
		choiceBox.getSelectionModel().selectFirst();

		lhs.getChildren().add(choiceBox);
		
		/*
		 * Read the config into the memory 
		 */
		
		TreeItem<String> treeRoot = new TreeItem<String>("Dashboard");	
		
		List<String>  axns = conf.getStringList("app.happenings.WORK_DAY_I.axns");
		TreeItem<String> axnItem = new TreeItem<String>("Actions");
		
		for (String axn : axns) {
	        axnItem.getChildren().add(new TreeItem<String>(axn));
		}

		treeRoot.getChildren().add(axnItem);
		
		List<String>  displays = conf.getStringList("app.happenings.WORK_DAY_I.displays");
		TreeItem<String> displayItem = new TreeItem<String>("Displays");
		
		for (String display : displays) {
	        displayItem.getChildren().add(new TreeItem<String>(display));
		}

		treeRoot.getChildren().add(displayItem);
 
        TreeView<String> actionsAndDisplays = new TreeView<String>();
        actionsAndDisplays.setShowRoot(true);
        actionsAndDisplays.setRoot(treeRoot);
        treeRoot.setExpanded(true);
		
		actionsAndDisplays.setMaxWidth(Control.USE_COMPUTED_SIZE);
		
		lhs.getChildren().add(actionsAndDisplays);
		
		return lhs;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}