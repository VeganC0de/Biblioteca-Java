
package com.miapp.biblioteca.service;
import com.miapp.biblioteca.Libro;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author TESLA
 */
public class LibroService {
    
    private ArrayList<Libro> biblioteca; //ArrayList que contiene los libros
    
    
    public LibroService(ArrayList<Libro> biblioteca){
        this.biblioteca = biblioteca;
    }
    
    //metodos
    public void crearLibro(String titulo,String autor,String ISBN,String genero){ //Crea los libros
        Libro nuevoLibro = new Libro(titulo,autor,ISBN,genero);
        biblioteca.add(nuevoLibro);
        System.out.println("Libro creado exitosamente");
    }
    
    public ArrayList<Libro> obtenerTodosLosLibros(){ //Lee los libros
        return biblioteca;
    } 
    
    //Método para buscar un libro por su ISBN
    public Libro buscarLibroPorISBN(String ISBN){
        for (Libro libro : biblioteca){
            if(libro.getISBN().equals(ISBN)){
                return libro;
            }
        }
        return null;
    } 
    // Retorna null si no se encunetra ningún libro con el ISBN proporciona
    
    //Método para buscar libros por título
    
    public ArrayList<Libro> buscarLibrosPorTitulos(String titulo){
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for(Libro libro : biblioteca){
            if(libro.getTitulo().equalsIgnoreCase(titulo)){
            librosEncontrados.add(libro);
         }
        }
        return librosEncontrados;
    }
    
    public boolean verificarDisponibilidad(Libro libro){
        return libro.isDisponible();
    }
    
    public void actualizarLibro(String ISBN, String nuevoTitulo, String nuevoAutor, String nuevoGenero){ //Actualiza el libro usado
        
        for (Libro libro : biblioteca){
            if(libro.getISBN().equals(ISBN)){
                libro.setTitulo(nuevoTitulo);
                libro.setAutor(nuevoAutor);
                libro.setGenero(nuevoGenero);
                System.out.println("Libro actualizado exitosamente");
            }
        }
    }
    
    public void eliminarLibro(String ISBN){
        
        Iterator<Libro> it = biblioteca.iterator();
        
        while(it.hasNext()){
            if(it.next().getISBN().equals(ISBN)){
                it.remove();
                System.out.println("Libro eliminado exitosamente");
        }
       }
    }
    
}
