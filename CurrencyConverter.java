import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class CurrencyConverter {
    
    // Exchange rates (as of example date)
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    
    static {
        exchangeRates.put("USD", 1.0);         // Base currency (US Dollar)
        exchangeRates.put("EUR", 0.92);        // Euro
        exchangeRates.put("GBP", 0.79);        // British Pound
        exchangeRates.put("JPY", 151.43);      // Japanese Yen
        exchangeRates.put("INR", 83.31);       // Indian Rupee
        exchangeRates.put("AUD", 1.52);        // Australian Dollar
        exchangeRates.put("CAD", 1.36);        // Canadian Dollar
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Currency Converter ===");
        System.out.println("Available currencies:");
        for (String currency : exchangeRates.keySet()) {
            System.out.println(currency);
        }
        
        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();
        
        System.out.print("Enter source currency (e.g. USD): ");
        String sourceCurrency = scanner.next().toUpperCase();
        
        System.out.print("Enter target currency (e.g. EUR): ");
        String targetCurrency = scanner.next().toUpperCase();
        
        if (!exchangeRates.containsKey(sourceCurrency) || !exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid currency code entered!");
            return;
        }
        
        double convertedAmount = convert(amount, sourceCurrency, targetCurrency);
        System.out.printf("%.2f %s = %.2f %s%n", 
                         amount, sourceCurrency, 
                         convertedAmount, targetCurrency);
        
        scanner.close();
    }
    
    public static double convert(double amount, String from, String to) {
        // First convert to USD (base currency), then to target currency
        double usdValue = amount / exchangeRates.get(from);
        return usdValue * exchangeRates.get(to);
    }
}
