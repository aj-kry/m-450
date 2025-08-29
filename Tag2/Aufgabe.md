## Übung 1 
### Tabelle mit abstrakten Testfällen
| Bedingung für Preis `p` (CHF) | Rabatt | Bemerkung |
|-----------------------------------|-------------------|-----------|
| `p < 15000`                      | 0 %               | Bereich „kein Rabatt“ |
| `15000 <= p <= 20000`            | 5 %               | „bis zu 20’000“ inkl. |
| `20000 < p < 25000`              | 7 %               | „unter 25’000“ |
| `p >= 25000`                     | 8.5 %             | „darüber“ = ab 25’000 |
| `p <= 0`                         | Eingabe ungültig  | Negativ/Nullpreis |
| `p` nicht numerisch              | Eingabe ungültig  | Text, leere Eingabe |

### Tabelle mit konkreten Testfällen
| Kaufpreis (CHF) | Erwarteter Rabatt | Rabattbetrag (CHF) | Endpreis (CHF) | Begründung |
|-----------------|-------------------|--------------------|----------------|------------|
| 14’999.95       | 0 %               | 0.00               | 14’999.99      | unter 15’000 |
| 15’000.00       | 5 %               | 750.00             | 14’250.00      | untere Grenze |
| 20’000.00       | 5 %               | 1’000.00           | 19’000.00      | obere Grenze |
| 20’000.05       | 7 %               | 1’400.00           | 18’600.01      | knapp über 20’000  |
| 24’999.95       | 7 %               | 1’750.00           | 23’249.99      | obere Grenze |
| 25’000.00       | 8.5 %             | 2’125.00           | 22’875.00      | untere Grenze  |
| 80’000.00       | 8.5 %             | 6’800.00           | 73’200.00      | hoher Preis  |
| 0.00            | –                 | –                  | –              | ungültig  |
| -1.00           | –                 | –                  | –              | ungültig  |
| „zwanzigtausend“| –                 | –                  | –              | ungültig  |

## übung 2 

| ID | Beschreibung | Erwartetes Resultat | Effektives Resultat | Status | Mögliche Ursache |
|---:|---|---|---|---|---|
| 1 | **Suche & Verfügbarkeit**: Nutzer gibt Standort ein und startet die Suche. | Trefferliste zeigt verfügbare Fahrzeugkategorien mit Preisen; keine Fehlermeldung. | — | Offen | Falscher Standort wird angezeigt; Falsche Zeit-/Datumsvalidierung;  Caching veraltet. |
| 2 | **Preisberechnung**: Auswahl eines Fahrzeugs, Steuern/Gebühren. | Gesamtpreis berechnet korrekt (Grundtarif + Steuern/Gebühren); Währung korrekt; Preis bleibt konsistent bis zur Zahlung. | — | Offen | Falsche Steuersätze; Rundungsfehler; fehlende Gebühr im Checkout. |
| 3 | **Buchung & Zahlung**: Checkout mit gültiger Kreditkarte (inkl. 3‑D‑Secure, falls aktiv) und Zustimmung zu AGB/Datenschutz. | Zahlung wird autorisiert; Buchung wird erstellt; Bestätigungsseite mit Buchungsnummer; Bestätigungs‑E‑Mail/SMS wird versendet. | — | Offen | Payment‑Gateway down; doppelte Buchung durch Retry; E‑Mail‑Service fehlerhaft. |
| 4 | **Alters-/Führerscheinprüfung**: Kunde unter Mindestalter bzw. ohne ausreichende Fahrpraxis. | Buchung wird blockiert oder Jungfahrergebühr wird korrekt hinzugefügt; klare Fehlsmeldung. | — | Offen | Unvollständige Validierungsregeln; Minderjähriger Fahrer; UI lässt falsche Eingaben zu. |
| 5 | **Stornierung & Erstattung**: Storierung innerhalb der Frist. | Stornierung wird entsprechend Tarifbedingungen akzeptiert/abgelehnt; Erstattungsbetrag korrekt berechnet und bestätigt. | — | Offen | Tariflogik falsch gemappt;  Refund‑API nicht erreichbar. |


## Übung 3

Die Beispiel-App bietet einfache Bankfunktionen (Konto anlegen, Kontostand anzeigen, Ein-/Auszahlung, Währungsumrechnung). Relevante Klassen im Codepaket:

* `Account` (Konto, Nutzer, Währung, Balance; u. a. `getBalance()`, `getCurrency()`, `getId()`, `pseudoDeleteAccount()`),
* `Bank` (verwaltet `accounts`, z. B. `createAccount(...)`, `printBalance(...)`, `printAccountsList()`, `printOtherAccounts(...)`, `getNumberOfAccounts()`),
* `Counter` (Konsolen-Menü/Interaktion, Regex-Parsing für Eingaben),
* `ExchangeRateOkhttp` (`getExchangeRate(from,to)` mit HTTP + Gson),
* `Main` (Start, Demo-Konten, Menüschleife), `Currency` (USD/EUR/CHF).

### A) Mögliche **Black-Box-Testfälle** (Benutzersicht)

