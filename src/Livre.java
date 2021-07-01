
/**
 * Cette classe modelise un livre. Chaque livre contient les informations
 * suivantes : 
 *    - un titre
 *    - un auteur
 *    - une annee de publication
 *    - un tableau contenant les numeros des categories associees a ce livre.
 * 
 * Les categories pouvant etre associees a un livre sont enumerees dans 
 * le tableau CATEGORIES, et le numero d'une categorie correspond a son 
 * indice dans le tableau. Par exemple, le nom de la categorie CAT_SCIENCE_FICTION 
 * est CATEGORIES[CAT_SCIENCE_FICTION], le nom de la categorie CAT_ROMANCE 
 * est CATEGORIES[CAT_ROMANCE], etc.
 * 
 * Un numero valide pour une categorie se situe donc entre 0 et 
 * CATEGORIES.length - 1.
 * 
 * Un livre peut n'etre associe a aucune categorie. 
 * 
 * Le tableau des numeros de categories associes a un livre est toujours de 
 * longueur minimum (lng tableau = nbr de categories associees). Par exemple, 
 * si 3 categories sont associees au livre, le tableau sera de longueur 3. 
 * S'il n'y a aucune categorie associee au livre, le tableau sera de longueur 0, 
 * etc.
 * 
 * Le tableau des numeros de categories associes a un livre ne contient aucun 
 * doublon et ne contient que des numeros de categories valides.
 * 
 * Pour associer une categorie a un livre, utilisez la methode 
 * ajouterCategorie(...).
 * 
 * INF1120 H19 : Classe fournie pour le TP3.
 * 
 * @author Melanie Lord
 * @version 20 mars 2019
 */
public class Livre {

   //-----------------------------
   // CONSTANTES DE CLASSE
   //-----------------------------
   
   //Numeros des categories pouvant etre associees a un livre
   public final static int CAT_SCIENCE_FICTION = 0;
   public final static int CAT_ROMANCE = 1;
   public final static int CAT_THRILLER = 2;
   public final static int CAT_POLICIER = 3;
   public final static int CAT_HUMOUR = 4;
   public final static int CAT_DRAME = 5;
   
   //Noms des categories pouvant etre associees a un livre
   public final static String [] CATEGORIES = {"science-fiction", "romance", 
         "thriller", "policier", "humour", "drame"};
   
   //Valeurs par defaut des attributs d'instance titre, auteur et annee de publ.
   public final static String TITRE_DEFAUT = "Titre inconnu";
   public final static String AUTEUR_DEFAUT = "Auteur inconnu";
   public final static int ANNEE_PUB_DEFAUT = -1; //signifie annee inconnue  
   
   //Valeurs limites pour une annee de publication valide.
   public final static int ANNEE_PUB_MIN = 1900;
   public final static int ANNEE_PUB_MAX = 2019;
   
   //-----------------------------
   // ATTRIBUTS D'INSTANCE
   //-----------------------------
   private String titre;   //titre du livre
   private String auteur;  //auteur du livre (on suppose qu'il n'y en n'a qu'un)
   private int anneePub;   //annee de publication du livre
   
   //liste des numeros de categories associees a ce livre.
   private int [] numCategories;  
   
   //-----------------------------
   // CONSTRUCTEUR
   //-----------------------------
   
   /**
    * Construit un livre avec les informations donnees en parametres, et qui 
    * n'est associe a aucune categorie.
    * 
    * Si l'un des parametres donnes est invalide, il sera remplace par sa valeur
    * par defaut correspondante (titre => TITRE_DEFAUT, auteur => AUTEUR_DEFAUT,
    * et anneePub => ANNEE_PUB_DEFAUT).
    *    - Un titre valide doit etre non null et non vide.
    *    - Un auteur valide doit etre non null et non vide.
    *    - une anneePub valide doit etre entre ANNEE_PUB_MIN et ANNEE_PUB_MAX
    *      inclusivement.
    * 
    * @param titre le titre de ce livre 
    * @param auteur le nom de l'auteur de ce livre
    * @param anneePub l'annee de publication de ce livre
    */
   public Livre (String titre, String auteur, int anneePub) {
      
      if (!titreValide(titre)) {
         titre = TITRE_DEFAUT;
      }
      
      if (!auteurValide(auteur)) {
         auteur = AUTEUR_DEFAUT;
      }
      
      if (!anneeValide(anneePub)) {
         anneePub = ANNEE_PUB_DEFAUT;
      }
      
      this.titre = titre;
      this.auteur = auteur;
      this.anneePub = anneePub;
      
      numCategories = new int[0];
   }
   
