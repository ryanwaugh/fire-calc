package dev.ryanwaugh.firecalc;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;

public class TaxView {
  VBox root;
  DecimalFormat form;

  Slider slider;
  Label netIncomeLabel;
  Label grossIncomeLabel;
  Button sendNetToFireCalc;

  public TaxView() {
    root = new VBox();
    root.setAlignment(Pos.CENTER);
    root.setPrefWidth(150);

    form = new DecimalFormat("#");
    netIncomeLabel = new Label("Net Income $");
    grossIncomeLabel = new Label("Gross Income $");

    slider = new Slider();
    slider.setMin(0);
    slider.setMax(1000000);
    slider.setShowTickLabels(true);
    slider.setMajorTickUnit(100000);

    slider.valueProperty().addListener(e -> {
//      System.out.println("slider val is " + slider.getValue());
      grossIncomeLabel.setText("Gross Income $ " + form.format(slider.getValue()));
      Main.taxModel.setGrossIncome(slider.getValue());
      Main.taxModel.calculateNetIncome();
//      System.out.println("Net is " + Main.taxModel.getNetIncome());
      netIncomeLabel.setText("Net Income $ " + form.format(Main.taxModel.getNetIncome()));
    });

    sendNetToFireCalc = new Button("Set Net Income");
    sendNetToFireCalc.setOnMouseClicked(e -> Main.model.setIncome(Main.taxModel.getNetIncome()));

    root.getChildren().addAll(grossIncomeLabel, slider, netIncomeLabel, sendNetToFireCalc);
//    root.getChildren().addAll(grossIncomeLabel, slider, netIncomeLabel);
  }



}