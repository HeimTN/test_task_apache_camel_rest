package com.digitalfuture.vacancy.routes;

import com.digitalfuture.vacancy.services.impl.SubscribeServiceImpl;
import org.apache.camel.builder.RouteBuilder;

public class DailyMailingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("quartz2://maillTimer?cron=0+0+0+*+*+?")
                .bean(SubscribeServiceImpl.class, "subCandidates")
                .split(body())
                    .to("direct:sendEmail");
    }
}
