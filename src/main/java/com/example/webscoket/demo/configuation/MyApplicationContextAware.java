/**
 * FileName: MyApplicationContextAware
 * Author:   DING WEI
 * Date:     2019/2/1 13:11:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.webscoket.demo.configuation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈网关启动文件〉
 *
 * @author DING WEI
 * @create 2019/2/1 13:11:11
 * @since 1.0.0
 */
@Component
@Lazy(false)
public class MyApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
}
