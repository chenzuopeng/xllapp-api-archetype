package org.xllapp.api.support;


/**
 * 此类用于存放应用配置,这些配置可以在运行时通过访问/config进行配置.
 * 
 * 注:支持运行时配置属性的类型：基础类型,String类型.
 * 
 * 例：
 * 
 * public class ApplicationConfig extends ApiApplicationConfig {
 * 
 *       @FieldDescription("是否发送事件") 
 *       private String isFireEvent;
 * 
 *       public boolean isFireEvent() {
 *          return this.isFireEvent;
 *       } 
 *                             
 *  }
 *
 * @author dylan.chen Aug 21, 2014
 * 
 */
public class ApplicationConfig extends ApiApplicationConfig {
    
}
