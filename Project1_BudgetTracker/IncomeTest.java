public class IncomeTest extends TestCase
{
    private Income income;


    /**
     * Sets up an Income object before each test.
     */
    public void setUp()
    {
        income = new Income(80, 20, 0.15);
    }


    /**
     * Tests calculating gross income.
     */
    public void testCalculateGrossIncome()
    {
        assertEquals(1600.0, income.calculateGrossIncome(), 0.001);
    }


    /**
     * Tests gross income when no hours were worked.
     */
    public void testCalculateGrossIncomeZeroHours()
    {
        Income zeroIncome = new Income(0, 20, 0.15);

        assertEquals(0.0, zeroIncome.calculateGrossIncome(), 0.001);
    }


    /**
     * Tests calculating net income.
     */
    public void testCalculateNetIncome()
    {
        assertEquals(1360.0, income.calculateNetIncome(), 0.001);
    }


    /**
     * Tests net income with a tax rate of zero.
     */
    public void testCalculateNetIncomeNoTax()
    {
        Income noTax = new Income(80, 20, 0.0);

        assertEquals(1600.0, noTax.calculateNetIncome(), 0.001);
    }


    /**
     * Tests getting the hours worked.
     */
    public void testGetHoursWorked()
    {
        assertEquals(80.0, income.getHoursWorked(), 0.001);
    }


    /**
     * Tests getting the hourly wage.
     */
    public void testGetHourlyWage()
    {
        assertEquals(20.0, income.getHourlyWage(), 0.001);
    }


    /**
     * Tests getting the tax rate.
     */
    public void testGetTaxRate()
    {
        assertEquals(0.15, income.getTaxRate(), 0.001);
    }
}
