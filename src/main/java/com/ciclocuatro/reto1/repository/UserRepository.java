package com.ciclocuatro.reto1.repository;

import com.ciclocuatro.reto1.crud.UserCrudRepository;
import com.ciclocuatro.reto1.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class UserRepository {
    @Autowired //Se encarga de que cuando arranque la aplicacion se encargue de realizar una implementacion e instancias la clase UserCrudRepo Relacion con el CRUD repository
    private UserCrudRepository userCrudRepository;
    
    //Metodo para obtener toda la info, realizar consultas directamente en la base de datos ej SELECT * FROM User
    public List<User> getAll() {
        return (List<User>) userCrudRepository.findAll();
    }
    
    //Se apoya en el CRUD repo (mediante el metodo findById) para buscar un elemento por llave primaria o ID
    public Optional<User> getUser(int id) {
        return userCrudRepository.findById(id);
    }
    /**
     * Este metodo se encarga de actualizar/guardar info, el repo siempre tiene un metodo save, funciona para insertar
     * y actualizar info, el internamente decide si va a insertar o actualizar ello depende del volumen de info que 
     *  reciba, si no se recibe por ejemplo ID spring asume que lo que se va a realizar es un registro nuevo.
     * Por el contrario si se recibe un ID es por que se va a realizar actualizacion de info sobre una llave primaria
     * existente
     */
    public User save(User user) {
        return userCrudRepository.save(user);
    }
    
    //ESTOS DOS METODOS SON PARA USAR LAS CONSULTAS CREADAS POR NOSOTROS, EL findByEmail y el findByEmailAndPassword
    
    //Validar si existe un email en la bd y retorna un booleano dependiendo si existe o no un email
    //Existe un email que corresponda a email? -> True si no existe el correo, False si ya existe el correo     
    public boolean existeEmail(String email) {
        Optional<User> usuario = userCrudRepository.findByEmail(email);
        return !usuario.isEmpty();
    }
    
    //Validar si el usuario se puede autenticar es decir si la contraseña y el usuario estan correctas dentro de la bd
    public Optional<User> autenticarUsuario(String email, String password) {
        return userCrudRepository.findByEmailAndPassword(email, password);
    }
    
}
