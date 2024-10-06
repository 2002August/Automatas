/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_automatas;

import java.util.*;
import java.io.*;

public class AlfabetoCadenasApp {

    private final Set<Character> alfabeto = new HashSet<>();
    private final List<String> cadenas = new ArrayList<>();
    private final Map<String, Boolean> estadoCadenas = new HashMap<>();
    
    // Método para definir el alfabeto
    public void definirAlfabeto(String caracteres) {
        alfabeto.clear(); // Limpiar cualquier alfabeto previo
        for (char c : caracteres.toCharArray()) {
            alfabeto.add(c);
        }
        System.out.println("Alfabeto definido: " + alfabeto);
    }

    // Método para registrar una cadena y verificar si es aceptada
    public void registrarCadena(String cadena) {
        boolean aceptada = true;
        for (char c : cadena.toCharArray()) {
            if (!alfabeto.contains(c)) {
                aceptada = false;
                break;
            }
        }
        cadenas.add(cadena);
        estadoCadenas.put(cadena, aceptada);
        if (aceptada) {
            System.out.println("Cadena aceptada: " + cadena);
        } else {
            System.out.println("Cadena no aceptada: " + cadena);
        }
    }

    // Método para exportar a un archivo TXT
    public void exportarTXT(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Alfabeto Definido: " + alfabeto.toString() + "\n");
            writer.write("Cadenas Registradas:\n");
            for (String cadena : cadenas) {
                String estado = estadoCadenas.get(cadena) ? "Aceptada" : "No aceptada";
                writer.write(cadena + ": " + estado + "\n");
            }
            System.out.println("Datos exportados correctamente a " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }

    // Menú de opciones
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        while (opcion != 4) {
            System.out.println("\n*** Menu Principal ***");
            System.out.println("1. Definir Alfabeto");
            System.out.println("2. Registrar Cadenas");
            System.out.println("3. Exportar Datos a TXT");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el alfabeto: ");
                    String alfabetoInput = scanner.nextLine();
                    definirAlfabeto(alfabetoInput);
                    break;
                case 2:
                    System.out.print("Ingrese una cadena (o 'salir' para terminar): ");
                    String cadenaInput = scanner.nextLine();
                    if (!cadenaInput.equalsIgnoreCase("salir")) {
                        registrarCadena(cadenaInput);
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del archivo para exportar: ");
                    String nombreArchivo = scanner.nextLine();
                    exportarTXT(nombreArchivo);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        AlfabetoCadenasApp programa = new AlfabetoCadenasApp();
        programa.mostrarMenu();
    }
}
