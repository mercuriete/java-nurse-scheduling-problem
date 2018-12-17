package nurse;

import com.mercuriete.nurse.api.PetsApi;
import com.mercuriete.nurse.model.Pet;
import com.mercuriete.nurse.model.Pets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PetController implements PetsApi {

    @Override
    public ResponseEntity<Pets> listPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        Pet p = new Pet();
        p.setId(1L);
        p.setName("pepe");
        p.setTag("benavente");
        pets.add(p);
        Pets pets2 = new Pets();
        pets2.addAll(pets);
        return new ResponseEntity<>(pets2, new HttpHeaders(),HttpStatus.OK);
    }
}
