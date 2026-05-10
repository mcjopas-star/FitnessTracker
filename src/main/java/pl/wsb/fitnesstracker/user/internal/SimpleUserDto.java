package pl.wsb.fitnesstracker.user.internal;

import pl.wsb.fitnesstracker.user.api.User;

record SimpleUserDto(
        Long id,
        String firstName,
        String lastName
) {
    static SimpleUserDto from(User user) {
        return new SimpleUserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}