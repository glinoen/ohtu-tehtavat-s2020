/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author julinden
 */
public class Erotus extends Komento {

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta,syotekentta,nollaa,undo,sovellus);
    }

    @Override
    public void suorita() {
        int arvo = 0;
        int tulos = 0;
        try {
            tulos = Integer.parseInt(tuloskentta.getText());
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.miinus(arvo);
        int laskunTulos = sovellus.tulos();
        edellinenT = tulos;
        edellinenS = arvo;
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenS);
        syotekentta.setText("" + edellinenS);
        tuloskentta.setText("" + edellinenT);
    }
    
}
