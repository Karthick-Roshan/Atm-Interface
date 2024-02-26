import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    private String firstname;
    private String lastname;
    private String uid;
    private byte pinHash[];
    private ArrayList<Account> accounts;

    public User(String firstname, String lastname, String pin, Bank bank) {

        this.firstname = firstname;
        this.lastname = lastname;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, Caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        this.uid = bank.getNewUserUid();

        this.accounts = new ArrayList<Account>();

        System.out.printf("New User %s %s, with ID %s created.\n", this.firstname, this.lastname, this.uid);
    }

    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    public String getUid(){
        return this.uid;
    }

    public boolean validatePin(String pin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, Caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    public String getFirstName(){
        return this.firstname;
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n%s Account Summary\n", this.firstname);
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.printf("  %d. %s\n", i+1, this.accounts.get(i).getSummaryLine());
        }

        System.out.println();
    }

    public int numAccounts(){
        return this.accounts.size();
    }

    public void printAccountTransactionHistory(int acct){
        this.accounts.get(acct).printTransHistory();
    }

    public double getAccountBalance(int idx){
        return this.accounts.get(idx).getBalance();
    }

    public String getAcctUid(int acctidx){
        return this.accounts.get(acctidx).getUid();
    }

    public void addAccountTransaction(int acctidx, double amount, String memo){
        this.accounts.get(acctidx).addTransaction(amount, memo);
    }
}


