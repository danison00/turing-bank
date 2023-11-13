package dan.turingbank.model.entity;
import lombok.Getter;

@Getter
public enum TypeAccount{

    CURRENT(1, "current"), SAVINGS(2, "savings");


    private int code;
    private String type;

    TypeAccount(int code, String type){
        this.code = code;
        this.type = type;
    }



}
