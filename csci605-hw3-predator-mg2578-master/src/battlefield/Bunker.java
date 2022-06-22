/*
Homework 3 : Predator
File Name : Bunker.java
 */
package battlefield;

import players.Soldier;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that represents the bunker of soldiers.
 *
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class Bunker {
    public static Queue<Soldier> bunker;

    /**
     * Create the bunker. Based on the total number of soldiers, each one
     * should be created here and then added to the bunker, with id's ranging
     * from 1-numSoldiers.
     * @param numSoldiers the total number of soldiers that start in the bunker
     */
    Bunker(int numSoldiers){
//        creates bunker for soldiers.
        bunker = new LinkedList<>();
        int i=1;
//        adds soldiers to bunker.
        while(i<= numSoldiers){
            bunker.add(new Soldier(i));
            i++;
        }

    }

    /**
     * Are their soldiers left in the bunker?
     * @return whether the bunker has soldiers or not
     */
    public boolean hasSoldiers(){
        return !bunker.isEmpty();
    }

    /**
     * Remove the next soldier from the front of the bunker to be deployed on
     * a rescue attempt.
     * @return the soldier at the front of the bunker
     */
    public Soldier deployNextSoldier(){
        return bunker.poll();
    }

    /**
     * Add a new soldier to the end of the bunker.
     * @param soldier the new soldier to add
     */
    public void fortifySoldier(Soldier soldier){
        bunker.add(soldier);
    }

    /**
     * Get how many soldiers are in the bunker.
     * @return number of soldiers in the bunker
     */
    public int getNumSoldiers(){
        return bunker.size();
    }
}
