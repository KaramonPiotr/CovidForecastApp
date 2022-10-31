package backend.backend;





import java.io.File;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

@Data
@Document("model")
public class AppModel {
       
        @Id
        private String id;
        
        String name;
        Double day;
        Double Pi; //liczba osób zarażonych
        Double Pv; //liczba osób zdrowych, podatnych na infejceję
        Double Pm; //liczba osób zmarłych 
        Double Pr; //liczba osób, które wyzdrowiały i nabyły odposrność 
        
        AppModel(String name,Double day,Double Pi, Double Pv, Double Pm,Double Pr){
                this.name = name;
                this.day = day;
                this.Pi = Pi;
                this.Pv = Pv;
                this.Pm = Pm;
                this.Pr = Pr;
        }
}
