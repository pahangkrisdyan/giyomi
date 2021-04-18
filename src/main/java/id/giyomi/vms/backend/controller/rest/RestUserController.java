package id.giyomi.vms.backend.controller.rest;

import id.giyomi.vms.backend.controller.rest.model.ReqUser;
import id.giyomi.vms.backend.controller.rest.model.ResUser;
import id.giyomi.vms.backend.service.UserService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class RestUserController {

    private UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userId}")
    private ResponseEntity<ResUser> getUser(@PathVariable Long userId) {
        try{
            id.giyomi.vms.backend.entity.User user = userService.getUser(userId);
            return ResponseEntity.ok(new ResUser(user));
        }catch (ResourceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    private ResponseEntity<List<ResUser>> getUsers(@RequestParam(required = false) String role) {
        List<ResUser> response = new ArrayList<>();
        try{
            List<id.giyomi.vms.backend.entity.User> staffs = userService.getUsers(role);
            staffs.forEach(user -> {
                response.add(new ResUser(user));
            });
            return ResponseEntity.ok(response);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    private ResponseEntity<ResUser> createuser(@RequestBody ReqUser reqUser){
        return ResponseEntity.ok(new ResUser(userService.createUser(reqUser)));
    }

    @PutMapping(value = "/{userId}")
    private ResponseEntity<ResUser> editUser(@RequestBody ReqUser reqUser, @PathVariable Long userId){
        return ResponseEntity.ok(new ResUser(userService.editUser(reqUser, userId)));
    }

    @DeleteMapping(value = "/{userId}")
    private ResponseEntity<String> deleteUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
