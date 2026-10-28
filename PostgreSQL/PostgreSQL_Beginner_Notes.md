# PostgreSQL Notes for Beginners
## Based on: freeCodeCamp, “Learn PostgreSQL Tutorial – Full Course for Beginners”
Video: https://youtu.be/qw--VYLpxG4

> These are structured study notes, not a verbatim transcript. The installation walkthrough is intentionally excluded. Examples use PostgreSQL and the `psql` terminal. Run the SQL yourself and change the values to practise.

---

## 1. Database, SQL, and PostgreSQL

### What is a database?
A database stores data so an application can create, retrieve, update, and delete it.

Examples:
- An e-commerce app stores customers, products, and orders.
- A banking app stores accounts and transactions.
- A social app stores users, posts, and comments.

### SQL vs PostgreSQL
- **SQL (Structured Query Language):** the language used to query and manage relational data.
- **PostgreSQL (Postgres):** a relational database management system that understands SQL and provides features for storing and managing data.

### Relational database terminology
- **Table:** data about one kind of thing, such as `person`.
- **Column:** an attribute, such as `first_name`.
- **Row:** one record.
- **Schema:** the structure that defines tables, columns, relationships, and constraints.
- **Relationship:** a connection between records in different tables.

Example:

| id | first_name | country |
|---:|---|---|
| 1 | Asha | India |
| 2 | Ravi | Canada |

---

## 2. Using `psql`: useful commands

These are `psql` meta-commands, not ordinary SQL statements. They start with a backslash and generally do not need a semicolon.

| Command | Purpose |
|---|---|
| `\l` | List databases |
| `\c database_name` | Connect to a database |
| `\dt` | List tables |
| `\d` | List relations such as tables and sequences |
| `\d table_name` | Describe a table's columns and constraints |
| `\i path/to/file.sql` | Run SQL commands from a file |
| `\q` | Quit `psql` |
| `\x` | Toggle expanded output |
| `\conninfo` | Show current connection details |

Common connection options:
- `-h`: host
- `-p`: port
- `-U`: database user
- `-d`: database name

The default PostgreSQL port is commonly `5432`, unless it was changed.

---

## 3. Create and connect to a database

```sql
CREATE DATABASE learning_db;
```

Connect to it in `psql`:

```text
\c learning_db
```

Delete a database:

```sql
DROP DATABASE learning_db;
```

**Warning:** `DROP DATABASE` permanently removes the database and its contents. Confirm the database name and your environment before running destructive commands. You cannot drop the database you are currently connected to.

---

## 4. Create tables and choose data types

A table defines the shape of the data.

```sql
CREATE TABLE person (
    id BIGINT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender VARCHAR(10),
    date_of_birth DATE,
    email VARCHAR(150)
);
```

Useful PostgreSQL data types:

| Type | Use |
|---|---|
| `SMALLINT`, `INTEGER`, `BIGINT` | Whole numbers of different ranges |
| `NUMERIC(10, 2)` | Exact decimal values, useful for money |
| `REAL`, `DOUBLE PRECISION` | Approximate floating-point numbers |
| `VARCHAR(n)` | Text with a maximum length |
| `TEXT` | Text without a declared maximum length |
| `BOOLEAN` | `TRUE` or `FALSE` |
| `DATE` | Calendar date |
| `TIME` | Time of day |
| `TIMESTAMP` | Date and time without time-zone semantics |
| `TIMESTAMPTZ` | Date and time interpreted with time-zone awareness |
| `UUID` | Universally unique identifier |
| `JSON` / `JSONB` | JSON data; `JSONB` is usually preferred for querying and indexing |

Use `DATE` for a birth date when a time of day is not needed.

---

## 5. Constraints: rules that protect your data

Constraints prevent invalid or inconsistent data from being stored.

### `NOT NULL`
A column must have a value.

### `PRIMARY KEY`
Uniquely identifies each row. A primary key must be unique and cannot be `NULL`. A table has one primary-key constraint, which may contain one or more columns.

### `UNIQUE`
Prevents duplicate values in the constrained column or column combination. In PostgreSQL, a normal `UNIQUE` constraint permits multiple `NULL` values by default.

### `CHECK`
Requires a Boolean condition to be true for each inserted or updated row.

### `FOREIGN KEY`
Requires a value to match a referenced key in another table, maintaining referential integrity.

