package br.com.ourogourmet.ouro.gourmet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
public class Usuario {

    @Id
    String id;

    String email;

    String login;

    Boolean ativo;

    public Usuario(String email, String login, Boolean ativo){
        this.id = String.valueOf(UUID.randomUUID());
        this.email = email;
        this.login = login;
        this.ativo = ativo;
    }

}
