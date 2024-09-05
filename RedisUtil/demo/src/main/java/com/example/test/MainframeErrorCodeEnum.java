package com.example.test;

/**
 * @author Sky
 * create 2018/11/07
 * email sky.li@ixiaoshuidi.com
 **/
public enum MainframeErrorCodeEnum {
    ERRCODE0("0", "正常"),

    ERRCODE1("3", "与中控链路断开"),

    ERRCODE2("6", "等待响应超时"),

    ERRCODE3("11", "不支持的操作"),

    ERRCODE4("-1", "非法参数"),

    ERRCODE5("-2", "系统错误"),

    ERRCODE6("-201", "地暖面板通信异常"),

    ERRCODE7("-202", "温度传感器异常"),

    ERRCODE8("-203", "系统状态参数异常"),

    ERRCODE9("-301", "风机面板串口通讯错误"),

    ERRCODE10("-302", "风机盘管传感器错误"),

    ERRCODE11("-303", "风机盘管参数错误"),

    ERRCODE12("-304", "通风模式下,不能改变温度"),

    ERRCODE13("-305", "通风模式下,不能使用自动风量"),

    ERRCODE14("-401", "电能表参数长度错误"),

    ERRCODE15("-402", "电能表参数错误"),

    ERRCODE16("-403", "电能表通信超时"),

    ERRCODE17("-404", "电能表串口通信错误"),

    ERRCODE18("-501", "密码锁操作超时"),

    ERRCODE19("-502", "密码锁用户id不存在"),

    ERRCODE20("-503", "密码锁开门权限被禁用"),

    ERRCODE21("-504", "密码锁开门次数已用尽"),

    ERRCODE22("-505", "密码锁重复操作时间未授权"),

    ERRCODE23("-506", "密码锁操作时间未达到"),

    ERRCODE24("-507", "密码锁操作时间已过期"),

    ERRCODE25("-508", "密码锁密码错误"),

    ERRCODE26("-509", "密码锁用户满了"),

    ERRCODE27("-510", "密码锁密码重复"),

    ERRCODE28("-511", "密码锁用户已存在"),

    ERRCODE29("-512", "密码锁设置时间格式错误"),

    ERRCODE30("-513", "密码锁已经存在虚位密码"),

    ERRCODE31("-514", "密码锁密码太简单"),

    ERRCODE32("-515", "密码锁卡号已重复"),

    ERRCODE33("-601", "新风面板通信异常"),

    ERRCODE34("-602", "新风处于自动模式,不能设置工作模式"),

    ERRCODE35("-603", "新风处于热交换模式,不能设置高风速"),

    ERRCODE36("-604", "新风处于自动和热交换模式,不能设置风速"),

    ERRCODE37("-701", "快见面板按键越界"),

    ERRCODE38("-801", "空开离线"),

    ERRCODE39("-802", "空开地址越界"),

    ERRCODE40("-803", "空开操作参数错误"),

    ERRCODE41("-804", "空开配置参数异常"),

    ERRCODE42("-10001", "获取P码失败"),

    ERRCODE43("-10002", "获取P码超时"),

    ERRCODE44("-10003", "码库未找到"),

    ERRCODE45("-10004", "上传码库失败"),

    ERRCODE46("-10005", "码库命名冲突"),

    ERRCODE47("-10006", "没有红外码可以保存"),

    ERRCODE48("-10007", "下载码库失败"),

    ERRCODE49("-10008", "获取通道P码失败"),

    ERRCODE50("-10009", "发送红外码失败"),

    ERRCODE51("-10010", "禁止删除码库"),

    ERRCODE52("-10101", "不支持的网关类型"),

    ERRCODE53("-10102", "非法的mac地址"),

    ERRCODE54("-10103", "网关已添加"),

    ERRCODE55("-10104", "网关保存失败"),

    ERRCODE56("-10105", "白名单删除设备,设备已入网"),

    ERRCODE57("-10106", "网关白名单设备数超过64个"),

    ERRCODE58("-10027","主机设备操作失败");

    String code;
    String message;

    MainframeErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String findMessageByCode(String code) {
        for (MainframeErrorCodeEnum mainframeErrorCodeEnum : MainframeErrorCodeEnum.values()) {
            if (mainframeErrorCodeEnum.getCode().equals(code)) {
                return mainframeErrorCodeEnum.getMessage();
            }
        }
        return null;
    }
}
