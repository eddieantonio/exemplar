package ca.ualberta.exemplar.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RelationInstance {
	String normalizedRelation;
	String originalRelation;
	String sentence;
	
	private int triggerIndex;
	
	List<Argument> arguments;
	Map<String, List<Argument>> argumentMap;
	Set<Integer> argumentIndices;
	
	public RelationInstance(){
		arguments = new ArrayList<Argument>(2);
		argumentMap = new HashMap<String,List<Argument>>();
		argumentIndices = new HashSet<Integer>();
	}

	public String getNormalizedRelation() {
		return normalizedRelation;
	}

	public void setNormalizedRelation(String normalizedRelation) {
		this.normalizedRelation = normalizedRelation;
	}


	public String getOriginalRelation() {
		return originalRelation;
	}

	public void setOriginalRelation(String originalRelation) {
		this.originalRelation = originalRelation;
	}

	public void addArgument(Argument arg){
		//Check for duplicates -- and ignore them.
		//This is a workaround for entities that get multiple dependencies with a verb at the parser's fault.
		if(argumentIndices.contains(arg.getStartIndex())){
			return;
		}
		arguments.add(arg);
		argumentIndices.add(arg.getStartIndex());
		if(!argumentMap.containsKey(arg.argumentType)){
			argumentMap.put(arg.argumentType, new ArrayList<Argument>());
		}
		argumentMap.get(arg.argumentType).add(arg);
	}

	public void removeArgument(Argument arg){
		argumentMap.values().remove(arg);
		arguments.remove(arg);
	}

	
	public List<Argument> getArgumentsByType(String type){
		return argumentMap.get(type);
	}
	
	public List<Argument> getArguments(){
		return arguments;
	}
	
	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public int getTriggerIndex() {
		return triggerIndex;
	}

	public void setTriggerIndex(int triggerIndex) {
		this.triggerIndex = triggerIndex;
	}

	@Override
	public String toString(){
		return originalRelation + " " + argumentMap;
	}
	
	public String detailedString(){
		StringBuilder sb = new StringBuilder();
		sb.append(normalizedRelation);
		sb.append(":");
		sb.append(triggerIndex);
		sb.append(" {");
		for(Argument arg : arguments){
			sb.append(arg.entityName);
			sb.append(":");
			sb.append(arg.getStartOffset());
			sb.append(",");
			sb.append(arg.getEndOffset());
			sb.append(" ");
		}
		sb.append("}");
		
		return sb.toString();
	}
	
}