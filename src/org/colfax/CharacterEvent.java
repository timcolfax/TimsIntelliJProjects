package org.colfax;

/**
 * Created by colfax on 11/16/2014.
 */
public class CharacterEvent {
    public CharacterSource source;
    public int character;

    public CharacterEvent(CharacterSource cs, int c){
        source      = cs;
        character   = c;
    }
}
