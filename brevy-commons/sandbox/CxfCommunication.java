/**
 * @Title:CXFServiceUtil.java 
 * @Package:com.ips.core.pbcs.util
 * @Description:TODO(用一句话描述该文件做什么)
 * @author:wangy
 * @date:2012-8-15
 * @version	V1.0
 */
package com.ips.commons.ws.support;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description 
 * @author caobin
 * @date 2013-5-27
 * @version 1.0
 */
public class CxfCommunication {

	/**
	 * @Description 动态webService调用
	 * @param wsUrl webservcie地址
	 * @param method 调用方法
	 * @param arg 参数
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	public static String wsInvoke(String wsUrl, String method, Object... arg) throws Exception {
		if(StringUtils.isBlank(wsUrl))throw new Exception("wsUrl is required.");
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		String wsdlUrl = wsUrl.concat("?wsdl");
		if(!WS_CLIENT_CACHE.containsKey(wsUrl)){
			synchronized (CxfCommunication.class) {
				if(!WS_CLIENT_CACHE.containsKey(wsdlUrl)){
					LOG.debug("cannot find key [{}] from webService client cache", new Object[]{wsUrl});
					Client client = dcf.createClient(wsdlUrl);
					
					
					List<ServiceInfo> lstSI = client.getEndpoint().getService().getServiceInfos();
					
					if(lstSI != null){
						for(ServiceInfo si : lstSI){
							Collection<EndpointInfo> cs = si.getEndpoints();
							if(cs != null){
								for(EndpointInfo ei : cs){
									ei.setAddress(wsUrl);
								}
							}
						}
					}
					WS_CLIENT_CACHE.put(wsUrl, client);
				}else{
					LOG.debug("load webService client from client cache with key [{}]", new Object[]{wsUrl});
				}
			}
		}	
		Client client = (Client)WS_CLIENT_CACHE.get(wsUrl);
		Object[] res = client.invoke(method, arg);
		return (String) res[0];
	}
	
	
	private final static LRUMap WS_CLIENT_CACHE = new LRUMap(200);
	
	private final static Logger LOG = LoggerFactory.getLogger(CxfCommunication.class);
}