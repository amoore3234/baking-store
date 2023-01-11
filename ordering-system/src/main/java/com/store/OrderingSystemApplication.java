package com.store;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class OrderingSystemApplication extends Application<OrderingSystemConfiguration> {

    public static void main(final String[] args) throws Exception {
        new OrderingSystemApplication().run(args);
    }

    @Override
    public String getName() {
        return "OrderingSystem";
    }

    @Override
    public void initialize(final Bootstrap<OrderingSystemConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final OrderingSystemConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
