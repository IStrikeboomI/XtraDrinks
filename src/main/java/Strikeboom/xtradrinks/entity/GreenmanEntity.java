package Strikeboom.xtradrinks.entity;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blockentities.itemhandlers.OutputOnlyItemHandler;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.init.XtraDrinksEntities;
import Strikeboom.xtradrinks.init.XtraDrinksItems;
import Strikeboom.xtradrinks.init.XtraDrinksTags;
import Strikeboom.xtradrinks.menus.GreenmanMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GreenmanEntity extends PathfinderMob implements MenuProvider  {
    public final ItemStackHandler HANDLER;
    public GreenmanEntity(EntityType<? extends PathfinderMob> p_21683_,Level p_21684_) {
        super(p_21683_, p_21684_);
        HANDLER = new OutputOnlyItemHandler(9) {
            @Override
            protected void onContentsChanged(int slot) {

            }
        };
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0,new WalkToEntityGoal<>(this,Player.class));
        goalSelector.addGoal(1,new WaterAvoidingRandomStrollGoal(this,.8f));
        goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        super.registerGoals();
    }

    @Override
    protected boolean canRide(Entity pEntity) {
        return false;
    }

    @Override
    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("entity."+ XtraDrinks.MOD_ID +".greenman");
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return false;
    }

    @Override
    public boolean save(CompoundTag pCompound) {
        pCompound.put("ItemStackHandler", HANDLER.serializeNBT(level().registryAccess()));
        return super.save(pCompound);
    }

    @Override
    public void load(CompoundTag pCompound) {
        super.load(pCompound);
        if (pCompound.contains("ItemStackHandler")) {
            HANDLER.deserializeNBT(level().registryAccess(), pCompound);
        }
    }

    @Override
    public boolean mayInteract(ServerLevel pLevel, BlockPos pPos) {
        return pPos.distSqr(new Vec3i(getBlockX(),getBlockY(),getBlockZ())) <= 64;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new GreenmanMenu(containerId,playerInventory,this);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(this,registryFriendlyByteBuf -> registryFriendlyByteBuf.writeUUID(uuid));
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        return XtraDrinksItems.GREENMAN_EGG.get().getDefaultInstance();
    }

    public void randomizeHandler() {
        if (!level().isClientSide()) {
            if (XtraDrinksConfig.GREENMAN_ITEMS_ENABLED.get()) {
                IItemHandler iItemHandler = getCapability(Capabilities.ItemHandler.ENTITY,null);
                List<Item> fruits = new ArrayList<>();
                BuiltInRegistries.ITEM.getTagOrEmpty(Tags.Items.FOODS_FRUIT).forEach(itemHolder -> fruits.add(itemHolder.value()));
                ItemStackHandler handler = (ItemStackHandler) iItemHandler;
                for (int i = 0; i < handler.getSlots(); i++) {
                    Item fruit = fruits.get(random.nextInt(fruits.size()));
                    if (random.nextBoolean()) {
                        handler.setStackInSlot(i, new ItemStack(fruit, random.nextInt(XtraDrinksConfig.GREENMAN_ITEMS_MAX .get()- 3) + 3));
                    }
                }
            }
        }
    }

    @Override
    public void die(DamageSource pCause) {
        if (!level().isClientSide()) {
            for (int i = 0;i<HANDLER.getSlots();i++) {
                Containers.dropItemStack(level(),getX(),getY(),getZ(),HANDLER.getStackInSlot(i));
            }
        }
        super.die(pCause);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }



    @EventBusSubscriber(modid = XtraDrinks.MOD_ID,bus = EventBusSubscriber.Bus.MOD)
    public static class GreenmanEvents {
        @SubscribeEvent
        public static void onAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(XtraDrinksEntities.GREENMAN.get(), LivingEntity.createLivingAttributes()
                            .add(Attributes.MAX_HEALTH,20.0)
                            .add(Attributes.MOVEMENT_SPEED,.3f)
                            .add(Attributes.FOLLOW_RANGE,40f)
                    .build());
        }
        @SubscribeEvent
        public static void spawnData(RegisterSpawnPlacementsEvent event) {
            event.register(XtraDrinksEntities.GREENMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GreenmanEvents::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        }

        /**
         * Taken From {@link Animal#checkAnimalSpawnRules}
         */
        private static boolean checkAnimalSpawnRules(
                EntityType<?> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random
        ) {
            boolean flag = EntitySpawnReason.ignoresLightRequirements(spawnReason) || level.getRawBrightness(pos, 0) > 8;
            return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && flag;
        }

    }
    @EventBusSubscriber(modid = XtraDrinks.MOD_ID,bus = EventBusSubscriber.Bus.GAME)
    public static class GreenmanSpawns {
        @SubscribeEvent
        public static void onSpawn(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof GreenmanEntity entity) {
                    entity.randomizeHandler();
                }
            }
        }
    }
}
