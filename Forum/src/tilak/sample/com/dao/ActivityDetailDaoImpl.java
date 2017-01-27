package tilak.sample.com.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ActivityDetailDaoImpl extends JdbcTemplate implements
        ActivityDetailDao {
    @SuppressWarnings("rawtypes")
    @Override
    public List procActivityDetail(String activityDetailId,
                                   String description, String user, String flag) {

        String procName = "proc_activity_detail";
        SimpleJdbcCall call = new SimpleJdbcCall(this);

        call = call.withProcedureName(procName);
        call.addDeclaredParameter(new SqlParameter("IN_FLAG", Types.CHAR));
        call.addDeclaredParameter(new SqlParameter("IN_ACTIVITY_DETAIL_ID", Types.NUMERIC));
        call.addDeclaredParameter(new SqlParameter("IN_DESCRIPTION", Types.VARCHAR));
        call.addDeclaredParameter(new SqlParameter("IN_USER", Types.VARCHAR));

        Map<String, Object> inp = new HashMap<String, Object>();

        inp.put("IN_FLAG", flag);
        inp.put("IN_ACTIVITY_DETAIL_ID", activityDetailId);
        inp.put("IN_DESCRIPTION", description);
        inp.put("IN_USER", user);

        Map<String, Object> resultMap = call.execute(inp);

        String keyName = resultMap.keySet().toArray()[0].toString();

        return (ArrayList<Map>) resultMap.get(keyName);

    }

}
