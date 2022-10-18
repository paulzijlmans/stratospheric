package com.myorg;

import dev.stratospheric.cdk.SpringBootApplicationStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;

import static java.util.Objects.requireNonNull;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

        String accountId = (String) app
                .getNode()
                .tryGetContext("accountId");
        requireNonNull(accountId, "context variable 'accountId' must not be null");
        String region = (String) app
                .getNode()
                .tryGetContext("region");
        requireNonNull(region, "context variable 'region' must not be null");
        new SpringBootApplicationStack(app,
                "SpringBootApplication",
                makeEnv(accountId, region),
                "docker.io/stratospheric/todo-app-v1:latest");

        app.synth();
    }

    static Environment makeEnv(String account, String region) {
        return Environment.builder()
                .account(account)
                .region(region)
                .build();
    }
}

