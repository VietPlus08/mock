package fa.com.mock_back_end.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import fa.com.mock_back_end.dto.NhanVienDTOThangDN8;

public interface UserInforService extends UserDetailsService {
    public String addUser(NhanVienDTOThangDN8 nhanVienDTO);
}
