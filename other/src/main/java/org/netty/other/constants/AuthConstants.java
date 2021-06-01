package org.netty.other.constants;

/**
 * @author luowx on 2019/2/22 0022.
 */
public class AuthConstants {

    /**
     * 授权码解析 ：机构码
     */
    public static final String AUTH_PARAM_ORG_CODE_KEY = "orgCode";

    /**
     * 授权码解析 ：授权数
     */
    public static final String  AUTH_PARAM_ALLOW_NUM_KEY= "maxNum";

    /**
     * 授权码解析 ：开始时间
     */
    public static final String  AUTH_PARAM_START_TIME_KEY= "startTime";

    /**
     * 授权码解析 ：结束时间
     */
    public static final String  AUTH_PARAM_END_TIME_KEY= "endTime";

    /**
     * 授权码解析 ：授权模块类型集合，多个之间逗隔开，1-魔点门禁，2-魔点签到，3-魔点考勤，4-魔点访客，5-魔点团餐
     */
    public static final String  AUTH_PARAM_MODULE_TYPE_KEY= "moduleTypes";

    /**
     * 生成时间
     */
    public static final String  AUTH_PARAM_GENERATE_TIME_KEY= "generateTime";


    /**
     * 授权版本号
     */
    public static final String  AUTH_PARAM_VERSION_KEY= "version";

    /**
     * 授权apps
     */
    public static final String  AUTH_PARAM_APPS_KEY= "licenseApps";

    /**
     * 授权app类型
     */
    public static final String  AUTH_PARAM_APP_TYPE_KEY= "appType";

    /**
     * 授权app数值
     */
    public static final String  AUTH_PARAM_APP_NUM_KEY= "appNum";

    /**
     * 授权app数值单位
     */
    public static final String  AUTH_PARAM_APP_UNIT_KEY= "appUnit";
}
