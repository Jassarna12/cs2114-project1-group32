import student.TestCase;


/**
 * Tests the Budget class.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.22
 */
public class BudgetTest extends TestCase
{
    private Income income;
    private Budget budget;


    /**
     * Sets up the budget.
     */
    public void setUp()
    {
        income = new Income(80.0, 20.0, 0.15);
        budget = new Budget(income);
    }


    public void testAddExpense()
    {
        budget.addExpense("Rent", 800.0, "fixed");

        assertEquals(1, budget.getExpenses().size());
        assertEquals(800.0, budget.getTotalSpending(), 0.01);
    }


    public void testAddDuplicateExpense()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Rent", 200.0, "fixed");

        assertEquals(1, budget.getExpenses().size());
        assertEquals(1000.0, budget.getTotalSpending(), 0.01);
    }


    public void testEditExpense()
    {
        budget.addExpense(
            "Groceries",
            200.0,
            "variable");

        boolean result =
            budget.editExpense(
                "Groceries",
                "variable",
                300.0);

        assertTrue(result);
        assertEquals(300.0, budget.getTotalSpending(), 0.01);
    }


    public void testEditExpenseNotFound()
    {
        assertFalse(
            budget.editExpense(
                "Utilities",
                "fixed",
                100.0));
    }


    public void testRemoveExpense()
    {
        budget.addExpense("Car", 300.0, "fixed");

        assertTrue(
            budget.removeExpense("Car", "fixed"));

        assertEquals(0, budget.getExpenses().size());
    }


    public void testRemoveExpenseNotFound()
    {
        assertFalse(
            budget.removeExpense("Car", "fixed"));
    }


    public void testGetTotalSpending()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 200.0, "variable");

        assertEquals(
            1000.0,
            budget.getTotalSpending(),
            0.01);
    }


    public void testGetTotalFixed()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 200.0, "variable");

        assertEquals(
            800.0,
            budget.getTotalFixed(),
            0.01);
    }


    public void testGetTotalVariable()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 200.0, "variable");

        assertEquals(
            200.0,
            budget.getTotalVariable(),
            0.01);
    }


    public void testRemainingBalance()
    {
        budget.addExpense("Rent", 800.0, "fixed");
        budget.addExpense("Food", 100.0, "variable");

        assertEquals(
            460.0,
            budget.getRemainingBalance(),
            0.01);
    }


    public void testNegativeBalance()
    {
        budget.addExpense("Rent", 1500.0, "fixed");

        assertEquals(
            -140.0,
            budget.getRemainingBalance(),
            0.01);
    }


    public void testIsOverBudgetFalse()
    {
        budget.addExpense("Rent", 900.0, "fixed");

        assertFalse(budget.isOverBudget());
    }


    public void testIsOverBudgetTrue()
    {
        budget.addExpense("Rent", 1500.0, "fixed");

        assertTrue(budget.isOverBudget());
    }
}
