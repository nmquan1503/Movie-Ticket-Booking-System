# Backend for Movie Ticket Booking Website

This is the backend service for a movie ticket booking website, developed with Spring Boot. It provides a comprehensive RESTful API to manage movies, showtimes, ticket bookings, user authentication, and payment processing through VNPAY.

---

## ⚙️ Technologies Used

- **Java 21**  
- **Spring Boot 3**  
- **Spring Security + OAuth2 Authorization Server**  
- **Spring Data JPA**  
- **MySQL**  
- **QueryDSL**  
- **VNPAY Payment Gateway**  

---

## 🧩 Key Features

### 🛡️ Authentication & Authorization

- User login using OAuth2 with JWT (access token + refresh token)  
- Role-based access control with user roles such as regular users and administrators  

### 🎞️ Movie Management

- View movie lists and detailed information  
- Comment on and rate movies with star ratings  
- Add, update, and delete movie records  

### 🗓️ Showtimes & Screening Rooms

- Create showtimes for movies assigned to specific screening rooms  
- Validate and prevent scheduling conflicts within the same room  

### 💺 Ticket Booking & Seat Selection

- Display seat layouts for each showtime  
- Book tickets by selecting available seats  

### 💳 Payment Integration via VNPAY

- Generate payment URLs for ticket orders  
- Handle and verify payment results securely  
- Validate security signatures from VNPAY  
- Update ticket status after successful payment  

### 👤 User Management

- Register new user accounts  
- Retrieve and update personal user information  
- Manage the list of users  