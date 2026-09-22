 public Income(double hoursWorked, double hourlyWage, double taxRate)
    {
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
        this.taxRate = taxRate;
    }


    /**
     * Calculates gross income before taxes
     *
     * @return gross income
     */
    public double calculateGrossIncome()
    {
        return hoursWorked * hourlyWage;
    }


    /**
     * Calculates income after estimated taxes.
     *
     * @return net income
     */
    public double calculateNetIncome()
    {
        double grossIncome = calculateGrossIncome();
        return grossIncome - (grossIncome * taxRate);
    }


    /**
     * Gets the hours worked
     *
     * @return hours worked
     */
    public double getHoursWorked()
    {
        return hoursWorked;
    }


    /**
     * Gets the hourly wage
     *
     * @return hourly wage
     */
    public double getHourlyWage()
    {
        return hourlyWage;
    }


    /**
     * Gets the tax rate
     *
     * @return tax rate
     */
    public double getTaxRate()
    {
        return taxRate;
    }
}
