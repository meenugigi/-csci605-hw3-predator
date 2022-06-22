/*
Homework 3 : Predator
File Name : Guerrilla.java
 */
package players;

/**
 * A class to represent a guerrilla, each whom has a unique ID.
 *
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class Guerrilla implements Player {
    /**
     * The chance the guerrilla has to defeat a soldier. If a die roll of
     * 1-100 is less than or equal to this, the guerrilla defeats the soldier,
     * otherwise the guerrilla loses.
     */
    public static final int CHANCE_TO_BEAT_SOLDIER = 20;
    private int guerrillaID;

    /**
     * Create a new guerrilla.
     * @param id the id of the guerrilla
     */
    public Guerrilla(int id){
        this.guerrillaID = id;
    }

    /**
     * If the guerrilla is triumphant over player, it displays the message,
     * "{guerrilla} yells, 'Victoria sobre {player}!'".
     * @param player the player that I defeated
     */
    @Override
    public void victory(Player player) {
        System.out.println(this + " yells, 'Victoria sobre " +
                player + "!'");
    }

    /**
     * If the guerrilla losses to player, it displays the message,
     * "{guerrilla} cries, 'Derrotado por {player}!'".
     * @param player the player that defeated me
     */
    @Override
    public void defeat(Player player) {
        System.out.println(this +" cries, 'Derrotado por " +
                player + "!'");
    }

    /**
     * The string representation of a guerrilla is: "Guerrilla #n", where n is
     * their id.
     * @return the guerrilla string
     */
    @Override
    public String toString(){
        return "Guerrilla #" + guerrillaID;
    }
}
