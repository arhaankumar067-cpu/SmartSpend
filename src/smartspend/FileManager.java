package smartspend;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class FileManager {

    private static final String FILE_PATH = "data/expenses.csv";

    // Save all expenses to CSV file
    public void saveExpenses(ArrayList<Expense> expenses) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Expense expense : expenses) {

                writer.write(
                        expense.getId() + "," +
                        expense.getAmount() + "," +
                        expense.getCategory() + "," +
                        expense.getDescription() + "," +
                        expense.getDate()
                );

                writer.newLine();
            }

            System.out.println("Expenses saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving expenses: " + e.getMessage()
            );
        }
    }

    // Load expenses from CSV file
    public ArrayList<Expense> loadExpenses() {

        ArrayList<Expense> expenses = new ArrayList<>();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 5) {

                    int id = Integer.parseInt(data[0]);
                    double amount = Double.parseDouble(data[1]);
                    Category category =
                            Category.valueOf(data[2]);
                    String description = data[3];
                    LocalDate date =
                            LocalDate.parse(data[4]);

                    Expense expense = new Expense(
                            id,
                            amount,
                            category,
                            description,
                            date
                    );

                    expenses.add(expense);
                }
            }

        } catch (IOException | IllegalArgumentException e) {

            System.out.println(
                    "Error while loading expenses: "
                            + e.getMessage()
            );
        }

        return expenses;
    }
// Save monthly budget
public void saveBudget(double budget) {

    try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter("data/budget.txt"))) {

        writer.write(String.valueOf(budget));

    } catch (IOException e) {

        System.out.println(
                "Error while saving budget: " + e.getMessage()
        );
    }
}

// Load monthly budget
public double loadBudget() {

    File file = new File("data/budget.txt");

    if (!file.exists()) {
        return 0;
    }

    try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

        String value = reader.readLine();

        if (value != null) {
            return Double.parseDouble(value);
        }

    } catch (IOException | NumberFormatException e) {

        System.out.println(
                "Error while loading budget: " + e.getMessage()
        );
    }

    return 0;
}
}