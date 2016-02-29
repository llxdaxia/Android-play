package cn.hotwoo.play.book;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.hotwoo.play.R;
import cn.hotwoo.play.book.model.Address;
import cn.hotwoo.play.book.model.AddressModel;
import cn.hotwoo.play.book.model.Callback;

public class AddressListActivity extends AppCompatActivity {

    private List<Address> data;
    private ListView listView;
    private AddressListAdapter addressListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        addressListAdapter = new AddressListAdapter(this,1);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(addressListAdapter);
        initData();


    }

    public void initData(){
        new AddressModel(this).getAddressList(new Callback<List<Address>>() {
            @Override
            public void callback(List<Address> data) {
                AddressListActivity.this.data = data;
                addressListAdapter.addAll(data);
            }
        });
    }


    class AddressListAdapter extends ArrayAdapter<Address> {

        public AddressListAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView name = new TextView(getContext());
            TextView phone = new TextView(getContext());
            name.setTextSize(20);
            phone.setTextSize(20);
            name.setText(getItem(position).getName());
            StringBuilder stringBuilder = new StringBuilder();
            for(String phoneNum : getItem(position).getPhone()){
                stringBuilder.append(phoneNum + "\n");
            }
            if(stringBuilder.length() > 1){
                stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
            }
            phone.setText(stringBuilder);

            linearLayout.addView(name);
            linearLayout.addView(phone);
            return linearLayout;
        }
    }
}
