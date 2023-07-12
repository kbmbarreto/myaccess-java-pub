package br.com.lambdateam.myaccessjava.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "passwords")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    private String description;

    @Column(nullable = false, length = 128, columnDefinition = "VARCHAR(128)")
    private String url;

    @Column(nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    private String username;

    @Column(nullable = false, length = 256, columnDefinition = "VARCHAR(256)")
    private String password;

    @Column(nullable = false, length = 128, columnDefinition = "VARCHAR(128)")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;
}