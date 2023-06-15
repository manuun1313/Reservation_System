package reservationSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reservationSystem.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Searching and pagination
     * @param keyword keyword for seaching
     * @param pageable pagination
     * @return search result
     */
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %?1%"
            + " OR c.email LIKE %?1%"
            + " OR CONCAT(c.duration, '') LIKE %?1%"
            + " OR CONCAT(c.date, '') LIKE %?1%")
    Page<Customer> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
