package fa.com.mock_back_end.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fa.com.mock_back_end.dto.AuthRequest;
import fa.com.mock_back_end.dto.NhanVienDTOThangDN8;
import fa.com.mock_back_end.dto.UserInforDetails;
import fa.com.mock_back_end.service.impl.JwtServiceImpl;
import fa.com.mock_back_end.service.impl.UserInforServiceImpl;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> addNewEmployee(@Valid @RequestBody NhanVienDTOThangDN8 nhanVienDTO,
	    BindingResult result) {
	Map<String, String> messageMap = new HashMap<>();
	if (!result.hasErrors() && nhanVienDTO.getMatKhau().equals(nhanVienDTO.getReMatKhau())) {
	    messageMap.put("success", userInforService.addUser(nhanVienDTO));
	    return ResponseEntity.status(HttpStatus.OK).body(messageMap);
	}
	if (result.hasErrors()) {
	    result.getFieldErrors().forEach(error -> messageMap.put(error.getField(), error.getDefaultMessage()));
	}
	// Kiểm tra mật khẩu và xác nhận mật khẩu
	if (!nhanVienDTO.getMatKhau().equals(nhanVienDTO.getReMatKhau())) {
	    messageMap.put("reMatKhau", "Xác nhận mật khẩu và mật khẩu không trùng khớp!!");
	}
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageMap);
    }
}
