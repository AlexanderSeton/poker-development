package com.codeClan.example.Poker;

import com.codeClan.example.Poker.game.models.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest
public class PokerApplicationTests {

//	CardSuitType cardSuitType;
//	CardFace cardFace;

	 Deck deck1;


	 Card card1;
	 Card card2;
//	 Card card3;
//	 Card card4;
//	 Card card5;
//	 Card card6;
//	 Card card7;


	GameTable gameTable1;
	ArrayList<Player> players = new ArrayList<>();
	ArrayList<Card> board1 = new ArrayList<>();



	Player player1;
	Player player2;

	List<Card> hand1 = new ArrayList<>();
	List<Card> hand2 = new ArrayList<>();








	@Before
	public void before(){
		 deck1 = new Deck();

		 card1 = new Card(CardSuitType.CLUBS, CardFace.ACE);
		 card2 = new Card(CardSuitType.DIAMONDS, CardFace.KING);

		 hand1 = new ArrayList<>();
		 hand1.add(card1);
		 hand1.add(card2);

		 player1 = new Player("iain",1000, "iain123","1234");
		 player2 = new Player("alex",900, "alex123","4321");
		 player1.addCard(card1);
		 player1.addCard(card2);

		 board1 = new ArrayList<>();
		 board1.add(card1);
		 board1.add(card2);

		 players = new ArrayList<>();
		 players.add(player1);
		 players.add(player2);
		 Deck deck = new Deck();
		 gameTable1 = new GameTable( 2000.0, players, 50.0, deck);
	}
	@Test
	public void contextLoads() {
	}

//	CARD TESTS

	@Test
	public void cardHasType(){
		assertEquals(CardSuitType.DIAMONDS, card2.getCardSuitType());
	}

	@Test
	public void cardHasFace(){
		assertEquals(CardFace.ACE, card1.getCardValue());
	}

	@Test
	public void cardHasValue(){
		assertEquals(1,card1.getCardValue().getValue());
	}

//	player tests

	@Test
	public void playerHasName(){
		assertEquals("iain", player1.getName());
	}

	@Test
	public void playerHasStack(){
		assertEquals(1000,player1.getStack(),0.0);
	}

	@Test
	public void playerHasHand(){
		assertEquals(2,player1.getHand().size());
	}

	@Test
	public void canAddToStack(){
		player1.addToStack(10);
		assertEquals(1010,player1.getStack(),0.0);
	}

	@Test
	public void canRemoveFromStack(){
		player1.removeFromStack(10);
		assertEquals(990,player1.getStack(),0.0);
	}

	@Test
	public void playerCanAddToPot(){
		player1.removeFromStack(20);
		gameTable1.addToPot(20);
		assertEquals(2020, gameTable1.getPot(),0.0);
	}

//	deck tests

	@Test
	public void deckHas52Cards(){
		assertEquals(52, deck1.getDeck().size());
	}

	@Test
	public void cardsAreUnique(){
		HashSet<Card> deckCheck = new HashSet<Card>(deck1.getDeck());
		assertEquals(52, deckCheck.size());
	}

//	Table Tests

	@Test
	public void tableHasPot(){
		assertEquals(2000, gameTable1.getPot(),0.0);
	}
	@Test
	public void tableHasPlayers(){
		assertEquals(2, gameTable1.getPlayers().size());
	}

	@Test
	public void tableHasBoard(){
		assertEquals(2, gameTable1.getBoard().size());
	}

	@Test
	public void canAddToPot(){
		gameTable1.addToPot(20);
		assertEquals(2020, gameTable1.getPot(),0.0);
	}

	@Test
	public void canResetPot(){
		gameTable1.resetPot();
		assertEquals(0, gameTable1.getPot(),0.0);
	}

}
