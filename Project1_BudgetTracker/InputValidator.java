import java.util.Scanner;


public class InputValidator
{
    /**
     * Gets a non-negative number from the user.
     *
     * @param scanner
     *            scanner
     * @param prompt
     *            message shown to user
     * @return valid number
     */
    public static double promptForPositiveDouble(
        Scanner scanner,
        String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (isNumeric(input))
            {
                double value = Double.parseDouble(input);

                if (value >= 0)
                {
                    return value;
                }

                System.out.println("Value cannot be negative.");
            }
            else
            {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    /**
     * Gets a tax rate from 0 to 1.
     *
     * @param scanner
     *            scanner
     * @param prompt
     *            message shown to user
     * @return valid tax rate
     */
    public static double promptForTaxRate(
        Scanner scanner,
        String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (isNumeric(input))
            {
                double rate = Double.parseDouble(input);

                if (rate >= 0 && rate <= 1)
                {
                    return rate;
                }

                System.out.println(
                    "Tax rate must be between 0 and 1.");
            }
            else
            {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    /**
     * Gets a non-blank String.
     *
     * @param scanner
     *            scanner
     * @param prompt
     *            message shown to user
     * @return valid String
     */
    public static String promptForNonBlankString(
        Scanner scanner,
        String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (!input.equals(""))
            {
                return input;
            }

            System.out.println("This field cannot be blank.");
        }
    }


    /**
     * Checks if text can be converted to a double.
     *
     * @param input
     *            text to check
     * @return true if numeric
     */
    public static boolean isNumeric(String input)
    {
        try
        {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException exception)
        {
            return false;
        }
    }
}