Example:

```sql
CREATE TABLE person (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(150) UNIQUE,
    CHECK (date_of_birth <= CURRENT_DATE)
);
```

An identity column generates values automatically. Older examples may use `SERIAL` or `BIGSERIAL`; these remain common in existing code, but identity columns are the modern SQL-standard approach.

Add a constraint later:

```sql
ALTER TABLE person
ADD CONSTRAINT person_email_unique UNIQUE (email);
```

**Remember:** `NOT NULL` means required. `UNIQUE` means no duplicate non-null values under the default semantics. `PRIMARY KEY` means unique row identifier and implies both.

---

## 6. Insert data

```sql
INSERT INTO person
    (first_name, last_name, gender, date_of_birth, email)
VALUES
    ('Asha', 'Patil', 'Female', '2000-07-30', 'asha@example.com');
```

If `id` is generated automatically, leave it out of the insert.

Insert several rows:

```sql
INSERT INTO person
    (first_name, last_name, gender, date_of_birth, email)
VALUES
    ('Ravi', 'Shah', 'Male', '1998-02-10', 'ravi@example.com'),
    ('Neha', 'Joshi', 'Female', '2001-11-05', NULL);
```

Strings and dates are typically written in single quotes. `NULL` is written without quotes.

To load SQL commands saved in a file while using `psql`:

```text
\i /path/to/data.sql
```

Only run SQL files you trust, and check the target database before loading them.

---

## 7. Read data with `SELECT`

All columns:

```sql
SELECT * FROM person;
```

Selected columns:

```sql
SELECT first_name, last_name
FROM person;
```

Rename output columns with an alias:

```sql
SELECT first_name AS given_name, last_name AS family_name
FROM person;
```

`*` is convenient for exploration. In application code, explicitly selecting the needed columns is often clearer and avoids returning unnecessary data.

---

## 8. Sort and remove duplicates

### `ORDER BY`
Ascending order is the default.

```sql
SELECT first_name, date_of_birth
FROM person
ORDER BY date_of_birth ASC;
```

Newest birth dates first:

```sql
SELECT first_name, date_of_birth
FROM person
ORDER BY date_of_birth DESC;
```

Sort by more than one column:

```sql
SELECT *
FROM person
ORDER BY last_name ASC, first_name ASC;
```

### `DISTINCT`
Return unique values:

```sql
SELECT DISTINCT gender
FROM person;
```

Unique combinations of two columns:

```sql
SELECT DISTINCT country_of_birth, gender
FROM person;
```

---

## 9. Filter rows with `WHERE`

```sql
SELECT *
FROM person
WHERE gender = 'Female';
```

Comparison operators:

| Operator | Meaning |
|---|---|
| `=` | Equal |
| `<>` or `!=` | Not equal |
| `>` | Greater than |
| `<` | Less than |
| `>=` | Greater than or equal |
| `<=` | Less than or equal |

Combine conditions:

```sql
SELECT *
FROM person
WHERE gender = 'Female'
  AND date_of_birth >= DATE '2000-01-01';
```

```sql
SELECT *
FROM person
WHERE gender = 'Female'
   OR gender = 'Male';
```

Use parentheses when combining `AND` and `OR` if the intended logic would otherwise be unclear.

### `IN`
Match any value in a list:

```sql
SELECT *
FROM person
WHERE gender IN ('Female', 'Male');
```

### `BETWEEN`
Match a value within an inclusive range:

```sql
SELECT *
FROM person
WHERE date_of_birth BETWEEN DATE '1995-01-01' AND DATE '2005-12-31';
```

### `LIKE` and `ILIKE`
`LIKE` performs case-sensitive pattern matching in PostgreSQL; `ILIKE` performs case-insensitive pattern matching.

- `%` means zero or more characters.
- `_` means exactly one character.

```sql
-- Email ends in .com
SELECT * FROM person WHERE email LIKE '%.com';

-- Email contains a domain, case-insensitively
SELECT * FROM person WHERE email ILIKE '%@example.com';

-- First name begins with A
SELECT * FROM person WHERE first_name LIKE 'A%';
```

---

## 10. Limit results and paginate

```sql
SELECT *
FROM person
LIMIT 10;
```

Skip five rows, then return five:

