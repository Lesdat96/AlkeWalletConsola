import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del primer usuario: ");
        String userName = scanner.nextLine();
        User user1 = new User(userName);
        User user2 = new User("Usuario2");
        User user3 = new User("Usuario3");

        System.out.println("\n=== Bienvenido " + user1.getName() + " ===");
        System.out.print("Ingrese una cantidad inicial a depositar en CLP: ");
        double depositAmount = scanner.nextDouble();
        user1.getWallet().deposit(depositAmount, "CLP");
        System.out.println("Depósito exitoso. Nuevo saldo en CLP: " + user1.getWallet().getBalance("CLP"));

        int choice;
        do {
            System.out.println("\n=== Menú de Operaciones ===");
            System.out.println("1. Ver Saldo");
            System.out.println("2. Depositar Dinero");
            System.out.println("3. Retirar Dinero");
            System.out.println("4. Enviar Dinero a otro Usuario");
            System.out.println("5. Convertir CLP a otra moneda");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Saldo de " + user1.getName() + " (CLP): " + user1.getWallet().getBalance("CLP"));
                    System.out.println("Saldo de " + user1.getName() + " (USD): " + user1.getWallet().getBalance("USD"));
                    System.out.println("Saldo de " + user1.getName() + " (EUR): " + user1.getWallet().getBalance("EUR"));
                    System.out.println("Saldo de " + user2.getName() + " (CLP): " + user2.getWallet().getBalance("CLP"));
                    System.out.println("Saldo de " + user2.getName() + " (USD): " + user2.getWallet().getBalance("USD"));
                    System.out.println("Saldo de " + user2.getName() + " (EUR): " + user2.getWallet().getBalance("EUR"));
                    System.out.println("Saldo de " + user3.getName() + " (CLP): " + user3.getWallet().getBalance("CLP"));
                    System.out.println("Saldo de " + user3.getName() + " (USD): " + user3.getWallet().getBalance("USD"));
                    System.out.println("Saldo de " + user3.getName() + " (EUR): " + user3.getWallet().getBalance("EUR"));
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: ");
                    double newDepositAmount = scanner.nextDouble();
                    System.out.print("Ingrese la moneda (CLP, USD, EUR): ");
                    String currency = scanner.next();
                    user1.getWallet().deposit(newDepositAmount, currency);
                    System.out.println("Depósito exitoso. Nuevo saldo en " + currency + ": " + user1.getWallet().getBalance(currency));
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double withdrawAmount = scanner.nextDouble();
                    System.out.print("Ingrese la moneda (CLP, USD, EUR): ");
                    currency = scanner.next();
                    user1.getWallet().withdraw(withdrawAmount, currency);
                    System.out.println("Retiro exitoso. Nuevo saldo en " + currency + ": " + user1.getWallet().getBalance(currency));
                    break;
                case 4:
                    System.out.println("Seleccione el usuario al que desea enviar dinero:");
                    System.out.println("1. " + user2.getName());
                    System.out.println("2. " + user3.getName());
                    System.out.print("Seleccione el usuario: ");
                    int userChoice = scanner.nextInt();
                    User receiverUser;
                    switch (userChoice) {
                        case 1:
                            receiverUser = user2;
                            break;
                        case 2:
                            receiverUser = user3;
                            break;
                        default:
                            System.out.println("Opción inválida.");
                            continue;
                    }
                    System.out.print("Ingrese la cantidad a enviar: ");
                    double sendAmount = scanner.nextDouble();
                    System.out.print("Ingrese la moneda (CLP, USD, EUR): ");
                    currency = scanner.next();
                    user1.getWallet().sendMoney(receiverUser.getWallet(), sendAmount, currency);
                    break;
                case 5:
                    System.out.print("Ingrese la cantidad de CLP a convertir: ");
                    double clpAmount = scanner.nextDouble();
                    System.out.print("Ingrese la moneda de destino (USD, EUR): ");
                    String toCurrency = scanner.next();
                    user1.getWallet().convertFromCLP(clpAmount, toCurrency);
                    break;
                case 6:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
