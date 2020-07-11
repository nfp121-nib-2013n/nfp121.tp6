package question2;

import java.util.*;
import question1.*;

public class TestsDesVisiteurs extends junit.framework.TestCase{

    public void testACompleter(){
        GroupeDeContributeurs grp1 = new GroupeDeContributeurs("Groupe 1");
        GroupeDeContributeurs grp2 = new GroupeDeContributeurs("Groupe 2");
        GroupeDeContributeurs grp3 = new GroupeDeContributeurs("Groupe 2");

        Contributeur ctr1 = new Contributeur("Durant", 30000);
        Contributeur ctr2 = new Contributeur("Dupont", 5000);
        Contributeur ctr3 = new Contributeur("Jacques", 10000);
        Contributeur ctr4 = new Contributeur("Dupont", 80000);
        Contributeur ctr5 = new Contributeur("Marie", 2000);

        // Test du visiteur CompositeValide
        grp1.ajouter(ctr1);
        grp1.ajouter(ctr2);
        assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", grp1.accepter(new CompositeValide()));
        assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", grp2.accepter(new CompositeValide()));
        assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", grp3.accepter(new CompositeValide()));

        // Test du visiteur SansDoublon
        grp2.ajouter(ctr3);
        grp2.ajouter(ctr4);
        grp2.ajouter(grp1);
        assertTrue(" Ce composite est sans doublon, revoyez SansDoublon !!!", grp1.accepter(new SansDoublon()));
        assertFalse(" Ce composite a un doublon, revoyez SansDoublon !!!", grp2.accepter(new SansDoublon()));
        assertTrue(" Ce composite est sans doublon, revoyez SansDoublon !!!", grp3.accepter(new SansDoublon()));
        
        // Test du visiteur DebitMaximal
        grp3.ajouter(ctr5);
        grp3.ajouter(grp2);
        assertEquals(" Revoyez DébitMaximal !!!", new Integer(5000), grp1.accepter(new DebitMaximal()));
        assertEquals(" Revoyez DébitMaximal !!!", new Integer(5000), grp2.accepter(new DebitMaximal()));
        assertEquals(" Revoyez DébitMaximal !!!", new Integer(2000), grp3.accepter(new DebitMaximal()));

    }

    public void testCompositeValide(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            g1.ajouter(new Contributeur("c",100));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testTroisContributeursUnGroupe(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            assertEquals(" Revoyez DébitMaximal !!!", new Integer(100), g.accepter(new DebitMaximal()));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g1.accepter(new CompositeValide()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testUnContributeurUnGroupeAvecLeMemeNom(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g_a");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            g.ajouter(new Contributeur("g_d",80));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            assertFalse(" Ce composite a au moins un doublon, revoyez SansDoublon !!!", g.accepter(new SansDoublon()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }
}

