package Generic;

/**
 * Created by kuush on 6/27/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.io.IOException;

import Enum.TaskType;
import Abstract.AsyncTaskListener;
import HTTP.HttpManager;

public class Generic_Async_Post extends AsyncTask<Object,String ,String> {


    String outputStr;
    ProgressDialog dialog;
    Context context;
    AsyncTaskListener taskListener;
    TaskType taskType;

    public Generic_Async_Post(Context context, AsyncTaskListener taskListener, TaskType taskType){
        this.context = context;
        this.taskListener = taskListener;
        this.taskType = taskType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Loading", "Connecting to Server .. Please Wait", true);
        dialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(Object... objects) {
        String Data_From_Server = null;
        HttpManager http_manager = null;
        try{
            http_manager = new HttpManager();

                Data_From_Server = http_manager.Customer_Updated(objects);
                return Data_From_Server;


        }catch(Exception e){
            return e.getLocalizedMessage().toString().trim();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            taskListener.onTaskCompleted((Activity) context,result, taskType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }



}