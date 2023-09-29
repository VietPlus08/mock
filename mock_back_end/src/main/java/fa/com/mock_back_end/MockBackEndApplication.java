package fa.com.mock_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
