package impl;
import api.Card;
import api.Player;
import api.Table;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * The table class represents a place in which card games happen. You
 * should specialise it specifically for Black Jack.
 *
 * Do not re-declare the attributes in the child class! They merely
 * need memory allocated, and then they can be used.
 */
public class BlackJackTable extends Table {
	
	public BlackJackTable(int numberOfPlayers) {
		this.players = new ArrayList<>();
		this.dealer = new BlackJackDealer("Name");
		this.wagers = new HashMap<>();
		for (int i = 0; i < numberOfPlayers; i++){
			Player player = new BlackJackPlayer("Player "+Integer.toString(i+1);
			this.players.add(player);
		}
	}


    /*
     * A game is over when there are no players, or no players with
     * money to bet
     */
    public boolean isGameOver(){
    	for (int i=0;i<players.size();i++){
    		if (players.get(i).getMoney() > 0){
    			return false;
    		}
    	}
    	return true;
    }

    /*
     * A string representation of the table
     */
    public String toString(){
    	String table = "";
    	for (int i=0;i<players.size();i++){
    		table += players.get(i).getName() + " " + players.get(i).getMoney() + "\n";
    	}
    	return table;
    }

    /*
     * Collect cards from all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void collectCards(){
    	for (Player player: this.players) {
			this.dealer.collectCards(player);
		}
    	this.dealer.collectCards((Player)dealer);
    }

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void dealTable(){
		for (Player player : this.players) {
			this.dealer.dealCard(player);
			this.dealer.dealCard(player);
		}
		this.dealer.dealCard((Player)dealer);
		this.dealer.dealCard((Player)dealer);
    }
    
    /*
     * Collect bets from all players at the table
     */
    protected void collectBets(){
    	for (int i=0; i < this.players.size(); i++) {
            if (this.players.get(i).getMoney() <= 0) {
                    this.players.remove(i);
                }
        }
    		
    	if (!this.isGameOver()){
    		this.dealer.shuffle();
    		for (Player player : this.players){
    			this.wagers.put(player, player.wager());
    		}
    	}
    }

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should have a turn as well!
     */
    protected void playerTurns(){
    	for (Player player : this.players){
    		while (player.requestCard()){
    			this.dealer.dealCard(player);
    		}
    	}
       	while(((Player)this.dealer).requestCard()){
    		this.dealer.dealCard((Player) this.dealer);
    	}
    }

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected void playerEvaluations(){
    	ArrayList<Player> playList = new ArrayList<Player>();

    	//check if they have a valid hand
    	for (Player player : this.players){
    		if (player.getHand().isValid()){
    			//add them to a can Play List
    			playList.add(player);
    		}
    	}
    	//check if they beat the dealer or not
    	for (Player player : playList){
    		if (player.compareTo((Player)this.dealer) == 1){
    			//add them to a can Play List
    			System.out.println(player.getName() + " beat the Dealer this round! They won " + player.wager() + " dollars.");
    			player.payOut(2*player.wager());
    			
    		}
    		else if (player.compareTo((Player)this.dealer) == 0){
    			System.out.println(player.getName() + " tied the Dealer this round! They won their wager of " + player.wager() + " back this round.");
    			player.payOut(player.wager());
    		}
    		else {
    			System.out.println(player.getName() + " has lost to the Dealer this round! They lost they wager of " + player.wager() + " this round.");
    		}
    	}
    	
    	//pay out
    	
    }
    
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