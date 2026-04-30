# Object-Oriented Programming (OOPs) Concepts, Design Principles, and Design Patterns

This document provides a comprehensive overview of fundamental concepts in object-oriented programming, key design principles for robust software, and common design patterns, with references to their implementation in the **MediTrack** project. It also covers other essential Java development topics.

## 1. OOPs Concepts

Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects", which can contain data and code.

### 1.1. Encapsulation
*   **Definition**: Bundling the data (attributes) and methods (functions) that operate on the data within a single unit (class), and restricting direct access to some components using access modifiers like `private`.
*   **MediTrack Example**: In the `Patient` class, the fields `id`, `name`, and `age` are private, and access is provided through public getter and setter methods. This ensures data integrity and controlled access to the object's state.

### 1.2. Inheritance
*   **Definition**: A mechanism where a new class (subclass) inherits properties and behaviors from an existing class (superclass). This promotes code reusability and establishes an "is-a" relationship.
*   **MediTrack Example**: Both `Doctor` and `Patient` inherit from the `Person` class (which itself might inherit from `MedicalEntity`). This allows them to share common attributes like `id`, `name`, and `age` and common behaviors.

### 1.3. Polymorphism
*   **Definition**: The ability of an object to take on many forms.
    *   **Runtime Polymorphism (Method Overriding)**: Achieved when a subclass provides a specific implementation for a method that is already defined in its superclass.
    *   **Compile-time Polymorphism (Method Overloading)**: Achieved when multiple methods in the same class have the same name but different parameters.
*   **MediTrack Example**: The `BillingStrategy` interface has an `calculateBill()` method. Classes like `NormalBillingStrategy`, `InsuranceBillingStrategy`, and `DiscountBillingStrategy` override this method to provide different billing logic, demonstrating runtime polymorphism.

### 1.4. Abstraction
*   **Definition**: Hiding complex implementation details and showing only essential features. Focuses on "what" an object does rather than "how" it does it. Achieved using abstract classes and interfaces.
*   **MediTrack Example**: The `BillingStrategy` interface is an abstraction. The `Bill` class uses this interface without needing to know the specific implementation details of how the discount or insurance is calculated. Similarly, `Payable` and `Observer` interfaces provide abstraction.

### 1.5. Advanced OOP Concepts

*   **Cloning (Deep vs Shallow)**:
    *   **Shallow Copy**: Creates a new object, but copies references to the original object's fields. If the fields are mutable objects, changes to them will be reflected in both the original and cloned objects.
    *   **Deep Copy**: Creates a new object and recursively copies all objects referenced by the original object's fields. This ensures that the cloned object is completely independent of the original.
*   **Immutability**:
    *   **Definition**: An object whose state cannot be modified after it is created.
    *   **Benefits**: Thread-safety, easier reasoning about code, suitable for keys in collections.
    *   **How to achieve**: Declare fields as `final`, don't provide setter methods, make fields private, prevent subclassing (e.g., `final class`), and ensure any mutable components are deep-copied or are themselves immutable.
*   **Enums**:
    *   **Definition**: A special data type that enables a variable to be a set of predefined constants.
    *   **MediTrack Example**: `Specialization`, `Entities`, and `AppointmentStatus` are enums, providing type-safe and readable constant values.
*   **Static Initialization**:
    *   **Definition**: The process of initializing static members (fields and blocks) of a class. Static blocks are executed once when the class is loaded into the JVM.
    *   **Usage**: Often used for one-time setup, such as loading configuration or initializing static utility classes.

---

## 2. Design Principles

### 2.1. SOLID Principles

*   **S - Single Responsibility Principle (SRP)**: A class should have only one reason to change.
    *   *MediTrack Example*: `DoctorService` handles doctor-related logic, while `AppointmentService` handles appointments. `BillSummary` is responsible only for printing the bill summary.
*   **O - Open/Closed Principle (OCP)**: Software entities should be open for extension but closed for modification.
    *   *MediTrack Example*: Adding a new `CharityBillingStrategy` doesn't require modifying the `Bill` class; you just implement the `BillingStrategy` interface, extending functionality without changing existing code.
*   **L - Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types without altering the correctness of the program.
    *   *MediTrack Example*: Any class implementing `BillingStrategy` (e.g., `NormalBillingStrategy`, `InsuranceBillingStrategy`) can be used interchangeably wherever a `BillingStrategy` is expected in the `Bill` class.
*   **I - Interface Segregation Principle (ISP)**: Clients should not be forced to depend on interfaces they do not use.
    *   *MediTrack Example*: Using specialized interfaces like `Payable` or `Observer` ensures that classes only implement methods relevant to their specific role, rather than a single, bloated interface.
