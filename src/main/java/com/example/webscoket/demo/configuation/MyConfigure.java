/**
 * FileName: MyConfigure
 * Author:   DING WEI
 * Date:     2019/2/1 13:22:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.webscoket.demo.configuation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈网关启动文件〉
 *
 * @author DING WEI
 * @create 2019/2/1 13:22:22
 * @since 1.0.0
 */
@Configuration
public class MyConfigure {

    @Bean
    public MyEndpointConfigure newConfigure() {
        return new MyEndpointConfigure();
    }

}
