package factory;

import attack.AttackStrategy;
import target.TargetAuthenticator;

/**
 * Fabrique abstraite pour créer des combinaisons attaque/cible
 */
public abstract class CrackingFactory {
    
    /**
     * Crée une stratégie d'attaque
     * @return L'instance de la stratégie d'attaque
     */
    public abstract AttackStrategy createAttackStrategy();
    
    /**
     * Crée un authentificateur cible
     * @return L'instance de l'authentificateur
     */
    public abstract TargetAuthenticator createTargetAuthenticator();
    
    /**
     * Méthode de commodité pour exécuter une attaque complète
     * @param login Le login à attaquer
     */
    public void executeAttack(String login) {
        System.out.println("=== Démarrage de l'attaque ===");
        System.out.println("Type de fabrique: " + this.getClass().getSimpleName());
        
        AttackStrategy strategy = createAttackStrategy();
        strategy.execute(login);
        
        System.out.println("=== Fin de l'attaque ===");
    }
}
