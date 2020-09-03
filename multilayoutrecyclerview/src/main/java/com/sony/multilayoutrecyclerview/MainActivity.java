package com.sony.multilayoutrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.sony.multilayoutrecyclerview.ModelClass.USER_AD_LAYOUT;
import static com.sony.multilayoutrecyclerview.ModelClass.USER_IMAGE_LAYOUT;
import static com.sony.multilayoutrecyclerview.ModelClass.USER_INFO_LAYOUT;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(USER_INFO_LAYOUT, R.drawable.ic_launcher_background, "Pradip Yadav", "jbjwehgf jdgwm jh kdjdig ewhjweb hjgduywegdd hgdewdkjbni khfehf  yhfjkhfiuwh hwgfiwfi jhgifypef jhgfwhfjkbfjhg "));
        modelClassList.add(new ModelClass(USER_IMAGE_LAYOUT, R.mipmap.ic_launcher));
        modelClassList.add(new ModelClass(USER_AD_LAYOUT, "Ad Shows here.."));
        modelClassList.add(new ModelClass(USER_INFO_LAYOUT, R.drawable.ic_launcher_background, "Pradip Yadav", "jbjwehgf jdgwm jh kdjdig ewhjweb hjgduywegdd hgdewdkjbni khfehf  yhfjkhfiuwh hwgfiwfi jhgifypef jhgfwhfjkbfjhg "));
        modelClassList.add(new ModelClass(USER_AD_LAYOUT, "Ad Shows here.."));
        modelClassList.add(new ModelClass(USER_AD_LAYOUT, "Ad Shows here.."));
        modelClassList.add(new ModelClass(USER_INFO_LAYOUT, R.drawable.ic_launcher_background, "Pradip Yadav", "jbjwehgf jdgwm jh kdjdig ewhjweb hjgduywegdd hgdewdkjbni khfehf  yhfjkhfiuwh hwgfiwfi jhgifypef jhgfwhfjkbfjhg "));
        modelClassList.add(new ModelClass(USER_IMAGE_LAYOUT, R.mipmap.ic_launcher));
        modelClassList.add(new ModelClass(USER_INFO_LAYOUT, R.drawable.ic_launcher_background, "Pradip Yadav", "jbjwehgf jdgwm jh kdjdig ewhjweb hjgduywegdd hgdewdkjbni khfehf  yhfjkhfiuwh hwgfiwfi jhgifypef jhgfwhfjkbfjhg "));
        modelClassList.add(new ModelClass(USER_AD_LAYOUT, "Ad Shows here.."));
        modelClassList.add(new ModelClass(USER_IMAGE_LAYOUT, R.mipmap.ic_launcher));
        modelClassList.add(new ModelClass(USER_INFO_LAYOUT, R.drawable.ic_launcher_background, "Pradip Yadav", "jbjwehgf jdgwm jh kdjdig ewhjweb hjgduywegdd hgdewdkjbni khfehf  yhfjkhfiuwh hwgfiwfi jhgifypef jhgfwhfjkbfjhg "));
        modelClassList.add(new ModelClass(USER_IMAGE_LAYOUT, R.mipmap.ic_launcher));
        modelClassList.add(new ModelClass(USER_AD_LAYOUT, "Ad Shows here.."));
        modelClassList.add(new ModelClass(USER_IMAGE_LAYOUT, R.mipmap.ic_launcher));

        Adapter adapter=new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
