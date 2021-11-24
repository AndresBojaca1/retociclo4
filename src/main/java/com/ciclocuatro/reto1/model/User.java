/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclocuatro.reto1.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author andre
 */

//ANOTACIONES
@Entity //Permite que esta clase represente a una base de datos
@Data //Anotacion para que loombok cree los get y set automaticamente
@RequiredArgsConstructor //Anotacion para que loombok cree los constructores
@NoArgsConstructor
@Table(name="user", indexes = @Index(name="indx_email", columnList="user_email", unique = true)) //La clase User va a estar reflejada en una tabla de base de datos cuyo nombre es User tambien, posteriormente añadi un index, cuyo fin es buscar que EMAIL (su nombre y su columna) sea unico y no se repita

public class User implements Serializable{ //Por seguiridad para transfirir info a traves de la red se implementa Serializable
    
    @Id //Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Estrategia o manera para generar la llave primaria, la columna se marque como incrementable en h2
    private Integer id;
    
    @NonNull
    @Column(name = "user_email", nullable = false, length = 50) //Le definicion un nombre a la columna, cuya longitud es de 50 caracteres y que no sea nula es decir es obligatoria
    private String email;
    
    @NonNull
    @Column(name = "user_password", nullable = false, length = 50)
    private String password;
    
    @NonNull
    @Column(name = "user_name", nullable = false, length = 80)
    private String name;
    
    /**
     * Como estoy usando loombok no tengo necesidad de crear getter, setter y constructores, por ende este codigo
     * es mucho mas corto pero tengo que tener en cuenta unas anotaciones que debo colocar al inicio de la clase
     */
}
