package ru.apps.e1em.welcomescreentemplate.ui.welcome.sign_up;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import ru.apps.e1em.welcomescreentemplate.data.model.SignUpBody;
import ru.apps.e1em.welcomescreentemplate.data.model.SignUpResponse;

public class SignUpFragment extends Fragment {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button signUpButton;
    private TextView errorMessage;

    private SignUpViewModel signUpViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        initViews(root);

        return root;
    }

    // Initializing Views
    private void initViews(View root) {
        usernameEditText = root.findViewById(R.id.sign_up_username_edit_text);
        emailEditText = root.findViewById(R.id.sign_up_email_edit_text);
        passwordEditText = root.findViewById(R.id.sign_up_password_edit_text);
        repeatPasswordEditText = root.findViewById(R.id.sign_up_repeat_password_edit_text);
        signUpButton = root.findViewById(R.id.sign_up_button_button);
        errorMessage = root.findViewById(R.id.sign_up_error_message_text_view);

        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        signUpViewModel.getLoginFormState().observe(this, new Observer<SignUpFormState>() {
            @Override
            public void onChanged(@Nullable SignUpFormState signUpFormState) {
                if (signUpFormState == null) {
                    return;
                }
                signUpButton.setEnabled(signUpFormState.isDataValid());
                if (signUpFormState.getUsernameError() != null) {
                    emailEditText.setError(getString(signUpFormState.getUsernameError()));
                }
                if (signUpFormState.getEmailError() != null) {
                    emailEditText.setError(getString(signUpFormState.getEmailError()));
                }
                if (signUpFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(signUpFormState.getPasswordError()));
                }
                if (signUpFormState.getRepeatPasswordError() != null) {
                    repeatPasswordEditText.setError(getString(signUpFormState.getRepeatPasswordError()));
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
                signUpViewModel.loginDataChanged(usernameEditText.getText().toString(), emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),repeatPasswordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        repeatPasswordEditText.addTextChangedListener(afterTextChangedListener);

        // Отправка данных регистрации пользователя.
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpBody signUpBody = new SignUpBody();
                signUpBody.setUsername(usernameEditText.getText().toString());
                signUpBody.setEmail(emailEditText.getText().toString());
                signUpBody.setPassword1(passwordEditText.getText().toString());
                signUpBody.setPassword2(repeatPasswordEditText.getText().toString());

                ApiFactory.createApi()
                        .registerUser(signUpBody)
                        .enqueue(new Callback<SignUpResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                                SignUpResponse signUpResponse = response.body();
                                errorMessage.setText(signUpResponse.getDetail());
                            }

                            @Override
                            public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                                errorMessage.setText("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
            }
        });
    }

    public static SignUpFragment newInstance() {

        return new SignUpFragment();
    }
}
