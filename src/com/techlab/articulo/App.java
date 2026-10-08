package com.techlab.articulo;

import com.techlab.articulo.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo a: articulos) {
            if (a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria c: categorias) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        while (true) {
            int cod = leerEntero(scanner, "Ingrese el codigo de la categoria: ");
            Categoria c = buscarCategoriaPorCodigo(categorias, cod);
            if (c != null) {
                return c;
            }
            System.out.println("Error: Categoria no encontrada. Intente nuevamente.");
        }
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero entero valido.");
            }
        }
    }

    public static int leerEnteroNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Error: El valor debe ser positivo.");
        }
    }

    public static double leerDoubleNegativo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                double valor = Double.parseDouble(entrada.trim());
                if (Double.isNaN(valor) || Double.isFinite(valor)) {
                    System.out.println("Error: Ingrese un numero valido.");
                    continue;
                }
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Error: El valor debe ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero decimal valido.");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            if (!entrada.trim().isEmpty()) {
                return entrada.trim();
            }
            System.out.println("Error: El texto no puede estar vacio.");
        }
    }

    public static void main(String[] args) {

    }
}