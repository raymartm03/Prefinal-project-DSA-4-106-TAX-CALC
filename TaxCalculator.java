import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class TaxCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat php = NumberFormat.getCurrencyInstance(Locale.of("en", "PH"));

        
        System.out.println("=== TAX CALCULATOR v1.0 ===");
        System.out.println("Developed by:");
        System.out.println("• Raymart Escuadro         - Lead Developer & Logic Designer");
        System.out.println("   raymartescuadro@gmail.com | 0917-123-4567");
        System.out.println("• Leonard Christian Gonzales - UI/UX & Testing Specialist");
        System.out.println("   leonard.gonzales@outlook.com | 0991-234-5678");
        System.out.println("• Joshua Elly Escalada     - Documentation & Validation");
        System.out.println("   joshua.escalada@gmail.com | 0939-345-6789");
        System.out.println();

        System.out.println("Choose payment frequency:");
        System.out.println("1. Daily");
        System.out.println("2. Weekly");
        System.out.println("3. Semi-Monthly");
        System.out.println("4. Monthly");
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice! Please restart.");
            return;
        }

        System.out.print("Enter your compensation: ");
        double compensation = scanner.nextDouble();

        if (compensation < 0) {
            System.out.println("Compensation cannot be negative!");
            return;
        }

        double tax = calculateTax(choice, compensation);
        double netSalary = compensation - tax;

        
        System.out.println("\n=== TAX CALCULATION RESULT ===");
        System.out.println("Payment Frequency: " + getFrequencyName(choice));
        System.out.println("Compensation     : " + php.format(compensation));
        System.out.println("Withholding Tax  : " + php.format(tax));
        System.out.println("Net Salary       : " + php.format(netSalary));

        scanner.close();
    }

    
    public static double calculateTax(int choice, double compensation) {
        int bracket;
        switch (choice) {
            case 1:
                bracket = getDailyBracket(compensation);
                return calculateDailyTax(compensation, bracket);
            case 2:
                bracket = getWeeklyBracket(compensation);
                return calculateWeeklyTax(compensation, bracket);
            case 3:
                bracket = getSemiMonthlyBracket(compensation);
                return calculateSemiMonthlyTax(compensation, bracket);
            case 4:
                bracket = getMonthlyBracket(compensation);
                return calculateMonthlyTax(compensation, bracket);
            default:
                return 0.0;
        }
    }

    
    public static int getDailyBracket(double c) {
        if (c <= 685) return 1;
        else if (c <= 1095) return 2;
        else if (c <= 2191) return 3;
        else if (c <= 5478) return 4;
        else if (c <= 21917) return 5;
        else return 6;
    }

    public static double calculateDailyTax(double c, int b) {
        switch (b) {
            case 1: return 0.00;
            case 2: return round(0.15 * (c - 685));
            case 3: return round(61.65 + 0.20 * (c - 1096));
            case 4: return round(280.85 + 0.25 * (c - 2192));
            case 5: return round(1102.60 + 0.30 * (c - 5479));
            case 6: return round(6034.30 + 0.35 * (c - 21918));
            default: return 0.00;
        }
    }

    
    public static int getWeeklyBracket(double c) {
        if (c <= 4808) return 1;
        else if (c <= 7691) return 2;
        else if (c <= 15384) return 3;
        else if (c <= 38461) return 4;
        else if (c <= 153845) return 5;
        else return 6;
    }

    public static double calculateWeeklyTax(double c, int b) {
        switch (b) {
            case 1: return 0.00;
            case 2: return round(0.15 * (c - 4808));
            case 3: return round(432.60 + 0.20 * (c - 7692));
            case 4: return round(1971.20 + 0.25 * (c - 15385));
            case 5: return round(7140.45 + 0.30 * (c - 38462));
            case 6: return round(42355.65 + 0.35 * (c - 153846));
            default: return 0.00;
        }
    }

    
    public static int getSemiMonthlyBracket(double c) {
        if (c <= 10417) return 1;
        else if (c <= 16666) return 2;
        else if (c <= 33332) return 3;
        else if (c <= 83332) return 4;
        else if (c <= 333332) return 5;
        else return 6;
    }

    public static double calculateSemiMonthlyTax(double c, int b) {
        switch (b) {
            case 1: return 0.00;
            case 2: return round(0.15 * (c - 10417));
            case 3: return round(937.50 + 0.20 * (c - 16667));
            case 4: return round(4270.70 + 0.25 * (c - 33333));
            case 5: return round(16770.70 + 0.30 * (c - 83333));
            case 6: return round(91770.70 + 0.35 * (c - 333333));
            default: return 0.00;
        }
    }

    
    public static int getMonthlyBracket(double c) {
        if (c <= 20833) return 1;
        else if (c <= 33332) return 2;
        else if (c <= 66666) return 3;
        else if (c <= 166666) return 4;
        else if (c <= 666666) return 5;
        else return 6;
    }

    public static double calculateMonthlyTax(double c, int b) {
        switch (b) {
            case 1: return 0.00;
            case 2: return round(0.15 * (c - 20833));
            case 3: return round(1875.00 + 0.20 * (c - 33333));
            case 4: return round(8541.80 + 0.25 * (c - 66667));
            case 5: return round(33541.80 + 0.30 * (c - 166667));
            case 6: return round(183541.80 + 0.35 * (c - 666667));
            default: return 0.00;
        }
    }

    
    public static String getFrequencyName(int choice) {
        switch (choice) {
            case 1: return "Daily";
            case 2: return "Weekly";
            case 3: return "Semi-Monthly";
            case 4: return "Monthly";
            default: return "Unknown";
        }
    }

    
    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}


