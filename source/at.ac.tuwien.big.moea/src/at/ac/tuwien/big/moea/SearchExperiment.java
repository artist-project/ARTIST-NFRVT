package at.ac.tuwien.big.moea;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.analysis.collector.Collector;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.util.progress.ProgressListener;

import at.ac.tuwien.big.moea.experiment.executor.SearchExecutor;
import at.ac.tuwien.big.moea.experiment.instrumenter.SearchInstrumenter;
import at.ac.tuwien.big.moea.problem.IMOEAProblem;
import at.ac.tuwien.big.moea.search.algorithm.provider.IRegisteredAlgorithm;
import at.ac.tuwien.big.moea.util.CastUtil;

public class SearchExperiment extends IndicatorConfiguration {
	
	protected ISearchOrchestration<?> searchOrchestration;
	
	// instrumentation
	protected File referenceSetFile = null;
	protected int frequency = 100;

	protected List<Collector> customCollectors = new ArrayList<>();
	protected boolean adaptiveMultimethodVariation;
	protected boolean adaptiveTimeContinuation;
	protected boolean approximationSet;	
	protected boolean epsilonProgress;	
	protected boolean elapsedTime;
	protected boolean populationSize;
	
	// execution
	protected int maxEvaluations; 
	protected double[] epsilon;
	protected List<ProgressListener> progressListeners = new ArrayList<>();
	
	// run
	protected int numberOfRuns = 1;
	
	// result
	protected Map<SearchExecutor, List<NondominatedPopulation>> results = new HashMap<>();
	
	public SearchExperiment() { }
	
	public SearchExperiment(ISearchOrchestration<?> searchOrchestration) {
		setSearchOrchestration(searchOrchestration);
	}
	
	public SearchExperiment(ISearchOrchestration<?> searchOrchestration, int maxEvaluations) {
		setSearchOrchestration(searchOrchestration);
		setMaxEvaluations(maxEvaluations);
	}
	
	public void setSearchOrchestration(
			ISearchOrchestration<?> searchOrchestration) {
		this.searchOrchestration = searchOrchestration;
	}
	
	public ISearchOrchestration<?> getSearchOrchestration() {
		return searchOrchestration;
	}
	
	protected IMOEAProblem<?> createProblem() {
		return getSearchOrchestration().createProblem();
	}
	
	public File getReferenceSetFile() {
		return referenceSetFile;
	}
	
	public void setReferenceSetFile(File referenceSetFile) {
		this.referenceSetFile = referenceSetFile;
	}
	
