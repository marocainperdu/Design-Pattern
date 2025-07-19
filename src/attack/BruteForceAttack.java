package attack;

import target.TargetAuthenticator;

/**
 * Stratégie d'attaque par force brute
 * Génère toutes les combinaisons possibles de caractères
 */
public class BruteForceAttack implements AttackStrategy {
    private TargetAuthenticator target;
    private int maxLength;
    private String charset;
    
    public BruteForceAttack(TargetAuthenticator target) {
        this.target = target;
        this.maxLength = 4; // Limite par défaut pour éviter les attaques trop longues
        this.charset = "abcdefghijklmnopqrstuvwxyz0123456789";
    }
    
    public BruteForceAttack(TargetAuthenticator target, int maxLength) {
        this.target = target;
        this.maxLength = maxLength;
        this.charset = "abcdefghijklmnopqrstuvwxyz0123456789";
    }
    
    @Override
    public void execute(String login) {
        System.out.println("Démarrage de l'attaque par force brute pour le login: " + login);
        System.out.println("Longueur maximale: " + maxLength);
        
        // Essai de mots de passe de longueur 1 à maxLength
        for (int length = 1; length <= maxLength; length++) {
            System.out.println("Test des mots de passe de longueur " + length + "...");
            if (bruteForceLength(login, length, "")) {
                return; // Mot de passe trouvé
            }
        }
        
        System.out.println("Attaque par force brute terminée. Aucun mot de passe trouvé.");
    }
    
    /**
     * Génère récursivement toutes les combinaisons pour une longueur donnée
     */
    private boolean bruteForceLength(String login, int length, String current) {
        // Si on a atteint la longueur désirée, on teste le mot de passe
        if (current.length() == length) {
            System.out.print("Test: " + current + " ... ");
            if (target.authenticate(login, current)) {
                System.out.println("SUCCÈS!");
                System.out.println("Mot de passe trouvé: " + current);
                return true;
            } else {
                System.out.println("échec");
                return false;
            }
        }
        
        // Générer récursivement toutes les combinaisons
        for (int i = 0; i < charset.length(); i++) {
            if (bruteForceLength(login, length, current + charset.charAt(i))) {
                return true;
            }
        }
        
        return false;
    }
}
