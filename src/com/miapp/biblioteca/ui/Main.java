package com.miapp.biblioteca.ui;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.service.LibroService;
import com.miapp.biblioteca.service.UsuarioService;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author TESLA
 */
public class Main {
    public static void main(String[] args){
        
        ArrayList<Libro> biblioteca = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        LibroService libroService = new LibroService(biblioteca);
        UsuarioService usuarioService = new UsuarioService(usuarios);
        Scanner scanner = new Scanner(System.in);
        
        int opcion;
        do{
            System.out.println("====BIBLIOTECA VIRTUAL ALKEMY====");
            System.out.println("1.  Crear Libro");
            System.out.println("2.  Actualizar Libro");
            System.out.println("3.  Buscar Libro por ISBN");
            System.out.println("4.  Buscar Libro por Título");
            System.out.println("5.  Listar Libros");
            System.out.println("6.  Eliminar Libro");
            System.out.println("7.  Prestamos");
            System.out.println("8.  Devoluciones");
            System.out.println("9.  Crear Usuario");
            System.out.println("10. Eliminar Usuario");
            System.out.println("11. Listar Usuarios");
            System.out.println("12. Actualizar Usuario");
            System.out.println("13. Salir");
            System.out.println("=================================")
            System.out.println("Por favor ingrese una opción: ");
            System.out.println(" ");
            opcion = scanner.nextInt();
            
        switch(opcion){
            case 1:
                //Crear Libro
                System.out.println("Ingrese un titulo: ");
                String titulo = scanner.next();
                System.out.println("Ingrese el autor: ");
                String autor = scanner.next();
                System.out.println("Ingrese el ISBN: ");
                String isbn = scanner.next();
                System.out.println("Ingrese el género: ");
                String genero = scanner.next();
                
                libroService.crearLibro(titulo,autor,isbn,genero);
                Libro libronuevo = libroService.buscarLibroPorISBN(isbn);
                libronuevo.setDisponible(true);
                break;
            
            case 2:
                //Actualizar el libro
                System.out.println("Ingrese el ISBN del libro a actualizar: ");
                String isbnActualizar = scanner.next();
                System.out.println("Ingrese el nuevo titulo: ");
                String nuevoTitulo = scanner.next();
                System.out.println("Ingrese el nuevo autor: ");
                String nuevoAutor = scanner.next();
                System.out.println("Ingrese el nuevo genero: ");
                String nuevoGenero = scanner.next();
                
                libroService.actualizarLibro(isbnActualizar, nuevoTitulo, nuevoAutor, nuevoGenero);
                break;
                
            case 3:
                //Buscar Libro por ISB
                System.out.println("Ingrese el ISBN del libro a buscar: ");
                String isbnBuscar = scanner.next();
                Libro libroISBN = libroService.buscarLibroPorISBN(isbnBuscar);
                if (libroISBN != null){
                    System.out.println("Libro encontrado: " + libroISBN.getTitulo());
                }else{
                    System.out.println("Libro no encontrado");
                }
                break;
                
            case 4:
                //Buscar Libros por Titulo
                System.out.println("Ingrese el titulo a buscar: ");
                String tituloBuscar = scanner.next();
                ArrayList<Libro> librosPorTitulo = libroService.buscarLibrosPorTitulos(tituloBuscar);
                if(!librosPorTitulo.isEmpty()){
                    System.out.println("Libros encontrados: ");
                    for(Libro libro : librosPorTitulo){
                        System.out.println(libro.getTitulo());
                    }
                }else{
                    System.out.println("Ningún libro encontrado con ese titulo");
                }
             break;
             
            case 5:
                //Listar libros
                ArrayList<Libro> listaLibros = libroService.obtenerTodosLosLibros();
                for(Libro libro : listaLibros){
                    System.out.println(libro.getTitulo()+ " ( " + libro.getISBN() + " )");
                }
                break;
            
            case 6:
                //Eliminar Libro
                System.out.println("Ingrese el ISBN del libro a eliminar: ");
                String isbnEliminar = scanner.next();
                libroService.eliminarLibro(isbnEliminar);
                break;
                
            case 7:
                //Prestamos
                System.out.println("Ingrese el número de identificación del usuario: ");
                String idUsuario = scanner.next();
                Usuario usuarioPrestamo = usuarioService.buscarUsuarioPorId(idUsuario);
                if (usuarioPrestamo != null){
                    System.out.println("Ingrese el ISBN del libro a prestar: ");
                    String isbnPrestamo = scanner.next();
                    Libro libroPrestamo = libroService.buscarLibroPorISBN(isbnPrestamo);
                    if(libroPrestamo != null){
                        if(libroService.verificarDisponibilidad(libroPrestamo)){
                            usuarioService.prestarLibro(usuarioPrestamo, libroPrestamo);
                            System.out.println("Préstamo exitoso. Libro prestado a " + usuarioPrestamo.getNombre());
                        } else{
                            System.out.println("El libro no está disponible para préstamo.");
                        }
                    }else{
                        System.out.println("libro no encontrado.");
                    }
                }else{
                    System.out.println("Usuario no encontrado.");
                }
                break;
                
            case 8: 
                //devoluciones
                System.out.println("Ingrese el número de identificación del usuario: ");
                String idUsuario1 = scanner.next();
                Usuario usuarioDevolucion = usuarioService.buscarUsuarioPorId(idUsuario1);
                if (usuarioDevolucion != null){
                    System.out.println("Ingrese el ISBN del libro a devolver: ");
                    String isbnDevolucion = scanner.next();
                    Libro libroDevolucion = libroService.buscarLibroPorISBN(isbnDevolucion);
                    if (libroDevolucion != null){
                        usuarioService.devolverLibro(usuarioDevolucion, libroDevolucion);
                        System.out.println("Devolución exitosa. Libro devuelto por " + usuarioDevolucion.getNombre());
                    } else{
                        System.out.println("Libro no encontrado.");
                    }
                } else{
                    System.out.println("Usuario no encontrado.");
                }
                break;
                
            case 9: 
                // crear usuario
                System.out.println("Ingrese el nombre del Usuario: ");
                String nombre = scanner.next();
                System.out.println("Ingrese el id del usuario: ");
                String id = scanner.next();
                usuarioService.crearUsuario(nombre,id);
                break;
                
            case 10:
                //Eliminar usuario
                System.out.println("Ingrese el Id del usuario que quiere eliminar: ");
                String delUsuario = scanner.next();
                usuarioService.eliminarUsuarios(delUsuario);
                break;
            
            case 11:
                //Listar usuarios
                ArrayList<Usuario> listaUsuarios = usuarioService.obtenerTodosLosUsuarios();
                for(Usuario usuario : listaUsuarios){
                    System.out.println(usuario.getNombre()+ ", "); 
                }
                break;
                
            case 12:
                //Actualizar Usuario
                //Actualizar el libro
                System.out.println("Ingrese el ISBN del libro a actualizar: ");
                String nombreActualizar = scanner.next();
                System.out.println("Ingrese el nuevo titulo: ");
                String nuevoId = scanner.next();
                usuarioService.actualizarUsuario(nuevoId, nombreActualizar);
                break;
                
            case 13:
                System.out.println("Gracias por usar la Biblioteca Virtual. Sesion terminada");
                break;
            default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
        }
            
       } while (opcion != 13);
        
        
    }
}
