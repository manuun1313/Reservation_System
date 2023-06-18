package reservationSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reservationSystem.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Searching and pagination
     * @param keyword keyword for seaching
     * @param pageable pagination
     * @return search result
     */
    @Query("SELECT r FROM Reservation r WHERE r.name LIKE %?1%"
            + " OR r.email LIKE %?1%"
            + " OR CONCAT(r.duration, '') LIKE %?1%"
            + " OR CONCAT(r.date, '') LIKE %?1%")
    Page<Reservation> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
