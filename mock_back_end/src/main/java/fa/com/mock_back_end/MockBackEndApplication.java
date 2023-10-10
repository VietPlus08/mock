package fa.com.mock_back_end;

import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.repository.NhanVienRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@SpringBootApplication
public class MockBackEndApplication {

    public static void main(String[] args) {
	SpringApplication.run(MockBackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner run(NhanVienRepository nhanVienRepository, PasswordEncoder encoder) {
	return args -> {
	    if (nhanVienRepository.findById("admin").isPresent()) {
		return;
	    }
	    NhanVien nv = new NhanVien("admin", "ROLE_ADMIN", encoder.encode("admin"));
	    nhanVienRepository.save(nv);
	};
    }
}
