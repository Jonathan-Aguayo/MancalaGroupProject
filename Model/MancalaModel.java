package Model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * A class implements a mancala model
 */
public class MancalaModel {

    final static int MIN_INIT_STONES = 3; // Mininum number of stones in each initialize pit
    final static int MAX_INIT_STONES = 4; // Maxinum number of stones in each initialize pit
    final static int NUMBER_OF_PITS = 6; // Number of pits for each player (Mancala store not included)

    private int[] p1_Pits; // Player1's pits
    private int[] p2_Pits; // Player2's pits
    private boolean isP1Turn; // Flag determines which player takes turn
    private int turnCount; // Number of turn left of a player
    private int redoCount; // Number of redo left in each turn
    private SaveState prevState; // Previous state of model during game

    private ArrayList<ChangeListener> listeners; // List of ChangeListener in this mancala model

    /**
     * Construct a mancala model
     */
    public MancalaModel() {
        listeners = new ArrayList<>();
    }

    /**
     * Attachs a listener to mancala model
     * 
     * @param listener
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies changes to all ChangeListeners in this model
     */
    private void update() {
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Initialize a new mancala match. Player1 takes first turn
     * 
     * @param stoneAmount
     */
    public void newGame(int stoneAmount) {
        // Set stoneAmount bounded to MIN and MAX
        if (stoneAmount < MIN_INIT_STONES) {
            stoneAmount = MIN_INIT_STONES;
        } else if (stoneAmount > MAX_INIT_STONES) {
            stoneAmount = MAX_INIT_STONES;
        }

        // Last position represents mancala store of each player
        p1_Pits = new int[NUMBER_OF_PITS + 1];
        p2_Pits = new int[NUMBER_OF_PITS + 1];

        // Initialize every pits to stoneAmount
        for (int i = 0; i < NUMBER_OF_PITS; i++) {
            p1_Pits[i] = stoneAmount;
            p2_Pits[i] = stoneAmount;
        }
        isP1Turn = true;
        turnCount = 1;
        redoCount = 3;
        prevState = null;
    }

    /**
     * @return shallow copy of player1's pits and mancala store
     */
    public int[] getP1Pits() {
        return p1_Pits.clone();
    }

    /**
     * @return shallow copy of player2's pits and mancala store
     */
    public int[] getP2Pits() {
        return p2_Pits.clone();
    }

    /**
     * @return true if player1 takes turn. Otherwise, false
     */
    public boolean isP1turn() {
        return isP1Turn;
    }

    /**
     * @return number of turn left for a player (0 or 1)
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * @return number of redo left in each turn
     */
    public int getRedoCount() {
        return redoCount;
    }

    /**
     * Replicates a player's turn in mancala match.
     */
    public boolean toMove(int pitIndex) {
        // Ensures pitIndex is inbound and player still has turn
        if (pitIndex < 0 || pitIndex >= NUMBER_OF_PITS || turnCount <= 0) {
            return false;
        }

        int[] a1 = p1_Pits; // Player1's turn
        int[] a2 = p2_Pits;

        if (!isP1Turn) {
            a1 = p2_Pits; // Player2's turn
            a2 = p1_Pits;
        }

        if (a1[pitIndex] == 0) {
            return false; // Selected pit is empty
        }

        prevState = new SaveState(p1_Pits, p2_Pits, isP1Turn); // Stores previous state

        turnCount--; // Decrement amount of turn left
        int stoneAmount = a1[pitIndex]; // Get all stones in selected pit
        a1[pitIndex] = 0; // Empty pit
        pitIndex++;
        while (stoneAmount > 0) { // Keep moving until player run out of stone

            // Drop stones on player's own pits including mancala store
            while (stoneAmount > 0 && pitIndex < a1.length) {
                a1[pitIndex]++;
                stoneAmount--;
                if (stoneAmount == 0) {
                    if (pitIndex == NUMBER_OF_PITS) { // Last stone on mancala store
                        turnCount++;
                    } else if (a1[pitIndex] == 1) { // Last stone on an empty pit
                        steal(a1, a2, pitIndex);
                    }
                }
                pitIndex++;
            }
            pitIndex = 0;

            // Drop stone on opponent's pits
            while (stoneAmount > 0 && pitIndex < a2.length - 1) {
                a2[pitIndex]++;
                stoneAmount--;
                pitIndex++;
            }
            pitIndex = 0;
        }
        this.update(); // Notifies all Changelisteners
        return true;
    }

    /**
     * Restore macala match to previous state
     */
    public void redo() {
        // Ensures that previous state is valid and player still can redo
        if (prevState != null && redoCount > 0) {
            turnCount = 1; // Return a turn to player
            redoCount--; // Decrement amount of redo left
            p1_Pits = prevState.getP1Pits();
            p2_Pits = prevState.getP2Pits();
            isP1Turn = prevState.getTurn();
            prevState = null; // Invalidate previous state
            this.update(); // Notifies all Changelisteners
        }
    }

    /**
     * Switch turn from one player to another. Re-initialize flags
     */
    public void endTurn() {
        if (turnCount <= 0) {
            isP1Turn = !isP1Turn; // Opposite player's turn
            turnCount = 1;
            redoCount = 3;
            prevState = null; // Invalidate previous state
            this.update(); // Notifies all Changelisteners
        }
    }

    /**
     * Steals all stones in opposite pit and places in mancala store
     */
    private void steal(int[] a1, int[] a2, int pitIndex) {
        int opponentPitIndex = NUMBER_OF_PITS - (pitIndex + 1);
        a1[NUMBER_OF_PITS] += a1[pitIndex] + a2[opponentPitIndex];
        a1[pitIndex] = a2[opponentPitIndex] = 0;
    }

    /**
     * @return true if one of the player has all pits empty. Otherwise, false
     */
    public boolean hasEmptyPits() {
        int i;
        for (i = 0; i <= NUMBER_OF_PITS - 1 && p1_Pits[i] == 0; i++) {
            if (i == NUMBER_OF_PITS - 1) {
                return true;
            }
        }
        for (i = 0; i <= NUMBER_OF_PITS - 1 && p2_Pits[i] == 0; i++) {
            if (i == NUMBER_OF_PITS - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Winner. (Will be modified to match with GUI)
     */
    public String getResult() {
        if (!hasEmptyPits()) {
            return "Not Finish";
        }
        for (int i = 0; i < NUMBER_OF_PITS; i++) {
            p1_Pits[NUMBER_OF_PITS] += p1_Pits[i];
            p2_Pits[NUMBER_OF_PITS] += p2_Pits[i];
            p1_Pits[i] = p2_Pits[i] = 0;
        }
        System.out.println(this);
        this.update();
        int result = p1_Pits[NUMBER_OF_PITS] - p2_Pits[NUMBER_OF_PITS];
        if (result > 0) {
            return "Player1 Win";
        }
        if (result < 0) {
            return "Player2 Win";
        }
        return "Draw";
    }

    /**
     * @return String repesentation of mancala match's current state. (Will be
     *         remove after GUI is done)
     */
    public String toString() {
        String s = "   | ";
        for (int i = NUMBER_OF_PITS - 1; i >= 0; i--) {
            s += p2_Pits[i] + " ";
        }
        s += String.format("|\n%2d | a b c d e f | %-2d\n   | ", p2_Pits[NUMBER_OF_PITS], p1_Pits[NUMBER_OF_PITS]);
        for (int i = 0; i < NUMBER_OF_PITS; i++) {
            s += p1_Pits[i] + " ";
        }
        return s + "|";
    }

    /**
     * A class that stores a state in a mancala match
     */
    private static class SaveState {
        private int[] p1_Pits;
        private int[] p2_Pits;
        private boolean isP1turn;

        private SaveState(int[] srcP1_Pits, int[] srcP2_Pits, boolean isP1Turn) {
            p1_Pits = srcP1_Pits.clone();
            p2_Pits = srcP2_Pits.clone();
            this.isP1turn = isP1Turn;
        }

        private int[] getP1Pits() {
            return p1_Pits;
        }

        private int[] getP2Pits() {
            return p2_Pits;
        }

        private boolean getTurn() {
            return isP1turn;
        }
    }
}