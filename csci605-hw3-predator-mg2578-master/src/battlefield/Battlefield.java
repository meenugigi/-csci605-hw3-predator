/*
Homework 3 : Predator
File Name : Battlefield.java
 */
package battlefield;

import players.Guerrilla;
import players.Hostage;
import players.Predator;
import players.Soldier;

import java.util.Random;

import static battlefield.Bunker.bunker;
import static battlefield.EnemyBase.hostageCave;

/**
 * The main simulation class is run on the command line as:<br>
 * <br>
 * $ java Battlefield #_seed #_hostages #_soldiers #_guerrillas
 *
 * @author RIT CS
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class Battlefield {

    public static int seed = 0;
    public static int numHostages = 0;
    public static int numSoldiers = 0;
    public static int numGuerrillas = 0;
    EnemyBase enemyBase;
    Chopper chopper;
    Bunker soldierBunker;

    /**
     * the single instance of the random number generator
     */
    private static Random rnd = new Random();


    /**
     * Create the battlefield.  This method is responsible for seeding the
     * random number generator and initializing all the supporting classes
     * in the simulation: Chopper, EnemyBase, Bunker and Predator.
     *
     * @param seed          the seed for the random number generator
     * @param numHostages   number of hostages being held in enemy base at start
     * @param numSoldiers   number of soldiers to rescue the hostages at start
     * @param numGuerrillas number of guerrillas in the enemy base at start
     */
    public Battlefield(int seed, int numHostages, int numSoldiers, int
            numGuerrillas) {
        setSeed(seed);
        enemyBase = new EnemyBase(numHostages, numGuerrillas);
        soldierBunker = new Bunker(numSoldiers);
        chopper = new Chopper();

    }

    /**
     * The main method expects there to be four command line arguments:<br>
     * <br>
     * 1: the seed for the random number generator (a positive integer)<br>
     * 2: the number of hostages (a positive integer)<br>
     * 3: the number of soldiers (a positive integer)<br>
     * 4: the number of guerrillas (a positive integer)<br>
     * <br>
     * If all the arguments are supplied it will create the battlefield
     * and then begin the battle.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: Battlefield #_seed #_hostages " +
                    "#_soldiers #_guerrillas");
            System.exit(0);
        } else {
            seed = Integer.parseInt(args[0]);
            numHostages = Integer.parseInt(args[1]);
            numSoldiers = Integer.parseInt(args[2]);
            numGuerrillas = Integer.parseInt(args[3]);

            //TODO Initialize the battlefield and run the simulation
            Battlefield battlefield = new Battlefield(seed, numHostages,
                    numSoldiers, numGuerrillas);
            battlefield.runSimulation();
        }
    }

    /**
     * Generate a random integer between min and max inclusive.  For example:
     * <br>
     * <br>
     * <tt>Battlefield.nextInt(1, 5): A random number, 1-5</tt><br>
     * <br>
     *
     * @param min the smallest value allowed.
     * @param max the largest value allowed.
     * @return A random integer
     */
    public static int nextInt(int min, int max) {
        return rnd.nextInt(min, max + 1);
    }

    /**
     * Set the seed to the pseudorandom number generator
     *
     * @param seed the seed
     */
    private static void setSeed(int seed) {
        //setting the seed for the pseudorandom generator
        rnd.setSeed(seed);
    }

    public void runSimulation() {
        enemyBase.addGuerrilla(new Guerrilla(1));
        enemyBase.addHostage(new Hostage(1));
        new Bunker(numSoldiers);


        System.out.println("Get to the choppa!");
        System.out.println("Statistics: " + numHostages + " hostage/s remain, " +
                numSoldiers + " soldier/s remain, " + numGuerrillas +
                " guerrilla/s remain, " + chopper.getNumRescued() +
                " rescued");

        while (soldierBunker.hasSoldiers() && !hostageCave.isEmpty()) {
            Soldier soldier = soldierBunker.deployNextSoldier();
            Hostage hostageInFront = enemyBase.rescueHostage(soldier);

            //if hostage is rescued from guerrilla.
            if (hostageInFront != null) {
                System.out.println(hostageInFront + " rescued from enemy " +
                        "base" + " by " + soldier);
                Predator predator = new Predator();
                int randomNumber01 = nextInt(1, 100);

                //soldier encounters predator.
                System.out.println(soldier + " encounters the predator who " +
                        "rolls a" + " " + randomNumber01);
                if (randomNumber01 > Predator.CHANCE_TO_BEAT_SOLDIER) {

                    //soldier wins against predator.
                    soldier.victory(predator);
                    predator.defeat(soldier);

                    //adds soldier back to the end of the bunker.
                    soldierBunker.fortifySoldier(soldier);
                    //hostage boards the chopper.
                    chopper.boardPassenger(hostageInFront);
                    System.out.println("Statistics: " +
                            enemyBase.getNumHostages() + " hostage/s remain, "
                            +
                            soldierBunker.getNumSoldiers() + " soldier/s remain, " +
                            enemyBase.getNumGuerrillas() +
                            " guerrilla/s remain, " + chopper.getNumRescued()
                            +
                            " rescued");

                }

                //soldier looses against predator.
                else {
                    predator.victory(soldier);
                    soldier.defeat(predator);
                    int randomNumber02 = nextInt(1, 100);

                    //hostage encounters predator.
                    System.out.println(hostageInFront + " encounters the " +
                            "predator who " + "rolls a " + randomNumber02);
                    if (randomNumber02 <= Predator.CHANCE_TO_BEAT_HOSTAGE) {

                        //predator wins against hostage.
                        predator.victory(hostageInFront);
                        hostageInFront.defeat(predator);
                    }

                    //predator looses against hostage.
                    else {

                        hostageInFront.victory(predator);
                        predator.defeat(hostageInFront);

                        //hostage boards chopper.
                        chopper.boardPassenger(hostageInFront);
                    }
                    System.out.println("Statistics: " +
                            enemyBase.getNumHostages() +
                            " hostage/s remain, " + soldierBunker.getNumSoldiers() +
                            " soldier/s remain, " +
                            enemyBase.getNumGuerrillas() +
                            " guerrilla/s remain, " +
                            chopper.getNumRescued() + " rescued");
                }
            } else {
                System.out.println("Statistics: " +
                        enemyBase.getNumHostages()
                        + " hostage/s remain, " +
                        soldierBunker.getNumSoldiers() + " soldier/s remain, " +
                        enemyBase.getNumGuerrillas() +
                        " guerrilla/s remain, " + chopper.getNumRescued() +
                        " rescued");
            }
        }

        //rescues remaining passengers to safety.
        if (bunker.isEmpty()) {
            chopper.rescuePassengers();

        }

        //soldiers left in bunker boards chopper.
        while (soldierBunker.hasSoldiers() && hostageCave.isEmpty()) {
            chopper.boardPassenger(bunker.poll());
        }

        //rescues passengers boarded on chopper to safety.
        chopper.rescuePassengers();
        System.out.println("Statistics: " + enemyBase.getNumHostages() +
                " hostage/s remain, " + soldierBunker.getNumSoldiers() + " soldier/s remain, " +
                enemyBase.getNumGuerrillas() + " guerrilla/s remain, " +
                chopper.getNumRescued() + " rescued");
    }
}
