import java.util.Scanner;
public class currency {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    String[] currencies = {"INR", "USD", "KRW", "EUR"};

    double[][] rates = {
      /* INR */ { 1.0,    0.012,   15.91,   0.0099 },
      /* USD */ { 83.33,  1.0,     1324.60, 0.82   },
      /* KRW */ { 0.063,  0.00076, 1.0,     0.00062 },
      /* EUR */ { 101.01, 1.22,    1606.45, 1.0    }
    };

    System.out.println("Supported currencies:");
    for (int i = 0; i < currencies.length; i++) {
      System.out.println(i + ". " + currencies[i]);
    }

    System.out.print("Enter the source currency index: ");
    int source = scanner.nextInt();

    System.out.print("Enter the target currency index: ");
    int target = scanner.nextInt();

    System.out.print("Enter amount in " + currencies[source] + ": ");
    double amount = scanner.nextDouble();

    double converted = amount * rates[source][target];
    System.out.printf("%.2f %s = %.2f %s%n", amount, currencies[source], converted, currencies[target]);

    scanner.close();
  }
}


