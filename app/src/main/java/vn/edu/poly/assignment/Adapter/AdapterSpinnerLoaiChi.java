package vn.edu.poly.assignment.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.assignment.DAO.DAO_Loai_Chi;
import vn.edu.poly.assignment.DTO.DTO_Loai_Chi;
import vn.edu.poly.assignment.R;

public class AdapterSpinnerLoaiChi extends BaseAdapter {
    ArrayList<DTO_Loai_Chi> listLChi;

    public AdapterSpinnerLoaiChi(ArrayList<DTO_Loai_Chi> listLChi) {
        this.listLChi = listLChi;
    }

    @Override
    public int getCount() {
        return listLChi==null?0: listLChi.size();
    }

    @Override
    public Object getItem(int position) {
        DTO_Loai_Chi obj = listLChi.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        DTO_Loai_Chi obj = listLChi.get(position);
        return obj.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row = View.inflate(parent.getContext(), R.layout.item_spinner, null);
        }else{
            row =convertView;
        }
        DTO_Loai_Chi obj = listLChi.get(position);
        TextView tv_spinner_loai_id = row.findViewById(R.id.tv_spinner_loai_id);
        tv_spinner_loai_id.setText("Loại: "+obj.getId());
        return row;
    }
}
