package org.max.seminar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.max.seminar.exception.DeckEmptyException;
import org.max.seminar.type.Ranks;
import org.max.seminar.type.Suits;

import java.util.ArrayList;
import java.util.Collections;

import static org.max.seminar.type.Ranks.TWO;
import static org.max.seminar.type.Suits.CLUBS;

public class MyDeckTest {
    Card checkCard;
    Deck deck;

    @BeforeEach
    void getNewDeck() {
        checkCard = new Card(TWO, CLUBS);
        ArrayList<Card> arrayList = new ArrayList<>();
        arrayList.add(checkCard);
        deck = new Deck(arrayList);
    }

    @Test
    void testGetCardEmptyDeck() {
        //Given
        deck = new Deck(new ArrayList<>());
        //When
        //Then
        Assertions.assertThrows(DeckEmptyException.class, deck::getCard);
    }

    @Test
    void testGetCardNotEmptyDeck() throws DeckEmptyException {
        //Given
        DeckService deckService = new DeckService();
        deck = deckService.getNewDeck();
        Card check = deck.getCards().get(0);
        int size = deck.getCards().size();
        //When
        Card result = deck.getCard();
        //Then
        Assertions.assertEquals(check, result);
        Assertions.assertEquals(size - 1, deck.getCards().size());
    }

    @Test
    void testGetCardFromOneCardDeck() throws DeckEmptyException {
        //Given
        //When
        Card result = deck.getCard();
        //Then
        Assertions.assertEquals(checkCard, result);
        Assertions.assertEquals(0, deck.getCards().size());
    }
}
