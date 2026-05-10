package pl.wsb.fitnesstracker.user.internal;

import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

record UserDto(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
    static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }
}