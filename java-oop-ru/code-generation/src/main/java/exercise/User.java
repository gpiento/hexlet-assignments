package exercise;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
