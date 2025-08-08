package com.Ecommerce.demo.Controller.WorkEmailController;

import com.Ecommerce.demo.Service.WorkEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkEmailController {

    private final WorkEmailService workEmailService;

    @PostMapping("/platform-email-registration")
    public ResponseEntity<?> registerWorkEmail(
            @RequestParam final String email,
            @RequestParam final String appPassword) {
        return this.workEmailService.registerEmail(email, appPassword);
    }
}
