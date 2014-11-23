package org.colfax.chapter02.example02;

import java.util.*;
import org.colfax.*;

public class RandomCharacterGenerator extends Thread implements CharacterSource {
    static char[] chars;
    static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
    static {
        chars = charArray.toCharArray();
    }

    Random random;
    CharacterEventHandler handler;

    /**
     * Constructor.
     */
    public RandomCharacterGenerator() {
        random = new Random();
        handler = new CharacterEventHandler();
    }

    /**
     * Each client pauses for a random period.
     * @return The number of milliseconds to wait.
     */
    public int getPauseTime() {
        return (int) (Math.max(1000, 5000 * random.nextDouble()));
    }

    /**
     * Registers a character listener client with this Generator.
     * @param cl The character listener to register.
     */
    public void addCharacterListener(CharacterListener cl) {
        handler.addCharacterListener(cl);
    }

    /**
     * Un-register a character listener client with this Generator.
     * @param cl The character listener to un-register.
     */
    public void removeCharacterListener(CharacterListener cl) {
        handler.removeCharacterListener(cl);
    }

    /**
     * Broadcasts a new random character to the listeners.
     */
    public void nextCharacter() {
        handler.fireNewCharacter(this,
                (int) chars[random.nextInt(chars.length)]);
    }

    /**
     * The continuously-looping run method.
     */
    public void run() {
        for (;;) {
            /* Broadcast... */
            nextCharacter();
            try {
                /* Sleep... */
                Thread.sleep(getPauseTime());
            } catch (InterruptedException ie) {
                return;
            }
        }
    }
}

