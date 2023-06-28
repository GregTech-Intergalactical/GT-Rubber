package io.github.gregtechintergalactical.gtrubber.entity;

import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;

public class RubberBoatEntity extends Boat {
    public RubberBoatEntity(EntityType<RubberBoatEntity> entityType, Level level) {
        super(entityType, level);
    }

    public RubberBoatEntity(Level level) {
        this(GTRubberData.RUBBER_BOAT_ENTITY, level);
    }

    public RubberBoatEntity(Level level, double x, double y, double z) {
        this(GTRubberData.RUBBER_BOAT_ENTITY, level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }
}
