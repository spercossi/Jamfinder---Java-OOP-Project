@echo off
echo === JamFinder Test Suite ===
echo.

REM Crea directory bin se non esiste  
if not exist bin mkdir bin

echo Compilazione test...
echo.

REM Compila prima il main code
REM Compila prima il codice principale
"C:\Program Files\Java\jdk-11.0.17\bin\javac.exe" -d bin -sourcepath src/main/java src/main/java/com/jamfinder/observer/*.java src/main/java/com/jamfinder/composite/*.java src/main/java/com/jamfinder/model/*.java src/main/java/com/jamfinder/factory/*.java src/main/java/com/jamfinder/io/*.java src/main/java/com/jamfinder/util/*.java src/main/java/com/jamfinder/strategy/*.java src/main/java/com/jamfinder/*.java

if %errorlevel% neq 0 (
    echo [ERRORE] Errore nella compilazione del codice principale
    pause
    exit /b 1
)

REM Compila i test
"C:\Program Files\Java\jdk-11.0.17\bin\javac.exe" -d bin -sourcepath src/test/java -cp bin src/test/java/com/jamfinder/*.java

if %errorlevel% neq 0 (
    echo [ERRORE] Errore nella compilazione dei test
    pause
    exit /b 1
)

echo [OK] Compilazione completata!
echo.
echo === Esecuzione Test Suite ===
echo.

REM Esegui tutti i test con assertions abilitate
"C:\Program Files\Java\jdk-11.0.17\bin\java.exe" -ea -cp bin com.jamfinder.RunAllTests

echo.
echo Test completati! Premi un tasto per chiudere...
pause > nul
