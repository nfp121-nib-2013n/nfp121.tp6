package question4;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question2.*;
import question3.*;
import static question2.Main.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;

public class IHM extends JFrame {

    private JTextArea resultat = new JTextArea("", 7,60);
    private JButton debiter = new JButton("débiter");
    private JButton crediter = new JButton("créditer");
    private JTextField somme = new JTextField(4);

    private GroupeDeContributeurs g;

    public IHM() {
        this.setTitle("Cotisant");
        Container container = this.getContentPane();
        somme.setText("40");
        container.setLayout(new BorderLayout());

        container.add(resultat, BorderLayout.NORTH);
        JPanel p = new JPanel(new FlowLayout());
        p.add(somme);
        p.add(debiter);
        p.add(crediter);
        container.add(p, BorderLayout.SOUTH);

        g = new GroupeDeContributeurs("g");
        g.ajouter(new Contributeur("g_a",100));
        g.ajouter(new Contributeur("g_b",50));
        g.ajouter(new Contributeur("g_c",150));
        GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
        g1.ajouter(new Contributeur("g1_a1",70));
        g1.ajouter(new Contributeur("g1_b1",200));
        g.ajouter(g1);

        actualiser();

        ButtonsListener buttonsListener = new ButtonsListener();
        debiter.addActionListener(buttonsListener);
        crediter.addActionListener(buttonsListener);

        this.pack();
        this.setVisible(true);
    }

    public void actualiser(){
        try{
            resultat.setText(Main.arbreXML(g));
        }catch(Exception e){}
    }

    private class ButtonsListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            Object event = ae.getSource();
            String valeurSomme = somme.getText();

            if(event == debiter){
                try{
                    // Débiter en utilisant les transactions !
                    AbstractTransaction trans = new TransactionDebit(g);
                    trans.debit(Integer.parseInt(valeurSomme));
                } catch(Exception exc){
                    System.out.println(exc.getMessage());
                }
            } else if(event == crediter){
                try{
                    g.credit(Integer.parseInt(valeurSomme));
                } catch(Exception exc){
                    System.out.println(exc.getMessage());
                }
            }
            actualiser();
        }
    }

    public static void main() {
        new IHM();    
    }    

}