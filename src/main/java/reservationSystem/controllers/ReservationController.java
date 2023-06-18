package reservationSystem.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reservationSystem.entity.Reservation;
import reservationSystem.service.ReservationService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        super();
        this.reservationService = reservationService;
    }

    /**
     * View Home page
     * @param model
     * @param keyword filter keyword
     * @param pageNo pagination page number
     * @param pageSize number of items on a page
     * @param sortField sorting field
     * @param sortDir direction of sorting
     * @return customers html page with sorted and paginated content
     */
    @GetMapping("/reservations")
    public String viewHomePage(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                               @RequestParam(value = "sortField", required = false, defaultValue = "date") String sortField,
                               @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        Page<Reservation> page = reservationService.searchAndPaginateReservation(keyword, pageNo, pageSize, sortField, sortDir);
        List<Reservation> listReservations = page.getContent();

        // Format the date for each customer
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (Reservation reservation : listReservations) {
            String formattedDate = reservation.getDate().format(dateFormatter);
            reservation.setFormattedDate(formattedDate);
        }

        model.addAttribute("listReservations", listReservations);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "reservations";
    }

    /**
     * create reservation form
     * @param model
     * @return create reservation html page
     */
    @GetMapping("/createReservationForm")
    public String createReservationForm(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        return "create_reservation";
    }

    /**
     * save customer to database
     * @param reservation customer
     * @return save customer to database and redirect to main page
     */
    @PostMapping("/reservations")
    public String saveCustomer(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";

    }

    /**
     * edit customer info in database based on id
     * @param id id of customer
     * @param model
     * @return edit customer html page
     */
    @GetMapping("/reservations/edit/{id}")
    public String editReservationForm(@PathVariable Long id, Model model) {
        model.addAttribute("reservation", reservationService.getReservationById(id));
        return "edit_reservation";
    }

    /**
     * Handler for update customer form request
     * @param id id of customer
     * @param reservation customer
     * @param model
     * @return update customer to database and redirect to main page
     */
    @PostMapping("/reservations/{id}")
    public String updateReservation(@PathVariable Long id, @ModelAttribute("reservation") Reservation reservation, Model model) {
        // getting customer from database by id

        Reservation existingReservation = reservationService.getReservationById(id);
        existingReservation.setId(id);
        existingReservation.setName(reservation.getName());
        existingReservation.setDate(reservation.getDate());
        existingReservation.setDuration(reservation.getDuration());
        existingReservation.setEmail(reservation.getEmail());


        // save updated customer object
        reservationService.updateReservation(existingReservation);
        return "redirect:/reservations";
    }

    /**
     * Delete button handler
     * @param id delete customer from databse based on id
     * @return redirect to main html page
     */
    @GetMapping("/reservations/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return "redirect:/reservations";
    }
}
