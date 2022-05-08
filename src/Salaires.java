abstract class Employe {

   private String nom;
   private String prenom;
   private int age;
   private String date;

    public Employe(String nom, String prenom, int age, String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.date = date;
    }

    public abstract double calculerSalaire();

    public String getTitre(){
        return "L'employé " ;
    }

    public String getNom() {
        return  getTitre() + prenom + " " + nom;
    }

}
abstract  class Commercial extends Employe {

    private double chifreAfaire ;

    public Commercial(String nom, String prenom, int age, String date, double chifreAfaire) {
        super(nom, prenom, age, date);
        this.chifreAfaire = chifreAfaire;
    }

    public double getChifreAfaire() {
        return  chifreAfaire;
    }
}

class Vendeur extends Commercial {

    private final double pourcentage_vendeur = 0.2;
    private final double base_vendeur = 400;

    public Vendeur(String nom, String prenom, int age, String date, double chifreAfaire) {
        super(nom, prenom, age, date, chifreAfaire);
    }

    @Override
    public double calculerSalaire() {
        return pourcentage_vendeur * getChifreAfaire() + base_vendeur;
    }

    public String getTitre() {
        return "Le Vendeur ";

    }
}
class Representant extends Commercial {

    private final double pourcentage_representant = 0.2;
    private final double base_representant = 800;

    public Representant(String nom, String prenom, int age, String date, double chifreAfaire) {
        super(nom, prenom, age, date, chifreAfaire);
    }

    @Override
    public double calculerSalaire() {
        return pourcentage_representant * getChifreAfaire() + base_representant;
    }

    public String getTitre() {
        return "Le representant ";

    }
}
abstract  class Producteur extends Employe {

    private final static double factor_unite = 5 ;
    private int unite;

    public Producteur(String nom, String prenom, int age, String date, int unite) {
        super(nom, prenom, age, date);
        this.unite = unite;
    }

    public double calculerSalaire() {
        return  unite * factor_unite ;
    }

    public  String getTitre() {
        return "Le producteur ";
    }
}
class Manutentionnaire extends Employe {

    private final static double factor_heure = 65;
    private double nombreHeures ;

    public Manutentionnaire(String nom, String prenom, int age, String date, double nombreHeures) {
        super(nom, prenom, age, date);
        this.nombreHeures = nombreHeures;
    }

    public double calculerSalaire() {
        return  nombreHeures * factor_heure ;
    }

    public  String getTitre() {
        return "Le manutentionnaire ";
    }
}

interface ARisque {
    int PRIME = 200;
}

class TechnARisque extends Producteur implements ARisque {

    public TechnARisque(String prenom, String nom, int age, String date, int unite) {
        super(prenom, nom, age, date, unite);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
class ManuARisque extends Manutentionnaire implements ARisque {

    public ManuARisque(String prenom, String nom, int age, String date, int unite) {
        super(prenom, nom, age, date, unite);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}

class Personnel {

    private Employe[] staff;
    private int nbreEmploye;
    private final static int MAXEMPLOYE = 200;

    public Personnel() {
        staff = new Employe[MAXEMPLOYE];
        nbreEmploye = 0;
    }

    public void ajouterEmploye(Employe e) {
        ++nbreEmploye;
        if (nbreEmploye <= MAXEMPLOYE) {
            staff[nbreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
        }
    }

    public double salaireMoyen() {
        double somme = 0.0;
        for (int i = 0; i < nbreEmploye; i++) {
            somme += staff[i].calculerSalaire();
        }
        return somme / nbreEmploye;
    }

    public void afficherSalaires() {
        for (int i = 0; i < nbreEmploye; i++) {
            System.out.println(staff[i].getNom() + " gagne "
                    + staff[i].calculerSalaire() + " francs.");
        }
    }
}
class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("Pierre", "Business", 45, "1995", 30000));
        p.ajouterEmploye(new Representant("Léon", "Vendtout", 25, "2001", 20000));
        p.ajouterEmploye(new Producteur("Yves", "Bosseur", 28, "1998", 1000) {
        });
        p.ajouterEmploye(new Manutentionnaire("Jeanne", "Stocketout", 32, "1998", 45));
        p.ajouterEmploye(new TechnARisque("Jean", "Flippe", 28, "2000", 1000));
        p.ajouterEmploye(new ManuARisque("Al", "Abordage", 30, "2001", 45));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");
    }
}