```sql
SELECT *
FROM person
ORDER BY id
LIMIT 5 OFFSET 5;
```

`FETCH FIRST` is an alternative:

```sql
SELECT *
FROM person
ORDER BY id
FETCH FIRST 10 ROWS ONLY;
```

For predictable pagination, use `ORDER BY`. Without an ordering, the database does not promise which rows will appear first.

---

## 11. Aggregate functions and `GROUP BY`

Aggregate functions calculate a value across rows.

| Function | Purpose |
|---|---|
| `COUNT(*)` | Count rows |
| `COUNT(column)` | Count non-`NULL` values in a column |
| `MIN(column)` | Smallest value |
| `MAX(column)` | Largest value |
| `AVG(column)` | Average of non-`NULL` numeric values |
| `SUM(column)` | Total of non-`NULL` numeric values |

Examples:

```sql
SELECT COUNT(*) FROM person;
```

```sql
SELECT MIN(date_of_birth), MAX(date_of_birth)
FROM person;
```

Group rows by a value:

```sql
SELECT gender, COUNT(*) AS people_count
FROM person
GROUP BY gender;
```

Filter groups with `HAVING`:

```sql
SELECT gender, COUNT(*) AS people_count
FROM person
GROUP BY gender
HAVING COUNT(*) > 5;
```

**`WHERE` vs `HAVING`:**
- `WHERE` filters individual rows before grouping.
- `HAVING` filters groups after aggregation.

A useful logical order is `FROM` → `WHERE` → `GROUP BY` → `HAVING` → `SELECT` → `ORDER BY` → `LIMIT`.

---

## 12. Arithmetic, expressions, and aliases

SQL can calculate values in a query.

```sql
SELECT
    100 AS original_price,
    100 * 0.10 AS discount,
    100 - (100 * 0.10) AS discounted_price;
```

Common operators include `+`, `-`, `*`, and `/`. PostgreSQL also supports `%` for remainder and `^` for exponentiation.

```sql
SELECT
    price,
    ROUND(price * 0.90, 2) AS price_after_10_percent_discount
FROM product;
```

`AS` names a result column. `ROUND(value, 2)` rounds a numeric result to two decimal places.

---

## 13. `NULL`, `COALESCE`, and `NULLIF`

`NULL` represents a missing or unknown value. It is not the same as zero or an empty string.

Do not test for null using `= NULL`.

```sql
SELECT *
FROM person
WHERE email IS NULL;
```

```sql
SELECT *
FROM person
WHERE email IS NOT NULL;
```

### `COALESCE`
Returns the first argument that is not `NULL`.

```sql
SELECT
    first_name,
    COALESCE(email, 'Email not provided') AS email_display
FROM person;
```

### `NULLIF`
Returns `NULL` when its two arguments are equal. Otherwise, it returns the first argument.

```sql
SELECT 10 / NULLIF(0, 0);
```

This avoids a division-by-zero error by making the denominator `NULL`; the result is `NULL`.

You can combine them:

```sql
SELECT COALESCE(10 / NULLIF(denominator, 0), 0)
FROM calculation;
```

Use a fallback such as zero only when it makes sense for the application. A zero result and an undefined result are not always equivalent.

---

## 14. Dates and timestamps

Get the current date/time:

```sql
SELECT NOW();
SELECT CURRENT_DATE;
```

Convert a timestamp to a date:

```sql
SELECT NOW()::DATE;
```

Add or subtract a time interval:

```sql
SELECT NOW() - INTERVAL '1 year';
SELECT NOW() + INTERVAL '10 days';
SELECT NOW() - INTERVAL '3 months';
```

Extract a part of a date/time:

```sql
SELECT EXTRACT(YEAR FROM NOW());
SELECT EXTRACT(MONTH FROM NOW());
SELECT EXTRACT(DOW FROM NOW());
```

Calculate an age interval:

```sql
SELECT AGE(CURRENT_DATE, DATE '2000-07-30');
```

Use a date literal such as `DATE '2000-07-30'` to make the intended type clear. Remember that timestamps and time zones can affect the exact displayed time.

---

## 15. Update and delete data

### `UPDATE`
Always use a `WHERE` clause when you intend to change only specific rows.

```sql
UPDATE person
SET email = 'asha.patil@example.com'
WHERE id = 1;
```

Without `WHERE`, every row in the table may be updated.

