package org.efit.mobile.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.efit.mobile.model.ModelDetailAir;
import org.efit.mobile.model.ModelListAir;
import org.efit.mobile.model.dataharian.ModelDetailAsupan;
import org.efit.mobile.model.dataharian.ModelDetailAsupanInput;
import org.efit.mobile.model.dataharian.ModelDetailOlahraga;
import org.efit.mobile.model.dataharian.ModelHarianSarapan;
import org.efit.mobile.model.dataharian.ModelMasterAsupan;
import org.efit.mobile.model.pertanyaan.ModelPertanyaan;
import org.efit.mobile.model.quesioner.ModelUserQuesioner;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Helpers;
import org.efit.mobile.utility.Sharepref;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by TheMac on 9/15/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 6;
    // Database Name
    private static final String DATABASE_NAME = "db_mobile.db";
    // Contacts table name
    private static final String TABLE_MASTER = "master";
    // Contacts Table Columns names
    private static final String TAG = "DatabaseHandler";
    private static final String KEY_ID = "id";
    private static final String KEY_TGL = "tanggal";
    private static final String KEY_H = "hari";
    private static final String KEY_M = "bulan";
    private static final String KEY_TH = "tahun";
    private static final String KEY_JAM = "jam";
    private static final String KEY_MNT = "menit";
    private static final String KEY_ID_ORDER = "id_order";
    private static final String KEY_ID_QZ = "id_quesioner";
    private static final String master_quiz = "master_quiz";
    private final Context myContext;
    private String pathToSaveDBFile;
    private Helpers helpers;

    /**
     * @param context
     */
    public DatabaseHandler(Context context, String filePath) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }

    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.d(TAG, "Database exists.");
