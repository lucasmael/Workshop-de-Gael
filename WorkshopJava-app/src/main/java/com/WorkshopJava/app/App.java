package com.WorkshopJava.app;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class App extends GameApplication {

    Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("My Game");
    }

    public static class Factory implements EntityFactory {

        @Spawns("enemy")
        public Entity newEnemy(SpawnData data) {
            return FXGL.entityBuilder(data)
                    .view(new Rectangle(25, 25, Color.RED))
                    .build();
        }
    }

    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .at(100, 100)
                .view(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach();
        FXGL.getGameWorld().addEntityFactory(new Factory());
        FXGL.spawn("enemy", 500, 400);
        FXGL.spawn("enemy", 500, 300);
    }
    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.UP, () -> {
            player.translateY(-5);
        });

        FXGL.onKeyDown(KeyCode.DOWN, () -> {
            player.translateY(5);
        });

        FXGL.onKeyDown(KeyCode.LEFT, () -> {
            player.translateX(-5);
        });

        FXGL.onKeyDown(KeyCode.RIGHT, () -> {
            player.translateX(5);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
