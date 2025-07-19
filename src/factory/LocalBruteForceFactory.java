package factory;

import attack.AttackStrategy;
import attack.BruteForceAttack;
import target.TargetAuthenticator;
import target.LocalAuthenticator;

/**
 * Fabrique pour créer une attaque par force brute sur une cible locale
 */
public class LocalBruteForceFactory extends CrackingFactory {
    
    @Override
    public AttackStrategy createAttackStrategy() {
        TargetAuthenticator target = createTargetAuthenticator();
        return new BruteForceAttack(target);
    }
    
    @Override
    public TargetAuthenticator createTargetAuthenticator() {
        return new LocalAuthenticator();
    }
}
