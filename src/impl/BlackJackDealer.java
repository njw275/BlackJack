package impl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import api.Player;
import api.Hand;
import api.Card;
import api.Dealer;

/*
 * A card dealer, the maestro of the table.
 */
public class BlackJackDealer extends BlackJackPlayer implements Dealer {
	/*
     * The set of cards currently in a hand
     */
	public static ArrayList<Card> cards = new ArrayList<Card>();
	public Hand hand;
	public int suit;

	
	public BlackJackDealer(String name) {
		super(name);
		this.hand = new BlackJackHand();
		this.suit = 0;
		for (Card.Value values : Card.Value.values()){
			for (Card.Suit suits : Card.Suit.values()) {
				cards.add(new Card(values, suits));
			}
		}
	}
		


	public void dealCard(Player player){
    	player.receive(cards.remove(0));
    	
    }
    
    public void collectCards(Player player){
    	Iterator<Card> it = player.getHand().getCards().iterator();
    	while(it.hasNext()){
    		BlackJackDealer.cards.add(it.next());
    		it.remove();
    	}
    }

	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
}