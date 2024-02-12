package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Infra.securityHandler.CreateTokenJWTDTO;
import med.voll.api.Infra.securityHandler.TokenService;
import med.voll.api.domain.user.AuthenticationUserDTO;
import med.voll.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<Object> Login(@RequestBody @Valid AuthenticationUserDTO userDTO){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password());
            Authentication authentication = manager.authenticate(authenticationToken);

            var tokenJwt = tokenService.GenerateToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new CreateTokenJWTDTO(tokenJwt));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
