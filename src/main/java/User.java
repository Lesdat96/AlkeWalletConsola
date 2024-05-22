public class User {
    // Nombre del usuario
    private String name;
    // Cartera asociada al usuario
    private Wallet wallet;
    
    /**
     * Constructor de la clase User.
     * @param name El nombre del usuario.
     */
    public User(String name) {
        // Inicializa el nombre del usuario con el parámetro proporcionado
        this.name = name;
        // Crea una nueva instancia de Wallet para este usuario
        this.wallet = new Wallet();
    }

    /**
     * Método para obtener el nombre del usuario.
     * @return El nombre del usuario.
     */
    public String getName() {
        return name;
    }    

    /**
     * Método para obtener la cartera del usuario.
     * @return La cartera del usuario.
     */
    public Wallet getWallet() {
        return wallet;
    }
}
