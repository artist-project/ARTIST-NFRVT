/*******************************************************************************
 * Copyright (c) 2015 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Fleck (Vienna University of Technology) - initial API and implementation
 *
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 *******************************************************************************/
package eu.artist.postmigration.nfrvt.search.run.internal;

public class AnalysisSettings {
	private int simulationTime;
	private String resultPath;
	
	public AnalysisSettings() { }
	
	public AnalysisSettings(int simulationTime, String resultPath) {
		this.simulationTime = simulationTime;
		this.resultPath = resultPath;
	}

	public int getSimulationTime() {
		return simulationTime;
	}
	
	public void setSimulationTime(int simulationTime) {
		this.simulationTime = simulationTime;
	}
	
	public String getResultPath() {
		return resultPath;
	}
	
	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
	
}
