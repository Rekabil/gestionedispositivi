package bilgenkaanremzi.gestionedispositivi.controllers;

import bilgenkaanremzi.gestionedispositivi.entities.Device;
import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.services.DeviceService;
import bilgenkaanremzi.gestionedispositivi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getDevice(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String orderby){
        return userService.GetUsers(page, size, orderby);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveDevice(@RequestBody User body) {return userService.save(body);}

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


}
