package edu.wpi.cs.c4fe;

import java.util.HashMap;
import java.util.Map;

public class CenterMajorityFeature extends BoardFeature {

    @Override
    public Map<String, Number> apply(GameState gameState) {
        Map<String, Number> featureCounts = new HashMap<>();

        int maxCount = 0;
        int minCount = 0;
        for (int x = 1; x < gameState.getWidth() - 1; x++) {
            for (int y = 0; y < gameState.getHeight(); y++) {
                switch (gameState.get(x, y)) {
                    case MAX:
                        maxCount++;
                        break;
                    case MIN:
                        minCount++;
                        break;
                }
            }
        }
        featureCounts.put("centerMajority", maxCount == minCount ? 0 : maxCount > minCount ? 1 : 2);

        return featureCounts;
    }
}
