package com.zup.crud.resources;

import com.zup.crud.entities.User;
import com.zup.crud.repository.UserRepository;
import com.zup.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> findAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable(value = "id") Long id){
        Optional<User> user = repository.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public User insert (@RequestBody User user){
        return repository.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Boolean> delete (@PathVariable(value = "id") Long id){
        Optional<User> user = repository.findById(id);

        user.isPresent();
        repository.delete(user.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deletado", Boolean.TRUE);
        return response;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update (@PathVariable(value = "id") Long id,
                                        @RequestBody User userDetails) throws Exception {

        Optional<User> optionalUser  = repository.findById(id);

        if(optionalUser.isPresent()){

            userDetails.setId(id);
            final User updatedUser = repository.save(userDetails);

            return ResponseEntity.ok().body(updatedUser);
        }else{
            throw new Exception("Erro");
        }
    }




    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> patch (@PathVariable(value = "id") Long id,
                                        @RequestBody User userDetails) throws Exception {

        Optional<User> optionalUser  = repository.findById(id);

        if(optionalUser.isPresent()){

            User user = optionalUser.get();
            if (!userDetails.getNome().isEmpty()){
                user.setNome(userDetails.getNome());
            }
            if(userDetails.getIdade() != null ){
                user.setIdade(userDetails.getIdade());
            }

            if(userDetails.getTelefone() != null){
                user.setTelefone(userDetails.getTelefone());
            }

            if(!userDetails.getEndereco().isEmpty()){
                user.setEndereco(userDetails.getEndereco());
            }

            if (!userDetails.getEmail().isEmpty()){
                user.setEmail(userDetails.getEmail());
            }

            final User updatedUser = repository.save(userDetails);

            return ResponseEntity.ok().body(updatedUser);
        }else{
            throw new Exception("Erro");
        }
    }
}
