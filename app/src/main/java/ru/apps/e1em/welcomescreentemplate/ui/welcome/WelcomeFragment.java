package ru.apps.e1em.welcomescreentemplate.ui.welcome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.apps.e1em.welcomescreentemplate.R;
import ru.apps.e1em.welcomescreentemplate.ui.interfaces.OnSignInButtonClickListener;
import ru.apps.e1em.welcomescreentemplate.ui.interfaces.OnSignUpButtonClickListener;

public class WelcomeFragment extends Fragment {

    private OnSignInButtonClickListener callbackActivityFromOnSignInButtonClick;
    private OnSignUpButtonClickListener callbackActivityFromOnSignUpButtonClick;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Связь фрагмента с активностью.
        callbackActivityFromOnSignInButtonClick = (OnSignInButtonClickListener) context;
        callbackActivityFromOnSignUpButtonClick = (OnSignUpButtonClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_welcome, container, false);

        initViews(root);

        return root;
    }

    /** Initializing Views. */
    private void initViews(View root) {
        Button signInButton = root.findViewById(R.id.sign_in_button);
        Button signUpButton = root.findViewById(R.id.sign_up_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackActivityFromOnSignInButtonClick.OnSignInButtonClick();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackActivityFromOnSignUpButtonClick.OnSignUpButtonClick();
            }
        });
    }
}
