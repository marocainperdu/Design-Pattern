package target;

/**
 * Authentificateur local avec des identifiants codés en dur
 */
public class LocalAuthenticator implements TargetAuthenticator {
    private String validLogin;
    private String validPassword;
    
    public LocalAuthenticator() {
        // Identifiants par défaut
        this.validLogin = "admin";
        this.validPassword = "esp";
    }
    
    public LocalAuthenticator(String login, String password) {
        this.validLogin = login;
        this.validPassword = password;
    }
    
    @Override
    public boolean authenticate(String login, String password) {
        // Simulation d'un délai d'authentification
        try {
            Thread.sleep(10); // 10ms de délai
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return validLogin.equals(login) && validPassword.equals(password);
    }
    
    /**
     * Méthode utilitaire pour afficher les identifiants (pour les tests)
     */
    public void showCredentials() {
        System.out.println("Identifiants valides: " + validLogin + "/" + validPassword);
    }
}
