package backend.backend;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import backend.backend.AppRepository;


@Service
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class AppService {

    @Autowired
    AppRepository appRepository;
    
    public void create(
        String name, //nazwa symulacji 
        Double P, //wielkosc populacji 
        Double I, //początkowa liczba zarażonych 
        Double R, // - wskaźnik określający ile osób zaraża jedna zarażona osoba, czyli znany z newsów covidowych wskaźnik R
        Double M, //wskaźnik śmiertelności, określający ilu spośród zarażonych umiera
        Double Ti, //ilość dni, która upływa od momentu zarażenia do wyzdrowienia chorego
        Double Tm, //- ilość dni, która upływa od momentu zarażenia do śmierci chorego
        Double Ts //- Ilość dni, dla których ma być przeprowadzona symulacja
        // Pi liczba osób zarażonych
        // Pv liczba osób zdrowych, podatnych na infejceję
        // Pm liczba osób zmarłych 
        // Pr liczba osób, które wyzdrowiały i nabyły odposrność  
        
    )
    {
        AppModel initialDay = new AppModel(name, 0D, I, P, 0D, 0D);
        appRepository.save(initialDay);
        for(Integer i=0;i<Ts;i++){
        
            Double Pi = appRepository.findAppModelByNameAndDay(name,Double.valueOf(i)).Pi;
            Double Pv = appRepository.findAppModelByNameAndDay(name,Double.valueOf(i)).Pv;
            Double Pm = appRepository.findAppModelByNameAndDay(name,Double.valueOf(i)).Pm;
            Double Pr = appRepository.findAppModelByNameAndDay(name,Double.valueOf(i)).Pm;
          
            //M spośród zarażonych Tm dni wcześniej przenosi się niestety z grupy osób zarażonych do grupy osób zmarłych
            if(Tm<=i){
                Pm = Pm + (M * appRepository.findAppModelByNameAndDay(name, i - Tm).Pi);
            }
            //Wszystkie osoby zarażone Ti dni wcześniej (i żywe...) przenoszą się z grupy osób zarażonych do grupy osób, które wyzdrowiały i nabyły odporność        
            if(Ti<=i){
                Pr = Pr + (appRepository.findAppModelByNameAndDay(name, i - Ti).Pi - ( M *appRepository.findAppModelByNameAndDay(name, i - Ti).Pi));
            }
            //Każda zarażona osoba zaraża R zdrowych podatnych na infekcję osób
                Pi = appRepository.findAppModelByNameAndDay(name,Double.valueOf(i)).Pi*M;
            //Liczba osób zdrowych podatnych na infekcję 
                Pv = Pv - (Pm+Pr+Pi);
                
            AppModel newDay = new AppModel(name,Double.valueOf(i+1) , Pi, Pv, Pm, Pr);
            appRepository.save(newDay);
        }

    }
}

