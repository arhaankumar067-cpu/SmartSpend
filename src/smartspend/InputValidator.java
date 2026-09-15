package smartspend;

public class InputValidator {

    // Validate expense amount
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    // Validate budget
    public static boolean isValidBudget(double budget) {
        return budget > 0;
    }

    // Validate expense description
    public static boolean isValidDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }

    // Validate menu choice
    public static boolean isValidMenuChoice(int choice) {
        return choice >= 1 && choice <= 10;
    }

    // Validate expense ID
    public static boolean isValidId(int id) {
        return id > 0;
    }
}