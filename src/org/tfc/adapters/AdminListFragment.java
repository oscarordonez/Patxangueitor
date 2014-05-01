package org.tfc.adapters;

import android.content.Context;
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
    //private TextView tv;

    private String user_id;
    private String llista_id;
    private JSONObject auxJSON;
    private int i;
    private JSONArray llista;


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

        llista = new JSONArray();

        Bundle bundle = getActivity().getIntent().getExtras();
        user_id = bundle.getString("User");

        /*-----------RECUPERAR LLISTES-----------------*/
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("where", "{\"user_id\" : \"" + user_id + "\"}");
        data.put("order", "nom");

        CCResponse response = null;
        try {
            response = sdk.sendRequest("objects/llista/query.json", CCRequestMethod.GET, data);
        } catch (ACSClientError acsClientError) {
            acsClientError.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject responseJSON = response.getResponseData();
        CCMeta meta = response.getMeta();
        if("ok".equals(meta.getStatus())
                && meta.getCode() == 200
                && "queryCustomObjects".equals(meta.getMethod())) {
            try {
                llista = responseJSON.getJSONArray("llista");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> values = new ArrayList<String>();
        //int i;
        for (i = 0; i < llista.length(); i++) {
            try {
                //JSONObject aux = llista.getJSONObject(i);
                auxJSON = llista.getJSONObject(i);
                values.add(i, auxJSON.getString("nom"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //String[] values = new String[] { "Llista 1", "Llista 2", "Llista 3" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
        lv = (ListView)getView().findViewById(R.id.lvAdmin);
        View footerView =  ((LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_footer,null, false);
        lv.addFooterView(footerView);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //tv = (TextView)getView().findViewById(R.id.tvNewAdminList);

        footerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity().getApplicationContext(), act_newlist.class);
                startActivity(intent3);
                //do something
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                try {
                    auxJSON = llista.getJSONObject(position);
                    llista_id = auxJSON.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent myIntent = new Intent(getActivity().getApplicationContext(), adminlistuser.class);
                Bundle b = new Bundle();
                b.putString("Llista", llista_id);
                myIntent.putExtras(b);
                startActivity(myIntent);
            }
        });
    }
}