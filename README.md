# 🛡️ Insurance Self-Service Web Application

A simple end-to-end insurance purchase web application built using **Spring Boot**, **Thymeleaf**, and **SQLite**.
This project simulates a travel insurance purchase journey with backend validation, pricing logic, and database persistence.

---

# 🚀 Features

### ✅ User Journey

1. **Step 1: Coverage Selection**

   * Choose Coverage Type (Single Trip / Annual)
   * Choose Area of Travel (DB-driven)
   * Select Travel Dates

2. **Step 2: Plan Selection**

   * Choose Plan A / Plan B
   * Price calculated from backend

3. **Step 3: Customer Information**

   * Input personal details (NRIC, email, mobile, etc.)
   * Backend validation applied

4. **Step 4: Summary & Confirmation**

   * Review all details
   * Submit application

---

### ✅ Key Functionalities

* 🔹 Backend pricing engine (DB-driven with ENUM fallback)
* 🔹 Travel area configuration stored in database
* 🔹 Validation logic handled in backend
* 🔹 Clean multi-step form flow
* 🔹 Flash success message after submission
* 🔹 Same customer can purchase multiple policies
* 🔹 Data persisted using SQLite

---

# 🧱 Tech Stack

| Layer    | Technology                  |
| -------- | --------------------------- |
| Backend  | Spring Boot                 |
| Frontend | Thymeleaf + Bootstrap       |
| Database | SQLite                      |
| ORM      | Spring Data JPA / Hibernate |

---

# 📂 Project Structure

```
src/main/java/com/my/insurance/
│
├── config/           # SQLite dialect & data loader
├── controller/       # Web controllers (page navigation)
├── service/          # Business logic (pricing, validation)
├── entity/           # JPA entities (Customer, Policy, TravelArea, Pricing)
├── repository/       # JPA repositories
├── dto/              # Request/Response objects
├── exception/        # Exception handling
├── util/             # Utility classes (NRIC parsing, enums)
```

---

# ⚙️ Setup & Run Guide

## ✅ Prerequisites

* Java 17+
* Maven 3+
* IDE (IntelliJ / Eclipse)

---

## ▶️ Run Application

### 1. Clone repository

```
git clone <your-repo-url>
cd insurance
```

---

### 2. Build project

```
mvn clean install
```

---

### 3. Run Spring Boot app

```
mvn spring-boot:run
```

OR run main class:

```
InsuranceApplication.java
```

---

### 4. Access application

Open browser:

```
http://localhost:8080/
```

---

# 🗄️ Database (SQLite)

* DB file auto-created:

```
insurance.db
```

* Auto table creation via:

```
spring.jpa.hibernate.ddl-auto=update
```

* Initial data loaded via:

```
DataLoader.java
```

Includes:

* Travel Areas
* Pricing rules

---

# 💰 Pricing Logic

Pricing is resolved using:

1. **Database (Primary Source)**
2. **Enum Fallback (Safety Mechanism)**

### Example:

| Plan   | Coverage | Area   | Price       |
| ------ | -------- | ------ | ----------- |
| Plan A | Single   | Area 1 | RM 10/day   |
| Plan B | Annual   | Area 3 | RM 250/year |

---

# 🧠 Business Rules Implemented

* Area 4 NOT allowed for Annual coverage
* NRIC format validation (12 digits, YYMMDD)
* Date validation (max 180 days for single trip)
* Mobile number format validation
* Email format validation

---

# 🔁 Flow Architecture

```
Coverage → Plan → Customer → Summary → Submit
```

* Data passed via form (hidden fields)
* Final submission persists:

  * Customer
  * Policy

---

# 📌 Design Decisions

### 🔹 Backend-driven logic

* Pricing handled in service layer
* Avoids frontend manipulation

### 🔹 DB-driven configuration

* Travel areas & pricing stored in DB
* Easy to extend without code changes

### 🔹 Enum fallback

* Ensures system works even if DB missing data

---

# ✅ Submission Output

After submission:

* Policy stored in DB
* Success message displayed
* Policy ID returned

---

# 👨‍💻 Author

Developed as a technical assessment project demonstrating:

* Backend architecture
* Clean code practices
* Business logic handling
* Full-stack flow implementation

---
