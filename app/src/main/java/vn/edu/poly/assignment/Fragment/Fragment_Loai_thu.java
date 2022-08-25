package vn.edu.poly.assignment.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.assignment.Adapter.Adapter_Loai_Thu;
import vn.edu.poly.assignment.DAO.DAO_Loai_Thu;
import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.R;


public class Fragment_Loai_thu extends Fragment {
    private RecyclerView rcvListLoaiThu;
    private FloatingActionButton btnInsertLoaiThu;
    DAO_Loai_Thu daoLoaiThu;
    ArrayList<DTO_Loai_Thu> list_LThu;
    Adapter_Loai_Thu adapterLoaiThu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__loai_thu, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListLoaiThu = (RecyclerView) view.findViewById(R.id.rcv_list_loai_thu);
        daoLoaiThu = new DAO_Loai_Thu(getContext());
        daoLoaiThu.opend();
        list_LThu = daoLoaiThu.getAll_Loai_thu();
        adapterLoaiThu = new Adapter_Loai_Thu(list_LThu, daoLoaiThu);
        rcvListLoaiThu.setAdapter(adapterLoaiThu);
        btnInsertLoaiThu = (FloatingActionButton) view.findViewById(R.id.btn_insert_loai_thu);
        DTO_Loai_Thu obj = new DTO_Loai_Thu();
        btnInsertLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_insert_loai_thu);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                EditText et_name_thu_insert = dialog.findViewById(R.id.et_name_thu_insert);
                TextView tv_loi_loai_thu = dialog.findViewById(R.id.tv_thong_bao_loi_loai_thu);
                Button btn_insert_loai_thu = dialog.findViewById(R.id.btn_insert_loai_thu);
                btn_insert_loai_thu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!et_name_thu_insert.getText().toString().equals("")) {
                            tv_loi_loai_thu.setText("");
                            obj.setName(et_name_thu_insert.getText().toString());
                            long res = daoLoaiThu.insert_loai_thu(obj);
                            if (res > 0) {
                                list_LThu.clear();
                                list_LThu.addAll(daoLoaiThu.getAll_Loai_thu());
                                adapterLoaiThu.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            tv_loi_loai_thu.setText("Không được để trống trường này");
                        }
                    }
                });
                dialog.show();
            }
        });
    }
}