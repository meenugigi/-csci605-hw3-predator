/*
Homework 3 : Predator
File Name : Chopper.java
 */
package battlefield;

import players.Player;

import java.util.Stack;


/**
 * A class that represent the chopper. The chopper can hold up to 6 passengers
 * aligned in a single columns of seats. There is only one door to the chopper
 * that is accessible by the passengers. When they enter the chopper, the
 * occupy the seat closest to the door and any existing passengers move one
 * seat down.
 *
 * @author Meenu Gigi, mg2578@rit.edu
 * @author Vedika Vishwanath Painjane, vp2312@rit.edu
 */
public class Chopper {

    public static Stack<Player> numOfSeatsOccupied;
    private static final int CHOPPER_MAX_CAPACITY = 6;
    private static int passengersRescued = 0;

    /**
     * Create the chopper so that all the passenger seats are empty and none
     * have been rescued yet.
     */
    public Chopper(){
        numOfSeatsOccupied = new Stack<>();
    }

    /**
     * When the chopper is full, or it is the last group of people to be
     * rescued, it will fly away and rescue the passengers. Each passenger
     * exits the chopper in the reverse order they entered it, e.g. the last
     * passenger to enter exits first.
     */
    public void rescuePassengers(){
        while (!isEmpty()){
            System.out.println("Chopper transported "+
                    numOfSeatsOccupied.pop() + " to safety!");
            passengersRescued++;
        }
    }

    /**
     * Board a passenger onto the chopper. If the chopper is full, it must
     * first fly away and rescue the passengers on it (hint, use the
     * rescuePassengers() helper method). Otherwise, the passenger boards the
     * chopper and occupies the front seat, making everyone else in the
     * chopper move down a seat. When the passenger boards the chopper display
     * the message "{passenger} boards the chopper!".
     * @param player the player boarding the chopper
     */
    public void boardPassenger(Player player){

        if(isFull()){
            rescuePassengers();
        }
        if(numOfSeatsOccupied.size() < CHOPPER_MAX_CAPACITY){
            numOfSeatsOccupied.push(player);
            System.out.println(player + " boards the chopper!");
        }
    }

    /**
     * Is the chopper full?
     * @return Whether or not the chopper has reached max occupancy or not
     */
    public boolean isFull(){
        if(numOfSeatsOccupied.size() == CHOPPER_MAX_CAPACITY){
            return true;
        }
        return false;
    }

    /**
     * Is the chopper empty?
     * @return Whether the chopper is empty or not
     */
    public boolean isEmpty(){
        return numOfSeatsOccupied.isEmpty();
    }

    /**
     * Get the total number of passengers that were rescued.
     * @return number of rescued passengers
     */
    public int getNumRescued(){
        return passengersRescued;
    }
}

