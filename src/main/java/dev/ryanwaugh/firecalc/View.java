package dev.ryanwaugh.firecalc;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;

public class View extends Pane {
  BorderPane root;
  TextFormatter<Double> textFormatter;
  ChartView chart;
  TaxView taxView;
  public View() {
    root = new BorderPane();
    textFormatter = new TextFormatter<>(new DoubleStringConverter(), 0.0);
    this.getChildren().add(root);
    chart = new ChartView();
    taxView = new TaxView();
    taxView.root.setVisible(false);
    chart.chart.setVisible(false);

    ChangeListener<Number> listen = (observableValue, aDouble, t1) -> {
      Main.model.setFireCalculations();
      if (chart.chart.isVisible()) {
        chart.updateChartData();
//        System.out.println("Updating chart from view by model property");
      }
    };
    Main.model.ageProperty().addListener(listen);
    Main.model.savingsProperty().addListener(listen);
    Main.model.stockAllocationProperty().addListener(listen);
    Main.model.bondAllocationProperty().addListener(listen);
    Main.model.cashAllocationProperty().addListener(listen);
    Main.model.stockReturnProperty().addListener(listen);
    Main.model.bondReturnProperty().addListener(listen);
    Main.model.incomeProperty().addListener(listen);
    Main.model.spendingProperty().addListener(listen);
    Main.model.retirementSpendProperty().addListener(listen);
    Main.model.targetWRRateProperty().addListener(listen);
    Main.model.taxRateProperty().addListener(listen);
  }

