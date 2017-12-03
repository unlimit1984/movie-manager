package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.util.exception.NotFoundException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Created by Vladimir on 09.10.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        User result = userRepository.getAll()
                .stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);

        if (result == null) {
            throw new NotFoundException("User with email=" + email + " wasn't found.");
        }
        return result;
    }

    @Override
    public User save(@Nonnull User object) {
        User result = userRepository.save(object);
        if (result == null) {
            throw new NotFoundException("User " + object + " wasn't created/updated.");
        }
        return result;
    }

    @Override
    public void remove(@Nonnull User object) {
        boolean found = userRepository.delete(object);
        if (!found) {
            throw new NotFoundException("User " + object + " wasn't found for deleting.");
        }
    }

    @Override
    public User getById(@Nonnull Long id) {
        User result = userRepository.get(id);
        if (result == null) {
            throw new NotFoundException("User with id=" + id + " wasn't found.");
        }
        return result;
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }
}
