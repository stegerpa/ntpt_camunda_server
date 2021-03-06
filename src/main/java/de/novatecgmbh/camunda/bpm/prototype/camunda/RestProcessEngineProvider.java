package de.novatecgmbh.camunda.bpm.prototype.camunda;

import java.util.Set;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.rest.spi.ProcessEngineProvider;

/**
 * Provides the Rest Engine for Camunda
 * @author Patrick Steger
 */
public class RestProcessEngineProvider implements ProcessEngineProvider {
	public ProcessEngine getDefaultProcessEngine() {
	    return ProcessEngines.getDefaultProcessEngine();
	  }
	public ProcessEngine getProcessEngine(String name) {
	    return ProcessEngines.getProcessEngine(name);
	  }
	public Set<String> getProcessEngineNames() {
	    return ProcessEngines.getProcessEngines().keySet();
	  }
}