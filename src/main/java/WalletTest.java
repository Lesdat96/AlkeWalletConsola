import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalletTest {

    private Wallet wallet;
    private Wallet receiverWallet;

    @BeforeEach
    void setUp() {
        // Configuración inicial para cada prueba
        wallet = new Wallet();
        receiverWallet = new Wallet();
    }

    @Test
    void testInitialBalances() {
        // Verifica que los saldos iniciales sean 0 para CLP, USD y EUR
        assertEquals(0.0, wallet.getBalance("CLP"), "El saldo inicial en CLP debe ser 0.0");
        assertEquals(0.0, wallet.getBalance("USD"), "El saldo inicial en USD debe ser 0.0");
        assertEquals(0.0, wallet.getBalance("EUR"), "El saldo inicial en EUR debe ser 0.0");
    }

    @Test
    void testDeposit() {
        // Verifica que los depósitos se realicen correctamente en diferentes monedas
        wallet.deposit(1000.0, "CLP");
        assertEquals(1000.0, wallet.getBalance("CLP"), "El saldo debe ser 1000.0 CLP después de depositar 1000.0 CLP");

        wallet.deposit(100.0, "USD");
        assertEquals(100.0, wallet.getBalance("USD"), "El saldo debe ser 100.0 USD después de depositar 100.0 USD");

        wallet.deposit(50.0, "EUR");
        assertEquals(50.0, wallet.getBalance("EUR"), "El saldo debe ser 50.0 EUR después de depositar 50.0 EUR");
    }

    @Test
    void testWithdraw() {
        // Verifica que los retiros se realicen correctamente en diferentes monedas
        wallet.deposit(1000.0, "CLP");
        wallet.withdraw(500.0, "CLP");
        assertEquals(500.0, wallet.getBalance("CLP"), "El saldo debe ser 500.0 CLP después de retirar 500.0 CLP");

        wallet.deposit(100.0, "USD");
        wallet.withdraw(50.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo debe ser 50.0 USD después de retirar 50.0 USD");

        wallet.deposit(50.0, "EUR");
        wallet.withdraw(30.0, "EUR");
        assertEquals(20.0, wallet.getBalance("EUR"), "El saldo debe ser 20.0 EUR después de retirar 30.0 EUR");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        // Verifica que los retiros con fondos insuficientes no modifiquen los saldos
        wallet.deposit(1000.0, "CLP");
        wallet.withdraw(1500.0, "CLP");
        assertEquals(1000.0, wallet.getBalance("CLP"), "El saldo debe seguir siendo 1000.0 CLP después de intentar retirar 1500.0 CLP con saldo insuficiente");

        wallet.deposit(100.0, "USD");
        wallet.withdraw(200.0, "USD");
        assertEquals(100.0, wallet.getBalance("USD"), "El saldo debe seguir siendo 100.0 USD después de intentar retirar 200.0 USD con saldo insuficiente");
    }

    @Test
    void testConvertCurrencyFromCLP() {
        // Verifica que la conversión de CLP a USD y EUR se realice correctamente
        wallet.deposit(8700.0, "CLP");
        wallet.convertFromCLP(8700.0, "USD");
        assertEquals(10.0, wallet.getBalance("USD"), 0.001, "El saldo debe ser 10.0 USD después de convertir 8700.0 CLP a USD");
        assertEquals(0.0, wallet.getBalance("CLP"), "El saldo en CLP debe ser 0.0 después de convertir 8700.0 CLP a USD");

        wallet.deposit(9600.0, "CLP");
        wallet.convertFromCLP(9600.0, "EUR");
        assertEquals(10.0, wallet.getBalance("EUR"), 0.001, "El saldo debe ser 10.0 EUR después de convertir 9600.0 CLP a EUR");
        assertEquals(0.0, wallet.getBalance("CLP"), "El saldo en CLP debe ser 0.0 después de convertir 9600.0 CLP a EUR");
    }

    @Test
    void testSendMoney() {
        // Verifica que el envío de dinero entre carteras se realice correctamente
        wallet.deposit(100.0, "USD");
        wallet.sendMoney(receiverWallet, 50.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo debe ser 50.0 USD después de enviar 50.0 USD");
        assertEquals(50.0, receiverWallet.getBalance("USD"), "El saldo del receptor debe ser 50.0 USD después de recibir 50.0 USD");

        wallet.deposit(100.0, "EUR");
        wallet.sendMoney(receiverWallet, 50.0, "EUR");
        assertEquals(50.0, wallet.getBalance("EUR"), "El saldo debe ser 50.0 EUR después de enviar 50.0 EUR");
        assertEquals(50.0, receiverWallet.getBalance("EUR"), "El saldo del receptor debe ser 50.0 EUR después de recibir 50.0 EUR");
    }

    @Test
    void testSendMoneyInsufficientFunds() {
        // Verifica que el envío de dinero con fondos insuficientes no modifique los saldos
        wallet.deposit(50.0, "USD");
        wallet.sendMoney(receiverWallet, 100.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo debe seguir siendo 50.0 USD después de intentar enviar 100.0 USD con saldo insuficiente");
        assertEquals(0.0, receiverWallet.getBalance("USD"), "El saldo del receptor debe seguir siendo 0.0 USD después de intentar recibir 100.0 USD con saldo insuficiente en el emisor");
    }

    @Test
    void testSetAndGetBalances() {
        // Verifica que se establezcan y obtengan los saldos correctamente
        Map<String, Double> balances = new HashMap<>();
        balances.put("USD", 1.0);
        balances.put("EUR", 20.0);
        balances.put("CLP", 1000.0);
        wallet.getBalances().putAll(balances);
        assertEquals(balances, wallet.getBalances(), "El mapa de saldos debe coincidir con el establecido");
    }
}
