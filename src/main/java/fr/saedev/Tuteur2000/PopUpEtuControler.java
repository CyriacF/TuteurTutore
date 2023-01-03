package fr.saedev.Tuteur2000;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PopUpEtuControler {
    @FXML
    Button BuOk;
    public void initialize() {
        System.out.println("Initialisation...");
    }

    public void PressedBuOk(){
        Stage stage = (Stage) BuOk.getScene().getWindow();
        stage.close();
    }


}
