package net.zoofantastique.controller.creature.composition;

import net.zoofantastique.controller.entity.creature.behavior.Age;
import net.zoofantastique.controller.entity.creature.behavior.Gender;
import net.zoofantastique.controller.entity.creature.composition.oviparous.Dragon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DragonTest {
    private Dragon Dragon;

    @BeforeEach
    void setUp() {
        Dragon = new Dragon("Test Dragon", Gender.FEMALE, 10.0, 1.0);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void flyPrintsExpectedOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Dragon.fly();
        assertEquals("Test Dragon *est entrain de voler*", outContent.toString().trim());
    }

    @Test
    void runPrintsExpectedOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Dragon.run();
        assertEquals("Test Dragon *cours*", outContent.toString().trim());
    }

    @Test
    void swimPrintsExpectedOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Dragon.swim();
        assertEquals("Test Dragon *nage*", outContent.toString().trim());
    }

    @Test
    void setAgeResetsAgeWhenDead() {
        Dragon.setAge(Age.DEAD);
        assertEquals(Age.BABY, Dragon.getAge());
    }

    @Test
    void eggsHatchReturnsBabyDragon() {
        Dragon baby = Dragon.eggsHatch();
        assertNotNull(baby);
        assertEquals(Age.BABY, baby.getAge());
    }

    @Test
    void rebirthResetsAgeWhenDead() {
        Dragon.setAge(Age.DEAD);
        Dragon.rebirth();
        assertEquals(Age.BABY, Dragon.getAge());
    }

    @Test
    void rebirthDoesNotResetAgeWhenAlive() {
        Dragon.setAge(Age.ADULT);
        Dragon.rebirth();
        assertEquals(Age.ADULT, Dragon.getAge());
    }
}
