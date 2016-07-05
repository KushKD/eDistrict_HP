package Abstract;


import android.app.Activity;

import java.io.IOException;

import Enum.TaskType;

/**
 * Created by kuush on 6/17/2016.
 */

public interface AsyncTaskListener {

     void onTaskCompleted(String result, TaskType taskType) throws IOException;

     void onTaskCompleted(Activity activity, String result, TaskType taskType);


}

