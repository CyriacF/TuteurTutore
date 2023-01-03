package fr.ulille.but.sae2_02.graphes;

public class Arete<S> {
    private S extremite1;

    private S extremite2;

    public Arete(S extremite1, S extremite2) {
        this.extremite1 = extremite1;
        this.extremite2 = extremite2;
    }

    public S getExtremite1() {
        return this.extremite1;
    }

    public S getExtremite2() {
        return this.extremite2;
    }

    public String toString() {
        return String.format("Arete(%s, %s)", new Object[] { getExtremite1(), getExtremite2() });
    }
}
