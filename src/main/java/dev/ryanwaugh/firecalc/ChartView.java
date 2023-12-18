package dev.ryanwaugh.firecalc;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class ChartView {
  NumberAxis xAxis;
  NumberAxis yAxis;

  LineChart<Number, Number> chart;

  XYChart.Series<Number, Number> series;

  public ChartView() {
    xAxis = new NumberAxis();
    yAxis = new NumberAxis();
    chart = new LineChart<>(xAxis, yAxis);
    chart.setTitle("Savings Chart");
    series = new XYChart.Series<>();
    series.setName("Age");
    updateChartData();
  }

  public void updateChartData() {
    int age = Main.model.getAge();
    ArrayList<Double> tempVals = new ArrayList<>(Main.model.getFireArray());
    series = new XYChart.Series<>();
    series.setName("Age");
    for (int i = 0; i < tempVals.size(); i++) {
      series.getData().add(new XYChart.Data<>(i + age, tempVals.get(i)));
    }

    chart.getXAxis().setAutoRanging(false);
    ((NumberAxis) chart.getXAxis()).setLowerBound(age);
    ((NumberAxis) chart.getXAxis()).setUpperBound(age+tempVals.size()+1);
    chart.getData().clear();
    chart.getData().add(series);
  }
}
