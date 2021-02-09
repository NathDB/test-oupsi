import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name="animaux")
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    public Animal() {
    }

    public Animal(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    //Permet d'ajouter avec un auto-incrément : ne mettre que les autres colonnes
    public Animal(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //pour convertir l'objet en chaine de caractères
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animaux {");
        sb.append("id=").append( id );
        sb.append(", nom=' ").append( nom);
        sb.append('}');
        return sb.toString();
    }

}
