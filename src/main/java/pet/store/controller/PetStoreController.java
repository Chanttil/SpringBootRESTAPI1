package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.store.controller.model.PetStoreData;
import pet.store.entity.Customer;
import pet.store.service.PetStoreService;

import java.util.List;

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

    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreData.PetStoreEmployee insertEmployee(@PathVariable Long petStoreId,
                                                        @RequestBody PetStoreData.PetStoreEmployee petStoreEmployee) {

        log.info("Creating Employee {}", petStoreEmployee);

        return petStoreService.saveEmployee(petStoreId, petStoreEmployee);

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
//    @GetMapping("/pet_store")
//    public List<PetStoreData> getAllPetStores() {
//        return petStoreService.retrieveAllPetStores();
//    }
//
}
