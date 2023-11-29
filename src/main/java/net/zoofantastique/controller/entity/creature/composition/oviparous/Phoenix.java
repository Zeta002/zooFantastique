package net.zoofantastique.controller.entity.creature.composition.oviparous;

import net.zoofantastique.controller.entity.creature.behavior.Age;
import net.zoofantastique.controller.entity.creature.behavior.Flying;
import net.zoofantastique.controller.entity.creature.behavior.Gender;
import net.zoofantastique.controller.entity.creature.behavior.Rebirth;

import java.util.Random;

/**
 * Classe Phoenix qui représente un phoenix dans un zoo.
 * Un phoenix est une créature ovipare qui peut voler et renaître.
 * Cette classe étend la classe Oviparous et implémente les interfaces Flying et Rebirth.
 */
public class Phoenix extends Oviparous implements Flying, Rebirth {
    public Phoenix(String name, Gender sexe, double weight, double height) {
        super(name, sexe, weight, height, "coua coua");
    }

    /**
     * Méthode pour faire voler un Phoenix.
     * Affiche un message indiquant que le Phoenix vole, en précisant le nom du Phoenix.
     */
    @Override
    public void fly() {
        System.out.println(super.getName() + " *est entrain de voler*");
    }

    /**
     * Méthode pour faire vieillir un Phoenix.
     * Cette méthode met à jour l'âge du Phoenix à l'âge suivant dans l'énumération Age, puis appelle la méthode rebirth pour vérifier si le Phoenix doit renaître.
     */
    @Override
    public void aging() {
        super.aging();
        rebirth();
    }

    /**
     * Méthode pour définir l'âge d'un Phoenix.
     * Cette méthode met à jour l'âge du Phoenix et appelle ensuite la méthode rebirth pour vérifier si le Phoenix doit renaître.
     *
     * @param age Le nouvel âge du Phoenix.
     */
    @Override
    public void setAge(Age age) {
        super.setAge(age);
        rebirth();
    }

    /**
     * Méthode pour faire renaître un Phoenix.
     * Si l'âge du Phoenix est DEAD (mort), son âge est réinitialisé à BABY (bébé).
     */
    @Override
    public void rebirth() {
        if (getAge() == Age.DEAD) {
            setAge(Age.BABY);
        }
    }

    /**
     * Méthode pour faire éclore les œufs d'un Phoenix.
     * Cette méthode génère un nouveau Phoenix avec un sexe, un poids et une taille aléatoires.
     * Le sexe est déterminé de manière aléatoire, avec une probabilité égale d'être mâle ou femelle.
     * Le poids et la taille sont déterminés en générant un nombre aléatoire dans un intervalle spécifique.
     *
     * @return Un nouveau Phoenix qui vient d'éclore.
     */
    @Override
    public Phoenix eggsHatch(){
        Random random = new Random();
        Gender babySexe = Gender.MALE;

        double babyWeight = getRandomInRange(50, 80);
        double babyHeight = getRandomInRange(2, 5);

        if (random.nextInt(2) == 1) {
            babySexe = Gender.FEMALE;
        }

        return new Phoenix(getName(), babySexe, babyWeight, babyHeight);
    }
}