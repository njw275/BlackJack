package impl;
import api.Player;
import api.Card;
import api.Hand;

import java.util.Random;


/*
 * A card player.
 */

public class BlackJackPlayer implements Player {
	
	private double chips;
	public Hand hand; 
	public double wager;
	public String playerName;
	public int suit;

	
	public BlackJackPlayer(String name) {
		this.hand = new BlackJackHand();
		this.playerName = name;
		this.chips = 150;
		this.suit = 0;

	}

	public int compareTo(Player player){
		return hand.compareTo(player.getHand());
	}
	/*
     * Receive a card
     */
    public void receive(Card card){
    	hand.addCard(card);
    	suit += card.getSuit().getSuit();
    }

	@Override
	public Hand getHand() {
		return hand;
	}

	@Override
	public double wager() {
		Random random = new Random();
		int toWager = random.nextInt(3);
		if (toWager == 0){
			wager = 5;
		}
		else if (toWager == 1){
			wager = 10;
		}
		else{
			wager = 20;
		}

		if (chips-wager <= 0) {
			chips = 0;
			wager = chips;
		}
		else{
			chips -= wager;
		}
		
		return wager;
	}

	public void payOut(double money){
    	chips += money;
    }

    /*
     * Return the amount of money currently available to the player
     */
    public double getMoney(){
    	return chips;
    }

    /*
     * Return the name of the player
     */


    /*
     * Whether the player would like to request a card: true indicates
     * that they would (equivalent to a 'hit' in Black Jack); false
     * indicates they would not.
     */
    public boolean requestCard(){
    	if (hand.valueOf() < 16){
    		return true;
    	}else{
    		return false;
    	}
    }

	public String getName(){
    	return playerName;
    }
	
}