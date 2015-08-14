package at.ac.tuwien.big.moea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.moeaframework.core.NondominatedPopulation;

import at.ac.tuwien.big.moea.experiment.analyzer.SearchAnalyzer;
import at.ac.tuwien.big.moea.experiment.executor.SearchExecutor;

public class SearchAnalysis extends IndicatorConfiguration {
	public static final double SIGNIFICANCE_ONE_PERCENT = 0.01;
	public static final double SIGNIFICANCE_ONE_PERCENT_FIVE_PERCENT = 0.05;
	
	protected SearchExperiment experiment;
	
	protected double significanceLevel = SIGNIFICANCE_ONE_PERCENT_FIVE_PERCENT;
	
	protected boolean showStatisticalSignificance;
	protected boolean showAggregate;
	protected boolean showIndividualValues;
	
	protected boolean maximumParetoFrontError;
	
	protected List<UnivariateStatistic> statistics = new ArrayList<>();
	
	public SearchAnalysis() { }
	
	public SearchAnalysis(SearchExperiment experiment) {
		setExperiment(experiment);
	}
	
	public void setExperiment(SearchExperiment experiment) {
		this.experiment = experiment;
	}
	
	public SearchExperiment getExperiment() {
		return experiment;
	}
	
	public void setSignificanceLevel(double significanceLevel) {
		this.significanceLevel = significanceLevel;
	}
	
	public double getSignificanceLevel() {
		return significanceLevel;
	}
	
	public void setShowStatisticalSignificance(
			boolean showStatisticalSignificance) {
		this.showStatisticalSignificance = showStatisticalSignificance;
	}
	
	public boolean isShowStatisticalSignificance() {
		return showStatisticalSignificance;
	}
	
	public void setShowAggregate(boolean showAggregate) {
		this.showAggregate = showAggregate;
	}
	
	public boolean isShowAggregate() {
		return showAggregate;
	}
	
	public void setShowIndividualValues(boolean showIndividualValues) {
		this.showIndividualValues = showIndividualValues;
	}
	
	public boolean isShowIndividualValues() {
		return showIndividualValues;
	}
	
	public void showAll(boolean showAll) {
		setShowAggregate(showAll);
		setShowIndividualValues(showAll);
		setShowStatisticalSignificance(showAll);
	}
	
	public void setMaximumParetoFrontError(boolean maximumParetoFrontError) {
		this.maximumParetoFrontError = maximumParetoFrontError;
	}
	
	public boolean isMaximumParetoFrontError() {
		return maximumParetoFrontError;
	}
	
	@Override
	public void setAllIndicators(boolean allIndicators) {
		super.setAllIndicators(allIndicators);
		setMaximumParetoFrontError(allIndicators);
	}
	
	public void setStatistics(List<UnivariateStatistic> statistics) {
		this.statistics = statistics;
	}
	
	public void addStatistic(UnivariateStatistic statistic) {
		getStatistics().add(statistic);
	}
	
	public List<UnivariateStatistic> getStatistics() {
		return statistics;
	}
	
	protected SearchAnalyzer createAnalyzer() {
		SearchAnalyzer analyzer = new SearchAnalyzer(getExperiment().getSearchOrchestration().createProblem());
		analyzer.withEpsilon(getExperiment().getEpsilon());
		analyzer.withReferenceSet(getExperiment().getReferenceSetFile());
		analyzer.withSignifianceLevel(getSignificanceLevel());
		
		if(isAdditiveEpsilonIndicator()) 
			analyzer.includeAdditiveEpsilonIndicator();
				
		if(isContribution()) 
			analyzer.includeContribution();
			
		if(isGenerationalDistance()) 
			analyzer.includeGenerationalDistance();
		
		if(isHypervolume()) 
			analyzer.includeHypervolume();
		
		if(isInvertedGenerationalDistance()) 
			analyzer.includeInvertedGenerationalDistance();
		
		if(isR1()) 
			analyzer.includeR1();
		
		if(isR2()) 
			analyzer.includeR2();
		
		if(isR3()) 
			analyzer.includeR3();
		
		if(isSpacing()) 
			analyzer.includeSpacing();
		
		if(isMaximumParetoFrontError())
			analyzer.includeMaximumParetoFrontError();
		
		if(isShowAggregate())
			analyzer.showAggregate();
		
		if(isShowIndividualValues())
			analyzer.showIndividualValues();
		
		if(isShowStatisticalSignificance())
			analyzer.showStatisticalSignificance();
		
		for(UnivariateStatistic statistic : getStatistics())
			analyzer.showStatistic(statistic);
		
		return analyzer;
	}
	
	public SearchAnalyzer analyze() {
		if(!getExperiment().hasResults())
			getExperiment().run();
		
		SearchAnalyzer analyzer = createAnalyzer();
		Map<SearchExecutor, List<NondominatedPopulation>> results = getExperiment().getResults();
		
		for(Entry<SearchExecutor, List<NondominatedPopulation>> result : results.entrySet()) 
			analyzer.addAll(result.getKey().getName(), result.getValue());
		
		try {
			analyzer.printAnalysis();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return analyzer;
	}
	
}
