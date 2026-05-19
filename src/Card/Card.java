package Card;

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

    public int takeDamage(int _damage)
    {
        return _healthPoint -= _damage;
    }
}
