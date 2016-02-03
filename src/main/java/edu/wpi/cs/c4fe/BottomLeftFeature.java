package edu.wpi.cs.c4fe;

import java.util.HashMap;
import java.util.Map;

public class BottomLeftFeature extends BoardFeature {

    @Override
    public Map<String, Number> apply(GameState gameState) {
        Map<String, Number> featureCounts = new HashMap<>();
        featureCounts.put("bottomLeft", gameState.get(0, gameState.getHeight() - 1).ordinal());
        return featureCounts;
    }
}
