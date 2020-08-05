package toby.oidc.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@GetMapping("/me")
	public Principal user(Principal principal) {

		return principal;
	}
}
