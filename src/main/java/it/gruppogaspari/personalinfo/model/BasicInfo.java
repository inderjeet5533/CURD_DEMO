package it.gruppogaspari.personalinfo.model;

import it.gruppogaspari.personalinfo.constants.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfo {

    private int infoId;
    @NotBlank
    @Pattern(regexp = Constants.STRING_REGEX, message = Constants.FIRSTNAME_ERROR_MSG)
    private String firstName;
    @NotBlank
    @Pattern(regexp = Constants.STRING_REGEX, message = Constants.LASTNAME_ERROR_MSG)
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = Constants.PHONE_REGEX, message = Constants.PHONE_ERROR_MSG)
    private String contact;
    @Valid
    private Address address;

}
