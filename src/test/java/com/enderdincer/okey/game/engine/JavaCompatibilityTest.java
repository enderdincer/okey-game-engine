package com.enderdincer.okey.game.engine;

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator;
import com.enderdincer.okey.game.engine.evaluator.Evaluators;
import com.enderdincer.okey.game.engine.evaluator.RackEvaluator;
import com.enderdincer.okey.game.engine.evaluator.group.DefaultTileGroupEvaluator;
import com.enderdincer.okey.game.engine.evaluator.run.DefaultTileRunEvaluator;
import com.enderdincer.okey.game.engine.event.handler.GameEventHandler;
import com.enderdincer.okey.game.engine.event.handler.GameEventHandlers;
import com.enderdincer.okey.game.engine.model.GameConfig;
import com.enderdincer.okey.game.engine.model.GameConfigs;
import org.junit.jupiter.api.Test;

public class JavaCompatibilityTest {

    @Test
    public void compatabilityTest() {
        final String name = GameEngines.getDefaultGameEngine(true).getName();
        System.out.println(name);

        final GameEventHandler gameEventHandler = GameEventHandlers.defaultGameEventHandler(true);
        System.out.println(gameEventHandler);

        final GameConfig gameConfig = GameConfigs.getDefaultGameConfig();

        final RackEvaluator rackEvaluator = Evaluators.getDefaultRackEvaluator(true);
    }
}
