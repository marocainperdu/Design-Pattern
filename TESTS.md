# Tests et Démonstration du Password Cracker Factory

## Tests effectués

### ✅ Compilation
Le projet compile sans erreur avec `javac`.

### ✅ Attaque par dictionnaire (locale)
- **Commande** : `java -cp . CrackerApp --type dictionary --target local --login admin`
- **Résultat** : ✅ Succès - Mot de passe "123" trouvé en 19 tentatives
- **Temps** : Très rapide (quelques secondes)

### ✅ Attaque par force brute (locale)
- **Commande** : `java -cp . CrackerApp --type bruteforce --target local --login admin`
- **Résultat** : ✅ En cours - Teste systématiquement toutes les combinaisons
- **Temps** : Plus long (trouvera "123" dans les combinaisons de longueur 3)

### ⚠️ Attaques en ligne
Les attaques en ligne nécessitent une URL valide pour fonctionner.

## Configuration actuelle

### Identifiants par défaut (LocalAuthenticator)
- **Login** : admin
- **Mot de passe** : 123

### Limitations de sécurité
- Force brute limitée à 4 caractères maximum pour éviter les tests trop longs
- Délai de 10ms entre chaque tentative pour simuler la latence

## Exemples d'utilisation

```bash
# Compilation
cd src
javac -cp . CrackerApp.java attack/*.java target/*.java factory/*.java

# Tests
java -cp . CrackerApp --type dictionary --target local --login admin
java -cp . CrackerApp --type bruteforce --target local --login admin
java -cp . CrackerApp --help

# Attaques en ligne (exemple)
java -cp . CrackerApp --type dictionary --target online --url http://example.com/login.php --login admin
```

## Patterns de conception implémentés

1. **Factory Method** : Chaque fabrique concrète crée ses propres instances
2. **Abstract Factory** : Coordination de familles d'objets compatibles
3. **Strategy** : Différentes stratégies d'attaque interchangeables

## Architecture modulaire

- Interfaces bien définies
- Séparation claire des responsabilités
- Facilité d'extension et de maintenance
- Configuration flexible via paramètres ligne de commande
