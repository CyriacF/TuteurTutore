package fr.saedev.Tuteur2000;


import fr.saedev.dev.objects.Affectation;
import fr.saedev.dev.objects.Etablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class MenuPrincipalControler{
    @FXML
    public ListView listAffectation;
    @FXML
    private Button BuGererEtudiant;
    @FXML
    private  Button BuGererAffectations;
    @FXML
    private  Button BuSauvegarder;
    @FXML
    private ListView ListAffect;

    public void initialize() {
        System.out.println("Initialisation...");
        Etablissement iut = MenuPrincipal.getIut();
        ArrayList<Affectation> couple = iut.getCouples();
        listAffectation.getItems().addAll(couple);
    }

    public void pressedButtonEtu(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("gerer_etu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Gérer les informations des étudiants © Tutorat 2000 IUT-A Lille");
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void pressedButtonAff(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("gerer_affectation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Gérer les affectations © Tutorat 2000 IUT-A Lille");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void pressedButtonMAJ(ActionEvent actionEvent) {
        Etablissement iut = MenuPrincipal.getIut();

        ArrayList<Affectation> coupleRAW = iut.getCouples();
        ArrayList<String> couples = new ArrayList<>();



        for (Affectation a:coupleRAW) {
            couples.add(a.getTuteur().getPrenom() + "< == >" + a.getTutoré().getPrenom());
        }
        //Fix de la dupli :
        listAffectation.getItems().clear();
        listAffectation.getItems().addAll(couples);
        System.out.println("SUCCES");
    }
}

