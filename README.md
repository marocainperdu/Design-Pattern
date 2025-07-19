# 🏭 Password Cracker Factory

Un projet Java démonstratif implémentant le **pattern Factory Method** et **Abstract Factory** pour simuler des attaques de mots de passe.

## 📋 Description

Ce projet illustre l'utilisation des patterns de conception Factory pour créer dynamiquement différentes combinaisons de stratégies d'attaque et de cibles d'authentification. Il supporte :

- **Deux types d'attaques** : Force brute et Dictionnaire
- **Deux types de cibles** : Locale (console Java) et En ligne (HTTP POST)
- **Architecture modulaire** et extensible

## 🚀 Utilisation

### Compilation

```bash
cd src
javac -cp . CrackerApp.java attack/*.java target/*.java factory/*.java
```

### Exécution

```bash
# Attaque par dictionnaire sur cible locale
java -cp . CrackerApp --type dictionary --target local --login admin

# Attaque par force brute sur cible locale
java -cp . CrackerApp --type bruteforce --target local --login admin

# Attaque par dictionnaire sur cible en ligne
java -cp . CrackerApp --type dictionary --target online --url http://example.com/login.php --login admin

# Afficher l'aide
java -cp . CrackerApp --help
```

## 🏗️ Architecture

### Diagramme de classes UML

```
┌─────────────────────┐     ┌─────────────────────┐
│   AttackStrategy    │     │ TargetAuthenticator │
│    <<interface>>    │     │    <<interface>>    │
├─────────────────────┤     ├─────────────────────┤
│ + execute(login)    │     │ + authenticate()    │
└─────────────────────┘     └─────────────────────┘
           ▲                           ▲
           │                           │
    ┌──────┴──────┐              ┌─────┴─────┐
    │             │              │           │
┌───▼───────┐ ┌───▼─────────┐  ┌─▼─────────┐ │
│BruteForce │ │ Dictionary  │  │   Local   │ │
│  Attack   │ │   Attack    │  │Authenticat│ │
└───────────┘ └─────────────┘  │    or     │ │
                               └───────────┘ │
                                      ┌─────▼──────┐
                                      │   Online   │
                                      │Authenticat │
                                      │    or      │
                                      └────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    CrackingFactory                              │
│                   <<abstract class>>                            │
├─────────────────────────────────────────────────────────────────┤
│ + createAttackStrategy() : AttackStrategy                       │
│ + createTargetAuthenticator() : TargetAuthenticator             │
│ + executeAttack(login : String) : void                          │
└─────────────────────────────────────────────────────────────────┘
                               ▲
        ┌──────────────────────┼──────────────────────┐
        │                      │                      │
┌───────▼─────────┐    ┌───────▼─────────┐    ┌───────▼─────────┐
│LocalBruteForce  │    │LocalDictionary  │    │OnlineBruteForce │
│    Factory      │    │    Factory      │    │    Factory      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                                       │
                                              ┌────────▼─────────┐
                                              │OnlineDictionary  │
                                              │    Factory       │
                                              └──────────────────┘
```

## 🔧 Composants

### Interfaces

- **`AttackStrategy`** : Définit le contrat pour les stratégies d'attaque
- **`TargetAuthenticator`** : Définit le contrat pour les cibles d'authentification

### Stratégies d'attaque

- **`BruteForceAttack`** : Génère toutes les combinaisons possibles (a-z, 0-9)
- **`DictionaryAttack`** : Teste une liste de mots de passe depuis un fichier

### Cibles d'authentification

- **`LocalAuthenticator`** : Authentification locale avec identifiants codés en dur
- **`OnlineAuthenticator`** : Authentification via requêtes HTTP POST

### Fabriques

- **`CrackingFactory`** : Fabrique abstraite définissant l'interface
- **`LocalBruteForceFactory`** : Crée une attaque brute force locale
- **`LocalDictionaryFactory`** : Crée une attaque par dictionnaire locale
- **`OnlineBruteForceFactory`** : Crée une attaque brute force en ligne
- **`OnlineDictionaryFactory`** : Crée une attaque par dictionnaire en ligne

## 🎯 Justification du pattern Factory

### Avantages

1. **Découplage** : Le code client ne dépend que des interfaces, pas des implémentations concrètes
2. **Extensibilité** : Facile d'ajouter de nouveaux types d'attaques ou de cibles
3. **Cohérence** : Chaque fabrique garantit la compatibilité entre stratégie et cible
4. **Configuration dynamique** : Choix de la fabrique au runtime selon les paramètres

### Pattern utilisé

Le projet combine deux patterns Factory :

- **Factory Method** : Chaque fabrique concrète implémente les méthodes de création
- **Abstract Factory** : La fabrique abstraite définit une famille de produits liés

## 🔄 Variantes implémentées

### 1. Factory Method simple
Chaque fabrique concrète crée ses propres instances selon sa spécialité.

### 2. Abstract Factory
La fabrique abstraite coordonne la création de familles d'objets compatibles.

### 3. Parameterized Factory
Les fabriques en ligne acceptent des paramètres (URL) pour personnaliser les instances.

## 📈 Pistes d'amélioration

### Fonctionnalités

1. **Nouvelles stratégies** :
   - Attaque hybride (dictionnaire + variations)
   - Attaque par règles (leet speak, capitalisation)
   - Attaque par masque (format spécifique)

2. **Nouvelles cibles** :
   - Base de données (JDBC)
   - Services SSH/FTP
   - APIs REST avec authentification

3. **Optimisations** :
   - Multi-threading pour les attaques
   - Cache des résultats
   - Statistiques détaillées

### Architecture

1. **Configuration externe** :
   - Fichiers properties pour les paramètres
   - Factory registry pour l'enregistrement dynamique

2. **Observateur** :
   - Pattern Observer pour le monitoring des attaques
   - Logs détaillés et métriques

3. **Plugin system** :
   - Chargement dynamique de nouvelles fabriques
   - Interface de plugin standardisée

## 📁 Structure du projet

```
src/
├── CrackerApp.java              # Application principale
├── attack/
│   ├── AttackStrategy.java      # Interface stratégie d'attaque
│   ├── BruteForceAttack.java    # Implémentation force brute
│   └── DictionaryAttack.java    # Implémentation dictionnaire
├── target/
│   ├── TargetAuthenticator.java # Interface cible
│   ├── LocalAuthenticator.java  # Authentification locale
│   └── OnlineAuthenticator.java # Authentification en ligne
└── factory/
    ├── CrackingFactory.java     # Fabrique abstraite
    ├── LocalBruteForceFactory.java
    ├── LocalDictionaryFactory.java
    ├── OnlineBruteForceFactory.java
    └── OnlineDictionaryFactory.java

resources/
└── dictionary.txt               # Dictionnaire de mots de passe
```

## ⚠️ Avertissement

Ce projet est uniquement à des fins éducatives pour comprendre les patterns de conception. Il ne doit pas être utilisé pour des attaques réelles. L'utilisation malveillante de ces techniques peut être illégale.

## 📽️Test

[![Vidéo de présentation du Password Cracker Factory](https://img.shields.io/badge/Vidéo-Présentation-red?style=for-the-badge&logo=youtube)](https://youtu.be/LKE8KgiFQSc)

## 📝 Licence

Projet éducatif - Utilisation libre pour l'apprentissage.
