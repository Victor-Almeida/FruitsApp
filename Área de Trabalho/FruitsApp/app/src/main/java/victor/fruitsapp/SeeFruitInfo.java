package victor.fruitsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class SeeFruitInfo extends AppCompatActivity {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            TextView fruitTextView = findViewById(R.id.fruitTextView);
            TextView dollarTextView = findViewById(R.id.dollarTextView);
            TextView realTextView = findViewById(R.id.realTextView);
            TextView infoTextView = findViewById(R.id.infoTextView);
            ImageView fruitImageView = findViewById(R.id.fruitImageView);

            fruitTextView.setText(bundle.get("fruit").toString());
            Glide.with(SeeFruitInfo.this).load(bundle.get("url").toString()).into(fruitImageView);

            String number = bundle.get("price").toString();
            Float dollars = Float.parseFloat(number);
            Float reais = dollars * 7/2;

            dollarTextView.setText(String.format(java.util.Locale.US, "US$%.2f", dollars));
            realTextView.setText(String.format(java.util.Locale.US, "R$%.2f", reais));
        }
    }
}
