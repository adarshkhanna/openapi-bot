package com.xatkit.plugins.openapi.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.openapi.platform.OpenAPIPlatform;
import com.xatkit.plugins.openapi.platform.utils.FormatterUtils;

import edu.uoc.som.openapi2.API;
import edu.uoc.som.openapi2.Path;

import java.util.HashMap;
import java.util.Map;

public class GetPathDetails extends RuntimeAction<OpenAPIPlatform> {
	  private String relativePath;

	    public GetPathDetails(OpenAPIPlatform platform, XatkitSession session, String relativePath) {
	        super(platform, session);
	        this.relativePath  = relativePath;
	    }

	    @Override
	    protected Object compute() throws Exception {
	        
	    	 API api = runtimePlatform.getApi(session);
	    	 Path path = api.getPathByRelativePath(relativePath);
	    	 Map<String,Object> result = new HashMap<String, Object>();
	    		 if(path!=null) {
	    			 result.put("found", true);
	    			 result.put("value", path);
	    			 result.put("formattedValue", FormatterUtils.formatPath(path));
	    		 }
	    		 else {
	    			 result.put("found", false);
	    		 }
	    			 
	    	return result;
	    }

}