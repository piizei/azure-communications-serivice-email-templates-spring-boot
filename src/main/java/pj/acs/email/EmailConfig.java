package pj.acs.email;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    private static final Logger logger = LoggerFactory.getLogger(EmailConfig.class);

    @Value("${azure.communication.connection-string}")
    private String connectionString;
    @Value("${azure.communication.endpoint}")
    private String endpoint;


    @Bean
    public EmailClient emailClient() {

        if (connectionString == null || connectionString.isEmpty()) {
            logger.info("Connection string is not set. Using Managed Identity to authenticate.");
            return new EmailClientBuilder().credential(new DefaultAzureCredentialBuilder().build()).endpoint(endpoint).buildClient();
        } else {
            logger.info("Using connection string to authenticate");
            return new EmailClientBuilder().connectionString(connectionString).buildClient();
        }
    }
}