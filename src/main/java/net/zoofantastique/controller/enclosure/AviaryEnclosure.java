package net.zoofantastique.controller.enclosure;

import net.zoofantastique.controller.creature.composition.Creature;

import java.util.ArrayList;

/**
 * Classe AviaryEnclosure qui représente une volière dans un zoo.
 * Une volière est un type d'enclos qui a une hauteur de toit en plus des attributs d'un enclos.
 * Cette classe étend la classe Enclosure.
 */
public class AviaryEnclosure extends Enclosure {
    // Attributs

    /**
     * La hauteur du toit de la volière.
     */
    private double roofHeight;

    /**
     * Construit un nouvel AviaryEnclosure avec les paramètres spécifiés.
     *
     * @param name       le nom de la volière
     * @param surface    la surface de la volière en mètres carrés
     * @param roofHeight la hauteur du toit de la volière en mètres
     * @param max        le nombre maximum de créatures qui peuvent être logées dans la volière
     */
    public AviaryEnclosure(String name, double surface, double roofHeight, int max) {
        super(name, surface, max);
        this.roofHeight = roofHeight;
    }

    /**
     * Construit un nouvel AviaryEnclosure avec les paramètres spécifiés.
     *
     * @param name         le nom de la volière
     * @param surface      la surface de la volière en mètres carrés
     * @param roofHeight   la hauteur du toit de la volière en mètres
     * @param max          le nombre maximum de créatures qui peuvent être logées dans la volière
     * @param listCreature la liste des créatures actuellement logées dans la volière
     */
    public AviaryEnclosure(String name, double surface, double roofHeight, int max, ArrayList<Creature> listCreature) {
        super(name, surface, max, listCreature);
        this.roofHeight = roofHeight;
    }

    // Getter et setter

    public double getRoofHeight() {
        return roofHeight;
    }
    public void setRoofHeight(double roofHeight) {
        this.roofHeight = roofHeight;
    }

    // Méthodes

    /**
     * Effectue une maintenance sur la volière en fonction de la hauteur du toit.
     *
     * @param roofHeight la hauteur du toit de la volière en mètres
     *
     * <p>Si la volière est vide, un message est affiché et la méthode est terminée.</p>
     * <p>Si la volière n'est pas vide, un message indiquant que la volière est en maintenance est affiché.</p>
     * <p>Ensuite, en fonction de la hauteur du toit :</p>
     * <ul>
     *   <li>Si la hauteur est <= 20 mètres, la propreté de la volière est augmentée.</li>
     *   <li>Si la hauteur est > 20 mètres et <= 40 mètres, la propreté de la volière est augmentée.</li>
     *   <li>Si la hauteur est > 40 mètres et <= 60 mètres, la propreté de la volière est augmentée.</li>
     *   <li>Si la hauteur est > 60 mètres, la propreté de la volière est augmentée.</li>
     * </ul>
     * <p>Après chaque augmentation de la propreté, un message indiquant la nouvelle propreté de la volière est affiché.</p>
     * <p>Si aucune des conditions de hauteur n'est remplie, un message indiquant la propreté actuelle de la volière est affiché.</p>
     */
    public void maintenance(double roofHeight) {
        if (getListCreature().isEmpty()) {
            System.out.println(getClass().getSimpleName() + " : " + getName() + " est vide.");
            return;
        }
        System.out.println(getClass().getSimpleName() + " : " + getName() + " est en maintenance.");
        // TODO : thread d'un temps aléatoire dans une intervalle donné, plus la hauteur du toit est grande, plus le temps est long
        if (roofHeight <= 20) {
            // TODO
            getCleanness().clean();
            System.out.println(getClass().getSimpleName() + " : " + getName() + " est maintenant " + getCleanness().getValue() + ".");
        } else if (roofHeight > 20 && roofHeight <= 40) {
            // TODO
            getCleanness().clean();
            System.out.println(getClass().getSimpleName() + " : " + getName() + " est maintenant " + getCleanness().getValue() + ".");
        } else if (roofHeight > 40 && roofHeight <= 60) {
            // TODO
            getCleanness().clean();
            System.out.println(getClass().getSimpleName() + " : " + getName() + " est maintenant " + getCleanness().getValue() + ".");
        } else if (roofHeight > 60) {
            // TODO
            getCleanness().clean();
            System.out.println(getClass().getSimpleName() + " : " + getName() + " est maintenant " + getCleanness().getValue() + ".");
        } else {
            System.out.println(getClass().getSimpleName() + " : " + getName() + " reste " + getCleanness().getValue() + ".");
        }
    }
}