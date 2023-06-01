package com.example.timesheet_api.auth;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<BlackListedToken , String> {
    boolean existsByToken(String token);
}
