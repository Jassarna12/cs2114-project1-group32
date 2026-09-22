import java.util.List;
import java.util.Scanner;

/**
 * Owned by Shalin.
 *
 * Single responsibility: handle interaction with the user. Controls the
 * order the program runs in — asks for income info, lets the user add,
 * edit, or remove expenses, and displays the final budget summary. It
 * does not perform budget calculations itself; it delegates to Income,
 * Budget, and InputValidator.
 */
public class BudgetTrackerApp {
    private Scanner scanner;
    private Income income;
    private Budget budget;

    public BudgetTrackerApp() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BudgetTrackerApp app = new BudgetTrackerApp();
        app.run();
    }

    /** Runs the console menu loop until the user chooses to finalize and exit. */
    public void run() {
        System.out.println("=== Budget Tracker ===");
        setupIncome();

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu");
            System.out.println("1) Manage expenses");
            System.out.println("2) View budget summary");
            System.out.println("3) Finalize and exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    manageExpenses();
                    break;
                case "2":
                    displaySummary();
                    break;
                case "3":
                    displaySummary();
                    System.out.println("\nFinal budget saved. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Please enter 1, 2, or 3.");
            }
        }
    }

    /** Prompts for hours, wage, and tax rate (via InputValidator) and creates the Income object. */
    public void setupIncome() {
        System.out.println("\nLet's set up your monthly income.");
        double hours = InputValidator.promptForPositiveDouble(scanner, "Hours worked this month: ");
        double wage = InputValidator.promptForPositiveDouble(scanner, "Hourly wage ($): ");
        double taxRate = InputValidator.promptForTaxRate(scanner, "Estimated tax rate (0 to 1, e.g. 0.15 for 15%): ");

        income = new Income(hours, wage, taxRate);
        budget = new Budget(income);

        System.out.printf("Gross monthly income: $%,.2f%n", income.calculateGrossIncome());
        System.out.printf("Estimated net monthly income: $%,.2f%n", income.calculateNetIncome());
    }

    /** Loops the add/edit/remove expense menu until the user is done. */
    public void manageExpenses() {
        boolean managing = true;
        while (managing) {
            System.out.println("\nManage Expenses");
            System.out.println("1) Add expense");
            System.out.println("2) Edit expense");
            System.out.println("3) Remove expense");
            System.out.println("4) View expenses");
            System.out.println("5) Back to main menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addExpenseFlow();
                    break;
                case "2":
                    editExpenseFlow();
                    break;
                case "3":
                    removeExpenseFlow();
                    break;
                case "4":
                    viewExpenses();
                    break;
                case "5":
                    managing = false;
                    break;
                default:
                    System.out.println("Please enter a number from 1 to 5.");
            }
        }
    }

    /** Prints total spending, fixed vs. variable breakdown, remaining balance, and an over-budget warning if applicable. */
    public void displaySummary() {
        System.out.println("\n=== Budget Summary ===");
        double total = budget.getTotalSpending();
        double fixed = budget.getTotalFixed();
        double variable = budget.getTotalVariable();

        System.out.printf("Total spending:   $%,.2f%n", total);
        System.out.printf("  Fixed:          $%,.2f (%s)%n", fixed, percentOf(fixed, total));
        System.out.printf("  Variable:       $%,.2f (%s)%n", variable, percentOf(variable, total));
        System.out.printf("Remaining balance: $%,.2f%n", budget.getRemainingBalance());

        if (budget.isOverBudget()) {
            System.out.println("Warning: your spending exceeds your net income this month.");
        }
    }

    // ---- private helpers (implementation detail, not part of the class contract) ----

    private void addExpenseFlow() {
        String category = InputValidator.promptForNonBlankString(scanner, "Category (e.g. Rent, Groceries): ");
        double amount = InputValidator.promptForPositiveDouble(scanner, "Amount ($): ");
        ExpenseType type = promptForExpenseType();
        budget.addExpense(category, amount, type);
        System.out.println("Added/updated: " + category + " ($" + amount + ", " + type + ")");
    }

    private void editExpenseFlow() {
        String category = InputValidator.promptForNonBlankString(scanner, "Category to edit: ");
        ExpenseType type = promptForExpenseType();
        double newAmount = InputValidator.promptForPositiveDouble(scanner, "New amount ($): ");
        boolean found = budget.editExpense(category, type, newAmount);
        System.out.println(found ? "Updated." : "No matching expense found.");
    }

    private void removeExpenseFlow() {
        String category = InputValidator.promptForNonBlankString(scanner, "Category to remove: ");
        ExpenseType type = promptForExpenseType();
        boolean removed = budget.removeExpense(category, type);
        System.out.println(removed ? "Removed." : "No matching expense found.");
    }

    private void viewExpenses() {
        List<Expense> expenses = budget.getExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        System.out.println("\nCurrent expenses:");
        for (Expense e : expenses) {
            System.out.println("  " + e);
        }
    }

    private ExpenseType promptForExpenseType() {
        while (true) {
            System.out.print("Type — 1) Fixed  2) Variable: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) return ExpenseType.FIXED;
            if (choice.equals("2")) return ExpenseType.VARIABLE;
            System.out.println("Please enter 1 or 2.");
        }
    }

    /** Avoids divide-by-zero when total spending is zero (e.g. zero income, no expenses yet). */
    private String percentOf(double part, double total) {
        if (total == 0) {
            return "N/A";
        }
        return String.format("%.0f%%", (part / total) * 100);
    }
}