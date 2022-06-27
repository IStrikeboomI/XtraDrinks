package Strikeboom.XtraDrinks.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class WalkToEntityGoal<T extends LivingEntity> extends Goal {
    CreatureEntity entity;
    Class<T> entityToWalk;
    T entity1;
    EntityPredicate entityTargeting;
    Path path;
    public WalkToEntityGoal(CreatureEntity entity, Class<T> entityToWalk) {
        this.entity = entity;
        this.entityToWalk = entityToWalk;
        entityTargeting = new EntityPredicate().allowNonAttackable().range(12D).selector(livingEntity -> !livingEntity.isSpectator());
    }
    @Override
    public boolean canUse() {
        List<T> entitiesOfClass = this.entity.level.getEntitiesOfClass(this.entityToWalk, this.entity.getBoundingBox().inflate(12D, 3.0D, 12D), (ent) -> true);
        this.entity1 = this.entity.level.getNearestEntity(entitiesOfClass, entityTargeting, this.entity, this.entity.getX(), this.entity.getY(), this.entity.getZ());
        if (entity1 != null) {
            Vector3d vec3 = RandomPositionGenerator.getPosTowards(this.entity, 16, 7, this.entity1.position());
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
