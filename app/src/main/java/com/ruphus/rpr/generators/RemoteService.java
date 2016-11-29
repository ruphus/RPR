package com.ruphus.rpr.generators;

import android.os.AsyncTask;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;



public class RemoteService extends AsyncTask<Integer, Void, RandomResult> {

    private static SimpleDateFormat dateFormat;
    private static HashMap requestParams;

    static{
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        requestParams = new HashMap<String, Object>();
        requestParams.put("apiKey", "df1a8ada-2722-4ae4-8ca5-53b2b992f11e");
        requestParams.put("n", 1);
        requestParams.put("min", 0);
        requestParams.put("max", 1);
        requestParams.put("base", 2);
    }

    @Override
    protected RandomResult doInBackground(Integer... params) {
        RandomResult randomResult = new RandomResult();

        try {
            URL serverURL = new URL("https://api.random.org/json-rpc/1/invoke");

            JSONRPC2Session session = new JSONRPC2Session(serverURL);
            session.getOptions().setRequestContentType("application/json-rpc");
            session.getOptions().setConnectTimeout(params[0]);

            JSONRPC2Request request = new JSONRPC2Request(
                "generateIntegers", requestParams, Math.abs(UUID.randomUUID().hashCode())
            );

            JSONRPC2Response response = session.send(request);

            if (response.indicatesSuccess())
            {
                JSONObject json = response.toJSONObject();
                JSONObject result = (JSONObject) json.get("result");
                JSONObject random = (JSONObject) result.get("random");
                JSONArray data = (JSONArray) random.get("data");
                String completionTime = (String) random.get("completionTime");

                randomResult.setData(((int) data.get(0)) == 1);
                randomResult.setCompletionTime(dateFormat.parse(completionTime));
            }
            else
            {
                throw new Exception(response.getError().getCause());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            randomResult.setErrorMessage(e.getMessage());
        }

        return randomResult;
    }
}
