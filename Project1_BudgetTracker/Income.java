public class Income
{
    private double hoursWorked;
    private double hourlyWage;
    private double taxRate;


    /**
     * Creates a new Income object.
     *
     * @param hoursWorked
     *            hours worked
     * @param hourlyWage
     *            hourly wage
     * @param taxRate
     *            tax rate
     */
    public Income(double hoursWorked, double hourlyWage, double taxRate)
    {
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
        this.taxRate = taxRate;
    }


    /**
     * Calculates gross income.
     *
     * @return gross income
     */
    public double calculateGrossIncome()
    {
        return hoursWorked * hourlyWage;
    }


    /**
     * Calculates net income.
     *
     * @return net income
     */
    public double calculateNetIncome()
    {
        double grossIncome = calculateGrossIncome();

        return grossIncome - (grossIncome * taxRate);
    }


    /**
     * Gets hours worked.
     *
     * @return hours worked
     */
    public double getHoursWorked()
    {
        return hoursWorked;
    }


    /**
     * Gets hourly wage.
     *
     * @return hourly wage
     */
    public double getHourlyWage()
    {
        return hourlyWage;
    }


    /**
     * Gets tax rate.
     *
     * @return tax rate
     */
    public double getTaxRate()
    {
        return taxRate;
    }
}
