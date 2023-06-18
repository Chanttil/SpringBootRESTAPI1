package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
    @Autowired
    private PetStoreService petStoreService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Creating store with ID={}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    @PutMapping("/{petStoreId}")
    public PetStoreData modifyPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        petStoreData.setPetStoreId(petStoreId);
        log.info("Updating petStore {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    @PostMapping("/pet_store/{petStoreId}/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreEmployee insertEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {

        log.info("Creating pet store employee {}", petStoreEmployee);

        PetStoreEmployee employee = petStoreService.saveEmployee(petStoreId, petStoreEmployee);

        return employee;
    }

//    @PostMapping("/pet_store/{petStoreId}/customer")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public PetStoreData.PetStoreCustomer insertCustomer(@PathVariable Long petStoreId,
//                                                        @RequestBody PetStoreData.PetStoreCustomer petStoreCustomer) {
//
//        log.info("Creating Customer {} ", petStoreCustomer);
//
//        return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
//}
    @GetMapping
    public List<PetStoreData> getAllPetStores() {
        return petStoreService.retrieveAllPetStores();
    }

//    @GetMapping("/{petStoreId}")
//    public PetStoreData getPetStoreById(@PathVariable("petStoreId") Long petStoreId) {
//        return petStoreService.retrievePetStoreById(petStoreId);
//    }

}
