package org.example.chery_payment.repository;

import org.example.chery_payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
