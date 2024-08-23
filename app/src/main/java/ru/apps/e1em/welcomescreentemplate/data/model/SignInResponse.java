package ru.apps.e1em.welcomescreentemplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Модель для ответа на запрос авторизации пользователя.
 */
public class SignInResponse implements Serializable {
    @SerializedName("key")
    @Expose
    private String key;

    public String getKey() {
        return key;
    }
}
