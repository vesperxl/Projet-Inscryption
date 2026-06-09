package Cards;

import Cards.Animals.*;
import Cards.Animals.Powers.Power;
import Cards.Obstacles.Fir;
import Cards.Obstacles.Rock;
import gameplay.Board;
import gameplay.Player;

import java.util.ArrayList;
import java.util.Random;


public abstract class Card implements Attacker
{
    private String _nom;
    private int _healthPoint;
    private ArrayList<Power> _powers;

    public Card(String name, int hp){
        this._nom = name;
        this._healthPoint = hp;
        this._powers = new ArrayList<>();
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
        int choix = rdm.nextInt(13);

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
            case 9: return new WolfPup();
            case 10: return new Moose();
            case 11: return new Porcupine();
            default: return new Viper();
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

    public String displayHp(){
        return "PV : " + this.get_healthPoint();

    }

    public String displayAttack(){
        return "";
    }

    public String displaySigil(){
        return "";
    }

    public abstract int getBlood();

    public abstract int getBone();


    public boolean sacrifice(Player player, Board board, int index){
        return false;
    }

    public boolean hasPower(String powerName) {
        for (int i = 0; i < this._powers.size(); i++) {
            if (this._powers.get(i).getName().equals(powerName)) {
                return true;
            }
        }
        return false;
    }

    public void addPower(Power p) {
        if (!this.hasPower(p.getName()))
        {
            this._powers.add(p);
        }
    }

    public void modifAttack(int modif)
    {
    }

    public void setAttack(int modif){
    }

    public void removePower(Power p)
    {
        if (this.hasPower(p.getName()))
        {
            this._powers.remove(p);
        }
    }
    public Power getPowers(int index)
    {
        return _powers.get(index);
    }

    public int getSizePower(){
        return _powers.size();
    }

    public void takeMortalDamage()
    {
    }
}
