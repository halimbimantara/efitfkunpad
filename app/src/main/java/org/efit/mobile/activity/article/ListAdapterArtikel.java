package org.efit.mobile.activity.article;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.efit.mobile.R;

import org.efit.mobile.model.dataartikel.ModelArtikel;

import java.util.List;


/**
 * Created by TheMac on 7/20/17.
 */

public class ListAdapterArtikel extends RecyclerView.Adapter<ListAdapterArtikel.AdapterArtikel> {

    private List<ModelArtikel> modelCabang;
    private Context mContext;
    Typeface face;
    private onDetail listener;


    public ListAdapterArtikel(List<ModelArtikel> listmatkul, Context mContext,onDetail listener) {
        this.modelCabang = listmatkul;
        this.mContext = mContext;
        this.listener=listener;
    }

    public class AdapterArtikel extends RecyclerView.ViewHolder {
        TextView judul, content, tanggal;

        public AdapterArtikel(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.judulArtikel);
            content = (TextView) itemView.findViewById(R.id.contentArtikel);
            tanggal = (TextView) itemView.findViewById(R.id.tglartikel);
        }
    }

    @Override
    public AdapterArtikel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, parent, false);
        return new AdapterArtikel(view);
    }


    @Override
    public void onBindViewHolder(AdapterArtikel holder, final int position) {
        face = Typeface.createFromAsset(mContext.getAssets(), "GothamRnd-Bold.otf");
        holder.judul.setText(modelCabang.get(position).getJudul());
//        holder.judul.setTypeface(face);

        holder.content.setText("Klik Untuk Menampilkan Detail Artikel");
//        holder.content.setTypeface(face);
        holder.tanggal.setText(modelCabang.get(position).getTgl());
//        holder.tanggal.setTypeface(face);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ShowDetail(modelCabang.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelCabang.size();
    }

    public interface onDetail{
        void ShowDetail(String id);
    }
}
