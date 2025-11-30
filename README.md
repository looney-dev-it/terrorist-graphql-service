# 🔎 GraphQL Terrorist Search & Hit Subscription

## 📖 Overview
This project is a **Spring Boot + GraphQL** application designed to:- Query terrorist data (search by lastname, firstname, or wholename).
- Subscribe to **real-time terrorist hit events** via GraphQL subscriptions.
- Provide a secure, event-driven architecture with JWT authentication for WebSocket connections.

The goal is to demonstrate how GraphQL can unify **queries, mutations, and subscriptions** in a single API layer.

---

## 🏗️ Architecture

```text
[GraphQL Clients (Queries)] ---> [Spring Boot GraphQL API] ---> [Database / Services] --> return hits to Clients
[GraphQL Clients (Subscriptions)] <---(WebSocket GraphQL Subscriptions)--- [Spring Boot GraphQL API]
```

- **GraphQL Clients**: Apollo Client (frontend), GraphiQL, or any GraphQL consumer.
- **GraphQL API**: Exposes queries, mutations, and subscriptions.
- **Database Layer**: JPA entities for persistence (e.g., `TerroristEntity`, `TerroristHitEntity`).
- **Subscription Flow**: Backend publishes `TerroristHit` events → clients receive notifications in real time.

---

## 📂 Project Structure

- `src/main/java/.../persistence`
  - `TerroristEntity` → persisted terrorist data
  - `TerroristHitEntity` → persisted hit events
- `src/main/java/.../auth`
  - `AuthService` → authentication service - generate token
  - `AuthController` → rest api for authentication
- `src/main/java/.../graphql`
  - `TerroristController` → queries for terrorist search
- `src/main/java/.../ingestion` → Ingestion of new list of data
  - `ExcelDownloadService` → connects to public webside & download excel
  - `ImportCoordinator` → coordinate the import of new version of terrorist list
- `src/main/java/.../parsing` 
    - perform parsing from excel to object
- `src/main/java/.../service`
  - `TerroristImportService` → import the excel list into database
  - `TerroristService` → coordinate the terrorist request and hits
- `src/main/java/.../subscription`
  - `TerroristHitSubscription` → GraphQL Subscription mapping and setup
- `src/main/resources/schema.graphqls`
    - GraphQL schema definition with types, queries, and subscriptions

---

## 📜 GraphQL Schema

```graphql
type Query {
    """
    Search by lastname
    - Ignore Case
    - Partial search (LIKE '%...%')
    Example: terroristsByLastname(lastname: "smi") will also return "Smith", "smith", "SMITH"
    """
    terroristsByLastname(lastname: String!): [Terrorist!]!
    """
    Search by firstname
    - Ignore Case
    - Partial search (LIKE '%...%')
    Example: terroristsByFirstname(firstname: "joh") will also return "John", "john", "JOHN"
    """
    terroristsByFirstname(firstname: String!): [Terrorist!]!
    """
    Search by wholename
    - Ignore Case
    - Partial search (LIKE '%...%')
    Example: terroristsByWholename(wholename: "smi") will also return "Smith", "smith", "SMITH"
    """
    terroristsByWholename(wholename: String!): [Terrorist!]!
    """
    Search by nrn (national register number)
    - Search will only take numbers into account to avoid formatting issue
    Example: terroristsByNrn(nrn: "11.22.33-444.55")
    the query will search for number 11223344455 in the nrn column which has also be normalized
    """
    terroristsByNrn(nrn: String!): [Terrorist!]!
}

type Subscription {
    terroristHitAdded: TerroristHit!
}


type TerroristHit {
    id: ID!
    user: User!
    hit_date: String
    hit_criteria: String
    hit_content: String
}

type User {
    id: ID!
    username: String
}

type Terrorist {
    id: ID!
    nrn: String
    lastname: String
    firstname: String
    middlename: String
    wholename: String
    gender: String
    birthdate: String
    birthplace: String
    birthcountry: String
    function: String
    remark: String
    type: String
    regulation: String
    publicationdate: String
    embargo: String
}

```

---

## 🚀 Usage

### Queries
Example: search by lastname
```graphql
query {
  terroristsByLastname(lastname: "SMITH") {
    id
    firstname
    lastname
  }
}
```

### Subscriptions
Example: listen for new hits
```graphql
subscription {
  terroristHitAdded {
    id
    hit_date
    hit_criteria
    hit_content
    user {
      id
      username
    }
  }
}
```

---

## 🔐 Security
- JWT authentication integrated with Spring Security.
- Tokens passed during WebSocket handshake for subscriptions.
- Ensures only authenticated clients can subscribe to hit events.

---

## 🛠️ Tech Stack
- **Backend**: Spring Boot, Spring GraphQL, JPA/Hibernate
- **Frontend/Client**: Apollo Client, GraphiQL (for testing purposes)
- **Database**: (e.g., PostgreSQL/MySQL) - SQLite by default
- **Build Tool**: Gradle

---

## 📦 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/looney-dev-it/terrorist-graphql-service.git
   cd terrorist-graphql-service
   ```

2. Build and run:
   ```bash
   sync you gradle
   ```

3. Access GraphQL endpoint:
    - Queries/Mutations: `http://localhost:8080/graphql`
    - Subscriptions: `ws://localhost:8080/graphql`

---

## 🎯 Demo Scenario
- Perform queries to search terrorists by name.
- When a hit occurs, backend publishes a `TerroristHit` event.
- Subscribed clients receive the event in real time.

---

## 📄 License
This project is licensed under the MIT License.
