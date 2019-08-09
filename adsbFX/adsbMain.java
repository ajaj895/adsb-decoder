package ads.b.decoder;

import javafx.application.Application;//needed for the application
import javafx.event.ActionEvent;//for event handling
import javafx.event.EventHandler;//for event handling
import javafx.scene.Scene;//needed for the scene
import javafx.scene.layout.Region;//part of the scene (a different one could be used though)
import javafx.scene.paint.Color;//to spice things up
import javafx.scene.shape.*;//also to spice things up
/**
 *
 * @author Evan
 */
public class adsbMain extends Application {//You always have to extend application in javaFx

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);//launches the application, passing args to the application.
    }
    
    @Override //start is a part of Application
    public void start(Stage primaryStage) throws Exception {
        /*
            A stage is a part of the window.
            The primary stage IS the window, and changing the attributes of primaryStage changes the window.
        */
	//A stage is the entire window, including the maximize/minimize/close buttons
        primaryStage.setTitle("Application title here");//this set the top title of the window.
        
        Region layout = new Region();//these two are used to create a custom scene based off of the next line.
        Scene scene = new Scene(layout, 400,600, Color.GAINSBORO);//Region is the root, region is used because it has the ability to resize the window. Width, hight. Color(optional)
        Rectangle r = new Rectangle(50,20,50,20);//double X,Y,WIDTH, HIGHT
        r.setFill(Color.CORNFLOWERBLUE);
        r.setArcHeight(20);
        r.setArcHeight(20);
        layout.setShape(r);
	Button
        primaryStage.setScene(scene);//this sets the custom scene made to
        primaryStage.show();//this is needed to bring up the window
    }

    @Override//handle stuff to be implemented later (OR LAMBDA EXPRESSIONS ( some variable -> some action)
    public void handle(ActionEvent event){
	
    }


}
