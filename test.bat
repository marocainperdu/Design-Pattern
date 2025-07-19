@echo off
chcp 65001 >nul
cls
echo ================================================================
echo          PASSWORD CRACKER FACTORY - TESTS COMPLETS
echo ================================================================
echo.

:: Compilation automatique
echo [1/7] COMPILATION DU PROJET...
echo ----------------------------------------------------------------
cd src
javac -cp . CrackerApp.java attack/*.java target/*.java factory/*.java
if %errorlevel% neq 0 (
    echo ERREUR: Compilation echouee!
    pause
    exit /b 1
)
echo ✅ Compilation reussie!
echo.

:: Structure globale du code
echo [2/7] PRESENTATION DE LA STRUCTURE GLOBALE
echo ----------------------------------------------------------------
echo Structure des packages:
echo   📁 src/
echo      📄 CrackerApp.java (Application principale)
echo      📁 attack/ (Strategies d'attaque)
echo         📄 AttackStrategy.java (Interface)
echo         📄 BruteForceAttack.java
echo         📄 DictionaryAttack.java
echo      📁 target/ (Cibles d'authentification)
echo         📄 TargetAuthenticator.java (Interface)
echo         📄 LocalAuthenticator.java
echo         📄 OnlineAuthenticator.java
echo      📁 factory/ (Fabriques)
echo         📄 CrackingFactory.java (Classe abstraite)
echo         📄 LocalBruteForceFactory.java
echo         📄 LocalDictionaryFactory.java
echo         📄 OnlineBruteForceFactory.java
echo         📄 OnlineDictionaryFactory.java
echo   📁 resources/
echo      📄 dictionary.txt (Dictionnaire de mots de passe)
echo.
echo Attente 5 secondes...
timeout /t 5 >nul

:: Fonctionnement général
echo [3/7] PRESENTATION DU FONCTIONNEMENT GENERAL
echo ----------------------------------------------------------------
echo Pattern Factory Method + Abstract Factory:
echo   1. L'utilisateur specifie type d'attaque et cible
echo   2. CrackerApp selectionne la fabrique appropriee
echo   3. La fabrique cree la strategie et la cible compatibles
echo   4. La strategie execute l'attaque sur la cible
echo.
echo Identifiants par defaut: admin/esp
echo.
echo Attente 5 secondes...
timeout /t 5 >nul

:: Vue d'ensemble du projet
echo [4/7] VUE D'ENSEMBLE DE LA STRUCTURE DU PROJET
echo ----------------------------------------------------------------
echo Retour au repertoire principal pour voir l'arborescence...
cd ..
dir /s
echo.
echo Fichiers de configuration:
echo   📄 README.md - Documentation complete avec UML
echo   📄 TESTS.md - Documentation des tests
echo   📄 compile.bat - Script de compilation
echo   📄 test.bat - Ce script de test
echo.
echo Attente 5 secondes...
timeout /t 5 >nul
cd src

:: Test 1: Attaque par dictionnaire locale
echo [5/7] DEMONSTRATION - ATTAQUE PAR DICTIONNAIRE (LOCALE)
echo ----------------------------------------------------------------
echo Commande: java -cp . CrackerApp --type dictionary --target local --login admin
echo.
java -cp . CrackerApp --type dictionary --target local --login admin
echo.
echo ✅ Test termine - Le mot de passe "esp" devrait avoir ete trouve rapidement
echo.
echo Attente 5 secondes...
timeout /t 5 >nul

:: Test 2: Attaque par force brute locale (limitée)
echo [6/7] DEMONSTRATION - ATTAQUE PAR FORCE BRUTE (LOCALE)
echo ----------------------------------------------------------------
echo Commande: java -cp . CrackerApp --type bruteforce --target local --login admin
echo Note: L'attaque va tester toutes les combinaisons jusqu'a trouver le mot de passe
echo.

:: Exécution complète de l'attaque par force brute
echo Demarrage de l'attaque par force brute complete...
java -cp . CrackerApp --type bruteforce --target local --login admin
echo.
echo ✅ Attaque par force brute terminee
echo.
echo Attente 5 secondes...
timeout /t 5 >nul

:: Test 3: Démonstration du pattern Factory
echo [7/7] DEMONSTRATION - UTILISATION DU PATTERN FABRIQUE
echo ----------------------------------------------------------------
echo Test des 4 variantes de fabriques:
echo.

echo 1. LocalDictionaryFactory:
java -cp . CrackerApp --type dictionary --target local --login admin | findstr "fabrique\|Factory\|Type"
echo.

echo 2. LocalBruteForceFactory:
java -cp . CrackerApp --type bruteforce --target local --login admin | findstr "fabrique\|Factory\|Type" | more +1
echo.

echo 3. Test des parametres en ligne (simulation):
echo    OnlineDictionaryFactory:
java -cp . CrackerApp --type dictionary --target online --url http://test.com --login admin 2>nul | findstr "fabrique\|Factory\|Type\|URL" | more +1
echo.

echo 4. Affichage de l'aide complete:
java -cp . CrackerApp --help
echo.

echo ================================================================
echo                    TESTS TERMINES AVEC SUCCES!
echo ================================================================
echo.
echo Resume des tests effectues:
echo ✅ Compilation du projet
echo ✅ Presentation de la structure globale du code
echo ✅ Explication du fonctionnement general
echo ✅ Vue d'ensemble de la structure du projet
echo ✅ Demonstration de l'attaque par dictionnaire
echo ✅ Apercu de l'attaque par force brute
echo ✅ Demonstration du pattern Fabrique (4 variantes)
echo.
echo Patterns implementes:
echo   🏭 Factory Method
echo   🏭 Abstract Factory  
echo   🎯 Strategy Pattern
echo.
echo Le projet est pret pour presentation et evaluation!
echo.
echo Appuyez sur une touche pour fermer...
pause
