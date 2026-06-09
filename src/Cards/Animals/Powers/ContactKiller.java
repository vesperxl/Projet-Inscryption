package Cards.Animals.Powers;

import Cards.Card;

public class ContactKiller extends Power{
    @Override
    public String getName() {
        return "Cont. Mor.";
    }


    @Override
    public void onDamageDealt(Card cardDefender)
    {
        cardDefender.takeMortalDamage();
    }
}
