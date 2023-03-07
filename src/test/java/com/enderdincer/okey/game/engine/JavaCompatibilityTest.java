package com.enderdincer.okey.game.engine;

import com.enderdincer.okey.game.engine.evaluator.Evaluators;
import com.enderdincer.okey.game.engine.evaluator.RackEvaluator;
import com.enderdincer.okey.game.engine.event.handler.GameEventHandler;
import com.enderdincer.okey.game.engine.event.handler.GameEventHandlers;
import com.enderdincer.okey.game.engine.model.GameConfig;
import com.enderdincer.okey.game.engine.model.GameConfigs;
import com.enderdincer.okey.game.engine.model.TileColor;
import org.junit.jupiter.api.Test;

public class JavaCompatibilityTest {

    @Test
    public void testFieldAndMethodAccess() {
        final String shortName = TileColor.RED.getShortName();
        final int  colorId = TileColor.GREEN.getColorId();

        final String name = GameEngines.getDefaultGameEngine().getName();
        System.out.println(name);

        final GameEventHandler gameEventHandler = GameEventHandlers.getDefaultGameEventHandler();
        System.out.println(gameEventHandler);

        final GameConfig gameConfig = GameConfigs.getDefaultGameConfig();

        final RackEvaluator rackEvaluator = Evaluators.getDefaultRackEvaluator();
    }
}
