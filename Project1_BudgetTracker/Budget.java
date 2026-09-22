import java.util.ArrayList;
import java.util.List;


/**
 * Stores expenses and performs budget calculations.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.21
 */
public class Budget
{
    private Income income;
    private ArrayList<Expense> expenses;


    /**
     * Creates a new budget.
     *
     * @param income
     *            the user's income
     */
    public Budget(Income income)
    {
        this.income = income;
        expenses = new ArrayList<Expense>();
    }


    /**
     * Adds an expense.
     * If the same category and type already exist,
     * the amounts are combined.
     *
     * @param category
     *            expense category
     * @param amount
     *            expense amount
     * @param type
     *            fixed or variable
     */
    public void addExpense(String category, double amount, String type)
    {
        for (Expense expense : expenses)
        {
            if (expense.getCategory().equals(category)
                && expense.getType().equals(type))
            {
                expense.addAmount(amount);
                return;
            }
        }

        expenses.add(new Expense(category, amount, type));
    }


    /**
     * Edits an existing expense.
     *
     * @param category
     *            expense category
     * @param type
     *            expense type
     * @param newAmount
     *            new expense amount
     * @return true if the expense was found
     */
    public boolean editExpense(
        String category,
        String type,
        double newAmount)
    {
        for (int i = 0; i < expenses.size(); i++)
        {
            Expense expense = expenses.get(i);

            if (expense.getCategory().equals(category)
                && expense.getType().equals(type))
            {
                Expense updatedExpense =
                    new Expense(category, newAmount, type);

                expenses.set(i, updatedExpense);

                return true;
            }
        }

        return false;
    }


    /**
     * Removes an expense.
     *
     * @param category
     *            expense category
     * @param type
     *            expense type
     * @return true if the expense was removed
     */
    public boolean removeExpense(String category, String type)
    {
        for (int i = 0; i < expenses.size(); i++)
        {
            Expense expense = expenses.get(i);

            if (expense.getCategory().equals(category)
                && expense.getType().equals(type))
            {
                expenses.remove(i);
                return true;
            }
        }

        return false;
    }


    /**
     * Calculates total spending.
     *
     * @return total spending
     */
    public double getTotalSpending()
    {
        double total = 0;

        for (Expense expense : expenses)
        {
            total = total + expense.getAmount();
        }

        return total;
    }


    /**
     * Calculates total fixed spending.
     *
     * @return total fixed spending
     */
    public double getTotalFixed()
    {
        double total = 0;

        for (Expense expense : expenses)
        {
            if (expense.getType().equals("fixed"))
            {
                total = total + expense.getAmount();
            }
        }

        return total;
    }


    /**
     * Calculates total variable spending.
     *
     * @return total variable spending
     */
    public double getTotalVariable()
    {
        double total = 0;

        for (Expense expense : expenses)
        {
            if (expense.getType().equals("variable"))
            {
                total = total + expense.getAmount();
            }
        }

        return total;
    }


    /**
     * Calculates the remaining balance.
     *
     * @return remaining balance
     */
    public double getRemainingBalance()
    {
        return income.calculateNetIncome() - getTotalSpending();
    }


    /**
     * Checks if spending is greater than income.
     *
     * @return true if over budget
     */
    public boolean isOverBudget()
    {
        return getTotalSpending() > income.calculateNetIncome();
    }


    /**
     * Returns the list of expenses.
     *
     * @return expenses
     */
    public List<Expense> getExpenses()
    {
        return expenses;
    }
}
