package gameplay;

import Card.Animals.Squirrel;
import Card.Card;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Deck {

    private Stack<Card> _cards;

    private Deck(){
        this._cards = new Stack<>();
    }

    public static Deck randomDeck(){
        Deck deck = new Deck();


        Random rdm = new Random();
        int nbEcureuil = rdm.nextInt(4) + 8;

        for(int i = 0; i < nbEcureuil){
            deck._cards.push(new Squirrel());
        }

        for(int i = 0; i < 15 - nbEcureuil; i++){
            deck._cards.push(Card.getRandomCard());
        }

        Collections.shuffle(deck._cards);


        return deck;


    }

    public void addCard(Card carte){

        this._cards.push(carte);

    }

}
