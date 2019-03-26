package org.efit.mobile.adapter.harian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.ModelListAir;
import org.efit.mobile.model.dataharian.ModelDetailOlahraga;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterHarianOlahraga extends RecyclerView.Adapter<ListAdapterHarianOlahraga.ViewHolder> {
    private List<ModelDetailOlahraga> model;
    private Context context;
    private TextView tvCrBahan;
    private TextView tvCrNilai;
    private TextView tvCrTotalnilai;

    public ListAdapterHarianOlahraga(Context context, List<ModelDetailOlahraga> model) {
        this.model = model;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crmenu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        tvCrTotalnilai.setText(model.get(position).getJumlah_kalori()+" kal");
        tvCrBahan.setText("Latihan");
        tvCrNilai.setText("");
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    private void initView(View ItemView) {
        tvCrBahan = (TextView) ItemView.findViewById(R.id.tv_cr_bahan);
        tvCrNilai = (TextView) ItemView.findViewById(R.id.tv_cr_nilai);
        tvCrTotalnilai = (TextView) ItemView.findViewById(R.id.tv_cr_totalnilai);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
    }


}
