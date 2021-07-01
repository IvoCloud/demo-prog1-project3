/**
 * Cette classe modelise une classe Bibliotheque qui contient un tableau de
 * la classe Livre
 *
 * @author Ivaylo Ivanov
 * @version 17 Avril 2019
 */

public class Bibliotheque {

    //-----------------------------------------------------
    //CONSTANTES DE CLASSE

    public final static int NBR_CASES = 4;

    //-----------------------------------------------------
    // ATTRIBUTS D'INSTANCE

    private Livre[] livres;
    private int nbrLivres;

    //-----------------------------------------------------
    //CONSTRUCTEURS

    //CONSTRUCTEUR PAR DEFAULT
    public Bibliotheque(){
        livres = new Livre[NBR_CASES];
    }

    //CONSTRUCTEUR A UN PARAMETRE
    public Bibliotheque(Livre[] lesLivres){
        livres = new Livre[NBR_CASES];
        nbrLivres=0;

        for(Livre livre: lesLivres){
            ajouterLivre(livre);
        }
    }

    //-----------------------------------------------------
    //METHODES PUBLIQUES

    /**
     * Une methode qui compte le nombre d'elements non-null dans
     * le tableau livres
     *
     * @return int le nombre d'elements dans le tableau livres
     */
    public int getNbrLivres (){
        int nbr=0;
        for(Livre i: livres ){
            if(i!=null){
                nbr++;
            }
        }
        return nbr;
    }

    /**
     * Permet de verifier si un objet livre existe deja dans le tableau livres
     *
     * @param livre objet Livre que la methode verifie s'il existe deja dans livres
     *
     * @return true si le livre fourni en parametre existe deja dans le tableaau livres
     */
    public boolean livreExiste(Livre livre){
        boolean existe = false;
        for(Livre i: livres){
            if(!existe&&i!=null) {
                existe = i.estEgal(livre);
            }
        }
        return existe;
    }

    /**
     * Permet d'ajouter un objet Livre dans le dableau d'objets Livre
     *
     * @param livre objet Livre a ajouter dans le tableau livres
     *
     * @return true si le livre est ajoute dans le tableau avec succes
     */
    public boolean ajouterLivre(Livre livre){
        boolean ajout=false;
        if(!livreExiste(livre)&&livre!=null) {
            if (livres.length == nbrLivres) {
                agrandirTableau();
            }
            livres[nbrLivres]=livre;
            nbrLivres++;
            ajout=true;
        }
        return ajout;
    }

    /**
     * Permet d'jouter un livre dans le tableau livres et fournissant le titre
     * l'auteur, l'annee de publication et les categories.
     *
     * @param titre le titre du livre
     * @param auteur l'auteur du livre
     * @param anneePub l'annee du livre
     * @param numCategories les categories du livre
     *
     * @return true si l'objet Livre cree avec les parametres fournis
     * est ajoute dans le tableau avec succes
     */
    public boolean ajouterLivre(String titre,String auteur,int anneePub,int[] numCategories){
        boolean ajout=false;
        Livre livre = new Livre(titre,auteur,anneePub);
        for(int categorie: numCategories) {
            livre.ajouterCategorie(categorie);
        }
        if(!livreExiste(livre)) {
            if (livres.length == nbrLivres) {
                agrandirTableau();
            }
            livres[nbrLivres]=livre;
            nbrLivres++;
            ajout=true;
        }
        return ajout;
    }

    /**
     * Permet d'obtenir l'objet livre du tableau livres qui est place dans
     * l'index iemeLivre fourni en parametre.
     *
     * @param iemeLivre l'index du livre a retrouver
     *
     * @return l'objet Livre qui se trouve dans l'index iemeLivre du tableau livres.
     * Sinon retourne null.
     */
    public Livre obtenirLivre(int iemeLivre){
        Livre livre = null;
        if(iemeLivre<livres.length&&iemeLivre>=0){
            livre = livres[iemeLivre];
        }
        return livre;
    }

    /**
     * Permet de supprimer l'objet livre du tableau livres qui est place dans
     * l'index iemeLivre fourni en parametre.
     *
     * @param iemeLivre l'index du livre a supprimer
     *
     * @return l'objet Livre qui est supprime de l'index iemeLivre du tableau livres.
     * Sinon retourne null.
     */
    public Livre supprimerLivre(int iemeLivre){
        Livre livre = obtenirLivre(iemeLivre);
        if(livre!=null){
            for (int i = iemeLivre;i<livres.length;i++){
                if (i!=livres.length-1) {
                    livres[i] = livres[i + 1];
                }else {
                    livres[i]=null;
                }
            }
            nbrLivres--;
        }
        return livre;
    }

