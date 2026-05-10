package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.user.api.User;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class UserService {

    private final UserRepository userRepository;

    List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::from)
                .toList();
    }

    List<SimpleUserDto> findAllSimple() {
        return userRepository.findAll().stream()
                .map(SimpleUserDto::from)
                .toList();
    }

    UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        return UserDto.from(user);
    }

    List<UserEmailDto> findByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email).stream()
                .map(UserEmailDto::from)
                .toList();
    }

    List<UserDto> findOlderThan(LocalDate date) {
        return userRepository.findOlderThan(date).stream()
                .map(UserDto::from)
                .toList();
    }

    UserDto create(UserRequest request) {
        User user = new User(
                request.firstName(),
                request.lastName(),
                request.birthdate(),
                request.email()
        );
        User saved = userRepository.save(user);
        return UserDto.from(saved);
    }

    void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    void update(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        setField(user, "firstName", request.firstName());
        setField(user, "lastName", request.lastName());
        setField(user, "birthdate", request.birthdate());
        setField(user, "email", request.email());
    }

    private void setField(User user, String fieldName, Object value) {
        try {
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(user, value);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot update field " + fieldName, e);
        }
    }
}
