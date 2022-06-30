package Strikeboom.XtraDrinks.entity;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.guis.containers.GreenmanContainer;
import Strikeboom.XtraDrinks.guis.tileentities.itemhandlers.OutputOnlyItemHandler;
import Strikeboom.XtraDrinks.init.XtraDrinksConfig;
import Strikeboom.XtraDrinks.init.XtraDrinksEntities;
import Strikeboom.XtraDrinks.init.XtraDrinksTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class GreenmanEntity extends CreatureEntity {
    private final ItemStackHandler HANDLER;
    private final LazyOptional<IItemHandler> HANDLER_LAZY_OPTIONAL;
    public GreenmanEntity(EntityType<? extends CreatureEntity> p_21683_, World p_21684_) {
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
        goalSelector.addGoal(0,new WalkToEntityGoal<>(this, PlayerEntity.class));
        goalSelector.addGoal(1,new WaterAvoidingRandomWalkingGoal(this,.8f));
        goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.addGoal(2, new RandomWalkingGoal(this,1));
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

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("entity."+ XtraDrinks.MOD_ID +".greenman");
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean save(CompoundNBT pCompound) {
        pCompound.put("ItemStackHandler", HANDLER.serializeNBT());
        return super.save(pCompound);
    }

    @Override
    public void load(CompoundNBT pCompound) {
        super.load(pCompound);
        if (pCompound.contains("ItemStackHandler")) {
            HANDLER.deserializeNBT(pCompound);
        }
    }

    public boolean mayInteract(BlockPos pPos) {
        return pPos.distSqr(new Vector3i(getX(),getY(),getZ())) <= 64;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return HANDLER_LAZY_OPTIONAL.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public ActionResultType interactAt(PlayerEntity pPlayer, Vector3d pVec, Hand pHand) {
        if (!level.isClientSide()) {
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("entity." + XtraDrinks.MOD_ID + ".greenman");
                    }

                    @Override
                    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new GreenmanContainer(windowId, GreenmanEntity.this, playerInventory);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) pPlayer, containerProvider, getOnPos());
        }
        return ActionResultType.SUCCESS;
    }


    public void randomizeHandler() {
        if (!level.isClientSide()) {
            if (XtraDrinksConfig.GREENMAN_ITEMS_ENABLED.get()) {
                getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
                    List<Item> fruits = XtraDrinksTags.FRUIT.getValues();
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
                InventoryHelper.dropItemStack(level,getX(),getY(),getZ(),HANDLER.getStackInSlot(i));
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
        public static void onBiomeLoad(BiomeLoadingEvent event) {
            if (event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
                if (event.getCategory() != Biome.Category.OCEAN && event.getCategory() != Biome.Category.RIVER && event.getCategory() != Biome.Category.SWAMP && event.getCategory() != Biome.Category.BEACH) {
                    event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(XtraDrinksEntities.GREENMAN.get(), 10, 1, 1));
                }
            }
        }
        @SubscribeEvent
        public static void onSpawn(EntityJoinWorldEvent event) {
            if (!event.getWorld().isClientSide()) {
                if (event.getEntity() instanceof GreenmanEntity) {
                    ((GreenmanEntity) event.getEntity()).randomizeHandler();
                }
            }
        }
    }
}
