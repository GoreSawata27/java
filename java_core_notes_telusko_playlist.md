# Java Core Notes: From Basics to Collections and Streams

**Study guide based on the Telusko Java Tutorial for Beginners playlist**

Playlist: <https://www.youtube.com/playlist?list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5>

## How to use these notes

These notes consolidate the playlist's indexed lesson topics into a single reference, instead of repeating the same definition in separate lesson summaries. They are written in original wording, with definitions, mental models, syntax, examples, common mistakes, and revision points. The public lecture index lists topics from Java setup and variables through OOP, exceptions, threads, collections, streams, `var`, sealed classes, records, and a JUnit 5 introduction.

**Scope and accuracy note:** this is a structured study guide aligned to the playlist's published lesson sequence, not a verbatim transcript. I could verify the topic index, but I could not reliably access a full transcript for every video. Therefore, explanations below are consolidated Java teaching notes, not claims about the instructor's exact wording or every example used in each video.

**Version note:** most examples use conventional Java syntax and work on Java 17 or newer. Some features have version requirements noted where relevant: local-variable type inference with `var` (Java 10+), switch expressions (Java 14+), records (Java 16+), and sealed classes (Java 17+).

---

## Table of contents

1. [Java overview and setup](#1-java-overview-and-setup)
2. [Your first Java program](#2-your-first-java-program)
3. [Variables, data types, and literals](#3-variables-data-types-and-literals)
4. [Type conversion and casting](#4-type-conversion-and-casting)
5. [Operators](#5-operators)
6. [Decision making: if, ternary, and switch](#6-decision-making-if-ternary-and-switch)
7. [Loops](#7-loops)
8. [Methods](#8-methods)
9. [Classes, objects, and memory basics](#9-classes-objects-and-memory-basics)
10. [Arrays](#10-arrays)
11. [Strings, StringBuilder, and StringBuffer](#11-strings-stringbuilder-and-stringbuffer)
12. [Static members, encapsulation, and constructors](#12-static-members-encapsulation-and-constructors)
13. [Inheritance and core OOP](#13-inheritance-and-core-oop)
14. [Packages and access modifiers](#14-packages-and-access-modifiers)
15. [Polymorphism, dynamic dispatch, and final](#15-polymorphism-dynamic-dispatch-and-final)
16. [Object methods, casting, and wrapper classes](#16-object-methods-casting-and-wrapper-classes)
17. [Abstract classes and interfaces](#17-abstract-classes-and-interfaces)
18. [Nested classes, anonymous classes, enums, and annotations](#18-nested-classes-anonymous-classes-enums-and-annotations)
19. [Functional interfaces and lambda expressions](#19-functional-interfaces-and-lambda-expressions)
20. [Exception handling](#20-exception-handling)
21. [Reading input](#21-reading-input)
22. [Multithreading](#22-multithreading)
23. [Collections and generics](#23-collections-and-generics)
24. [Stream API](#24-stream-api)
25. [Modern Java features: `var`, records, sealed classes](#25-modern-java-features-var-records-sealed-classes)
26. [JUnit 5 introduction](#26-junit-5-introduction)
27. [Common mistakes and quick revision](#27-common-mistakes-and-quick-revision)
28. [Playlist lesson map](#28-playlist-lesson-map)
29. [Further learning and references](#29-further-learning-and-references)

---

# 1. Java overview and setup

## 1.1 What is Java?

**Java** is a general-purpose, strongly typed programming language and platform. It is widely used for backend services, enterprise applications, Android development, developer tools, and many other types of software.

Important characteristics:

- **Statically typed:** variable types are checked by the compiler.
- **Object-oriented:** classes and objects are central to the language, although Java also supports primitives and functional-style programming.
- **Compiled and executed on the JVM:** Java source is normally compiled into bytecode, which is executed by the Java Virtual Machine.
- **Portable:** bytecode can run on compatible JVM implementations on different operating systems.
- **Garbage-collected:** the JVM automatically reclaims memory occupied by objects that are no longer reachable, although programmers still need to close resources such as files and network connections.

## 1.2 JDK, JRE, and JVM

These terms describe different parts of the Java runtime and development setup.

| Term | Full name | Purpose |
|---|---|---|
| **JVM** | Java Virtual Machine | Loads and executes Java bytecode. |
| **JRE** | Java Runtime Environment | Conceptually, a runtime containing the JVM and libraries needed to run Java applications. Modern JDK distributions do not always ship a separate JRE installer. |
| **JDK** | Java Development Kit | Tools for developing Java applications, including the compiler (`javac`), launcher (`java`), and runtime components. |

**Remember:** to develop Java, install a JDK. The JVM alone is not a Java compiler.

## 1.3 How Java code runs

Typical flow:

1. Write source code in a `.java` file.
2. The Java compiler (`javac`) checks the source and produces `.class` bytecode files.
3. The `java` launcher starts a JVM.
4. The class loader loads the required classes.
5. The JVM verifies and executes the bytecode. Frequently executed code may be compiled into native machine code by the Just-In-Time (JIT) compiler.

Example commands:

```bash
javac Main.java
java Main
```

Do not use `java Main.class` for the normal class-launching command. Use the class name, without `.class`.

Useful setup checks after installing a JDK:

```bash
java -version
javac -version
```

Both commands should report an installed Java version. If `java` works but `javac` is not found, you may have only a runtime or your JDK's `bin` directory may not be on `PATH`. IDEs such as IntelliJ IDEA, Eclipse, or VS Code can help edit and run code, but they still need an installed and correctly configured JDK.

## 1.4 Bytecode and portability

- **Source code:** human-readable Java code in `.java` files.
- **Bytecode:** platform-independent instructions stored in `.class` files.
- **Machine code:** processor-specific instructions executed directly by a CPU.

Java portability is often summarized as “write once, run anywhere,” but this depends on a compatible JVM, available libraries, and avoiding platform-specific assumptions.

---

# 2. Your first Java program

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

Output:

```text
Hello, Java!
```

## 2.1 Understand each part

- `public`: the method is accessible to the Java launcher and other classes as required.
- `class Main`: declares a class named `Main`.
- `static`: lets the JVM invoke `main` without first creating an instance of `Main`.
- `void`: the method does not return a value.
- `main`: the conventional entry-point method name.
- `String[] args`: an array of command-line arguments.
- `System.out.println(...)`: prints a value and then moves to a new line.
- `{ ... }`: marks a block of code.
- `;`: terminates a statement.

For a `public` top-level class named `Main`, the source file is conventionally named `Main.java`.

## 2.2 `print`, `println`, and `printf`

```java
System.out.print("Hello ");
System.out.println("world");
System.out.printf("Age: %d, score: %.2f%n", 26, 91.456);
```

- `print` writes without automatically adding a newline.
- `println` writes a newline after the value.
- `printf` formats values. `%d` is for integral values, `%f` for floating-point values, `%.2f` prints two digits after the decimal, `%s` for strings, and `%n` for a platform-appropriate line separator.

## 2.3 Comments

```java
// One-line comment

/*
   Multi-line comment
*/

/**
 * Documentation comment used by Javadoc.
 */
```

Comments document code; they are not executed as program statements.

---

# 3. Variables, data types, and literals

## 3.1 Variable definition

A **variable** is a named storage location or named reference used by a program to hold a value.

```java
int age = 26;
String city = "Parbhani";
```

In `int age = 26;`:

- `int` is the declared type.
- `age` is the variable name.
- `26` is the value.
- `=` assigns the initial value.

Declare a local variable before using it. Local variables do not automatically receive default values, so they must be definitely assigned before being read.

## 3.2 Primitive data types

Java has eight primitive types.

| Type | Typical size | Use / example |
|---|---:|---|
| `byte` | 8 bits | Small signed integer, `byte level = 10;` |
| `short` | 16 bits | Small signed integer, `short year = 2026;` |
| `int` | 32 bits | Common integer type, `int count = 120;` |
| `long` | 64 bits | Large integer, `long population = 8_000_000_000L;` |
| `float` | 32 bits | Single-precision decimal, `float rate = 2.5F;` |
| `double` | 64 bits | Default floating-point type, `double price = 99.95;` |
| `char` | 16-bit UTF-16 code unit | `char grade = 'A';` |
| `boolean` | JVM-dependent storage representation | Logical `true` or `false`, `boolean active = true;` |

A Java `char` stores one UTF-16 code unit, not necessarily one complete user-perceived Unicode character. A `String` should usually be used for text.

## 3.3 Literals

A **literal** is a value written directly in source code. Java has several literal forms.

```java
int decimal = 26;         // decimal integer literal
int binary = 0b11010;     // binary, prefix 0b or 0B
int hex = 0x1A;           // hexadecimal, prefix 0x or 0X
int octal = 032;          // octal, leading 0 (avoid unless intended)
long big = 26L;
double rate = 1.25;
float ratio = 1.25F;
char letter = 'J';
String text = "Java";
boolean ready = true;
String missing = null;   // null applies to reference types, not primitives
```

Notes:

- Integer literals are decimal by default. `0b` indicates binary, `0x` indicates hexadecimal, and a leading `0` indicates octal. Leading-zero octal literals can be confusing, so binary or hexadecimal notation is usually clearer when the base matters.
- Use `L` for a `long` literal when needed. Uppercase `L` is easier to distinguish from the digit `1`.
- Decimal floating-point literals are `double` by default, so add `F` for a `float` literal.
- Single quotes create a `char` literal, such as `'A'`; double quotes create a `String` literal, such as `"A"`.
- Escape sequences include `\n` (newline), `\t` (tab), `\\` (backslash), `\"` (double quote), and `\'` (single quote).
- `null` is a literal that represents the absence of an object reference. It cannot be assigned to primitive variables.
- Underscores can separate digits for readability, for example `1_000_000`, but they cannot appear everywhere.

### Integer and decimal literal tips

```java
int million = 1_000_000;       // underscores improve readability
long distance = 9_000_000_000L; // L marks a long literal
float temperature = 36.5F;      // F marks a float literal
double amount = 36.5;            // double is the default decimal type
```

Underscores may separate digits for readability but cannot be placed arbitrarily, such as immediately beside a decimal point.

## 3.4 Reference types

Reference types include classes, arrays, interfaces, and enums. A reference variable refers to an object or can hold `null`.

```java
String name = "Sawata";
int[] numbers = {10, 20, 30};
Computer computer = new Computer(); // assumes Computer is a declared class
```

The primitive `int` variable stores an integer value. A reference variable such as `computer` holds a reference to an object, not the complete object itself.

## 3.5 Default values versus local variables

Fields receive default values when an object or class is initialized. Typical defaults are `0` for numeric primitive fields, `false` for `boolean`, `\u0000` for `char`, and `null` for reference fields.

```java
class Example {
    int count;       // defaults to 0
    boolean enabled; // defaults to false
    String name;     // defaults to null

    void demo() {
        int local;
        // System.out.println(local); // compile-time error: not initialized
    }
}
```

## 3.6 Constants with `final`

A local variable or field declared with `final` can be assigned only once.

```java
final double PI = 3.141592653589793;
```

For a `final` reference, the reference cannot be reassigned, but the referenced object may still be mutable:

```java
final java.util.List<String> names = new java.util.ArrayList<>();
names.add("Asha"); // valid
// names = new java.util.ArrayList<>(); // invalid reassignment
```

## 3.7 Variable naming conventions

- Variables and methods normally use `lowerCamelCase`: `totalPrice`, `calculateTotal()`.
- Classes and interfaces normally use `UpperCamelCase`: `BankAccount`, `Runnable`.
- Constants are often written in `UPPER_SNAKE_CASE`: `MAX_RETRIES`.
- Package names normally use lowercase: `com.example.billing`.

Names must follow Java identifier rules and cannot be reserved keywords such as `class`, `int`, or `return`.

## 3.8 Local-variable type inference with `var`

Since Java 10, `var` can infer the type of a local variable from its initializer:

```java
var message = "Hello"; // inferred as String
var total = 100;        // inferred as int
```

`var` is not dynamic typing. The inferred type remains fixed at compile time. It cannot be used for fields, method parameters, or a method's return type, and a local declared with `var` needs an initializer. See [Section 25](#25-modern-java-features-var-records-sealed-classes).

---

# 4. Type conversion and casting

**Type conversion** changes a value from one type to another.

## 4.1 Widening conversion

A widening primitive conversion moves to a type that can represent a broader range. It is commonly performed automatically.

```java
int count = 42;
double value = count; // int -> double
System.out.println(value); // 42.0
```

Common numeric widening path:

`byte -> short -> int -> long -> float -> double`

`char` can widen to `int`, `long`, `float`, or `double`. Actual precision is a separate issue: `float` cannot exactly represent every large integer.

## 4.2 Narrowing conversion

A narrowing conversion may lose information and usually requires an explicit cast.

```java
double price = 99.99;
int whole = (int) price;
System.out.println(whole); // 99, fractional part is discarded
```

Casting floating-point values to an integer type truncates toward zero. It does not round to the nearest integer.

```java
int result = (int) 8.9; // 8
```

Narrowing an integer to a smaller type can overflow or wrap according to the target type's bit representation:

```java
int number = 130;
byte small = (byte) number; // value is not 130 because byte cannot represent it
```

## 4.3 Numeric promotion

Small integral types are generally promoted to `int` during arithmetic operations.

```java
byte a = 10;
byte b = 20;
// byte c = a + b; // compile-time error: result of a + b is int
byte c = (byte) (a + b);
```

## 4.4 String and numeric conversions

```java
int value = Integer.parseInt("123");
double decimal = Double.parseDouble("12.75");
String text = String.valueOf(123);
```

`Integer.parseInt("abc")` throws `NumberFormatException`. Conversions from a number to a string can use `String.valueOf(...)` or concatenation, but explicit methods make intent clearer.

---

# 5. Operators

An **operator** performs an operation on one or more operands.

## 5.1 Arithmetic operators

| Operator | Meaning | Example |
|---|---|---|
| `+` | Addition; also string concatenation | `5 + 2`, `"Hi " + name` |
| `-` | Subtraction | `5 - 2` |
| `*` | Multiplication | `5 * 2` |
| `/` | Division | `5 / 2` gives `2` when both operands are integers |
| `%` | Remainder | `5 % 2` gives `1` |

Integer division discards the fractional part. Use a floating-point operand when a decimal result is required:

```java
System.out.println(5 / 2);       // 2
System.out.println(5 / 2.0);     // 2.5
System.out.println((double) 5 / 2); // 2.5
```

## 5.2 Increment and decrement

- `x++`: post-increment, evaluates to the old value, then increments `x`.
- `++x`: pre-increment, increments `x` first, then evaluates to the new value.
- `x--` and `--x` work analogously.

```java
int x = 5;
int a = x++; // a = 5, x = 6
int b = ++x; // x = 7, b = 7
```

Prefer simple statements over complicated expressions mixing increments. Such expressions are harder to read and debug.

## 5.3 Assignment operators

```java
int total = 10;
total += 5; // equivalent in value to total = total + 5
total -= 2;
total *= 3;
total /= 2;
total %= 4;
```

There are also bitwise compound assignments such as `&=`, `|=`, `^=`, `<<=`, `>>=`, and `>>>=`.

## 5.4 Relational operators

Relational operators evaluate to a `boolean`:

`==`, `!=`, `<`, `>`, `<=`, `>=`

```java
int age = 26;
System.out.println(age >= 18); // true
```

For primitive values, `==` compares values. For object references, `==` tests whether both references point to the same object. To compare the content of many objects such as strings, use their appropriate equality method, for example `equals`.

## 5.5 Logical operators and short-circuiting

- `&&`: logical AND, short-circuits when the left side is `false`.
- `||`: logical OR, short-circuits when the left side is `true`.
- `!`: logical NOT.
- `&` and `|` can also perform boolean AND/OR without short-circuiting; they also have bitwise uses for integral operands.
- `^`: boolean XOR or integral bitwise XOR.

```java
int age = 20;
boolean hasId = true;
if (age >= 18 && hasId) {
    System.out.println("Allowed");
}
```

Short-circuiting is useful for null checks:

```java
String name = null;
if (name != null && name.length() > 0) {
    System.out.println(name);
}
```

The right side is not evaluated when `name != null` is false, so this avoids a `NullPointerException` in this example.

## 5.6 Bitwise and shift operators

For integral types, bitwise operators work on individual bits:

- `&`: bitwise AND
- `|`: bitwise OR
- `^`: bitwise XOR
- `~`: bitwise complement
- `<<`: left shift
- `>>`: signed right shift, preserving the sign bit
- `>>>`: unsigned right shift, filling with zero bits

These are useful in low-level code, flags, masks, and performance-sensitive algorithms, but everyday business logic often needs only arithmetic and logical operators.

## 5.7 Operator precedence

Java has precedence rules, but use parentheses when an expression could be unclear:

```java
int result = (a + b) * c;
```

Parentheses make intent explicit and reduce mistakes during maintenance.

---

# 6. Decision making: if, ternary, and switch

## 6.1 `if` / `else`

Use `if` when a block should run only if a boolean condition is true.

```java
int age = 20;
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

The condition must be a `boolean`. Java does not treat integers such as `0` and `1` as false/true.

## 6.2 `else if`

Use an `else if` chain for mutually exclusive branches:

```java
int marks = 78;
if (marks >= 90) {
    System.out.println("A");
} else if (marks >= 75) {
    System.out.println("B");
} else if (marks >= 60) {
    System.out.println("C");
} else {
    System.out.println("Needs improvement");
}
```

Conditions are tested from top to bottom. Put more specific or higher-threshold conditions first when necessary.

## 6.3 Ternary operator

The conditional operator `condition ? valueIfTrue : valueIfFalse` is a compact expression that returns one of two values.

```java
int age = 20;
String category = age >= 18 ? "Adult" : "Minor";
```

Use it for simple value selection. Deeply nested ternaries are harder to read and usually belong in `if` statements instead.

## 6.4 Traditional `switch` statement

A `switch` selects a branch based on a selector value. Depending on the Java version and selector type, common selector types include integral types such as `int`, `char`, enum types, and `String`.

```java
int day = 2;
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    default:
        System.out.println("Other day");
}
```

With the traditional colon syntax, execution can fall through into the next case if you do not use `break`, `return`, or another control transfer. Fall-through can be intentional, but accidental fall-through is a common bug.

## 6.5 Switch with multiple labels

```java
int day = 6;
switch (day) {
    case 6:
    case 7:
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Weekday");
}
```

## 6.6 Switch expressions (Java 14+)

Switch expressions return a value. Arrow cases do not fall through.

```java
int day = 2;
String label = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    default -> "Other";
};
System.out.println(label);
```

For a multi-statement arrow case, use a block and `yield` to return the result:

```java
int number = 3;
String kind = switch (number) {
    case 1, 2 -> "Small";
    case 3 -> {
        System.out.println("Matched three");
        yield "Medium";
    }
    default -> "Other";
};
```

Use the switch style supported by the version your project targets.

---

# 7. Loops

A **loop** repeats a block of code while a condition or iteration rule allows it.

## 7.1 `while`

Use `while` when the loop should check the condition before each iteration and the number of iterations may not be known in advance.

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

If the condition is false initially, the loop body executes zero times.

## 7.2 `do-while`

A `do-while` loop evaluates its condition after executing the body, so the body executes at least once.

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

Notice the semicolon after the `while` condition.

## 7.3 `for`

A traditional `for` loop puts initialization, condition, and update in one place.

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

Use it when iteration has a clear counter or range. A loop condition is checked before each iteration.

## 7.4 Nested loops

A loop inside another loop is a **nested loop**. For example, this prints a multiplication grid:

```java
for (int row = 1; row <= 3; row++) {
    for (int col = 1; col <= 3; col++) {
        System.out.print(row * col + "\t");
    }
    System.out.println();
}
```

If the outer loop runs `m` times and the inner loop runs `n` times per outer iteration, the body typically runs `m * n` times.

## 7.5 `break` and `continue`

- `break` exits the nearest loop or switch statement.
- `continue` skips the remainder of the current iteration and proceeds to the next iteration.

```java
for (int i = 1; i <= 10; i++) {
    if (i == 3) continue; // skip 3
    if (i == 8) break;    // stop before printing 8
    System.out.println(i);
}
```

Labeled `break` and `continue` can target an outer loop, but should be used sparingly because they can make control flow difficult to follow.

## 7.6 Which loop should you choose?

- **`for`:** known range or counter-based iteration.
- **`while`:** repeat until a condition changes, such as reading until a sentinel value.
- **`do-while`:** body must execute at least once, such as showing a menu before asking whether to continue.
- **Enhanced `for`:** read each element of an array or `Iterable` collection without manually managing an index. See [Arrays](#10-arrays) and [Collections](#23-collections-and-generics).

Avoid accidental infinite loops. Check that the loop's state can eventually make the condition false.

---

# 8. Methods

A **method** is a named block of code that may accept inputs, perform work, and return a result. Methods help structure programs, avoid duplication, and name meaningful operations.

## 8.1 Method structure

```java
static int add(int a, int b) {
    return a + b;
}
```

- `static`: this method belongs to the class rather than a particular instance.
- `int`: return type.
- `add`: method name.
- `int a, int b`: parameters.
- `return a + b`: returns the result to the caller.

Calling it from a static context:

```java
int result = add(10, 20);
```

## 8.2 Parameters and arguments

A **parameter** appears in the method declaration; an **argument** is the actual value supplied by the caller.

```java
static void greet(String name) { // name is a parameter
    System.out.println("Hello, " + name);
}

greet("Asha"); // "Asha" is an argument
```

Java passes arguments **by value**. For object variables, the value copied is the reference. A method can mutate a referenced mutable object, but reassigning its local parameter does not reassign the caller's variable.

## 8.3 `void` and `return`

A `void` method does not return a value. It may use `return;` to exit early. A non-void method must return a compatible value along every path that completes normally.

```java
static void printStatus(boolean active) {
    if (!active) {
        System.out.println("Inactive");
        return;
    }
    System.out.println("Active");
}
```

## 8.4 Method overloading

**Overloading** means declaring methods with the same name but different parameter lists.

```java
static int add(int a, int b) {
    return a + b;
}

static double add(double a, double b) {
    return a + b;
}

static int add(int a, int b, int c) {
    return a + b + c;
}
```

A method cannot be overloaded by changing only its return type. The compiler selects an overload based mainly on the method name and argument types.

## 8.5 Recursion

A recursive method calls itself. It requires a base case to stop recursion.

```java
static int factorial(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("n must be non-negative");
    }
    if (n <= 1) return 1; // base case
    return n * factorial(n - 1);
}
```

Recursion is useful for naturally recursive structures and algorithms, but deep recursion may exhaust the call stack. An iterative solution may be simpler for some tasks.

---

# 9. Classes, objects, and memory basics

## 9.1 Class and object

A **class** defines a type, describing the state (fields) and behavior (methods) of objects. An **object** is an instance of a class created at runtime.

```java
class Computer {
    String brand;
    int ramGb;

    void showSpecs() {
        System.out.println(brand + " with " + ramGb + " GB RAM");
    }
}

public class Main {
    public static void main(String[] args) {
        Computer c = new Computer();
        c.brand = "Lenovo";
        c.ramGb = 16;
        c.showSpecs();
    }
}
```

## 9.2 Understanding `Computer c = new Computer()`

Break it down:

- `Computer` is the declared reference type.
- `c` is a local reference variable.
- `new Computer()` allocates and initializes a new object through the class's constructor.
- `=` stores the returned reference in `c`.

The variable `c` is not the object itself; it holds a reference to the object.

Other ways to obtain a reference include receiving it as a method parameter, returning it from a method, retrieving it from a collection, or assigning it from another reference. For an ordinary class instance, creating a new object usually involves `new`, but not every reference assignment creates an object.

```java
Computer first = new Computer();
Computer second = first; // copies the reference, not the object
second.brand = "Dell";
System.out.println(first.brand); // Dell
```

Both variables refer to the same object.

## 9.3 Fields and methods

- **Field (instance variable):** data stored per object.
- **Static field:** data shared by the class.
- **Instance method:** operates on an instance and can use `this`.
- **Static method:** belongs to the class and has no `this` reference.

## 9.4 Stack and heap: useful mental model

A simplified JVM mental model:

- Each thread has its own stack containing method-call frames, local variables, and call bookkeeping.
- Objects and arrays are generally allocated in heap memory managed by the JVM.
- Local variables that hold object references are typically in a stack frame, while the objects they refer to are typically on the heap.
- Static fields are associated with class-level runtime state, though exact physical memory placement is an implementation detail.

Do not treat this simplified model as a complete JVM memory specification. JIT optimizations can change physical allocation behavior while preserving Java program semantics.

## 9.5 Garbage collection

Garbage collection automatically reclaims memory used by objects that are no longer reachable from live code. Setting a reference to `null` does not guarantee that an object is collected immediately. `System.gc()` is only a request, not a guarantee.

Garbage collection does not replace closing files, sockets, database connections, or other external resources. Use try-with-resources for `AutoCloseable` resources.

---

# 10. Arrays

An **array** is a fixed-length object that stores multiple values of one component type. Array indexes start at `0`.

## 10.1 Why arrays?

Instead of declaring many separate variables, an array groups related values under one variable and allows indexed access.

```java
int[] marks = {78, 82, 91, 65};
System.out.println(marks[0]); // 78
System.out.println(marks.length); // 4
```

`length` is a field for arrays, so use `marks.length`, not `marks.length()`.

## 10.2 Creating arrays

```java
int[] numbers = new int[5]; // five ints, each initially 0
numbers[0] = 10;
numbers[1] = 20;

String[] names = new String[3]; // entries initially null
String[] teams = {"A", "B", "C"};
```

Valid indexes range from `0` to `length - 1`. An invalid index throws `ArrayIndexOutOfBoundsException`.

## 10.3 Iterating an array

Index-based loop:

```java
int[] values = {10, 20, 30};
for (int i = 0; i < values.length; i++) {
    System.out.println(values[i]);
}
```

Enhanced `for` loop:

```java
for (int value : values) {
    System.out.println(value);
}
```

Use an index-based loop when you need the index or need to update a specific array element. In an enhanced loop over primitive values, assigning to the loop variable does not update the array element.

## 10.4 Sum and maximum example

```java
int[] values = {7, 3, 12, 4};
int sum = 0;
int max = values[0];

for (int value : values) {
    sum += value;
    if (value > max) max = value;
}
System.out.println("Sum = " + sum);
System.out.println("Max = " + max);
```

This assumes the array is not empty. For general code, handle an empty array before using `values[0]`.

## 10.5 Multidimensional arrays

Java multidimensional arrays are arrays whose elements can themselves be arrays.

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};

System.out.println(matrix[1][2]); // 6
```

A 2D array is not required to be rectangular. Each row may have a different length.

```java
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[4];
jagged[2] = new int[1];

jagged[1][3] = 99;
```

Use nested loops to visit every value in a rectangular or jagged array, taking each row's length into account:

```java
for (int[] row : jagged) {
    for (int value : row) {
        System.out.print(value + " ");
    }
    System.out.println();
}
```

A 3D array adds one more array level, for example `int[][][] cube = new int[2][3][4];`.

## 10.6 Array of objects

```java
Computer[] computers = new Computer[2];
computers[0] = new Computer();
computers[1] = new Computer();
```

The first line creates an array with two reference slots. It does **not** create two `Computer` objects. Initially both slots contain `null`, so each object must be created or assigned separately before calling its methods.

## 10.7 Array limitations

- Fixed length after creation.
- Stores one declared component type, although that type may be a superclass or interface.
- Insertion and removal may require manual shifting.
- No built-in high-level methods like a collection's `add` or `remove`.

Use arrays when fixed-size indexed storage is suitable. Use collection types such as `ArrayList` when dynamic size and collection operations are needed.

---

# 11. Strings, StringBuilder, and StringBuffer

## 11.1 What is a `String`?

A `String` represents a sequence of characters. A Java `String` is an object, not a primitive type.

```java
String language = "Java";
System.out.println(language.length()); // 4
System.out.println(language.toUpperCase()); // JAVA
```

Useful methods include `length()`, `charAt(index)`, `substring(...)`, `contains(...)`, `equals(...)`, `equalsIgnoreCase(...)`, `startsWith(...)`, `endsWith(...)`, `replace(...)`, `split(...)`, `trim()`, and `isBlank()` (Java 11+).

Method names and overloads matter. For example, `substring(begin, end)` includes `begin` but excludes `end`.

## 11.2 Strings are immutable

**Immutable** means an object's observable value cannot be changed after creation. String methods that appear to modify text return a new `String`.

```java
String text = "java";
text.toUpperCase(); // returned value is ignored
System.out.println(text); // java

text = text.toUpperCase();
System.out.println(text); // JAVA
```

Immutability makes strings easier to share safely and enables features such as string pooling.

## 11.3 String pool and `==` versus `equals`

String literals can be reused from a pool of canonical strings. Do not rely on object identity to compare string contents.

```java
String a = "java";
String b = "java";
String c = new String("java");

System.out.println(a == b);      // commonly true because literals are pooled
System.out.println(a == c);      // false: different object identity
System.out.println(a.equals(c)); // true: same text content
```

Use `equals` for content equality. `==` checks reference identity for objects.

## 11.4 Concatenation

```java
String first = "Core";
String second = "Java";
String result = first + " " + second;
```

For a small number of concatenations, `+` is clear and normal. For repeated concatenation inside a loop, prefer a builder to avoid constructing many intermediate strings.

## 11.5 `StringBuilder`

`StringBuilder` is a mutable sequence of characters. It is usually the preferred option for repeated string construction in a single thread.

```java
StringBuilder builder = new StringBuilder();
builder.append("Hello");
builder.append(' ');
builder.append("Java");
String result = builder.toString();
System.out.println(result);
```

Common methods include `append`, `insert`, `delete`, `replace`, `reverse`, `length`, and `toString`.

## 11.6 `StringBuffer`

`StringBuffer` is similar to `StringBuilder`, but its methods are synchronized. This provides synchronization for individual operations, not automatically for every multi-step operation involving the buffer. Synchronization can add overhead, so use `StringBuilder` for typical single-threaded construction and choose `StringBuffer` only when its synchronization behavior is relevant.

| Type | Mutable? | Synchronization | Typical use |
|---|---|---|---|
| `String` | No | Not needed for its immutable content | Text values |
| `StringBuilder` | Yes | No built-in synchronization | Efficient single-threaded construction |
| `StringBuffer` | Yes | Synchronized methods | Legacy or specific synchronized use cases |

---

# 12. Static members, encapsulation, and constructors

## 12.1 Static variable

A **static field** belongs to the class and is shared across instances.

```java
class Employee {
    static String company = "Example Ltd";
    String name;

    Employee(String name) {
        this.name = name;
    }
}
```

Every `Employee` instance sees the same `Employee.company` field. Prefer accessing it through the class name, as in `Employee.company`, to show that it is class-level state.

## 12.2 Static method

A static method can be called using the class name without creating an object.

```java
class MathTools {
    static int square(int value) {
        return value * value;
    }
}

int answer = MathTools.square(5);
```

A static method cannot directly use `this`, and it cannot access an instance field without an object reference.

## 12.3 Static initialization block

A static block runs when the class is initialized, and is used for complex initialization of static state when a simple field initializer is insufficient.

```java
class Config {
    static final String ENVIRONMENT;

    static {
        ENVIRONMENT = "development";
        System.out.println("Config initialized");
    }
}
```

Keep static initialization simple. Work that can fail or has external side effects is often better handled through explicit initialization code.

## 12.4 Encapsulation

**Encapsulation** combines data and behavior in a class while controlling access to internal state. A common pattern is to make fields `private` and expose methods that validate or safely update the state.

```java
class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }
}
```

Clients cannot directly assign `balance`; they must use the class's public behavior. In real financial systems, monetary values often use `BigDecimal` or integer minor units instead of `double` to avoid floating-point rounding issues.

## 12.5 Getters and setters

- A **getter** returns a field's value.
- A **setter** changes a field's value, often after validation.

```java
class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        this.username = username;
    }
}
```

Do not generate getters and setters mechanically for every field if they undermine invariants. Sometimes a domain method such as `deposit()` is safer than a general-purpose setter such as `setBalance(...)`.

## 12.6 The `this` keyword

`this` refers to the current instance. It commonly distinguishes a field from a parameter with the same name.

```java
class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }
}
```

Here `this.name` means the instance field and `name` on the right is the parameter.

`this(...)` can invoke another constructor in the same class and must be the first constructor invocation statement in that constructor under traditional Java constructor rules:

```java
class Rectangle {
    private int width;
    private int height;

    Rectangle(int size) {
        this(size, size);
    }

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
```

Constructor invocation rules have evolved in newer Java releases, but for conventional code and this course, remember the common `this(...)` and `super(...)` chaining rules.

## 12.7 Constructors

A **constructor** initializes a new object. Its name matches the class name and it has no return type, not even `void`.

```java
class Laptop {
    String brand;
    int ramGb;

    Laptop(String brand, int ramGb) {
        this.brand = brand;
        this.ramGb = ramGb;
    }
}

Laptop laptop = new Laptop("Lenovo", 16);
```

### No-argument and parameterized constructors

- **No-argument constructor:** takes no parameters. It may be written explicitly.
- **Parameterized constructor:** accepts values used to initialize state.
- **Default constructor:** the compiler-provided no-argument constructor, generated only when the class declares no constructor at all.

```java
class A {
    A() { } // explicitly declared no-argument constructor
}

class B {
    B(int value) { }
    // Compiler does not add B() because a constructor already exists.
}
```

If a class declares only `B(int value)`, then `new B()` will not compile unless a no-argument constructor is added.

## 12.8 Constructor overloading and chaining

```java
class UserProfile {
    String name;
    int age;

    UserProfile(String name) {
        this(name, 0);
    }

    UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Constructor chaining avoids duplicated initialization code. The call to `this(...)` selects another constructor in the same class; `super(...)` invokes a superclass constructor.

## 12.9 Anonymous object

An **anonymous object** is an object created without storing its reference in a named variable.

```java
new StringBuilder().append("Java").append(" notes").toString();
```

The object can be used for the immediate expression. If you need to reuse it later, store it in a variable.

---

# 13. Inheritance and core OOP

The four commonly taught OOP pillars are encapsulation, inheritance, polymorphism, and abstraction.

## 13.1 Inheritance

**Inheritance** allows a class to extend another class and reuse or specialize its state and behavior. The class being extended is commonly called the superclass or parent class; the extending class is the subclass or child class.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}

Dog dog = new Dog();
dog.eat();  // inherited behavior
dog.bark();
```

Use inheritance for a genuine “is-a” relationship. If one object merely uses another object's service, composition is often a better design.

## 13.2 Types of class inheritance

- **Single:** one subclass extends one superclass.
- **Multilevel:** a class extends a class that itself extends another class.
- **Hierarchical:** several subclasses extend the same superclass.
- **Multiple inheritance of classes:** Java classes cannot extend two classes directly.

Example of multilevel inheritance:

```java
class Animal { }
class Mammal extends Animal { }
class Dog extends Mammal { }
```

Java allows a class to implement multiple interfaces, which provides multiple type contracts without the ambiguity of extending multiple implementation classes.

## 13.3 `super`

`super` accesses superclass behavior or constructors.

```java
class Vehicle {
    protected String type = "Vehicle";

    Vehicle(String type) {
        this.type = type;
    }

    void start() {
        System.out.println("Vehicle starting");
    }
}

class Car extends Vehicle {
    Car() {
        super("Car"); // invoke superclass constructor
    }

    @Override
    void start() {
        super.start();
        System.out.println("Car ready");
    }
}
```

If a constructor does not explicitly chain to a superclass constructor, Java normally inserts an implicit no-argument `super()` call when applicable. If the superclass has no accessible no-argument constructor, the subclass must call a matching superclass constructor explicitly.

## 13.4 Method overriding

**Overriding** means a subclass supplies an implementation of an inherited instance method with a compatible signature.

```java
class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Woof");
    }
}
```

Use `@Override` because it lets the compiler detect mistakes in the method signature. Static methods are hidden rather than overridden in the runtime-polymorphism sense. Constructors are not overridden.

## 13.5 Overloading versus overriding

| Topic | Overloading | Overriding |
|---|---|---|
| Purpose | Same operation name with different parameter lists | Subclass changes inherited instance-method behavior |
| Usually occurs | In the same class or across inherited methods | Between superclass and subclass |
| Selected by | Compile-time overload resolution | Runtime dispatch for overridden instance methods |
| Parameter list | Must differ | Must match the overridden method's signature |
| Annotation | No special annotation required | `@Override` recommended |

## 13.6 Polymorphism

**Polymorphism** means the same interface or superclass reference can work with different concrete object types.

```java
Animal a = new Dog();
a.sound(); // calls Dog.sound()
```

The reference type controls which members the compiler allows you to access. The actual object type determines which overridden instance method implementation runs.

## 13.7 Dynamic method dispatch

For an overridden instance method, Java chooses the implementation based on the runtime class of the object, not merely the reference variable's declared type.

```java
class Shape {
    void draw() { System.out.println("Shape"); }
}
class Circle extends Shape {
    @Override void draw() { System.out.println("Circle"); }
}
class Square extends Shape {
    @Override void draw() { System.out.println("Square"); }
}

Shape shape = new Circle();
shape.draw(); // Circle
shape = new Square();
shape.draw(); // Square
```

This is the foundation of many extensible designs: code can operate on a `Shape` abstraction while concrete subclasses define their own drawing behavior.

## 13.8 `final`

`final` has different meanings depending on where it appears:

- **Final variable:** can be assigned only once.
- **Final method:** cannot be overridden in a subclass.
- **Final class:** cannot be subclassed.

```java
final class Utility { }

class Parent {
    final void cannotOverride() { }
}
```

A `final` reference cannot be reassigned, but it does not automatically make the referenced object immutable.

---

# 14. Packages and access modifiers

## 14.1 Packages

A **package** groups related types, helps avoid naming conflicts, and provides package-level access boundaries.

```java
package com.example.billing;

public class InvoiceService {
}
```

The package declaration, if present, appears before imports and type declarations. In conventional project layouts, the directory path matches the package name, such as `com/example/billing/InvoiceService.java`.

Use `import` to refer to a type without writing its fully qualified name every time:

```java
import java.util.ArrayList;
import java.util.List;
```

`java.lang` types such as `String`, `System`, and `Math` are available without an explicit import. Import declarations do not copy code; they make type names available to source code.

## 14.2 Access modifiers

| Modifier | Same class | Same package | Subclass in another package | Unrelated class in another package |
|---|---:|---:|---:|---:|
| `private` | Yes | No | No | No |
| No modifier (package-private) | Yes | Yes | No, unless also same package | No |
| `protected` | Yes | Yes | Yes, subject to Java's subclass access rules | No |
| `public` | Yes | Yes | Yes | Yes, if the type and containing context are accessible |

Important notes:

- Top-level classes can ordinarily be `public` or package-private. A top-level class cannot be `private` or `protected`.
- `protected` across packages is more nuanced than “available to any subclass”: access from outside the package is constrained to subclass contexts and the relevant qualifying reference type.
- The absence of a modifier is called package-private access, not a `default` access modifier keyword.

Use the least permissive access level that supports the intended API.

---

# 15. Polymorphism, dynamic dispatch, and final

This section consolidates several related concepts because they are easiest to understand together.

## 15.1 Upcasting

**Upcasting** treats a subclass object as a superclass or interface type. It is normally implicit and safe because every instance of the subclass is also an instance of the superclass.

```java
Dog dog = new Dog();
Animal animal = dog; // upcasting
animal.sound();
```

Through `animal`, you can access only members exposed by the compile-time type `Animal`, though overridden methods dispatch to the concrete object implementation.

## 15.2 Downcasting

**Downcasting** casts a superclass reference to a subclass type. It is only valid when the referenced object really has the target type (or a compatible subtype).

```java
Animal animal = new Dog();
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.bark();
}
```

Casting an `Animal` that refers to a `Cat` into `Dog` throws `ClassCastException`.

Modern Java supports pattern matching for `instanceof` (permanent since Java 16):

```java
if (animal instanceof Dog dog) {
    dog.bark();
}
```

## 15.3 The `Object` class

`java.lang.Object` is the root superclass of Java's ordinary class hierarchy. Important methods include:

- `toString()`: a string representation of the object. Override it for meaningful output.
- `equals(Object other)`: logical equality. Default behavior in `Object` is identity-based.
- `hashCode()`: a hash value used by hash-based collections.
- `getClass()`: obtains runtime class metadata.
- `wait`, `notify`, and `notifyAll`: low-level monitor coordination methods, generally used only when working directly with monitor-based concurrency.

## 15.4 `equals` and `hashCode` contract

If two objects are equal according to `equals`, they must have the same `hashCode`. The reverse is not required: unequal objects can have the same hash code.

When overriding `equals`, usually override `hashCode` too. Otherwise collections such as `HashSet` and `HashMap` may behave unexpectedly.

Example:

```java
import java.util.Objects;

class PersonKey {
    private final String name;
    private final int id;

    PersonKey(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof PersonKey that)) return false;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "PersonKey{name='" + name + "', id=" + id + "}";
    }
}
```

For equality, decide which fields define logical identity and keep that decision consistent. Mutable fields used in `equals`/`hashCode` can be dangerous if the object is stored in a hash-based collection and those fields change afterward.

## 15.5 Wrapper classes

Wrapper classes represent primitive values as objects.

| Primitive | Wrapper |
|---|---|
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

Example:

```java
int primitive = 10;
Integer boxed = primitive; // autoboxing
int again = boxed;         // unboxing
```

Collections store object references, so `List<Integer>` is common while `List<int>` is invalid.

Be aware of `null` during unboxing:

```java
Integer value = null;
// int n = value; // throws NullPointerException at runtime
```

For value comparison, use methods such as `equals` or compare primitive values. Do not rely on `==` between wrapper objects because object identity and caching can affect the result.

---

# 16. Object methods, casting, and wrapper classes

The playlist treats these as separate lessons, but in practice they connect through references and runtime types. Key revision points:

1. A reference variable has a **compile-time type** and refers to an object with a **runtime type**.
2. The compile-time type controls which methods and fields are accessible in source code.
3. Overridden instance methods use runtime dispatch.
4. Upcasting is normally implicit; downcasting requires a valid runtime object type.
5. Use `instanceof` when checking whether a cast is valid.
6. `equals` expresses logical equality; `==` on references expresses identity.
7. Override `hashCode` whenever you override `equals` to define value-based equality.
8. Wrapper types bridge primitives and object-based APIs. Unboxing `null` throws `NullPointerException`.

---

# 17. Abstract classes and interfaces

## 17.1 Abstraction

**Abstraction** exposes the essential operations of a concept while hiding unnecessary implementation details. In Java it is commonly represented using interfaces and abstract classes.

## 17.2 Abstract class

An abstract class is declared with `abstract` and cannot be instantiated directly. It may contain abstract methods (without a method body), concrete methods, fields, and constructors.

```java
abstract class Shape {
    abstract double area();

    void describe() {
        System.out.println("I am a shape");
    }
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
```

A concrete subclass must implement inherited abstract methods unless it is also abstract.

## 17.3 Interface

An **interface** declares a contract that implementing classes promise to satisfy. Interfaces can also contain default methods, static methods, and private helper methods, subject to Java version rules.

```java
interface Payable {
    double amountDue();
}

class Invoice implements Payable {
    private final double amount;

    Invoice(double amount) {
        this.amount = amount;
    }

    @Override
    public double amountDue() {
        return amount;
    }
}
```

When implementing an interface method, the implementation must be `public` because interface contract methods are public.

## 17.4 Abstract class versus interface

| Abstract class | Interface |
|---|---|
| A class can extend only one class | A class can implement multiple interfaces |
| Can hold instance state and constructors | Does not provide ordinary per-instance fields; fields declared in interfaces are implicitly `public static final` constants |
| Useful for related classes sharing state and implementation | Useful for defining capabilities or contracts across different classes |
| Can mix abstract and concrete methods | Can declare abstract methods and, in modern Java, default/static/private methods |

Choose based on the design, not simply because one feels “more object-oriented.” Interfaces are often useful at boundaries between components.

## 17.5 Multiple interfaces

```java
interface Flyable {
    void fly();
}

interface Trackable {
    String location();
}

class Drone implements Flyable, Trackable {
    @Override
    public void fly() {
        System.out.println("Drone flying");
    }

    @Override
    public String location() {
        return "Warehouse";
    }
}
```

A class may extend one class and implement several interfaces:

```java
class SmartDrone extends SomeBaseClass implements Flyable, Trackable {
    // implementations
}
```

## 17.6 Interface categories often taught in beginner courses

- **Normal interface:** may declare multiple abstract methods.
- **Functional interface:** has one abstract method and can normally be implemented with a lambda expression.
- **Marker interface:** conveys a marker or capability through its type identity, often without declaring methods. `Serializable` is a well-known example.

A functional interface may still have methods matching `Object` methods and default/static methods. The important rule is one abstract method for its functional descriptor.

---

# 18. Nested classes, anonymous classes, enums, and annotations

## 18.1 Nested class overview

A class declared inside another class is a nested class. Nested types may group implementation details or represent a close relationship to their containing type.

Common forms:

- **Static nested class:** does not require an enclosing instance.
- **Inner member class:** non-static class declared at member level; an instance is associated with an enclosing object.
- **Local class:** class declared inside a method or block.
- **Anonymous class:** one-off class declaration and instantiation with no explicit class name.

## 18.2 Static nested class

```java
class Outer {
    static class Nested {
        void show() {
            System.out.println("Nested class");
        }
    }
}

Outer.Nested nested = new Outer.Nested();
nested.show();
```

## 18.3 Inner member class

```java
class Outer {
    private String message = "Hello from Outer";

    class Inner {
        void show() {
            System.out.println(message);
        }
    }
}

Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
inner.show();
```

A non-static inner instance is created through an enclosing instance, using `outer.new Inner()` syntax.

## 18.4 Anonymous inner class

An anonymous class is useful for a one-off implementation of an interface or subclass of a class. Before lambdas became common, anonymous classes were frequently used for callbacks.

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running task");
    }
};

new Thread(task).start();
```

For a functional interface, a lambda is often shorter and clearer. Anonymous classes remain useful when you need a separate class body, multiple methods, or distinct `this` semantics.

## 18.5 Enum

An **enum** is a special type that represents a fixed set of named constants.

```java
enum Status {
    NEW,
    IN_PROGRESS,
    COMPLETED
}

Status status = Status.IN_PROGRESS;
if (status == Status.COMPLETED) {
    System.out.println("Done");
}
```

Enums are type-safe compared with arbitrary strings such as `"completed"`. Enum constants are instances of the enum type.

Enums can also hold fields, constructors, and methods:

```java
enum Priority {
    LOW(1), MEDIUM(2), HIGH(3);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    public int level() {
        return level;
    }
}
```

Enum constructors are not called with `new` from ordinary client code. For conditionals, use `switch` or compare enum constants directly.

## 18.6 Annotation

An **annotation** adds metadata to code. An annotation can be processed by the compiler, development tools, build tools, or frameworks.

Common examples:

- `@Override`: tells the compiler that a method is intended to override a superclass method or implement a method in an appropriate context.
- `@Deprecated`: marks an API as discouraged for use.
- `@SuppressWarnings`: suppresses selected compiler warnings.
- `@FunctionalInterface`: documents and checks the functional-interface intent.

Example:

```java
@Override
public String toString() {
    return "Example";
}
```

Annotations do not automatically perform arbitrary behavior by themselves. The compiler or a runtime/tooling mechanism must interpret them.

---

# 19. Functional interfaces and lambda expressions

## 19.1 Functional interface definition

A **functional interface** is an interface with one abstract method, suitable for representing a function-like behavior. The annotation `@FunctionalInterface` is optional but helpful because it asks the compiler to verify that the interface meets the rule.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

## 19.2 Lambda expression

A **lambda expression** is a concise way to provide an implementation of a functional interface.

```java
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(3, 4));      // 7
System.out.println(multiply.calculate(3, 4)); // 12
```

General form:

```java
(parameters) -> expression
(parameters) -> { statements; }
```

The compiler infers parameter types from the target functional interface in many cases.

## 19.3 Lambda with a block and return

```java
Calculator largerSum = (a, b) -> {
    int result = a + b;
    return result;
};
```

A single-expression lambda can omit braces and an explicit `return`. A block lambda that returns a value generally uses `return` and must return along every normal path.

## 19.4 Common functional interfaces

The `java.util.function` package provides many ready-made functional interfaces:

| Interface | Core method concept | Example use |
|---|---|---|
| `Predicate<T>` | `boolean test(T value)` | Check a condition, such as `age >= 18` |
| `Function<T, R>` | `R apply(T value)` | Transform a value |
| `Consumer<T>` | `void accept(T value)` | Perform an action with a value |
| `Supplier<T>` | `T get()` | Provide a value without an input |
| `UnaryOperator<T>` | `T apply(T value)` | Transform a value into the same type |
| `BinaryOperator<T>` | `T apply(T left, T right)` | Combine two values of the same type |

These interfaces are heavily used by the Stream API.

## 19.5 Effectively final variables

A lambda can capture a local variable only when it is final or effectively final, meaning it is assigned once and not reassigned afterward.

```java
int base = 10;
java.util.function.IntUnaryOperator addBase = value -> value + base;
System.out.println(addBase.applyAsInt(5)); // 15
```

The lambda captures the value of `base`; changing `base` afterward would violate the effectively-final rule.

## 19.6 Method references

A **method reference** is a compact form of a lambda when an existing method already matches the required functional signature.

```java
java.util.List<String> names = java.util.List.of("Asha", "Ravi");
names.forEach(System.out::println);
```

`System.out::println` is equivalent in purpose to a lambda such as `name -> System.out.println(name)`.

---

# 20. Exception handling

## 20.1 What is an exception?

An **exception** is an event represented by an object that interrupts the normal flow of program execution. Exceptions can be detected and handled by code, or allowed to propagate to a caller.

Common examples:

- `ArithmeticException`: some invalid arithmetic operations, such as integer division by zero.
- `NullPointerException`: attempting an operation that requires a non-null reference when the reference is `null`.
- `ArrayIndexOutOfBoundsException`: invalid array index.
- `NumberFormatException`: invalid text passed to a numeric parsing method.
- `IOException`: an I/O operation failed.

## 20.2 Exception hierarchy

Simplified hierarchy:

```text
Object
  └── Throwable
       ├── Error
       └── Exception
            └── RuntimeException
```

- **`Error`:** serious conditions generally not intended for ordinary application recovery, such as many VM-level failures.
- **Checked exception:** a subclass of `Exception` that is not a subclass of `RuntimeException`; code must catch it or declare it in its method's `throws` clause.
- **Unchecked exception:** `RuntimeException` and its subclasses, plus `Error` and its subclasses; the compiler does not require them to be caught or declared.

Do not catch every `Throwable` or `Error` indiscriminately. Catch failures that your code can meaningfully handle.

## 20.3 `try-catch`

```java
try {
    int result = 10 / 0;
    System.out.println(result);
} catch (ArithmeticException ex) {
    System.out.println("Cannot divide by zero");
}
```

The code in `try` is attempted. If a matching exception is thrown, control moves to the matching `catch` block.

## 20.4 Multiple catch blocks

```java
try {
    int value = Integer.parseInt("abc");
    System.out.println(10 / value);
} catch (NumberFormatException ex) {
    System.out.println("Input was not a valid integer");
} catch (ArithmeticException ex) {
    System.out.println("Cannot divide by zero");
}
```

Put more specific exception types before broader types. A broader catch first can make a later narrower catch unreachable.

Multi-catch lets one handler catch unrelated alternatives:

```java
try {
    java.nio.file.Files.readString(java.nio.file.Path.of("data.txt"));
} catch (java.io.IOException | IllegalArgumentException ex) {
    System.out.println(ex.getMessage());
}
```

## 20.5 `finally`

A `finally` block normally runs after `try` and `catch` processing, whether or not an exception occurs. It is commonly used for cleanup in older code.

```java
try {
    System.out.println("Work");
} catch (RuntimeException ex) {
    System.out.println("Handled");
} finally {
    System.out.println("Cleanup path");
}
```

There are unusual cases where a `finally` block may not run, such as abrupt JVM termination. Avoid `return` from `finally`, because it can hide exceptions or override earlier return values.

## 20.6 `throw` versus `throws`

- `throw` explicitly throws an exception object.
- `throws` declares that a method may propagate one or more checked exceptions to its caller.

```java
static void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be at least 18");
    }
}
```

Checked exception declaration example:

```java
static String readFirstLine(java.nio.file.Path path)
        throws java.io.IOException {
    try (var reader = java.nio.file.Files.newBufferedReader(path)) {
        return reader.readLine();
    }
}
```

The caller must catch `IOException` or declare it in its own `throws` clause.

## 20.7 Custom exceptions

Create a custom exception when a domain-specific failure deserves a clear type.

```java
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

class Wallet {
    private int balance;

    Wallet(int balance) {
        this.balance = balance;
    }

    void withdraw(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough balance");
        }
        balance -= amount;
    }
}
```

Extend `Exception` for a checked domain exception or `RuntimeException` for an unchecked one. Choose intentionally based on how callers should be required to respond.

## 20.8 Exception propagation, or “ducking” an exception

A method can avoid catching a checked exception locally by declaring it with `throws` and letting the caller decide how to handle it. This is sometimes informally called passing or “ducking” the exception.

Declare checked exceptions when they form a meaningful part of the method contract. Do not add `throws Exception` everywhere merely to silence compile errors.

## 20.9 Try-with-resources

Try-with-resources automatically closes resources that implement `AutoCloseable`, including many file, stream, and database APIs.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ReadFileExample {
    static void printFile(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
```

The resource is closed automatically when control exits the `try` block, including exceptional exits. This is generally safer than manual cleanup in `finally`.

## 20.10 Exception-handling best practices

- Catch specific exception types that you can handle.
- Preserve useful context when rethrowing or wrapping an exception.
- Avoid silently swallowing exceptions.
- Do not use exceptions for ordinary expected branching when a normal conditional is clearer.
- Do not expose secrets or sensitive data in exception messages and logs.
- Use try-with-resources for closeable resources.

---

# 21. Reading input

## 21.1 `Scanner`

`Scanner` is beginner-friendly and can parse common types from keyboard input.

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            System.out.println("Age: " + age);
        }
    }
}
```

`nextInt()` reads the next integer token. Mixing token methods such as `nextInt()` with line-oriented methods such as `nextLine()` can surprise beginners because the newline after the integer may remain pending.

Example of consuming the rest of the line:

```java
int age = scanner.nextInt();
scanner.nextLine(); // consume remaining newline
String fullName = scanner.nextLine();
```

## 21.2 `BufferedReader`

`BufferedReader` reads text efficiently, usually line by line. Parsing numeric values is done separately.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        System.out.print("Enter a number: ");
        int number = Integer.parseInt(reader.readLine());
        System.out.println(number * 2);
    }
}
```

`readLine()` returns a `String` or `null` at end-of-input. `Integer.parseInt(...)` can throw `NumberFormatException` for invalid input.

## 21.3 `Scanner` versus `BufferedReader`

| `Scanner` | `BufferedReader` |
|---|---|
| Convenient token parsing (`nextInt`, `nextDouble`, etc.) | Primarily reads characters and lines |
| Often easier for beginner console programs | Often preferred for efficient line input |
| Parsing convenience can come with overhead | Numeric conversion must be performed explicitly |

For application code, validate input at the boundary and communicate invalid input clearly.

---

# 22. Multithreading

## 22.1 Process and thread

A **process** is a running program with its own managed resources and address space. A **thread** is an execution path within a process. Multiple threads can make progress concurrently and may run in parallel on different CPU cores.

Use concurrency when tasks can usefully overlap, such as waiting for I/O or performing independent work. Concurrency introduces complexity around shared state, coordination, resource use, and failures.

## 22.2 Creating a thread by extending `Thread`

```java
class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("Worker is running");
    }
}

public class Main {
    public static void main(String[] args) {
        WorkerThread worker = new WorkerThread();
        worker.start();
    }
}
```

Call `start()` to request a new thread of execution. Calling `run()` directly is an ordinary method call and does not start a new thread.

## 22.3 `Runnable`

`Runnable` separates the task from the thread that runs it.

```java
Runnable task = () -> System.out.println("Task running");
Thread worker = new Thread(task);
worker.start();
```

In most designs, implementing or supplying a `Runnable` (or using an executor) is more flexible than subclassing `Thread`, because the task class can still extend another class.

## 22.4 Multiple threads

```java
Runnable task = () -> {
    for (int i = 1; i <= 3; i++) {
        System.out.println(Thread.currentThread().getName() + ": " + i);
    }
};

Thread first = new Thread(task, "First");
Thread second = new Thread(task, "Second");
first.start();
second.start();
```

The output order can vary. Do not assume that one thread will finish all its lines before another begins unless you explicitly coordinate them.

## 22.5 `sleep`, `join`, and interruption

- `Thread.sleep(...)` pauses the **current** thread for approximately a specified duration, subject to scheduler and operating-system behavior.
- `join()` lets one thread wait for another thread to terminate.
- `interrupt()` requests interruption. Code should handle or propagate `InterruptedException` appropriately.

```java
Thread worker = new Thread(() -> {
    try {
        Thread.sleep(500);
        System.out.println("Worker complete");
    } catch (InterruptedException ex) {
        Thread.currentThread().interrupt(); // restore interrupt status
        return;
    }
});

worker.start();
try {
    worker.join();
} catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
}
System.out.println("Main continues after join");
```

Do not use `sleep` as a substitute for correct synchronization. Time delays do not guarantee that a particular thread has completed a task.

## 22.6 Thread priority

Java thread priorities express a scheduling hint in a range defined by `Thread.MIN_PRIORITY` through `Thread.MAX_PRIORITY`, with `Thread.NORM_PRIORITY` as the normal priority. They do **not** guarantee execution order or provide reliable coordination. Correctness must not depend on priority.

## 22.7 Race condition

A **race condition** occurs when the result depends on timing or interleaving of concurrent operations.

```java
class Counter {
    int count;

    void increment() {
        count++; // not an atomic operation
    }
}
```

If multiple threads call `increment()` concurrently, some updates can be lost. `count++` is a read-modify-write sequence, not one indivisible operation.

## 22.8 `synchronized`

A synchronized instance method acquires that object's monitor before executing. A synchronized static method acquires the monitor associated with the `Class` object.

```java
class SafeCounter {
    private int count;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

Synchronization can protect shared mutable state and establish visibility guarantees, but excessive or poorly designed locking can cause contention or deadlocks. All code accessing a shared invariant must coordinate consistently.

For simple counters, `AtomicInteger` may be a better fit. For larger concurrent designs, consider high-level tools such as `ExecutorService`, concurrent collections, locks, and other `java.util.concurrent` types.

## 22.9 Thread states

The Java `Thread.State` enum contains:

- `NEW`: created but not started.
- `RUNNABLE`: executing or ready to execute; Java groups several OS scheduling situations under this state.
- `BLOCKED`: waiting to acquire a monitor lock.
- `WAITING`: waiting indefinitely for another action.
- `TIMED_WAITING`: waiting for a bounded time, such as during `sleep` or timed `join`.
- `TERMINATED`: execution has finished.

These states describe a thread's JVM-level state. A thread's state can change quickly, so snapshots are not a reliable mechanism for program synchronization.

## 22.10 Modern concurrency advice

For production systems, avoid creating unbounded numbers of raw threads. Thread pools and executors manage work more safely. For shared state, prefer immutable data, confinement, message passing, concurrent utilities, or carefully scoped synchronization.

---

# 23. Collections and generics

## 23.1 Why collections?

An array has a fixed length and limited operations. The Java Collections Framework provides interfaces and classes to store, find, sort, and process groups of objects.

High-level distinction:

- `Collection<E>` represents a group of elements.
- `List<E>`, `Set<E>`, and `Queue<E>` are major collection interfaces.
- `Map<K, V>` stores key-value associations and is part of the framework but does **not** extend `Collection`.

## 23.2 Common interfaces and implementations

| Interface | Common implementations | Main property |
|---|---|---|
| `List<E>` | `ArrayList`, `LinkedList` | Ordered sequence; duplicates allowed |
| `Set<E>` | `HashSet`, `LinkedHashSet`, `TreeSet` | No duplicate elements according to its equality/ordering semantics |
| `Queue<E>` | `ArrayDeque`, `PriorityQueue`, some concurrent queues | Elements are often processed in a queue-specific order |
| `Map<K,V>` | `HashMap`, `LinkedHashMap`, `TreeMap`, `ConcurrentHashMap` | Unique keys mapped to values |

Choose the implementation based on ordering requirements, performance characteristics, concurrency needs, and whether nulls are allowed.

## 23.3 Generics

**Generics** allow types to be parameterized. They improve compile-time type safety and reduce casting.

```java
java.util.List<String> names = new java.util.ArrayList<>();
names.add("Asha");
names.add("Ravi");

String first = names.get(0); // no cast required
```

`String` is the type argument. `List<int>` is invalid because generic type arguments must be reference types, so use `List<Integer>` when working with integers.

The diamond operator `<>` lets the compiler infer the type argument from context in many cases.

## 23.4 `ArrayList`

`ArrayList` is a resizable-array implementation of `List`. It preserves insertion order and permits duplicates.

```java
import java.util.ArrayList;
import java.util.List;

List<String> languages = new ArrayList<>();
languages.add("Java");
languages.add("Python");
languages.add("Java");

System.out.println(languages.get(0)); // Java
System.out.println(languages.size()); // 3
languages.set(1, "TypeScript");
languages.remove("Java"); // removes the first matching item
```

Useful methods include `add`, `get`, `set`, `remove`, `contains`, `size`, `isEmpty`, and `clear`.

Complexity intuition:

- Access by index: typically `O(1)`.
- Append at the end: amortized `O(1)`.
- Insert/remove near the beginning or middle: typically `O(n)` due to shifting.
- Searching by value: typically `O(n)`.

Avoid removing elements from a list using an increasing index without adjusting for shifting. Iterator-based removal or collecting a filtered result is often safer.

## 23.5 Enhanced `for` loop and `forEach`

```java
for (String language : languages) {
    System.out.println(language);
}

languages.forEach(System.out::println);
```

Use an enhanced loop when the code should visit each element. `forEach` accepts a `Consumer` and is convenient for simple actions. For more complex transformations, Stream operations may be clearer.

## 23.6 `Set`

A `Set` stores no duplicates according to the set's equality or ordering rules.

```java
import java.util.HashSet;
import java.util.Set;

Set<String> skills = new HashSet<>();
skills.add("Java");
skills.add("React");
skills.add("Java");
System.out.println(skills.size()); // 2
```

Common choices:

- `HashSet`: no guaranteed iteration order; typical average-case fast membership operations.
- `LinkedHashSet`: maintains insertion order.
- `TreeSet`: maintains sorted order and is based on ordering.

For objects in `HashSet`, implement `equals` and `hashCode` consistently. For sorted sets, ensure the comparator or natural ordering is consistent with the intended duplicate semantics.

## 23.7 `Map`

A `Map<K,V>` associates each key with a value. Keys are unique; a new value for an existing key replaces the previous value.

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> scores = new HashMap<>();
scores.put("Asha", 90);
scores.put("Ravi", 85);
scores.put("Asha", 95); // replaces Asha's value

System.out.println(scores.get("Asha")); // 95
System.out.println(scores.containsKey("Ravi")); // true
```

Common methods include `put`, `get`, `getOrDefault`, `containsKey`, `containsValue`, `remove`, `keySet`, `values`, and `entrySet`.

Iterate entries when both key and value are needed:

```java
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

Common implementations:

- `HashMap`: no guaranteed order, typical average-case efficient operations.
- `LinkedHashMap`: predictable iteration order, generally insertion order by default.
- `TreeMap`: orders keys by natural order or a comparator.
- `ConcurrentHashMap`: designed for concurrent access with its own constraints.

## 23.8 `Comparable` versus `Comparator`

Both define ordering, but they serve different design purposes.

- `Comparable<T>` is implemented by the type itself and defines its **natural ordering** through `compareTo`.
- `Comparator<T>` is a separate object or lambda that defines a chosen ordering through `compare`.

```java
import java.util.Comparator;
import java.util.List;

record Employee(String name, int salary) { }

List<Employee> employees = List.of(
    new Employee("Asha", 50000),
    new Employee("Ravi", 65000),
    new Employee("Mina", 52000)
);

employees.stream()
    .sorted(Comparator.comparingInt(Employee::salary))
    .forEach(System.out::println);
```

More comparator examples:

```java
Comparator<Employee> bySalary = Comparator.comparingInt(Employee::salary);
Comparator<Employee> byName = Comparator.comparing(Employee::name);
Comparator<Employee> bySalaryDescending = bySalary.reversed();
Comparator<Employee> bySalaryThenName = bySalary.thenComparing(byName);
```

The comparator API reduces fragile handwritten comparison logic.

## 23.9 Useful collection concepts

- Prefer interface types in variable declarations, for example `List<String> list = new ArrayList<>();`.
- Use `List.of(...)`, `Set.of(...)`, and `Map.of(...)` for convenient unmodifiable collections on modern Java. These factories do not allow null elements or keys/values, and mutation methods throw `UnsupportedOperationException`.
- Most standard collections such as `ArrayList` and `HashMap` are not thread-safe for unsynchronized concurrent mutation.
- Iterating and structurally modifying a collection outside the iterator's supported operations can produce `ConcurrentModificationException`; this is a bug-detection mechanism, not a synchronization strategy.
- Know the behavioral contract you need before choosing a collection implementation.

---

# 24. Stream API

## 24.1 What is a stream?

The **Stream API** provides a declarative way to process a sequence of elements through operations such as filtering, transforming, sorting, and aggregating. A stream is not itself a data structure; it describes a pipeline over a source.

Example: print names that start with `A`.

```java
import java.util.List;

List<String> names = List.of("Asha", "Ravi", "Anil", "Mina");
names.stream()
    .filter(name -> name.startsWith("A"))
    .forEach(System.out::println);
```

## 24.2 Stream pipeline

A pipeline commonly has three parts:

1. **Source:** a collection, array, generated sequence, or other source.
2. **Intermediate operations:** transform the stream and return another stream, such as `filter`, `map`, `sorted`, or `distinct`.
3. **Terminal operation:** triggers processing and produces a result or side effect, such as `forEach`, `count`, `collect`, `reduce`, `min`, or `max`.

Many intermediate operations are lazy. Nothing may be processed until a terminal operation consumes the pipeline.

## 24.3 `filter`

`filter` keeps elements that pass a predicate.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .toList(); // unmodifiable result on modern Java
```

`Stream.toList()` is available since Java 16 and returns an unmodifiable list. If you need a mutable list, use a collector such as `collect(Collectors.toCollection(ArrayList::new))`.

## 24.4 `map`

`map` transforms each input value into a result.

```java
List<String> names = List.of("asha", "ravi");
List<String> upper = names.stream()
    .map(String::toUpperCase)
    .toList();
```

The number of elements usually stays the same, although each element's value or type can change.

## 24.5 `sorted` and `distinct`

```java
List<Integer> values = List.of(4, 2, 4, 1, 3, 2);
List<Integer> result = values.stream()
    .distinct()
    .sorted()
    .toList();
System.out.println(result); // [1, 2, 3, 4]
```

Without a comparator, `sorted()` uses natural ordering where supported. To sort in a custom order, provide a `Comparator`.

## 24.6 `reduce`

`reduce` combines stream elements into one result.

```java
int total = List.of(1, 2, 3, 4).stream()
    .reduce(0, Integer::sum);
System.out.println(total); // 10
```

The `0` is the identity for addition. Other reductions need an identity and accumulator suitable for the operation. Use specialized terminal operations like `sum()` on primitive streams where applicable.

## 24.7 `forEach`

```java
List<String> names = List.of("Asha", "Ravi");
names.stream().forEach(System.out::println);
```

`forEach` is a terminal operation. Prefer transformations that return values instead of using side effects to build another collection inside a `forEach` callback.

## 24.8 Combining operations

```java
List<Integer> numbers = List.of(10, 15, 20, 25, 30);
int totalOfLargeValues = numbers.stream()
    .filter(n -> n >= 20)
    .map(n -> n * 2)
    .reduce(0, Integer::sum);

System.out.println(totalOfLargeValues); // 150
```

Read it in order: keep values at least 20, double them, then sum them.

## 24.9 `collect` and grouping

`collect` accumulates stream elements into a result container or summary.

```java
import java.util.List;
import java.util.stream.Collectors;

List<String> names = List.of("Asha", "Anil", "Ravi");
String joined = names.stream().collect(Collectors.joining(", "));
System.out.println(joined); // Asha, Anil, Ravi
```

Grouping example:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));
```

`collect` and collector utilities are important extensions of the introductory topics, and are used frequently in real applications.

## 24.10 Primitive streams

For numeric pipelines, Java has `IntStream`, `LongStream`, and `DoubleStream`. They provide operations such as `sum`, `average`, `min`, and `max` without requiring every value to be boxed into a wrapper object.

```java
int sum = java.util.stream.IntStream.rangeClosed(1, 5).sum();
System.out.println(sum); // 15
```

## 24.11 Stream pitfalls

- A stream should generally be consumed once. Do not reuse a stream after a terminal operation.
- Avoid mutating shared external state from stream callbacks, especially with parallel streams.
- `parallelStream()` does not automatically make code faster; tasks, data size, splitting costs, ordering, and thread contention all matter.
- Use plain loops when they make logic clearer, when you need complex control flow, or when early break/continue semantics are important.
- Do not use `map` for a callback whose purpose is only a side effect. Use `map` for transformation and a terminal operation for consumption.

---

# 25. Modern Java features: `var`, records, sealed classes

## 25.1 Local-variable type inference with `var` (Java 10+)

`var` lets the compiler infer a local variable's static type from the initializer.

```java
var name = "Java";          // String
var count = 10;              // int
var employees = new java.util.ArrayList<String>();
```

It is not allowed for uninitialized local variables, normal fields, method parameters, or method return types. It does not make a variable dynamically typed.

Use `var` when the right-hand side makes the type obvious. Use the explicit type when inference would hide meaningful information.

## 25.2 Record classes (Java 16+)

A **record** is a concise class form intended primarily to model data. The compiler generates component fields, accessors, a canonical constructor, `equals`, `hashCode`, and `toString` according to record rules.

```java
record Point(int x, int y) { }

Point point = new Point(3, 4);
System.out.println(point.x());
System.out.println(point); // Point[x=3, y=4]
```

Notice that record accessors use component names such as `x()`, not conventional `getX()` methods. Records are implicitly final, so they cannot be extended by ordinary classes. Their component fields are final, but a record is only shallowly immutable: a component could itself refer to a mutable object.

Example with validation:

```java
record User(String username, int age) {
    User {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username is required");
        }
        if (age < 0) {
            throw new IllegalArgumentException("age cannot be negative");
        }
    }
}
```

The compact constructor allows validation while the compiler still assigns component fields from the constructor parameters.

## 25.3 Sealed classes (Java 17+)

A **sealed class or interface** restricts which types may directly extend or implement it. This is useful when a model should permit a known set of alternatives.

```java
sealed interface PaymentMethod permits CardPayment, CashPayment { }

final class CardPayment implements PaymentMethod { }
final class CashPayment implements PaymentMethod { }
```

A permitted direct subtype must be declared `final`, `sealed`, or `non-sealed` (depending on the intended extension policy). `final` stops further subclassing; `sealed` restricts the next level; `non-sealed` opens that subtype to further extension.

Sealed types are useful for controlled domain models, interpreters, state machines, and APIs where the full set of implementations should be known.

## 25.4 Switch expressions and modern Java

The playlist includes a separate update on switch. Modern switch expressions are commonly available from Java 14 and can return a value, as shown in Section 6.6. Be careful not to mix syntax from one Java version with a project configured for an older release.

## 25.5 Choosing a Java version

Newer JDKs include language and runtime improvements, but a real project must use a version supported by its deployment environment, build tools, and dependencies. For learning, Java 17+ supports the major language concepts covered here; Java 21 or a newer supported LTS release can be appropriate when your course and development setup are aligned to it.

---

# 26. JUnit 5 introduction

The playlist's final indexed entry is a JUnit 5 tutorial. JUnit is a testing framework for Java code. Unit tests verify behavior of small units, commonly individual methods or classes, so refactoring and bug fixes can be checked automatically.

## 26.1 Basic JUnit 5 concepts

- `@Test`: marks a test method.
- `Assertions.assertEquals(expected, actual)`: checks that values match.
- `Assertions.assertTrue(condition)`: checks that a condition is true.
- `Assertions.assertThrows(...)`: checks that an operation throws an expected exception.
- Setup and teardown methods can use annotations such as `@BeforeEach` and `@AfterEach`.

Example (assumes JUnit Jupiter is already configured in your project):

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    @Test
    void addsTwoNumbers() {
        assertEquals(5, 2 + 3);
    }
}
```

For a practical project, configure JUnit Jupiter through Maven or Gradle rather than copying test source into an ordinary application class. Test a meaningful unit of your own code, not only arithmetic written directly inside the test.

## 26.2 Arrange, Act, Assert

A common unit-testing structure is:

1. **Arrange:** prepare the object and inputs.
2. **Act:** call the method under test.
3. **Assert:** compare the actual result with the expected result.

A useful test name says what behavior is being tested and under what condition, such as `withdraw_rejectsAmountGreaterThanBalance`.

---

# 27. Common mistakes and quick revision

## 27.1 Quick comparison table

| Confusing pair | Correct distinction |
|---|---|
| JDK vs JVM | JDK provides development tools; JVM executes bytecode. |
| Primitive vs reference | A primitive stores a primitive value; a reference variable refers to an object or can be `null`. |
| `==` vs `equals` | `==` checks primitive equality or reference identity; `equals` can define logical object equality. |
| `print` vs `println` | `println` adds a newline. |
| `while` vs `do-while` | `while` may run zero times; `do-while` runs at least once. |
| `break` vs `continue` | `break` exits the nearest loop/switch; `continue` skips to the next loop iteration. |
| Overloading vs overriding | Different parameter lists vs redefining an inherited instance method. |
| Static vs instance | Static belongs to the class; instance members are associated with an object. |
| Constructor vs method | Constructor initializes objects and has no return type; a method has a return type or `void`. |
| Abstract class vs interface | Abstract class can hold instance state and constructors; interface primarily defines a contract and can be implemented alongside other interfaces. |
| `throw` vs `throws` | `throw` raises an exception; `throws` declares possible propagation. |
| `ArrayList` vs array | `ArrayList` resizes; an array has fixed length. |
| `Comparable` vs `Comparator` | Natural ordering in the type vs a separate ordering strategy. |
| Collection vs Map | `Map` stores key-value associations and does not extend `Collection`. |
| `StringBuilder` vs `StringBuffer` | Both are mutable; `StringBuffer` synchronizes its methods, while `StringBuilder` does not. |
| `start()` vs `run()` | `start()` schedules thread execution; direct `run()` is a normal method call. |
| Intermediate vs terminal stream op | Intermediate operations build a pipeline; a terminal operation consumes it. |

## 27.2 Common coding errors

1. **Forgetting to initialize local variables:** local variables must be definitely assigned before use.
2. **Using `==` for string content:** use `equals` or another explicit comparison.
3. **Array off-by-one errors:** valid indexes are `0` to `length - 1`.
4. **Integer division surprise:** `5 / 2` is `2`; use a floating-point operand to get `2.5`.
5. **Constructor confusion:** the compiler creates a default no-argument constructor only if you declared no constructor at all.
6. **Forgetting `break` in colon-style switch:** this can cause fall-through.
7. **Calling `run()` instead of `start()`:** this does not start a new thread.
8. **Sharing mutable state across threads without coordination:** can produce race conditions.
9. **Overriding `equals` but not `hashCode`:** can break hash-based collection behavior.
10. **Reusing a consumed stream:** create a new stream for a new pipeline.
11. **Assuming `List.of(...)` returns a mutable list:** the result is unmodifiable.
12. **Swallowing exceptions:** empty `catch` blocks hide failures and make production issues harder to diagnose.

## 27.3 Mini practice set

Try solving these before checking references or searching for solutions.

1. Write a program that prints whether an integer is even or odd.
2. Given an array of integers, print the sum, average, minimum, and maximum. Handle an empty array explicitly.
3. Create a `BankAccount` class with a private balance, validated deposit, and validated withdrawal.
4. Demonstrate constructor overloading and constructor chaining using `this(...)`.
5. Create a parent `Shape` class and subclasses `Circle` and `Rectangle` that override `area()`.
6. Demonstrate why `==` and `equals` can give different answers for two `String` objects.
7. Store five names in an `ArrayList`, remove duplicates with a `Set`, and explain whether order is preserved.
8. Use a `Map<String, Integer>` to count the occurrences of words in a sentence.
9. Filter a list of numbers to keep even values, double them, sort them, and calculate the total using streams.
10. Start two threads and demonstrate a race condition with an unsynchronized counter, then fix it using synchronization or an atomic type.
11. Create a custom checked exception and explain why callers must catch or declare it.
12. Represent a small immutable data object with a `record` and validate its constructor input.

## 27.4 A sensible way to study this playlist

For each topic:

1. Watch the matching lesson with the Java IDE open.
2. Type the example yourself instead of only reading it.
3. Predict the output before running it.
4. Change one detail at a time and explain why the output changes.
5. Write a small independent program using the concept.
6. Revisit mistakes and put the cause in your own words.

Do not try to memorize every API method on the first pass. Focus first on how types, control flow, objects, references, and runtime behavior fit together. Repetition through small programs is more valuable than copying large code blocks.

---

# 28. Playlist lesson map

The public index associated with this playlist lists the following lesson groups. The mapping below lets you use these notes alongside the video sequence. Some concepts are combined in the notes to reduce repetition.

| Playlist lessons | Indexed topics | Main sections in these notes |
|---|---|---|
| 1-8 | Introduction, JDK setup, first code, how Java works, variables, data types, literals, course update | Sections 1-4 |
| 9-12 | Type conversion, assignment, relational and logical operators, plus arithmetic operators in the course sequence | Sections 4-5 |
| 13-17 | `if`, `else if`, ternary operator, switch, modern switch | Section 6 |
| 18-22 | Need for loops, `while`, `do-while`, `for`, choosing a loop | Section 7 |
| 23-28 | Class and object theory/practical, JDK/JRE/JVM, methods, overloading, stack and heap | Sections 1, 8, 9 |
| 29-35 | Need for arrays, array creation, multidimensional/jagged/3D arrays, drawbacks, array of objects, enhanced for | Section 10 |
| 36-38 | String, mutable versus immutable, `StringBuffer` and `StringBuilder` | Section 11 |
| 39-49 | Static variable/method/block, encapsulation, getters/setters, `this`, constructors, constructor types, `this`/`super`, naming convention, anonymous object | Section 12 |
| 50-54 | Need for inheritance, inheritance types, multiple inheritance, method overriding | Sections 13 and 17 |
| 55-56 | Packages and access modifiers | Section 14 |
| 57-62 | Polymorphism, dynamic method dispatch, `final`, Object methods, upcasting/downcasting, wrapper classes | Sections 15-16 |
| 63-69 | Abstract keyword, inner/anonymous classes, need for interfaces, interface basics and deeper topics | Sections 17-18 |
| 70-73 | Enum, enum with `if`/`switch`, enum class, annotations | Section 18 |
| 74-77 | Functional interfaces, lambda expressions, lambda return values, interface types | Section 19 |
| 78-86 | Exceptions, try/catch, multiple catch, hierarchy, `throw`, custom exceptions, `throws`, input, try-with-resources | Sections 20-21 |
| 87-92 | Threads, multiple threads, priority/sleep, Runnable vs Thread, race condition, thread states | Section 22 |
| 93-97 | Collection API, `ArrayList`, `Set`, `Map`, `Comparator` versus `Comparable` | Section 23 |
| 98-101 | Need for streams, `forEach`, Stream API, `map`/`filter`/`reduce`/`sorted` | Section 24 |
| 102-105 | Java update, `var`, sealed classes, record classes | Section 25 |
| 106 | JUnit 5 tutorial | Section 26 |

### Topic note

The playlist is a strong Core Java introduction, but it should not be mistaken for an exhaustive Java ecosystem course. The indexed topics do not fully teach everything needed for a production backend role, such as build tools, automated testing in depth, I/O and networking, JDBC, SQL, dependency injection, web APIs, security, observability, deployment, or a framework such as Spring Boot. Treat those as next-stage topics after you can write and explain small Core Java programs confidently.

---

# 29. Further learning and references

Use these for verification and deeper study after watching the relevant playlist sections:

1. **Playlist:** [Telusko - Java Tutorial for Beginners](https://www.youtube.com/playlist?list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5)
2. **Public lecture index:** [Java Tutorial for Beginners lesson list](https://www.iemdaily.com/2026/02/java-tutorial-for-beginners-english.html)
3. **Java SE specifications:** [Oracle Java SE specifications](https://docs.oracle.com/javase/specs/)
4. **Java language updates:** [Oracle Java language changes by release](https://docs.oracle.com/en/java/javase/25/language/java-language-changes-release.html)
5. **Record classes:** [Oracle record class guide](https://docs.oracle.com/en/java/javase/25/language/records.html)

When code behaves differently from what you expected, check the Java version used by your compiler and build configuration, reproduce the issue in a minimal program, and consult the official API documentation for the relevant type.

---

## Final revision checklist

Before moving beyond Core Java, you should be able to explain and demonstrate each of these without copying a solution:

- [ ] What JDK, JVM, bytecode, and JIT mean.
- [ ] How variables, primitives, references, literals, and casts work.
- [ ] Operator precedence, integer division, short-circuit logic, and increment behavior.
- [ ] How to choose between conditionals and loop types.
- [ ] How methods, parameters, return values, overloading, and recursion work.
- [ ] What a class, object, constructor, reference, and instance field are.
- [ ] How arrays, strings, and mutable string builders differ.
- [ ] How static members, encapsulation, `this`, and constructors work.
- [ ] How inheritance, overriding, polymorphism, abstract classes, and interfaces fit together.
- [ ] The relationship between `equals` and `hashCode`, and when casting is safe.
- [ ] How exceptions propagate and when to use try-with-resources.
- [ ] How to read and validate console input.
- [ ] How `start`, `run`, `join`, synchronization, and race conditions differ.
- [ ] When to use a list, set, map, comparator, and generic type.
- [ ] How a stream pipeline works and when not to use streams.
- [ ] What `var`, records, sealed types, and basic JUnit tests provide.

**Core principle:** for every concept, be able to give a definition, write a small example, predict its output, explain why it behaves that way, and name one common mistake. That is a better measure of understanding than simply finishing the playlist.
