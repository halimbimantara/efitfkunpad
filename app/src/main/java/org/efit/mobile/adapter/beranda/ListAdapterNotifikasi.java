package org.efit.mobile.adapter.beranda;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.kemendesa.absensi.ModelNotifikasi;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterNotifikasi extends RecyclerView.Adapter<ListAdapterNotifikasi.ViewHolder> {
    private List<ModelNotifikasi.DataEntity> model;
    private Context context;
    private TextView tglHistory;
    private TextView historyDatang;
    private TextView historyPulang;
    private CardView jobLayout;
    private LinearLayout llIsilististory;
    private LinearLayout llDosir;
    private TextView aplikasi;
    private Button btnLihatfile;
    private CardView agendaLayout;
    private TextView tvContentOnnotif;
    private TextView tvTglOnnotif;

    public ListAdapterNotifikasi(Context context, List<ModelNotifikasi.DataEntity> model) {
        this.model = model;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notifikasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        tvTglOnnotif.setText(model.get(position).getTanggal());
        tvContentOnnotif.setText(model.get(position).getInfo());
        aplikasi.setText(model.get(position).getAplikasi());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    private void initView(View ItemView) {
        agendaLayout = (CardView) ItemView.findViewById(R.id.agenda_layout);
        tvContentOnnotif = (TextView) ItemView.findViewById(R.id.tv_content_onnotif);
        aplikasi = (TextView) ItemView.findViewById(R.id.tv_aplikasi);
        tvTglOnnotif = (TextView) ItemView.findViewById(R.id.tv_tgl_onnotif);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
    }

}
