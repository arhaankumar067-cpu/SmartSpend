package smartspend;

public class BudgetManager {

    private double monthlyBudget;

    // Set the monthly budget
    public void setBudget(double budget) {
        monthlyBudget = budget;
    }

    // Get the current budget
    public double getBudget() {
        return monthlyBudget;
    }

    // Calculate remaining budget
    public double getRemainingBudget(double totalSpent) {
        return monthlyBudget - totalSpent;
    }

    // Calculate percentage of budget used
    public double getBudgetPercentage(double totalSpent) {

        if (monthlyBudget == 0) {
            return 0;
        }

        return (totalSpent / monthlyBudget) * 100;
    }

    // Check the current budget status
    public void checkBudgetStatus(double totalSpent) {

        if (monthlyBudget == 0) {
            System.out.println("Please set a monthly budget first.");
            return;
        }

        double percentage = getBudgetPercentage(totalSpent);
        double remaining = getRemainingBudget(totalSpent);

        System.out.println("\n========== BUDGET STATUS ==========");
        System.out.printf("Monthly Budget : ₹%.2f%n", monthlyBudget);
        System.out.printf("Total Spent    : ₹%.2f%n", totalSpent);
        System.out.printf("Remaining      : ₹%.2f%n", remaining);
        System.out.printf("Budget Used    : %.2f%%%n", percentage);

        if (percentage >= 100) {
            System.out.println("WARNING: You have exceeded your budget!");
        } 
        else if (percentage >= 80) {
            System.out.println("WARNING: You have used more than 80% of your budget.");
        } 
        else {
            System.out.println("Status: You are within your budget.");
        }

        System.out.println("===================================");
    }
}