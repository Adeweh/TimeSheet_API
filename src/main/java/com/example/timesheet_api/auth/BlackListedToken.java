package com.example.timesheet_api.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BlackListedToken {
    @Id
    @UuidGenerator
    private String id;
    @NonNull
    private String token;
    public BlackListedToken(){}
}
