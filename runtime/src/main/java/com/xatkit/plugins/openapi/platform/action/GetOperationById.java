package com.xatkit.plugins.openapi.platform.action;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.openapi.platform.OpenAPIPlatform;
import com.xatkit.plugins.openapi.platform.utils.OpenAPIUtils;
import edu.uoc.som.openapi2.API;
import edu.uoc.som.openapi2.Operation;
import edu.uoc.som.openapi2.Path;

public class GetOperationById extends RuntimeAction<OpenAPIPlatform> {
	
	
	private String operationId;
	private API api;

    public GetOperationById(OpenAPIPlatform platform, XatkitSession session, String operationId) {
        super(platform, session);
        api = (API) session.get(OpenAPIPlatform.LOADED_API_KEY);
		this.operationId = operationId;
    }

    @Override
    protected Map<String, Object> compute() throws Exception {
    	Operation operation = OpenAPIUtils.getOperationByIdIgnoreCase(api,operationId);
    	Map<String, Object> result = new HashMap<String, Object>();
    	if(nonNull(operation)) {
    		result.put("found", true);
    		result.put("value", operation);
    		result.put("path",((Path) operation.eContainer()).getRelativePath());
    		result.put("responses",operation.getResponses().entrySet().stream().collect(Collectors.toList()));
    	}
    	else {
    		result.put("found", false);
    		result.put("operations", api.getAllOperations());
    	}
        return result;
    }
}
