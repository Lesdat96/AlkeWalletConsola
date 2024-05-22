# Documentación
### Clase Wallet

#### Atributos
- `balances`: Un mapa que almacena los saldos en diferentes monedas.
- `DEFAULT_CURRENCY`: Una constante que representa la moneda predeterminada.

#### Métodos

1. `public Wallet()`
   - **Descripción:** Constructor de la clase `Wallet` que inicializa los balances en 0 para EUR, USD y CLP.
   - **Parámetros:** Ninguno.

2. `public double getBalance(String currency)`
   - **Descripción:** Obtiene el balance actual en la moneda especificada.
   - **Parámetros:**
     - `currency`: La moneda para la cual se desea obtener el balance.
   - **Retorno:** El balance actual en la moneda especificada.

3. `public void deposit(double amount, String currency)`
   - **Descripción:** Deposita una cantidad en la moneda especificada en la cartera.
   - **Parámetros:**
     - `amount`: La cantidad a depositar.
     - `currency`: La moneda en la que se realizará el depósito.

4. `public void withdraw(double amount, String currency)`
   - **Descripción:** Retira una cantidad de la moneda especificada de la cartera, si hay fondos suficientes.
   - **Parámetros:**
     - `amount`: La cantidad a retirar.
     - `currency`: La moneda de la cual se realizará el retiro.

5. `public void convertFromCLP(double amount, String toCurrency)`
   - **Descripción:** Convierte una cantidad de CLP a otra moneda (USD o EUR), si hay fondos suficientes.
   - **Parámetros:**
     - `amount`: La cantidad de CLP a convertir.
     - `toCurrency`: La moneda a la cual se realizará la conversión.

6. `public void sendMoney(Wallet receiverWallet, double amount, String currency)`
   - **Descripción:** Envía una cantidad de dinero a otra cartera en la moneda especificada, si hay fondos suficientes.
   - **Parámetros:**
     - `receiverWallet`: La cartera receptora.
     - `amount`: La cantidad a enviar.
     - `currency`: La moneda en la que se realizará el envío.

7. `public Map<String, Double> getBalances()`
   - **Descripción:** Obtiene todos los balances actuales en un mapa.
   - **Retorno:** Un mapa que contiene todos los balances actuales en diferentes monedas.

### Clase User

#### Atributos
- `name`: El nombre del usuario.
- `wallet`: La cartera asociada al usuario.

#### Métodos

1. `public User(String name)`
   - **Descripción:** Constructor de la clase `User` que inicializa el nombre del usuario y crea una nueva instancia de `Wallet`.
   - **Parámetros:**
     - `name`: El nombre del usuario.

2. `public String getName()`
   - **Descripción:** Obtiene el nombre del usuario.
   - **Retorno:** El nombre del usuario.

3. `public Wallet getWallet()`
   - **Descripción:** Obtiene la cartera asociada al usuario.
   - **Retorno:** La cartera del usuario.

