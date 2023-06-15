package reservationSystem.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reservationSystem.entity.Customer;
import reservationSystem.repository.CustomerRepository;
import reservationSystem.service.CustomerService;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    /**
     *
     * @param keyword filter keyword
     * @param pageNo pagination page number
     * @param pageSize number of items on a page
     * @param sortField sorting field
     * @param sortDir direction of sorting
     * @return customers html page with sorted and paginated content
     */
    @Override
    public Page<Customer> searchAndPaginateCustomers(String keyword, int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.isEmpty()) {
            return customerRepository.findByKeyword(keyword, pageable);
        } else {
            return customerRepository.findAll(pageable);
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }


}
