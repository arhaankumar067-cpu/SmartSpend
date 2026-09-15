package smartspend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    // Display complete spending report
    public void generateReport(List<Expense> expenses,
                               double monthlyBudget) {

        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses available for the report.");
            return;
        }

        double total = calculateTotal(expenses);
        double average = total / expenses.size();

        System.out.println("\n========================================");
        System.out.println("           SPENDING REPORT");
        System.out.println("========================================");

        System.out.printf("Monthly Budget : ₹%.2f%n", monthlyBudget);
        System.out.printf("Total Spent    : ₹%.2f%n", total);
        System.out.printf("Remaining      : ₹%.2f%n",
                monthlyBudget - total);
        System.out.printf("Average Expense: ₹%.2f%n", average);

        Expense highest = getHighestExpense(expenses);

        if (highest != null) {
            System.out.printf("Highest Expense : ₹%.2f (%s)%n",
                    highest.getAmount(),
                    highest.getDescription());
        }

        System.out.println("\n------ Category-wise Spending ------");

        Map<Category, Double> categoryTotals =
                calculateCategoryTotals(expenses);

        for (Map.Entry<Category, Double> entry :
                categoryTotals.entrySet()) {

            System.out.printf("%-15s ₹%.2f%n",
                    entry.getKey(),
                    entry.getValue());
        }

        System.out.println("========================================");
    }

    // Calculate total spending
    public double calculateTotal(List<Expense> expenses) {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    // Calculate category-wise spending
    public Map<Category, Double> calculateCategoryTotals(
            List<Expense> expenses) {

        Map<Category, Double> totals = new HashMap<>();

        for (Expense expense : expenses) {

            Category category = expense.getCategory();

            totals.put(
                    category,
                    totals.getOrDefault(category, 0.0)
                            + expense.getAmount()
            );
        }

        return totals;
    }

    // Find highest expense
    public Expense getHighestExpense(List<Expense> expenses) {

        if (expenses.isEmpty()) {
            return null;
        }

        Expense highest = expenses.get(0);

        for (Expense expense : expenses) {

            if (expense.getAmount() > highest.getAmount()) {
                highest = expense;
            }
        }

        return highest;
    }
}