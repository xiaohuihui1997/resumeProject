package com.wjz.utils;

public interface CommonVariable {
    interface ResumeVariable{
        //待筛选(待推送)
        Integer TO_BE_SCREENED = 0;

        //初筛中
        Integer PRIMARY_SCREENING = 10;

        //初筛通过
        Integer PRIMARY_SCREENING_PASS = 11;

        //初筛未通过
        Integer PRIMARY_SCREENING_NOT_PASS = 12;

        //初试
        Integer PRELIMINARY_TEST = 20;

        //初试通过
        Integer PRELIMINARY_TEST_PASS = 21;

        //初试未通过
        Integer PRELIMINARY_TEST_NOT_PASS = 22;

        //复试
        Integer SECONDARY_EXAMINATION = 30;

        //复试通过
        Integer SECONDARY_TEST_PASS = 31;

        //复试未通过
        Integer SECONDARY_TEST_NOT_PASS = 32;
    }

    interface ResumeProcessVariable{
        //初筛
        Integer PRIMARY_SCREENING = 1;

        //初试
        Integer PRELIMINARY_TEST = 2;

        //复试
        Integer SECONDARY_EXAMINATION = 3;
    }
    //未删除
    String IS_NOT_DELETE = "0";

    //已推送
    Integer IS_PUSH = 1;

    //待定
    Integer UNDETERMINED = 0;

    //通过
    Integer IS_PASS = 1;

    //未通过
    Integer IS_NOT_PASS = 2;

    //待筛选
    String TO_BE_SCREENED = "待筛选";

    //初筛中
    String PRELIMINARY_SCREENING_TO_BE_CONFIRMED = "初筛中";

    //初筛通过
    String PRIMARY_SCREENING_PASS = "初筛通过";

    //初筛未通过
    String PRIMARY_SCREENING_NOT_PASS = "初筛未通过";

    //初试中
    String PRELIMINARY_TESTING = "初试中";

    //初试通过
    String PRELIMINARY_TEST_PASS = "初试通过";

    //初试未通过
    String PRELIMINARY_TEST_NOT_PASS = "初试未通过";

    //复试中
    String SECONDARY_TESTING = "复试中";

    //复试通过
    String SECONDARY_TEST_PASS = "复试通过";

    //复试未通过
    String SECONDARY_TEST_NOT_PASS = "复试未通过";

}
