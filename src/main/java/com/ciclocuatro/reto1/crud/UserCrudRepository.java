package com.ciclocuatro.reto1.crud;

import com.ciclocuatro.reto1.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author andre
 */
public interface UserCrudRepository extends CrudRepository<User, Integer>{ //Permite acceder a metodos para C R U D informacion, el primer valor va el nombre de la entidad, en el segundo el tipo de dato de la llave primaria
    /**
     * Estos dos metodos permiten realizar consultas findBy ... y posteriormente coloco el nombre del 
     *Atributo valido, SELECT * FROM User WHERE blablabla, es similar
     *Estos metodos los cree YO, ya que los que extiende del CrudRepository no existen, son metodos mas
     *estandar, por ende tuve que escribirlos dentro del UserCrudRepository o mi clase
     * 
     * Se coloca optional como metodo de control, asegura/protege de errores mas adelante en caso que un
     * correo sea nulo
     */
    
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