//            int currentDBVersion = getVersionId();
//            if (DATABASE_VERSION > currentDBVersion) {
//                Log.d(TAG, "Database version is higher than old.");
//                deleteDb();
//                try {
//                    copyDataBase();
//                } catch (IOException e) {
//                    Log.e(TAG, e.getMessage());
//                }
//            }
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch (SQLiteException e) {
            Log.d("DBhen", e.getMessage());
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = myContext.getAssets().open("sqlite/" + DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }

    public void deleteDb() {
        File file = new File(pathToSaveDBFile);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "onCreate");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASTER);
        // Create tables again
        //onCreate(db);
    }

    private int getVersionId() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT version_id FROM dbVersion";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int v = cursor.getInt(0);
        db.close();
        return v;
    }

    public List<ModelPertanyaan> showPertanyaan() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select a.id_quesioner,a.pertanyaan,b.kategori,b.description,a.tipe_soal from ifs_quesioner a " +
                "left join ifs_kategori_quesioner b on (a.id_kategori = b.id_kategori)";
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<ModelPertanyaan> list = new ArrayList<ModelPertanyaan>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelPertanyaan res = new ModelPertanyaan();
                res.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID_QZ)));
                res.setPertanyaan(cursor.getString(cursor.getColumnIndex("pertanyaan")));
                res.setTipe_soal(cursor.getInt(cursor.getColumnIndex("tipe_soal")));
                res.setKategori(cursor.getString(cursor.getColumnIndex("kategori")));
                res.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                list.add(res);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public double getTotalEnergi(String tanggal) {
        String query = "SELECT a.tanggal,round(sum(b.energi*b. jumlah)) as total_energi " +
                "FROM ifs_master_asupan a " +
                "LEFT JOIN ifs_detail_asupan b on a.id_master_asupan=b.id_master_asupan " +
                "WHERE a.tanggal='" + tanggal + "'";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        double total_energi = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                total_energi = cursor.getInt(cursor.getColumnIndex("total_energi"));
                cursor.close();
            } else {
                total_energi = 0;
            }
        } catch (SQLiteConstraintException ex) {
            Log.e("Err", ex.getMessage());
            total_energi = 0;
        }
        return total_energi;
    }

    public double getTotalOlahraga(String tanggal) {
        String query = "SELECT a.tanggal,round(sum(b.jumlah_kalori)) as total_energi " +
                "FROM ifs_master_asupan a " +
                "LEFT JOIN ifs_detail_olahraga b on a.kode_transaksi=b.kode_transaksi " +
                "WHERE a.tanggal='" + tanggal + "'";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        double total_energi = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                total_energi = cursor.getInt(cursor.getColumnIndex("total_energi"));
                cursor.close();
            } else {
                total_energi = 0;
            }
        } catch (SQLiteConstraintException ex) {
            Log.e("Err", ex.getMessage());
            total_energi = 0;
        }
        return total_energi;
    }

    public int CekJawabanUserQuesioner(int id_quesioner) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        int id_jawaban = 0;
        String query = "SELECT * FROM " + "ifs_users_quesioner" + " WHERE " + "id_quesioner" + "='" + id_quesioner + "'";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id_jawaban = cursor.getInt(cursor.getColumnIndex("id_user_quesioner"));
                cursor.close();
            } else {
                id_jawaban = 0;
            }
        } catch (SQLiteConstraintException ex) {
            Log.e("Err", ex.getMessage());
            id_jawaban = 0;
        }
        return id_jawaban;
    }

    public int getLastIDMasterAsupan(String tanggal) {
        tanggal = tanggal.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID);
        helpers = new Helpers();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = null;
        int lastID = 0;
        String query = "SELECT MAX(id_master_asupan) last_id,kode_transaksi FROM ifs_master_asupan WHERE kode_transaksi='" + tanggal + "'";
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                lastID = cursor.getInt(cursor.getColumnIndex("last_id"));
            } else {
                lastID = 0;
            }
            return lastID;
        } finally {
            cursor.close();
        }
    }

    public void addData(ModelUserQuesioner data) {
        int status;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("id_quesioner", data.getId_quesioner());
        initialValues.put("id_user", data.getId_user());
        initialValues.put("jawaban", data.getJawaban());
        try {
            db.insert("ifs_users_quesioner", null, initialValues);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
        }
    }

    public long addDataOlahraga(ModelDetailOlahraga data) {
        long status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("id_user", data.getId_user());
        initialValues.put("kode_transaksi", data.getKode_transaksi());
        initialValues.put("nama_latihan", data.getNama_latihan());
        initialValues.put("jumlah_kalori", data.getJumlah_kalori());
        initialValues.put("tanggal_input", data.getTanggal_input());
        try {
            db.insert("ifs_detail_olahraga", null, initialValues);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
        }
        return status;
    }

    public long DeleteMakanan(int id) {
        long status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        try {
            db.delete("ifs_detail_olahraga", "id_detail_asupan=" + id, null);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
        }
        return status;
    }

    /**
     * @param data
     * @return SELECT a.tanggal,round(sum(b.energi)) as total_energi
     * FROM ifs_master_asupan a
     * LEFT JOIN ifs_detail_asupan b on a.id_master_asupan=b.id_master_asupan
     * WHERE a.tanggal=''
     */

    public long addDataMasterAsupan(ModelMasterAsupan data) {
        helpers = new Helpers();
        long status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("kode_transaksi", data.getKode_transaksi());
        initialValues.put("tanggal", data.getTanggal());//tgl waktu input
        initialValues.put("status", data.getStatus());//Belum n Sudah Sinkronasi
        initialValues.put("id_user", Sharepref.getString(Constant.USERID));
        try {
            status = db.insert("ifs_master_asupan", null, initialValues);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
        }
        return status;
    }

    public long addDetailAsupan(ModelDetailAsupanInput data) {
        long status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("kode_transaksi", data.getKode_transaksi());
        initialValues.put("id_master_asupan", data.getId_master_asupan());
        initialValues.put("id_dkbm", data.getId_dkbm());
        initialValues.put("energi", data.getEnergi());
        initialValues.put("protein", data.getProtein());
        initialValues.put("vit_c", data.getVit_c());
        initialValues.put("vit_a", data.getVit_a());
        initialValues.put("calsium", data.getCalcium());
        initialValues.put("zinc", data.getZinc());
        initialValues.put("jumlah", data.getJumlah());
        initialValues.put("jam_makan", data.getJam_makan());
        initialValues.put("id_user", Sharepref.getString(Constant.USERID));
        try {
            status = db.insert("ifs_detail_asupan", null, initialValues);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
        }
        return status;
    }

    public long addDetailAir(ModelDetailAir data) {
        long status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("kode_transaksi", data.getKode_transaksi());
        initialValues.put("volume_air", data.getVolume_air());
        initialValues.put("tanggal", data.getTanggal());
        initialValues.put("status", data.getStatus_update());
        initialValues.put("id_user", Sharepref.getString(Constant.USERID));
        try {
            status = db.insert("ifs_detail_asupan_air", null, initialValues);
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
        }
        return status;
    }

    public int UpdateUserQuesioner(ModelUserQuesioner data, int id_user_quesioner) {
        int status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("id_quesioner", data.getId_quesioner());
        initialValues.put("id_user", data.getId_user());
        initialValues.put("jawaban", data.getJawaban());
        try {
            status = db.update("ifs_users_quesioner", initialValues, "id_user_quesioner" + "='" + id_user_quesioner + "'", null);
        } catch (SQLiteConstraintException ex) {
            return status;
        }
        return status;
    }

    public int UpdateOlahraga(ModelDetailOlahraga data, int id_) {
        int status = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues initialValues = new ContentValues();
        initialValues.put("jumlah_kalori", data.getJumlah_kalori());
        try {
            status = db.update("ifs_detail_olahraga", initialValues, "id" + "='" + id_ + "'", null);
        } catch (SQLiteConstraintException ex) {
            return status;
        }
        return status;
    }

    public List<ModelDetailOlahraga> showListOlahraga(String tgl) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String sql = "select b.id,b.kode_transaksi,b.nama_latihan,b.jumlah_kalori,b.tanggal_input " +
                "FROM ifs_master_asupan a " +
                "LEFT JOIN ifs_detail_olahraga b on a.kode_transaksi=b.kode_transaksi " +
                "WHERE a.tanggal ='" + tgl + "'";
