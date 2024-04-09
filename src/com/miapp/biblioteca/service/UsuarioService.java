
package com.miapp.biblioteca.service;
import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author TESLA
 */
public class UsuarioService {
    
    private ArrayList<Usuario> usuarios;
    
    public UsuarioService(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }
    //metodos
    
    public Usuario buscarUsuarioPorId(String id){ //Busca un usuario por Id y lo valida. Si no exise arroja NULL
        for(Usuario usuario : usuarios){
            if(usuario.getId().equals(id)){
                return usuario;
            }
        }
        return null;
    }
    //Verifica que el libro fue prestado por el usuario. Remueve el mismo y lo deja en estado disponible
    public void devolverLibro(Usuario usuario, Libro libro){ 
        if(usuario.getLibrosPrestados().contains(libro)){
            usuario.getLibrosPrestados().remove(libro);
            libro.setDisponible(true);
        } else{
            System.out.println("Este libro no fue prestado a este usuario.");
        }
    }
    
    public void crearUsuario(String nombre, String id){ //Crea un nuevo Usuario
        Usuario nuevoUsuario = new Usuario(nombre,id);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado exitosamente");
    }
    
    public ArrayList<Usuario> obtenerTodosLosUsuarios(){ //Lee todos los usuarios
    return usuarios;
    }
    
    public ArrayList<Libro> obtenerLibrosPrestados(Usuario usuario){ //metodo para obtener libros actuialmente en prestamo
        return usuario.getLibrosPrestados();
    }
    
    public void actualizarUsuario(String id, String nuevoNombre){
        for(Usuario usuario : usuarios){
            if(usuario.getId().equals(id)){
                usuario.setNombre(nuevoNombre);
                System.out.println("Nombre actualizado exitosamente");
          }
        }
    }
    
    public void eliminarUsuarios(String id){
        Iterator<Usuario> it = usuarios.iterator();
                while(it.hasNext()){
                    if(it.next().getId().equals(id)){
                        it.remove();
                        System.out.println("Usuario Eliminado exitosamente");
                    }
                }
    }
    
    public void prestarLibro(Usuario usuario, Libro libro){
        if(libro.isDisponible()){
            usuario.getLibrosPrestados().add(libro);
            libro.setDisponible(false);
        }else{
            System.out.println("El libro solicitado no está disponible para préstamo.");
        }
    }
    
}
