package pj.acs.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.azure.communication.email.*;
import com.azure.communication.email.models.*;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    
    private static final String MAIN_TEMPLATE = "main.html";
    private final EmailClient emailClient;


    @Value("${azure.communication.connection-string}")
    private String connectionString;


    public EmailController(EmailClient emailClient) {
        this.emailClient = emailClient;
    }
    
    /**
     * Add example how to make the connection with managed identity*
     */



    @PostMapping("/send")
    public ResponseEntity<String> receiveEmail(@RequestBody Email email) {
        EmailAddress toAddress = new EmailAddress(email.getTo());

        var innerTemplate = loadTemplateAndReplaceTags(email.getTemplate(), email.getTemplateData());
        var outer = loadTemplateAndReplaceTags(MAIN_TEMPLATE, Map.of("body", innerTemplate));

        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress(email.getSender())
                .setToRecipients(toAddress)
                .setSubject(email.getSubject())
                .setBodyHtml(outer);

        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
        PollResponse<EmailSendResult> result = poller.waitForCompletion();
        return ResponseEntity.ok().build();
    }

    // This method reads the template-file from resources folder and replaces the tags with the values from the map
    public String loadTemplateAndReplaceTags(String templateName, Map<String, String> replacements) {
        try {
            // Step 1 & 2: Read the HTML file and convert it to a string
            String content = new String(Files.readAllBytes(Paths.get(getClass().getResource("/" + templateName).toURI())));

            // Step 3: Iterate over the entries in the map
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                // Step 4: Replace all occurrences of the key (wrapped with <% and %>) with the value
                content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
            return content;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load and process template", e);
        }
    }

}
