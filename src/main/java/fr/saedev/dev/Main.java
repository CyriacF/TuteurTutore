package fr.saedev.dev;

import fr.saedev.dev.objects.Etablissement;
import fr.saedev.dev.objects.Personne;
import fr.saedev.dev.utils.Graph;
import fr.saedev.dev.utils.ParseJson;

import java.io.File;
import java.io.IOException;

public class Main {
    private static Etablissement iut = new Etablissement("IUT A");
    public static Etablissement getIUT(){
        return iut;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 1){
            String[] arg = args[0].split("=");
            if(arg[0].equals("json")){
                System.out.println("Lancement de la fonction avec un JSON personnalisé");
                File f = new File(arg[1]);
                String content = ParseJson.getFileContent(f.getAbsolutePath());
                iut = new Etablissement("IUT A");
                Personne[] per = ParseJson.TextToTabPer(content);
                for(Personne p : per){
                    System.out.println("Importation de l'étudiant : " + p.getPrenom());
                    iut.addPersonne(p);
                }
                ParseJson.saveToJson(iut.getPersonne());
                for(int i = 0; i<iut.getPersonne().size(); i++){
                    iut.getPersonne().get(i).setPoints(CalculAlgorithme.getTotal(iut.getPersonne().get(i)));
                }

                Graph graphe = new Graph(iut.getTuteur(), iut.getTutore());
                graphe.calculAffectation(iut);
                ParseJson.saveToJson(iut.getCouples());
            }else{
                System.out.println("Argument invalide");
            }
        }else if (args.length>1){
            System.out.println("Erreur trop d'arguments passé en paramètre");
            return;
        }else{
            System.out.println("Lancement du scénario de base");
            Personne gautier = new Personne("Louvier", "Gautier", 12.34, 5, 1, 2, false, true);
            Personne matteo = new Personne("Dupont", "Matteo", 7.9, 8, 1, 7, false, true);
            Personne loick = new Personne("Feur", "Loick", 13.5, 4, 1, 1, false, true);
            Personne timo = new Personne("Stiti", "Timothé", 10.9, 6, 2, 4, true, false);
            Personne mattieu = new Personne("Durand", "Mattieu", 14.3, 7, 3, 2, true, false);
            Personne julien = new Personne("Zimba", "Julien", 13.9, 3, 2, 4, true, false);
            Personne thomas = new Personne("Solaire", "Thomas", 15.9, 7, 3, 2, true, false);
            Personne titouan = new Personne("Delbart", "Titouan", 6.9, 3, 1, 4, false, true);
            Personne celine = new Personne("Rocmont", "Céline", 14.5, 9, 1, 2, false, true);

            iut.addPersonne(gautier);
            iut.addPersonne(matteo);
            iut.addPersonne(loick);
            iut.addPersonne(timo);
            iut.addPersonne(mattieu);
            iut.addPersonne(julien);
            iut.addPersonne(thomas);
            iut.addPersonne(titouan);
            iut.addPersonne(celine);

            ParseJson.saveToJson(iut.getPersonne());

            for(int i = 0; i<iut.getPersonne().size(); i++){
                iut.getPersonne().get(i).setPoints(CalculAlgorithme.getTotal(iut.getPersonne().get(i)));
            }

            Graph graphe = new Graph(iut.getTuteur(), iut.getTutore());
            graphe.calculAffectation(iut);

            ParseJson.saveToJson(iut.getCouples());
        }
    }

}
