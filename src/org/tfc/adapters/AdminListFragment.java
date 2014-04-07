package org.tfc.adapters;

import android.content.Intent;
import android.widget.*;
import com.appcelerator.cloud.sdk.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tfc.patxangueitor.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.tfc.patxangueitor.act_newlist;
import org.tfc.patxangueitor.adminlistuser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AdminListFragment extends Fragment {
    private ListView lv;
    private TextView tv;

    private String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adminlist, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // Test ACS
        ACSClient sdk = new ACSClient("iGXpZFRj2XCl9Aixrig80d0rrftOzRef",getActivity().getApplicationContext()); // app key
        //ACSClient sdk = new ACSClient("<OAuth Key>", "<OAuth Secret>"); // OAuth key & secret

        JSONArray llista = new JSONArray();

        Bundle bundle = getActivity().getIntent().getExtras();
        user_id = bundle.getString("User");

        Map<String, Object> data = new HashMap<String, Object>();
        //data.put("where", "{\"user_id\" : \"5327444115d8270b5b03200a\"}");
        data.put("where", "{\"user_id\" : \"" + user_id + "\"}");
        data.put("order", "nom");

        CCResponse response = null;
        try {
            response = sdk.sendRequest("objects/llista/query.json", CCRequestMethod.GET, data);
        } catch (ACSClientError acsClientError) {
            acsClientError.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        JSONObject responseJSON = response.getResponseData();
        CCMeta meta = response.getMeta();
        if("ok".equals(meta.getStatus())
                && meta.getCode() == 200
                && "queryCustomObjects".equals(meta.getMethod())) {
            try {
                llista = responseJSON.getJSONArray("llista");
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
        ArrayList<String> values = new ArrayList<String>();
        int i;
        for (i = 0; i < llista.length(); i++) {
            try {
                JSONObject aux = llista.getJSONObject(i);
                values.add(i, aux.getString("nom"));
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        // Test ACS

        //String[] values = new String[] { "Llista 1", "Llista 2", "Llista 3" };
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

        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity().getApplicationContext(), act_newlist.class);
                //Bundle b = new Bundle();
                //b.putString("Session", session);
                //intent3.putExtras(b);
                startActivity(intent3);
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
