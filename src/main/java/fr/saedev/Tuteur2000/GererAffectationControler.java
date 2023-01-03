package fr.saedev.Tuteur2000;

import fr.saedev.dev.CalculAlgorithme;
import fr.saedev.dev.objects.Affectation;
import fr.saedev.dev.objects.Etablissement;
import fr.saedev.dev.objects.Personne;
import fr.saedev.dev.utils.Graph;
import fr.saedev.dev.utils.ParseJson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;


public class GererAffectationControler {
    @FXML
    Button buRetour;
    @FXML
    Button buLancerAlgo;
    @FXML
    Button buOkFiltre;
    @FXML
    Button buSupprFiltre;
    @FXML
    Button buAjouter;
    @FXML
    ListView listD;
    @FXML
    ListView listG;
    @FXML
    ListView listFiltre;
    @FXML
    ChoiceBox categorieCb;
    @FXML
    ChoiceBox operateurCb;
    @FXML
    TextField textFieldFiltre;
    @FXML
    Button buSpprAff;

    String tuteurSelected;
    String tutoreSelected;

    public void initialize() {
        System.out.println("Initialisation...");

        categorieCb.getItems().add("Moyenne");
        categorieCb.getItems().add("Abscence");
        categorieCb.getItems().add("Annee");
        categorieCb.getItems().add("Motivation");

        operateurCb.getItems().add("=");
        operateurCb.getItems().add(">=");
        operateurCb.getItems().add("<=");

        listFiltre.getItems().addAll();

        //Ajout des Tuteurs dans la liste de Gauche

        ArrayList<Personne> listTuteurRaw = MenuPrincipal.getIut().getTuteur();
        ArrayList<String> listTuteur = new ArrayList<>();
        for (Personne p: listTuteurRaw) {
            listTuteur.add(p.getStringName());
        }
        listG.getItems().add("Liste des Tuteurs.");
        Collections.sort(listTuteur);
        listG.getItems().addAll(listTuteur);

        //Ajout des Turorés dans la liste de Droite

        ArrayList<Personne> listTutoreRaw = MenuPrincipal.getIut().getTutore();
        ArrayList<String> listTutore = new ArrayList<>();
        for (Personne p: listTutoreRaw) {
            listTutore.add(p.getStringName());
        }
        listD.getItems().add("Liste des Tutorés");
        Collections.sort(listTutore);
        listD.getItems().addAll(listTutore);

        listD.getItems().addAll();
        listG.getItems().addAll();



        class ListChangeListenerFiltre implements javafx.collections.ListChangeListener<String>{

            @Override
            public void onChanged(Change<? extends String> change) {


            }
        }
        listFiltre.getSelectionModel().getSelectedItems().addListener(new ListChangeListenerFiltre());

        class ListChangeListenerListGaucheTuteur implements javafx.collections.ListChangeListener<String>{

            @Override
            public void onChanged(Change<? extends String> change) {
                if(change.getList().get(0).equals("Liste des Tuteurs.")){
                    System.out.println("DoNothings");
                }
                else{
                    //System.out.println(change.getList().get(0));
                    tuteurSelected = change.getList().get(0);
                    System.out.println(tuteurSelected+"selection réussi");
                }

            }
        }
        listG.getSelectionModel().getSelectedItems().addListener(new ListChangeListenerListGaucheTuteur());

        class ListChangeListenerListDroiteTutore implements  javafx.collections.ListChangeListener<String>{
            @Override
            public  void onChanged(Change<? extends  String> change){
                if(change.getList().get(0).equals("Liste des Tutorés")){
                    System.out.println("DoNothingD");
                }
                else {
                    //System.out.println(change.getList().get(0));
                    tutoreSelected = change.getList().get(0);
                    System.out.println(tutoreSelected + "selection réussi");
                }


            }
        }
        listD.getSelectionModel().getSelectedItems().addListener(new ListChangeListenerListDroiteTutore());
    }





    public  void BuLancerAlgo(){

        Etablissement iut = MenuPrincipal.getIut();
        iut.clearCouples();
        for(int i = 0; i<iut.getPersonne().size(); i++){
            iut.getPersonne().get(i).setPoints(CalculAlgorithme.getTotal(iut.getPersonne().get(i)));
        }

        Graph graphe = new Graph(iut.getTuteur(), iut.getTutore());
        graphe.calculAffectation(iut);

        ParseJson.saveToJson(iut.getCouples());
    }

    public  void BuRetour(){
        Stage stage = (Stage) buRetour.getScene().getWindow();
        //Action à effectuer avant
        stage.close();
    }

    public void refreshFiltre(){
        for(int k = 0;k <=listFiltre.getItems().size();k++){
            if(listFiltre.getItems().isEmpty()){
                System.out.println("Rien ne se passe");
            }

            //Faire action filtre sur les listes.
            System.out.println("CHECK FILTRE");

        }
    }

    public void BuSupprFiltre(){
        //Actualisation du filtre des listes.
        if(listFiltre.getItems().size() == 0){
            System.out.println("Rien ne se passe");

        }
        else{
            System.out.println("SUPPRESSION DU FILTRE EN SELECTION");
            listFiltre.getItems().remove(listFiltre.getItems().size()-1);
            //Refresh Filtre;
            refreshFiltre();
        }


    }