*   **D - Dependency Inversion Principle (DIP)**: High-level modules should not depend on low-level modules. Both should depend on abstractions.
    *   *MediTrack Example*: The `Bill` class depends on the `BillingStrategy` interface (an abstraction), not on concrete strategy classes like `NormalBillingStrategy`. This makes the `Bill` class flexible and decoupled from specific billing implementations.

### 2.2. DRY (Don't Repeat Yourself)
*   **Definition**: Every piece of knowledge must have a single, unambiguous, authoritative representation within a system. Avoid duplication of logic.
*   **MediTrack Example**: The `IdGenerator` utility provides a centralized way to generate unique IDs, avoiding repetitive ID generation logic in every entity or service. `DateUtil` centralizes date formatting and parsing.

---

## 3. Design Patterns

### 3.1. Creational Patterns
*   **Factory Method Pattern**:
    *   *Definition*: Defines an interface for creating an object, but lets subclasses decide which class to instantiate.
    *   *MediTrack Implementation*: `BillingStrategyFactory`. It centralizes the creation of different `BillingStrategy` objects based on a given type, decoupling the client from concrete strategy classes.
*   **Singleton Pattern**:
    *   *Definition*: Ensures a class has only one instance and provides a global point of access to that instance.
    *   *MediTrack Potential Implementation*: The `DataStore` class could be implemented as a Singleton to ensure a single, consistent source of truth for all application data.

### 3.2. Structural Patterns
*   **Facade Pattern**:
    *   *Definition*: Provides a simplified interface to a complex subsystem.
    *   *MediTrack Implementation*: The `Main` class's menu system acts as a facade, providing a simple, high-level interface for users to interact with the complex underlying services (`DoctorService`, `PatientService`, `AppointmentService`, `Bill`).

### 3.3. Behavioral Patterns
*   **Strategy Pattern**:
    *   *Definition*: Defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.
    *   *MediTrack Implementation*: `BillingStrategy`. It encapsulates different billing algorithms (Normal, Insurance, Discount) and allows the `Bill` class to use any of these strategies interchangeably at runtime.
*   **Observer Pattern**:
    *   *Definition*: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
    *   *MediTrack Implementation*: The `NotificationService` (which could be an `ObserverSubject`) with `EmailNotificationService` and `SmsNotificationService` (as `Observer`s). When an appointment status changes or is created, these "observers" are notified to send alerts.
*   **Template Method Pattern**:
    *   *Definition*: Defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.
    *   *MediTrack Potential Implementation*: A base `ReportGenerator` abstract class could define the steps for generating a report (e.g., `fetchData()`, `formatHeader()`, `formatBody()`, `formatFooter()`), with subclasses implementing specific formatting steps for different report types (e.g., `PDFReportGenerator`, `CSVReportGenerator`).

---

## 4. Core Java Features

### 4.1. Collections, Generics, Comparators, Iterators, equals/hashCode

*   **Collections Framework**: Provides interfaces and classes to represent groups of objects (e.g., `List`, `Set`, `Map`).
    *   *MediTrack Example*: `DataStore` uses `HashMap` (`Map<String, T>`) to store entities, and services use `ArrayList` (`List<Doctor>`, `List<Patient>`) to manage lists of objects.
*   **Generics**: Allow types (classes and interfaces) to be parameters when defining classes, interfaces, and methods.
    *   *MediTrack Example*: `DataStore<T extends MedicalEntity>` uses generics to provide type-safe storage for different medical entities.
*   **Comparators**: Used to define custom sorting orders for objects.
    *   *Usage*: Could be used to sort lists of `Doctor`s by `consultationFee` or `Patient`s by `age`.
*   **Iterators**: Provide a standard way to traverse elements in a collection.
    *   *Usage*: Implicitly used in enhanced for-loops (`for (Doctor doctor : doctors)`).
*   **`equals()` and `hashCode()`**:
    *   **`equals()`**: Used to compare the content of two objects for equality.
    *   **`hashCode()`**: Returns an integer hash code value for the object. Essential for correct functioning of hash-based collections (`HashMap`, `HashSet`).
    *   *MediTrack Example*: `Person` or `MedicalEntity` classes should override `equals()` and `hashCode()` to ensure proper identification and comparison of entities, especially when stored in `DataStore`'s `HashMap`.

### 4.2. Exception Handling

*   **Definition**: A mechanism to handle runtime errors and abnormal conditions gracefully, preventing program crashes.
*   **Custom Exceptions**:
    *   *MediTrack Example*: `InvalidDataException` and `AppointmentNotFoundException` are custom exceptions, allowing the application to throw and catch specific errors related to business logic.
*   **Exception Chaining**:
    *   *Definition*: Allows an exception to encapsulate another exception, preserving the stack trace of the original cause. Achieved using `initCause()` or constructor arguments.
    *   *Usage*: Useful for translating low-level exceptions into higher-level, more meaningful application exceptions.
