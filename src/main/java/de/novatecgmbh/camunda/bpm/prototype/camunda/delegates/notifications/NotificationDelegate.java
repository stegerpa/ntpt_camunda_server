package de.novatecgmbh.camunda.bpm.prototype.camunda.delegates.notifications;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contains all the Notification Methods, checks current Activity (Task) and print to the console
 * @author Patrick Steger
 */
@Service("NotificationAdapter")
public class NotificationDelegate implements JavaDelegate {

    @Autowired
    RuntimeService service;

    public void execute(DelegateExecution execution) throws Exception {
        String additionalArtifactsDesc = (String) execution.getVariable("additional_artifacts_text");

        if (execution.getCurrentActivityId().equals("RequestArtifactsNotification")) {
            service.startProcessInstanceByMessage("notifyAdminToCreateArtifacts", execution.getVariables());
            System.out.println("\n######\n");
            System.out.println("NOW WE WOULD SEND A NOTIFICATION TO SYSADMIN TO CREATE THE ADDITIONAL ARTIFACTS");
            System.out.println("To Sys-Admin: \n" + additionalArtifactsDesc);
            System.out.println("\n######\n");
        }

        if (execution.getCurrentActivityId().equals("CloseTimerNotification")) {
            System.out.println("\n######\n");
            System.out.println("NOW WE WOULD SEND A REMINDER TO THE DEV TO ASK IF HE WANTS TO DELETE THE ARTIFACTS NOW");
            System.out.println("\n######\n");
        }

        if (execution.getCurrentActivityId().equals("CloseExtraArtifactsNotification")) {
            service.startProcessInstanceByMessage("notifyAdminToDeleteArtifacts", execution.getVariables());
            System.out.println("\n######\n");
            System.out.println("NOW WE WOULD SEND A NOTIFICATION TO SYSADMIN TO CLOSE THE ADDITIONAL ARTIFACTS");
            System.out.println("\n######\n");
        }
    }
}
