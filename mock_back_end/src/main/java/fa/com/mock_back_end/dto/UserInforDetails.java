package fa.com.mock_back_end.dto;

import fa.com.mock_back_end.entity.NhanVien;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class dùng để lấy giá trị từ entity và tạo thành 1 đối tượng UserDetails cho
 * Spring security sử dụng.
 *
 * @author ThangDN8
 */
public class UserInforDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInforDetails(NhanVien nvInfor) {
	username = nvInfor.getMaNhanVien();
	password = nvInfor.getMatKhau();
	authorities = Arrays.stream(nvInfor.getChucVu().split(",")).map(SimpleGrantedAuthority::new)
		.collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return authorities;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }
}
