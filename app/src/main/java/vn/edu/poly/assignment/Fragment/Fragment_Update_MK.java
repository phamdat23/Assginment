package vn.edu.poly.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.assignment.DAO.DAO_AC;
import vn.edu.poly.assignment.DTO.DTOAC;
import vn.edu.poly.assignment.R;

public class Fragment_Update_MK extends Fragment {
    private TextInputLayout etUserUp;
    private TextInputLayout etPassUp;
    private TextInputLayout etRepassUp;


    private Button btnUpdatePass;
    DAO_AC dao;
    ArrayList<DTOAC> list_ac;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_doi_mk, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new DAO_AC(getContext());
        dao.opend();
        list_ac = dao.getALLAC();
        etUserUp = view.findViewById(R.id.et_user_up);
        etPassUp = view.findViewById(R.id.et_pass_up);
        etRepassUp = view.findViewById(R.id.et_repass_up);
        btnUpdatePass = (Button) view.findViewById(R.id.btn_update_pass);
        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpass();
                for (int i = 0; i < list_ac.size(); i++) {
                    if (etUserUp.getEditText().getText().toString().equals(list_ac.get(i).getUserName())) {
                        DTOAC obj = list_ac.get(i);
                        if (checkpass() == true) {
                            obj.setPassWord(etPassUp.getEditText().getText().toString());
                            obj.setRePass(etRepassUp.getEditText().getText().toString());
                            int res = dao.update_ac(obj);
                            if (res > 0) {
                                list_ac.set(i, obj);
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_content1, new LoginFragment());
                                transaction.commit();
                                Toast.makeText(getContext(), "?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "L???i", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (etUserUp.getEditText().getText().toString().equals("")) {
                        etUserUp.setError("T??n t??i kho???n kh??ng ???????c ????? tr???ng");
                    } else {
                        etUserUp.setError("T??i kho???n kh??ng t???n t???i");
                    }

                    break;
                }
            }
        });

    }

    public boolean checkpass() {
        boolean a = true;
        if (etPassUp.getEditText().getText().toString().trim().equals("")) {
            etPassUp.setError("M???t kh???u kh??ng ???????c ????? tr???ng");
            a = false;
        } else if (etPassUp.getEditText().getText().toString().length() < 8) {
            etPassUp.setError("M???t kh???u ph???i ??t nh???t 8 k?? t???");
            a = false;
        } else {
            etPassUp.setError("");
            a = true;
        }
        if (!etRepassUp.getEditText().getText().toString().equals(etPassUp.getEditText().getText().toString())) {
            etRepassUp.setError("Kh??ng ????ng m???t kh???u");
            a = false;
        } else if (etRepassUp.getEditText().getText().toString().trim().equals("")) {
            etRepassUp.setError("Nh???p l???i m???t kh???u kh??ng ???????c ????? tr???ng");
            a = false;
        } else {
            etRepassUp.setError("");
            a = true;
        }

        return a;
    }
}
