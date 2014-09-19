package test.support;

import java.lang.reflect.Method;

import org.apache.cxf.jaxws.EndpointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;


/**
 * @Description 
 * @author caobin
 * @date 2013-6-5
 * @version 1.0
 */
@ContextConfiguration(locations = {"classpath:/spring-basic.xml"})
@ActiveProfiles("dev")//激活dev配置
public abstract class AbstractSpringContextTestSupport extends AbstractTestNGSpringContextTests {
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	@BeforeMethod
	protected void echoInvokedMethod(Method method){
		log.info(">>>>> Invoking test method: {}", new Object[]{method.getName()});
	}
	
	/**
	 * @Description 从Spring容器中获取指定类型的bean
	 * @param beanType bean类型
	 * @return
	 * @author caobin
	 */
	protected<BEAN> BEAN getBean(Class<BEAN> beanType){
		return applicationContext.getBean(beanType);
	}
	
	/**
	 * @Description 从Spring容器中获取指定名称的bean
	 * @param beanName bean名称(或ID)
	 * @param beanType bean类型
	 * @return
	 * @author caobin
	 */
	protected<BEAN> BEAN getBean(String beanName, Class<BEAN> beanType){
		return applicationContext.getBean(beanName, beanType);	
	}
	
	/**
	 * @Description 初始化webService测试端点
	 * @param wsImplementor 服务接口实现
	 * @param endpointBeanName 端点Bean的名称(或ID)
	 * @param newWsAddr (重设)新的服务URI
	 * @author caobin
	 */
	protected void initWsEndpoint(Object wsImplementor, String endpointBeanName, String newWsAddr){
		//从spring容器中获取端点
		EndpointImpl realEP = this.getBean(endpointBeanName, EndpointImpl.class);
		//创建测试端点
		EndpointImpl testEP = new EndpointImpl(wsImplementor);
		//配置测试端点
		BeanUtils.copyProperties(realEP, testEP);
		//重设测试端点服务URI
		testEP.setAddress(newWsAddr);
		//发布测试端点
		testEP.publish();
	}
}
