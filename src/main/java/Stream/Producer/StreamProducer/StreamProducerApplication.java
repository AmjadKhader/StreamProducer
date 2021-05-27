package Stream.Producer.StreamProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
public class StreamProducerApplication {

	@Autowired
	private MessageChannel output;

	@GetMapping("/")
	public void produceMessage() {
		this.output.send(
				MessageBuilder
						.withPayload("walla?")
						.setHeader("my header", "amjad")
						.build());
	}

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(StreamProducerApplication.class);
		application.setDefaultProperties(Collections.singletonMap("server.port", "8084"));
		application.run(args);
	}
}
