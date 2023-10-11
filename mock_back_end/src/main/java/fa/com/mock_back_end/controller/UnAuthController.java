package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.dto.AuthRequest;
import fa.com.mock_back_end.dto.NhanVienDTOThangDN8;
import fa.com.mock_back_end.dto.UserInforDetails;
import fa.com.mock_back_end.service.impl.JwtServiceImpl;
import fa.com.mock_back_end.service.impl.UserInforServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThangDN8 Class xử lý những request không yêu cầu đăng nhập
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UnAuthController {
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private UserInforServiceImpl userInforService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserInforDetails) authentication.getPrincipal();
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("invalid username request!");
        }
    }

    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public ResponseEntity<?> addNewEmployee(@Valid @RequestBody NhanVienDTOThangDN8 nhanVienDTO,
                                                              BindingResult result) {
        boolean flagError = true;
        Map<String, String> messageMap = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> messageMap.put(error.getField(), error.getDefaultMessage()));
            flagError =false;
        }

        if (nhanVienDTO.getNgaySinh().plusYears(18).isAfter(LocalDate.now())) {
            messageMap.put("ngaySinh", "Nhân viên phải từ 18 tuổi trở lên");
            flagError =false;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (!nhanVienDTO.getPassword().equals(nhanVienDTO.getRePassword())) {
            messageMap.put("reMatKhau", "Xác nhận mật khẩu và mật khẩu không trùng khớp!!");
            flagError =false;
        }

        if (flagError) {
            messageMap.put("maNhanVien", userInforService.addUser(nhanVienDTO));
            return ResponseEntity.status(HttpStatus.OK).body(messageMap);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageMap);
    }
}
