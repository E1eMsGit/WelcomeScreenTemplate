package ru.apps.e1em.welcomescreentemplate.data.model;

/**
 * Модель для запроса на авторизацию пользователя.
 */
public class SignInBody {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
