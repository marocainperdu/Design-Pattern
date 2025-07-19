package factory;

import attack.AttackStrategy;
import attack.BruteForceAttack;
import target.TargetAuthenticator;
import target.OnlineAuthenticator;

/**
 * Fabrique pour créer une attaque par force brute sur une cible en ligne
 */
public class OnlineBruteForceFactory extends CrackingFactory {
    private String targetUrl;
    
    public OnlineBruteForceFactory(String targetUrl) {
        this.targetUrl = targetUrl;
    }
    
    @Override
    public AttackStrategy createAttackStrategy() {
        TargetAuthenticator target = createTargetAuthenticator();
        return new BruteForceAttack(target, 3); // Limite plus faible pour les attaques en ligne
    }
    
    @Override
    public TargetAuthenticator createTargetAuthenticator() {
        return new OnlineAuthenticator(targetUrl);
    }
}
