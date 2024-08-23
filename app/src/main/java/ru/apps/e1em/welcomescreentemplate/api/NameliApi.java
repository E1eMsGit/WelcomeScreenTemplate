package ru.apps.e1em.welcomescreentemplate.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.apps.e1em.welcomescreentemplate.data.model.SignInBody;
import ru.apps.e1em.welcomescreentemplate.data.model.SignInResponse;
import ru.apps.e1em.welcomescreentemplate.data.model.SignUpBody;
import ru.apps.e1em.welcomescreentemplate.data.model.SignUpResponse;

public interface NameliApi {
    @GET("/api/v1/rest-auth/login/")
    Call<SignInResponse> getPostWithID(@Path("id") int id);

    @POST("/api/v1/rest-auth/registration/")
    Call<SignUpResponse> registerUser(@Body SignUpBody signUpBody);

    @POST("/api/v1/rest-auth/login/")
    Call<SignInResponse> signInUser(@Body SignInBody signInBody);
}