    public void BuOkFiltre(ActionEvent actionEvent) {
        String categorie =(String) categorieCb.getValue();
        String operateur =(String) operateurCb.getValue();
        String argument =textFieldFiltre.getText();
        String filtreActu = null;


        for (int i = 0; i < listFiltre.getItems().size(); i++) {
            filtreActu =(String) listFiltre.getItems().get(i);
        }

        if(!categorie.isBlank()&&!operateur.isBlank()&&!argument.isBlank()&&!categorie.isEmpty()&&!operateur.isEmpty()
        &&!argument.isEmpty()){
            String filtre = categorie +" "+ operateur +" "+ argument;
            listFiltre.getItems().add(filtre);
            System.out.println("AJOUT DU FILTRE");
            refreshFiltre();
        }


    }

    public void popUpErreurNonAffectableDejaAff(String Etu){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Affectation Impossible");
        alert.setHeaderText(Etu+" est déja affecté à un étudiant");
        alert.showAndWait();
    }


    public void popUpConfirmationBienAffect(Personne p1,Personne p2){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Affectation");
        alert.setHeaderText("Affectation réussite");
        alert.setContentText(p1.getStringName()+" a bien été affecté à "+p2.getStringName());
        alert.showAndWait();
    }

    public  void BuAjouter(ActionEvent actionEvent) {
        Personne tutore = MenuPrincipal.getIut().getSpecifyPersonne(tutoreSelected);
        Personne tuteur = MenuPrincipal.getIut().getSpecifyPersonne(tuteurSelected);
        boolean tuteurAff = false;
        boolean tutoreAff = false;

        for (Affectation a: MenuPrincipal.getIut().getCouples()) {
            if(a.getTuteur().equals(tuteur)){
                tuteurAff=true;
            }
            else {
                System.out.println("NonAff");
            }

        }

        for (Affectation a: MenuPrincipal.getIut().getCouples()) {
            if(a.getTutoré().equals(tutore)){
                tutoreAff=true;
            }
            else {
                System.out.println("NonAff");
            }

        }
        if (tuteurAff==true||tutoreSelected==null||tutoreAff==true||tuteurSelected==null){
            if(tuteurAff==true){
                popUpErreurNonAffectableDejaAff(tuteurSelected);
            }
            else {
                popUpErreurNonAffectableDejaAff(tutoreSelected);
            }
        }
        else {
            System.out.println(tuteurSelected+" AFFECTE A "+tutoreSelected);
            Affectation affectation = new Affectation(tuteur,tutore);
            MenuPrincipal.getIut().getCouples().add(affectation);
            popUpConfirmationBienAffect(tuteur,tutore);
        }

        //System.out.println("AJOUT DE LAFFECTATION DES DEUX ELEMENTS SI PAS DAFFECTATION");
    }


    public void popUpConfirmationSupprAff(Personne p,ArrayList<Affectation> aff){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Suppression");
        alert.setHeaderText("Vous allez supprimer une affectation êtes vous sûr ?");
        alert.setContentText(p.getStringName()+" est affecté");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            System.out.println("Suppresion de l'affectation");
            for (Affectation a:MenuPrincipal.getIut().getCouples()) {
                if(a.getTuteur().equals(p)||a.getTutoré().equals(p)){
                    MenuPrincipal.getIut().getCouples().remove(a);
                }
            }
        }
        if(alert.getResult()==ButtonType.CANCEL){
            System.out.println("RienNeSePasse");
        }
    }

    public void popUpConfirmationSupprAff(Personne p,Personne p2,ArrayList<Affectation> aff){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Suppression");
        alert.setHeaderText("Vous allez supprimer une affectation êtes vous sûr ?");
        alert.setContentText(p.getStringName()+" est affecté à "+p2.getStringName());
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            System.out.println("Suppresion de l'affectation");
            for (Affectation a:MenuPrincipal.getIut().getCouples()) {
                if(a.getTuteur().equals(p)||a.getTutoré().equals(p2)){
                    MenuPrincipal.getIut().getCouples().remove(a);
                }
            }
        }
        if(alert.getResult()==ButtonType.CANCEL){
            System.out.println("RienNeSePasse");
        }
    }


    public void buSpprAff(ActionEvent actionEvent) {
        Personne tuteur=null;
        Personne tutore=null;
        ArrayList<Affectation> affect = MenuPrincipal.getIut().getCouples();
        if(tutoreSelected != null){
            tutore=MenuPrincipal.getIut().getSpecifyPersonne(tutoreSelected);
        }
        if(tuteurSelected != null){
            tuteur=MenuPrincipal.getIut().getSpecifyPersonne(tuteurSelected);
        }

        boolean affTe=false;
        boolean affTr=false;
        if(tuteur!=null){
            for (Affectation a:affect) {
                if(a.getTuteur().equals(tuteur)){
                    affTr=true;
                }
            }
        }
        if (tutore!=null){
            for (Affectation a:affect) {
                if(a.getTutoré().equals(tutore)){
                    affTe=true;
                }
            }
        }
        if(affTe==true&&affTr==true){
            popUpConfirmationSupprAff(tuteur,tutore,affect);
        } else if (affTr==true) {
            System.out.println("SUPPR"+tuteurSelected);
            popUpConfirmationSupprAff(tuteur,affect);
        } else if (affTe==true) {
            System.out.println("SUPPR"+tutoreSelected);
            popUpConfirmationSupprAff(tutore,affect);
        }
    }


}