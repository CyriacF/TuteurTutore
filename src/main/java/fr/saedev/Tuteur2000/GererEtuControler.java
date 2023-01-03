package fr.saedev.Tuteur2000;

import fr.saedev.dev.CalculAlgorithme;
import fr.saedev.dev.objects.Affectation;
import fr.saedev.dev.objects.Etablissement;
import fr.saedev.dev.objects.Personne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GererEtuControler {
    private String selected;
    @FXML
    TextField textAreaEtuSelectioner;
    @FXML
    Button BuRetour;
    @FXML
    Button BuAjouter;
    @FXML
    Button BuModifier;
    @FXML
    Button BuSupprimer;
    @FXML
    ListView listEtudiant;
    @FXML
    RadioButton rdTuteur;
    @FXML
    RadioButton rdTutore;
    @FXML
    TextField textAreaName;
    @FXML
    TextField textAreaMoyenne;
    @FXML
    TextField textAreaAbs;
    @FXML
    TextField textAreaPrenom;
    @FXML
    TextField textAreaMotivation;
    @FXML
    TextField textAreaAnnee;

    public void initialize() {
        System.out.println("Initialisation...");
        ArrayList<Personne> etudiantRaw = MenuPrincipal.getIut().getPersonne();
        ArrayList<String> etudiant = new ArrayList<>();

        for (Personne p:etudiantRaw) {
            etudiant.add(p.getStringName());
        }
        Collections.sort(etudiant);

        listEtudiant.getItems().clear();
        listEtudiant.getItems().addAll(etudiant);
    //--------------------------------------------------------------------
        class ListChangeListener implements javafx.collections.ListChangeListener<String>{
            @Override
            public void onChanged(Change<? extends String> change) {
                if(change.getList().size() == 0){
                    textAreaEtuSelectioner.setText("");
                }else{
                    textAreaEtuSelectioner.setText(change.getList().get(0));
                }
                Personne personne;
                personne = MenuPrincipal.getIut().getSpecifyPersonne(change.getList().get(0));
                selected = change.getList().get(0);
                textAreaName.setText(personne.getNom());
                textAreaAbs.setText(""+personne.getAbsences());
                textAreaAnnee.setText(""+personne.getAnnee());
                textAreaMoyenne.setText(""+personne.getMoyenne());
                textAreaPrenom.setText(personne.getPrenom());
                textAreaMotivation.setText(""+personne.getMotivation());
                if(personne.isTuteur()){
                    rdTutore.setSelected(false);
                    rdTuteur.setSelected(true);
                }
                else {
                    rdTuteur.setSelected(false);
                    rdTutore.setSelected(true);
                }
            }
        }
        listEtudiant.getSelectionModel().getSelectedItems().addListener(new ListChangeListener());
    }


    public void ConfirmationAlerteSuppressionAffectation(Personne personneSuppr,Personne personneAffecté){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ATTENTION Suppression d'un édutiant");
        alert.setHeaderText("Vous allez supprimer un étudiant affecté !!");
        alert.setContentText(personneSuppr.getStringName()+ " est affecté à "+personneAffecté.getStringName()+"\n" +
                "Vous allez donc supprimer l'affectation êtes vous sûr ?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            System.out.println("AARFOU");
            MenuPrincipal.getIut().removePersonne(personneSuppr);
            for (Affectation a:MenuPrincipal.getIut().getCouples()) {
                if(a.getTuteur().equals(personneAffecté)||a.getTutoré().equals(personneAffecté)){
                    MenuPrincipal.getIut().getCouples().remove(a);
                }
            }
            initialize();
        }
        if(alert.getResult() == ButtonType.CANCEL){
        }
        clearInformationsEtuArea();
    }

    public void BuSupprimer(){
        Etablissement iut= MenuPrincipal.getIut();
        ArrayList<Affectation> affectes = iut.getCouples();
        textAreaEtuSelectioner.setText(selected);
        Personne per = MenuPrincipal.getIut().getSpecifyPersonne(selected);
        if(MenuPrincipal.getIut().tutoreEstAff(per)|| MenuPrincipal.getIut().tuteurEstAff(per)){
            if(per.isTuteur()){
                if(MenuPrincipal.getIut().tuteurEstAff(per)){

                    if(MenuPrincipal.getIut().tuteurEstAff(per)){
                        Personne etuImplique=null;
                        for (Affectation a:affectes) {
                            if(a.getTuteur().equals(per)){
                                etuImplique=a.getTutoré();
                            }
                        }
                        ConfirmationAlerteSuppressionAffectation(per,etuImplique);

                    }
                }
                /*else {
                    System.out.println("AARF");
                    MenuPrincipal.getIut().removePersonne(per);
                    initialize();
                }*/
            }
            if(per.isTutore()){

                if(MenuPrincipal.getIut().tutoreEstAff(per)){
                    Personne etuImplique=null;
                    for (Affectation a:affectes) {
                        if(a.getTutoré().equals(per)){
                            etuImplique =a.getTuteur();
                        }
                    }
                    ConfirmationAlerteSuppressionAffectation(per,etuImplique);
                }
                /*else {
                    System.out.println("AARF");
                    MenuPrincipal.getIut().removePersonne(per);
                    initialize();
                }*/
            }
        }
        else {
            System.out.println("AARF");
            MenuPrincipal.getIut().removePersonne(per);
            initialize();
            clearInformationsEtuArea();
        }
    }

    public void BuModifier(){
        String nom;
        String prenom;
        int abscence;
        int annee;
        int motivation;
        double moyenne;
        boolean tutore = false;
        boolean tuteur = false;

        nom = textAreaName.getText();
        prenom = textAreaPrenom.getText();
        abscence = Integer.parseInt(textAreaAbs.getText());
        moyenne = Double.parseDouble(textAreaMoyenne.getText());
        motivation = Integer.parseInt(textAreaMotivation.getText());
        annee = Integer.parseInt(textAreaAnnee.getText());

        if(rdTutore.isSelected()){
            tutore = true;
        }
        else {
            tuteur = true;
        }
        textAreaEtuSelectioner.setText(selected);
        Personne per = MenuPrincipal.getIut().getSpecifyPersonne(selected);
        per.setNom(nom);
        per.setPrenom(prenom);
        per.setAbsences(abscence);
        per.setAnnee(annee);
        per.setMotivation(motivation);
        per.setMoyenne(moyenne);
        per.setTuteur(tuteur);
        per.setTutore(tutore);
        per.setPoints(CalculAlgorithme.getTotal(per));
        initialize();
    }

    public void popUpInformationEtuExisteDeja(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ajouter un etudiant");
        alert.setHeaderText("Impossible d'ajouter l'étudiant.");
        alert.setContentText("L'étudiant existe déja !");
        alert.showAndWait();
    }

    public void popUpInformationEtuAjouter(Personne p){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout étudiant");
        alert.setHeaderText(p.getStringName()+" a bien été ajouté");
        alert.showAndWait();
    }

    public void clearInformationsEtuArea(){
        textAreaMoyenne.clear();
        textAreaAbs.clear();
        textAreaName.clear();
        textAreaAnnee.clear();
        textAreaMotivation.clear();
        textAreaPrenom.clear();
        textAreaEtuSelectioner.clear();
        rdTutore.setSelected(false);
        rdTuteur.setSelected(false);
        initialize();
    }






    public void BuAjouter() throws IOException {
        String nom;
        String prenom;
        int abscence;
        int annee;
        int motivation;
        double moyenne;
        boolean tutore = false;
        boolean tuteur = false;

        nom = textAreaName.getText();
        prenom = textAreaPrenom.getText();
        abscence = Integer.parseInt(textAreaAbs.getText());
        moyenne = Double.parseDouble(textAreaMoyenne.getText());
        motivation = Integer.parseInt(textAreaMotivation.getText());
        annee = Integer.parseInt(textAreaAnnee.getText());

        if(rdTutore.isSelected()){
            tutore = true;
        }
        else {
            tuteur = true;
        }

        Personne etudiantEnAjout = new Personne(nom, prenom, moyenne, motivation, annee, abscence, tuteur, tutore);
        boolean nEstPasPresent =true;

        for (Personne p:MenuPrincipal.getIut().getPersonne()){
            if(p.getStringName().equals(etudiantEnAjout.getStringName())){
                nEstPasPresent=false;
                System.out.println("existedeja");
            }
        }

        if (nEstPasPresent){
            MenuPrincipal.getIut().addPersonne(etudiantEnAjout);
            initialize();
            popUpInformationEtuAjouter(etudiantEnAjout);
            clearInformationsEtuArea();
            System.out.println("AJOUT ETU");
        }
        else {
            clearInformationsEtuArea();
            popUpInformationEtuExisteDeja();
        }




    }

    public void rdTuteurAction(){
        rdTutore.setSelected(false);
    }

    public void rdTutoreAction(){
        rdTuteur.setSelected(false);

    }

    public void BuRetour(){
        Stage stage = (Stage) BuRetour.getScene().getWindow();
        //Ajouter code avant fermeture
        stage.close();
    }

    public void TextAreaEtuSelectioner(ActionEvent actionEvent) {
    }
}


