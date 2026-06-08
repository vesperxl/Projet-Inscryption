package gameplay;

public class AttackData {

    private String _attackerName;
    private String _targetName;
    private int _damageDealt;
    private boolean _isLethal;
    private int _overkillDamage;

    public AttackData(String attacker, String target, int damage, boolean lethal, int overkill) {
        this._attackerName = attacker;
        this._targetName = target;
        this._damageDealt = damage;
        this._isLethal = lethal;
        this._overkillDamage = overkill;
    }

    public String getAttackerName() { return _attackerName; }
    public String getTargetName() { return _targetName; }
    public int getDamageDealt() { return _damageDealt; }
    public boolean isLethal() { return _isLethal; }
    public int getOverkillDamage() { return _overkillDamage; }


}
