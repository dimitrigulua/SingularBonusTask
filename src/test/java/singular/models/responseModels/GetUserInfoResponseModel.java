package singular.models.responseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GetUserInfoResponseModel {
    private int code;
    private String message;
    private UserData data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserData {
        private String name;
        private String surname;
        private int age;
        private int gender;
        private String language;
        private String status;
        @JsonProperty("isBlocked")
        private boolean isBlocked;
    }
}
