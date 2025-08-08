package com.Ecommerce.demo.Service;


import com.Ecommerce.demo.Components.AESEmailAppPasswordEncryptor;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import com.Ecommerce.demo.Repository.WorkEmailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkEmailService {

    private final WorkEmailRepo workEmailRepo;
    private final AESEmailAppPasswordEncryptor aesEmailAppPasswordEncryptor;

    public ResponseEntity<?> registerEmail(String email, String appPassword) {


        String appPassTest;
        try {
            appPassword = aesEmailAppPasswordEncryptor.encrypt(appPassword); //encrypt the appPassword
            WorkEmail workEmail = new WorkEmail(email, appPassword);
            this.workEmailRepo.save(workEmail); //saave the email
            appPassTest = workEmail.getAppPassword();
        }catch (Exception e) {
            e.printStackTrace();;
        }

        return ResponseEntity.ok( appPassword);
    }
}
