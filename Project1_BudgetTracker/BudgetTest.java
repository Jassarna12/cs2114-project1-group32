import student.TestCase;

/**
 * Tests the Budget class.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.21
 */
public class BudgetTest extends TestCase
{
    private Income income;
    private Budget budget;


    /**
     * Sets up an income and budget before each test.
     */
    public void setUp()
    {
        income = new Income(80.0, 20.0, 0.15);
        budget = new Budget(income);
    }


    /**
     * Tests adding a new expense.
     */
    public void testAddExpense()
    {
        budget.addExpense("Rent", 800.0, "fixed");

        assertEquals(1, budget.getExpenses().size());
        assertEquals(800.0, budget.getTotalSpending(), 0.01);
    }


    /**
     * Tests adding the same expense twice.
     */
    public void testAddDuplicateExpense()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Rent", 200.0, "fixed");

        assertEquals(1, budget.getExpenses().size());
        assertEquals(1000.0, budget.getTotalSpending(), 0.01);
    }


    /**
     * Tests editing an existing expense.
     */
    public void testEditExpense()
    {
        budget.addExpense("Groceries", 200.0, "variable");

        boolean result =
            budget.editExpense("Groceries", "variable", 300.0);

        assertTrue(result);
        assertEquals(300.0, budget.getTotalSpending(), 0.01);
    }


    /**
     * Tests editing an expense that does not exist.
     */
    public void testEditExpenseNotFound()
    {
        boolean result =
            budget.editExpense("Utilities", "fixed", 100.0);

        assertFalse(result);
    }


    /**
     * Tests removing an existing expense.
     */
    public void testRemoveExpense()
    {
        budget.addExpense("Car", 300.0, "fixed");

        boolean result =
            budget.removeExpense("Car", "fixed");

        assertTrue(result);
        assertEquals(0, budget.getExpenses().size());
    }


    /**
     * Tests removing an expense that does not exist.
     */
    public void testRemoveExpenseNotFound()
    {
        boolean result =
            budget.removeExpense("Car", "fixed");

        assertFalse(result);
    }


    /**
     * Tests total spending.
     */
    public void testGetTotalSpending()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 200.0, "variable");

        assertEquals(1000.0, budget.getTotalSpending(), 0.01);
    }


    /**
     * Tests total fixed spending.
     */
    public void testGetTotalFixed()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 200.0, "variable");

        assertEquals(800.0, budget.getTotalFixed(), 0.01);
    }


    /**
     * Tests total variable spending.
     */
    public void testGetTotalVariable()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 200.0, "variable");

        assertEquals(200.0, budget.getTotalVariable(), 0.01);
    }


    /**
     * Tests remaining balance.
     */
    public void testGetRemainingBalance()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 100.0, "variable");

        assertEquals(460.0, budget.getRemainingBalance(), 0.01);
    }


    /**
     * Tests negative remaining balance.
     */
    public void testNegativeRemainingBalance()
    {
        budget.addExpense("Rent", 1500.0, "fixed");

        assertEquals(-140.0, budget.getRemainingBalance(), 0.01);
    }


    /**
     * Tests when the user is not over budget.
     */
    public void testIsOverBudgetFalse()
    {
        budget.addExpense("Rent", 900.0, "fixed");

        assertFalse(budget.isOverBudget());
    }


    /**
     * Tests when the user is over budget.
     */
    public void testIsOverBudgetTrue()
    {
        budget.addExpense("Rent", 1500.0, "fixed");

        assertTrue(budget.isOverBudget());
    }
}
