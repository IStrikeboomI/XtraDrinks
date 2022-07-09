package Strikeboom.xtradrinks.entity;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.blockentities.itemhandlers.OutputOnlyItemHandler;
import Strikeboom.xtradrinks.guis.menus.GreenmanMenu;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.init.XtraDrinksEntities;
import Strikeboom.xtradrinks.init.XtraDrinksTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GreenmanEntity extends PathfinderMob {
    private final ItemStackHandler HANDLER;
    private final LazyOptional<IItemHandler> HANDLER_LAZY_OPTIONAL;
    public GreenmanEntity(EntityType<? extends PathfinderMob> p_21683_,Level p_21684_) {
        super(p_21683_, p_21684_);
        HANDLER = new OutputOnlyItemHandler(9) {
            @Override
            protected void onContentsChanged(int slot) {

            }
        };
        HANDLER_LAZY_OPTIONAL = LazyOptional.of(() -> HANDLER);
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

    @Override
    public float getEyeHeight(Pose pPose) {
        return 1.62f;
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
    public boolean save(CompoundTag pCompound) {
        pCompound.put("ItemStackHandler", HANDLER.serializeNBT());
        return super.save(pCompound);
    }

    @Override
    public void load(CompoundTag pCompound) {
        super.load(pCompound);
        if (pCompound.contains("ItemStackHandler")) {
            HANDLER.deserializeNBT(pCompound);
        }
    }

    @Override
    public boolean mayInteract(Level pLevel, BlockPos pPos) {
        return pPos.distSqr(new Vec3i(getX(),getY(),getZ())) <= 64;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return HANDLER_LAZY_OPTIONAL.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        if (!level.isClientSide()) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.translatable("entity." + XtraDrinks.MOD_ID + ".greenman");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return new GreenmanMenu(windowId, GreenmanEntity.this, playerInventory);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) pPlayer, containerProvider, getOnPos());
        }
        return InteractionResult.SUCCESS;
    }


    public void randomizeHandler() {
        if (!level.isClientSide()) {
            if (XtraDrinksConfig.GREENMAN_ITEMS_ENABLED.get()) {
                getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
                    List<Item> fruits = new ArrayList<>();
                    Registry.ITEM.getTagOrEmpty(XtraDrinksTags.FRUIT).forEach(itemHolder -> fruits.add(itemHolder.value()));
                    ItemStackHandler handler = (ItemStackHandler) iItemHandler;
                    for (int i = 0; i < handler.getSlots(); i++) {
                        Item fruit = fruits.get(random.nextInt(fruits.size()));
                        if (random.nextBoolean()) {
                            handler.setStackInSlot(i, new ItemStack(fruit, random.nextInt(XtraDrinksConfig.GREENMAN_ITEMS_MAX .get()- 3) + 3));
                        }
                    }
                });
            }
        }
    }

    @Override
    public void die(DamageSource pCause) {
        if (!level.isClientSide()) {
            for (int i = 0;i<HANDLER.getSlots();i++) {
                Containers.dropItemStack(level,getX(),getY(),getZ(),HANDLER.getStackInSlot(i));
            }
        }
        super.die(pCause);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class GreenmanEvents {
        @SubscribeEvent
        public static void onAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(XtraDrinksEntities.GREENMAN.get(), LivingEntity.createLivingAttributes()
                            .add(Attributes.MAX_HEALTH,20.0)
                            .add(Attributes.MOVEMENT_SPEED,.3f)
                            .add(Attributes.FOLLOW_RANGE,40f)
                    .build());
        }
    }
    @Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class GreenmanSpawn {
        @SubscribeEvent
        public static void onBiomeLoad(Biomee event) {
            if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
                if (event.getCategory() != Biome.BiomeCategory.OCEAN && event.getCategory() != Biome.BiomeCategory.RIVER && event.getCategory() != Biome.BiomeCategory.SWAMP && event.getCategory() != Biome.BiomeCategory.BEACH) {
                    event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(XtraDrinksEntities.GREENMAN.get(), 10, 1, 1));
                }
            }
        }
        @SubscribeEvent
        public static void onSpawn(EntityJoinWorldEvent event) {
            if (!event.getWorld().isClientSide()) {
                if (event.getEntity() instanceof GreenmanEntity entity) {
                    entity.randomizeHandler();
                }
            }
        }
    }
}
