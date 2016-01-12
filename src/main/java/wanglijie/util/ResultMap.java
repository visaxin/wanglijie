package wanglijie.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by $Jason.Zhang on 1/3/16.
 */
public class ResultMap {
    public Map result(int statusCode,String info,String session){
        Map<String,String> res = new HashMap();
        res.put("status",String.valueOf(statusCode));
        res.put("info",info);
        res.put("session",session);
        return res;
    }

    public String resultJson(int statusCode,String info,String session){
        Gson gson = new Gson();
        return gson.toJson(result(statusCode,info,session));
    }


    public void responseResult(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(new ResultMap().resultJson(503, "Unknown", "SQL Exception")));
    }
}
