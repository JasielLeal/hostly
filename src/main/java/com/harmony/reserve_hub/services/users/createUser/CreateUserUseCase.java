package com.harmony.reserve_hub.services.users.createUser;

import com.harmony.reserve_hub.domain.User;
import com.harmony.reserve_hub.repositories.users.UsersRepository;
import com.harmony.reserve_hub.services.email.SendEmailCreateUser;
import com.harmony.reserve_hub.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class CreateUserUseCase {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendEmailCreateUser sendEmailCreateUser;

    public CreateUserUseCase(UsersRepository usersRepository, PasswordEncoder passwordEncoder, SendEmailCreateUser sendEmailCreateUser) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailCreateUser = sendEmailCreateUser;
    }

    public User execute(CreateUserDTO createUserDTO) {

        var user = new User(createUserDTO.username(),
                createUserDTO.email(), createUserDTO.password());

        usersRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new CustomException("User already exists", HttpStatus.CONFLICT);
        });

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        String opt = generateOTP();
        user.setOptCode(opt);

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);
        user.setOptExpiration(expirationTime);

        System.out.println(user.getEmail());

        User savedUser = usersRepository.save(user);

        String body = String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5;'>" +
                        "<table align='center' width='100%%' style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden;'>" +
                        "<tr>" +
                        "<td style='color: #333333; padding: 10px; text-align: left;'>" +
                        "<p style='font-size: 24px; font-weight: bold; color: #0C41FF;'>" +
                        "Hostly" +
                        "</p>" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td style='padding: 20px;'>" +
                        "<p style='font-size: 16px; line-height: 1.5; color: #333333; font-weight: 600'>" +
                        "Olá %s," +
                        "</p>" +
                        "<p style='font-size: 16px; line-height: 1.5; color: #333333;'>" +
                        "Estamos muito felizes em tê-lo conosco! Sua conta foi criada com sucesso, e você agora pode explorar todas as funcionalidades do nosso aplicativo." +
                        "</p>" +
                        "<p style='font-size: 16px; line-height: 1.5; color: #333333;'>" +
                        "Para ativar sua conta, insira o código abaixo no aplicativo:" +
                        "</p>" +
                        "<p style='font-size: 24px; font-weight: bold; text-align: center; color: #0C41FF;'>" +
                        "%s" +
                        "</p>" +
                        "<p style='font-size: 16px; line-height: 1.5; color: #333333;'>" +
                        "Se você tiver alguma dúvida ou precisar de assistência, nossa equipe de suporte está pronta para ajudar." +
                        "</p>" +
                        "<p style='font-size: 16px; line-height: 1.5; color: #333333;'>" +
                        "Aproveite sua experiência no Hostly!" +
                        "</p>" +
                        "<p style='font-size: 16px; line-height: 1.5; color: #333333;'>" +
                        "Atenciosamente,<br>Equipe Hostly." +
                        "</p>" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td style='background-color: #f5f5f5; padding: 10px; text-align: center;'>" +
                        "<p style='font-size: 12px; color: #999999; margin: 0;'>" +
                        "&copy; %d Hostly. Todos os direitos reservados." +
                        "</p>" +
                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "</body>" +
                        "</html>",
                user.getName(), // Primeiro nome
                user.getOptCode(), // Código de verificação
                LocalDateTime.now().getYear() // Ano atual
        );



        sendEmailCreateUser.sendEmail(user.getEmail(), "Olá, seja bem-vindo ao Hostly.", body);

        return savedUser;
    }

    private String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000); // Gera um número de 6 dígitos
        return String.valueOf(otp);
    }
}
