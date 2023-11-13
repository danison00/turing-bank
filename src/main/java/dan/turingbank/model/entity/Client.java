package dan.turingbank.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String name;
    private String telephone;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_fk")
    private User user;

    public Client( String cpf, String name, String email, String telephone, User user) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.user = user;
    }

}
