package ru.apps.e1em.welcomescreentemplate.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.apps.e1em.welcomescreentemplate.R;
import ru.apps.e1em.welcomescreentemplate.data.model.SignInResponse;
import ru.apps.e1em.welcomescreentemplate.ui.interfaces.OnGetLoginDataListener;
import ru.apps.e1em.welcomescreentemplate.ui.interfaces.OnSignInButtonClickListener;
import ru.apps.e1em.welcomescreentemplate.ui.interfaces.OnSignUpButtonClickListener;
import ru.apps.e1em.welcomescreentemplate.ui.welcome.WelcomeFragment;
import ru.apps.e1em.welcomescreentemplate.ui.welcome.sign_in.SignInFragment;
import ru.apps.e1em.welcomescreentemplate.ui.welcome.sign_up.SignUpFragment;
import ru.apps.e1em.welcomescreentemplate.ui.user_content.UserDataFragment;

public class MainActivity extends AppCompatActivity implements OnGetLoginDataListener, OnSignInButtonClickListener, OnSignUpButtonClickListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, new WelcomeFragment())
                    .commit();
        }
    }

    @Override
    public void OnSignInButtonClick() {
        SignInFragment signInFragment = SignInFragment.newInstance();

        fragmentManager.beginTransaction()
                .replace(R.id.container, signInFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void OnSignUpButtonClick() {
        SignUpFragment signUpFragment = SignUpFragment.newInstance();

        fragmentManager.beginTransaction()
                .replace(R.id.container, signUpFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onGetLoginData(SignInResponse signInResponse) {
        UserDataFragment userDataFragment = UserDataFragment.newInstance(signInResponse);

        fragmentManager.beginTransaction()
                .replace(R.id.container, userDataFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    /** Initializing user interface. */
    private void initUI() {
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
    }


}
