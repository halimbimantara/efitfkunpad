package org.efit.mobile.adapter.carimenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.model.dataharian.ModelHarianSarapan;
import org.efit.mobile.model.kemendesa.dosir.ModelListDosir;

import java.util.List;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */
public class ListAdapterCariMenu extends RecyclerView.Adapter<ListAdapterCariMenu.ViewHolder> {
    private List<ModelHarianSarapan> model;
    private Context context;
    private TextView tvCrBahan;
    private LinearLayout llClick;
    private TextView tvCrNilai;
    private TextView tvCrTotalnilai;
    private actionMakanan actionMakanan;

    public ListAdapterCariMenu(Context context, List<ModelHarianSarapan> model, actionMakanan actionDetail) {
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
        tvCrBahan.setText(model.get(position).getBAHAN_MAKANAN());
        tvCrTotalnilai.setText(model.get(position).getBAHAN_MAKANAN() + " : " + model.get(position).getPROTEIN());
        tvCrNilai.setText(model.get(position).getENERGI());
        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionMakanan.DetailMakanan(model.get(position).getId_menu());
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
        llClick = (LinearLayout) ItemView.findViewById(R.id.ll_onclik_mknan);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
    }

    public interface actionMakanan {
        void DetailMakanan(int id);
    }
}
