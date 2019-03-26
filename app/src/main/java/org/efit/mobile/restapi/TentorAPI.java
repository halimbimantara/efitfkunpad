package org.efit.mobile.restapi;


import org.efit.mobile.model.ApiResponse;
import org.efit.mobile.model.ModalAbout;
import org.efit.mobile.model.ModelbiayaGoshow;
import org.efit.mobile.model.Myorder;
import org.efit.mobile.model.ResponseEfit;
import org.efit.mobile.model.ResponseEfitBasic;
import org.efit.mobile.model.dataartikel.ModelArtikel;
import org.efit.mobile.model.datachat.ModelHistoryChat;
import org.efit.mobile.model.datachat.ModelPesanPribadi;
import org.efit.mobile.model.dataharian.ModelInAsupanharian;
import org.efit.mobile.model.dataharian.ModelMasterOlahraga;
import org.efit.mobile.model.kemendesa.CekAbsensiku;
import org.efit.mobile.model.kemendesa.absensi.CekLokasiKerja;
import org.efit.mobile.model.kemendesa.absensi.LoginAbsen;
import org.efit.mobile.model.kemendesa.absensi.ModelHistoryMingguan;
import org.efit.mobile.model.kemendesa.absensi.ModelNotifikasi;
import org.efit.mobile.model.kemendesa.dosir.ModelListDosir;
import org.efit.mobile.model.login.EfitLogin;
import org.efit.mobile.model.loker.Lowongan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TentorAPI {

    @GET("getListModul/")
    Call<ApiResponse<List<ModelHistoryChat>>> getListModul();

    @GET("data_induk/chat/getDetailChat/{nip}/{nip_sender}")
    Call<ApiResponse<List<ModelPesanPribadi>>> getDetailchat(@Path("nip") String nip_penerima, @Path("nip_sender") String nip_sender);

    @FormUrlEncoded
    @POST("data_induk/chat/sendMessages")
    Call<ApiResponse> postKirimPesan(@Field("pengirim") String sender, @Field("pesan") String pesan, @Field("penerima") String penerima, @Field("darinama") String darinama);

    /**
     * Diklat
     *
     * @return
     */


    @GET("diklat/detailDiklat/{iddiklat}/{nim}")
    Call<ApiResponse> getDetailDiklat(@Path("iddiklat") String idlok, @Path("nim") String nim);

    @FormUrlEncoded
    @POST("diklat/postdiklat")
    Call<ApiResponse> postDiklat(@Field("id_diklat") String iddiklat,
                                 @Field("nim") String nim,
                                 @Field("kelas") String kelas
    );

    @GET("loker/listlokerlatall")
    Call<ApiResponse<List<Lowongan>>> getListLoker();

    @GET("loker/detailLoker/{idloker}/{nim}")
    Call<ApiResponse> getDetailLoker(@Path("idloker") String idlok, @Path("nim") String nim);

    @FormUrlEncoded
    @POST("loker/postloker")
    Call<ApiResponse> postLoker(@Field("id_loker") String idloker,
                                @Field("nim") String nim
    );

    @GET("loker/appliedloker/{nim}")
    Call<ApiResponse<List<Lowongan>>> getAppliedLoker(@Path("nim") String user);

    @GET("getListArtikel/")
    Call<ApiResponse<List<ModelArtikel>>> getListArtikel();

    @GET("Listlayanan")
    Call<ApiResponse<List<ModelLayanan>>> getSpinnerLayanan();

    @GET("checkingwilayah/{id}")
    Call<CekLokasiKerja> cekLokasiKerja(@Path("id") String id);

    @FormUrlEncoded
    @POST("order/GetDrivingDistance")
    Call<ApiResponse<List<ModelbiayaGoshow>>> getHargaGoshow(@Field("lati") String lati,
                                                             @Field("longi") String longi
    );

    @FormUrlEncoded
    @POST("order/listmyorder")
    Call<ApiResponse<List<Myorder>>> postgetMyorder(@Field("id_user") String iduser);


    /**
     * ABSENSI
     */
    @FormUrlEncoded
    @POST("cekauth")
    Call<LoginAbsen> postSigninAbsen(@Field("NIP") String NIP, @Field("Password") String password, @Field("Key") String Key, @Field("Imei") String imei, @Field("token") String tokenid);

    /**
     * EFIT
     */
    @FormUrlEncoded
    @POST("users/api/v1/login")
    Call<EfitLogin> loginefit(@Field("login") String username, @Field("password") String pwd);

    @FormUrlEncoded
    @POST("users/api/v1/register")
    Call<ResponseEfit> registrasi(@Field("email") String email,
                                  @Field("display_name") String dname,
                                  @Field("password") String pwd,
                                  @Field("pass_confirm") String pwd_confirm,
                                  @Field("language") String lg,
                                  @Field("timezones") String timezone,
                                  @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("fit_quesioner/api/v1/insert_quesioner")
    Call<ResponseEfit> postJawabanQuesioner(@Field("id_user") String id_user,
                                            @Field("id_quesioner") String id_quesioner,
                                            @Field("jawaban") String jawaban
    );

    @FormUrlEncoded
    @POST("fit_quesioner/api/v1/update_quesioner")
    Call<ResponseEfit> updateJawabanQuesioner(@Field("id_user") String id_user,
                                              @Field("id_quesioner") String id_quesioner,
                                              @Field("jawaban") String jawaban
    );

    @POST("fit_lapasupanmakan/api/v1/insertupdate")
    Call<ResponseEfitBasic> updateAsupanMakanan(@Body List<ModelInAsupanharian> model);

    @POST("fit_laplatihan/api/v1/insertupdate")
    Call<ResponseEfitBasic> updateOlahraga(@Body List<ModelMasterOlahraga> model);

    @FormUrlEncoded
    @POST("fit_lapair/api/v1/insert_update")
    Call<ResponseEfitBasic> updateAsupanAir(@Field("id_user") String iduser,
                                            @Field("volume_air") String volair,
                                            @Field("tanggal") String tanggal,
                                            @Field("kode_transaksi") String kode_transaksi
    );

    @FormUrlEncoded
    @POST("fit_lapsasaran/api/v1/insert_update")
    Call<ResponseEfitBasic> updateTargetSasaran(@Field("id_user") String iduser,
                                                @Field("target_kalori") String tkalori,
                                                @Field("berat_badan") String bb,
                                                @Field("tinggi_badan") String tb
    );

    @POST("pages/api/v1/get_all")
    Call<ModalAbout> getPage();

    @POST("pages/api/v1/get_detail/{id_post}")
    Call<ModalAbout> getPageDetail(@Path("id_post") String id);


    @FormUrlEncoded
    @POST("postabsenv2")
    Call<org.efit.mobile.model.kemendesa.ApiResponse> postAbsen(@Field("NIP") String NIP,
                                                                @Field("Type") String Type,
                                                                @Field("Tanggal") String Tgl,
                                                                @Field("Jam") String Jam,
                                                                @Field("Status") String Status,
                                                                @Field("Key") String key
    );

    @GET("checkabsensayahini/{nip}")
    Call<CekAbsensiku> checkmyabsen(@Path("nip") String nip);

    @FormUrlEncoded
    @POST("getHistoryMingguan")
    Call<ApiResponse<List<ModelHistoryMingguan>>> getHistory(@Field("NIP") String NIP,
                                                             @Field("Key") String key);

    @FormUrlEncoded
    @POST("dosir/api/getdoc")
    Call<ModelListDosir> getListDosir(@Field("nip") String NIP, @Field("key") String key);


    @FormUrlEncoded
    @POST("notifikasi")
    Call<ModelNotifikasi> getNotifikasi(@Field("NIP") String NIP, @Field("Key") String key);

    /**
     * Cek Version
     *
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST("cekversi")
    Call<org.efit.mobile.model.kemendesa.ApiResponse> cekversion(@Field("versi") String versi, @Field("Key") String key);
}



