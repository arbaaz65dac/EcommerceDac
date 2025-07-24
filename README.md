# Multi-Price E-Commerce

A Spring Boot-based e-commerce backend supporting tiered pricing, user authentication, cart, order, payment, ratings, and reviews.

---

## Features
- **Tiered Pricing:** Product prices decrease as buyer count increases (e.g., $200 default, $100 for 25K+, $50 for 50K+, $25 for 100K+ buyers).
- **Admin & User APIs:** Separate endpoints for admin and regular users.
- **Authentication:** JWT-based signup/login, role-based access control.
- **Product Management:** CRUD, tiered pricing, price calculation.
- **Cart & Cart Items:** Add, update, remove items; reset cart.
- **Order Management:** Place orders, view orders, update status.
- **Payment:** Initiate and confirm payments.
- **Ratings & Reviews:** Submit and fetch product ratings and reviews.

---

## Project Structure & Code Organization
```
Multi-Price_E-Commerce/
├── src/main/java/com/cdac/e_Commerce/
│   ├── controller/         # All REST controllers (API endpoints)
│   ├── service/            # Service interfaces and implementations (business logic)
│   ├── repository/         # Spring Data JPA repositories (database access)
│   ├── model/              # JPA entities (Product, User, Order, etc.)
│   ├── dto/                # Data Transfer Objects (API payloads)
│   ├── security/           # JWT utilities and filters
│   ├── config/             # Security configuration
│   └── MultiPriceECommerceApplication.java
│
├── src/main/resources/
│   ├── application.properties
│   ├── static/
│   └── templates/
└── pom.xml
```

---

## Controllers & Endpoints

### UserProductController (`/api/products`)
- `GET /api/products` — List all available products
- `GET /api/products/{id}` — Product detail including tier info
- `GET /api/products/{id}/price` — Get current price based on buyer count
- `GET /api/products/{id}/tiers` — Get full tier breakdown

### AuthController (`/api/auth`)
- `POST /api/auth/signup` — Register a new user (payload: UserDto)
- `POST /api/auth/login` — Login and receive JWT token (payload: `{ "username": "...", "password": "..." }`)

### CartController (`/api/cart`)
- `GET /api/cart?userId=...` — Get logged-in user’s cart
- `POST /api/cart?userId=...` — Create or reset a cart

### CartItemController (`/api/cart/items`)
- `POST /api/cart/items?userId=...` — Add product to cart (payload: CartItemDto)
- `PUT /api/cart/items/{id}?userId=...` — Update cart item (payload: CartItemDto)
- `DELETE /api/cart/items/{id}?userId=...` — Remove item from cart

### OrderController (`/api/orders`)
- `POST /api/orders` — Place a new order (payload: OrderDto)
- `GET /api/orders?userId=...` — Get current user’s orders
- `GET /api/orders/{id}` — Get order details

### PaymentController (`/api/payment`)
- `POST /api/payment/initiate` — Initiate payment session (payload: PaymentDto)
- `POST /api/payment/confirm` — Confirm payment (payload: PaymentDto)

### RatingController (`/api/ratings`)
- `POST /api/ratings` — Submit product rating (payload: RatingDto)
- `GET /api/ratings/product/{productId}` — Get all ratings for a product

### ReviewController (`/api/reviews`)
- `POST /api/reviews` — Submit a review (payload: ReviewDto)
- `GET /api/reviews/product/{productId}` — Get reviews for a product

### UserController (`/api/users`)
- `GET /api/users/me?userId=...` — Get logged-in user info
- `PUT /api/users/update?userId=...` — Update user profile (payload: UserDto)

### HomeController (`/api/home`)
- `GET /api/home` — Fetch homepage content (banners, featured products)

### AdminOrderController (`/api/admin/orders`)
- `GET /api/admin/orders` — Fetch all orders (admin access)
- `GET /api/admin/orders/{id}` — Get specific order details by ID
- `PUT /api/admin/orders/{id}/status` — Update order status (payload: `{ "status": "shipped" }`)

### AdminProductController (`/api/admin/products`)
- `GET /api/admin/products` — List all products (admin access)
- `POST /api/admin/products` — Add a new product with tier pricing (payload: ProductDto)
- `PUT /api/admin/products/{id}` — Update product and tier pricing (payload: ProductDto)
- `GET /api/admin/products/name/{name}` — Fetch product by name
- `DELETE /api/admin/products/{id}` — Delete a product

