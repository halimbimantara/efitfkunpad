package org.efit.mobile.adapter.beranda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.kemendesa.absensi.ModelHistoryMingguan;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterMyOrder extends RecyclerView.Adapter<ListAdapterMyOrder.ViewHolder> {
    private List<ModelHistoryMingguan> model;
    private Context context;
    private TextView tglHistory;
    private TextView historyDatang;
    private TextView historyPulang;

    public ListAdapterMyOrder(Context context, List<ModelHistoryMingguan> model) {
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
        tglHistory.setText(model.get(position).getTanggal());
        historyDatang.setText(model.get(position).getDatang());
        historyPulang.setText(model.get(position).getPulang());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    private void initView(View ItemView) {
        tglHistory = (TextView) ItemView.findViewById(R.id.tgl_history);
        historyDatang = (TextView) ItemView.findViewById(R.id.history_datang);
        historyPulang = (TextView) ItemView.findViewById(R.id.history_pulang);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
    }

}
