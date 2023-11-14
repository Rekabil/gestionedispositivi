package bilgenkaanremzi.gestionedispositivi.controllers;

import bilgenkaanremzi.gestionedispositivi.entities.Device;
import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.services.DeviceService;
import bilgenkaanremzi.gestionedispositivi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserControllers {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getDevice(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String orderby){
        return userService.GetUsers(page, size, orderby);
    }

    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    }
    @PutMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal User currentUser,@RequestBody User body) {
        return (UserDetails) userService.findAndUpdate(currentUser.getId(),body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getProfile(@AuthenticationPrincipal User currentUser){
        userService.findAndDelete(currentUser.getId());
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.findById(id);
    }
    @PutMapping("/{id}")
    public User findAndUpdate(@PathVariable int id,@RequestBody User body) {
        return  userService.findAndUpdate(id,body);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int id){ userService.findAndDelete(id);}

    @PostMapping("/upload")
public String upload(@RequestParam("picture")MultipartFile body) throws IOException{
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return userService.uploadPicture(body);
    }
}
