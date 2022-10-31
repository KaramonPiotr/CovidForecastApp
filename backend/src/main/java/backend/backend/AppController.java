package backend.backend;





import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@Slf4j
public class AppController {
    @Autowired
    AppRepository appRepository;

    @Autowired
    AppService appService;

    // @GetMapping("/getAll")
    // public ResponseEntity<Iterable<AppModel>> getAll(){
    //     return ResponseEntity.ok().body(appRepository.getAllLastName());
    // }

    @GetMapping("/getByAppModelName/{name}")
    public ResponseEntity<Iterable<String>> getByName(@PathVariable(value = "name") String name){
        List<AppModel> findAppModelByName = appRepository.findAppModelByName(name);
        List<String> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            returnAppModelNameList.add(findAppModelByName.get(i).name);
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }
    
    @GetMapping("/getPiByAppModelName/{name}")
    public ResponseEntity<Iterable<Double>> getPiByName(@PathVariable(value = "name") String name){
        List<AppModel> findAppModelByName = appRepository.findAppModelByName(name);
        List<Double> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            returnAppModelNameList.add(findAppModelByName.get(i).Pi);
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }
       
    @GetMapping("/getDaysByAppModelName/{name}")
    public ResponseEntity<Iterable<Double>> getDaysByName(@PathVariable(value = "name") String name){
        List<AppModel> findAppModelByName = appRepository.findAppModelByName(name);
        List<Double> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            returnAppModelNameList.add(findAppModelByName.get(i).day);
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }

    @GetMapping("/getPvByAppModelName/{name}")
    public ResponseEntity<Iterable<Double>> getPvByName(@PathVariable(value = "name") String name){
        List<AppModel> findAppModelByName = appRepository.findAppModelByName(name);
        List<Double> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            returnAppModelNameList.add(findAppModelByName.get(i).Pv);
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }
    
     @GetMapping("/getPmByAppModelName/{name}")
    public ResponseEntity<Iterable<Double>> getPmByName(@PathVariable(value = "name") String name){
        List<AppModel> findAppModelByName = appRepository.findAppModelByName(name);
        List<Double> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            returnAppModelNameList.add(findAppModelByName.get(i).Pm);
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }
    
    @GetMapping("/getPrByAppModelName/{name}")
    public ResponseEntity<Iterable<Double>> getPrByName(@PathVariable(value = "name") String name){
        List<AppModel> findAppModelByName = appRepository.findAppModelByName(name);
        List<Double> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            returnAppModelNameList.add(findAppModelByName.get(i).Pr);
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }

    @GetMapping("/getAllNames")
    public ResponseEntity<Iterable<String>> getAllNames(){
        List<AppModel> findAppModelByName = (List<AppModel>)appRepository.findAllByOrderByName();
        List<String> returnAppModelNameList = new ArrayList<>();
        for(int i = 0; i < findAppModelByName.size(); i++) {
            if(!returnAppModelNameList.contains(findAppModelByName.get(i).name)){
                returnAppModelNameList.add(findAppModelByName.get(i).name);
            }
        }
        return ResponseEntity.ok().body(returnAppModelNameList);
    }
    
    
 
    @PostMapping("/createNewSimulation/{name}/{P}/{I}/{R}/{M}/{Ti}/{Tm}/{Ts}")
    public ResponseEntity<String> createNewSimulation(
        @Valid @RequestBody
        @PathVariable(value = "name") String name,
        @PathVariable(value = "P") Double P,
        @PathVariable(value = "I") Double I,
        @PathVariable(value = "R") Double R,
        @PathVariable(value = "M") Double M,
        @PathVariable(value = "Ti") Double Ti,
        @PathVariable(value = "Tm") Double Tm,
        @PathVariable(value = "Ts") Double Ts
        ){
        if(appRepository.existsByName(name)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Symulacja o tej nazwie istnieje");
        }
        else{
            appService.create(name, P, I, R, M, Ti, Tm, Ts);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/createInitialllllll")
    public AppModel createTicket(@Valid @RequestBody AppModel  newAppModel){
        return appRepository.save(newAppModel);
    }
}
