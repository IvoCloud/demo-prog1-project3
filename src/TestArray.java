import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestArray {


    public static void main(String [] args){

        Livre l1 = new Livre("Amazone", "M. Gregory", 2004);
        Livre l2 = new Livre("Matrix", "J. Polly", 1998);
        Livre l3 = new Livre("The Hobbit", "J. R. R. Tolkien", 1937);
        Livre l4 = new Livre("The Lord of the Rings", "J. R. R. Tolkien", 1954);
        Livre l5 = new Livre("A Game of Thrones", "George R. R. Martin", 1996);
        Livre l6 = new Livre("The Return of the King", "J. R. R. Tolkien", 1955);
        Livre l7 = new Livre("The Last Wish", "Andrzej Sapkowski", 1993);
        Livre l8 = new Livre("HARRY POTTER AND THE SORCERER’S STONE", "J.K ROWLING", 1997);
        Livre l9 = new Livre("Fantastic Beasts and Where to Find Them", "J.K ROWLING", 2001);

        l1.ajouterCategorie(3);

        l2.ajouterCategorie(3);
        l2.ajouterCategorie(4);
        l2.ajouterCategorie(5);

        l3.ajouterCategorie(3);
        l3.ajouterCategorie(4);
        l3.ajouterCategorie(5);

        l4.ajouterCategorie(2);

        l3.ajouterCategorie(5);

        l7.ajouterCategorie(0);
        l7.ajouterCategorie(2);

        Livre[] listeLivres = new Livre[]{l1,l2,l3,l4};
        Bibliotheque livres = new Bibliotheque(listeLivres);

        livres.ajouterLivre(l7);
        livres.ajouterLivre(l7);
        System.out.println(livres.obtenirLivre(1));

        System.out.println();
        System.out.println(livres.toString());

        System.out.println("--------------------------------");

        int [] categroiesRecherche = new int[]{3,5};

//        int grandeur=0;
//
//        for (Livre l: listeLivres) {
//            System.out.println(livres.categoriesDuLivreConjonction(categroiesRecherche, l));
//        }
//
//        System.out.println("--------------------------------");
//
//        for (Livre l: listeLivres) {
//            if(livres.categoriesDuLivreConjonction(categroiesRecherche, l))
//            {
//                grandeur++;
//            }
//        }
//        System.out.println(grandeur);

//        Livre[] conjonctionAssorti = livres.rechercherParConjonctionDeCategories(categroiesRecherche);
//
//        for(Livre l: conjonctionAssorti){
//            if(l!=null) {
//                System.out.println(l + "\n");
//            }
//        }

//        Livre[] disjonctionAssorti = livres.rechercherParDisjonctionDeCategories(categroiesRecherche);
//
//        for(Livre l: disjonctionAssorti){
//            if(l!=null) {
//                System.out.println(l + "\n");
//            }
//        }

    }
}
