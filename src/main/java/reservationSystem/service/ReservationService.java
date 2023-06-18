package reservationSystem.service;

import org.springframework.data.domain.Page;
import reservationSystem.entity.Reservation;

public interface ReservationService {
    Page<Reservation> searchAndPaginateReservation(String keyword, int pageNo, int pageSize, String sortField, String sortDir);

    Reservation saveReservation(Reservation reservation);

    Reservation getReservationById(Long id);

    Reservation updateReservation(Reservation reservation);

    void deleteReservationById(Long id);

}
