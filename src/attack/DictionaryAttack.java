package attack;

import target.TargetAuthenticator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Stratégie d'attaque par dictionnaire
 * Lit une liste de mots de passe depuis un fichier et les teste
 */
public class DictionaryAttack implements AttackStrategy {
    private TargetAuthenticator target;
    private String dictionaryPath;
    
    public DictionaryAttack(TargetAuthenticator target) {
        this.target = target;
        this.dictionaryPath = "../resources/dictionary.txt";
    }
    
    public DictionaryAttack(TargetAuthenticator target, String dictionaryPath) {
        this.target = target;
        this.dictionaryPath = dictionaryPath;
    }
    
    @Override
    public void execute(String login) {
        System.out.println("Démarrage de l'attaque par dictionnaire pour le login: " + login);
        System.out.println("Dictionnaire: " + dictionaryPath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryPath))) {
            String password;
            int attempts = 0;
            
            while ((password = reader.readLine()) != null) {
                password = password.trim();
                if (password.isEmpty()) {
                    continue;
                }
                
                attempts++;
                System.out.print("Test #" + attempts + ": " + password + " ... ");
                
                if (target.authenticate(login, password)) {
                    System.out.println("SUCCÈS!");
                    System.out.println("Mot de passe trouvé: " + password);
                    System.out.println("Nombre de tentatives: " + attempts);
                    return;
                } else {
                    System.out.println("échec");
                }
            }
            
            System.out.println("Attaque par dictionnaire terminée. Aucun mot de passe trouvé.");
            System.out.println("Total des tentatives: " + attempts);
            
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier dictionnaire: " + e.getMessage());
            System.err.println("Assurez-vous que le fichier " + dictionaryPath + " existe.");
        }
    }
}
