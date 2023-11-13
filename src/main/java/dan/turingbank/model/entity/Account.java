package dan.turingbank.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    private BigDecimal balance;

    private TypeAccount type;

    @Column(columnDefinition = "DATE")
    private LocalDate openingDate;

    @JsonIgnore
    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transferSend;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transferReceived;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deposit> deposits;

    @ManyToMany
    @JoinTable(name = "Favorites", joinColumns = @JoinColumn(name = "account_id_fk"), inverseJoinColumns = @JoinColumn(name = "favorite_id"))
    private Set<Account> favorites;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id_fk")
    private Client client;

    public Account(TypeAccount type) {
        this.type = type;
        this.balance = BigDecimal.valueOf(0);
    }

}