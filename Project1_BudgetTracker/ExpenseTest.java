import student.TestCase;
/**
 * Tests the Expense class.
 *
 * @author Jasjeet Sarna
 * @version 2026.09.21
 */
public class ExpenseTest extends TestCase
{
    private Expense expense;


    /**
     * Sets up an expense before each test.
     */
    public void setUp()
    {
        expense = new Expense("Rent", 800.0, "fixed");
    }


    /**
     * Tests getCategory.
     */
    public void testGetCategory()
    {
        assertEquals("Rent", expense.getCategory());
    }


    /**
     * Tests getAmount.
     */
    public void testGetAmount()
    {
        assertEquals(800.0, expense.getAmount(), 0.01);
    }


    /**
     * Tests getType.
     */
    public void testGetType()
    {
        assertEquals("fixed", expense.getType());
    }


    /**
     * Tests adding to an expense amount.
     */
    public void testAddAmount()
    {
        expense.addAmount(200.0);

        assertEquals(1000.0, expense.getAmount(), 0.01);
    }


    /**
     * Tests adding zero.
     */
    public void testAddAmountZero()
    {
        expense.addAmount(0.0);

        assertEquals(800.0, expense.getAmount(), 0.01);
    }
}
