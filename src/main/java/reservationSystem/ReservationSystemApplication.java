package reservationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reservationSystem.entity.Customer;
import reservationSystem.repository.CustomerRepository;

@SpringBootApplication
public class ReservationSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public void run(String... args) throws Exception {

	}
}