*   **`try-with-resources`**:
    *   *Definition*: A `try` statement that declares one or more resources. The resources are closed automatically at the end of the `try` block, regardless of whether the `try` block completes normally or abruptly.
    *   *Usage*: Ideal for managing resources like `Scanner`, `FileInputStream`, database connections, etc., ensuring they are always closed.

### 4.3. Java 8+ Features

*   **Streams**:
    *   **Definition**: A sequence of elements from a source that supports aggregate operations. They enable functional-style operations on collections.
    *   *Usage*: Could be used in services to filter, map, or reduce collections of doctors, patients, or appointments (e.g., `doctorService.getAllDoctors().stream().filter(d -> d.getSpecialization() == CARDIOLOGIST).collect(Collectors.toList())`).
*   **Lambdas (Lambda Expressions)**:
    *   **Definition**: A concise way to represent an anonymous function. They are primarily used to implement functional interfaces.
    *   *Usage*: Often used with Streams, `Comparator`s, `Runnable`s, and other functional interfaces to write more compact and readable code.

---

## 5. Concurrency

*   **Definition**: The ability of different parts of a program to execute independently or in parallel.
*   **Threads**:
    *   **Definition**: The smallest unit of execution within a process. Allows a program to perform multiple tasks concurrently.
    *   *Usage*: Could be used for background tasks like sending notifications or performing long-running data processing.
*   **Synchronization**:
    *   **Definition**: A mechanism to control access to shared resources by multiple threads, preventing data corruption and ensuring thread safety. Achieved using `synchronized` blocks/methods or `java.util.concurrent` utilities.
    *   *Usage*: Essential when multiple threads might modify shared data structures like the `DataStore`.
*   **`AtomicInteger`**:
    *   **Definition**: A class in `java.util.concurrent.atomic` that provides atomic operations on an `int` value. It's a thread-safe alternative to `int` for counters.
    *   *MediTrack Potential Implementation*: `IdGenerator` could use `AtomicInteger` to ensure unique ID generation is thread-safe if multiple threads were requesting IDs concurrently.
*   **`TimerTask`**:
    *   **Definition**: A task that can be scheduled for one-time or repeated execution by a `Timer`.
    *   *Usage*: Could be used to schedule periodic tasks, such as sending daily reminders or generating weekly reports.

---

## 6. Development Practices

### 6.1. Java Setup and JVM Basics

*   **JDK (Java Development Kit)**: Contains tools for developing Java applications (compiler, debugger, JRE).
*   **JRE (Java Runtime Environment)**: Provides the libraries and JVM needed to run Java applications.
*   **JVM (Java Virtual Machine)**: An abstract machine that provides a runtime environment in which Java bytecode can be executed. It's responsible for loading, verifying, and executing Java code.

### 6.2. JavaDocs and Command-Line Usage

*   **JavaDocs**:
    *   **Definition**: A documentation generator that creates API documentation in HTML format from source code comments.
    *   **Usage**: Essential for documenting classes, methods, and fields, making code easier to understand and maintain for other developers.
*   **Command-Line Usage**:
    *   **Definition**: Running Java applications directly from the command line using `javac` (compiler) and `java` (JVM launcher).
    *   **Usage**: `javac Main.java` to compile, `java Main` to run. Useful for understanding the build process and deploying simple applications.

### 6.3. Git-based Collaboration

*   **Definition**: Using Git, a distributed version control system, for managing source code and facilitating collaboration among developers.
*   **Key Concepts**:
    *   **Repository**: The central place where all project files and their history are stored.
    *   **Commits**: Snapshots of your project at a specific point in time.
    *   **Branches**: Independent lines of development, allowing features to be developed in isolation.
    *   **Merging/Rebasing**: Integrating changes from one branch into another.
    *   **Pull Requests**: A mechanism to propose changes and have them reviewed before merging into a main branch.
    *   **Usage**: Essential for team development, tracking changes, reverting to previous versions, and managing different features concurrently.


---
## 7. Immutability & Thread Safety
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

## 8. Dynamic Dispatch (Runtime Polymorphism)

The billing module uses dynamic dispatch to determine the correct billing logic at runtime.

```java
BillingStrategy strategy = new InsuranceBillingStrategy();
strategy.calculateTotal(...);
```

The JVM invokes the appropriate method based on the actual object type, enabling flexible and extensible behavior without conditional logic.

---

## 9. Key Improvements Made

* Replaced switch-based factory with Map-based strategy registration (OCP compliance)
* Removed unsafe type casting (`double → int`) to preserve financial precision
* Ensured all billing calculations are encapsulated within strategy classes (SRP)
* Used immutable `BillSummary` (Java record) for thread-safe results
* Leveraged dynamic dispatch for runtime behavior selection
* Improved extensibility for future billing types without code modification

---