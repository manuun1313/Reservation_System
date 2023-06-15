package reservationSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import reservationSystem.entity.Customer;

import java.util.List;

public interface CustomerService {
    Page<Customer> searchAndPaginateCustomers(String keyword, int pageNo, int pageSize, String sortField, String sortDir);

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Long id);

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Long id);

}
