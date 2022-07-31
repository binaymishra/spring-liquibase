package com.bm.liquibase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service("scheduleRunService")
public class ScheduleRunService {

  @Value("${server.port}")
  private String lockedByPort;

  private final PodLockComponent podLockComponent;

  public ScheduleRunService(PodLockComponent podLockComponent) {
    this.podLockComponent = podLockComponent;
  }

  @Scheduled(cron = "0 0/1 * * * *")
  public void retryRunOne() {
    final String lockName = "retryRunOne";
    if(podLockComponent.lockPod(lockName)) {
      log.info("Successfully locked {} by {}", lockName, lockedByPort);
    }
  }

  @Scheduled(cron = "0 0/1 * * * *")
  public void retryRunTwo() {
    final String lockName = "retryRunTwo";
    if(podLockComponent.lockPod(lockName)) {
      log.info("Successfully locked {} by {}", lockName, lockedByPort);
    }
  }
}
