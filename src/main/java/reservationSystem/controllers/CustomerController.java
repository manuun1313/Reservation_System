package reservationSystem.controllers;

import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reservationSystem.entity.Customer;
import reservationSystem.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    /**
     * View Home page method
     * @param model
     * @param keyword filter keyword
     * @param pageNo pagination page number
     * @param pageSize number of items on a page
     * @param sortField sorting field
     * @param sortDir direction of sorting
     * @return customers html page with sorted and paginated content
     */
    @GetMapping("/customers")
    public String viewHomePage(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                               @RequestParam(value = "sortField", required = false, defaultValue = "date") String sortField,
                               @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        Page<Customer> page = customerService.searchAndPaginateCustomers(keyword, pageNo, pageSize, sortField, sortDir);
        List<Customer> listCustomers = page.getContent();

        model.addAttribute("listCustomers", listCustomers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "customers";
    }

    /**
     * create reservation form
     * @param model
     * @return create reservation html page
     */
    @GetMapping("/createReservationForm")
    public String createReservationForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "create_reservation";
    }

    /**
     * save customer to database
     * @param customer customer
     * @return save customer to database and redirect to main page
     */
    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";

    }

    /**
     * edit customer info in database based on id
     * @param id id of customer
     * @param model
     * @return edit customer html page
     */
    @GetMapping("/customers/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer",customerService.getCustomerById(id));
        return "edit_customer";
    }

    /**
     * Handler for update customer form request
     * @param id id of customer
     * @param customer customer
     * @param model
     * @return update customer to database and redirect to main page
     */
    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute("customer") Customer customer, Model model) {
        // getting customer from database by id

        Customer existingCustomer = customerService.getCustomerById(id);
        existingCustomer.setId(id);
        existingCustomer.setName(customer.getName());
        existingCustomer.setDate(customer.getDate());
        existingCustomer.setDuration(customer.getDuration());
        existingCustomer.setEmail(customer.getEmail());


        // save updated customer object
        customerService.updateCustomer(existingCustomer);
        return "redirect:/customers";
    }

    /**
     * Delete button handler
     * @param id delete customer from databse based on id
     * @return redirect to main html page
     */
    @GetMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }
}