    /**
     * Permet de rechercher dans le tableau livres, un tableau de livres qui contiennent
     * tous les categories fournis en parametre numCategories. Les livres trouves sont
     * places dans le taleau LivresTrouvees et retournes.
     *
     * @param numCategories tableau des categories-criteres pour trouver des livres
     *
     * @return un tableau des livres retouves qui contiennent tous les categories de numCategories.
     * Si numCriteres est invalide, la methode retourne le tableau de tous les livres
     */
    public Livre[] rechercherParConjonctionDeCategories(int[] numCategories){
        Livre[] livresTrouvees;
        int indexLivresTrouvees=0;
        int longueur =0;
        if(numCategories != null && validerCategories(numCategories)) {
            for (Livre l : livres) {
                if (l != null) {
                    if (categoriesDuLivreConjonction(numCategories, l)) {
                        longueur++;
                    }
                }
            }
            livresTrouvees = new Livre[longueur];
            for (Livre l : livres) {
                if (l != null) {
                    if (categoriesDuLivreConjonction(numCategories, l)) {
                        livresTrouvees[indexLivresTrouvees] = l;
                        indexLivresTrouvees++;

                    }
                }
            }
        }else {livresTrouvees=livres;}
        return livresTrouvees;
    }

    /**
     * Permet de rechercher dans le tableau livres, un tableau de livres qui contiennent
     * au moins une des categories fournis en parametre numCategories. Les livres trouves sont
     * places dans le taleau LivresTrouvees et retournes.
     *
     * @param numCategories tableau des categories-criteres pour trouver des livres
     *
     * @return un tableau des livres retouves qui contiennent au moins une des categories de numCategories.
     * Si numCriteres est invalide, la methode retourne le tableau de tous les livres
     */
    public Livre[] rechercherParDisjonctionDeCategories(int[] numCategories){
        Livre[] livresTrouvees;
        int indexLivresTrouvees=0;
        int longueur =0;
        if(numCategories != null && validerCategories(numCategories)) {
            for (Livre l : livres) {
                if (l != null) {
                    if (categoriesDuLivreDisjonction(numCategories, l)) {
                        longueur++;
                    }
                }
            }
            livresTrouvees = new Livre[longueur];
            for (Livre l : livres) {
                if (l != null) {
                    if (categoriesDuLivreDisjonction(numCategories, l)) {
                        livresTrouvees[indexLivresTrouvees] = l;
                        indexLivresTrouvees++;

                    }
                }
            }
        }else {livresTrouvees=livres;}
        return livresTrouvees;
    }

    //-----------------------------------------------------
    //METHODES PRIVEES

    /**
     * Permet de d'augmenter la longueur d'un tableau de 4 cases
     * en utiliseant un tableau temporaire pour tranferer les valeur dans le
     * nouveau tableau.
     *
     * @return le tableau d'origine augmente de 4 cases.
     */
    private void agrandirTableau(){
        Livre [] livresRallonges = new Livre[livres.length+NBR_CASES];
        for(int i=0;i<livres.length;i++){
            livresRallonges[i]=livres[i];
        }
        livres = livresRallonges;
    }

    /**
     * Permet de verifier si un objet livre contient toutes les categories
     * fournies par le tableau numCategories.
     *
     * @param numCategories un tableau contenant les categories fournies pour
     *                      la verification
     * @param livre objet Livre que la methode verifie s'il contient toutes les
     *              categories de numCAtegorie
     * @return true si toutes les categories de numCategorie se retrouvent dans
     * livre
     */
    private boolean categoriesDuLivreConjonction(int[] numCategories, Livre livre) {
        boolean categoriesLivre=true;
        for(int i:numCategories){
            if(categoriesLivre) {
                categoriesLivre = livre.estClasseDans(i);
            }
        }
        return categoriesLivre;
    }

    /**
     * Permet de verifier si le livre contient au moins une categorie fournie
     * en parametre.
     *
     * @param numCategories un tableau contenant les categories fournies pour
     *                      la verification
     * @param livre objet Livre que la methode verifie s'il contient au
     *              moins une categorie de numCAtegorie
     * @return true l'objet livre contient au moins une categorie du parametre numCategories
     */
    private boolean categoriesDuLivreDisjonction(int[] numCategories, Livre livre) {
        boolean categoriesLivre=false;
        for(int i:numCategories){
            if(!categoriesLivre) {
                categoriesLivre = livre.estClasseDans(i);
            }
        }
        return categoriesLivre;
    }
    /**
     * Retourne une representation sous forme de chaine de caracteres de ce
     * tableau
     * @return une representation sous forme de chaine de caracteres de ce tableau.
     */
    public String toString() {
        String s = "";
            for(Livre l:livres){
                if(l!=null) {
                    s = s + l + "\n";
                }
            }
        return s;
    }

    /**
     * Permet de verifier si le tableau categories fais partie des categories valides
     * dans la classe Livre
     *
     * @param categories un tableau de categories a verifier si ils font partie
     *                   des categories valides dans Livre
     *
     * @return true si categories contient des valeurs valides selon les categories dans la classe Livre
     */
    private boolean validerCategories(int[] categories){
        boolean valide = true;
        for (int i: categories){
            if(valide){
               valide = Livre.numCatValide(i);
            }
        }
        return valide;
    }
}
