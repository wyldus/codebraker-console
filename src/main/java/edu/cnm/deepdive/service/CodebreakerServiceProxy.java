package edu.cnm.deepdive.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.model.Game;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface CodebreakerServiceProxy {

  @POST("codes")
  Call<Game> StartGame(@Body Game game);

  static CodebreakerServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final CodebreakerServiceProxy INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://ddc-java.services/codebreaker/")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      INSTANCE = retrofit.create(CodebreakerServiceProxy.class);
    }

  }

}