### `DELETE`
Delete selected rows:

```sql
DELETE FROM person
WHERE id = 2;
```

Without `WHERE`, all rows are deleted, although the table itself remains.

### `ON CONFLICT`
Handle an insert that conflicts with a unique or primary-key constraint.

Ignore the conflict:

```sql
INSERT INTO person
    (first_name, last_name, gender, date_of_birth, email)
VALUES
    ('Asha', 'Patil', 'Female', '2000-07-30', 'asha@example.com')
ON CONFLICT (email) DO NOTHING;
```

Update the existing row instead (an **upsert**):

```sql
INSERT INTO person
    (first_name, last_name, gender, date_of_birth, email)
VALUES
    ('Asha', 'Patil', 'Female', '2000-07-30', 'asha@example.com')
ON CONFLICT (email)
DO UPDATE SET
    first_name = EXCLUDED.first_name,
    last_name = EXCLUDED.last_name;
```

`EXCLUDED` refers to the row you attempted to insert. The conflict target must correspond to an appropriate unique constraint or index.

---

## 16. Relationships and foreign keys

A relational database separates related data into tables and connects them with keys.

Example: one car can be associated with multiple people, while each person row references one car. This is a **one-to-many** relationship.

```sql
CREATE TABLE car (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL
);

CREATE TABLE person (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    car_id BIGINT REFERENCES car(id)
);
```

Here `person.car_id` is a foreign key that references `car.id`. It can be `NULL` unless `NOT NULL` is specified.

Add a foreign key later:

```sql
ALTER TABLE person
ADD CONSTRAINT person_car_fk
FOREIGN KEY (car_id) REFERENCES car(id);
```

Update a relationship:

```sql
UPDATE person
SET car_id = 1
WHERE id = 2;
```

The referenced car must exist before assigning its ID to a person.

### Deleting related rows
By default, deleting a parent row is blocked if child rows still reference it. Options include:
- Delete or reassign the child rows first.
- Set the foreign key to `NULL` if the column permits it.
- Configure an intentional action such as `ON DELETE CASCADE` if deleting dependent rows is truly the desired business rule.

Do not add cascading deletion casually. It can remove many related records.

---

## 17. Joins

Joins combine related rows from two or more tables.

### `INNER JOIN`
Returns only rows with matching values in both tables.

```sql
SELECT
    person.first_name,
    car.make,
    car.model
FROM person
INNER JOIN car ON person.car_id = car.id;
```

### `LEFT JOIN`
Returns every row from the left table and matching rows from the right table. If there is no match, right-table columns are `NULL`.

```sql
SELECT
    person.first_name,
    car.make,
    car.model
FROM person
LEFT JOIN car ON person.car_id = car.id;
```

This is useful when you want all people, including people who do not have a car.

### Join summary

| Join | What it returns |
|---|---|
| `INNER JOIN` | Matching rows from both sides |
| `LEFT JOIN` | All left rows and any matching right rows |
| `RIGHT JOIN` | All right rows and any matching left rows |
| `FULL OUTER JOIN` | All rows from both sides, matched where possible |

Use table aliases for readability:

```sql
SELECT p.first_name, c.make
FROM person AS p
LEFT JOIN car AS c ON p.car_id = c.id;
```

---

## 18. Export query results to CSV

In `psql`, `\copy` writes query results through the client machine's file access:

```text
\copy (SELECT p.first_name, c.make FROM person AS p LEFT JOIN car AS c ON p.car_id = c.id) TO 'results.csv' WITH (FORMAT CSV, HEADER TRUE)
```

- `FORMAT CSV` selects CSV format.
- `HEADER TRUE` writes column names as the first row.
- The destination path is interpreted on the machine running `psql`.

`\copy` is a `psql` command, not a normal SQL statement. Its file permissions differ from server-side SQL `COPY`.

---

## 19. Sequences and generated IDs

A sequence generates numeric values, often for IDs.

Identity columns are generally a good choice for new tables:

```sql
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
```

Older schemas may use:

```sql
id BIGSERIAL PRIMARY KEY
```

`SERIAL` and `BIGSERIAL` are shorthand patterns that create an integer column with a sequence-backed default. They are widely seen in existing databases.

