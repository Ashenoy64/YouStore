package inf_storage.inf_storage;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inf_storage.Youtube.YoutubeController;

@SpringBootApplication
@RestController
public class InfStorageApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(InfStorageApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
	}

	@GetMapping("/upload")
	public String hello() {
		YoutubeController.uploadVideo();
		return "Hello World";
	}



}