  public void loadSplashScreen() {
    ImageView splashImage = new ImageView();
    StackPane centered = new StackPane();
    centered.alignmentProperty().setValue(Pos.CENTER);
    Image loadingDisk = new Image("money_with_wings.png");
    splashImage.setImage(loadingDisk);
    splashImage.setFitHeight(this.getHeight()/2);
    splashImage.setFitWidth(this.getWidth()/2);
    centered.getChildren().add(splashImage);
    root.setCenter(centered);

    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.4), splashImage);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    fadeIn.setCycleCount(1);

    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), splashImage);
    fadeOut.setFromValue(1);
    fadeOut.setToValue(0);
    fadeOut.setCycleCount(1);

    fadeIn.play();
    fadeIn.setOnFinished(e -> fadeOut.play());
    fadeOut.setOnFinished(e -> init());
  }

  public void loadAboutMenu() {
    Stage aboutStage = new Stage();

    VBox contents = new VBox();
    contents.setPadding(new Insets(5));
    contents.setAlignment(Pos.CENTER);
    ImageView logo = new ImageView("money_with_wings.png");
    logo.setFitHeight(200);
    logo.setFitWidth(200);

    Label name = new Label("FIRE Calc");
    name.setStyle("-fx-text-fill: red;");

    Label author = new Label("Author: Ryan Waugh");
    author.setStyle("-fx-text-fill: green;");

    contents.getChildren().addAll(logo, name, author);

    Scene aboutScene = new Scene(contents, 350, 250);
    aboutStage.initModality(Modality.APPLICATION_MODAL);
    aboutStage.setTitle("About - FIRE Calc");
    aboutStage.setScene(aboutScene);
    aboutStage.showAndWait();
  }

  public void loadHelpMenu() {
    Stage helpStage = new Stage();

    VBox helpContents = new VBox();
    helpContents.setPadding(new Insets(5));
    helpContents.setAlignment(Pos.BASELINE_LEFT);
    Label helpGreet = new Label("Usage:");
    Label wr = new Label("- Target Withdraw Rate must be a value < 10%");
    Label allocations = new Label("- Stock, Bond, & Cash allocations must add to 100%");
    Label netIncomeLabel = new Label("- Click Menu Items to Toggle Chart & Tax Calc");
    helpContents.getChildren().addAll(helpGreet, wr, allocations, netIncomeLabel);

    Scene aboutScene = new Scene(helpContents, 350, 250);
    helpStage.initModality(Modality.APPLICATION_MODAL);
    helpStage.setTitle("Help - FIRE Calc");
    helpStage.setScene(aboutScene);
    helpStage.showAndWait();
  }

  private void init() {
    root.setTop(initMenuBar());
    root.setCenter(initStartingFields());
  }

  private MenuBar initMenuBar() {
    MenuBar menuBar = new MenuBar();
    menuBar.prefWidthProperty().bind(this.widthProperty());

    MenuItem helpScreen = new MenuItem("Help");
    MenuItem aboutScreen = new MenuItem("About");
    Menu helpMenu = new Menu("Help Menu");
    helpMenu.getItems().addAll(helpScreen, aboutScreen);

    MenuItem chartScreen = new MenuItem("Main FI Chart");
    Menu charting = new Menu("Charting");
    charting.getItems().add(chartScreen);


    helpScreen.setOnAction(e -> Main.controller.displayHelpInfo());
    aboutScreen.setOnAction(e -> Main.controller.displayAboutInfo());
    chartScreen.setOnAction(e -> {
      if (chart.chart.isVisible()) {
        Main.controller.handleResize();
        root.setLeft(null);
        chartScreen.setGraphic(null);
      } else {
        Main.controller.handleResize();
        root.setLeft(chart.chart);
        chartScreen.setGraphic(new Label("✅"));
      }
      // toggle chart visibility
      chart.chart.setVisible(!chart.chart.isVisible());
    });
    Menu tax = new Menu("Tax Calc");
    MenuItem taxScreen = new MenuItem("Tax Widget");
    tax.getItems().add(taxScreen);
    taxScreen.setOnAction(e -> {
      if (taxView.root.isVisible()) {
        Main.controller.handleResize();
        root.setRight(null);
        taxScreen.setGraphic(null);
      } else {
        Main.controller.handleResize();
        root.setRight(taxView.root);
        taxScreen.setGraphic(new Label("✅"));
      }
      // toggle chart visibility
      taxView.root.setVisible(!taxView.root.isVisible());
    });

    menuBar.getMenus().addAll(charting, tax, helpMenu);
    return menuBar;
  }

  private GridPane initStartingFields() {
    GridPane grid = new GridPane();
    grid.setVgap(8);
    grid.setHgap(8);
    grid.setPadding(new Insets(15));
    // Age
    Label startAgeLabel = new Label("Age");
    TextField startAge = new TextField("32");
    startAge.setPrefWidth(50);
    // Start Savings
    Label startSavingsLabel = new Label("Savings");
    TextField startSavings = new TextField("25000");
    startSavings.setPrefWidth(50);
    // Stock/Bond/Cash Allocation
    Label allocationLabel = new Label("Stock/Bond/Cash %");
    TextField stockAllocation = new TextField("80");
    stockAllocation.setPrefWidth(50);
    TextField bondAllocation = new TextField("18");
    bondAllocation.setPrefWidth(50);
    TextField cashAllocation = new TextField("2");
    cashAllocation.setPrefWidth(50);
    // Stock Returns %
    Label stockReturnsLabel = new Label("Stock/Bond Returns %");
    TextField stockReturns = new TextField("8.1");
    stockReturns.setPrefWidth(50);
    TextField bondReturns = new TextField("2.4");
    bondReturns.setPrefWidth(50);
    // Income(-tax)
    Label incomeLabel = new Label("Income(-tax)");
    TextField income = new TextField("60000");
    income.setPrefWidth(50);
    // Spending
    Label spendingLabel = new Label("Spending");
    TextField spending = new TextField("45000");
    spending.setPrefWidth(50);
    // Retirement Spending/year
    Label retirementSpendingLabel = new Label("Retirement Spending");
    TextField retirementSpending = new TextField("40000");
    retirementSpending.setPrefWidth(50);
    // Target Withdraw Rate
    Label targetWRLabel = new Label("Target WR (%)");
    TextField targetWR = new TextField("4");
    targetWR.setPrefWidth(50);
    // Tax Rate of Target WR Amount
    Label taxRateLabel = new Label("Avg Tax Rate (%)");
    TextField taxRate = new TextField("7");
    taxRate.setPrefWidth(50);
    // Calculated FIRE Goal Amount
    Label targetFIRELabel = new Label("FIRE Target");
    TextField targetFIRE = new TextField();
    targetFIRE.setPrefWidth(50);
    targetFIRE.setText(String.valueOf(Main.model.getFireGoal()));
    // Calculated FIRE Years
    Label fireYearsLabels = new Label("Years");
    TextField fireYears = new TextField();
    fireYears.setPrefWidth(50);
    fireYears.setText(String.valueOf(Main.model.getFireYears()));

    grid.addRow(0, startAgeLabel, startAge);
    grid.addRow(1, startSavingsLabel, startSavings);
    grid.addRow(2, allocationLabel, new HBox(stockAllocation, bondAllocation, cashAllocation));
    grid.addRow(3, stockReturnsLabel, new HBox(stockReturns, bondReturns));
    grid.addRow(4, incomeLabel, income);
    grid.addRow(5, spendingLabel, spending);
    grid.addRow(6, retirementSpendingLabel, retirementSpending);
    grid.addRow(7, targetWRLabel, targetWR);
    grid.addRow(8, taxRateLabel, taxRate);
    grid.addRow(9, targetFIRELabel, targetFIRE);
    grid.addRow(10, fireYearsLabels, fireYears);

    startAge.textProperty().addListener(e -> Main.controller.setAge(startAge.getText()));
    startSavings.textProperty().addListener(e -> Main.controller.setSavings(startSavings.getText()));
    stockAllocation.textProperty().addListener(e -> Main.controller.setStockAllocation(stockAllocation.getText()));
    bondAllocation.textProperty().addListener(e -> Main.controller.setBondAllocation(bondAllocation.getText()));
    cashAllocation.textProperty().addListener(e -> Main.controller.setCashAllocation(cashAllocation.getText()));
    stockReturns.textProperty().addListener(e -> Main.controller.setStockReturns(stockReturns.getText()));
    bondReturns.textProperty().addListener(e -> Main.controller.setBondReturns(bondReturns.getText()));
    income.textProperty().addListener(e -> Main.controller.setIncome(income.getText()));
    spending.textProperty().addListener(e -> Main.controller.setSpending(spending.getText()));
    retirementSpending.textProperty().addListener(e -> Main.controller.setRetirementSpending(retirementSpending.getText()));
    targetWR.textProperty().addListener(e -> Main.controller.setTargetWR(targetWR.getText()));
    taxRate.textProperty().addListener(e -> Main.controller.setTaxRate(taxRate.getText()));

    Main.model.fireGoalProperty().addListener(e -> targetFIRE.setText(String.valueOf(Main.model.getFireGoal())));
    Main.model.fireYearsProperty().addListener(e -> fireYears.setText(String.valueOf(Main.model.getFireYears())));

    return grid;
  }
}