   //-----------------------------
   // METHODES PUBLIQUES
   //-----------------------------

   /**
    * Permet d'obtenir le titre de ce livre.
    * 
    * @return le titre de ce livre.
    */
   public String getTitre() {
      return titre;
   }

   /**
    * Permet de modifier le titre de ce livre par celui passe en parametre.
    * Si le titre donne est invalide (null ou vide), la modification n'est 
    * pas effectuee.
    * 
    * @param titre la valeur de modification pour le titre.
    */
   public void setTitre(String titre) {
      if (titreValide(titre)) {
         this.titre = titre;
      }
   }

   /**
    * Permet d'obtenir le nom de l'auteur de ce livre.
    * 
    * @return le nom de l'auteur de ce livre.
    */
   public String getAuteur() {
      return auteur;
   }

   /**
    * Permet de modifier l'auteur de ce livre par celui passe en parametre.
    * Si l'auteur donne est invalide (null ou vide), la modification n'est pas 
    * effectuee.
    * 
    * @param auteur la valeur de modificatino pour l'auteur.
    */
   public void setAuteur(String auteur) {
      if (auteurValide(auteur)) {
         this.auteur = auteur;
      }  
   }

   /**
    * Permet d'obtenir l'annee de publication de ce livre.
    * 
    * @return l'annee de publication de ce livre.
    */
   public int getAnneePub() {
      return anneePub;
   }

   /**
    * Permet de modifier l'annee de publication de ce livre par celle donnee
    * en parametre. Si l'annee donnee est invalide (PAS entre ANNEE_PUB_MIN et
    * ANNEE_PUB_MAX inclusivement), la modification n'est pas effectuee.
    * 
    * @param anneePub la valeur de modification pour l'annee de publ. de ce 
    *                 livre.
    */
   public void setAnneePub(int anneePub) {
      if (anneeValide(anneePub)) {
         this.anneePub = anneePub;
      }
   }
   
   /**
    * Permet d'ajouter un numero de categorie au tableau des numeros de 
    * categories deja associes a ce livre.
    * 
    * Si le numero de categorie donne en parametre n'est pas valide, l'ajout 
    * n'est pas effectue. Un numero de categorie valide doit etre entre
    * 0 et CATEGORIES.length - 1 inclusivement.
    * 
    * Si le numero de categorie donne en parametre existe deja dans la liste
    * des numeros de categories associes a ce livre, l'ajout n'est pas effectue.
    * 
    * @param numCategorie le numero de categorie a ajouter a ce livre.
    * @return true si le numero de categorie a ete ajoute, false sinon.
    */
   public boolean ajouterCategorie(int numCategorie) {
      boolean ajout = numCatValide(numCategorie) && 
                      !numCatExiste(numCategorie);
      if (ajout) {
         agrandirTabNumCat();
         
         //ajouter a la derniere case
         numCategories[numCategories.length - 1] = numCategorie;
      }
      return ajout;
   }
   
   /**
    * Permet de retirer un numero de categorie du tableau des numeros de 
    * categories associes a ce livre.
    * 
    * Si le numero de categorie donne n'existe pas dans le tableau des numeros
    * de categories associes a ce livre, le retrait n'est pas effectue.
    * 
    * @param numCategorie le numero de categorie a retirer.
    * @return true si le retrait a ete effectue, false sinon.
    */
   public boolean retirerCategorie(int numCategorie) {
      boolean retrait = numCatExiste(numCategorie);
      int[] tab;
      int j = 0;
      
      if (retrait) {
         tab = new int[numCategories.length - 1];
         
         for (int i = 0 ; i < numCategories.length ; i++) {
            if (numCategories[i] != numCategorie) {
               tab[j] = numCategories[i];
               j++;
            }
         }
         numCategories = tab;
      }
      return retrait;
   }
   
   /**
    * Permet de verifier si ce livre est classe dans la categorie dont le numero
    * est donne en parametre.
    * 
    * @param numCategorie le numero de la categorie dont on veut savoir si elle
    *        est associee a ce livre.
    * @return true si le numero de categorie donne est associe a ce livre, 
    *         false sinon.
    */
   public boolean estClasseDans(int numCategorie) {
      boolean estClasseDans = false;
      int i = 0;
      
      while (i < numCategories.length && !estClasseDans) {
         estClasseDans = numCategories[i] == numCategorie;
         i++;
      }
      return estClasseDans;
   }
   
