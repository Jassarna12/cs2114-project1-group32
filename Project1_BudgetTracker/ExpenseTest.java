import student.TestCase;


/**
 * Tests the Expense class.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.22
 */
public class ExpenseTest extends TestCase
{
    private Expense expense;


    /**
     * Sets up an expense.
     */
    public void setUp()
    {
        expense = new Expense("Rent", 800.0, "fixed");
    }


    public void testGetCategory()
    {
        assertEquals("Rent", expense.getCategory());
    }


    public void testGetAmount()
    {
        assertEquals(800.0, expense.getAmount(), 0.01);
    }


    public void testGetType()
    {
        assertEquals("fixed", expense.getType());
    }


    public void testAddAmount()
    {
        expense.addAmount(200.0);

        assertEquals(1000.0, expense.getAmount(), 0.01);
    }


    public void testAddAmountZero()
    {
        expense.addAmount(0.0);

        assertEquals(800.0, expense.getAmount(), 0.01);
    }


    public void testToString()
    {
        assertEquals(
            "Rent - $800.0 - fixed",
            expense.toString());
    }
}
