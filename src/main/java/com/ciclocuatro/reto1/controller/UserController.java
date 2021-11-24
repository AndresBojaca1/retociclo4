package com.ciclocuatro.reto1.controller;

import com.ciclocuatro.reto1.model.User;
import com.ciclocuatro.reto1.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre
 */
@RestController //Esta anotacion indica que la clase UserController va a ser un servicio
@RequestMapping("/api/user") //endpoint, permite definir un "nombre" o un contexto
@CrossOrigin("*") //elimina la restriccion generada cuando el front y el back estaban en servidores diferentes 
public class UserController {
    
    //Relacion con la capa de servicios
    @Autowired //para que sea spring quien se encargue de instanciar la clase y no hacerlo manual
    private UserService userService;
    
    //Este metodo permite recuperar TODOS los usuarios
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }
    
    //Este metodo permite crear nuevos usuarios
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED) //estado 201 created
    public User registrar(@RequestBody User user) { //el cuerpo del mensaje va a ir el usuario
        return userService.registrar(user);
    }
    
    //Este metodo permite autenticar si usuario y contraseña coinciden en la bd
    @GetMapping("/{email}/{password}") //El PathVariable indica que el email de la ruta sera tomado como parametro del metodo
    public User autenticarUsuario(@PathVariable("email") String email, @PathVariable("password") String password) {
        return userService.autenticarUsuario(email, password);
    }
    
    //Este Metodo permite verificar si el correo ingresado ya existe en la base de datos
    @GetMapping("/{email}")
    public boolean existeEmail(@PathVariable("email") String email) {
        return userService.existeEmail(email);
    }
    
    
    
}
