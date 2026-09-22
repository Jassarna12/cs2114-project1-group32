/**
 * Represents one expense in the budget tracker.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.21
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
     *            the expense category
     * @param amount
     *            the expense amount
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
     * Returns the category.
     *
     * @return the category
     */
    public String getCategory()
    {
        return category;
    }


    /**
     * Returns the amount.
     *
     * @return the amount
     */
    public double getAmount()
    {
        return amount;
    }


    /**
     * Returns the expense type.
     *
     * @return the type
     */
    public String getType()
    {
        return type;
    }


    /**
     * Adds more money to the expense.
     *
     * @param extra
     *            amount to add
     */
    public void addAmount(double extra)
    {
        amount = amount + extra;
    }
}
