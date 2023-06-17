package pet.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PetStoreService {
    @Autowired
    private PetStoreDao petStoreDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CustomerDao customerDao;

    @Transactional(readOnly = false)
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        Long petStoreId = petStoreData.getPetStoreId();
        PetStore petStore = findOrCreatePetStore(petStoreId);

        copyPetStoreFields(petStore, petStoreData);
        return new PetStoreData(petStoreDao.save(petStore));

    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
        PetStore petStore;

        if (Objects.isNull(petStoreId)) {
            petStore = new PetStore();
        } else {
            petStore = findPetStoreById(petStoreId);
        }
        return petStore;

    }

    private PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException(
                "Pet store with ID=" + petStoreId + " does not exist."));
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setPetStoreId(petStoreData.getPetStoreId());
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());

    }

    @Transactional(readOnly = false)
    public PetStoreData.PetStoreEmployee saveEmployee(Long petStoreId, PetStoreData.PetStoreEmployee petStoreEmployee) {
        PetStore petStore = findPetStoreById(petStoreId);
        Long employeeId = petStoreEmployee.getEmployeeId();
        Employee employee = findEmployeeById(petStoreId, employeeId);

        copyEmployeeFields(employee, petStoreEmployee);

        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);
        Employee dbEmployee = employeeDao.save(employee);

        return new PetStoreData.PetStoreEmployee(dbEmployee);

    }

    private Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId).orElseThrow(() ->
                new NoSuchElementException("Employee with ID=" + employeeId + " does not exist"));
        petStoreDao.findById(petStoreId).orElseThrow(() ->
                new IllegalArgumentException("Pet store with ID=" + petStoreId + " does not exist"));
        return employee;
    }

    private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {
        Employee employee;

        if (Objects.isNull(employeeId)) {
            employee = new Employee();
        } else {
            employee = findEmployeeById(petStoreId, employeeId);
        }
        return employee;
    }

    private void copyEmployeeFields(Employee employee, PetStoreData.PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeId(petStoreEmployee.getEmployeeId());
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
    }



//    @Transactional(readOnly = false)
//    public PetStoreData.PetStoreCustomer saveCustomer(Long petStoreId, PetStoreData.PetStoreCustomer petStoreCustomer) {
//        PetStore petStore = findPetStoreById(petStoreId);
//        Long customerId = petStoreCustomer.getCustomerId();
//        Customer customer = findOrCreateCustomer(petStoreId, customerId);
//
//        copyCustomerFields(customer, petStoreCustomer);
//
//        customer.setPetStores();
//
//    }
//
//    private Customer findCustomerById(Long petStoreId, Long customerId) {
//        Customer customer = customerDao.findById(customerId).orElseThrow(() ->
//                new NoSuchElementException("Customer with ID=" + customerId + " does not exist"));
//        petStoreDao.findById(petStoreId).orElseThrow(() ->
//                new IllegalArgumentException("Pet store with ID=" + petStoreId + " does not exist"));
//        return customer;
//
//        //Note that customer and pet store have a many-to-many relationship.
//        // This means that a Customer object has a list of PetStore objects.
//        // This means that, in the method findCustomerById,
//        // you will need to loop through the list of PetStore objects looking for the pet store with the given pet store ID.
//        // If not found, throw an IllegalArgumentException.
//    }
//
//    private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {
//        Customer customer;
//
//        if (Objects.isNull(customerId)) {
//            customer = new Customer();
//        } else {
//            customer = findCustomerById(petStoreId, customerId);
//        }
//        return customer;
//    }
//
//    private void copyCustomerFields(Customer customer, PetStoreData.PetStoreCustomer petStoreCustomer) {
//        customer.setCustomerId(petStoreCustomer.getCustomerId());
//        customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
//        customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
//        customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
//    }

//    public List<PetStoreData> retrieveAllPetStores() {
//
//    }


}
