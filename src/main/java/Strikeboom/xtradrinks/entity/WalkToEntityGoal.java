package Strikeboom.xtradrinks.entity;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class WalkToEntityGoal<T extends LivingEntity> extends Goal {
    PathfinderMob entity;
    Class<T> entityToWalk;
    T entity1;
    TargetingConditions entityTargeting;
    Path path;
    public WalkToEntityGoal(PathfinderMob entity, Class<T> entityToWalk) {
        this.entity = entity;
        this.entityToWalk = entityToWalk;
        entityTargeting = TargetingConditions.forNonCombat().range(12D).selector(EntitySelector.NO_SPECTATORS::test);
    }
    @Override
    public boolean canUse() {
        List<T> entitiesOfClass = this.entity.level.getEntitiesOfClass(this.entityToWalk, this.entity.getBoundingBox().inflate(12D, 3.0D, 12D), (ent) -> true);
        this.entity1 = this.entity.level.getNearestEntity(entitiesOfClass, entityTargeting, this.entity, this.entity.getX(), this.entity.getY(), this.entity.getZ());
        if (entity1 != null) {
            Vec3 vec3 = DefaultRandomPos.getPosAway(this.entity, 16, 7, this.entity1.position());
            if (vec3 != null) {
                path = entity.getNavigation().createPath(vec3.x, vec3.y, vec3.z, 0);
                return path != null;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void stop() {
        entity1 = null;
    }

    @Override
    public boolean canContinueToUse() {
        return !entity.getNavigation().isDone();
    }

    @Override
    public void start() {
        entity.getNavigation().moveTo(path,entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
    }

}
