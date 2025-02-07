package by.frost.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {
    String firstname;
    String lastname;
}
