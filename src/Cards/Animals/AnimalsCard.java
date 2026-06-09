package Cards.Animals;

import Cards.Animals.Powers.Power;
import Cards.Card;
import gameplay.*;

import java.util.ArrayList;
import java.util.Optional;

public abstract class AnimalsCard extends Card
{
    private int _attack;
    private int _blood;
    private int _bone;
    private ArrayList<Power> _powers;

    public AnimalsCard(String name, int hp, int attack, int blood, int bone)
    {
        super(name, hp);
        _attack = attack;
        _blood = blood;
        _bone = bone;
        this._powers = new ArrayList<>();
    }



    @Override
    public void attack(Side attacker, int index, ScoreManager scoreManager, Optional<Card> card, Board board, ArrayList<AttackData> attackHistory)
    {
        Battle.basicAttack(attacker, index, _attack, scoreManager, card, board,attackHistory);
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
    public boolean sacrifice(Player player, Board board, int index)
    {
        Optional<Card> card = board.getCard(index, Side.PLAYER);

        if(card.isPresent())
        {
            player.addBloodStock();
            player.addBoneStock();

            boolean survived = false;

            for (int i = 0; i < this.getSizePower(); i++) {
                if (this.getPowers(i).get().onSacrificeEvent(player, board, index)) {
                    survived = true;
                }
            }

            if (!survived) {
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


    @Override
    public boolean addPower(Power p) {
        if (!this.hasPower(p.getName()))
        {
            this._powers.add(p);
        }
        return true;
    }

    @Override
    public String displayPower(int index){

        if(index < this.getSizePower()){
            return this._powers.get(index).getName();
        }

        return "";
    }

    @Override
    public boolean hasPower(String powerName) {
        for (int i = 0; i < this._powers.size(); i++) {
            if (this._powers.get(i).getName().equals(powerName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removePower(Power p)
    {
        if (this.hasPower(p.getName()))
        {
            this._powers.remove(p);
        }
    }

    @Override
    public Optional<Power> getPowers(int index)
    {
        if (index >= 0 && index < this.getSizePower())
        {
            return Optional.of(_powers.get(index));
        }
        return Optional.empty();
    }

    @Override
    public int getSizePower(){
        return _powers.size();
    }

}
