import java.util.Scanner;

class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
    }
  }

  public boolean withdraw(double amount) {
    if (amount > 0 && balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }
}

class ATM {
  private BankAccount account;
  private Scanner scanner;

  public ATM(BankAccount account) {
    this.account = account;
    this.scanner = new Scanner(System.in);
  }

  public void displayMenu() {
    System.out.println("\nATM Menu:");
    System.out.println("1. Check Balance");
    System.out.println("2. Deposit");
    System.out.println("3. Withdraw");
    System.out.println("4. Exit");
  }

  public void run() {
    System.out.println("Welcome to the ATM!");

    while (true) {
      displayMenu();
      System.out.print("Please select an option (1-4): ");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          checkBalance();
          break;
        case 2:
          deposit();
          break;
        case 3:
          withdraw();
          break;
        case 4:
          System.out.println("Thank you for using the ATM. Goodbye!");
          scanner.close();
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private void checkBalance() {
    System.out.printf("Your current balance is: Rs%.2f\n", account.getBalance());
  }

  private void deposit() {
    System.out.print("Enter amount to deposit: Rs");
    double amount = scanner.nextDouble();
        
    if (amount <= 0) {
      System.out.println("Invalid amount. Deposit amount must be positive.");
      return;
    }
        
    account.deposit(amount);
    System.out.printf("Rs%.2f has been deposited successfully.\n", amount);
    System.out.printf("New balance: Rs%.2f\n", account.getBalance());
  }

  private void withdraw() {
    System.out.print("Enter amount to withdraw: Rs");
    double amount = scanner.nextDouble();
        
    if (amount <= 0) {
      System.out.println("Invalid amount. Withdrawal amount must be positive.");
      return;
    }
        
    if (account.withdraw(amount)) {
      System.out.printf("Rs%.2f has been withdrawn successfully.\n", amount);
      System.out.printf("Remaining balance: Rs%.2f\n", account.getBalance());
    } 
    else {
      System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
    }
  }
}

public class ATM_INTERFACE {
  public static void main(String[] args) {
    BankAccount userAccount = new BankAccount(1000.0);

    ATM atm = new ATM(userAccount);

    atm.run();
  }
}