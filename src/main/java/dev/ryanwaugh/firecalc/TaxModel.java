package dev.ryanwaugh.firecalc;

import javafx.beans.property.SimpleDoubleProperty;

public class TaxModel {
  private SimpleDoubleProperty grossIncome;
  private SimpleDoubleProperty netIncome;

  private final Double[][] federalBrackets = {
    { 0.0, 50197.0, 0.15}, // Bracket[start], Bracket[end], Tax Rate (%)
    { 50198.0, 100392.0, 0.205},
    { 100393.0, 155625.0, 0.26},
    { 155626.0, 221708.0, 0.29},
    { 221709.0, Double.MAX_VALUE, 0.33}
  };
  private final Double[][] provincialBrackets = {
    { 0.0, 44887.0, 0.094}, // Bracket[start], Bracket[end], Tax Rate (%)
    { 44888.0, 89775.0, 0.1482},
    { 89776.0, 145995.0, 0.1652},
    { 145996.0, 166280.0, 0.1784},
    { 166280.0, Double.MAX_VALUE, 0.203}
  };

  public TaxModel() {
    grossIncome = new SimpleDoubleProperty();
    netIncome = new SimpleDoubleProperty();
  }

  public Double calculateProvincialTax() {
    double tax = 0;
    double incomeLeft = getGrossIncome();
    for(Double[] bracket : provincialBrackets) {
      double start = bracket[0];
      double end = bracket[1];
      double rate = bracket[2];

      if (incomeLeft <= 0) {
        break;
      }

      double taxableAmount = Math.min(end - start, incomeLeft);
      tax += taxableAmount * rate;
      incomeLeft -= taxableAmount;
    }
    return tax;
  }
  public Double calculateFederalTax() {
    double tax = 0;
    double incomeLeft = getGrossIncome();
    for(Double[] bracket : federalBrackets) {
      double start = bracket[0];
      double end = bracket[1];
      double rate = bracket[2];

      if (incomeLeft <= 0) {
        break;
      }

      double taxableAmount = Math.min(end - start, incomeLeft);
      tax += taxableAmount * rate;
      incomeLeft -= taxableAmount;
    }
    return tax;
  }

  public void calculateNetIncome() {
    double federalTax = calculateFederalTax();
    double provincialTax = calculateProvincialTax();

    double net = getGrossIncome() - federalTax - provincialTax;
    setNetIncome(net);
  }

  public void setGrossIncome(Double grossIncome) {
    this.grossIncome.set(grossIncome);
  }
  public void setNetIncome(Double netIncome) {
    this.netIncome.set(netIncome);
  }

  public Double getGrossIncome() {
    return grossIncome.get();
  }
  public Double getNetIncome() {
    return netIncome.get();
  }

}