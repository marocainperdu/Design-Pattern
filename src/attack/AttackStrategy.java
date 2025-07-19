package attack;

/**
 * Interface définissant une stratégie d'attaque de mot de passe
 */
public interface AttackStrategy {
    /**
     * Exécute l'attaque pour un login donné
     * @param login Le nom d'utilisateur à attaquer
     */
    void execute(String login);
}
