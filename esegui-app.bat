@echo off
echo === JamFinder ===
echo.
echo Compilazione del progetto...

REM Crea directory bin se non esiste  
if not exist bin mkdir bin

REM Compila tutti i file Java necessari
"C:\Program Files\Java\jdk-11.0.17\bin\javac.exe" -d bin -sourcepath src/main/java src/main/java/com/jamfinder/observer/*.java src/main/java/com/jamfinder/composite/*.java src/main/java/com/jamfinder/model/*.java src/main/java/com/jamfinder/factory/*.java src/main/java/com/jamfinder/io/*.java src/main/java/com/jamfinder/util/*.java src/main/java/com/jamfinder/strategy/*.java src/main/java/com/jamfinder/*.java

if %errorlevel% equ 0 (
    echo [OK] Compilazione completata!
    echo.
    echo === Avvio JamFinder ===
    echo.
    "C:\Program Files\Java\jdk-11.0.17\bin\java.exe" -cp bin com.jamfinder.JamFinderMain
) else (
    echo [ERRORE] Errore nella compilazione
    pause
)

echo.
echo Applicazione terminata! Premi un tasto per chiudere...
pause
