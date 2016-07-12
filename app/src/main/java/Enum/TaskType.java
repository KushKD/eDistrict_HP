package Enum;

/**
 * Created by kuush on 6/17/2016.
 */
public enum TaskType {

      USER_LOGIN(1),CENCUS_DISTRICT(2),CENCUS_TEHSIL(3),CENCUS_VILLAGE_TOWN(4);

    int value; private TaskType(int value) { this.value = value; }


}
