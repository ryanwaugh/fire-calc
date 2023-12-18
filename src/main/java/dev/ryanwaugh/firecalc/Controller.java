package dev.ryanwaugh.firecalc;

import javafx.stage.Stage;

public class Controller {
  Stage stage;

  public Controller(Stage stage) {
    this.stage = stage;
  }

  public void handleResize() {
    stage.sizeToScene();
  }

  public void displaySplashScreen() {
    Main.view.loadSplashScreen();
  }

  public void displayAboutInfo() {
    Main.view.loadAboutMenu();
  }

  public void displayHelpInfo() {
    Main.view.loadHelpMenu();
  }

  public void setAge(String age) {
    try {
      int val = Integer.parseInt(age.trim());
      Main.model.setAge(val);
//      System.out.println("Controller - Set age in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setSavings(String savings) {
    try {
      double val = Double.parseDouble(savings.trim());
      Main.model.setSavings(val);
//      System.out.println("Controller - Set savings in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setStockAllocation(String stocks) {
    try {
      double val = Double.parseDouble(stocks.trim());
      Main.model.setStockAllocation(val);
//      System.out.println("Controller - Set stocks in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setBondAllocation(String bonds) {
    try {
      double val = Double.parseDouble(bonds.trim());
      Main.model.setBondAllocation(val);
//      System.out.println("Controller - Set bonds in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setCashAllocation(String cash) {
    try {
      double val = Double.parseDouble(cash.trim());
      Main.model.setCashAllocation(val);
//      System.out.println("Controller - Set cash in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setStockReturns(String stockReturn) {
    try {
      double val = Double.parseDouble(stockReturn.trim());
      Main.model.setStockReturn(val);
//      System.out.println("Controller - Set stock return in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setBondReturns(String bondReturn) {
    try {
      double val = Double.parseDouble(bondReturn.trim());
      Main.model.setBondReturn(val);
//      System.out.println("Controller - Set bond return in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setIncome(String income) {
    try {
      double val = Double.parseDouble(income.trim());
      Main.model.setIncome(val);
//      System.out.println("Controller - Set income in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setSpending(String spending) {
    try {
      double val = Double.parseDouble(spending.trim());
      Main.model.setSpending(val);
//      System.out.println("Controller - Set spending in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setRetirementSpending(String retirementSpending) {
    try {
      double val = Double.parseDouble(retirementSpending.trim());
      Main.model.setRetirementSpend(val);
//      System.out.println("Controller - Set retirement spending in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setTargetWR(String targetWR) {
    try {
      double val = Double.parseDouble(targetWR.trim());
      Main.model.setTargetWRRate(val);
//      System.out.println("Controller - Set target WR in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setTaxRate(String taxRate) {
    try {
      double val = Double.parseDouble(taxRate.trim());
      Main.model.setTaxRate(val);
//      System.out.println("Controller - Set tax rate in model to " + val);
    } catch (NumberFormatException e) {
      //e.printStackTrace();
    }
  }
  public void setInitialValues() {
    Main.model.setAge(32);
    Main.model.setSavings(25000);
    Main.model.setStockAllocation(80);
    Main.model.setBondAllocation(18);
    Main.model.setCashAllocation(2);
    Main.model.setStockReturn(8.1);
    Main.model.setBondReturn(2.4);
    Main.model.setIncome(60000);
    Main.model.setSpending(45000);
    Main.model.setRetirementSpend(40000);
    Main.model.setTargetWRRate(4);
    Main.model.setTaxRate(7);
    Main.view.chart.updateChartData();
  }
}
