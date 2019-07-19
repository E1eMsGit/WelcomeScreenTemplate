package ru.apps.e1em.welcomescreentemplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Модель для ответа на запрос регистрации пользователя.
 */
public class SignUpResponse {
    @SerializedName("detail")
    @Expose
    private String detail;

    public String getDetail() {
        return detail;
    }
}
