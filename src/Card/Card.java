package Card;

import Card.Animals.*;
import Card.Obstacles.Fir;
import Card.Obstacles.Rock;

import java.util.Random;

public abstract class Card
{
    private String _nom;
    private int _healthPoint;

    public Card(String name, int hp){
        this._nom = name;
        this._healthPoint = hp;
    }
    
    public int get_healthPoint()
    {
        return _healthPoint;
    }

    public String get_nom()
    {
        return _nom;
    }

    public int takeDamage(int damage)
    {
        return _healthPoint -= damage;

    }

    public static Card getRandomAnimalCard(){
        Random rdm = new Random();
        int choix = rdm.nextInt(10);

        switch(choix){
            case 0: return new Bug();
            case 1: return new Cat();
            case 2: return new Coyote();
            case 3: return new Ermine();
            case 4: return new Grizzly();
            case 5: return new Raven();
            case 6: return new Sparrow();
            case 7: return new Squirrel();
            case 8: return new Wolf();
            default: return new WolfPup();
        }

    }

    public static Card getRandomObstacleCard(){
        Random rdm = new Random();
        int choix = rdm.nextInt(2);
        switch (choix){
            case 0: return new Fir();
            default: return new Rock();
        }
    }

    public int getAttack() {
        return 0;
    }

    public boolean isFlyable()
    {
        return false;
    }
}
