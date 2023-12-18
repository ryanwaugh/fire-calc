package dev.ryanwaugh.firecalc;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private SimpleIntegerProperty age; // only integer here!
  private SimpleDoubleProperty savings;
  private SimpleDoubleProperty stockAllocation;
  private SimpleDoubleProperty bondAllocation;
  private SimpleDoubleProperty cashAllocation;
  private SimpleDoubleProperty stockReturn;
  private SimpleDoubleProperty bondReturn;
  private SimpleDoubleProperty income;
  private SimpleDoubleProperty spending;
  private SimpleDoubleProperty retirementSpend;
  private SimpleDoubleProperty targetWRRate;
  private SimpleDoubleProperty taxRate;
  private SimpleDoubleProperty fireGoal;
  private SimpleDoubleProperty fireYears;
  private ObservableList<Double> fireList;

  public Model() {
    age = new SimpleIntegerProperty();
    savings = new SimpleDoubleProperty();
    stockAllocation = new SimpleDoubleProperty();
    bondAllocation = new SimpleDoubleProperty();
    cashAllocation = new SimpleDoubleProperty();
    stockReturn = new SimpleDoubleProperty();
    bondReturn = new SimpleDoubleProperty();
    income = new SimpleDoubleProperty();
    spending = new SimpleDoubleProperty();
    retirementSpend = new SimpleDoubleProperty();
    targetWRRate = new SimpleDoubleProperty();
    taxRate = new SimpleDoubleProperty();
    fireGoal = new SimpleDoubleProperty();
    fireYears = new SimpleDoubleProperty();
    fireList = FXCollections.observableArrayList();
  }
  public int getAge() {
    return age.get();
  }
  public void setAge(int age) {
    this.age.set(age);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public SimpleIntegerProperty ageProperty() {
    return age;
  }
  public double getSavings() {
    return savings.get();
  }
  public void setSavings(double savings) {
    this.savings.set(savings);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public SimpleDoubleProperty savingsProperty() {
    return savings;
  }
  public double getStockAllocation() {
    return stockAllocation.get();
  }
  public void setStockAllocation(double stockAllocation) {
    this.stockAllocation.set(stockAllocation);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public SimpleDoubleProperty stockAllocationProperty() {
    return stockAllocation;
  }
  public SimpleDoubleProperty bondAllocationProperty() {
    return bondAllocation;
  }
  public SimpleDoubleProperty cashAllocationProperty() {
    return cashAllocation;
  }
  public SimpleDoubleProperty stockReturnProperty() {
    return stockAllocation;
  }
  public SimpleDoubleProperty bondReturnProperty() {
    return bondAllocation;
  }
  public double getBondAllocation() {
    return bondAllocation.get();
  }
  public void setBondAllocation(double bondAllocation) {
    this.bondAllocation.set(bondAllocation);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public double getCashAllocation() {
    return cashAllocation.get();
  }
  public void setCashAllocation(double cashAllocation) {
    this.cashAllocation.set(cashAllocation);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public double getStockReturn() {
    return stockReturn.get();
  }
  public void setStockReturn(double stockReturn) {
    this.stockReturn.set(stockReturn);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public double getBondReturn() {
    return bondReturn.get();
  }
  public void setBondReturn(double bondReturn) {
    this.bondReturn.set(bondReturn);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public double getIncome() {
    return income.get();
  }
  public void setIncome(double income) {
    this.income.set(income);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public SimpleDoubleProperty incomeProperty() {
    return income;
  }
  public SimpleDoubleProperty spendingProperty() {
    return spending;
  }
  public double getSpending() {
    return spending.get();
  }
  public void setSpending(double spending) {
    this.spending.set(spending);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public double getRetirementSpend() {
    return retirementSpend.get();
  }
  public SimpleDoubleProperty retirementSpendProperty() {
    return retirementSpend;
  }
  public void setRetirementSpend(double retirementSpend) {
    this.retirementSpend.set(retirementSpend);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public SimpleDoubleProperty targetWRRateProperty() {
    return targetWRRate;
  }
  public double getTargetWR() {
    return targetWRRate.get();
  }
  public void setTargetWRRate(double targetWRRate) {
    this.targetWRRate.set(targetWRRate);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }
  public SimpleDoubleProperty taxRateProperty() {
    return taxRate;
  }
  public double getTaxRate() {
    return taxRate.get();
  }
  public void setTaxRate(double taxRate) {
    this.taxRate.set(taxRate);
//    setFireCalculations();
//    Main.view.chart.updateChartData();
  }

  public SimpleDoubleProperty fireGoalProperty() {
    return fireGoal;
  }
  public double getFireGoal() {
    return fireGoal.get();
  }

  public void setFireGoal(double fireGoal) {
    this.fireGoal.set(fireGoal);
  }
  public void setFireYears(double fireYears) {
    this.fireYears.set(fireYears);
  }

  public SimpleDoubleProperty fireYearsProperty() {
    return fireYears;
  }

  public double getFireYears() {
    return fireYears.get();
  }
  public ObservableList<Double> getFireArray() {
    return fireList;
  }
  public void setFireArray(List<Double> fireList) {
    this.fireList.setAll(fireList);
  }
  public void setFireCalculations() {
    double total = getSavings();
    double goal = (getRetirementSpend() / getTargetWR() * 100) * (1 + getTaxRate()/100);
    double savingsPerYear = getIncome() - getSpending();

    double savingsRate = (getStockAllocation()/100 * getStockReturn()/100) +
      (getBondAllocation()/100 * getBondReturn()/100) +
      (getCashAllocation()/100*0);

    double years = 0;
    List<Double> fireList = new ArrayList<>();
    while (total < goal) {
      //System.out.println("year " + years + " - savings: $" + total);
      total = (total * (1 + savingsRate)) + savingsPerYear;
      years += 1;
      fireList.add(total);
    }
//    System.out.println("Fire goal is " +goal);
    setFireArray(fireList);
    setFireGoal(goal);
    setFireYears(years);
  }
}
