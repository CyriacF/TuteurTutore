package fr.saedev.dev;

import fr.saedev.dev.objects.Personne;

public class CalculAlgorithme {
    public static double getTotal(Personne per){
        double res = 0;
        res = res + getPointAnnee(per);
        res = res + getPointMotivation(per);
        res = res + getPointMoyenneTuteur(per);
        res = res + getAbsence(per);
        return res;
    }

    public static int getPointAnnee(Personne per){
        int annee = per.getAnnee();
        int res = 0;
        if (annee == 1){
            res = 10;
        }
        if (annee == 2){
            res = 3;
        }
        if (annee == 3){
            res = 1;
        }
        return res;
    }

    /**
     * Use algorithm to calcul the motivation
     * @param per
     * @return
     */
    public static int getPointMotivation(Personne per){
        return 10-per.getMotivation();
    }

    public static double getPointMoyenneTuteur(Personne per){
        return (20-per.getMoyenne());
    }

    public static double getPointMoyenneTutoré(Personne per){
        return (40-(per.getMoyenne()*2));
    }

    public static int getAbsence(Personne per){
        return per.getAbsences();
    }
}
