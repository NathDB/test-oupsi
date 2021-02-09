import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("oupsi");
        EntityManager em = emf.createEntityManager();

        //pour chercher qqch en bdd = "find"
        Animal a1 = em.find(Animal.class, 1);
        System.out.println(a1);

        //pour ajouter en bdd : transaction begin et commit, j'ajoute ce qui sera entre ces 2 balises
        em.getTransaction().begin();
        Animal animal = new Animal("guepard"); //Pour auto-incrément : Penser à créer un constrcuteur sans l'id
        em.persist( animal );
        em.getTransaction().commit();

        //modifier objet
        em.getTransaction().begin();
        Animal animalMod = em.find(Animal.class, 1);
        if (animalMod != null) {
            animal.setNom("booba");
            em.getTransaction().commit();
        }

        //Supprimer
        em.getTransaction().begin();
        Animal l = em.find(Animal.class, 2);
        if (l != null){
            em.remove(l);
            em.getTransaction().commit();
            System.out.println("Votre animal a bien été supprimé !");
        }

        //Récupérer valeur via une requête JPQL : en fonction du nom de l'animal
        TypedQuery<Animal> query1 = em.createQuery("select a from Animal a where a.nom='booba'", Animal.class);
        Animal animal1 = query1.getResultList().get(0);
        System.out.println(animal1);

        //Afficher liste
        TypedQuery<Animal> queryList = em.createQuery("select a from Animal a", Animal.class);
        List<Animal> liste = queryList.getResultList();
        for (Animal A :liste){
            System.out.println("ID : " + A.getId() + " , nom : " + A.getNom());
        }

        em.close();
        emf.close();
    }
}
