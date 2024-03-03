package com.digitalfuture.vacancy.routes;

import com.digitalfuture.vacancy.dto.MailingDTO;
import com.digitalfuture.vacancy.services.impl.SubscribeServiceImpl;
import org.apache.camel.builder.RouteBuilder;

public class MailingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:sendEmail")
                .bean(SubscribeServiceImpl.class, "createMailData(${body})")
                .split(body())
                    .setHeader("subjects", constant("Новые вакансии от Цифровое будущее!"))
                    .setHeader("to", method(MailingDTO.class, "getCandidate().getEmail()"))
                    .setHeader("from", constant("sender@exemple.com"))
                    .bean(SubscribeServiceImpl.class, "buildEmailBody(${body})")
                    .to("smtps://smtp.example.com:465?username=test&password=test");
    }
}
