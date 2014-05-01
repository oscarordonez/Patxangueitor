package org.tfc.patxangueitor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.appcelerator.cloud.sdk.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tfc.patxangueitor.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Oscar
 * Date: 25/03/14
 * Time: 21:15
 */
public class act_newuser extends Activity {
    private ListView lv;

    private String user_id;
    private String llista_id;
    private JSONObject auxJSON;
    private int i;
    private JSONArray users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);

    // Test ACS
    ACSClient sdk = new ACSClient("iGXpZFRj2XCl9Aixrig80d0rrftOzRef",getApplicationContext()); // app key

    users = new JSONArray();
    /*-----------RECUPERAR USUARIS-----------------*/
    CCResponse response = null;
    try {
        response = sdk.sendRequest("users/search.json", CCRequestMethod.GET, null);
    } catch (ACSClientError acsClientError) {
        acsClientError.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    JSONObject responseJSON = response.getResponseData();
    CCMeta meta = response.getMeta();
    if("ok".equals(meta.getStatus())
            && meta.getCode() == 200
            && "searchUsers".equals(meta.getMethod())) {
        try {
            users = responseJSON.getJSONArray("users");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    ArrayList<String> values = new ArrayList<String>();
    //int i;
    for (i = 0; i < users.length(); i++) {
        try {
            //JSONObject aux = llista.getJSONObject(i);
            auxJSON = users.getJSONObject(i);
            values.add(i, auxJSON.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
    lv = (ListView)findViewById(R.id.lvNewUsers);
    lv.setAdapter(adapter);
    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}