package net.tobeit.exampleretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import net.tobeit.exampleretrofit.models.Element;
import net.tobeit.exampleretrofit.models.Museums;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    private InterfazDePeticiones interfazDePeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.test_view_result);
        //recyclerView = (RecyclerView) findViewById(R.id.recycle_View);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/dataset/museus/format/json/pag-ini/1/pag-fi/10/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interfazDePeticiones = retrofit.create(InterfazDePeticiones.class);

        getElements();
    }

    private void getElements () {

        Call<Museums> call = interfazDePeticiones.getElements("https://do.diba.cat/api/dataset/museus/format/json/pag-ini/1/pag-fi/30/");
        call.enqueue(new Callback<Museums>() {



            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());

                    return;
                }


                Museums museum = response.body();
                List<Element>  elements =museum.getElements();

                for(Element element: elements){
                    String content ="";
                    content += "Imatge: " + element.getImatge().get(0) + "\n";
                    content += "Nom: " + element.getAdrecaNom() + "\n\n";


                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }



}
