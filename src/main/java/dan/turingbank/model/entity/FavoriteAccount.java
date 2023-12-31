package dan.turingbank.model.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class FavoriteAccount implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String number;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "favorites", cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

    public FavoriteAccount(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public FavoriteAccount(String number, String name, Set<Account> accounts) {
        this.number = number;
        this.name = name;
        this.accounts = accounts;
    }

 

   

}
