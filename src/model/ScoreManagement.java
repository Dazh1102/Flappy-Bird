package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreManagement {
    
    private final HashMap<String, Double> hashMap = new HashMap<>();
    
    public ScoreManagement() {
    }

    public void addScore(String name, double score) {
        hashMap.put(name, score);
    }
    
    public void removeScore(String name) {
        hashMap.remove(name);
    }

    public Map<String, Double> getSortedScores() {
        // Using Stream API and LinkedHashMap to maintain insertion order
        return hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public HashMap<String, Double> getHashMap() {
        return hashMap;
    }
    
    public void printSortedScores() {
        getSortedScores().forEach((name, score) -> 
            System.out.println("Player: " + name + ", Score: " + score)
        );
    }
    
    public Map.Entry<String, Double> getEntryByIndex(int index) {
        // Get the sorted scores
        Map<String, Double> sortedScores = getSortedScores();
        // Check if index is within bounds
        if (index < 0 || index >= sortedScores.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        // Convert sortedScores to an array and return the entry at the specified index
        return sortedScores.entrySet().toArray(new Map.Entry[0])[index];
    }
    
    
	// lấy tên trong hashMap
	public String getKeyByIndex(int index) {
		return getEntryByIndex(index).getKey();
	}

	// lấy điểm trong hashMap
	public Double getValueByIndex(int index) {
		return getEntryByIndex(index).getValue();
	}

	
}
