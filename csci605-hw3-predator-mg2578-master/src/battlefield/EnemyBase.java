/*
Homework 3 : Predator
File Name : EnemyBase.java
 */
package battlefield;

import players.Guerrilla;
import players.Hostage;
import players.Soldier;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static battlefield.Battlefield.*;
import static players.Guerrilla.CHANCE_TO_BEAT_SOLDIER;

/**
 * This class represents the enemy base. It contains a guard line of
 * guerrillas that are guarding a group of hostages stored in a narrow cave
 * with only an entrance and now way for the hostages to reorder themselves.
 *
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class EnemyBase {

    private static Queue<Guerrilla> guerrillaGuardLine;
    public static Stack<Hostage> hostageCave;

    /**
     * Create the enemy base with a number of hostages, 1-numHostages, pushed
     * into the cave in order, and a number of guerrillas, 1-numguerrillas,
     * added to the guard line in order.
     * @param numHostages the number of hostages to start in enemy base
     * @param numGuerrillas the number of guerrillas to start in enemy base
     */
    public EnemyBase(int numHostages,int numGuerrillas){
        guerrillaGuardLine = new LinkedList<>();
        hostageCave = new Stack<>();
    }

    /**
     * Add a guerrilla to the end of the guard line.
     * @param guerrilla guerrilla to add
     */
    public void addGuerrilla(Guerrilla guerrilla){
        int i=1;
        //creates a guerrilla and adds them to guard line.
        while(i<= numGuerrillas){
            guerrillaGuardLine.add(new Guerrilla(i));
            i++;
        }
    }

    /**
     * Add a hostage to the front of the cave.
     * @param hostage hostage to add
     */
    public void addHostage(Hostage hostage){
        int j=1;
        //creates hostage and adds them to hostage cave.
        while (j <= numHostages){
            hostageCave.add(new Hostage(j));
            j++;
        }
    }

    /**
     * Remove a guerrilla from the front of the guard line.
     * @return the front guerrilla
     */
    public Guerrilla getGuerrilla(){
        return guerrillaGuardLine.poll();
    }

    /**
     * Remove a hostage from the head of the cave.
     * @return the hostage at the head of the cave
     */
    public Hostage getHostage(){
        return hostageCave.pop();
    }

    /**
     * A soldier enters the enemy base and attempts to rescue a hostage. First
     * they must defeat the guerrilla at the front of the guard line, then
     * they can rescue one hostage at the front of the cave.
     * @param soldier the rescuing solder.
     * @return if a hostage was rescued the hostage, otherwise if the soldier
     * failed, null
     */
    public Hostage rescueHostage(Soldier soldier) {

        System.out.println(soldier + " enters enemy base...");

        //Remove and holds the hostage at the front of the cave.
        Hostage hostageInFront = getHostage();
        if (guerrillaGuardLine.isEmpty()) {
            return hostageInFront;
        }
        else {
            //get next guerrilla from guard line.
            Guerrilla fighterGuerrilla = getGuerrilla();
            int randomNumber = nextInt(1,100);
            System.out.println(soldier + " battles " + fighterGuerrilla +
                    " who rolls a " + randomNumber);
            //soldier encounters guerrilla.
            if (randomNumber > CHANCE_TO_BEAT_SOLDIER) {
                //soldier wins against guerrilla.
                soldier.victory(fighterGuerrilla);
                fighterGuerrilla.defeat(soldier);
                return hostageInFront;
            } else {
                //guerrilla wins against soldier.
                fighterGuerrilla.victory(soldier);
                soldier.defeat(fighterGuerrilla);
                hostageCave.add(hostageInFront);
                guerrillaGuardLine.add(fighterGuerrilla);
                return null;
            }
        }
    }

    /**
     * Get the number of hostages in the cave.
     * @return number of hostages
     */
    public int getNumHostages(){
        return hostageCave.size();
    }

    /**
     * Get the number of guerrillas in the guard line.
     * @return number of guerrillas
     */
    public int getNumGuerrillas(){
        return guerrillaGuardLine.size();
    }

}
