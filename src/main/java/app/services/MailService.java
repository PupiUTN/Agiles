package app.services;

import app.models.entities.User;
import app.utils.MailType;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;

@Service
public class MailService {
    @Value("${app.aws.ses.accessKey}")
    private String accesskey;

    @Value("${app.aws.ses.secretKey}")
    private String secretKey;

    public void sendEmail(User user, MailType type) {

        try {
            AmazonSimpleEmailService client = getClient();
            String FROM = "reservas@pupi.com.ar";
            SendEmailRequest sendEmailRequest = createEmailRequest(FROM, user.getEmail(), type, user.getUsername());
            SendEmailResult sendEmailResult = client.sendEmail(sendEmailRequest);
            if (sendEmailResult.getSdkHttpMetadata()
                    .getHttpStatusCode() != 200) {
                throw new ServerException("Error when trying to send email");
            }
        } catch (Exception e) {
            throw new AmazonSimpleEmailServiceException(e.toString());
        }
    }

    private static SendEmailRequest createEmailRequest(String from, String to, MailType type, String username) {

        String emailTemplate = type.getMailTemplate(username);
        Destination destination = new Destination().withToAddresses(to);
        Content subjectContent = new Content().withData(type.getMailSubject());
        Content textContent = new Content().withData(emailTemplate);
        Body body = new Body().withHtml(textContent);
        Message message = new Message().withSubject(subjectContent)
                .withBody(body);

        return new SendEmailRequest().withSource(from)
                .withDestination(destination)
                .withMessage(message);
    }


    @Bean
    private AmazonSimpleEmailService getClient() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accesskey, secretKey);
        AmazonSimpleEmailServiceClientBuilder builder = AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1.getName())
                .withClientConfiguration(
                        new ClientConfiguration()
                                .withConnectionTimeout(3000)
                                .withRequestTimeout(3000));

        return builder.build();
    }
}
