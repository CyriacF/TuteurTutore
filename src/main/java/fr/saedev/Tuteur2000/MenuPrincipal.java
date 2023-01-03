package fr.saedev.Tuteur2000;

import fr.saedev.dev.objects.Affectation;
import fr.saedev.dev.objects.Etablissement;
import fr.saedev.dev.objects.Personne;
import fr.saedev.dev.utils.ParseJson;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class MenuPrincipal extends Application {
    private static Etablissement iut = new Etablissement("IUT A");
    public static Etablissement getIut(){
        return iut;
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource("menu_principale.fxml");
        if (fxmlFileUrl == null) {
            System.out.println("Impossible de charger le fichier fxml");
            System.exit(-1);
        }
        try{
            File f = new File("saves/etu.json");
            String content = ParseJson.getFileContent(f.getAbsolutePath());
            Personne[] per = ParseJson.TextToTabPer(content);
            for(Personne p : per){
                System.out.println("Importation de l'étudiant : " + p.getPrenom());
                iut.addPersonne(p);
            }
        }catch (NoSuchFileException e){
            e.printStackTrace();
        }
        /*
        try{
            File fcouple = new File("saves/couples.json");
            String contentcouple = ParseJson.getFileContent(fcouple.getAbsolutePath());
            Affectation[] aff = ParseJson.TextToTabAff(contentcouple);
            for(Affectation p : aff){
                iut.addAffectation(p);
            }
        }catch (NoSuchFileException e){
            e.printStackTrace();
        }

         */
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu Principal © Tutorat 2000 IUT-A Lille");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

