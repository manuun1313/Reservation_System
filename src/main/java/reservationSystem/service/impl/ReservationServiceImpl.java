package reservationSystem.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reservationSystem.entity.Reservation;
import reservationSystem.repository.ReservationRepository;
import reservationSystem.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        super();
        this.reservationRepository = reservationRepository;
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
    public Page<Reservation> searchAndPaginateReservation(String keyword, int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.isEmpty()) {
            return reservationRepository.findByKeyword(keyword, pageable);
        } else {
            return reservationRepository.findAll(pageable);
        }
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }


}
