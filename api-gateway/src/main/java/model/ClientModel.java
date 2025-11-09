package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a client entity fetched from the client-service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String address;
}
