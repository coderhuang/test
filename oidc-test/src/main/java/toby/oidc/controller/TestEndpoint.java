package toby.oidc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoint {

	
	@GetMapping("/user/{id}")
    public String obtainUser(@PathVariable String id) {
		
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "user id : " + id;
    }

}
