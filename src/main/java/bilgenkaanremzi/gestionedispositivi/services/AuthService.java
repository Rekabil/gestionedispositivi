package bilgenkaanremzi.gestionedispositivi.services;

import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.exceptions.UnautorizedException;
import bilgenkaanremzi.gestionedispositivi.payloads.UserLoginDTO;
import bilgenkaanremzi.gestionedispositivi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body){
        User user = userService.findByEmail(body.email());

        if (body.password().equals(user.getPassword())){
            return jwtTools.createToken(user);
        } else {
           throw new UnautorizedException("Credentials not Valid") ;
        }
    }
}
