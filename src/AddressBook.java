import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class AddressBook {
    public static HashMap<String, String> addressBook = new HashMap<>();
    static final String FILEPATH = Paths.get("AddressBook_AL03056091.txt").toAbsolutePath().toString();
    static Scanner scan;

    public AddressBook() {
    }

    public static void main(String[] args) {
        load();
        printMenu();
    }

    public static void load() {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(FILEPATH));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] contact = line.split(", ");
                addressBook.put(contact[0], contact[1]);
            }
        } catch (IOException var11) {
            System.out.println("IOException catched while reading: " + var11.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException var10) {
                System.out.println("IOException catched while closing: " + var10.getMessage());
            }

        }

    }

    public static void save() {
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILEPATH));
            Iterator var1 = addressBook.keySet().iterator();

            while(var1.hasNext()) {
                String phone = (String)var1.next();
                bufferedWriter.write(phone + ", " + (String)addressBook.get(phone) + "\n");
            }
        } catch (IOException var11) {
            System.out.println("IOException catched while reading: " + var11.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException var10) {
                System.out.println("IOException catched while closing: " + var10.getMessage());
            }

        }

    }

    public static void printMenu() {
        int option = 0;

        do {
            System.out.println("Selecciona la opción a realizar:");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Crear contacto nuevo");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Salir");

            try {
                option = Integer.parseInt(scan.nextLine());
                switch (option) {
                    case 1:
                        list();
                        System.out.print("\n");
                        break;
                    case 2:
                        create();
                        System.out.print("\n");
                        break;
                    case 3:
                        delete();
                        System.out.print("\n");
                        break;
                    case 4:
                        System.out.println("Su agenda se ha guardado exitosamente.");
                        save();
                        break;
                    default:
                        System.out.println("Por favor, ingrese una opción válida.");
                }
            } catch (NumberFormatException var2) {
                System.out.println("Por favor, ingrese una opción válida.");
            }
        } while(option != 4);

    }

    public static void list() {
        System.out.println("Contactos:");
        Iterator var0 = addressBook.keySet().iterator();

        while(var0.hasNext()) {
            String phone = (String)var0.next();
            System.out.println(phone + ": " + (String)addressBook.get(phone));
        }

    }

    public static void create() {
        System.out.println("Teléfono del contacto nuevo:");
        String phone = scan.nextLine();
        System.out.println("Nombre del contacto nuevo:");
        String name = scan.nextLine();
        if (!addressBook.containsKey(phone)) {
            addressBook.put(phone, name);
            System.out.println("Se creó el contacto exitosamente. \n");
        } else {
            System.out.println("Ese teléfono ya existe en su agenda. Verifique los datos.\n");
        }

    }

    public static void delete() {
        System.out.println("Teléfono del contacto a eliminar:");
        String phone = scan.nextLine();
        if (addressBook.containsKey(phone)) {
            addressBook.remove(phone);
            System.out.println("Se eliminó el contacto exitosamente. \n");
        } else {
            System.out.println("Ese teléfono no existe en su agenda. Verifique los datos.\n");
        }

    }

    static {
        scan = new Scanner(System.in);
    }
}