---

## DTOs (Data Transfer Objects)
- **UserDto:** id, username, email, role
- **ProductDto:** id, name, description, defaultPrice, tierPrices, buyerCount
- **OrderDto:** id, userId, items (OrderItemDto), status, totalAmount
- **OrderItemDto:** productId, quantity, price
- **CartDto:** id, userId, items (CartItemDto), totalAmount
- **CartItemDto:** productId, quantity, price
- **RatingDto:** id, productId, userId, score, comment
- **ReviewDto:** id, productId, userId, content, createdAt
- **PaymentDto:** id, orderId, userId, amount, status, transactionId

---

## Models (Entities)
- **User:** JPA entity for users
- **Product:** JPA entity for products, includes tiered pricing
- **TierPrice:** JPA entity for tiered price levels
- **Order:** JPA entity for orders
- **OrderItem:** JPA entity for order items
- **Cart:** JPA entity for shopping carts
- **CartItem:** JPA entity for cart items
- **Rating:** JPA entity for product ratings
- **Review:** JPA entity for product reviews
- **Payment:** JPA entity for payment records

---

## Services
- **ProductService:** Business logic for products and tiered pricing
- **UserService:** User registration, authentication, profile update
- **OrderService:** Place orders, update status, fetch orders
- **CartService:** Cart and cart item management
- **RatingService:** Submit and fetch ratings
- **ReviewService:** Submit and fetch reviews
- **PaymentService:** Initiate and confirm payments

---

## Security
- **Spring Security** with JWT authentication
- **JwtUtil:** Utility for generating and validating JWT tokens
- **JwtAuthenticationFilter:** Extracts and validates JWT from requests
- **SecurityConfig:** Configures endpoint security and JWT filter
- **Role-based access:** `/api/admin/**` for admins, `/api/**` for users and admins, `/api/auth/**` is public

---

## Example Payloads

**User Signup/Login:**
```json
{
  "username": "john",
  "email": "john@example.com",
  "role": "USER"
}
```
**Login:**
```json
{
  "username": "john",
  "password": "password"
}
```
**Product:**
```json
{
  "name": "Laptop",
  "description": "High-end laptop",
  "defaultPrice": 200.0,
  "tierPrices": [ { "minBuyers": 25000, "price": 100.0 }, ... ],
  "buyerCount": 0
}
```
**Order:**
```json
{
  "userId": 1,
  "items": [ { "productId": 2, "quantity": 1, "price": 200.0 } ],
  "status": "PLACED",
  "totalAmount": 200.0
}
```
**Cart Item:**
```json
{
  "productId": 2,
  "quantity": 1,
  "price": 200.0
}
```
**Rating:**
```json
{
  "productId": 2,
  "userId": 1,
  "score": 5,
  "comment": "Great product!"
}
```
**Review:**
```json
{
  "productId": 2,
  "userId": 1,
  "content": "Excellent quality!"
}
```
**Payment:**
```json
{
  "orderId": 1,
  "userId": 1,
  "amount": 200.0,
  "status": "INITIATED",
  "transactionId": "txn_123"
}
```

---

## Tiered Pricing Logic
- Default: $200
- 25K+ buyers: $100
- 50K+ buyers: $50
- 100K+ buyers: $25

---

## Setup & Run
1. **Clone the repo:**
   ```bash
   git clone <repo-url>
   cd Multi-Price_E-Commerce
   ```
2. **Configure Database:**
   Edit `src/main/resources/application.properties` for your DB (H2, MySQL, etc.).
3. **Build & Run:**
   ```bash
   ./mvnw spring-boot:run
   ```
4. **API Docs:**
   Use Postman or Swagger to explore endpoints.

---

## Technologies Used
- Java 11+
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- Maven

---

## Notes
- All business logic is implemented in the service layer.
- Controllers are organized under `controller/` package.
- DTOs are used for API data transfer.
- Entities are mapped with JPA annotations.
- Security is JWT-based with role support.

---

**This project is a full-featured backend for a modern e-commerce platform with scalable pricing, robust user management, and a clean, modular codebase.** 