package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
    @Autowired
    private PetStoreService petStoreService;

    @PostMapping("/pet_store")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Creating store with ID={}", petStoreData);

        return petStoreService.savePetStore(petStoreData);
    }

    @PutMapping("/pet_store/{petStoreId}")
    public PetStoreData modifyPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        petStoreData.setPetStoreId(petStoreId);
        log.info("Updating petStore {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }
}
