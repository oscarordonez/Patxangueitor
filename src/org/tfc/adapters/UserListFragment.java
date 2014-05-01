package org.tfc.adapters;

import android.content.Context;
import android.content.Intent;
import org.tfc.patxangueitor.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.appcelerator.cloud.sdk.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tfc.patxangueitor.act_newuser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar on 12/03/14.
 */

public class UserListFragment extends Fragment {
    private ListView lv;
    private TextView tv;
    private String llista_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.userlist, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*----------RECUPERAR SUBSCRIPCIONS (USUARIS) LLISTA------------------*/

        Bundle bundle = getActivity().getIntent().getExtras();
        llista_id = bundle.getString("Llista");

        ACSClient sdk = new ACSClient("iGXpZFRj2XCl9Aixrig80d0rrftOzRef",getActivity().getApplicationContext()); // app key
        JSONArray llista = new JSONArray();

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("where", "{\"id_llista\" : \"" + llista_id + "\"}");
        data.put("order", "id_usuari");

        CCResponse response = null;
        try {
            response = sdk.sendRequest("objects/subscripcio_usuari/query.json", CCRequestMethod.GET, data);
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
                llista = responseJSON.getJSONArray("subscripcio_usuari");
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
        ArrayList<String> values = new ArrayList<String>();
        int i;
        for (i = 0; i < llista.length(); i++) {
            try {
                JSONObject aux = llista.getJSONObject(i);
                values.add(i, aux.getString("id_usuari"));
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        /*----------------------*/


        //String[] values = new String[] { "Usuari 1", "Usuari 2", "Usuari 3" };
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
        //setListAdapter(adapter);
        //tv = (TextView)getView().findViewById(org.tfc.patxangueitor.R.id.AddNewUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
        lv = (ListView)getView().findViewById(org.tfc.patxangueitor.R.id.lvUsers);
        View footerView =  ((LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(org.tfc.patxangueitor.R.layout.user_footer,null, false);
        lv.addFooterView(footerView);
        lv.setAdapter(adapter);



        footerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity().getApplicationContext(), act_newuser.class);
                startActivity(intent3);
                //do something
            }
        });
    }

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // do something with the data
    } */
}