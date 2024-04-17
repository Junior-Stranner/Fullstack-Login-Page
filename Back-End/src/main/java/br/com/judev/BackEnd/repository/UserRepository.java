package br.com.judev.BackEnd.repository;

import br.com.judev.BackEnd.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , String> {
}
