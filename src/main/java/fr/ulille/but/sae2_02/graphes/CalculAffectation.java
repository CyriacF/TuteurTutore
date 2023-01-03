package fr.ulille.but.sae2_02.graphes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.KuhnMunkresMinimalWeightBipartitePerfectMatching;

public class CalculAffectation<S> {
    private MatchingAlgorithm.Matching<S, Arete<S>> matching;

    public CalculAffectation(GrapheNonOrienteValue<S> graphe, List<S> partie1, List<S> partie2) {
        KuhnMunkresMinimalWeightBipartitePerfectMatching<S, Arete<S>> algo = new KuhnMunkresMinimalWeightBipartitePerfectMatching((Graph)graphe.getJGraph(), new HashSet<>(partie1), new HashSet<>(partie2));
        try {
            this.matching = algo.getMatching();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Le graphe donnn'est pas un graphe bi-partite complet de parties de tailles");
        }
    }

    public List<Arete<S>> getAffectation() {
        return new ArrayList<>(this.matching.getEdges());
    }

    public double getCout() {
        return this.matching.getWeight();
    }
}