   /**
    * Retourne une representation sous forme de chaine de caracteres de ce
    * livre. Par exemple : 
    * LA VIE, L'UNIVERS ET LE RESTE (1983), D. Adams [ scicence-fiction, humour ]
    * 
    * @return une representation sous forme de chaine de caracteres de ce livre.
    */
   public String toString() {
      String s = titre.toUpperCase() + " ";
      
      if (anneePub != ANNEE_PUB_DEFAUT) 
         s = s + "(" + anneePub + "), ";
      else
         s = s + "(), ";
      
      s = s + auteur;
      if (numCategories.length != 0) {
         s = s + " [ ";
         for (int num : numCategories) {
            s = s + CATEGORIES[num].toLowerCase() + ", ";
         }
         s = s.substring(0, s.length() - 2) + " ]";
      } else {
         s = s + " [ aucune categorie ]";
      }
      
      return s;
   }
    
   /**
    * Teste si ce livre est egal a celui passe en parametre.
    * Deux livres sont consideres egaux s'il ont le meme titre (peu importe la 
    * casse), le meme auteur (peu importe la casse) et la meme annee de
    * publication.
    * 
    * @param livre le livre a comparer avec ce livre.
    * @return true si ce livre est egal au livre donne, false sinon.
    */
   public boolean estEgal(Livre livre) {
      return this.titre.equalsIgnoreCase(livre.titre)
              && this.auteur.equalsIgnoreCase(livre.auteur)
              && this.anneePub == livre.anneePub;
   } 
   
   /**
    * Determine si le titre donne en parametre est valide ou non. Un titre
    * valide ne doit pas etre null, ni vide.
    * 
    * @param titre le titre a verifier.
    * @return true si le titre donne est valide, false sinon.
    */
   public static boolean titreValide(String titre) {
      return titre != null && !titre.isEmpty();
   }
   
   /**
    * Determine si l'auteur donne en parametre est valide ou non. Un auteur 
    * valide ne doit pas etre null ni vide.
    * 
    * @param auteur l'auteur a verifier.
    * @return true si l'auteur donne est valide, false sinon.
    */
   public static boolean auteurValide(String auteur) {
      return auteur != null && !auteur.isEmpty();
   }
   
   /**
    * Determine si l'annee donnee est valide. Une annee valide doit etre entre
    * ANNEE_PUB_MIN et ANNEE_PUB_MAX inclusivement.
    * 
    * @param annee l'annee a verifier.
    * @return true si l'annee donnee est valide, false sinon.
    */
   public static boolean anneeValide(int annee) {
      return annee >= ANNEE_PUB_MIN && annee <= ANNEE_PUB_MAX;
   }
   
   /**
    * Determine si le numero de categorie donne en parametre est valide. Un
    * numero de categorie est valide s'il se trouve entre 0 et 
    * CATEGORIES.length - 1 inclusivement.
    * 
    * @param numCategorie le numero de categorie a verifier.
    * @return true si le numero de categorie donne est valide, false sinon.
    */
   public static boolean numCatValide(int numCategorie) {
      return numCategorie >= 0 && numCategorie < CATEGORIES.length;
   }
   
   //-----------------------------
   // METHODES PRIVEES
   //-----------------------------
   
   /**
    * Verifie si le numero de categorie donne en parametre existe dans le 
    * tableau des categories associees a ce livre.
    * 
    * @param numCategorie la categorie dont on veut savoir si elle est deja
    *                     associee a ce livre.
    * @return true si le numero de categorie donne existe, false sinon.
    */
   private boolean numCatExiste(int numCategorie) {
      boolean existe = false;
      int i = 0;
      
      while (i < numCategories.length && !existe) {
         existe = numCategories[i] == numCategorie;
         i++;
      }
      
      return existe;
   }

   /**
    * Agrandit le tableau des numeros de categories d'une case.
    */
   private void agrandirTabNumCat() {
      int [] numCatTmp;

      numCatTmp = new int[numCategories.length + 1];
      
      for (int i = 0 ; i < numCategories.length ; i++) {
         numCatTmp[i] = numCategories[i];
      }
      
      numCategories = numCatTmp;

   }

}
