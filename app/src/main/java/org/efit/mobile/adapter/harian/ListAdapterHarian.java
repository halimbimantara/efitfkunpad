package org.efit.mobile.adapter.harian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.dataharian.ModelDetailAsupan;
import org.efit.mobile.model.dataharian.ModelHarianSarapan;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterHarian extends RecyclerView.Adapter<ListAdapterHarian.ViewHolder> {
    private List<ModelDetailAsupan> model;
    private Context context;
    private TextView tvCrBahan;
    private TextView tvCrNilai;
    private TextView tvCrTotalnilai;
    private actionMakanan actionMakanan;

    public ListAdapterHarian(Context context, List<ModelDetailAsupan> model, actionMakanan actionDetail) {
        this.model = model;
        this.context = context;
        this.actionMakanan = actionDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crmenu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        tvCrBahan.setText(model.get(position).getBahan_makanan());
        String x = Integer.toString(model.get(position).getJumlah());
        double total = Double.parseDouble(model.get(position).getEnergi()) * Double.parseDouble(x);
        tvCrTotalnilai.setText(Double.toString(total));
        tvCrNilai.setText(model.get(position).getEnergi());
        tvCrBahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionMakanan.EditMakanan(model.get(position).getId_detail_asupan());
            }
        });
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

    public interface actionMakanan {
        void EditMakanan(int id);
    }
}
