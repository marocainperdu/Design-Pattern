import factory.*;

/**
 * Application principale du Password Cracker Factory
 * Utilise le pattern Factory pour créer dynamiquement des combinaisons attaque/cible
 */
public class CrackerApp {
    
    public static void main(String[] args) {
        // Affichage de l'aide si aucun argument
        if (args.length == 0) {
            showHelp();
            return;
        }
        
        // Parsing des arguments
        String attackType = null;
        String targetType = null;
        String login = "admin";
        String url = null;
        
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--type":
                    if (i + 1 < args.length) {
                        attackType = args[++i];
                    }
                    break;
                case "--target":
                    if (i + 1 < args.length) {
                        targetType = args[++i];
                    }
                    break;
                case "--login":
                    if (i + 1 < args.length) {
                        login = args[++i];
                    }
                    break;
                case "--url":
                    if (i + 1 < args.length) {
                        url = args[++i];
                    }
                    break;
                case "--help":
                case "-h":
                    showHelp();
                    return;
                default:
                    System.err.println("Option inconnue: " + args[i]);
                    showHelp();
                    return;
            }
        }
        
        // Validation des arguments
        if (attackType == null || targetType == null) {
            System.err.println("Erreur: --type et --target sont obligatoires");
            showHelp();
            return;
        }
        
        if ("online".equals(targetType) && url == null) {
            System.err.println("Erreur: --url est obligatoire pour les attaques en ligne");
            showHelp();
            return;
        }
        
        // Création de la fabrique appropriée
        CrackingFactory factory = createFactory(attackType, targetType, url);
        if (factory == null) {
            System.err.println("Erreur: Combinaison type/target invalide");
            showHelp();
            return;
        }
        
        // Exécution de l'attaque
        System.out.println("Password Cracker Factory v1.0");
        System.out.println("Type d'attaque: " + attackType);
        System.out.println("Type de cible: " + targetType);
        System.out.println("Login: " + login);
        if (url != null) {
            System.out.println("URL: " + url);
        }
        System.out.println();
        
        factory.executeAttack(login);
    }
    
    /**
     * Crée la fabrique appropriée selon les paramètres
     */
    private static CrackingFactory createFactory(String attackType, String targetType, String url) {
        if ("local".equals(targetType)) {
            if ("bruteforce".equals(attackType)) {
                return new LocalBruteForceFactory();
            } else if ("dictionary".equals(attackType)) {
                return new LocalDictionaryFactory();
            }
        } else if ("online".equals(targetType)) {
            if ("bruteforce".equals(attackType)) {
                return new OnlineBruteForceFactory(url);
            } else if ("dictionary".equals(attackType)) {
                return new OnlineDictionaryFactory(url);
            }
        }
        return null;
    }
    
    /**
     * Affiche l'aide d'utilisation
     */
    private static void showHelp() {
        System.out.println("Password Cracker Factory v1.0");
        System.out.println("Usage: java CrackerApp [options]");
        System.out.println();
        System.out.println("Options obligatoires:");
        System.out.println("  --type <type>      Type d'attaque: bruteforce ou dictionary");
        System.out.println("  --target <target>  Type de cible: local ou online");
        System.out.println();
        System.out.println("Options facultatives:");
        System.out.println("  --login <login>    Login à attaquer (défaut: admin)");
        System.out.println("  --url <url>        URL pour les attaques online (obligatoire si --target online)");
        System.out.println("  --help, -h         Affiche cette aide");
        System.out.println();
        System.out.println("Exemples:");
        System.out.println("  java CrackerApp --type dictionary --target local --login admin");
        System.out.println("  java CrackerApp --type bruteforce --target local");
        System.out.println("  java CrackerApp --type dictionary --target online --url http://example.com/login.php");
        System.out.println();
        System.out.println("Note: Pour les attaques par dictionnaire, assurez-vous que le fichier");
        System.out.println("      resources/dictionary.txt existe.");
    }
}
