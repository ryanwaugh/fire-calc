package dev.ryanwaugh.firecalc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
  public static final Model model = new Model();
  public static final TaxModel taxModel = new TaxModel();
  public static final View view = new View();
  public static Controller controller = null;
  @Override
  public void start(Stage stage) {
    controller = new Controller(stage);
    Scene scene = new Scene(view, 600, 475);
    stage.setScene(scene);
    stage.setTitle("FIRE Calc");
    stage.getIcons().add(new Image("money_with_wings.png"));
    stage.show();
    controller.displaySplashScreen();
    controller.setInitialValues();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
