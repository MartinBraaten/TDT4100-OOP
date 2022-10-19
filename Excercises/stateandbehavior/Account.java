package stateandbehavior;

public class Account {
    double balance = 0.0;
    double interestRate = 0.0;

    public void deposit(double Deposit) {
        if (Deposit >= 0) {
            this.balance += Deposit;
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
        this.interestRate = newInterestRate;
    }

    public Account() {
        
    }

    @Override
    public String toString() {
        return "balance =" + balance +", interestrate=" + interestRate;
    }

    public static void main(String[] args) {
        Account a1 = new Account();
        System.out.println(a1);
        a1.setInterestRate(5);
        a1.deposit(20);
        System.out.println(a1);
        a1.addInterest();
        System.out.println(a1);

    }

    
}

