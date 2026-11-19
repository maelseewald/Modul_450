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

        price = calculatePrice(10000.00, 2000.00, 1000.00, 3, 0);
        test_ok = test_ok && (Math.abs(price - 12900.00) < 0.01);

        return test_ok;
    }
}