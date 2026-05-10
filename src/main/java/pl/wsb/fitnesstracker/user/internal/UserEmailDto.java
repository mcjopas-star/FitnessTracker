package pl.wsb.fitnesstracker.user.internal;

import pl.wsb.fitnesstracker.user.api.User;

record UserEmailDto(
        Long id,
        String email
) {
    static UserEmailDto from(User user) {
        return new UserEmailDto(
                user.getId(),
                user.getEmail()
        );
    }
}