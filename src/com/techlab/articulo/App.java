package com.techlab.articulo;

import com.techlab.articulo.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();

        // Precargar categorías del sistema
        precargarCategorias(categorias);

        int opcion = -1;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Ingrese una opción: ");
            System.out.println();

            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("¡Gracias por utilizar el sistema! Hasta luego.");
                    break;
                default:
                    System.out.println("Opción incorrecta. Ingrese un número del 0 al 6.");
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    // 4.2. Precarga de categorías obligatorias
    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    // 5. Mostrar Menú Principal
    private static void mostrarMenu() {
        System.out.println("======================================================");
        System.out.println("SISTEMA DE ARTÍCULOS");
        System.out.println("CLASE 4 (HERENCIA Y TO_STRING)");
        System.out.println("======================================================");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar un artículo");
        System.out.println("4 - Modificar un artículo");
        System.out.println("5 - Eliminar un artículo");
        System.out.println("6 - Listar categorías");
        System.out.println("0 - Salir");
        System.out.println("======================================================");
    }

    // 6.1. Opción 1: Ingresar artículo
    public static void ingresarArticulo(Scanner scanner, ArrayList<Articulo> articulos,
            ArrayList<Categoria> categorias) {
        System.out.println("--- INGRESAR ARTÍCULO ---");
        System.out.println("1 - Artículo Electrónico");
        System.out.println("2 - Artículo Periférico");
        System.out.println("3 - Artículo Alimenticio");
        System.out.println("4 - Artículo de Limpieza");

        int tipo = 0;
        while (tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4) {
            tipo = leerEntero(scanner, "Seleccione el tipo de artículo: ");
            if (tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4) {
                System.out.println("Error: Tipo inválido. Debe seleccionar 1, 2, 3 o 4.");
            }
        }

        int codigo;
        while (true) {
            codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo: ");
            if (buscarArticuloPorCodigo(articulos, codigo) != null) {
                System.out.println("Error: Ya existe un artículo con ese código. Cancelando ingreso.");
                return;
            }
            break;
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");

        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria c : categorias) {
            System.out.println(c.getCodigo() + " - " + c.getNombre());
        }
        System.out.println();

        Categoria categoriaSeleccionada = pedirCategoriaExistente(scanner, categorias);

        Articulo nuevoArticulo;

        if (tipo == 1) {
            int garantia = leerEnteroNoNegativo(scanner, "Ingrese la garantía en meses: ");
            nuevoArticulo = new ArticuloElectronico(codigo, nombre, precio, categoriaSeleccionada, garantia);
        } else if (tipo == 2) {
            String tipoConexion = leerTextoNoVacio(scanner, "Ingrese el tipo de conexión del periférico: ");
            nuevoArticulo = new ArticuloPeriferico(codigo, nombre, precio, categoriaSeleccionada, tipoConexion);
        } else if (tipo == 3) {
            int diasVenc = leerEnteroNoNegativo(scanner, "Ingrese los días para el vencimiento: ");
            nuevoArticulo = new ArticuloAlimenticio(codigo, nombre, precio, categoriaSeleccionada, diasVenc);
        } else {
            String tipoProducto = leerTextoNoVacio(scanner, "Ingrese el tipo de producto de limpieza: ");
            nuevoArticulo = new ArticuloLimpieza(codigo, nombre, precio, categoriaSeleccionada, tipoProducto);
        }

        articulos.add(nuevoArticulo);
        System.out.println("\nArtículo ingresado correctamente.");
        System.out.println("Resumen del objeto creado:\n");
        System.out.println(nuevoArticulo);
    }

    // 6.2. Opción 2: Listar artículos
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        System.out.println("--- LISTADO DE ARTÍCULOS ---");
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }
        for (Articulo a : articulos) {
            System.out.println(a);
            System.out.println("------------------------------------------------------");
        }
    }

    // 6.3. Opción 3: Consultar un artículo
    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("--- CONSULTAR ARTÍCULO ---");
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a consultar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
        } else {
            System.out.println("\nDatos del artículo:");
            System.out.println(articulo);
            System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
            if (articulo instanceof ArticuloElectronico) {
                ArticuloElectronico elec = (ArticuloElectronico) articulo;
                System.out.println("Mesa de ayuda: " + elec.nroTelMesaDeAyudaParaReclamos());
            }
        }
    }

    // 6.4. Opción 4: Modificar un artículo
    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos,
            ArrayList<Categoria> categorias) {
        System.out.println("--- MODIFICAR ARTÍCULO ---");
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a modificar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("\nModificando artículo: " + articulo.getNombre());
        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese nuevo nombre: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese nuevo precio: ");

        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria c : categorias) {
            System.out.println(c.getCodigo() + " - " + c.getNombre());
        }
        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        // Modificar atributos comunes
        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        // Modificar atributo particular usando instanceof y casting
        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;
            int nuevaGarantia = leerEnteroNoNegativo(scanner, "Ingrese nueva garantía en meses: ");
            electronico.setGarantiaMeses(nuevaGarantia);
        } else if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
            int nuevosDias = leerEnteroNoNegativo(scanner, "Ingrese nuevos días para vencimiento: ");
            alimenticio.setDiasParaVencimiento(nuevosDias);
        } else if (articulo instanceof ArticuloPeriferico) {
            ArticuloPeriferico periferico = (ArticuloPeriferico) articulo;
            String nuevoTipoConexion = leerTextoNoVacio(scanner, "Ingrese el nuevo tipo de conexión: ");
            periferico.setTipoConexion(nuevoTipoConexion);
        } else if (articulo instanceof ArticuloLimpieza) {
            ArticuloLimpieza limpieza = (ArticuloLimpieza) articulo;
            String nuevoTipoProducto = leerTextoNoVacio(scanner, "Ingrese el nuevo tipo de producto de limpieza: ");
            limpieza.setTipoProducto(nuevoTipoProducto);
        }

        System.out.println("\nArtículo modificado correctamente.");
    }

    // 6.5. Opción 5: Eliminar un artículo
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("--- ELIMINAR ARTÍCULO ---");
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a eliminar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
        } else {
            articulos.remove(articulo);
            System.out.println("Artículo eliminado correctamente.");
        }
    }

    // 6.6. Opción 6: Listar categorías
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("--- LISTADO DE CATEGORÍAS ---");
        for (Categoria c : categorias) {
            System.out.println(c);
            System.out.println("------------------------------------------------------");
        }
    }

    // ======================================================
    // 7. MÉTODOS AUXILIARES Y VALIDACIONES DE LECTURA
    // ======================================================

    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria c : categorias) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        while (true) {
            int cod = leerEntero(scanner, "Ingrese el código de la categoría: ");
            Categoria cat = buscarCategoriaPorCodigo(categorias, cod);
            if (cat != null) {
                return cat;
            }
            System.out.println("Error: Código de categoría inexistente. Intente nuevamente.");
        }
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Error: El valor no puede ser negativo.");
        }
    }

    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                double valor = Double.parseDouble(entrada.trim());
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("Error: Valor decimal no válido.");
                    continue;
                }
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Error: El precio no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número decimal válido (ej: 1500.50).");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Error: El texto no puede estar vacío.");
        }
    }
}