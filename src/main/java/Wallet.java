import java.util.HashMap;
import java.util.Map;

public class Wallet {
    // Mapa para almacenar los balances en diferentes monedas
    private Map<String, Double> balances;
    // Moneda predeterminada (CLP)
    private static final String DEFAULT_CURRENCY = "CLP";

    // Constructor que inicializa los balances en 0 para EUR, USD y CLP
    public Wallet() {
        this.balances = new HashMap<>();
        this.balances.put("EUR", 0.0);
        this.balances.put("USD", 0.0);
        this.balances.put("CLP", 0.0);
    }

    // Método para obtener el balance de una moneda específica
    public double getBalance(String currency) {
        return balances.getOrDefault(currency, 0.0);
    }

    // Método para depositar una cantidad en una moneda específica
    public void deposit(double amount, String currency) {
        balances.put(currency, balances.getOrDefault(currency, 0.0) + amount);
    }

    // Método para retirar una cantidad en una moneda específica, si hay fondos suficientes
    public void withdraw(double amount, String currency) {
        if (balances.getOrDefault(currency, 0.0) >= amount) {
            balances.put(currency, balances.get(currency) - amount);
        } else {
            System.out.println("Saldo insuficiente en " + currency + ".");
        }
    }

    // Método para convertir una cantidad de CLP a otra moneda (USD o EUR)
    public void convertFromCLP(double amount, String toCurrency) {
        double rate = 0.0;
        switch (toCurrency) {
            case "USD":
                rate = 1.0 / 870; // Tasa de conversión de CLP a USD
                break;
            case "EUR":
                rate = 1.0 / 960; // Tasa de conversión de CLP a EUR
                break;
            default:
                System.out.println("Moneda no soportada para conversión.");
                return;
        }
        // Verificar si hay suficientes CLP para convertir
        if (balances.getOrDefault(DEFAULT_CURRENCY, 0.0) >= amount) {
            double convertedAmount = amount * rate;
            withdraw(amount, DEFAULT_CURRENCY);
            deposit(convertedAmount, toCurrency);
            System.out.println("Conversión exitosa. Convertidos " + amount + " CLP a " + convertedAmount + " " + toCurrency);
        } else {
            System.out.println("Saldo insuficiente en CLP.");
        }
    }

    // Método para enviar dinero a otra cartera en una moneda específica, si hay fondos suficientes
    public void sendMoney(Wallet receiverWallet, double amount, String currency) {
        if (balances.getOrDefault(currency, 0.0) >= amount) {
            withdraw(amount, currency);
            receiverWallet.deposit(amount, currency);
            System.out.println("Se han enviado " + amount + " " + currency + ".");
        } else {
            System.out.println("Saldo insuficiente para enviar dinero en " + currency + ".");
        }
    }

    // Método para obtener todos los balances
    public Map<String, Double> getBalances() {
        return balances;
    }
}
