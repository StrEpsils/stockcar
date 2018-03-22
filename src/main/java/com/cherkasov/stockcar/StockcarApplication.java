package com.cherkasov.stockcar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockcarApplication {

	private static Log log = LogFactory.getLog(StockcarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockcarApplication.class, args);
		log.info("Running app...");
	}
}
