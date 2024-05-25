package singular.helpers;

import singular.models.requestModels.AuthorizeRequestModel;
import singular.models.responseModels.GetUserInfoResponseModel;

public class TestData {
    public static final AuthorizeRequestModel USER_CREDENTIALS =
            AuthorizeRequestModel.builder()
                    .username("testUser")
                    .password("testPass")
                    .build();

    public static final GetUserInfoResponseModel USER_INFO =
            GetUserInfoResponseModel.builder()
                    .code(10)
                    .message("SUCCESS")
                    .data(
                            GetUserInfoResponseModel.UserData.builder()
                                    .name("John")
                                    .surname("Doe")
                                    .age(30)
                                    .gender(1)
                                    .language("en")
                                    .status("registered")
                                    .isBlocked(false)
                                    .build()
                    )
                    .build();
}
