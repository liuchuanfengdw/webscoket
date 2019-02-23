/**
 * FileName: MyEndpointConfigure
 * Author:   DING WEI
 * Date:     2019/2/1 13:21:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.webscoket.demo.configuation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.websocket.server.ServerEndpointConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈网关启动文件〉
 *
 * @author DING WEI
 * @create 2019/2/1 13:21:21
 * @since 1.0.0
 */
public class MyEndpointConfigure extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("auto load"+this.hashCode());
        MyEndpointConfigure.context = applicationContext;
    }

}
