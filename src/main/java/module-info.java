module dev.ryanwaugh.firecalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.ryanwaugh.firecalc to javafx.fxml;
    exports dev.ryanwaugh.firecalc;
}