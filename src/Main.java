import javafx.scene.Scene;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.lang.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;




public class Main extends Application {

    TheRaven theRaven = new TheRaven();
    public static void main(String[] args) throws IOException {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane layout = new StackPane();
        Button button = new Button("Run Parser");
        button.setOnAction(actionEvent -> {
            try {
                theRaven.print();
            } catch(Exception e) {
                System.out.print(e);
            }
        });
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.setTitle("The Raven");
        stage.show();
    }
}
