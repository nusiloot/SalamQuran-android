package com.salamquran.android.salamquran.Lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.salamquran.android.R;
import com.salamquran.android.salamquran.api.LmsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LmsLevelList_Activity extends AppCompatActivity {

  String id = null;

  private RecyclerView recyclerView;
  private List<LmsModel_levelList> model;
  private LmsLevelList_Adaptor adaptor;
  private LinearLayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lms_level_list);
    id = getIntent().getStringExtra("id");

    recyclerView = findViewById(R.id.recycler_view);
    model = new ArrayList<>();
    adaptor = new LmsLevelList_Adaptor(getApplication(),model);
    layoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
    recyclerView.setAdapter(adaptor);

    LmsApi.levelList(getApplication(), id, new LmsApi.levelListListener() {
      @Override
      public void onReceived(String Result) {
        addItem(Result);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adaptor.notifyDataSetChanged();
      }

      @Override
      public void onFiled() {

      }
    });


  }

  private void addItem(String Response){
    try {
      JSONArray array = new JSONArray(Response);
      for (int i = 0; i < array.length(); i++) {
        JSONObject object = array.getJSONObject(i);


/*{
    "id": "37",
      "lm_group_id": "c",
      "title": "الفبای قرآن",
      "desc": "آشنایی با ۲۸ حرف عربی",
      "type": "reading",
      "type_title": "Reading quran",
      "file": "https://salamquran.com/files/1/126-adce855c35180f774aca8dfe7802249b.mp4",
      "sort": "102",
      "ratio": "1",
      "unlockscore": null,
      "badge": "LmsFirstFullScore",
      "userstar": null,
      "xtype": "video"
  }*/

        model.add(
            new LmsModel_levelList(
                object.getString("id"),
                null,
                object.getString("title"),
                object.getString("desc"),
                null,
                null,
                object.getString("file"),
                object.getString("filepic"),
                null,
                null,
                null,
                null,
                null,
                null
                ));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
