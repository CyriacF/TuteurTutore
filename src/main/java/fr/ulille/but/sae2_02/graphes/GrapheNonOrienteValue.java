package fr.ulille.but.sae2_02.graphes;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;

public class GrapheNonOrienteValue<S> {
    private DefaultUndirectedWeightedGraph<S, Arete<S>> graph = new DefaultUndirectedWeightedGraph(Arete.class);

    protected DefaultUndirectedWeightedGraph<S, Arete<S>> getJGraph() {
        return this.graph;
    }

    public void ajouterSommet(S sommet) {
        this.graph.addVertex(sommet);
    }

    public List<S> sommets() {
        return new ArrayList<>(this.graph.vertexSet());
    }

    public boolean contientSommet(S sommet) {
        return this.graph.containsVertex(sommet);
    }

    public void ajouterArete(S extremite1, S extremite2, double poids) {
        if (!this.graph.containsVertex(extremite1) || !this.graph.containsVertex(extremite2))
            throw new IllegalArgumentException("Sommet inexistant. Ajouter les sommets extravant d'ajouter une ar");
        if (this.graph.getEdge(extremite1, extremite2) != null)
            throw new IllegalArgumentException("Le graphe contient dune telle ar");
                    Arete<S> edge = new Arete<>(extremite1, extremite2);
        this.graph.addEdge(extremite1, extremite2, edge);
        this.graph.setEdgeWeight(edge, poids);
    }

    public List<Arete<S>> aretes() {
        return new ArrayList<>(this.graph.edgeSet());
    }

    public boolean contientArete(S extremite1, S extremite2) {
        return this.graph.containsEdge(extremite1, extremite2);
    }

    public Arete<S> getArete(S extremite1, S extremite2) {
        Arete<S> a = (Arete<S>)this.graph.getEdge(extremite1, extremite2);
        if (a == null)
            throw new IllegalArgumentException("Le graphe ne contient pas d'arentre ces sommets.");
        return a;
    }

    public double getPoids(S extremite1, S extremite2) {
        Arete<S> edge = (Arete<S>)this.graph.getEdge(extremite1, extremite2);
        if (edge == null)
            throw new IllegalArgumentException("Le graphe ne contient pas de telle arrête");
        return this.graph.getEdgeWeight(edge);
    }

    public String toString() {
        String sommets = this.graph.vertexSet().toString();
        sommets = "{" + sommets.substring(1, sommets.length() - 1) + "}";
        StringBuilder saretes = new StringBuilder();
        for (Arete<S> a : aretes()) {
            saretes.append(String.format("(%s, %s):%f, ", new Object[] { a.getExtremite1(), a.getExtremite2(), Double.valueOf(getPoids(a.getExtremite1(), a.getExtremite2())) }));
        }
        if (saretes.length() >= 2)
            saretes.delete(saretes.length() - 2, saretes.length());
        return String.format("GrapheOrienteNonValue[ Sommets: %s, Aret poids: %s ]", new Object[] { sommets, saretes.toString() });
    }
}
