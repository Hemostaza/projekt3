package pl.sdacademy.projekt3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.projekt3.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
