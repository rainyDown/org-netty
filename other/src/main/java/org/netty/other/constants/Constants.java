package org.netty.other.constants;

/**
 * @author luowx on 2018/10/25 0025.
 */
public class Constants {

    public static final int ZERO_INT = 0;
    public static final int ONE_INT = 1;
    public static final int TWO_INT = 2;
    public static final int THREE_INT = 3;
    public static final int FOUR_INT = 4;

    public static final long ZERO_LONG = 0L;

    public static final int DML_FAIL = 0;

    public static final int EXPIRE_TIME = 30;

    public static final int TOKEN_EXPIRE_TIME = 7200;

    public static final int WAIT_TIME = 500;

    public static final int HEART_EXPIRE_TIME = 35;

    public static final int HOURS_OF_DAY = 24;

    /**
     * 上传图片单台设备处理最大值，
     */
    public static final int PICTURE_MAX_NUM = 30;

    /**
     * 发布服务ip缓存key
     */
    public static final String SERVER_IP_ADDRESS_KEY = "server_ip_address";

    /**
     * 发布服务设备硬件信息id缓存key
     */
    public static final String SERVER_ORG_CODE_KEY = "server_org_code";

    /**
     * 服务器时区
     */
    public static final String SERVER_TIME_ZONE_KEY = "server_time_zone";

    /**
     * 服务当前版本缓存key
     */
    public static final String SERVER_CURRENT_VERSION_KEY = "server_current_version";

    /**
     * 通讯录同步key
     */
    public static final String SYNC_MEMBER_COUNT_KEY = "member";

    /**
     * 保存识别记录key
     */
    public static final String SAVE_VERIFY_RECORD_COUNT_KEY = "save_verify_record";

    /**
     * 默认sn
     */
    public static final String DEFAULT_TARGET = "000000000000";

    /**
     * 设备单位
     */
    public static final String  DEVICE_UNIT= "台";

    /**
     * 全部
     */
    public static final String  DEVICE_ALL= "全部";

    public static final String  DEVICE_OFF_LINE= "设备离线";

    public static final String DEFAULT_SCOPE = "1,2,3,4,5,6,7";

    public static final String DEFAULT_START_TIME = "00:00";

    public static final String DEFAULT_END_TIME = "23:59";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    public static final int DEFAULT_OPEN_TIME = 4;

    public static final int DEFAULT_THRESHOLD_SCORE = 72;

    public static final int DEFAULT_FAST_MODE = 1;

    public static final int DEFAULT_DELAY_UPGRADE = 10;

    public static final int DEFAULT_BACKUP_DATA_COUNT = 4;

    public static final int DEFAULT_RECORD_PERIOD = 90;

    public static final int DEFAULT_LIGHT = 70;

    public static final int DEFAULT_FRAME = 3;

    public static final int DEFAULT_FREEZE_TIME = 15 * 60 * 1000;

    public static final int DEFAULT_FREEZE_INTERVAL_TIME = 5 * 60 * 1000;

    public static final int DEFAULT_MISTAKE_TIME = 4;

    public static final int DEFAULT_SNAP_PERIOD = 90;

    /**
     * 重抽人脸进度缓存key
     */
    public static final String FACE_RELOAD_PROGRESS = "face_reload_progress";

    /**
     * 冻结账号缓存key
     */
    public static final String FREEZE_ACCOUNT = "freeze_account_";

    public static final String DEFAULT_MOBILE_PRE = "+86";

    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 默认口罩模式关闭
     */
    public static final int DEFAULT_MASK_MODEL = 0;

    /**
     * 默认铃声模式水晶
     */
    public static final int DEFAULT_RING_MODEL = 1;

    /**
     * 默认字号，中
     */
    public static final int DEFAULT_FONT_SIZE = 3;

    /**
     * 默认展示时长(毫秒)
     */
    public static final int DEFAULT_SHOW_TIME = 800;

    /**
     * 访客失效1天
     */
    public static final int DEFAULT_VISITOR_INVALID_TIME = 24 * 60 * 60 * 1000;

    /**
     * 默认录脸开启查重
     */
    public static final int DEFAULT_REPEAT_FACE = 1;
}
