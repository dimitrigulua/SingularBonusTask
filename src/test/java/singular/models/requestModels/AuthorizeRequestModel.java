package singular.models.requestModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthorizeRequestModel {
    private String username;
    private String password;
}
