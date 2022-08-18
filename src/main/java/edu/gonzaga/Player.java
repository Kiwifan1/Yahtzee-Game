package edu.gonzaga;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Player implements PropertyChangeListener {
    private Hand hand;
    private String name;

    private UpperScorecard upperScorecard;
    private LowerScorecard lowerScorecard;

    private GameConfiguration config;

    private int turn;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Player(String name, GameConfiguration config) {
        this.name = name;
        this.config = config;

        turn = 1;

        hand = new Hand(config);
        upperScorecard = new UpperScorecard(config);
        lowerScorecard = new LowerScorecard(config);

        upperScorecard.addPropertyChangeListener(this::propertyChange);
        lowerScorecard.addPropertyChangeListener(this::propertyChange);
    }

    /**
     * Registers a PropertyChangeListener to this class.
     * 
     * @param listener the listener to register.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener to this class.
     * 
     * @param listener the listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /**
     * Tells if this player has their scorecard full or not.
     * 
     * @return
     */
    public boolean gameOver() {
        return upperScorecard.isFull() && lowerScorecard.isFull();
    }

    /**
     * Advanced the turn for this player.
     */
    public void newTurn() {
        hand.newTurn();
        turn++;
    }

    /**
     * Scores the players current hand
     * 
     * @return the scorecard with the new scored values in it.
     */
    public ArrayList<Scorecard> score() {
        upperScorecard.scoreNewHand(hand);
        lowerScorecard.scoreNewHand(hand);
        ArrayList<Scorecard> scorecards = new ArrayList<>();
        scorecards.add(upperScorecard);
        scorecards.add(lowerScorecard);
        return scorecards;
    }

    public UpperScorecard getUpperScorecard() {
        return upperScorecard;
    }

    public void setUpperScorecard(UpperScorecard upperScorecard) {
        this.upperScorecard = upperScorecard;
    }

    public LowerScorecard getLowerScorecard() {
        return lowerScorecard;
    }

    public void setLowerScorecard(LowerScorecard lowerScorecard) {
        this.lowerScorecard = lowerScorecard;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("scored") || evt.getPropertyName().equals("total")) {
            pcs.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        }
    }

    /**
     * This function returns the hand of the player
     * 
     * @return The hand object.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * This function returns the turn of the player
     * 
     * @return The turn variable is being returned.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * This function returns the name of the person
     * 
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description returns if the players scorecard is full or not.
     * @pre
     * @post
     **/
    public boolean isFull() {
        return lowerScorecard.isFull() && upperScorecard.isFull();
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description returns the combined upper and lower scorecard of this player.2
     * @pre
     * @post
     **/
    public int totalScore() {
        return lowerScorecard.getTotalLine().getValue() + upperScorecard.getTotalLine().getValue();
    }
}
