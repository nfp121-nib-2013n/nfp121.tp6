package question3;

import question1.*;

public abstract class AbstractTransaction{ 
    protected Cotisant cotisant;
    protected int somme;

    public abstract void beginTransaction();     // Template Method
    public abstract void endTransaction();
    public abstract void rollbackTransaction();

    public AbstractTransaction(Cotisant cotisant){
        this.cotisant = cotisant;
    }

    final public void debit(int somme) throws SoldeDebiteurException{
        try{
            beginTransaction();
            this.somme = somme;
            cotisant.debit(somme);
            endTransaction();
        }catch(SoldeDebiteurException e){
            rollbackTransaction();
            throw e;
        }
    }
}
