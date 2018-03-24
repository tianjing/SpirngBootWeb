package tgtools.spirngbootweb.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import tgtools.plugin.util.JARLoader;

@Component
public class WelcomeJob implements Job {
    JARLoader jar = new JARLoader(ClassLoader.getSystemClassLoader());

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

    }

}