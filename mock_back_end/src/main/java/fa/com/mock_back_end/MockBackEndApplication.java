package fa.com.mock_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@SpringBootApplication
public class MockBackEndApplication {

    public static void main(String[] args) {
	SpringApplication.run(MockBackEndApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(NhanVienRepository nhanVienRepository, PasswordEncoder encoder) {
//	return args -> {
//	    if (nhanVienRepository.findByMaNhanVien("admin").isPresent()) {
//		return;
//	    }
//	    NhanVien nv = new NhanVien("admin", "ADMIN", encoder.encode("admin"));
//	    nhanVienRepository.save(nv);
//	};
//    }
}
