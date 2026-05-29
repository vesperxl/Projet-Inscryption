package gameplay;

public class ScoreManager {
    private int _score;

    public ScoreManager(){
        _score = 0;
    }

    public void addPlayerPoint(int points){
        _score += points;
    }
    public void addEnemyPoint(int points){
        _score -= points;
    }

    public int getScore(){
        return _score;
    }

    public boolean playerVictory(){
        return _score >= 5;
    }

    public boolean enemyVictory(){
        return _score <= -5;
    }
}
