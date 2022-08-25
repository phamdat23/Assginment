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

import vn.edu.poly.assignment.Adapter.Adapter_Loai_Chi;
import vn.edu.poly.assignment.DAO.DAO_Loai_Chi;
import vn.edu.poly.assignment.DTO.DTO_Loai_Chi;
import vn.edu.poly.assignment.R;

public class Fragment_Loai_chi extends Fragment {

        private RecyclerView rcvLoaiChi;
        FloatingActionButton fabInsertLoaiChi;
        ArrayList<DTO_Loai_Chi> list_LChi;
        DAO_Loai_Chi daoLoaiChi;
        Adapter_Loai_Chi adapterLoaiChi;
    @Override//khởi tại layout fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment__loai_chi, container, false);


        return view;
    }

    @Override// tương tác với các view trong fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvLoaiChi = (RecyclerView) view.findViewById(R.id.rcv_loai_chi);
        fabInsertLoaiChi = view.findViewById(R.id.fab_insert_loai_chi);
        daoLoaiChi= new DAO_Loai_Chi(getContext());
        daoLoaiChi.opend();
        list_LChi = daoLoaiChi.getAll_Loai_chi();
        adapterLoaiChi= new Adapter_Loai_Chi(list_LChi, daoLoaiChi);
        rcvLoaiChi.setAdapter(adapterLoaiChi);
        DTO_Loai_Chi obj = new DTO_Loai_Chi();
        fabInsertLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_insert_loai_chi);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog_update);
                EditText et_name_chi_insert = dialog.findViewById(R.id.et_name_chi_insert);
                TextView tv_loi_chi = dialog.findViewById(R.id.tv_thong_bao_loi_loai_chi);
                Button btn_insert_chi = dialog.findViewById(R.id.btn_insert_chi);
                btn_insert_chi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!et_name_chi_insert.getText().toString().equals("")){
                            tv_loi_chi.setText("");
                            obj.setName(et_name_chi_insert.getText().toString());
                            long res = daoLoaiChi.insert_loai_chi(obj);
                            if(res>0){
                                list_LChi.clear();
                                list_LChi.addAll(daoLoaiChi.getAll_Loai_chi());
                                adapterLoaiChi.notifyDataSetChanged();
                                list_LChi = daoLoaiChi.getAll_Loai_chi();
                                adapterLoaiChi= new Adapter_Loai_Chi(list_LChi, daoLoaiChi);
                                rcvLoaiChi.setAdapter(adapterLoaiChi);
                                Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            tv_loi_chi.setText("Không được để trống trường này");
                        }


                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        list_LChi = daoLoaiChi.getAll_Loai_chi();
        adapterLoaiChi= new Adapter_Loai_Chi(list_LChi, daoLoaiChi);
        rcvLoaiChi.setAdapter(adapterLoaiChi);
    }
}