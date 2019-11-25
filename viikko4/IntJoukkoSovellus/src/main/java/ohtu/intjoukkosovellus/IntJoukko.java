
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5; // luotava uusi taulukko on
                                // näin paljon isompi kuin vanha
    private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 1) {
            return;
        }

        ljono = new int[kapasiteetti];
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 1 || kasvatuskoko < 1) {
            return;
        }

        ljono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;

    }

    public void kasvataTaulukkoa() {
        int[] uusiTaulukko = new int[ljono.length + kasvatuskoko];
        kopioiTaulukko(ljono, uusiTaulukko);
        ljono = uusiTaulukko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        if (alkioidenLkm == ljono.length) {
            kasvataTaulukkoa();
        }

        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;

        return true;

    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                return true;
            }
        }
        return false;
    }

    public int getIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public void siirraAlkioitaTaaksepain(int indeksi) {
        for (int i = indeksi; i < alkioidenLkm - 1; i++) {
            ljono[i] = ljono[i + 1];
        }

    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }

        int indeksi = getIndeksi(luku);

        siirraAlkioitaTaaksepain(indeksi);

        alkioidenLkm--;

        return true;

    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm < 1) {
            return "{}";
        }

        StringBuilder luvutMerkkijonona = new StringBuilder();
        luvutMerkkijonona.append("{");

        for (int i = 0; i < alkioidenLkm - 1; i++) {
            luvutMerkkijonona.append(ljono[i] + ", ");
        }

        luvutMerkkijonona.append(ljono[alkioidenLkm - 1] + "}");

        return luvutMerkkijonona.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}