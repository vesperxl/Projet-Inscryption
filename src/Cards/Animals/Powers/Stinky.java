package Cards.Animals.Powers;

public class Stinky extends Power
{
    @Override
    public String getName() {
        return "Stinky";
    }
    //Test
    @Override
    public int stinkyPower(int attack)
    {
        if (attack > 0){
            return attack -= 1;
        }

        return attack;
    }

}
