/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 4/21/22
 * Programming Assigment: Final Yahtzee Project
 * Description: This class controls the game
 * Notes: 
 * 
 * 
 **/

package edu.gonzaga;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.WindowConstants;

import edu.gonzaga.dialogs.ConfigurationDialog;
import edu.gonzaga.dialogs.PlayerDialog;
import edu.gonzaga.views.PlayerView;

public class GameControl implements PropertyChangeListener {

    private ArrayList<String> names;
    ArrayList<Player> players;
    private ArrayList<PlayerView> playerViews;
    private int playerTurn = 0;
    private JFrame frame = new JFrame();

    public GameControl() {
        frame.setSize(854, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ConfigurationDialog dialog = new ConfigurationDialog(frame);
        dialog.setVisible(true);
        GameConfiguration config = dialog.getPayload();

        if (config != null) {
            PlayerDialog playerDialog = new PlayerDialog(frame);
            playerDialog.setVisible(true);

            names = playerDialog.getPayload();
            if (!names.isEmpty()) {
                players = new ArrayList<>();
                for (String s : names) {
                    players.add(new Player(s, config));
                }

                playerViews = new ArrayList<>();

                // add propertyChangeListeners to players so they can see if player changes
                for (Player player : players) {
                    PlayerView playerView = new PlayerView(player);
                    playerView.addPropertyChangeListener(this::propertyChange);
                    playerViews.add(playerView);
                }
                startNextPlayerRound();
            }
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 4/22/22;
     *       Date last modified: 4/22/22
     * @Description makes the next player's content pane visible
     * @pre previously visible player content pane
     * @post invisible previous player content pane, visible current player content
     *       pane
     **/
    private void startNextPlayerRound() {
        if (playerTurn >= playerViews.size()) {
            playerTurn = 0;
        }
        changeView(playerViews.get(playerTurn));
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description sets the main view of the screen.
     * @pre previously visible screen in content pane
     * @post invisible previous screen content pane, visible new screen content pane
     **/
    private void changeView(Container container) {
        frame.setVisible(false);
        frame.setContentPane(container);
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("nextPlayer")) {
            if (!checkGameEnd()) { // If game not over
                playerTurn++;
                startNextPlayerRound();
            } else { // If game over
                for (Player player : players) {
                    player.getUpperScorecard().checkBonus();
                }
                setWinnerScreen(findWinner());
            }
        }
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description sets teh screen on a player in winner form.
     * @pre previously visible screen in content pane
     * @post the winner's screen
     **/
    private void setWinnerScreen(Player player) {
        PlayerView winnerView = getPlayerView(player);
        winnerView.setWinnerView();
        changeView(winnerView);
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description returns the view corresponding to a player
     * @pre
     * @post
     **/
    private PlayerView getPlayerView(Player player) {
        for (PlayerView view : playerViews) {
            if (view.getPlayer().equals(player)) {
                return view;
            }
        }
        return null;
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description determines the player with the highest total of points
     * @pre
     * @post
     **/
    private Player findWinner() {
        int total = 0;
        Player winner = null;
        for (Player player : players) {
            if (player.totalScore() > total) {
                winner = player;
                total = player.totalScore();
            }
        }
        return winner;
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description Checks if an endgame condition has been met
     * @pre
     * @post
     **/
    private boolean checkGameEnd() {
        return checkSameTurn() && checkScorecardFull();
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description Checks if all players have done the same turn.
     * @pre
     * @post
     **/
    private boolean checkSameTurn() {
        return playerTurn == playerViews.size() - 1;
    }

    /**
     * @Author Tyler CH
     * @Date created: 4/24/22;
     *       Date last modified: 4/24/22
     * @Description Checks if all players scorecards are full
     * @pre
     * @post
     **/
    private boolean checkScorecardFull() {
        boolean result = true;
        for (Player player : players) {
            result = result && player.isFull();
        }
        return result;
    }
}
