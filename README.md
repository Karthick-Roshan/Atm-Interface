# Java ATM Interface

This project is a **command-line based ATM Interface** developed in **Java**, simulating basic banking operations such as user login, account management, fund transfer, deposits, and withdrawals. It demonstrates strong use of **Object-Oriented Programming (OOP)** principles such as encapsulation, abstraction, and modular design.




## Features

- ✅ User Registration and Authentication
- 💳 Multiple Account Handling per User
- 💰 Deposit, Withdrawal, and Transfer Operations
- 📜 Transaction History Display
- 🔐 PIN-based Login (with MD5 hashing for basic security)
- 🧾 Account Summary and Balance Display




## Object-Oriented Structure

The project uses the following Java classes:

| Class        | Responsibility |
|--------------|----------------|
| `Bank`       | Manages users, generates unique user/account IDs |
| `User`       | Holds user info, manages multiple accounts |
| `Account`    | Represents a bank account, stores transactions |
| `Transaction`| Records individual transactions with memo & timestamp |
| `Atm`        | Main application logic and user interface (CLI) |

---

## Getting Started

### Prerequisites

- Java JDK 8 or later
- Any Java IDE (IntelliJ IDEA, Eclipse) or terminal with `javac`

### Run the App

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Atm-Interface.git
   cd Atm-Interface/src
