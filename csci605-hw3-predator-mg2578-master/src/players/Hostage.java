/*
Homework 3 : Predator
File Name : Guerrilla.java
 */
package players;

/**
 * A class to represent a hostage, each whom has a unique ID.
 *
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class Hostage implements Player {
    private int hostageID;

    /**
     * Create a new hostage.
     * @param id the id of the hostage
     */
    public Hostage(int id){
        this.hostageID = id;
    }

    /**
     * If the hostage is triumphant over player, it displays the message,
     * "{hostage} yells, 'Victory over {player}!'".
     * @param player the player that I defeated
     */
    @Override
    public void victory(Player player) {
        System.out.println(this + " yells, 'Victory over " +
                player + "!'");
    }

    /**
     * If the hostage losses to player, it displays the message, "{hostage}
     * cries, 'Defeated by {player}!'".
     * @param player the player that defeated me
     */
    @Override
    public void defeat(Player player) {
        System.out.println(this + " cries, 'Defeated by " +
                player + "!'");
    }

    /**
     * The string representation of a hostage is: "Hostage #n", where n is
     * their id.
     * @return the hostage string
     */
    @Override
    public String toString(){
        return "Hostage #" + hostageID;
    }
}
