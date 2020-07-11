package question3;

import question1.*;

public class TransactionDebit extends AbstractTransaction{
    private Gardien gardien;

    public TransactionDebit(Cotisant cotisant){
        super(cotisant);
        this.gardien = new Gardien();
    }

    public void beginTransaction(){
        gardien.setMemento(new Memento(cotisant));
    }

    public void endTransaction(){
    }

    public  void rollbackTransaction(){
        gardien.getMemento().setState(cotisant);
    }
}