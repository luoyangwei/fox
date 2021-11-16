package com.fox.message;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 这个是消息的主类
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/13 package: com.fox.message
 */
public interface Message {


    /**
     * 把消息转换成json
     *
     * @return {@link JSON}
     */
    JSON toJson();

}
