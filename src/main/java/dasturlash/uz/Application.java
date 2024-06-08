package dasturlash.uz;

import dasturlash.uz.util.MD5Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		String key = UUID.randomUUID().toString();
		System.out.println(key);
	}
}
