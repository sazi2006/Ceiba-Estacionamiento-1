package co.com.ceiba;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import co.com.ceiba.persistencia.repositorio.*;
import co.com.ceiba.persistencia.entidad.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//@EntityScan
@SpringBootApplication
public class Application{

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private RepositorioVehiculo repositorioVehiculo;
	
	@Autowired
	private RepositorioMoto repositorioMoto;
	
	@Autowired
	private RepositorioCarro repositorioCarro;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner demo(RepositorioParqueadero repository) {
		return (args) -> {
			
			//EntidadMoto moto = new EntidadMoto();
			//moto.setPlaca("IJX14E");
			//moto.setCilindrada((short) 180);
			
			//repositorioMoto.save(moto);
			
			// fetch all customers
			log.info("Motos con el metodo findAll():");
			log.info("-------------------------------");
			for (EntidadMoto moto1 : repositorioMoto.findAll()) {
				log.info(moto1.getPlaca() + " -> " + moto1.getCilindrada());
			}
			log.info("");
			
			// fetch all customers
			log.info("Carros con el metodo findAll():");
			log.info("-------------------------------");
			for (EntidadCarro carro1 : repositorioCarro.findAll()) {
				log.info(carro1.getPlaca() + " -> " + carro1.getId());
			}
			log.info("");
			
			// save a couple of customers
			/*repository.save(new EntidadParqueadero("Vivo", (short) 10));
			repository.save(new EntidadParqueadero("Vivo2", (short) 15));
			repository.save(new EntidadParqueadero("Vivo", (short) 17));
			repository.save(new EntidadParqueadero("Vivo4", (short) 50));*/
			
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (EntidadParqueadero customer : repository.findAll()) {
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