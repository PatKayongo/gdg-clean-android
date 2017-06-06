package com.patkayongo.gazette.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.patkayongo.gazette.R;
import com.patkayongo.gazette.domain.Gazette;
import com.patkayongo.gazette.services.GazetteServiceImpl;

import java.util.List;

public class ListActivity extends AppCompatActivity implements GazetteListView {

    private GazetteListPresenter gazetteListPresenter;
    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.gazetteListPresenter = new GazetteListPresenter(this, new GazetteServiceImpl(Volley.newRequestQueue(this)));
        this.listView = (ListView)this.findViewById(R.id.gazette_list);
        this.editText = (EditText)this.findViewById(R.id.search_text);
    }

    @Override
    public String getSearchTerm() {
        return this.editText.getText().toString();
    }

    public void search(View view) {
        this.gazetteListPresenter.search();
    }

    @Override
    public void displayGazettes(List<Gazette> gazetteList) {
        String [] listItems = new String[gazetteList.size()];
        for (int i = 0; i < gazetteList.size(); i ++) {
            listItems[i] = gazetteList.get(i).getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        this.listView.setAdapter(adapter);
    }
}
