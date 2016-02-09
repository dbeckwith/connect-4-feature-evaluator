package edu.wpi.cs.c4fe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LineBoardFeatureCounter extends BoardFeature {

    /**
     * Returns all the {@link BoardFeature}s of the specified {@link GameState}.
     *
     * @param state The game state to evaluate for features.
     * @return a Map&lt;Integer, Set&lt;LineBoardFeature&gt;&gt; of feature length mapped to a collection of all the features of that lnegth.
     */
    public Map<Integer, Set<LineBoardFeature>> getFeatures(GameState state, Player player) {
        Map<Integer, Set<LineBoardFeature>> features = new HashMap<>();
        int n = state.getConnectLength();

        for (int dir = 0; dir < LineBoardFeature.VALID_DIRS.length; dir += 2) {
            int dx = LineBoardFeature.VALID_DIRS[dir];
            int dy = LineBoardFeature.VALID_DIRS[dir + 1];

            for (int x = 0; x < state.getWidth() - (dx == 0 ? 0 : dx * n - 1); x++) {
                yLoop:
                for (int y = (dy < 0 ? n - 1 : 0); y < state.getHeight() - (dy <= 0 ? 0 : dy * n - 1); y++) {
                    BoardPos[] positions = new BoardPos[n];
                    for (int i = 0; i < n; i++) {
                        int xx = x + dx * i;
                        int yy = y + dy * i;
                        BoardCell cell = state.get(xx, yy);
                        if (cell == player.getOpponent().getAsBoardCell()) {
                            continue yLoop;
                        }
                        positions[i] = new BoardPos(xx, yy, cell);
                    }
                    LineBoardFeature feature = new LineBoardFeature(positions);
                    if (feature.getLength() > 0) {
                        features.computeIfAbsent(feature.getLength(), integer -> new HashSet<>())
                                .add(feature);
                    }
                }
            }
        }

        return features;
    }

    @Override
    public Map<String, Number> apply(GameState gameState) {
        Map<String, Number> featureCounts = new HashMap<>();
        for (Player player : Player.values()) {
            Map<Integer, Set<LineBoardFeature>> features = getFeatures(gameState, player);
            features.forEach((length, lengthFeatures) -> {
                String featureName = String.format("line_%d_%d", player.ordinal() + 1, length);
                featureCounts.put(featureName, lengthFeatures.size());
            });
        }
        return featureCounts;
    }
}
