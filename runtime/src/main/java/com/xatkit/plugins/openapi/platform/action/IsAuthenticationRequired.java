package com.xatkit.plugins.openapi.platform.action;

import java.util.HashMap;
import java.util.Map;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.openapi.platform.OpenAPIPlatform;

import edu.uoc.som.openapi2.API;
import edu.uoc.som.openapi2.commons.OpenAPIUtils;

public class IsAuthenticationRequired extends RuntimeAction<OpenAPIPlatform> {

    public IsAuthenticationRequired(OpenAPIPlatform platform, XatkitSession session) {
        super(platform, session);
    }

    @Override
    protected Map<String, Object> compute() throws Exception {
        API api = (API) session.get(OpenAPIPlatform.LOADED_API_KEY);
        Map<String, Object> results = new HashMap<String, Object> ();
        results.put("securityDefinitions", api.getSecurityDefinitions());
        results.put("security", api.getSecurity());
        results.put("operation", OpenAPIUtils.getOperationsIncludingSecurity(api));
        return results;
    }
}