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
public class BudgetTrackerApp
{
    private Scanner scanner;
    private Income income;
    private Budget budget;


    /**
     * Creates the app.
     */
    public BudgetTrackerApp()
    {
        scanner = new Scanner(System.in);
    }


    /**
     * Starts the program.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args)
    {
        BudgetTrackerApp app = new BudgetTrackerApp();
        app.run();
    }


    /**
     * Runs the main menu.
     */
    public void run()
    {
        System.out.println("=== Budget Tracker ===");

        setupIncome();

        boolean running = true;

        while (running)
        {
            System.out.println();
            System.out.println("Main Menu");
            System.out.println("1) Manage expenses");
            System.out.println("2) View budget summary");
            System.out.println("3) Finalize and exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1"))
            {
                manageExpenses();
            }
            else if (choice.equals("2"))
            {
                displaySummary();
            }
            else if (choice.equals("3"))
            {
                displaySummary();

                System.out.println();
                System.out.println("Goodbye!");

                running = false;
            }
            else
            {
                System.out.println("Please enter 1, 2, or 3.");
            }
        }
    }


    /**
     * Sets up income information.
     */
    public void setupIncome()
    {
        System.out.println();
        System.out.println("Enter your monthly income information.");

        double hours =
            InputValidator.promptForPositiveDouble(
                scanner,
                "Hours worked this month: ");

        double wage =
            InputValidator.promptForPositiveDouble(
                scanner,
                "Hourly wage: ");

        double taxRate =
            InputValidator.promptForTaxRate(
                scanner,
                "Tax rate (example: 0.15): ");

        income = new Income(hours, wage, taxRate);
        budget = new Budget(income);

        System.out.println(
            "Gross income: $" + income.calculateGrossIncome());

        System.out.println(
            "Net income: $" + income.calculateNetIncome());
    }


    /**
     * Runs the expense menu.
     */
    public void manageExpenses()
    {
        boolean managing = true;

        while (managing)
        {
            System.out.println();
            System.out.println("Manage Expenses");
            System.out.println("1) Add expense");
            System.out.println("2) Edit expense");
            System.out.println("3) Remove expense");
            System.out.println("4) View expenses");
            System.out.println("5) Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1"))
            {
                addExpenseFlow();
            }
            else if (choice.equals("2"))
            {
                editExpenseFlow();
            }
            else if (choice.equals("3"))
            {
                removeExpenseFlow();
            }
            else if (choice.equals("4"))
            {
                viewExpenses();
            }
            else if (choice.equals("5"))
            {
                managing = false;
            }
            else
            {
                System.out.println(
                    "Please enter a number from 1 to 5.");
            }
        }
    }


    /**
     * Displays the budget summary.
     */
    public void displaySummary()
    {
        System.out.println();
        System.out.println("=== Budget Summary ===");

        System.out.println(
            "Total spending: $" + budget.getTotalSpending());

        System.out.println(
            "Fixed spending: $" + budget.getTotalFixed());

        System.out.println(
            "Variable spending: $" + budget.getTotalVariable());

        System.out.println(
            "Remaining balance: $" + budget.getRemainingBalance());

        if (budget.isOverBudget())
        {
            System.out.println(
                "Warning: your expenses exceed your income.");
        }
    }


    /**
     * Adds an expense.
     */
    private void addExpenseFlow()
    {
        String category = promptForCategory();

        double amount =
            InputValidator.promptForPositiveDouble(
                scanner,
                "Amount: ");

        String type = promptForExpenseType();

        budget.addExpense(category, amount, type);

        System.out.println("Expense added.");
    }


    /**
     * Edits an expense.
     */
    private void editExpenseFlow()
    {
        List<Expense> expenses = budget.getExpenses();

        if (expenses.isEmpty())
        {
            System.out.println("No expenses to edit.");
            return;
        }

        System.out.println();
        System.out.println("Choose an expense to edit:");

        for (int i = 0; i < expenses.size(); i++)
        {
            System.out.println(
                (i + 1) + ") " + expenses.get(i));
        }

        System.out.print("Choose an option: ");

        String input = scanner.nextLine().trim();

        if (!InputValidator.isNumeric(input))
        {
            System.out.println("Please enter a valid number.");
            return;
        }

        int choice = Integer.parseInt(input);

        if (choice < 1 || choice > expenses.size())
        {
            System.out.println("Invalid option.");
            return;
        }

        Expense selectedExpense =
            expenses.get(choice - 1);

        double newAmount =
            InputValidator.promptForPositiveDouble(
                scanner,
                "New amount: ");

        boolean found =
            budget.editExpense(
                selectedExpense.getCategory(),
                selectedExpense.getType(),
                newAmount);

        if (found)
        {
            System.out.println("Expense updated.");
        }
        else
        {
            System.out.println("Expense not found.");
        }
    }


    /**
     * Removes an expense.
     */
    private void removeExpenseFlow()
    {
        List<Expense> expenses = budget.getExpenses();

        if (expenses.isEmpty())
        {
            System.out.println("No expenses to remove.");
            return;
        }

        System.out.println();
        System.out.println("Choose an expense to remove:");

        for (int i = 0; i < expenses.size(); i++)
        {
            System.out.println(
                (i + 1) + ") " + expenses.get(i));
        }

        System.out.print("Choose an option: ");

        String input = scanner.nextLine().trim();

        if (!InputValidator.isNumeric(input))
        {
            System.out.println("Please enter a valid number.");
            return;
        }

        int choice = Integer.parseInt(input);

        if (choice < 1 || choice > expenses.size())
        {
            System.out.println("Invalid option.");
            return;
        }

        Expense selectedExpense =
            expenses.get(choice - 1);

        boolean removed =
            budget.removeExpense(
                selectedExpense.getCategory(),
                selectedExpense.getType());

        if (removed)
        {
            System.out.println("Expense removed.");
        }
        else
        {
            System.out.println("Expense not found.");
        }
    }


    /**
     * Displays current expenses.
     */
    private void viewExpenses()
    {
        List<Expense> expenses = budget.getExpenses();

        if (expenses.isEmpty())
        {
            System.out.println("No expenses yet.");
            return;
        }

        System.out.println();
        System.out.println("Current Expenses:");

        for (Expense expense : expenses)
        {
            System.out.println(expense);
        }
    }


    /**
     * Gets fixed or variable expense type.
     *
     * @return expense type
     */
    private String promptForExpenseType()
    {
        while (true)
        {
            System.out.println("1) Fixed");
            System.out.println("2) Variable");
            System.out.print("Choose type: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1"))
            {
                return "fixed";
            }
            else if (choice.equals("2"))
            {
                return "variable";
            }

            System.out.println("Please enter 1 or 2.");
        }
    }


    /**
     * Displays category choices.
     *
     * @return selected category
     */
    private String promptForCategory()
    {
        while (true)
        {
            System.out.println();
            System.out.println("Choose a category:");
            System.out.println("1) Rent");
            System.out.println("2) Utilities");
            System.out.println("3) Car");
            System.out.println("4) Groceries");
            System.out.println("5) Entertainment");
            System.out.println("6) Other");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1"))
            {
                return "Rent";
            }
            else if (choice.equals("2"))
            {
                return "Utilities";
            }
            else if (choice.equals("3"))
            {
                return "Car";
            }
            else if (choice.equals("4"))
            {
                return "Groceries";
            }
            else if (choice.equals("5"))
            {
                return "Entertainment";
            }
            else if (choice.equals("6"))
            {
                return "Other";
            }
            else
            {
                System.out.println(
                    "Please enter a number from 1 to 6.");
            }
        }
    }
}
