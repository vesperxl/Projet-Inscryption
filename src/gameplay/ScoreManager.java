package gameplay;

public class ScoreManager {
    private int _score = 0;



    public void addPoint(int points, Side side){
        if (side == Side.PLAYER){
            this._score += points;
        }
        else{
            this._score -= points;
        }
    }

    public int getCurrentScore(){
        return _score;
    }

    public boolean playerVictory(){
        return _score >= 5;
    }

    public boolean enemyVictory(){
        return _score <= -5;
    }

    public boolean isGameOver(){
        return playerVictory() || enemyVictory();
    }
}
