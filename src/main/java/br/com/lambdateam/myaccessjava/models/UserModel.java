package br.com.lambdateam.myaccessjava.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    private String user;

    @Column(nullable = false, columnDefinition = "VARCHAR(60)", unique = true)
    private String email;

    public byte[] storedHash;
    public byte[] storedSalt;

    public UserModel(String username, String email) {
        this.user = username;
        this.email = email;
    }
}