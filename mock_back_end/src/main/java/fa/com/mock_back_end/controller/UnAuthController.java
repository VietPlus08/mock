package fa.com.mock_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fa.com.mock_back_end.dto.AuthRequest;
import fa.com.mock_back_end.dto.NhanVienDTOThangDN8;
import fa.com.mock_back_end.service.impl.JwtServiceImpl;
import fa.com.mock_back_end.service.impl.UserInforServiceImpl;

/**
 * @author ThangDN8 Class xử lý những request không yêu cầu đăng nhập
 */
@RestController
public class UnAuthController {
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private UserInforServiceImpl userInforService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcome() {
	return "Home page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	if (authentication.isAuthenticated()) {
	    return jwtService.generateToken(authRequest.getUsername());
	} else {
	    throw new UsernameNotFoundException("invalid username request!");
	}
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addNewEmployee(@RequestBody NhanVienDTOThangDN8 nhanVienDTO) {
	return userInforService.addUser(nhanVienDTO);
    }
}
