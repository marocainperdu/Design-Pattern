package target;

/**
 * Interface représentant une cible d'authentification
 */
public interface TargetAuthenticator {
    /**
     * Tente d'authentifier un utilisateur avec un mot de passe
     * @param login Le nom d'utilisateur
     * @param password Le mot de passe à tester
     * @return true si l'authentification réussit, false sinon
     */
    boolean authenticate(String login, String password);
}
