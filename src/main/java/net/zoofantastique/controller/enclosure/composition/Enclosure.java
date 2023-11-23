package net.zoofantastique.controller.enclosure.composition;

import net.zoofantastique.controller.enclosure.behavior.Cleanness;
import net.zoofantastique.controller.entity.creature.composition.Creature;
import net.zoofantastique.controller.consumable.composition.Food;

import java.util.ArrayList;
import java.util.List;

import static net.zoofantastique.controller.entity.creature.behavior.Hunger.SATISFIED;

/**
 * Classe Enclosure représentant un enclos dans un zoo.
 * Un enclos a un type, un nom, une surface, un nombre maximum de créatures, un nombre actuel de créatures, une liste de créatures et un niveau de propreté.
 */
public class Enclosure<T extends Creature> {
    // Le type de l'enclos est déterminé par le nom de la classe qui l'étend
    private final String enclosureType = getClass().getSimpleName();
    // Le nom de l'enclos
    private String name;
    // La surface de l'enclos en mètres carrés
    private double surface;
    // Le nombre maximum de créatures que l'enclos peut contenir
    private int max;
    // Le nombre actuel de créatures dans l'enclos
    private int nbCreature;
    // La liste des créatures actuellement dans l'enclos
    private List<T> listCreature;
    // Le niveau de propreté de l'enclos
    private Cleanness cleanness;

    /**
     * Constructeur de la classe Enclosure avec nom, surface et nombre maximum de créatures.
     * Initialise également la liste des créatures à une liste vide, le nombre de créatures à 0 et la propreté à CORRECT.
     *
     * @param name    le nom de l'enclos.
     * @param surface la surface de l'enclos en mètres carrés.
     * @param max     le nombre maximum de créatures que l'enclos peut contenir.
     */
    public Enclosure(String name, double surface, int max) {
        this.name = name;
        this.surface = surface;
        this.max = max;
        this.listCreature = new ArrayList<T>();
        this.nbCreature = 0;
        this.cleanness = Cleanness.CORRECT;
    }

    /**
     * Cette méthode est utilisée pour ajouter des créatures à l'enclos.
     * Elle accepte un tableau de créatures et vérifie si l'enclos a la capacité d'accueillir toutes les créatures.
     * Si l'enclos a la capacité, toutes les créatures sont ajoutées à l'enclos et le nombre actuel de créatures est mis à jour.
     * Si l'enclos n'a pas la capacité, un message d'erreur est affiché.
     *
     * @param creatures Un tableau de créatures à ajouter à l'enclos.
     */
    public void addCreature(T... creatures) {
        int totalCreatures = nbCreature + creatures.length;
        if (totalCreatures <= max) {
            this.listCreature.addAll(List.of(creatures));
            nbCreature += creatures.length;
        } else {
            System.err.println("Pas assez de places disponibles!");
        }
    }

    /**
     * Cette méthode est utilisée pour retirer des créatures de l'enclos.
     * Elle accepte un tableau de créatures à retirer et vérifie si chaque créature est présente dans l'enclos.
     * Si la créature est présente dans l'enclos, elle est retirée.
     * Si la créature n'est pas présente dans l'enclos, un message d'erreur est affiché.
     *
     * @param creaturesToRemove Un tableau de créatures à retirer de l'enclos.
     */
    public void removeCreature(T... creaturesToRemove) {
        for (T creature : creaturesToRemove) {
            if (listCreature.contains(creature)) {
                listCreature.remove(creature);
                nbCreature--;
            } else {
                System.err.println("La créature:\n" + creature + "\nn'est pas dans l'enclos!");
            }
        }
    }

    /**
     * Méthode pour nourrir une créature dans l'enclos.
     * Si la créature est présente dans l'enclos et qu'elle n'est pas rassasiée, elle mange la nourriture fournie.
     * Si la créature n'est pas présente dans l'enclos, un message d'erreur est affiché.
     *
     * @param food La nourriture à donner à la créature.
     * @param creature La créature à nourrir.
     */
    public void feedCreature(Food food, T creature){
        if (listCreature.contains(creature)) {
            if (!creature.getHungerState().equals(SATISFIED.getState())) {
                creature.eat(food);
            }
        } else {
            System.err.println("La créature:\n" + creature + "\nn'est pas dans l'enclos!");
        }
    }

    /**
     * Méthode pour effectuer la maintenance de l'enclos.
     * Si l'enclos est vide, une erreur est affichée et la fonction s'arrête.
     * Sinon, affiche un message indiquant que l'enclos est en maintenance, nettoie l'enclos, puis affiche un message indiquant le nouvel état de propreté de l'enclos.
     */
    public void maintenance() {
        if (listCreature.isEmpty()) {
            System.err.println("L'enclos est vide!");
            return;
        }
        System.out.println(getClass().getSimpleName() + " : " + getName() + " est en maintenance.");
        getCleanness().clean();
        System.out.println(getClass().getSimpleName() + " : " + getName() + " est maintenant " + getCleanness().getValue() + ".");
    }

    // Getter et setter

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }

    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }

    public int getNbCreature() {
        return nbCreature;
    }
    public void setNbCreature(int nbCreature) {
        this.nbCreature = nbCreature;
    }

    public List<T> getListCreature() {
        return listCreature;
    }
    public void setListCreature(ArrayList<T> listCreature) {
        this.listCreature = listCreature;
    }

    public Cleanness getCleanness() {
        return cleanness;
    }
    public void setCleanness(Cleanness cleanness) {
        this.cleanness = cleanness;
    }

    /**
     * Méthode pour obtenir une représentation sous forme de chaîne de caractères de l'enclos.
     * Construit une chaîne de caractères contenant le type d'enclos, le nom, la surface, le nombre maximum de créatures, le nombre actuel de créatures, le niveau de propreté et la liste des créatures.
     *
     * @return Une chaîne de caractères représentant l'enclos.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("<-/ " + enclosureType + " \\->\n");
        sb.append("-------------------------")
                .append("\nNom: ").append(name)
                .append("\nSuperficie: ").append(getSurface()).append("m²")
                .append("\nMax creatures: ").append(getMax())
                .append("\nNb creatures: ").append(getNbCreature())
                .append(" / ").append(getMax())
                .append("\nPropreté: ").append(getCleanness().getValue()).append("\n");
        for (T creature : listCreature) {
            sb.append("\n").append(creature.toString());
        }
        return sb.toString();
    }
}