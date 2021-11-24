package com.ciclocuatro.reto1.service;

import com.ciclocuatro.reto1.model.User;
import com.ciclocuatro.reto1.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service //Anotacion para que la clase funcione como servici
public class UserService {

    //atributo de relacion o referencia al repositorio este servicio se debe relacionar con el UserRepository
    @Autowired
    private UserRepository userRepository;

    /**
     * metodo getAll(), permite obtener informacion apoyandose en el
     * userRepository llamar al metodo getAll() del repositorio y de esta manera
     * solicitar a la interfaz CRUD que retorne informacion
     *
     * @return
     */
    public List<User> getAll() {
        return userRepository.getAll();
    }

    /**
     * metodo getUser(), permite obtener informacion de un usuario apoyandose
     * del userRepository llama el metodo getUser() del repositorio de esta
     * manera solicita a la interfaz CRUD que retorne informacion referente a la
     * id de un usuario
     *
     * @param id
     * @return
     */
    public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }

    /**
     * metoro registrar(), este metodo permite registrar nuevos usuarios,
     * apoyandose del userRepository mediante la funcion existeEmail() se puede
     * evaluar si el usuario ya ha sido creado con el mismo correo de esta
     * manera permite crear o no un usuario con correo
     *
     * @param user
     * @return
     */
    public User registrar(User user) {
        if (user.getId() == null) {
            if (existeEmail(user.getEmail()) == false) { //¿existe el email?
                return userRepository.save(user); //Si no existe realiza el metodo save del userRepo
            } else {
                return user; //Si ya existe retorna info del usuario existente
            }
        } else {
            return user; //Si la ID no es nula (es decir que el usuario ya existe) entonces retorna info del usuario existente
        }
    }

    /**
     * metodo existeEmail(), permite determinar si un email existe o no mediante
     * el retorno de un booleano, este metodo retorna apoyandose en los metodos
     * de la clase UserRepository
     *
     * @param email
     * @return
     */
    public boolean existeEmail(String email) {
        return userRepository.existeEmail(email);
    }

    /**
     * Metodo autenticarUsuario(String email, String password), este metodo permite verificar que
     * correo y contraseña coincidan en la base de datos. Este metodo se apoya en el metodo 
     * autenticarUsuario(email, password) creado en la clase UserRepository.
     * 
     * Si es vacio es decir que ni correo ni contraseña coinciden regresa un objeto de tipo usuario
     * con el email, el password y la palabra no definido y el id por ende sera nulo. 
     * 
     * Si el usuario no es vacio entonces retorna un get() del usuario
     * @param email
     * @param password
     * @return 
     */
    public User autenticarUsuario(String email, String password) {
        Optional<User> usuario = userRepository.autenticarUsuario(email, password);

        if (usuario.isEmpty()) {
            return new User(email, password, "NO DEFINIDO"); 
        } else {
            return usuario.get();
        }
    }
}
