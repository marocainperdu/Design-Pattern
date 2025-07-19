@echo off
chcp 65001 >nul
echo Compilation du projet Password Cracker Factory...
cd src
javac -cp . CrackerApp.java attack/*.java target/*.java factory/*.java
if %errorlevel% == 0 (
    echo Compilation reussie!
    echo.
    echo Exemples d'utilisation:
    echo java -cp . CrackerApp --type dictionary --target local --login admin
    echo java -cp . CrackerApp --type bruteforce --target local --login admin
    echo java -cp . CrackerApp --help
) else (
    echo Erreur de compilation!
)
pause