| ID   | Beschreibung (Eingabe/Aktion)                              | Erwartetes Ergebnis                                                   |
| ---- | ---------------------------------------------------------- | --------------------------------------------------------------------- |
| BB1  | **Konto anlegen** (Name, Währung, Startsaldo)              | Konto erscheint in Liste; Startsaldo/ISO-Währung korrekt              |
| BB2  | **Kontostand anzeigen**                                    | Formatierter Saldo wird ausgegeben (2 Dezimalstellen)                 |
| BB3  | **Einzahlung** (positiver Betrag)                          | Saldo steigt exakt um Betrag                                          |
| BB4  | **Abhebung ≤ Saldo**                                       | Saldo sinkt exakt um Betrag                                           |
| BB5  | **Abhebung > Saldo**                                       | Abweisung/Fehlermeldung; Saldo unverändert                            |
| BB6  | **Währungsumrechnung** (z. B. „USD → CHF“)                 | Kurs wird angezeigt (z. B. „1 USD = x CHF“); bei Fehler klare Meldung |
| BB7  | **Ungültige Eingabe** (Text bei Betrag, falscher ISO-Code) | Validierungsfehler; kein Absturz                                      |
| BB8  | **Kontoliste drucken**                                     | Alle Konten mit ID/Name/Währung werden gelistet                       |
| BB9  | **Programm beenden** aus Menü                              | Sauberer Exit ohne Stacktrace                                         |
| BB10 | **Mehrere Konten anlegen**                                 | `getNumberOfAccounts()` steigt konsistent an                          |

> Hinweis: Black-Box = nur Ein-/Ausgaben & Verhalten, interne Implementierung egal.

### B) **White-Box-Kandidaten** (methodenbezogene Tests)

| Methode (Klasse)                                             | Wichtige Fälle                                                                                |
| ------------------------------------------------------------ | --------------------------------------------------------------------------------------------- |
| `createAccount(name,currency,start)` (**Bank**)              | legt Objekt an, fügt es der Liste hinzu, ID/Startsaldo/Währung korrekt                        |
| `getNumberOfAccounts()` (**Bank**)                           | nach 0,1,n Anlegungen korrekt                                                                 |
| `printAccountsList()` / `printOtherAccounts(acc)` (**Bank**) | keine NPEs bei leeren/mehreren Konten; Ausgabe enthält erwartete IDs/Namen                    |
| `pseudoDeleteAccount()` (**Account**)                        | setzt Felder wie dokumentiert (Name→`null`, Balance→`0`, Currency→`null`, ID→`0`)             |
| `getExchangeRate(from,to)` (**ExchangeRateOkhttp**)          | normaler Response; HTTP-Fehler (4xx/5xx); Timeout; ungültiges JSON → robuste Fehlerbehandlung |
| Regex-Parsing in **Counter** (Eingaben „FROM TO“)            | akzeptiert gültige Muster; lehnt falsche ab; Gross/klein-Schreibung                            |

> Für HTTP/GSON Tests: Netzaufrufe **mocken** (z. B. OkHttp MockWebServer) und fehlerhafte/partielle JSONs einspeisen.

### C) **Verbesserungen / Best Practices**

* **Geldbeträge nicht mit `double`** rechnen → **`BigDecimal`** (Rundungsfehler vermeiden), feste `RoundingMode`.
* **Validierung/Fehler**: keine „stille“ Fehler; klare Exceptions/Fehlermeldungen statt z. B. `0.0` als Fehlerwert.
* **Trennung von Anliegen (SoC)**: **I/O (Console)** von **Domänenlogik** trennen (keine `System.out` in Kernlogik; Rückgabewerte statt Direkt-Print).
* **Dependency Injection**: HTTP-Client/Gson injizieren → testbar ohne echte Calls.
* **Kein „Pseudo-Delete“**: statt Felder nullen → `active`-Flag oder tatsächliches Entfernen; vermeidet inkonsistente Zustände.
* **Immutables & Kapselung**: Felder privat, nur nötige Getter; IDs unveränderlich.
* **Logging** statt `System.out` in Logikschichten.
* **Unit-Tests** (Junit/Mockito) + **CI** (GitHub Actions) einrichten.
* **Internationalisierung**: Ausgabeformat (Währung, Dezimaltrennzeichen) explizit festlegen.
* **Fehlerrobustheit**: Zeitüberschreitung/Netzfehler bei Kursabfrage abfangen und dem Benutzer sinnvoll melden.

### D) Testfälle tabellarisch (fürs Repo)

```markdown
#### Black-Box

| ID | Test | Erwartet |
|----|------|----------|
| BB1 | Konto anlegen (Musterkunde, CHF, 100.00) | Konto gelistet; Saldo 100.00 CHF |
| BB3 | Einzahlung 50.00 | Saldo +50.00 |
| BB4 | Abhebung 20.00 (Saldo reicht) | Saldo –20.00 |
| BB5 | Abhebung 1’000.00 (Saldo zu klein) | Fehlermeldung; Saldo unverändert |
| BB6 | Kursabfrage USD→CHF | Kurssatz; kein Crash bei Netzfehler |

#### White-Box (Ausschnitt)

- Bank: `createAccount`, `getNumberOfAccounts`
- Account: `pseudoDeleteAccount`
- ExchangeRateOkhttp: `getExchangeRate` (OK/Fehler/Timeout/invalid JSON)
- Counter: Regex-Parsing gültig/ungültig
```

 