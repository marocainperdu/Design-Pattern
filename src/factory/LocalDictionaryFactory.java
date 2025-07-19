package factory;

import attack.AttackStrategy;
import attack.DictionaryAttack;
import target.TargetAuthenticator;
import target.LocalAuthenticator;

/**
 * Fabrique pour créer une attaque par dictionnaire sur une cible locale
 */
public class LocalDictionaryFactory extends CrackingFactory {
    
    @Override
    public AttackStrategy createAttackStrategy() {
        TargetAuthenticator target = createTargetAuthenticator();
        return new DictionaryAttack(target);
    }
    
    @Override
    public TargetAuthenticator createTargetAuthenticator() {
        return new LocalAuthenticator();
    }
}
