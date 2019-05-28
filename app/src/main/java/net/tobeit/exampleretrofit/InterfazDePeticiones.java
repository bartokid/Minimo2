package net.tobeit.exampleretrofit;

import net.tobeit.exampleretrofit.models.Element;
import net.tobeit.exampleretrofit.models.Museums;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface InterfazDePeticiones {


    @GET
    Call<Museums> getElements(@Url String url);

    //museus

    @GET
    Call<Element> getAdrecaNom(@Url String url);
}
