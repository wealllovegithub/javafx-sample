package org.saltations.controller;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.saltations.Happenings;
import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.Program;

import uk.co.it.modular.beans.BeanUtils;
import uk.co.it.modular.beans.TypeProperty;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TextColumn;
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
		String fileName = conf.getString("app.datastore.location");
				
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().activationDepth(30);
		config.common().updateDepth(30);
		
		File dbFile = new File(fileName);
		
		ObjectContainer objStore = Db4oEmbedded.openFile(config,dbFile.getAbsolutePath());
		
		Context.set(objStore);
		
		/*
		 * Retrieve the Program from the Data Store 
		 */
		
		ObjectSet<Program> programs = objStore.query(Program.class);
		
		
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
		
		primaryStage.setScene(new Scene(root, 300, 250));
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
		SplitPane centerSplit = new SplitPane();
        
		VBox lhs = genLHS();
		
		HBox rhs = new HBox();
		
		TableControl<Participant> participantTable = new  TableControl<Participant>();
		participantTable.setController(new ParticipantTableController());
		participantTable.setRecordClass(Participant.class);
		
		participantTable.autosize();

		List<TypeProperty> properties = BeanUtils.propertyList(Participant.class);
		
		for (TypeProperty property : properties) {
		
			if (property.isString())
			{
				participantTable.addColumn(new TextColumn<Participant>(property.getName()));
			}
			else if (property.isBoolean() )
			{
				participantTable.addColumn(new CheckBoxColumn<Participant>(property.getName()));
			}
			else if (property.isInteger() )
			{
				participantTable.addColumn(new NumberColumn<Participant, Integer>(property.getName(), Integer.class));
			}
		}
		
		rhs.getChildren().add(participantTable);
		rhs.autosize();
		
		centerSplit.getItems().addAll(lhs, rhs);
		
		return centerSplit;
	}

	/**
	 * @return
	 */
	private VBox genLHS() {
		VBox lhs = new VBox();

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

		lhs.getChildren().add(choiceBox);
		lhs.autosize();
		
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
		
		actionsAndDisplays.autosize();
		
		lhs.getChildren().add(actionsAndDisplays);
		
		return lhs;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}