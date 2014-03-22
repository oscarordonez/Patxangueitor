package org.tfc.adapters;

import android.content.Intent;
import android.widget.*;
import com.appcelerator.cloud.sdk.ACSClient;
import com.appcelerator.cloud.sdk.CCMeta;
import com.appcelerator.cloud.sdk.CCRequestMethod;
import com.appcelerator.cloud.sdk.CCResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tfc.patxangueitor.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.tfc.patxangueitor.adminlistuser;

import java.util.HashMap;
import java.util.Map;


public class AdminListFragment extends Fragment {
    private ListView lv;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adminlist, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        /*
        // Test ACS
        ACSClient sdk = new ACSClient("<YOUR APP APP KEY>"); // app key
        //ACSClient sdk = new ACSClient("<OAuth Key>", "<OAuth Secret>"); // OAuth key & secret

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("where", "{'user': ' 5327444115d8270b5b03200a'}");
        CCResponse response = sdk.sendRequest("objects/Llista1/query.json", CCRequestMethod.GET, data);

        JSONObject responseJSON = response.getResponseData();
        CCMeta meta = response.getMeta();
        if("ok".equals(meta.getStatus())
                && meta.getCode() == 200
                && "queryCustomObjects".equals(meta.getMethod())) {
            JSONArray lists = responseJSON.getJSONArray("Llista1");

        }
        // Test ACS
        */
        String[] values = new String[] { "Llista 1", "Llista 2", "Llista 3" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);

        lv = (ListView)getView().findViewById(R.id.lvAdmin);
        lv.setAdapter(adapter);

        tv = (TextView)getView().findViewById(R.id.tvNewAdminList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent myIntent = new Intent(getActivity().getApplicationContext(), adminlistuser.class);
                startActivity(myIntent);
            }
        });

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) lv.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getActivity().getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }
        });*/
    }


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adminlist, null);
        ListView lv = (ListView) v.findViewById(R.id.lvAdmin);
        lv.setAdapter(new AdminListAdapter(getActivity().getApplicationContext()));

        //setHasOptionsMenu(true);

        // Listview on child click listener
        lv.setOnClickListener(new ListView.OnClickListener() {
            @Override
            public boolean onClick() {
                //Toast.makeText(SubsListFragment.this.getActivity(),"Hola", Toast.LENGTH_SHORT).show();
                //return false;
                Intent myIntent = new Intent(getActivity(), adminlistuser.class);
                startActivity(myIntent);
                return false;
            }
        });
        return v;

    }*/
}
