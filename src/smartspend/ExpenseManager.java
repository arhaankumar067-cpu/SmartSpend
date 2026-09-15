package smartspend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExpenseManager {

    private ArrayList<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    // Add a new expense
    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    // Display all expenses
    public void viewExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n========== ALL EXPENSES ==========");

        for (Expense expense : expenses) {
            System.out.println(expense);
        }

        System.out.println("==================================");
    }

    // Delete expense using ID
    public boolean deleteExpense(int id) {

        for (Expense expense : expenses) {

            if (expense.getId() == id) {
                expenses.remove(expense);
                return true;
            }
        }

        return false;
    }

    // Find an expense using ID
    public Expense findExpenseById(int id) {

        for (Expense expense : expenses) {

            if (expense.getId() == id) {
                return expense;
            }
        }

        return null;
    }

    // Search expenses by category
    public List<Expense> searchByCategory(Category category) {

        ArrayList<Expense> result = new ArrayList<>();

        for (Expense expense : expenses) {

            if (expense.getCategory() == category) {
                result.add(expense);
            }
        }

        return result;
    }

    // Get all expenses
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    // Calculate total spending
    public double calculateTotal() {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    // Find highest expense
    public Expense getHighestExpense() {

        if (expenses.isEmpty()) {
            return null;
        }

        return expenses.stream()
                .max(Comparator.comparingDouble(Expense::getAmount))
                .orElse(null);
    }
    public void addExpenseSilently(Expense expense) {
    expenses.add(expense);
    }
    // Search expenses by date
    public List<Expense> searchByDate(LocalDate date) {

        ArrayList<Expense> result = new ArrayList<>();

        for (Expense expense : expenses) {

            if (expense.getDate().equals(date)) {
            result.add(expense);
            }
        }

        return result;
    }
// Sort expenses by amount from highest to lowest
public List<Expense> sortByAmountDescending() {

    ArrayList<Expense> sortedExpenses =
            new ArrayList<>(expenses);

    sortedExpenses.sort(
            Comparator.comparingDouble(
                    Expense::getAmount
            ).reversed()
    );

    return sortedExpenses;
}
}