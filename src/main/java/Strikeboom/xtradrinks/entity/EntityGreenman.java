package Strikeboom.xtradrinks.entity;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.tileentity.customs.OutPutOnlyItemHandler;
import Strikeboom.xtradrinks.handlers.GuiHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class EntityGreenman extends EntityLiving {
    private final ItemStackHandler handler;
    public EntityGreenman(World worldIn) {
        super(worldIn);
        handler = new OutPutOnlyItemHandler(9);
        setCanPickUpLoot(false);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIWatchClosest(this,EntityPlayer.class,20f));
        this.tasks.addTask(0, new EntityGreenman.WalkToPlayer(this));
        this.tasks.addTask(2, new EntityAISwimming(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
    }

    @Override
    public float getEyeHeight() {
        return 1.62F;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("ItemStackHandler",this.handler.serializeNBT());

        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.handler.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));
        super.readFromNBT(compound);
    }


    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.handler;
        }
        return null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("entity.greenman.name");
    }
    public boolean canPlayerInteractWith(EntityPlayer player) {
        return player.getDistanceSq(this.getPosition().add(0.5, 0.5, 0.5)) <= 64;
    }

    @Override
    public boolean isNonBoss() {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            player.openGui(XtraDrinks.instance, GuiHandler.GREENMAN,world,getPosition().getX(),getPosition().getY(),getPosition().getZ());

        }
        return true;
    }

    @Override
    public float getAIMoveSpeed() {
        return .25f;
    }

    public void randomizeHandler() {
        if (!this.world.isRemote) {
                //Greenman should always have a capability so there should be no error
                ItemStackHandler handler = (ItemStackHandler) this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                for (int i = 0; i < handler.getSlots(); i++) {
                    Item randomFruit = OreDictionary.getOres("fruit", false).get(rand.nextInt(OreDictionary.getOres("fruit", false).size())).getItem();
                    //50% chance to have an item in the slot
                    if (rand.nextInt(2) == 1) {

                        handler.setStackInSlot(i, new ItemStack(randomFruit, rand.nextInt(13) + 3));
                    }
                }
        }
    }
    @Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID)
    public static class GreenmanEvents {
        //when greenman is spawned naturally
        @SubscribeEvent
        public static void GreenmanSpawn(LivingSpawnEvent event) {
            if (!event.getWorld().isRemote) {
                if (event.getEntityLiving() instanceof EntityGreenman) {
                    ((EntityGreenman)event.getEntityLiving()).randomizeHandler();
                }
            }
        }
        //when greenman dies and drops all fruit
        @SubscribeEvent
        public static void GreenmanDeath(LivingDeathEvent event) {
            if (!event.getEntityLiving().getEntityWorld().isRemote) {
                if (event.getEntityLiving() instanceof EntityGreenman) {

                    EntityGreenman greenman = (EntityGreenman) event.getEntityLiving();
                    ItemStackHandler handler = (ItemStackHandler) greenman.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                    for (int i = 0; i < handler.getSlots(); i++) {
                        ItemStack stack = handler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            InventoryHelper.spawnItemStack(greenman.getEntityWorld(), greenman.posX, greenman.posY, greenman.posZ, stack);
                        }
                    }
                }
            }
        }
    }
    public static class WalkToPlayer extends EntityAIBase{
        EntityLiving entity;
        EntityPlayer player;
        public WalkToPlayer(EntityLiving entityLiving) {
            entity = entityLiving;
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }

        @Override
        public void updateTask() {
            player = entity.world.getClosestPlayerToEntity(entity,15.0d);
           if (player != null) {
               if (entity.getDistance(player) > 5f) {
                   entity.getLookHelper().setLookPositionWithEntity(player, (float) (this.entity.getHorizontalFaceSpeed() + 20), (float) this.entity.getVerticalFaceSpeed());
                   entity.getNavigator().tryMoveToEntityLiving(player, entity.getAIMoveSpeed());
               }
           }
           super.updateTask();
        }

    }
}
