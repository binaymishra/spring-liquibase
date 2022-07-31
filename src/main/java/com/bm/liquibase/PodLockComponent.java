package com.bm.liquibase;

import java.util.Date;
import java.util.UUID;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("podLockComponent")
public class PodLockComponent {

  private static final String POD_LOCK_SQL =
      "insert into pod_lock (pod_lock_id, pod_lock_name, pod_lock_datetime) "
          + "values ('%s', '%s','%s')";

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PodLockComponent(@Qualifier("dataSource") DataSource mysqlDataSource) {
    this.jdbcTemplate = new JdbcTemplate(mysqlDataSource);
  }

  public boolean lockPod(String uniqueLockName) {
    if (StringUtils.isBlank(uniqueLockName)) {
      return false;
    } else {
      String dateTime = DateFormatUtils.format(new Date(), "dd-MMM-yyyy HH:mm");
      final String uuid =
          UUID.nameUUIDFromBytes(StringUtils.join(dateTime, uniqueLockName).getBytes()).toString();
      try {
        int count =
            jdbcTemplate.update(String.format(POD_LOCK_SQL, uuid, uniqueLockName, dateTime));
        return count > 0;
      } catch (DataIntegrityViolationException e) {
        log.warn("{}", e.getMessage());
        return false;
      }
    }
  }
}
