package question2;

// Les quatres premiers import peuvent être remplacé par : 
// import question1.*;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import question1.Cotisant;

public class CompositeValide implements Visiteur<Boolean>{

    public Boolean visite(Contributeur c){ 
        if(c == null)
            return false;
        // Le solde de chaque contributeur doit être supérieur ou égal à 0
        return c.solde() >= 0;
    }

    public Boolean visite(GroupeDeContributeurs g){
        if(g == null)
            return false;

        // Il n’existe pas de groupe n’ayant pas de contributeurs
        if(g.nombreDeCotisants() == 0)
            return false;

        // Valider les instances Cotisants du groupe
        // Utilisation de foreach
        for(Cotisant cotisant : g.getChildren()){
            if(!cotisant.accepter(this)){
                return false;
            }
        }
        
        // Si tous va bien !
        return true ;
    }
}