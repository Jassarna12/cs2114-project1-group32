import java.util.Scanner;
import student.TestCase;

public class InputValidatorTest extends TestCase
{
    public void testIsNumericTrue()
    {
        assertTrue(InputValidator.isNumeric("500"));
    }


    public void testIsNumericFalse()
    {
        assertFalse(InputValidator.isNumeric("hello"));
    }


    public void testPositiveDouble()
    {
        Scanner scanner = new Scanner("500\n");

        double result =
            InputValidator.promptForPositiveDouble(
                scanner,
                "");

        assertEquals(500.0, result, 0.01);
    }


    public void testPositiveDoubleBadThenGood()
    {
        Scanner scanner =
            new Scanner("hello\n-5\n200\n");

        double result =
            InputValidator.promptForPositiveDouble(
                scanner,
                "");

        assertEquals(200.0, result, 0.01);
    }


    public void testTaxRate()
    {
        Scanner scanner = new Scanner("0.15\n");

        double result =
            InputValidator.promptForTaxRate(
                scanner,
                "");

        assertEquals(0.15, result, 0.01);
    }


    public void testTaxRateBadThenGood()
    {
        Scanner scanner =
            new Scanner("5\n-1\n0.20\n");

        double result =
            InputValidator.promptForTaxRate(
                scanner,
                "");

        assertEquals(0.20, result, 0.01);
    }


    public void testNonBlankString()
    {
        Scanner scanner =
            new Scanner("Rent\n");

        String result =
            InputValidator.promptForNonBlankString(
                scanner,
                "");

        assertEquals("Rent", result);
    }


    public void testBlankThenValidString()
    {
        Scanner scanner =
            new Scanner("\nGroceries\n");

        String result =
            InputValidator.promptForNonBlankString(
                scanner,
                "");

        assertEquals("Groceries", result);
    }
}
