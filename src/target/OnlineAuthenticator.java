package target;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Authentificateur en ligne qui envoie des requêtes HTTP POST
 */
public class OnlineAuthenticator implements TargetAuthenticator {
    private String targetUrl;
    private String loginParameter;
    private String passwordParameter;
    private String successPattern;
    
    public OnlineAuthenticator(String targetUrl) {
        this.targetUrl = targetUrl;
        this.loginParameter = "login";
        this.passwordParameter = "password";
        this.successPattern = "success"; // Motif à rechercher dans la réponse en cas de succès
    }
    
    public OnlineAuthenticator(String targetUrl, String loginParam, String passwordParam, String successPattern) {
        this.targetUrl = targetUrl;
        this.loginParameter = loginParam;
        this.passwordParameter = passwordParam;
        this.successPattern = successPattern;
    }
    
    @Override
    public boolean authenticate(String login, String password) {
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Configuration de la requête POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "PasswordCracker/1.0");
            
            // Préparation des données POST
            String postData = loginParameter + "=" + login + "&" + passwordParameter + "=" + password;
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
            
            // Envoi de la requête
            try (OutputStream os = connection.getOutputStream()) {
                os.write(postDataBytes);
            }
            
            // Lecture de la réponse
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            
            // Vérification si la réponse contient le motif de succès
            return response.toString().toLowerCase().contains(successPattern.toLowerCase());
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la requête HTTP: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Méthode utilitaire pour tester la connectivité
     */
    public boolean testConnection() {
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            return responseCode >= 200 && responseCode < 400;
        } catch (Exception e) {
            return false;
        }
    }
}