	public void setReferenceSetFile(String referenceSetFileUri) {
		this.referenceSetFile = CastUtil.asFile(referenceSetFileUri);
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public int getNumberOfRuns() {
		return numberOfRuns;
	}
	
	public void setNumberOfRuns(int numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}
	
	public void setMaxEvaluations(int maxEvaluations) {
		this.maxEvaluations = maxEvaluations;
	}
	
	public int getMaxEvaluations() {
		return maxEvaluations;
	}
	
	public void setEpsilon(double... epsilon) {
		this.epsilon = epsilon;
	}
	
	public double[] getEpsilon() {
		return epsilon;
	}
	
	public List<Collector> getCustomCollectors() {
		return customCollectors;
	}
	
	public void setCustomCollectors(List<Collector> customCollectors) {
		this.customCollectors = customCollectors;
	}
	
	public void addCustomCollector(Collector collector) {
		getCustomCollectors().add(collector);
	}
	
	public void setAdaptiveMultimethodVariation(
			boolean adaptiveMultimethodVariation) {
		this.adaptiveMultimethodVariation = adaptiveMultimethodVariation;
	}
	
	public boolean isAdaptiveMultimethodVariation() {
		return adaptiveMultimethodVariation;
	}
	
	public void setAdaptiveTimeContinuation(boolean adaptiveTimeContinuation) {
		this.adaptiveTimeContinuation = adaptiveTimeContinuation;
	}
	
	public boolean isAdaptiveTimeContinuation() {
		return adaptiveTimeContinuation;
	}
	
	public void setApproximationSet(boolean approximationSet) {
		this.approximationSet = approximationSet;
	}
	
	public boolean isApproximationSet() {
		return approximationSet;
	}
	
	public void setEpsilonProgress(boolean epsilonProgress) {
		this.epsilonProgress = epsilonProgress;
	}
	
	public boolean isEpsilonProgress() {
		return epsilonProgress;
	}
	
	public void setElapsedTime(boolean elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public boolean isElapsedTime() {
		return elapsedTime;
	}
	
	public void setPopulationSize(boolean populationSize) {
		this.populationSize = populationSize;
	}
	
	public boolean isPopulationSize() {
		return populationSize;
	}	
	
	public void setAllCollectors(boolean all) {
		setAllIndicators(all);
		setAdaptiveMultimethodVariation(all);
		setAdaptiveTimeContinuation(all);
		setApproximationSet(all);
		setEpsilonProgress(all);
		setElapsedTime(all);
		setPopulationSize(all);
	}
	
	public List<ProgressListener> getProgressListeners() {
		return progressListeners;
	}
	
	public void setProgressListeners(List<ProgressListener> progressListeners) {
		this.progressListeners = progressListeners;
	}
	
	public void addProgressListener(ProgressListener progressListener) {
		this.progressListeners.add(progressListener);
	}	
	
	protected Instrumenter createInstrumenter() {		
		SearchInstrumenter instrumenter = new SearchInstrumenter(createProblem());

		File referenceFile = getReferenceSetFile();		
		if(referenceFile != null) 
			instrumenter.withReferenceSet(referenceFile);
		
		instrumenter.withFrequency(getFrequency());
		instrumenter.withEpsilon(getEpsilon());
		
		if(isAdaptiveMultimethodVariation()) 
			instrumenter.attachAdaptiveMultimethodVariationCollector();
		
		if(isAdaptiveTimeContinuation()) 
			instrumenter.attachAdaptiveTimeContinuationCollector();
		
		if(isAdditiveEpsilonIndicator()) 
			instrumenter.attachAdditiveEpsilonIndicatorCollector();
		
		if(isApproximationSet()) 
			instrumenter.attachApproximationSetCollector();
		
		if(isContribution()) 
			instrumenter.attachContributionCollector();
		
		if(isElapsedTime()) 
			instrumenter.attachElapsedTimeCollector();
		
		if(isEpsilonProgress()) 
			instrumenter.attachEpsilonProgressCollector();
		
		if(isGenerationalDistance()) 
			instrumenter.attachGenerationalDistanceCollector();
		
		if(isHypervolume()) 
			instrumenter.attachHypervolumeCollector();
		
		if(isInvertedGenerationalDistance()) 
			instrumenter.attachInvertedGenerationalDistanceCollector();
		
		if(isPopulationSize())
			instrumenter.attachPopulationSizeCollector();
		
		if(isR1()) 
			instrumenter.attachR1Collector();
		
		if(isR2()) 
			instrumenter.attachR2Collector();
		
		if(isR3()) 
			instrumenter.attachR3Collector();
		
		if(isSpacing()) 
			instrumenter.attachSpacingCollector();
		
		for(Collector collector : customCollectors)
			instrumenter.attach(collector);
		
		return instrumenter;
	}
	
	protected <T extends Executor> T attachProgressListeners(T executor) {
		for(ProgressListener listener : getProgressListeners())
			executor.withProgressListener(listener);
		return executor;
	}
	
	protected String getAlgorithmName(IRegisteredAlgorithm<? extends Algorithm> algorithm) {
		return getSearchOrchestration().getAlgorithmName(algorithm);
	}
	
	protected List<SearchExecutor> createExecutors() {
		List<SearchExecutor> executors = new ArrayList<>();
		for(IRegisteredAlgorithm<? extends Algorithm> algorithm : getSearchOrchestration().getAlgorithms()) {
			SearchExecutor executor = new SearchExecutor(createProblem())
					.setName(getAlgorithmName(algorithm))
					.withMaxEvaluations(getMaxEvaluations())
					.withInstrumenter(createInstrumenter())
					.withAlgorithm(algorithm.getRegisteredName())
					.withEpsilon(getEpsilon())
					.distributeOnAllCores();
			attachProgressListeners(executor);
			executors.add(executor);
		}
		return executors;
	}
	
	public Map<SearchExecutor, List<NondominatedPopulation>> getResults() {
		return results;
	}
	
	public boolean hasResults() {
		return !results.isEmpty();
	}
	
	public Map<SearchExecutor, List<NondominatedPopulation>> run(int nrRuns) {
		if(getMaxEvaluations() == 0)
			System.err.println("Warning: Missing maximum number of evaluations in experiment. No search will be executed.");
		
		List<SearchExecutor> executors = createExecutors();

		for(SearchExecutor executor : executors) {
			System.out.println("Run '" + executor.getName() + "' " + nrRuns + " times...");
			results.put(executor, executor.runSeeds(nrRuns));
		}
		return results;
	}
	
	public Map<SearchExecutor, List<NondominatedPopulation>> run() {
		return run(getNumberOfRuns());
	}
}
