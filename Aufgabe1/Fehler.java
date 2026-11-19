public class Fehler {

    public static void main(String[] args) {
        boolean result = test_calculate_price();
        System.out.println(result);
    }

    public static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        if (extras >= 3) addon_discount = 10;
        else if (extras >= 5) addon_discount = 15;
        else addon_discount = 0;

        if (discount > addon_discount) addon_discount = discount;

        result = baseprice / 100.0 * (100 - discount) + specialprice + extraprice / 100.0 * (100 - addon_discount);

        return result;
    }

    public static boolean test_calculate_price() {
        double price;
        boolean test_ok = true;

        // Test 1: 3 Extras -> 10% Rabatt auf Extras
        price = calculatePrice(10000.00, 2000.00, 1000.00, 3, 0);
        test_ok = check("Test 1 (3 Extras)", price, 12900.00) && test_ok;

        // Test 2: keine Extras, kein Rabatt
        price = calculatePrice(10000.00, 2000.00, 1000.00, 0, 0);
        test_ok = check("Test 2 (0 Extras)", price, 13000.00) && test_ok;

        // Test 3: 5 Extras -> 15% Rabatt auf Extras
        price = calculatePrice(10000.00, 2000.00, 1000.00, 5, 0);
        test_ok = check("Test 3 (5 Extras)", price, 12850.00) && test_ok;

        // Test 4: Haendlerrabatt 20% ist groesser als Extra-Rabatt (10%)
        price = calculatePrice(10000.00, 2000.00, 1000.00, 3, 20);
        test_ok = check("Test 4 (3 Extras, 20% Rabatt)", price, 10800.00) && test_ok;

        // Test 5: 5 Extras (15%) ist groesser als Haendlerrabatt (10%)
        price = calculatePrice(10000.00, 2000.00, 1000.00, 5, 10);
        test_ok = check("Test 5 (5 Extras, 10% Rabatt)", price, 11850.00) && test_ok;

        return test_ok;
    }

    private static boolean check(String name, double actual, double expected) {
        boolean ok = Math.abs(actual - expected) < 0.01;
        System.out.println(name + ": " + (ok ? "OK" : "FEHLER") + " (erwartet " + expected + ", erhalten " + actual + ")");
        return ok;
    }
}