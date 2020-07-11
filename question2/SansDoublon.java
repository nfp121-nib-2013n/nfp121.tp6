package question2;

import question1.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class SansDoublon implements Visiteur<Boolean>{

    // Sauvegarde des noms des Cotisants dans un TreeSet
    private Set<String> nomsDesCotisants = new TreeSet<>();

    public Boolean visite(Contributeur c){
        if(c == null)
            return true;
        // Si la liste 'nomsDesCotisants' contient le nom du 
        // Contributeur alors le cotisant approprié n'est pas 'SansDoublon'
        return !contientNoms(c);
    }

    public Boolean visite(GroupeDeContributeurs g){
        if(g == null)
            return true;
        boolean res = contientNoms(g);

        // Verifier que les Cotisants du groupe 
        // (Contributeur/GroupeDeContibuteurs) n'ont pas les mêmes noms
        if(!res){
            Iterator<Cotisant> it = g.iterator();
            while(it.hasNext()){
                Cotisant cotisant = it.next();
                res = contientNoms(cotisant);
                if(res)
                    return !res;
            }
        }
        return !res ;
    }

    private boolean contientNoms(Cotisant cotisant){
        boolean contient = nomsDesCotisants.contains(cotisant.nom());
        if (!contient)
            nomsDesCotisants.add(cotisant.nom());
        return contient;
    }

}