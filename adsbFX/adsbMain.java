
package ads.b.decoder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
/**
 *
 * @author Evan
 */
public class adsbMain extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);//launches the application, passing args to the application.
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
            A stage is a part of the window.
            The primary stage IS the window, and changing the attributes of primaryStage changes the window.
        */
        primaryStage.setTitle("Application title here");//this set the top title of the window.
        
        Region layout = new Region();//these two are used to create a custom scene based off of the next line.
        Scene scene = new Scene(layout, 400,600, Color.GAINSBORO);//Region is the root, region is used because it has the ability to resize the window. Width, hight. Color(optional)
        Rectangle r = new Rectangle(50,20,50,20);//double X,Y,WIDTH, HIGHT
        r.setFill(Color.CORNFLOWERBLUE);
        r.setArcHeight(20);
        r.setArcHeight(20);
        layout.setShape(r);
        primaryStage.setScene(scene);//this sets the custom scene made to
        primaryStage.show();//this is needed to bring up the window
    }

}