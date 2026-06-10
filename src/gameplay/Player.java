package gameplay;

public class Player {
    private Deck _deck;
    private Hand _hand;
    private int _bloodStock;
    private int _boneStock;
    private Deck _initialDeck;


    public Player(){
        this._initialDeck = Deck.randomDeck();
        this._deck = Deck.initialDeck(this._initialDeck);
        this._hand = new Hand(_deck);
    }

    public void draw(){
        _hand.draw(_deck);
    }

    public void newGame(){

        this._deck = Deck.initialDeck(this._initialDeck);



        this._deck.shuffle();

        this._boneStock = 0;
        this._bloodStock = 0;
    }

    public void newHand(){
        this._hand = new Hand(this._deck);
    }


    public Hand getHand(){
        return _hand;
    }

    public void addBloodStock()
    {
        _bloodStock += 1;
    }

    public void removeBloodStock(int quantity)
    {
        _bloodStock -= quantity;
    }

    public int getBloodStock()
    {
        return  _bloodStock;
    }

    public void resetBlood(){
        _bloodStock = 0;
    }


    public void addBoneStock()
    {
        _boneStock += 1;
    }

    public void removeBoneStock(int quantity)
    {
        _boneStock -= quantity;
    }

    public int getBoneStock()
    {
        return  _boneStock;
    }

    public Deck getDeck(){
        return _deck;
    }

    public Deck getInitialDeck(){
        return _initialDeck;
    }
}
