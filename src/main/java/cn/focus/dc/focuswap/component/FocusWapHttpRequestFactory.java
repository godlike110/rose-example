package cn.focus.dc.focuswap.component;

import cn.focus.dc.focuswap.config.AppConstants;

import java.net.URI;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


@SuppressWarnings("deprecation")
public class FocusWapHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 300;

    private static final int DEFAULT_TIMEOUT_MILLISECONDS = 5 * 1000;
    
    private static final String DEFAULT_SIGN_NAME = "sign";

    public FocusWapHttpRequestFactory(int maxConnectionsPerRoute) {
        super();
        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);
        HttpClient httpClient = new DefaultHttpClient(connectionManager);
        super.setHttpClient(httpClient);
        setReadTimeout(DEFAULT_TIMEOUT_MILLISECONDS);
        setConnectTimeout(DEFAULT_TIMEOUT_MILLISECONDS);
    }

    // TODO 现在默认查询字符串已经排序过,将来可能会取消该默认。
    @Override
    protected void postProcessHttpRequest(HttpUriRequest request) {
        if (StringUtils.equals(HttpGet.METHOD_NAME, request.getMethod())) {
            URI url = request.getURI();
            String path = url.getPath();
            String host = url.getHost();
            String query = url.getQuery();
            if (null != query) {
                String sign = genSignParam(query,host + path);
                if(StringUtils.isNotBlank(sign)){
                    request.addHeader(DEFAULT_SIGN_NAME, sign);
                }
            }
        }
        
        super.postProcessHttpRequest(request);
        
    }
    
//    private String genSignParam(String queryStr) {
//        if (StringUtils.isBlank(queryStr)) {
//            return DigestUtils.md5Hex(DATACENTER_BUILDING_SECRETKEY);
//        } else {
//            StringBuilder sb = new StringBuilder();
//            sb.append(StringUtils.chomp(queryStr, "&")).append(DATACENTER_BUILDING_SECRETKEY);
//            return DigestUtils.md5Hex(sb.toString());
//        }
//        
//    }
    
    private String genSignParam(String queryStr,String path) {
        StringBuilder sb = new StringBuilder();
        String s = AppConstants.secretKeyMap.get(path);
        if (StringUtils.isNotBlank(s)) {
            if (StringUtils.isBlank(queryStr)) {
                sb.append(s);
            } else {
                sb.append(StringUtils.chomp(queryStr, "&")).append(s);
            }
        } else {
            return null;
        }
        return DigestUtils.md5Hex(sb.toString());
    }
}
