# MediTrack-Clinic-Appointment-Management-System




## Design Principles – Billing Module (SOLID)

The Billing module in MediTrack is designed using SOLID principles to ensure scalability, maintainability, and clean separation of concerns.

### 1. Single Responsibility Principle (SRP)

Each class has a single, well-defined responsibility:

* `BillingStrategy` implementations handle only billing calculations.
* `Bill` acts as an orchestrator that delegates calculation to the strategy.
* `BillSummary` is an immutable data holder for final billing results.

**Improvement Made:**
Removed mixed responsibilities in `Bill` (e.g., type casting and calculation logic). Financial calculations are now fully handled by strategy classes.

---

### 2. Open/Closed Principle (OCP)

The system is open for extension but closed for modification.

* New billing types (e.g., `CorporateBillingStrategy`) can be added without modifying existing logic.
* Replaced switch-based factory with a **Map-based strategy registry**.

**Before (Violation):**

* Required modifying `BillingStrategyFactory` for every new type.

**After (Improved):**

* Strategies are registered in a map and can be extended without changing existing code.

---

### 3. Liskov Substitution Principle (LSP)

All implementations of `BillingStrategy` can be used interchangeably.

Example:

```java
BillingStrategy strategy = new InsuranceBillingStrategy();
```

The system behaves correctly regardless of the concrete implementation.

---

### 4. Interface Segregation Principle (ISP)

The `BillingStrategy` interface is small and focused:

```java
public interface BillingStrategy {
    double calculateTotal(double consultationFee, double additionalCharges);
}
```

Clients are not forced to implement unnecessary methods.

---

### 5. Dependency Inversion Principle (DIP)

High-level modules depend on abstractions, not concrete classes.

* `Bill` depends on `BillingStrategy` (interface), not specific implementations.
* Strategy is injected at runtime (via Factory or caller).

Example:

```java
BillingStrategy strategy = BillingStrategyFactory.getStrategy(type);
Bill bill = new Bill(..., strategy);
```

---
## Immutability & Thread Safety
Once created, the object state cannot change.
BillSummary is implemented as a Java record, making it:
* Final
* Fields are implicitly private final
* No setters

This guarantees thread safety because:
* No shared mutable state
* No synchronization required
* Safe for concurrent read access
---

## Dynamic Dispatch (Runtime Polymorphism)

The billing module uses dynamic dispatch to determine the correct billing logic at runtime.

```java
BillingStrategy strategy = new InsuranceBillingStrategy();
strategy.calculateTotal(...);
```

The JVM invokes the appropriate method based on the actual object type, enabling flexible and extensible behavior without conditional logic.

---

## Key Improvements Made

* Replaced switch-based factory with Map-based strategy registration (OCP compliance)
* Removed unsafe type casting (`double → int`) to preserve financial precision
* Ensured all billing calculations are encapsulated within strategy classes (SRP)
* Used immutable `BillSummary` (Java record) for thread-safe results
* Leveraged dynamic dispatch for runtime behavior selection
* Improved extensibility for future billing types without code modification

---

## Summary

The Billing module demonstrates a clean application of:

* Strategy Pattern (for flexible billing logic)
* Factory Pattern (for object creation)
* Dynamic Dispatch (runtime polymorphism)

This results in a modular, extensible, and maintainable design aligned with real-world backend system practices.
