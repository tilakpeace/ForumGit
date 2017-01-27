package tilak.sample.com.dao;

import java.util.List;

public interface ActivityDetailDao {

    @SuppressWarnings("rawtypes")
    public List procActivityDetail(String activityDetailId,
                                   String description, String user, String flag);
}
