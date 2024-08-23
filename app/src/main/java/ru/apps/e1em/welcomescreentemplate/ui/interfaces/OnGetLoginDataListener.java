package ru.apps.e1em.welcomescreentemplate.ui.interfaces;

import ru.apps.e1em.welcomescreentemplate.data.model.SignInResponse;

public interface OnGetLoginDataListener {
    void onGetLoginData(SignInResponse signInResponse);
}
