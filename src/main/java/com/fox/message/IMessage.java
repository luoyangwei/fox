package com.fox.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fox.activity.Activity;
import com.fox.activity.ApplicationActivity;
import com.fox.event.ActionType;
import com.fox.event.element.Element;
import com.fox.register.ConfigurableLoadClasses;
import com.fox.register.HandleableConfigurationView;

import java.util.Map;

/**
 * <p>
 * 主要的消息对象
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/11/16 package: com.fox.message
 */
public class IMessage implements Message {

    private final ConfigurableLoadClasses configurableLoadClasses;
    /**
     * 原始消息内容
     */
    private final String baseMessageContent;
    /**
     * 消息总长度
     */
    private final int messageContentLength;

    /**
     * 操作的 activity
     */
    private Activity activity;
    /**
     * 操作类型
     */
    private ActionType actionType;
    /**
     * 操作的元素
     */
    private Element element;


    public IMessage(String baseMessageContent, ConfigurableLoadClasses configurableLoadClasses) {
        this.baseMessageContent = baseMessageContent;
        this.messageContentLength = baseMessageContent.length();
        this.configurableLoadClasses = configurableLoadClasses;

        // 先把消息转换成可以识别的json
        JSONObject jsonObject = JSONObject.parseObject(baseMessageContent);
        this.activity = getActivity(jsonObject.getJSONObject(MessageJsonKeys.PAGE_MAIN));


    }


    private ApplicationActivity getActivity(JSONObject pageJsonObject) {
        JSONObject activityJsonObject = pageJsonObject.getJSONObject(MessageJsonKeys.PAGE_ACTION);

        String activityViewId = activityJsonObject.getString(MessageJsonKeys.ELEMENT_ID);
        Map<String, HandleableConfigurationView> activityMap = configurableLoadClasses.getActivityClasses();
        HandleableConfigurationView handleableConfigurationView = activityMap.get(activityViewId);
        // 获取消息中的activity信息
        return null;
    }


    @Override
    public JSON toJson() {
        return (JSON) JSON.parse(this.baseMessageContent);
    }

}
