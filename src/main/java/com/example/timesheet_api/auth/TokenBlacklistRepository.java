package com.example.timesheet_api.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<BlackListedToken , String> {
    boolean existsByToken(String token);
}
