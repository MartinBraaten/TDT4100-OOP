package oving2;

public class Account {
    private double balance;
    private double interestRate;

    public void deposit(double Deposit) {
        if (Deposit >= 0) {
            this.balance += Deposit;
        } else {
            throw new IllegalArgumentException("Deposit needs to be a positive value.");
        }
    }
    public void withdraw(double withdrawamount) {
        if (withdrawamount >= 0) {
            if (balance - withdrawamount < 0) {
                throw new IllegalArgumentException("Cannot withdraw more money than the account has.");
            } else {
                this.balance -= withdrawamount;
            }
            
        } else {
            throw new IllegalArgumentException("Negative withdraw value is not possible.");
        }
    }

    public void addInterest() {
        this.balance += interestRate/100 * balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double newInterestRate) {
        if (newInterestRate >= 0) {
            this.interestRate = newInterestRate;
        } else {
            throw new IllegalArgumentException("Not valid interestrate. Cannot be a negative value.");
        }
        
    }

    public Account(double Balance, double interestRate) {
        if (Balance >= 0 & interestRate >= 0) {
            this.balance = Balance;
            this.interestRate = interestRate;
        } else {
            throw new IllegalArgumentException("Not valid balance and interestrate. Cannot be negative values.");
        }

    }

    @Override
    public String toString() {
        return "balance =" + balance +", interestrate=" + interestRate;
    }

    public static void main(String[] args) {
        Account a1 = new Account(1,1);
        System.out.println(a1);
        a1.setInterestRate(5);
        a1.deposit(20);
        System.out.println(a1);
        a1.addInterest();
        System.out.println(a1);

    }

    
}