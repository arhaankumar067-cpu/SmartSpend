# SmartSpend – Student Expense & Budget Management System

# Overview
SmartSpend is a Java-based application designed to help students manage their daily expenses and monthly budgets.

The system allows users to record expenses, categorize them, search and sort expenses, monitor their monthly budget, and generate spending reports.

# Features
- Add expenses
- View all expenses
- Delete expenses
- Search expenses by category
- Search expenses by date
- Sort expenses by amount
- Set monthly budget
- Check budget status
- Generate spending reports
- Category-wise spending analysis
- Persistent data storage
- Input validation
- Exception handling

## Technologies Used
- Java
- Object-Oriented Programming
- ArrayList
- HashMap
- Enum
- File Handling
- Exception Handling
- LocalDate
- Visual Studio Code

# Functional Modules

 1. Expense Management
Handles adding, viewing, deleting, searching and sorting expenses.

 2. Budget Management
Allows users to set and monitor their monthly budget.

 3. Reports and Analytics
Calculates total spending, average spending, highest expense and category-wise spending.

 4. Data Storage
Stores expenses in `expenses.csv` and the monthly budget in `budget.txt`.

# Project Structure

SmartSpend
├── data
│   ├── expenses.csv
│   └── budget.txt
├── out
├── src
│   └── smartspend
│       ├── BudgetManager.java
│       ├── Category.java
│       ├── Expense.java
│       ├── ExpenseManager.java
│       ├── FileManager.java
│       ├── InputValidator.java
│       ├── Main.java
│       └── ReportGenerator.java
├── README.md
└── statement.md