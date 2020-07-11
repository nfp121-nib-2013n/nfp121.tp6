package question3;

import question1.*;
import java.util.*;
import org.jdom.*;

/**
 * VisiteurRestitution, un second visiteur pour la restitution 
 * du composite.
 */

public class VisiteurRestitution implements Visiteur<Cotisant>
{
    // L'arbre XML du Cotisant à restituée
    private Element state;

    public VisiteurRestitution(Element state){
        this.state = state;
    }

    public Cotisant visite(Contributeur cpt){
        if(cpt == null || state == null)
            return null;
        updateState(cpt, state);
        return cpt;
    }

    public Cotisant visite(GroupeDeContributeurs grp){
        if(grp == null || state == null)
            return null;
        updateState(grp, state);
        return grp;
    }

    private void updateState(Contributeur cpt, Element state){
        String nomCpt = cpt.nom();
        String valeurDuNom = state.getAttribute("nom").getValue();

        if(nomCpt.equals(valeurDuNom)){
            /**
             * Restitution de la valeur ancienne du solde du 
             * contributeur approprié.
             */
            int valeurDuSolde = Integer.parseInt(state.getAttribute("solde").getValue());
            cpt.affecterSolde(valeurDuSolde);
        }
    }

    private void updateState(GroupeDeContributeurs grp, Element state){
        String nomGrp = grp.nom();
        String valeurDuNom = state.getAttribute("nom").getValue();

        if(nomGrp.equals(valeurDuNom)){
            List<Cotisant> cotisants = grp.getChildren();
            List<Element> elements = state.getChildren();
            
            /**
             * Traverser la liste des Cotisants du groupe afin de
             * restituer la valeur ancienne du solde de ses 
             * contributeurs.
             */ 
            for(int count = 0; count < cotisants.size(); count += 1){
                Cotisant cotisant = cotisants.get(count);
                Element element = elements.get(count);
                if(cotisant instanceof Contributeur)
                    updateState((Contributeur)cotisant, element);
                else
                    updateState((GroupeDeContributeurs)cotisant, element);
            }
        }
    }
}
