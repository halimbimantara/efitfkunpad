package org.efit.mobile.adapter.harian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.ModelListAir;
import org.efit.mobile.model.dataharian.ModelDetailAsupan;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterHarianAir extends RecyclerView.Adapter<ListAdapterHarianAir.ViewHolder> {
    private List<ModelListAir> model;
    private Context context;
    private TextView tvCrBahan;
    private TextView tvCrNilai;
    private TextView tvCrTotalnilai;
    private actionMinum actionMinum;

    public ListAdapterHarianAir(Context context, List<ModelListAir> model, actionMinum actionDetail) {
        this.model = model;
        this.context = context;
        this.actionMinum = actionDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crmenu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String volume=String.valueOf(model.get(position).getVolume_air());
        tvCrTotalnilai.setText(volume+" ml");
        tvCrBahan.setText("Air Mineral");
        tvCrNilai.setText("");
        tvCrBahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionMinum.EditAir(model.get(position).getId());
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

    public interface actionMinum {
        void EditAir(int id);
    }
}
