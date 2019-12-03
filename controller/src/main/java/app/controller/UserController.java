package app.controller;

import app.config.TokenProvider;
import app.dto.UserDTO;
import app.model.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

//@CrossOrigin(origins="http://angularjs:8080")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtTokenUtil;

    //BEING USED
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User lUser = userService.findByUsername(user.getUsername());
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(lUser.getId());
        userDTO.setUsername(lUser.getUsername());
        userDTO.setRole(lUser.getRole().getName());
        userDTO.setToken(jwtTokenUtil.generateToken(authentication));

        return ResponseEntity.ok(userDTO);
    }

    //BEING USED
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody User user) {
        User u = userService.findByUsername(user.getUsername());
        if (u != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!");
        }

        return ResponseEntity.ok(userService.save(user));
    }

}