//        System.out.println("air :"+sql);
        Cursor cursor = db.rawQuery(sql, null);
        List<ModelDetailOlahraga> list = new ArrayList<ModelDetailOlahraga>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelDetailOlahraga res = new ModelDetailOlahraga();
//                res.setId(cursor.getInt(cursor.getColumnIndex("id")));
                res.setKode_transaksi(cursor.getString(cursor.getColumnIndex("kode_transaksi")));
                res.setJumlah_kalori(cursor.getString(cursor.getColumnIndex("jumlah_kalori")));
                res.setNama_latihan(cursor.getString(cursor.getColumnIndex("nama_latihan")));
                list.add(res);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<ModelListAir> showListAirHarian(String tgl) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String sql = "select b.id,b.kode_transaksi,b.volume_air,b.tanggal " +
                "FROM ifs_master_asupan a " +
                "LEFT JOIN ifs_detail_asupan_air b on a.kode_transaksi=b.kode_transaksi " +
                "WHERE a.tanggal ='" + tgl + "'";
        System.out.println("air :" + sql);
        Cursor cursor = db.rawQuery(sql, null);
        List<ModelListAir> list = new ArrayList<ModelListAir>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelListAir res = new ModelListAir();
                res.setId(cursor.getInt(cursor.getColumnIndex("id")));
                res.setKode_trx(cursor.getString(cursor.getColumnIndex("kode_transaksi")));
                res.setTanggal_time(cursor.getString(cursor.getColumnIndex("tanggal")));
                res.setVolume_air(cursor.getInt(cursor.getColumnIndex("volume_air")));
                list.add(res);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<ModelDetailAsupan> showListHarian(String tgl, String jenis_kategori) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select b.id_detail_asupan,c.bahan_makanan,b.jumlah,b.jam_makan,b.energi " +
                "FROM ifs_master_asupan a " +
                "LEFT JOIN ifs_detail_asupan b on a.id_master_asupan = b.id_master_asupan " +
                "LEFT JOIN ifs_dkbm c on b.id_dkbm = c.ID " +
                "WHERE a.tanggal ='" + tgl + "' and b.jam_makan='" + jenis_kategori + "'";
