package org.saltations.tracker.ui;

import java.io.File;
import java.util.Arrays;
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

import org.joda.time.DateTime;
import org.saltations.StockEvt;
import org.saltations.controller.Context;
import org.saltations.tracker.model.Coach;
import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.Program;
import org.saltations.tracker.ui.tab.CoachesTab;
import org.saltations.tracker.ui.tab.GeneralTab;
import org.saltations.tracker.ui.tab.HeadCoachesTab;
import org.saltations.tracker.ui.tab.ParticipantAttendanceCommunicationTab;
import org.saltations.tracker.ui.tab.ParticipantTab;
import org.saltations.tracker.ui.tab.ProductionTab;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.google.common.base.Preconditions;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Data
@Slf4j
public class MainApp extends Application {
	
	/**
	 * Load configuration
	 */
	
	private Config conf = ConfigFactory.load();
	
	/**
	 * Main Application 
	 */
	
	public MainApp()
	{
		
		/*
		 * Get the location of the data storage.
		 */
		
		String fileName = conf.getString("app.datastore-location");
		
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
		 * If it doesn't exist, create it.
		 */
		
		if (programs.size() == 0)
		{
			Program program = new Program();
			
			/*
			 * Start and End Date
			 */
			
			Preconditions.checkArgument(conf.hasPath("program.start-date"));
			
			String startDateAsString = conf.getString("program.start-date");

			Preconditions.checkArgument(conf.hasPath("program.end-date"));
			
			String endDateAsString = conf.getString("program.end-date");

			program.setStartDate(DateTime.parse(startDateAsString));
			
			program.setEndDate(DateTime.parse(endDateAsString));
			
			/*
			 * List of sessions
			 */

			List<? extends Config> sessionConfigs = conf.getConfigList("program.courses.selp.sessions");
						
			for (Config sessionConfig : sessionConfigs) {
				
				System.out.println(sessionConfig.getString("name"));
				System.out.println(sessionConfig.getString("date"));
				
			}
			
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
        
        String[] emptyArray = {};
        
        GeneralTab<Participant> tab1 = new ParticipantTab(Arrays.asList(emptyArray));
        tabPane.getTabs().add(tab1);

        GeneralTab<Coach> tab2 = new CoachesTab(Arrays.asList(emptyArray));
        tabPane.getTabs().add(tab2);
        
        HeadCoachesTab tab3 = new HeadCoachesTab(Arrays.asList(emptyArray));
        tabPane.getTabs().add(tab3);
        
        ProductionTab tab4 = new ProductionTab(Arrays.asList(emptyArray));
        tabPane.getTabs().add(tab4);
        
        ParticipantAttendanceCommunicationTab tab5 = new ParticipantAttendanceCommunicationTab(Arrays.asList(emptyArray));
        tabPane.getTabs().add(tab5);
        
        rhs.getChildren().add(tabPane);
        
		rhs.autosize();
		return rhs;
	}

	/**
	 * @return
	 */
	private VBox genLHS() {
		VBox lhs = new VBox();

		ChoiceBox<StockEvt> choiceBox = new ChoiceBox<StockEvt>();
		choiceBox.getItems().addAll(
				StockEvt.SIX_WEEKS_OUT,
				StockEvt.FIVE_WEEKS_OUT,
				StockEvt.FOUR_WEEKS_OUT,
				StockEvt.THREE_WEEKS_OUT,
				StockEvt.TWO_WEEKS_OUT,
				StockEvt.ONE_WEEKS_OUT,
				StockEvt.COACHES_TRAINING,
				StockEvt.WORK_DAY_I,
				StockEvt.CLASSROOM_1,
				StockEvt.CLASSROOM_2,
				StockEvt.CLASSROOM_3,
				StockEvt.CLASSROOM_4,
				StockEvt.WORK_DAY_II,
				StockEvt.CLASSROOM_5,
				StockEvt.CLASSROOM_6,
				StockEvt.CLASSROOM_7,
				StockEvt.CLASSROOM_8,
				StockEvt.FUTURES_MEETING_TRAINING,
				StockEvt.WORK_DAY_III,
				StockEvt.CLASSROOM_9,
				StockEvt.CLASSROOM_10,
				StockEvt.CLASSROOM_11,
				StockEvt.CLASSROOM_12
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