package ru.apps.e1em.welcomescreentemplate.ui.welcome.sign_in;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.apps.e1em.welcomescreentemplate.R;
import ru.apps.e1em.welcomescreentemplate.api.ApiFactory;
import ru.apps.e1em.welcomescreentemplate.data.model.SignInBody;
import ru.apps.e1em.welcomescreentemplate.data.model.SignInResponse;
import ru.apps.e1em.welcomescreentemplate.ui.interfaces.OnGetLoginDataListener;

public class SignInFragment extends Fragment {

    private OnGetLoginDataListener callbackActivityFromOnGetLoginData;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private TextView errorMessage;

    private SignInViewModel signInViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Связь фрагмента с активностью.
        callbackActivityFromOnGetLoginData = (OnGetLoginDataListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initViews(root);

        return root;
    }

    // Initializing Views
    private void initViews(View root) {
        usernameEditText = root.findViewById(R.id.sign_in_username_edit_text);
        passwordEditText = root.findViewById(R.id.sign_in_password_edit_text);
        signInButton = root.findViewById(R.id.sign_in_button_button);
        errorMessage = root.findViewById(R.id.sign_in_error_message_text_view);

        signInViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);

        signInViewModel.getLoginFormState().observe(this, new Observer<SignInFormState>() {
            @Override
            public void onChanged(@Nullable SignInFormState signInFormState) {
                if (signInFormState == null) {
                    return;
                }
                signInButton.setEnabled(signInFormState.isDataValid());
                if (signInFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(signInFormState.getUsernameError()));
                }
                if (signInFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(signInFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                signInViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInBody signInBody = new SignInBody();
                signInBody.setUsername(usernameEditText.getText().toString());
                signInBody.setPassword(passwordEditText.getText().toString());

                ApiFactory.createApi()
                        .signInUser(signInBody)
                        .enqueue(new Callback<SignInResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                                SignInResponse signInResponse = response.body();
                                if (response.isSuccessful()) {
                                    errorMessage.append("Успех");
                                    errorMessage.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                                    callbackActivityFromOnGetLoginData.onGetLoginData(signInResponse);
                                } else {
                                    errorMessage.append("Ошибка");
                                    errorMessage.setTextColor(Color.RED);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                                errorMessage.append("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
            }
        });
    }

    public static SignInFragment newInstance() {

        return new SignInFragment();
    }
}
