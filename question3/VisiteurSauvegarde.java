package question3;

import question1.*;
import question2.*;
import org.jdom.*;

/**
 * VisiteurSauvegarde, un premier visiteur pour la sauvegarde.
 * L'arbre XML du cotisant sera sauvegardée, en utilisant le 
 * visiteur VisiteurToXML, pour une restitution ultérieure.
 */

public class VisiteurSauvegarde implements Visiteur<Element>
{
    public Element visite(Contributeur cpt){
        if(cpt == null)
            return null;
        return cpt.accepter(new VisiteurToXML());
    }

    public Element visite(GroupeDeContributeurs grp){
        if(grp == null)
            return null;
        return grp.accepter(new VisiteurToXML());
    }
}
