package org.efit.mobile.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.barteksc.pdfviewer.PDFView;
import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TheMac on 10/8/17.
 */

public class ActivityTentang extends BaseActivity {
    @BindView(R.id.pdfView)
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    public static final String SAMPLE_FILE = "Ketentuan.pdf";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

    }


}
