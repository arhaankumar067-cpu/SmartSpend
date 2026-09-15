package smartspend;

import java.time.LocalDate;

public class Expense {

    private int id;
    private double amount;
    private Category category;
    private String description;
    private LocalDate date;

    public Expense(int id, double amount, Category category,
                   String description, LocalDate date) {

        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Amount: ₹" + amount +
               " | Category: " + category +
               " | Description: " + description +
               " | Date: " + date;
    }
}