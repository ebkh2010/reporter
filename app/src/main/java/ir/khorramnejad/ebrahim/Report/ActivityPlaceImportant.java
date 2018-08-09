package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import ir.khorramnejad.ebrahim.R;
import ir.khorramnejad.ebrahim.Splash.G;

public class ActivityPlaceImportant extends Activity {



    private Button next;
    private TextView txtPlace,txtImportant,txtCategory;


    MaterialBetterSpinner placeSpinner,importantSpinner,categorySpinner;

    String[] place = {"استدیو شهید زین الدین","استدیو شهید سعیدی","استدیو شهید فهمیده",
            "استدیو شهید دل آذر","استدیو جمکران","استدیو حرم مطهر","اتاق کنترل پخش سیما","استدیو خبر","استدیو پخش رادیو",
            "استدیو تولید یک رادیو","استدیو تولید دو رادیو","سرور سیما","واحد سیار سیما","واحد سیار رادیو",
            "باکسهای ادیت","قسمتهای اداری و سایر قسمتها",};
    String[] important = { "تاثیر مستقیم و فوری روی آنتن","تاثیر مستقیم بر روی انتن","میتواند روی انتن موثر باشد"
            ,"در حال حاضر بر روی انتن تاثیر مستقیم ندارد"};
    String[] category = { "تاسیساتی: برق","تاسیساتی: تهویه","تاسیساتی: متفرقه","تجهیزات تصویر",
            "تجهیزات صدا","تعاملات نیروی انسانی","متفرقه"};


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_importatnt);


        next = findViewById(R.id.btn_place_next);
        txtPlace = findViewById(R.id.place_b1);
        txtImportant = findViewById(R.id.place_b2);
        txtCategory = findViewById(R.id.place_b3);


        placeSpinner = findViewById(R.id.place_spinner);
        final ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(ActivityPlaceImportant.this,
                android.R.layout.simple_dropdown_item_1line, place);
        placeSpinner.setAdapter(placeAdapter);
        placeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.Place = position;
            }
        });




        importantSpinner = findViewById(R.id.important_spinner);
        ArrayAdapter<String> importantAdapter = new ArrayAdapter<String>(ActivityPlaceImportant.this,
                android.R.layout.simple_dropdown_item_1line, important);
        importantSpinner.setAdapter(importantAdapter);
        importantSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.Importatnt = position;

            }
        });




        categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(ActivityPlaceImportant.this,
                android.R.layout.simple_dropdown_item_1line, category);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.category = position;

            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               txtPlace.setText(place[G.Place]);
               txtImportant.setText(important[G.Importatnt]);
               txtCategory.setText(category[G.category]);

            }
        });

    }

}

