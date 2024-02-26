import java.util.Scanner;

public class Atm {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank bank = new Bank("Indian Bank");

        User user = bank.addUser("john", "Doe", "1234");

        Account account = new Account("Checking", user, bank);
        user.addAccount(account);
        bank.addAccount(account);

        User currentuser;
        while (true) {
            currentuser = Atm.mainMenuPrompt(bank, sc);

            Atm.printUserMenu(currentuser, sc);
        }

    }

    public static User mainMenuPrompt(Bank bank, Scanner sc) {
        String userid;
        String pin;
        User authUser;

        do {
            System.out.printf("\n\nWelcome to %s \n\n", bank.getName());
            System.out.print("Enter User Id: ");
            userid = sc.nextLine();
            System.out.printf("Enter pin: ");
            pin = sc.nextLine();

            authUser = bank.userlogin(userid, pin);
            if(authUser == null){
                System.out.println("Incorrect user ID/Pin Combination. " + "Please Try again. ");
            }
        } while (authUser == null);

        return authUser;
    }

    public static void printUserMenu(User user, Scanner sc) {
        user.printAccountsSummary();

        int choice;

        do {
            System.out.printf("Welcome %s, What would you like to do? \n", user.getFirstName());
            System.out.println("  1. Show Account transaction history ");
            System.out.println("  2. Withdraw ");
            System.out.println("  3. Deposit ");
            System.out.println("  4. Transfer ");
            System.out.println("  5. Quit");
            System.out.println();
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid Choice. Please choose 1-5.");
            }
        }  while ( choice < 1 || choice > 5 );

        switch (choice) {
            case 1:
                Atm.showTransactionHistory(user, sc);
                break;
            case 2:
                Atm.withdrawFund(user, sc);
                break;
            case 3:
                Atm.depositFund(user, sc);
                break;
            case 4:
                Atm.transferFunds(user, sc);
                break;
            case 5:
                sc.nextLine();
                break;
        }

        if (choice != 5) {
            Atm.printUserMenu(user, sc);
        }

    }

    public static void showTransactionHistory(User user, Scanner sc){
        int acct;

        do {
            System.out.printf("Enter the number (1-%d) of the account whose transactions you want to see: ", user.numAccounts());

            acct = sc.nextInt()-1;
            if (acct < 0 || acct >= user.numAccounts()){
                System.out.println("Invalid Account. Try Again");
            }
        } while (acct < 0 || acct >= user.numAccounts());

        user.printAccountTransactionHistory(acct);
    }

    public static void transferFunds(User user, Scanner sc){

        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        do {
            System.out.printf("Enter the number (1 - %d) of the account to transfer from: ", user.numAccounts());
            fromAcct = sc.nextInt()-1;
            if(fromAcct < 0 || fromAcct >= user.numAccounts()){
                System.out.println("Invalid Account. Try Again");
            }
        } while (fromAcct < 0 || fromAcct >= user.numAccounts());

        acctBal = user.getAccountBalance(fromAcct);

        do {
            System.out.printf("Enter the number (1 - %d) of the account to transfer to: ", user.numAccounts());
            toAcct = sc.nextInt()-1;
            if(toAcct < 0 || toAcct >= user.numAccounts()){
                System.out.println("Invalid Account. Try Again");
            }
        } while (toAcct < 0 || toAcct >= user.numAccounts());

        do {
            System.out.printf("Enter the amount to transfer (Max: ₹ %.02f ): ", acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than balance of ₹ %.02f.\n", acctBal);
            }
        }while (amount < 0 || amount > acctBal);

        user.addAccountTransaction(fromAcct, -1*amount, String.format(
                "Transfer to account %s", user.getAcctUid(toAcct)));

        user.addAccountTransaction(toAcct, amount, String.format(
                "Transfer to account %s", user.getAcctUid(fromAcct)));

    }

    public static void withdrawFund(User user, Scanner sc){
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        do {
            System.out.printf("Enter the number (1 - %d) of the account to withdraw from: ", user.numAccounts());
            fromAcct = sc.nextInt()-1;
            if(fromAcct < 0 || fromAcct >= user.numAccounts()){
                System.out.println("Invalid Account. Try Again");
            }
        } while (fromAcct < 0 || fromAcct >= user.numAccounts());

        acctBal = user.getAccountBalance(fromAcct);

        do {
            System.out.printf("Enter the amount to transfer (Max: ₹ %.02f ): ", acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than balance of ₹ %.02f.\n", acctBal);
            }
        }while (amount < 0 || amount > acctBal);

        sc.nextLine();

        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        user.addAccountTransaction(fromAcct, -1*amount, memo);
    }

    public static void depositFund(User user, Scanner sc) {
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        do {
            System.out.printf("Enter the number (1 - %d) of the account to deposit in: ", user.numAccounts());
            toAcct = sc.nextInt()-1;
            if(toAcct < 0 || toAcct >= user.numAccounts()){
                System.out.println("Invalid Account. Try Again");
            }
        } while (toAcct < 0 || toAcct >= user.numAccounts());

        acctBal = user.getAccountBalance(toAcct);

        do {
            System.out.printf("Enter the amount to transfer (Max: ₹ %.02f ): ", acctBal);
            amount = sc.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than zero.");
            }
        }while (amount < 0);

        sc.nextLine();

        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        user.addAccountTransaction(toAcct, amount, memo);
    }
}