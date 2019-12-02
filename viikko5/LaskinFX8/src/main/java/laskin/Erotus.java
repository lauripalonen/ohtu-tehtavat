package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    private int tulosMuisti;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus){
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        tulosMuisti = Integer.parseInt(tuloskentta.getText());

        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        sovellus.miinus(arvo);

        paivitaNakyma(sovellus.tulos());
    }

    @Override
    public void peru() {
        syotekentta.setText("");
        tuloskentta.setText(Integer.toString(tulosMuisti));

        if (tulosMuisti == 0){
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

    private void paivitaNakyma(int laskunTulos){
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }
}
