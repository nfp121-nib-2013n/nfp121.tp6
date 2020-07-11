package question3;

import question1.*;
import question2.*;
import java.util.*;
import org.jdom.*;

public class Memento {

    private Element state;

    public Memento(Cotisant c) {
        // Sauvegarde
        if(c != null){
            state = c.accepter(new VisiteurSauvegarde());
        }

    }

    public void setState(Cotisant c) {
        // Restitution
        if(c != null){
            c.accepter(new VisiteurRestitution(state));
        }
    }

}