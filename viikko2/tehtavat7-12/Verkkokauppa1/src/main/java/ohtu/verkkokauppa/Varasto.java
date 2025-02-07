package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Varasto implements VarastoInterface {

    private KirjanpitoInterface kirjanpito;
    private HashMap<Tuote, Integer> saldot;

    @Autowired
    public Varasto(KirjanpitoInterface kirjanpito) {
        this.kirjanpito = kirjanpito;
        saldot = new HashMap<Tuote, Integer>();
        alustaTuotteet();
    }
            
    @Override
    public Tuote haeTuote(int id){
        for (Tuote t : saldot.keySet()) {
            if ( t.getId()==id) return t;
        }
        
        return null;
    }

    @Override
    public int saldo(int id){
        return saldot.get(haeTuote(id));
    }
    
    @Override
    public void otaVarastosta(Tuote t){
        saldot.put(t,  saldo(t.getId())-1 );
        kirjanpito.lisaaTapahtuma("otettiin varastosta "+t);
    }
    
    @Override
    public void palautaVarastoon(Tuote t){
        saldot.put(t,  saldo(t.getId())+1 );
        kirjanpito.lisaaTapahtuma("palautettiin varastoon "+t);
    }

    public void alustaTuotteet() {
        saldot.put(new Tuote(1, "Koff Portteri", 3), 100);
        saldot.put(new Tuote(2, "Fink Bräu I", 1), 25);
        saldot.put(new Tuote(3, "Sierra Nevada Pale Ale", 5), 30);
        saldot.put(new Tuote(4, "Mikkeller not just another Wit", 7), 40);
        saldot.put(new Tuote(5, "Weihenstephaner Hefeweisse", 4), 15);
    }

}
