package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import oqu.today.initital.model.JWTUtil;
import oqu.today.initital.model.User;
import oqu.today.initital.payload.EmailService;
import oqu.today.initital.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/javaApi/api/Profile")
public class UserRestController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    final String localhost = "https://45.80.70.68";
//    final String localhost = "http://localhost:3000";

    @Autowired
    UserRepository userRepository;

    @CrossOrigin(localhost)
    @PostMapping(value = "/checklogin" ,produces = "application/json")
    public String checkLogin(@RequestBody User user) throws JSONException {
        try {
            User usr = userRepository.findByEmailPassword(user.getEmail(), user.getPassword());
            String token = jwtUtil.generateToken(user);
            return "{\n" + "\"user_id\": \"" + usr.getId() + "\",\n" +
                    "\"token\": \"" + token + "\",\n" +
                    "\"name\": \"" + usr.getName() + "\"\n" + "}";
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", e);
        }
    }
    @CrossOrigin(localhost)
    @PostMapping(value = "/create" ,produces = "application/json")
    public String createLogin(@RequestBody User user) throws JSONException {
        if(userRepository.findByEmail(user.getEmail()) == null){
            if(userRepository.findByEmailPassword(user.getEmail(), user.getPassword()) == null){
                emailService.sendSimpleMessage(user.getEmail(),"Регистрация на сайте Oqu.Today","Вы успешно зарегестрировались на сайте oqu.today");
                userRepository.save(new User(user.getName(),user.getEmail(),user.getPassword()));
                return "{ \"Message\": " + "\"Successfull Registration\" }";
            }
        }
        return "{ \"Message\": " + "\"Successfull Update\" }";
    }
    @CrossOrigin(localhost)
    @GetMapping(value = "/read", produces = "application/json")
    public String getUser(@RequestParam(name = "id") long id){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = userRepository.findById(id);
        String element = gson.toJson(user,User.class);
        return element == null ? "[]" : element;
    }

    @CrossOrigin(localhost)
    @PostMapping(value = "/edit", produces = "application/json")
    public String update(@RequestBody User newUser){
        System.out.println(newUser.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = userRepository.findById(newUser.getId());
        if(!user.getPassword().equals(newUser.getPassword())){
            return "{ \"Message\": " + "\"Password doesn't equal\" }";
        }
        userRepository.updateUser(newUser.getId(),newUser.getName(),newUser.getEmail(),newUser.getPassword(),newUser.getGender(),newUser.getAvatar(),newUser.getBday(),newUser.getPhone());
        return "{ \"Message\": " + "\"Successfull Update\" }";
    }
}