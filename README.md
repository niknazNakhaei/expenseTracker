
# 📊 Expense Tracker - Modular Spring Boot + Kafka Stream

A modular expense tracking system with full JWT authentication, dynamic search using Spring Specification, and real-time reporting powered by Kafka Streams.

---

## 🧱 Modules

```
├── gateway # Entry point for client APIs
├── auth # Authentication & user management (JWT)
└── expense # Expense tracking, categorization & Kafka processing
```

## 🚀 How It Works

1. **User Registration & Token Generation**
   - Users must first register via the `auth` service.
   - Upon successful login, a **JWT token** is issued.

2. **Category Setup**
   - Users define custom categories (e.g., Coffee, Rent) via `/categories`.

3. **Expense / Income Entry**
   - Users register financial records (expense/income) using their categories.
   - Records are stored in `tbl_expense`.

4. **Event Queuing with Kafka**
   - Each `Expense` insert also creates a corresponding `SentEvent` in `tbl_sent_event`.
   - Both are saved in a **single transaction** to ensure consistency.
   - Then, the Kafka producer sends the message to the expense-processor topic.

5. **Kafka Stream Processing**
   - Kafka consumer reads from the `expense-processor` topic.
   - Aggregates monthly expenses and writes results into `tbl_monthly_report`.

6. **Search & Reporting**
   - All entries across categories, expenses, and Monthly-report are searchable via the dynamic `/search` endpoint using **Spring Specification**.

## 🛠️ Technologies Used

- Java 17 + Spring Boot 3.5
- Spring Security (JWT)
- Spring Data JPA + Specification API
- Apache Kafka + Kafka Streams
- Oracle Database
- MapStruct
- Lombok
- Swagger / OpenAPI

## ⚙️ Kafka Event Handling

Due to Kafka's non-transactional nature, we ensure delivery by:

- Saving both `Expense` and `SentEvent` in **1 DB transaction**.
- Only after success, we publish the Kafka message to topic `expense-processor`.
- The Kafka Stream consumer aggregates data by category and month.
- Results are persisted in `tbl_monthly_report`.

## 🔍 Dynamic Search

All major entities (`Category`, `Expense`, `MonthlyReport`) support dynamic filtering via `/search` endpoints using Spring Data **Specification API**.

Example:

```http
POST http://localhost:8080/category/search 
```

## 📂 Database Tables

Table	Description
tbl_category	User-defined categories
tbl_expense	Income/expense records
tbl_sent_event	Stores sent Kafka messages
tbl_monthly_report	Aggregated report (Kafka Stream output)

## 📦 API Flow Diagram

sequenceDiagram
    participant Client
    participant Gateway
    participant Auth
    participant Expense
    participant Kafka
    participant ReportDB

    Client->>Gateway: Register/Login
    Gateway->>Auth: Forward request
    Auth-->>Gateway: JWT Token
    Client->>Gateway: Add Category
    Gateway->>Expense: Save in tbl_category
    Client->>Gateway: Add Expense
    Gateway->>Expense: Save Expense + SentEvent
    Expense-->>Kafka: Send message (if transaction success)
    Kafka->>ReportDB: Aggregate and save monthly report
    Client->>Gateway: /search (for categories/expenses/reports)

## 🧪 How to Run

Ensure Kafka, Oracle DB, and all ports are running.

Run each module (gateway, auth, expense) individually or as part of a monorepo.

Access the Swagger UI to explore all APIs:

http://localhost:8080/swagger-ui/index.html#/
Fully documented endpoints for:

Authentication (Register/Login)

Category Management

Expense Tracking

Monthly Reports


## 📌 Notes

All services communicate via REST behind the gateway.

Every REST response follows a uniform error format using @ControllerAdvice.

Kafka Stream processor is idempotent and resilient to reprocessing.

## 👨‍💻 Author

Created with ❤️ by Niknaz Nakhaei

## 📄 License

MIT – use it freely.
