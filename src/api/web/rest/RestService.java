package api.web.rest;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import api.Constants;
import api.db.dao.controller.DataRecordGetter;
import api.util.formatter.ListMapToJson;

@Path("/")
public class RestService {		
		@GET @Path("/data")
		public Response getData(@DefaultValue("") @QueryParam(Constants.URI_OBJECT_NAME) String objName, @DefaultValue("") @QueryParam(Constants.URI_FILTERS) String filters,
	            @Context UriInfo uriInfo) {
			
			List<Map<String, Object>> dbResults = DataRecordGetter.fetch(objName, filters);
			
			if (dbResults==null || dbResults.isEmpty() ) {
				return  Response.status(404).entity("Object not found [" + objName+"]").build();
			}
			String output = ListMapToJson.convert(dbResults).toString();
			return Response.status(200).entity(output).build();
	 
		}

		@GET @Path("/meta")
		public Response getMeta(@DefaultValue("") @QueryParam(Constants.URI_OBJECT_NAME) String objName, @DefaultValue("")
	            @Context UriInfo uriInfo) {
			
			List<Map<String, Object>> dbColumnMetas = DataRecordGetter.fetch(objName);
			
			if (dbColumnMetas==null || dbColumnMetas.isEmpty() ) {
				return  Response.status(404).entity("Object not found [" + objName+"]").build();
			}
			String output = ListMapToJson.convert(dbColumnMetas).toString();
			return Response.status(200).entity(output).build();
	 
		}		
}

