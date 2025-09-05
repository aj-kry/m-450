## Übung 1 
Befindet sich im ordner simpleCalc

## Übung 2 JUnit 5 – Zusammenfassung 
 

### 1. @Test
- Markiert eine Methode als Test.  
```java
@Test
void testAddition() {
    assertEquals(4, 2 + 2);
}
```

### 2. Assertions
- Prüfen erwartete vs. tatsächliche Werte.  
- Beispiele:  
```java
assertEquals(5, calc.add(2,3));
assertTrue(list.isEmpty());
assertThrows(IllegalArgumentException.class, () -> calc.divide(1,0));
```

### 3. Lifecycle
- Steuern Setup/Teardown.  
- `@BeforeEach`, `@AfterEach` → pro Test  
- `@BeforeAll`, `@AfterAll` → einmalig  
```java
@BeforeEach
void init() { calc = new Calc(); }
```

### 4. Parameterized Tests
- Mehrere Eingaben automatisch testen.  
```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void testIsPositive(int n) {
    assertTrue(n > 0);
}
```

### 5. Tags & Suites
- `@Tag("slow")` → Gruppierung von Tests  
- `@Suite` + `@SelectClasses(...)` → Tests bündeln  

---

### Referenz
 [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)  

 
## Übung 3 Bank-Software – Uebersicht

### Zweck
- Verwaltung von Bankkonten (Erstellung, Ein- und Auszahlungen, Salden).  
- Unterstützung verschiedener Kontoarten (Spar-, Gehalts-, Jugendkonto).  
- Verwaltung von Buchungen mit Betraegen und Datum.  
- Formatierung von Beträgen/Datumsangaben über Hilfsklasse `BankUtils`.  

---

### Klassen und Beziehungen

#### 1. `Bank`
- Verwaltet Konten.  
- Methoden:  
  - `createAccount()` → neues Konto anlegen  
  - `deposit()` → Einzahlung  
  - `withdraw()` → Auszahlung  
  - `getBalance()` → Saldo abfragen  
  - `print()` → Uebersicht anzeigen  
- Beziehung: enthält 0..* `Account`  

---

#### 2. `Account` (Basisklasse)
- Attribute: `balance: long`, `id: String`  
- Methoden: `deposit()`, `withdraw()`, `canTransact()`, `print()`  
- Abstrakte Basis für verschiedene Kontoarten  
- Beziehung: enthält 0..* `Booking`  

---


#### 4. `SalaryAccount`
- Erbt von `Account`  
- Konto für Gehaltseingänge  
- Überschreibt `print()` und `withdraw()`  

#### 5. `PromoYouthSavingsAccount`
- Erbt von `SavingsAccount`  
- Jugend-Sparkonto  
- Besonderes Verhalten bei `deposit()` 

---

#### 6. `Booking`
- ReprÄsentiert eine Transaktion  
- Attribute: `amount: long`, `date: int`  
- Methode: `print()`  
- Beziehung: gehÖrt zu einem `Account`  

---

#### 7. `BankUtils`
- Statische Hilfsklasse  
- Konstanten: `AMOUNT_FORMAT`, `TWO_DIGIT_FORMAT`  
- Methoden:  
  - `formatAmount()` → Betrag formatieren  
  - `formatBankDate()` → Datum formatieren  
- Wird von `Booking` und `Bank` genutzt  

---

### Zusammenspiel
- `Bank` erstellt und verwaltet Konten.  
- `Account` fÜhrt Transaktionen aus, die als `Booking` gespeichert werden.  
- Spezielle Kontenarten passen Standardverhalten an.  
- `BankUtils` sorgt fÜr einheitliche Ausgabeformatierung.  

## Übung 4 
Befindet sich im 02_bank-vorgabe Ordner
