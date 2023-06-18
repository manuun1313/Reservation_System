package reservationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reservationSystem.repository.ReservationRepository;

@SpringBootApplication
public class ReservationSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

	@Autowired
	private ReservationRepository reservationRepository;
	@Override
	public void run(String... args) throws Exception {

	}
}
