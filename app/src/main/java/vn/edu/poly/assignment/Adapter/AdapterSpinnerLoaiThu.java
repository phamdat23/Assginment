package vn.edu.poly.assignment.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.assignment.DTO.DTO_Loai_Thu;
import vn.edu.poly.assignment.R;

public class AdapterSpinnerLoaiThu extends BaseAdapter {
    ArrayList<DTO_Loai_Thu> listLoaiThu;

    public AdapterSpinnerLoaiThu(ArrayList<DTO_Loai_Thu> listLoaiThu) {
        this.listLoaiThu = listLoaiThu;
    }

    @Override
    public int getCount() {
        return listLoaiThu.size();
    }

    @Override
    public Object getItem(int position) {
        DTO_Loai_Thu obj = listLoaiThu.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        DTO_Loai_Thu obj = listLoaiThu.get(position);
        return obj.getIdLT();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row = View.inflate(parent.getContext(), R.layout.item_spinner,null);
        }else{
            row = convertView;
        }
        DTO_Loai_Thu obj = listLoaiThu.get(position);
        TextView tvSpinnerLoaiId = row.findViewById(R.id.tv_spinner_loai_id);
        tvSpinnerLoaiId.setText("Loại: "+obj.getIdLT()+"");
        return row;
    }
}
