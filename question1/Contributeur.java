package question1;

public class Contributeur extends Cotisant{
    private int solde;

    /**
     * Note:
     * RuntimeException est une 'Unchecked exceptions' qui peut 
     * ne pas être déclaré dans 'throws'.
     * Mais l'utilisation de 'throws' ici va permettre de tenir 
     * l'exception dans un 'catch' afin d'afficher un message d'erreur
     * compréhensible.
     */
    
    public Contributeur(String nom, int somme) throws RuntimeException{
        super(nom);
        if(somme < 0)
            throw new RuntimeException ("La somme allouée lors de la création ne peut être négative");
        this.solde = somme;
    }

    public int solde(){
        return this.solde;
    }

    public int nombreDeCotisants(){
        return 1;
    }

    public void debit(int somme) throws RuntimeException,SoldeDebiteurException{
        if(somme < 0)
            throw new RuntimeException("La somme débitée ne peut être négative");
        if(somme > solde)
            throw new SoldeDebiteurException("Débit inopérant, le solde est insuffisant");
        solde -= somme;
    }

    public  void credit(int somme) throws RuntimeException{
        if(somme < 0)
            throw new RuntimeException("La somme créditée ne peut être négative");
        solde += somme;
    }

    public void affecterSolde(int somme) throws RuntimeException{
        if(somme < 0) 
            throw new RuntimeException("La somme ne peut être négative");
        try{
            debit(solde()); credit(somme);// mode élégant ... 
        }catch(SoldeDebiteurException sde){ 
            // exception peu probable
            this.solde = somme; // mode efficace ...
        }
    }

    public <T> T accepter(Visiteur<T> visiteur){
        if(visiteur == null)
            return null;
        return visiteur.visite(this);
    }

    public String toString(){
        return "<Contributeur : " + nom + "," + solde + ">";
    }

}
