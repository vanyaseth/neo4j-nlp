/*
 * Copyright (c) 2013-2017 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.graphaware.nlp.dsl.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.graphaware.nlp.dsl.request.RequestConstants.*;

public class PipelineSpecification {

    private static final long DEFAULT_THREAD_NUMBER = 4;

    private String name;

    private String textProcessor;

    private Map<String, Boolean> processingSteps = new HashMap<>();

    private String stopWords;

    private long threadNumber;

    private List<String> excludedNER = new ArrayList<>();

    private List<String> excludedPOS = new ArrayList<>();

    public PipelineSpecification() {

    }
    
    public static PipelineSpecification fromMap(Map<String, Object> map) {
        PipelineSpecification pipelineSpecification = new PipelineSpecification(map.get("name").toString(), map.containsKey("textProcessor") ? map.get("textProcessor").toString() : null);
        pipelineSpecification.setThreadNumber(map.containsKey("threadNumber") ? ((Number) map.get("threadNumber")).longValue() : DEFAULT_THREAD_NUMBER);
        if (map.containsKey("processingSteps")) {
            pipelineSpecification.setProcessingSteps((Map) map.get("processingSteps"));
        }
        if (map.containsKey(EXCLUDED_NER)) {
            pipelineSpecification.setExcludedNER((List<String>) map.get(EXCLUDED_NER));
        }

        return pipelineSpecification;
    }

    public PipelineSpecification(String name, String textProcessor) {
        this.name = name;
        this.textProcessor = textProcessor;
    }

    public String getName() {
        return name;
    }

    public String getTextProcessor() {
        return textProcessor;
    }

    public boolean hasProcessingStep(String stepName) {
        return processingSteps.containsKey(stepName) && processingSteps.get(stepName);
    }

    public boolean hasProcessingStep(String stepName, boolean defaultValue) {
        if (processingSteps.containsKey(stepName)) {
            return processingSteps.get(stepName);
        }

        return defaultValue;
    }

    public void addProcessingStep(String step) {
        processingSteps.put(step, true);
    }

    public String getStopWords() {
        return stopWords;
    }

    public void setStopWords(String stopWords) {
        this.stopWords = stopWords;
    }

    public long getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(long threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void setProcessingSteps(Map<String, Boolean> processingSteps) {
        this.processingSteps = processingSteps;
    }

    public Map<String, Boolean> getProcessingSteps() {
        return processingSteps;
    }

    public List<String> getExcludedNER() {
        return excludedNER;
    }

    public void setExcludedNER(List<String> excludedNER) {
        this.excludedNER = excludedNER;
    }

    public List<String> getExcludedPOS() {
        return excludedPOS;
    }

    public void setExcludedPOS(List<String> excludedPOS) {
        this.excludedPOS = excludedPOS;
    }
}
