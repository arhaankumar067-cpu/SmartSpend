package smartspend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static ExpenseManager expenseManager =
            new ExpenseManager();

    private static BudgetManager budgetManager =
            new BudgetManager();

    private static ReportGenerator reportGenerator =
            new ReportGenerator();

    private static FileManager fileManager =
            new FileManager();

    public static void main(String[] args) {

        // Load previously saved expenses
        ArrayList<Expense> savedExpenses =
                fileManager.loadExpenses();

        for (Expense expense : savedExpenses) {
            expenseManager.addExpenseSilently(expense);
        }
        // Load previously saved budget
        double savedBudget = fileManager.loadBudget();

        if (savedBudget > 0) {
            budgetManager.setBudget(savedBudget);
        }

        System.out.println("========================================");
        System.out.println("          WELCOME TO SMARTSPEND");
        System.out.println("   Student Expense & Budget Manager");
        System.out.println("========================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    expenseManager.viewExpenses();
                    break;

                case 3:
                    deleteExpense();
                    break;

                case 4:
                    searchByCategory();
                    break;

                case 5:
                    searchByDate();
                    break;

                case 6:
                    sortByAmount();
                    break;

                case 7:
                    setBudget();
                    break;

                case 8:
                    checkBudget();
                    break;

                case 9:
                    generateReport();
                    break;

                case 10:
                    saveAndExit();
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-10."
                    );
            }
        }

        scanner.close();
    }

    // Display main menu
    private static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Delete Expense");
        System.out.println("4. Search by Category");
        System.out.println("5. Search by Date");
        System.out.println("6. Sort by Amount");
        System.out.println("7. Set Monthly Budget");
        System.out.println("8. Check Budget Status");
        System.out.println("9. Generate Spending Report");
        System.out.println("10. Exit");
        System.out.println("========================================");
    }

    // Add a new expense
    private static void addExpense() {

        System.out.println("\n========== ADD EXPENSE ==========");

        double amount = readDouble("Enter amount: ₹");

        if (!InputValidator.isValidAmount(amount)) {
            System.out.println(
                    "Invalid amount. Amount must be greater than zero."
            );
            return;
        }

        System.out.println("\nSelect Category:");

        Category[] categories = Category.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.println(
                    (i + 1) + ". " + categories[i]
            );
        }

        int categoryChoice =
                readInteger("Enter category number: ");

        if (categoryChoice < 1 ||
                categoryChoice > categories.length) {

            System.out.println("Invalid category.");
            return;
        }

        Category category =
                categories[categoryChoice - 1];

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        if (!InputValidator.isValidDescription(description)) {
            System.out.println(
                    "Description cannot be empty."
            );
            return;
        }

        int id = generateId();

        Expense expense = new Expense(
                id,
                amount,
                category,
                description,
                LocalDate.now()
        );

        expenseManager.addExpense(expense);

        // Save immediately
        fileManager.saveExpenses(
                expenseManager.getExpenses()
        );
    }

    // Delete an expense
    private static void deleteExpense() {

        System.out.println("\n========== DELETE EXPENSE ==========");

        int id = readInteger("Enter expense ID: ");

        if (!InputValidator.isValidId(id)) {
            System.out.println("Invalid ID.");
            return;
        }

        boolean deleted =
                expenseManager.deleteExpense(id);

        if (deleted) {

            System.out.println(
                    "Expense deleted successfully."
            );

            fileManager.saveExpenses(
                    expenseManager.getExpenses()
            );

        } else {

            System.out.println(
                    "No expense found with ID " + id
            );
        }
    }

    // Search expenses by category
    private static void searchByCategory() {

        System.out.println(
                "\n========== SEARCH BY CATEGORY =========="
        );

        Category[] categories = Category.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.println(
                    (i + 1) + ". " + categories[i]
            );
        }

        int choice =
                readInteger("Select category: ");

        if (choice < 1 ||
                choice > categories.length) {

            System.out.println("Invalid category.");
            return;
        }

        Category selected =
                categories[choice - 1];

        List<Expense> results =
                expenseManager.searchByCategory(selected);

        if (results.isEmpty()) {

            System.out.println(
                    "No expenses found in " + selected
            );

            return;
        }

        System.out.println(
                "\nExpenses in " + selected + ":"
        );

        for (Expense expense : results) {
            System.out.println(expense);
        }
    }

    // Set monthly budget
    private static void setBudget() {

        System.out.println(
                "\n========== SET MONTHLY BUDGET =========="
        );

        double budget =
                readDouble("Enter monthly budget: ₹");

        if (!InputValidator.isValidBudget(budget)) {

            System.out.println(
                    "Invalid budget. Budget must be greater than zero."
            );

            return;
        }

        budgetManager.setBudget(budget);

fileManager.saveBudget(budget);

System.out.printf(
                "Monthly budget set to ₹%.2f%n",
                budget
        );
    }

    // Check budget status
    private static void checkBudget() {

        double totalSpent =
                expenseManager.calculateTotal();

        budgetManager.checkBudgetStatus(totalSpent);
    }

    // Generate spending report
    private static void generateReport() {

        reportGenerator.generateReport(
                expenseManager.getExpenses(),
                budgetManager.getBudget()
        );
    }

    // Save data and exit
    private static void saveAndExit() {

        fileManager.saveExpenses(
                expenseManager.getExpenses()
        );

        System.out.println("\nThank you for using SmartSpend!");
        System.out.println("Goodbye!");
    }

    // Generate unique ID
    private static int generateId() {

        int maxId = 0;

        for (Expense expense :
                expenseManager.getExpenses()) {

            if (expense.getId() > maxId) {
                maxId = expense.getId();
            }
        }

        return maxId + 1;
    }

    // Read integer safely
    private static int readInteger(String message) {

        while (true) {

            try {

                System.out.print(message);

                String input =
                        scanner.nextLine();

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid whole number."
                );
            }
        }
    }

    // Read decimal number safely
    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                String input =
                        scanner.nextLine();

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
// Search expenses by date
private static void searchByDate() {

    System.out.println("\n========== SEARCH BY DATE ==========");

    System.out.print("Enter date (YYYY-MM-DD): ");

    String dateInput = scanner.nextLine();

    try {

        LocalDate date = LocalDate.parse(dateInput);

        List<Expense> results =
                expenseManager.searchByDate(date);

        if (results.isEmpty()) {

            System.out.println(
                    "No expenses found on " + date
            );

            return;
        }

        System.out.println(
                "\nExpenses on " + date + ":"
        );

        for (Expense expense : results) {
            System.out.println(expense);
        }

    } catch (Exception e) {

        System.out.println(
                "Invalid date. Please use YYYY-MM-DD."
        );
        }
}
// Display expenses sorted by amount
private static void sortByAmount() {

    System.out.println(
            "\n========== EXPENSES BY AMOUNT =========="
    );

    List<Expense> sortedExpenses =
            expenseManager.sortByAmountDescending();

    if (sortedExpenses.isEmpty()) {
        System.out.println("No expenses available.");
        return;
    }

    for (Expense expense : sortedExpenses) {
        System.out.println(expense);
    }
}
}