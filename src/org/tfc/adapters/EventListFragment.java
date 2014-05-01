package org.tfc.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.appcelerator.cloud.sdk.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tfc.patxangueitor.adminlistevent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar on 12/03/14.
 */

public class EventListFragment extends ListFragment {

    private String llista_id;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

          /*----------RECUPERAR SUBSCRIPCIONS (EVENTS) LLISTA------------------*/

        Bundle bundle = getActivity().getIntent().getExtras();
        llista_id = bundle.getString("Llista");

        ACSClient sdk = new ACSClient("iGXpZFRj2XCl9Aixrig80d0rrftOzRef",getActivity().getApplicationContext()); // app key
        JSONArray llista = new JSONArray();

        Map<String, Object> data = new HashMap<String, Object>();
        //data.put("where", "{\"user_id\" : \"5327444115d8270b5b03200a\"}");
        //data.put("where", "{\"id_llista\" : \"" + id_llista + "\"}");
        data.put("where", "{\"id_llista\" : \"" + llista_id + "\"}");
        data.put("order", "id_event");

        CCResponse response = null;
        try {
            response = sdk.sendRequest("objects/event/query.json", CCRequestMethod.GET, data);
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
                llista = responseJSON.getJSONArray("event");
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
        ArrayList<String> values = new ArrayList<String>();
        int i;
        for (i = 0; i < llista.length(); i++) {
            try {
                JSONObject aux = llista.getJSONObject(i);
                values.add(i, aux.getString("id_event"));
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        /*----------------------*/
        //String[] values = new String[] { "Event 1", "Event 2", "Event 3" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, values);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        //        android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent myIntent = new Intent(getActivity(), adminlistevent.class);
        startActivity(myIntent);
    }
}
