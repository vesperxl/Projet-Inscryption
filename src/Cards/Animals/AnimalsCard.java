package Cards.Animals;

import Cards.Card;
import gameplay.*;

import java.util.Optional;

public abstract class AnimalsCard extends Card
{
    private int _attack;
    private int _blood;
    private int _bone;

    public AnimalsCard(String name, int hp, int attack, int blood, int bone)
    {
        super(name, hp);
        _attack = attack;
        _blood = blood;
        _bone = bone;
    }



    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card,Board board)
    {
        Battle.basicAttack(attacker, index, _attack, scoreManager, card, board);
    }

    @Override
    public int getAttack()
    {
        return _attack;
    }

    @Override
    public String displayAttack(){
        return "Att: " + getAttack();
    }

    @Override
    public int getBlood(){
        return _blood;
    }

    @Override
    public int getBone(){
        return _bone;
    }

    @Override
    public boolean sacrifice(Player player, Board board, int index){

        Optional<Card> card = board.getCard(index, Side.PLAYER);

        if(card.isPresent()){
            player.addBloodStock();
            player.addBoneStock();
            if (!this.hasPower("Many Lives")) {
                board.removeCard(index, Side.PLAYER);
            }
            return true;
        }

        return false;
    }

    @Override
    public void modifAttack(int modif){
        _attack += modif;
    }

    @Override
    public void setAttack(int modif){
        _attack = modif;
    }

    @Override
    public void takeMortalDamage() {
        this.takeDamage(this.get_healthPoint());
    }
}
