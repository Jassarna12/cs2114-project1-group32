/**
 * Represents one expense in the budget tracker.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.22
 */
public class Expense
{
    private String category;
    private double amount;
    private String type;


    /**
     * Creates a new expense.
     *
     * @param category
     *            expense category
     * @param amount
     *            expense amount
     * @param type
     *            fixed or variable
     */
    public Expense(String category, double amount, String type)
    {
        this.category = category;
        this.amount = amount;
        this.type = type;
    }


    /**
     * Gets the category.
     *
     * @return category
     */
    public String getCategory()
    {
        return category;
    }


    /**
     * Gets the amount.
     *
     * @return amount
     */
    public double getAmount()
    {
        return amount;
    }


    /**
     * Gets the expense type.
     *
     * @return type
     */
    public String getType()
    {
        return type;
    }


    /**
     * Adds money to the expense.
     *
     * @param extra
     *            amount to add
     */
    public void addAmount(double extra)
    {
        amount = amount + extra;
    }


    /**
     * Returns expense information as text.
     *
     * @return expense information
     */
    public String toString()
    {
        return category + " - $" + amount + " - " + type;
    }
}