//        Log.i(TAG, "showListHarian: "+);
        System.out.println(selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<ModelDetailAsupan> list = new ArrayList<ModelDetailAsupan>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelDetailAsupan res = new ModelDetailAsupan();
                res.setId_detail_asupan(cursor.getInt(cursor.getColumnIndex("id_detail_asupan")));
                res.setBahan_makanan(cursor.getString(cursor.getColumnIndex("bahan_makanan")));
                res.setJumlah(cursor.getInt(cursor.getColumnIndex("jumlah")));
                res.setJam_makan(cursor.getString(cursor.getColumnIndex("jam_makan")));
                res.setEnergi(cursor.getString(cursor.getColumnIndex("energi")));
                list.add(res);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<ModelHarianSarapan> showCariMakanan(String carimakan) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select * FROM ifs_dkbm WHERE bahan_makanan like '%" + carimakan + "%'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<ModelHarianSarapan> list = new ArrayList<ModelHarianSarapan>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelHarianSarapan res = new ModelHarianSarapan();
                res.setId_menu(cursor.getInt(cursor.getColumnIndex("ID")));
                res.setKD_BARU(cursor.getString(cursor.getColumnIndex("kode_baru")));
                res.setBAHAN_MAKANAN(cursor.getString(cursor.getColumnIndex("bahan_makanan")));
                res.setENERGI(cursor.getString(cursor.getColumnIndex("energi")));
                res.setPROTEIN(cursor.getString(cursor.getColumnIndex("protein")));
                res.setRETNOL(cursor.getString(cursor.getColumnIndex("retnol")));
                res.setVIT_C(cursor.getString(cursor.getColumnIndex("vit_c")));
                res.setKALSIUM(cursor.getString(cursor.getColumnIndex("calsium")));
                res.setZN(cursor.getString(cursor.getColumnIndex("zn")));
                list.add(res);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public ModelHarianSarapan detailMakanan(int idmakanan) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select * FROM ifs_dkbm WHERE ID =" + idmakanan;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ModelHarianSarapan res = new ModelHarianSarapan();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                res.setId_menu(cursor.getInt(cursor.getColumnIndex("ID")));
                res.setKD_BARU(cursor.getString(cursor.getColumnIndex("kode_baru")));
                res.setBAHAN_MAKANAN(cursor.getString(cursor.getColumnIndex("bahan_makanan")));
                res.setENERGI(cursor.getString(cursor.getColumnIndex("energi")));
                res.setPROTEIN(cursor.getString(cursor.getColumnIndex("protein")));
                res.setVIT_C(cursor.getString(cursor.getColumnIndex("vit_c")));
                res.setRETNOL(cursor.getString(cursor.getColumnIndex("retnol")));
                res.setKALSIUM(cursor.getString(cursor.getColumnIndex("calsium")));
                res.setKH(cursor.getString(cursor.getColumnIndex("kh")));
                res.setSERAT(cursor.getString(cursor.getColumnIndex("serat")));
                res.setLEMAK(cursor.getString(cursor.getColumnIndex("lemak")));
                res.setFOSFOR(cursor.getString(cursor.getColumnIndex("fosfor")));
                res.setZN(cursor.getString(cursor.getColumnIndex("zn")));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return res;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