Useful sequence concepts:
- `nextval(...)` obtains the next sequence value.
- Sequences are not transactional counters in the usual sense, so gaps in IDs are normal.
- Do not assume IDs will be gap-free or that they indicate the exact number of rows.

Inspect the table definition with `\d table_name` to see the generated column/default details.

---

## 20. UUIDs

A UUID is a 128-bit identifier designed to have an extremely low chance of collision when generated properly. UUIDs can be useful when IDs are generated across different services or databases.

PostgreSQL supports UUID values directly:

```sql
CREATE TABLE app_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email TEXT UNIQUE NOT NULL
);
```

On current PostgreSQL versions, `gen_random_uuid()` is available without installing `uuid-ossp`. Older tutorials may use the extension function `uuid_generate_v4()`.

UUIDs are harder to guess than sequential integers, but **UUIDs alone do not provide authorization or make a system secure**.

### Moving an existing schema from numeric IDs to UUIDs
This is a migration, not just a type change. You must account for:
1. Existing primary keys.
2. Every foreign key that references them.
3. Existing records and their ID mapping.
4. Application code, APIs, and indexes.
5. Migration order and rollback strategy.

For a real application, plan and test this migration rather than dropping and recreating production tables.

---

## 21. A practical practice sequence

Create a small database and practise the topics in this order:

1. Create `learning_db` and connect to it.
2. Create `person` and `car` tables.
3. Add primary keys, `NOT NULL`, `UNIQUE`, `CHECK`, and foreign-key constraints.
4. Insert at least 10 people and 3 cars.
5. Practise `SELECT`, column aliases, `WHERE`, `IN`, `BETWEEN`, `LIKE`, `ILIKE`, `ORDER BY`, `DISTINCT`, `LIMIT`, and `OFFSET`.
6. Use `COUNT`, `MIN`, `MAX`, `AVG`, `SUM`, `GROUP BY`, and `HAVING`.
7. Practise `COALESCE`, `NULLIF`, and date operations.
8. Use `UPDATE`, `DELETE`, and `ON CONFLICT` on test data.
9. Write both `INNER JOIN` and `LEFT JOIN` queries.
10. Export a query to CSV.
11. Inspect generated IDs and experiment with UUIDs in a separate test table.

### Practice questions

Try answering these by writing SQL yourself:

1. Show all people born after 1 January 2000.
2. Show only first and last names, sorted alphabetically by last name.
3. Find people whose email is missing.
4. Find emails ending in `.com`, case-insensitively.
5. Count people by gender.
6. Show only groups containing more than three people.
7. Show every person and their car, including people without a car.
8. Change one person's email, then verify the result.
9. Insert a duplicate email and handle the conflict safely.
10. Explain why deleting a car might fail when people reference it.

---

## 22. Quick revision sheet

| Concept | Remember |
|---|---|
| `CREATE DATABASE` | Create a database |
| `CREATE TABLE` | Define columns and constraints |
| `INSERT` | Add rows |
| `SELECT` | Read rows |
| `WHERE` | Filter rows |
| `ORDER BY` | Sort results |
| `DISTINCT` | Remove duplicate result combinations |
| `LIMIT` / `OFFSET` | Restrict and skip result rows |
| `GROUP BY` | Aggregate rows into groups |
| `HAVING` | Filter groups |
| `UPDATE` | Modify existing rows |
| `DELETE` | Remove rows |
| `PRIMARY KEY` | Unique, non-null row identifier |
| `FOREIGN KEY` | Enforce a reference to another table |
| `INNER JOIN` | Only matching rows |
| `LEFT JOIN` | All left rows, matching right rows where present |
| `COALESCE` | First non-null value |
| `NULLIF` | Return null when two values are equal |
| `ON CONFLICT` | Handle a uniqueness conflict during insert |
| `\copy` | Export query output from `psql` to a client-side file |
| `UUID` | Globally unique-style identifier with very low collision risk |

## What to focus on for backend development

Since PostgreSQL is part of a backend learning path, prioritise:
1. Table design and data types.
2. Primary keys, foreign keys, and constraints.
3. CRUD operations (`INSERT`, `SELECT`, `UPDATE`, `DELETE`).
4. Filtering, sorting, and pagination.
5. Joins and aggregation.
6. Transactions and indexes as the next topics after these fundamentals.
7. Connecting a Spring Boot application to PostgreSQL after you can write and explain these queries yourself.
