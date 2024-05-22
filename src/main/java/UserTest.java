import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    // Instancia de User para las pruebas
    private User user;
    // Instancia de Wallet asociada al User para las pruebas
    private Wallet wallet;

    // Configuración antes de cada prueba
    @BeforeEach
    void setUp() {
        user = new User("Alice");
        wallet = user.getWallet();
    }

    // Prueba para verificar el nombre del usuario
    @Test
    void testUserName() {
        assertEquals("Alice", user.getName(), "El nombre del usuario debe ser 'Alice'");
    }

    // Prueba para verificar los saldos iniciales en las diferentes monedas
    @Test
    void testUserWalletInitialBalances() {
        assertEquals(0.0, wallet.getBalance("CLP"), "El saldo inicial en CLP de la cartera del usuario debe ser 0.0");
        assertEquals(0.0, wallet.getBalance("USD"), "El saldo inicial en USD de la cartera del usuario debe ser 0.0");
        assertEquals(0.0, wallet.getBalance("EUR"), "El saldo inicial en EUR de la cartera del usuario debe ser 0.0");
    }

    // Prueba para verificar depósitos en diferentes monedas
    @Test
    void testUserWalletDeposit() {
        wallet.deposit(100.0, "CLP");
        assertEquals(100.0, wallet.getBalance("CLP"), "El saldo de la cartera del usuario debe ser 100.0 CLP después de depositar 100.0 CLP");

        wallet.deposit(50.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo de la cartera del usuario debe ser 50.0 USD después de depositar 50.0 USD");

        wallet.deposit(30.0, "EUR");
        assertEquals(30.0, wallet.getBalance("EUR"), "El saldo de la cartera del usuario debe ser 30.0 EUR después de depositar 30.0 EUR");
    }

    // Prueba para verificar retiros en diferentes monedas
    @Test
    void testUserWalletWithdraw() {
        wallet.deposit(100.0, "CLP");
        wallet.withdraw(50.0, "CLP");
        assertEquals(50.0, wallet.getBalance("CLP"), "El saldo de la cartera del usuario debe ser 50.0 CLP después de retirar 50.0 CLP");

        wallet.deposit(50.0, "USD");
        wallet.withdraw(20.0, "USD");
        assertEquals(30.0, wallet.getBalance("USD"), "El saldo de la cartera del usuario debe ser 30.0 USD después de retirar 20.0 USD");

        wallet.deposit(30.0, "EUR");
        wallet.withdraw(10.0, "EUR");
        assertEquals(20.0, wallet.getBalance("EUR"), "El saldo de la cartera del usuario debe ser 20.0 EUR después de retirar 10.0 EUR");
    }

    // Prueba para verificar retiros con fondos insuficientes en diferentes monedas
    @Test
    void testUserWalletWithdrawInsufficientFunds() {
        wallet.deposit(100.0, "CLP");
        wallet.withdraw(150.0, "CLP");
        assertEquals(100.0, wallet.getBalance("CLP"), "El saldo debe seguir siendo 100.0 CLP después de intentar retirar 150.0 CLP con saldo insuficiente");

        wallet.deposit(50.0, "USD");
        wallet.withdraw(100.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo debe seguir siendo 50.0 USD después de intentar retirar 100.0 USD con saldo insuficiente");
    }

    // Prueba para verificar la conversión de CLP a otras monedas
    @Test
    void testUserWalletConvertFromCLP() {
        wallet.deposit(8700.0, "CLP");
        wallet.convertFromCLP(8700.0, "USD");
        assertEquals(10.0, wallet.getBalance("USD"), 0.001, "El saldo debe ser 10.0 USD después de convertir 8700.0 CLP a USD");
        assertEquals(0.0, wallet.getBalance("CLP"), "El saldo en CLP debe ser 0.0 después de convertir 8700.0 CLP a USD");

        wallet.deposit(9600.0, "CLP");
        wallet.convertFromCLP(9600.0, "EUR");
        assertEquals(10.0, wallet.getBalance("EUR"), 0.001, "El saldo debe ser 10.0 EUR después de convertir 9600.0 CLP a EUR");
        assertEquals(0.0, wallet.getBalance("CLP"), "El saldo en CLP debe ser 0.0 después de convertir 9600.0 CLP a EUR");
    }

    // Prueba para verificar el envío de dinero a otra cartera
    @Test
    void testUserWalletSendMoney() {
        Wallet receiverWallet = new Wallet();
        wallet.deposit(100.0, "USD");
        wallet.sendMoney(receiverWallet, 50.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo debe ser 50.0 USD después de enviar 50.0 USD");
        assertEquals(50.0, receiverWallet.getBalance("USD"), "El saldo del receptor debe ser 50.0 USD después de recibir 50.0 USD");

        wallet.deposit(100.0, "EUR");
        wallet.sendMoney(receiverWallet, 50.0, "EUR");
        assertEquals(50.0, wallet.getBalance("EUR"), "El saldo debe ser 50.0 EUR después de enviar 50.0 EUR");
        assertEquals(50.0, receiverWallet.getBalance("EUR"), "El saldo del receptor debe ser 50.0 EUR después de recibir 50.0 EUR");
    }

    // Prueba para verificar el intento de envío de dinero con fondos insuficientes
    @Test
    void testUserWalletSendMoneyInsufficientFunds() {
        Wallet receiverWallet = new Wallet();
        wallet.deposit(50.0, "USD");
        wallet.sendMoney(receiverWallet, 100.0, "USD");
        assertEquals(50.0, wallet.getBalance("USD"), "El saldo debe seguir siendo 50.0 USD después de intentar enviar 100.0 USD con saldo insuficiente");
        assertEquals(0.0, receiverWallet.getBalance("USD"), "El saldo del receptor debe seguir siendo 0.0 USD después de intentar recibir 100.0 USD con saldo insuficiente en el emisor");
    }
}
