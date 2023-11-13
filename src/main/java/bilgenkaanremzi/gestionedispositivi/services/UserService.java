package bilgenkaanremzi.gestionedispositivi.services;

import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.exceptions.BadRequestException;
import bilgenkaanremzi.gestionedispositivi.exceptions.NotFoundException;
import bilgenkaanremzi.gestionedispositivi.payloads.NewUserDTO;
import bilgenkaanremzi.gestionedispositivi.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;
    public User save(NewUserDTO body)  {
        userRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        User newUser = new User();
        newUser.setPicture("http://ui-avatars.com/api/?name="+body.name() + "+" + body.surname());
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setPassword(body.password());
        newUser.setEmail(body.email());
        return userRepository.save(newUser);
    }

    public Page<User> GetUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }

    public User findById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public void findAndDelete(int id) throws NotFoundException{
        User found = this.findById(id);
        userRepository.delete(found);
    }

    public User findAndUpdate(int id, User body) {
        User found = this.findById(id);
        found.setId(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        return userRepository.save(found);
    }

    public String uploadPicture(MultipartFile file)throws IOException{
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("User with Email: "+email+" not Found!"));
    }
}
