package api;

import java.util.Map;
import java.util.List;

/*
 * The table class represents a place in which card games happen. You
 * should specialise it specifically for Black Jack.
 *
 * Do not re-declare the attributes in the child class! They merely
 * need memory allocated, and then they can be used.
 */
public abstract class Table {
    /*
     * A list of players
     */
    protected List<Player> players;

    /*
     * The dealer
     */
    protected Dealer dealer;

    /*
     * Keeps track of wagers made during a round
     */
    protected Map<Player, Double> wagers;

    /*
     * A game is over when there are no players, or no players with
     * money to bet
     */
    public abstract boolean isGameOver();

    /*
     * A string representation of the table
     */
    public abstract String toString();

    /*
     * Collect cards from all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected abstract void collectCards();

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected abstract void dealTable();
    
    /*
     * Collect bets from all players at the table
     */
    protected abstract void collectBets();

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should havea turn as well!
     */
    protected abstract void playerTurns();

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected abstract void playerEvaluations();
    
    /*
     * A method that brings together actions of a round
     */
    public void round() {
        collectBets();
        dealTable();
        playerTurns();
        playerEvaluations();
        collectCards();
    }
}