package victor.fruitsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetFruitsList extends AppCompatActivity {
    //JSON URL
    private String jsonURL = " https://raw.githubusercontent.com/muxidev/desafio-android/master/fruits.json";
    //Auxiliar fruit-type variable to add a fruit to the list view
    private JSONArray jsonArray;
    private RequestQueue queue;
    private FruitAdapter fruitAdapter;
    private List fruitsList = new ArrayList<>();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_fruits_list);

        final ListView fruitsListView = findViewById(R.id.fruitsListView);

        fruitAdapter = new FruitAdapter(this, R.layout.list_item_layout);
        fruitsListView.setAdapter(fruitAdapter);

        queue = Volley.newRequestQueue(GetFruitsList.this);
        jsonParse();

       // Button moreInfoButton = findViewById(R.id.moreInfoButton);
        fruitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int i, long l){
                Fruit auxiliarFruit = (Fruit) fruitsList.get(i);
             //   Toast.makeText(GetFruitsList.this, auxiliarFruit.getFruit() + "\n" + auxiliarFruit.getPrice() + "\n", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GetFruitsList.this, SeeFruitInfo.class);
                intent.putExtra("fruit", auxiliarFruit.getFruit());
                intent.putExtra("url", auxiliarFruit.getImage());
                intent.putExtra("price", auxiliarFruit.getPrice());
                startActivity(intent);
            }
        });
    }

    private void jsonParse() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonURL, "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("fruits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject fruit = jsonArray.getJSONObject(i);
                                Fruit newFruit = new Fruit(fruit.getString("name"), fruit.getString("image"), Float.parseFloat(fruit.getString("price")));
                                fruitAdapter.add(newFruit);
                                fruitsList.add(newFruit);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }
}
