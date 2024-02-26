import java.util.ArrayList;

public class Account {
    private String name;
    private String uid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank bank){
        this.name = name;
        this.holder = holder;

        this.uid = bank.getNewAccountUid();

        this.transactions = new ArrayList<Transaction>();
    }

    public String getUid(){
        return this.uid;
    }

    public String getSummaryLine(){
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s : ₹ %.02f: %s", this.uid, balance, this.name);
        }
        else {
            return String.format("%s : ₹ (%.02f): %s", this.uid, balance, this.name);
        }
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory(){
        System.out.printf("\nTransaction history of account %s\n", this.uid);
        for (int t = this.transactions.size()-1; t >= 0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo){
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
