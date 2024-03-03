package com.digitalfuture.vacancy;

import com.digitalfuture.vacancy.routes.DailyMailingRoute;
import com.digitalfuture.vacancy.routes.MailingRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VacancyApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(VacancyApplication.class, args);
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new DailyMailingRoute());
        camelContext.addRoutes(new MailingRoute());
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }

}
