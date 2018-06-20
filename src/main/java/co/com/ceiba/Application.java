package co.com.ceiba;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.ceiba.persistencia.repositorio.*;
import co.com.ceiba.persistencia.entidad.*;

import org.springframework.boot.CommandLineRunner;

//@EntityScan

@SpringBootApplication
public class Application{

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner demo(RepositorioParqueadero repository) {
		return (args) -> {
			// save a couple of customers
			//repository.save(new ParqueaderoEntity("Vivo", (short) 10));
			//repository.save(new ParqueaderoEntity("Vivo2", (short) 15));
			//repository.save(new ParqueaderoEntity("Vivo", (short) 17));
			//repository.save(new ParqueaderoEntity("Vivo4", (short) 50));
			
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (ParqueaderoEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
				.ifPresent(customer -> {
					log.info("Customer found with findById(1L):");
					log.info("--------------------------------");
					log.info(customer.getNombre() + " -> " + customer.getNroCeldas());
					log.info("");
				});

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByNombre("Vivo").forEach(bauer -> {
				log.info(bauer.getNombre() + " -> " + bauer.getNroCeldas());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}