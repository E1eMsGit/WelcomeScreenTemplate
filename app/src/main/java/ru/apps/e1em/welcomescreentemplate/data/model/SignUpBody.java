package ru.apps.e1em.welcomescreentemplate.data.model;

/**
 * Модель для запроса на регистрацию пользователя.
 */
public class SignUpBody {

    private String username;
    private String email;
    private String password1;
    private String password2;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
