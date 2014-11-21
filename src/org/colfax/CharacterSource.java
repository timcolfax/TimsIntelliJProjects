package org.colfax;

/**
 * Created by colfax on 11/16/2014.
 */
public interface CharacterSource {
    public void addCharacterListener(CharacterListener cl);
    public void removeCharacterListener(CharacterListener cl);
    public void nextCharacter();
}
