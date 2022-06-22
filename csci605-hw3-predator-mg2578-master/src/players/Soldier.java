/*
Homework 3 : Predator
File Name : Soldier.java
 */
package players;

/**
 * A class to represent a soldier, each whom has a unique ID.
 *
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class Soldier implements Player {
    private int soldierID;

    /**
     * Create a new soldier.
     * @param id the id of the soldier
     */
    public Soldier(int id){
        this.soldierID = id;
    }

    /**
     * If the soldier is triumphant over player, it displays the message,
     * "{soldier} yells, 'Sieg über {player}!'".
     * @param player the player that I defeated
     */
    @Override
    public void victory(Player player) {
        System.out.println(this + " yells, 'Sieg über " +
                player + "!'");
    }

    /**
     *If the soldier losses to player, it displays the message, "{soldier}
     * cries, 'Besiegt von {player}!'".
     * @param player the player that defeated me
     */
    @Override
    public void defeat(Player player) {
        System.out.println( this + " cries, 'Besiegt von " +
                player + "!'");
    }

    /**
     * The string representation of a soldier is: "Soldier #n", where n is
     * their id.
     * @return the soldier string
     */
    @Override
    public String toString(){
        return "Soldier #" + soldierID;
    }
}
