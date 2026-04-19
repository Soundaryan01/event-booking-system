# Event Booking System

A backend REST API for managing event bookings, venues, seats, and tickets.
Built using **Spring Boot**, **Spring Data JPA**, and **MySQL**.

---

## Features

* User Management
* Venue Management
* Event Creation
* Seat Management
* Booking System
* Ticket Generation
* Booking/Ticket Cancellation
* Seat Availability Tracking
* Global Exception Handling
* Request Validation
* Standardized API Responses

---

## Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven

---

## Project Architecture

The project follows a **layered architecture**:

Client → Controller → Service → Repository → Database

DTO → Mapper → Entity

### Package Structure

```
controller
service
repository
entity
dto
mapper
exception
wrapper
enum
```

---

## Core Entities

* User
* Venue
* Event
* Seat
* Booking
* Ticket

---

## Entity Relationships

```
User
│
└── Booking
     │
     └── Event
          │
          └── Venue
               │
               └── Seat

Ticket
├── Booking
├── Event
└── Seat
```

---

## API Response Format

All APIs return a standardized response:

```json
{
  "success": true,
  "message": "Request successful",
  "data": {}
}
```

---

## Example APIs

### Create User

POST `/users`

```json
{
  "name": "John Doe",
  "email": "john@email.com"
}
```

---

### Create Event

POST `/events`

```json
{
  "name": "Music Concert",
  "venueId": 1,
  "eventDate": "2026-02-15T19:00:00"
}
```

---

### Create Booking

POST `/bookings`

```json
{
  "userId": 1,
  "eventId": 2
}
```

---

### Create Ticket

POST `/tickets`

```json
{
  "bookingId": 1,
  "seatId": 15
}
```

---

## Validation

Request DTOs use validation annotations:

* `@NotNull`
* `@NotBlank`
* `@Email`
* `@Future`

Validation errors are handled globally using `@ControllerAdvice`.

---

## Exception Handling

Global exception handling ensures consistent error responses across the API.

---

## Future Improvements

* Authentication (JWT / OAuth)
* Payment integration
* Seat locking mechanism
* Event search & filtering
* Pagination support
* Redis caching

---

## Author

Soundaryan J
