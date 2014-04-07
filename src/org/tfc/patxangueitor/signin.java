package org.tfc.patxangueitor;


import android.app.ProgressDialog;
import com.appcelerator.cloud.sdk.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class signin extends Activity {
     private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        View connectbtn = findViewById(R.id.btnOk);
        /*connectbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(signin.this,mainscreen.class);
                startActivity(intent);
            }
        });*/

        connectbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(signin.this);
                dialog.setMessage("Connectant...");
                dialog.show();

                if (performsignin()){
                    Intent intent = new Intent(signin.this,mainscreen.class);
                    Bundle b = new Bundle();
                    b.putString("Status","Connected");
                    b.putString("User", user_id);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                {
                    ((EditText) findViewById(R.id.txt_user)).setText("");
                    ((EditText) findViewById(R.id.txt_pass)).setText("");
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Usuario / contraseña incorrectos", Toast.LENGTH_LONG);
                    toast1.show();
                }
                dialog.dismiss();
            }
        });

        View cancelbtn = findViewById(R.id.btnCancel);
        cancelbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){ finish(); }
        });

        View newuserbtn = findViewById(R.id.btnNewUser);
        newuserbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent3 = new Intent(signin.this,signup.class);
                startActivity(intent3);
            }
        });
    }

    public boolean performsignin(){
        boolean boolSuccess = false;
        ACSClient sdk = new ACSClient("iGXpZFRj2XCl9Aixrig80d0rrftOzRef",getApplicationContext());

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("login", ((EditText) findViewById(R.id.txt_user)).getText().toString());
        dataMap.put("password", ((EditText) findViewById(R.id.txt_pass)).getText().toString());

        try {
            CCResponse response;
            response = sdk.sendRequest("users/login.json", CCRequestMethod.POST, dataMap);
            CCMeta meta = response.getMeta();
            if("ok".equals(meta.getStatus())
                    && meta.getCode() == 200
                    && "loginUser".equals(meta.getMethod())){
                try{
                    JSONObject json = response.getResponseData();
                    JSONArray users = json.getJSONArray("users");
                    JSONObject aux = users.getJSONObject(0);

                    user_id = aux.getString("id");
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                //JSONArray responseJSON = meta.toString();
                //session = responseJSON.
                boolSuccess = true;
                }
            else{
                boolSuccess = false;
            }
        } catch (ACSClientError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boolSuccess;

    }
}
