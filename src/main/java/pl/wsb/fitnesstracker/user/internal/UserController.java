package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping
    List<SimpleUserDto> getAllUsers() {
        return userService.findAllSimple();
    }

    @GetMapping("/simple")
    List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllSimple();
    }

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/email")
    List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/older/{time}")
    List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findOlderThan(time);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto addUser(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    void updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        userService.update(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}