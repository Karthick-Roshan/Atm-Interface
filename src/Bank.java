import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUid(){
        String uid;
        Random random = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            uid = "";
            for (int i = 0; i < len; i++) {
                uid += ((Integer)random.nextInt(10)).toString();
            }

            nonUnique = false;
            for (User u: this.users) {
                if (uid.compareTo(u.getUid()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uid;
    }

    public String getNewAccountUid(){
        String uid;
        Random random = new Random();
        int len = 10;
        boolean nonUnique;

        do {
            uid = "";
            for (int i = 0; i < len; i++) {
                uid += ((Integer)random.nextInt(10)).toString();
            }

            nonUnique = false;
            for (Account a: this.accounts) {
                if (uid.compareTo(a.getUid()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uid;
    }

    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    public User addUser(String firstName, String lastName, String pin) {

        User newuser = new User(firstName, lastName, pin, this);
        this.users.add(newuser);

        Account newAccount = new Account("savings", newuser, this);
        newuser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newuser;
    }

    public User userlogin(String userid, String pin) {

        for (User u: this.users) {
            if(u.getUid().compareTo(userid) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }

    public String getName(){
        return this.name;
    }
}
