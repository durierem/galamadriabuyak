package galamadriabuyak;

import java.util.Stack;

import galamadriabuyak.util.Tools;

public class Deck implements IDeck {

    // ATTRIBUTES

    private int size;
    private Stack<ICard> deck;

    // CONSTRUCTORS

    public Deck() {
        size = 0;
        deck = new Stack<>();
    }

    // REQUESTS

    public int getSize() {
        return size;
    }

    // COMMANDS

    public void addCard(ICard c) {
        if (c == null) {
            throw new AssertionError();
        }

        deck.push(c);
        size += 1;
    }

    public void rmTopCard() {
        if (getSize() <= 0) {
            throw new AssertionError();
        }

        deck.pop();
        size -= 1;
    }

    public void drawCard(IHand hand) {
        if (hand == null || getSize() <= 0
                || hand.getSize() >= IHand.MAX_SIZE) {
            throw new AssertionError();
        }

        hand.addCard(deck.pop());
        size -= 1;
    }

    public void shuffleDeck() {
        if (size < 0) {
            throw new AssertionError();
        }
        int randomFactor = 7;
        Stack<ICard>[] bufStack = new Stack[randomFactor];

        //Initializing stacks.
        for (int i = 0; i < randomFactor; ++i) {
            bufStack[i] = new Stack<>();
        }

        //  Splitting the deck in randomFactor stacks
        //  Card by card we randomly choose one of the stack and
        //  pushing in the card.
        while (!deck.empty()) {
            bufStack[Tools.alea(0, randomFactor - 1)].push(deck.pop());
        }

        //  Assembling all the stacks in one deck.
        for (int i = 0; i < randomFactor; ++i) {
            while (!bufStack[i].empty()) {
                deck.push(bufStack[i].pop());
            }
        }
    }

    public void randomFill(int n, final ICard[] possibleCards) {
        if (n <= 0 || n > MAX_SIZE - getSize()) {
            throw new AssertionError();
        }
        if (possibleCards == null || possibleCards.length == 0) {
            throw new AssertionError();
        }

        for (int i = 0; i < n; i++) {
            addCard(possibleCards[Tools.alea(0, possibleCards.length - 1)]);
        }
    }
}
