package bank;

public class CheckingAccount extends Account{
    private double overdraftAmount;

    public CheckingAccount(double initBalance, double overdraftAmount) {
        super(initBalance);
        this.overdraftAmount = overdraftAmount;
    }

    public CheckingAccount(double initBalance) {
        super(initBalance);
    }

    public boolean withdraw(double amt) {
        boolean result = true;

        if ( balance < amt ) {

            // Not enough balance to cover the amount requested to withdraw
            // Check if there is enough in the overdraft protection (if any)
            double overdraftNeeded = amt - balance;
            if ( overdraftAmount < overdraftNeeded ) {

                // No overdraft protection or not enough to cover the amount needed
                result = false;
            } else {

                // Yes, there is overdraft protection and enough to cover the amount
                balance = 0.0;
                overdraftAmount -= overdraftNeeded;
            }

        } else {

            // Yes, there is enough balance to cover the amount
            // Proceed as usual
            balance = balance - amt;
        }

        return result;
    }
}
