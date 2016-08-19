package quartz.listener;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * scheduler 监听器
 * Created by chenzhixiong on 2016/8/12.
 */
public class MySchedulerListener implements SchedulerListener {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void jobScheduled(Trigger trigger) {
        logger.info("Trigger:"+trigger.getKey()+" scheduled...");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        logger.info("Trigger:"+triggerKey+" unScheduled...");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        logger.info("Trigger:"+trigger.getKey()+" finalized...");
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        logger.info("Trigger:"+triggerKey+" paused...");
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        logger.info("Trigger Group:"+triggerGroup+" paused...");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        logger.info("Trigger:"+triggerKey+" resumed...");
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        logger.info("Trigger Group:"+triggerGroup+" resumed...");
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {

    }

    @Override
    public void jobDeleted(JobKey jobKey) {

    }

    @Override
    public void jobPaused(JobKey jobKey) {

    }

    @Override
    public void jobsPaused(String jobGroup) {

    }

    @Override
    public void jobResumed(JobKey jobKey) {

    }

    @Override
    public void jobsResumed(String jobGroup) {

    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        logger.error(msg,cause);
    }

    @Override
    public void schedulerInStandbyMode() {

    }

    @Override
    public void schedulerStarted() {
        logger.info("Scheduler was started");
    }

    @Override
    public void schedulerStarting() {
        logger.info("Scheduler is starting");
    }

    @Override
    public void schedulerShutdown() {
        logger.info("Scheduler was shutdown");
    }

    @Override
    public void schedulerShuttingdown() {
        logger.info("Scheduler is shutting down");
    }

    @Override
    public void schedulingDataCleared() {
        logger.info("Scheduler's Data was cleared");
    }
}
