package org.saltations.tracker.ui;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.poifs.property.PropertyTable;
import org.azeckoski.reflectutils.ClassFields;
import org.azeckoski.reflectutils.ReflectUtils;
import org.saltations.tracker.infra.Display;
import org.saltations.tracker.model.Coach;
import org.saltations.tracker.model.HeadCoach;
import org.saltations.tracker.model.Participant;
import org.saltations.tracker.model.Person;
import org.saltations.tracker.model.Program;
import org.tbee.javafx.scene.layout.MigPane;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.panemu.tiwulfx.form.CheckBoxControl;
import com.panemu.tiwulfx.form.NumberControl;
import com.panemu.tiwulfx.form.TextControl;

@Data
@Slf4j
public class ProtoApp extends Application {
	
	
	private Program program = new Program();

	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private class DisplayCfg {
		
		private String name = "";
		
		private String desc = "";
		
		private boolean required = false;

	};
	
	/**
	 * Main Application 
	 */
	
	public ProtoApp()
	{
		String[][] people = {
			
				{ "Brian","Brian","Albere"             },
				{ "Michelle","Michelle","Belliveau"    },
				{ "Leon","Laddie","Benton"             },
				{ "Michael","Mike","Bosco"             },
				{ "Irene","Irene","Bratsis"            },
				{ "Christopher","Chris","Brooks"       },
				{ "Sabrina","Sabrina","Brundage"       },
				{ "Chandra","Chandra","Cantor"         },
				{ "Caroline","Caroline","Caruso"       },
				{ "Deanna","Deanna","Coyle"            },
				{ "Daniela","Daniela","Dana"           },
				{ "Lisa","Lisa","Delisio"              },
				{ "Dillan","Dillan","DiGiovanni"       },
				{ "Amanda","Amanda","Duclos"           },
				{ "Debbie","Debbie","Duclos"           },
				{ "Allison","Allison","Genetelli"      },
				{ "Robert","Robb","Genetelli"          },
				{ "Susan","Susan","Glass"              },
				{ "Jessica","Jess","Guidaboni"         },
				{ "Janette","Janette","Gustafson"      },
				{ "Richard","Rich","Gustafson"         },
				{ "Larissa","Larissa","Haynes"         },
				{ "David","David","Hill"               },
				{ "Jeffrey","Jeff","Hollett"           },
				{ "Sara","Sara","Howell"               },
				{ "Katharine","Kate","Jackson"         },
				{ "Justin","Justin","Jarboe"           },
				{ "Nancy","Nancy","Kalgren"            },
				{ "James","Jim","Kaloutas"             },
				{ "Dharam-Jot","Dharam-Jot","Khalsa"   },
				{ "Brian","Brian","Kitchens"           },
				{ "Kristina","Kristina","Knight"       },
				{ "Debra","Debra","Koczon"             },
				{ "Francois","Francois","Laberge"      },
				{ "Caryn","Caryn","Laviv"              },
				{ "Ekaterina","Katrina","Lemberg"      },
				{ "Stacy","Stacy","Ludden"             },
				{ "Nancy","Nancy","Mahoney"            },
				{ "Dennis","Dennis","McGuire"          },
				{ "Sarah","Sarah","Mercado"            },
				{ "Estera","Estera","Mlch"             },
				{ "Trent","Trent","Mochel"             },
				{ "Samantha","Samantha","Moland"       },
				{ "Anna","Anna","Morrison"             },
				{ "Jakiel","Jakiel","Moses-Harris"     },
				{ "Jeremy","jeremy","Muldavin"         },
				{ "Benjamin","Ben","Norman"            },
				{ "Ruth","Ruth","Norton"               },
				{ "Natalia","Natalia","Novicka"        },
				{ "Leslie","Leslie","Piela"            },
				{ "Michael","Mike","Piela"             },
				{ "Renee","Renee","Santalo"            },
				{ "David","Dave","Shinnlinger"         },
				{ "Charles","Charlie","Stevenson"      },
				{ "Jeanne","JeanneLouise","Washington" } 
		};
		
		

		/*
		 * If it doesn't exist, create it.
		 */
			
			/*
			 * 9 Coaches
			 */
			
			
			int i = 0;
			
			for (String[]  row : people) {
				
				Person person = new Person(row[0], row[1], row[2]);
				
				if ( i <= 2 )
				{
					HeadCoach hc = new HeadCoach(person);
					program.getHeadCoaches().add(hc);
				}
				else if (i <= 9) {
					Coach c = new Coach(person);
					program.getCoaches().add(c);
				}
				else {
					Participant p = new Participant(person);
					program.getParticipants().add(p);
				}

				i++;
			} 
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Possibility Tracker");
		
		MigPane form = new MigPane();
		
		try {

			/*
			 * Configure Names
			 */

			String[] names = {"last","called",""};
			
			List<String> requestedProperties = Lists.asList("first", names); 

			/*
			 * Get the object
			 */
			
			Object bean = Participant.class.newInstance();
			
			/*
			 * Get the list of properties
			 */
			
			@SuppressWarnings("unchecked")
			Map<String,?> beanProperties = (Map<String,?>) BeanUtilsBean.getInstance().describe(bean);
			Set<String> propertyNames = beanProperties.keySet();
			
			/*
			 * Check that all the property names we are looking for are also in the list of known properties.  
			 */
			
			Set<String> unknownProperties = Sets.difference(Sets.newHashSet(requestedProperties), propertyNames);
			
			if ( !unknownProperties.isEmpty() )
			{
				log.error(MessageFormat.format("Some properties that were requested to be displayed have not been found : {0}", unknownProperties));
				
				throw new IllegalArgumentException();
			}
			
			/*
			 * For each requested property, create a Label and a Entry point
			 */
			
			
			for (String propertyName : requestedProperties) {

				PropertyDescriptor descriptor = BeanUtilsBean2.getInstance().getPropertyUtils().getPropertyDescriptor(bean, propertyName);

				Class<?> propertyType = descriptor.getPropertyType();
				
				log.info(MessageFormat.format("{0} - {1}", propertyType.getSimpleName(), propertyName));
				
				/*
				 * Determine the text of the label
				 */
			
				String labelText = propertyName;
				
				Label label = new Label(labelText);
				
				form.add(label);
				
				/*
				 * Determine the type of the Control.
				 */
				
				if (!propertyType.isInterface())
				{
					if (propertyType.isAssignableFrom(Boolean.class))
					{
						CheckBoxControl control = new CheckBoxControl(propertyName);
						form.add(control, "wrap");		
					}
					else if (propertyType.isAssignableFrom(String.class))
					{
						TextControl control = new TextControl(propertyName);
						form.add(control, "wrap");		
					}
					else if (propertyType.isAssignableFrom(Long.class))
					{
						NumberControl<Long> control = new NumberControl<Long>();
						control.setPropertyName(propertyName);
						form.add(control, "wrap");		
					}
				}
			}

		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Root Pane.
		 */
		
		BorderPane root = new BorderPane();
		
        /*
         * Center Body
         */
		
        VBox box = new VBox();
        
        box.getChildren().add(form);
		box.autosize();
        
		root.setCenter(box);
		root.autosize();
		
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


	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}