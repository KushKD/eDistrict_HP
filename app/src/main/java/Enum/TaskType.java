package Enum;

/**
 * Created by kuush on 6/17/2016.
 */
public enum TaskType {

      USER_LOGIN(1),CENCUS_DISTRICT(2),CENCUS_TEHSIL(3),CENCUS_VILLAGE_TOWN(4),WARD(5),MUNICIPALITY(6),BLOCK(7),PANCHAYAT(8),QUESTION_ONE(9), QUESTION_TWO(10);

    int value; private TaskType(int value) { this.value = value; }


}
