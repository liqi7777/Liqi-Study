package com.jz.test.redistest.mapStudy;

public class Common {
    public static final String SF_FENCE = "sf:{geographic_fence}:";
    public static class PinHuaParam {
        public  static  double shiftDis=4d;
    }    /**
     * 倒车参数
     */
    public static class ReverseParam {
        //转弯半径1
        public static final double dis1 = 0.75d;
        //转弯半径2
        public static final double dis2 = 1d;
        public static final double dis3 = 0.75d;
    }

    public static class MapParam {
        public static final double FOOT_DISTANCE_THRELOAD = 1.5;
        public static final double ROAD_DIS_THRELOAD = 6;
    }

    public static class PlanCode {
        public static final String START_TOO_FAR = "START_TOO_FAR";
        public static final String END_TOO_FAR = "END_TOO_FAR";
    }

    /**
     * 动作 切到下一段路的动作0-反向倒车 1-直行  2-左转 4-右转   8-左变道 16-右变道   32-掉头
     */
    public static class Action {
        public static final String REVERSE = "0";
        public static final String STRAIGHT = "1";
        public static final String LEFT_TURN = "2";
        public static final String RIGHT_TURN = "4";
        public static final String LEFT_LANE = "8";
        public static final String RIGHT_LANE = "16";
        public static final String TURN = "32";
    }

    /**
     * 1-直行 2-转弯 8-变道
     */
    public static class BuildAction {
        public static final String STRAIGHT = "1";
        public static final String TURN = "2";
        public static final String CHANGE_LANE = "8";
    }
}
