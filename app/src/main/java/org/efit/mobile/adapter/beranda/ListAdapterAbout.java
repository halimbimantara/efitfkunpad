package org.efit.mobile.adapter.beranda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.detailactivity.DetailActivityAbout;
import org.efit.mobile.model.ModalAbout;
import org.efit.mobile.model.kemendesa.absensi.ModelHistoryMingguan;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterAbout extends RecyclerView.Adapter<ListAdapterAbout.ViewHolder> {
    private List<ModalAbout.Data> model;
    private Context context;
    private TextView tglHistory;
    private TextView historyDatang;
    private TextView historyPulang;
    private LinearLayout llOnclikMknan;
    private TextView tvCrNamaMenu;

    public ListAdapterAbout(Context context, List<ModalAbout.Data> model) {
        this.model = model;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_about, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        tvCrNamaMenu.setText(model.get(position).getPost_title());
        llOnclikMknan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDetail = new Intent(context, DetailActivityAbout.class);
                openDetail.putExtra("ID", model.get(position).getId_post());
                openDetail.putExtra("URL", model.get(position).getPost_guid());
                context.startActivity(openDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    private void initView(View ItemView) {
        llOnclikMknan = (LinearLayout) ItemView.findViewById(R.id.ll_onclik_mknan);
        tvCrNamaMenu = (TextView) ItemView.findViewById(R.id.tv_cr_nama_menu);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
    }

}
