package ru.apps.e1em.welcomescreentemplate.ui.user_content;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.apps.e1em.welcomescreentemplate.R;
import ru.apps.e1em.welcomescreentemplate.data.model.SignInResponse;

public class UserDataFragment  extends Fragment {
    private static final String USER_DATA_KEY = "key";

    private SignInResponse userData;

    @Override
    public void onAttach(Context context) {
        // Связь с активностью.
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_data, container, false);
        userData = (SignInResponse) getArguments().getSerializable(USER_DATA_KEY);

        initViews(root);

        return root;
    }

    /** Initializing Views. */
    private void initViews(View root) {
        TextView textView = root.findViewById(R.id.text_text);

        textView.append(userData.getKey() + "\n");
    }

    public static UserDataFragment newInstance(SignInResponse signInResponse) {
        UserDataFragment fragment = new UserDataFragment();

        // Прием переданных данных.
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_DATA_KEY, signInResponse);
        fragment.setArguments(bundle);

        return fragment;
    }
}

