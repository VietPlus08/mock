package fa.com.mock_back_end.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "/admin/profile")
    public String admin() {
        return "Admin";
    }

    @RequestMapping(value = "staff/profile")
    public String staff() {
        return "Staff";
    }
}
