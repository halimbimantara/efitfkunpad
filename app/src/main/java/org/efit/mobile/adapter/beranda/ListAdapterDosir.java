package org.efit.mobile.adapter.beranda;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.kemendesa.dosir.ModelListDosir;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterDosir extends RecyclerView.Adapter<ListAdapterDosir.ViewHolder> {
    private List<ModelListDosir.ListDosir> model;
    private Context context;
    private TextView tglHistory;
    private TextView historyDatang;
    private TextView historyPulang;
    private CardView jobLayout;
    private LinearLayout llListhistory;
    private LinearLayout llIsilististory;
    private LinearLayout llDosir;
    private TextView namaFile;
    private Button btnLihatfile;

    public ListAdapterDosir(Context context, List<ModelListDosir.ListDosir> model) {
        this.model = model;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        tglHistory.setText(model.get(position).getCreated());
        llListhistory.setVisibility(View.GONE);
        llIsilististory.setVisibility(View.GONE);
        llDosir.setVisibility(View.VISIBLE);
        btnLihatfile.setVisibility(View.VISIBLE);
        namaFile.setText(model.get(position).getNama_file());

        btnLihatfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://"+model.get(position).getData_file();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                if (i.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    private void initView(View ItemView) {
        tglHistory = (TextView) ItemView.findViewById(R.id.tgl_history);
        jobLayout = (CardView) ItemView.findViewById(R.id.job_layout);
        llListhistory = (LinearLayout) ItemView.findViewById(R.id.ll_listhistory);
        llIsilististory = (LinearLayout) ItemView.findViewById(R.id.ll_isilististory);
        llDosir = (LinearLayout) ItemView.findViewById(R.id.ll_dosir);
        namaFile = (TextView) ItemView.findViewById(R.id.nama_file);
        btnLihatfile = (Button) ItemView.findViewById(R.id.btn_lihatfile);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
    }

}
