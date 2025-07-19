package factory;

import attack.AttackStrategy;
import attack.DictionaryAttack;
import target.TargetAuthenticator;
import target.OnlineAuthenticator;

/**
 * Fabrique pour créer une attaque par dictionnaire sur une cible en ligne
 */
public class OnlineDictionaryFactory extends CrackingFactory {
    private String targetUrl;
    
    public OnlineDictionaryFactory(String targetUrl) {
        this.targetUrl = targetUrl;
    }
    
    @Override
    public AttackStrategy createAttackStrategy() {
        TargetAuthenticator target = createTargetAuthenticator();
        return new DictionaryAttack(target);
    }
    
    @Override
    public TargetAuthenticator createTargetAuthenticator() {
        return new OnlineAuthenticator(targetUrl);
    }
}
