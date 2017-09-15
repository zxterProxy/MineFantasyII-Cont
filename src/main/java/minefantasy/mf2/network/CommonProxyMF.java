package minefantasy.mf2.network;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.archery.AmmoMechanicsMF;
import minefantasy.mf2.api.refine.ISmokeHandler;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityBellows;
import minefantasy.mf2.block.tileentity.TileEntityBigFurnace;
import minefantasy.mf2.block.tileentity.TileEntityBloomery;
import minefantasy.mf2.block.tileentity.TileEntityBombBench;
import minefantasy.mf2.block.tileentity.TileEntityBombPress;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.block.tileentity.TileEntityChimney;
import minefantasy.mf2.block.tileentity.TileEntityComponent;
import minefantasy.mf2.block.tileentity.TileEntityCrossbowBench;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.block.tileentity.TileEntityFirepit;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import minefantasy.mf2.block.tileentity.TileEntityQuern;
import minefantasy.mf2.block.tileentity.TileEntityResearch;
import minefantasy.mf2.block.tileentity.TileEntityRoad;
import minefantasy.mf2.block.tileentity.TileEntityRoast;
import minefantasy.mf2.block.tileentity.TileEntityTanningRack;
import minefantasy.mf2.block.tileentity.TileEntityWorldGenMarker;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFC;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.block.tileentity.decor.TileEntityAmmoBox;
import minefantasy.mf2.block.tileentity.decor.TileEntityRack;
import minefantasy.mf2.block.tileentity.decor.TileEntityTrough;
import minefantasy.mf2.container.ContainerAnvilMF;
import minefantasy.mf2.container.ContainerBigFurnace;
import minefantasy.mf2.container.ContainerBlastChamber;
import minefantasy.mf2.container.ContainerBlastHeater;
import minefantasy.mf2.container.ContainerBloomery;
import minefantasy.mf2.container.ContainerBombBench;
import minefantasy.mf2.container.ContainerCarpenterMF;
import minefantasy.mf2.container.ContainerCrossbowBench;
import minefantasy.mf2.container.ContainerCrucible;
import minefantasy.mf2.container.ContainerForge;
import minefantasy.mf2.container.ContainerQuern;
import minefantasy.mf2.container.ContainerReload;
import minefantasy.mf2.container.ContainerResearch;
import minefantasy.mf2.entity.EntitySmoke;
import minefantasy.mf2.entity.list.EntityListMF;
import minefantasy.mf2.hunger.HungerSystemMF;
import minefantasy.mf2.item.archery.ArrowFireFlint;
import minefantasy.mf2.item.archery.ArrowFirerMF;
import minefantasy.mf2.mechanics.ArrowHandlerMF;
import minefantasy.mf2.mechanics.CombatMechanics;
import minefantasy.mf2.mechanics.EventManagerMF;
import minefantasy.mf2.mechanics.MonsterUpgrader;
import minefantasy.mf2.mechanics.PlayerTickHandlerMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Anonymous Productions
 */
public class CommonProxyMF implements IGuiHandler, ISmokeHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 1 && x == 1 && player.getHeldItem() != null) {
			return new ContainerReload(player.inventory, player.getHeldItem());
		} else if (ID == 0) {
			TileEntity tile = world.getTileEntity(x, y, z);
			// int meta = world.getBlockMetadata(x, y, z);
			if (tile == null) {
				return null;
			}

			if (tile instanceof TileEntityAnvilMF) {
				return new ContainerAnvilMF(player.inventory, (TileEntityAnvilMF) tile);
			}
			if (tile instanceof TileEntityCarpenterMF) {
				return new ContainerCarpenterMF(player.inventory, (TileEntityCarpenterMF) tile);
			}
			if (tile instanceof TileEntityBombBench) {
				return new ContainerBombBench(player.inventory, (TileEntityBombBench) tile);
			}
			if (tile instanceof TileEntityBlastFH) {
				return new ContainerBlastHeater(player.inventory, (TileEntityBlastFH) tile);
			}
			if (tile instanceof TileEntityBlastFC) {
				return new ContainerBlastChamber(player.inventory, (TileEntityBlastFC) tile);
			}
			if (tile instanceof TileEntityCrucible) {
				return new ContainerCrucible(player.inventory, (TileEntityCrucible) tile);
			}
			if (tile instanceof TileEntityForge) {
				return new ContainerForge(player.inventory, (TileEntityForge) tile);
			}
			if (tile instanceof TileEntityResearch) {
				return new ContainerResearch(player.inventory, (TileEntityResearch) tile);
			}
			if (tile instanceof TileEntityBloomery) {
				return new ContainerBloomery(player.inventory, (TileEntityBloomery) tile);
			}
			if (tile instanceof TileEntityCrossbowBench) {
				return new ContainerCrossbowBench(player.inventory, (TileEntityCrossbowBench) tile);
			}
			if (tile instanceof TileEntityQuern) {
				return new ContainerQuern(player.inventory, (TileEntityQuern) tile);
			}
			if (tile instanceof TileEntityBigFurnace) {
				return new ContainerBigFurnace(player, (TileEntityBigFurnace) tile);
			}
		}
		return null;
	}

	public World getClientWorld() {
		return null;
	}

	public void registerMain() {
		AmmoMechanicsMF.addHandler(new ArrowFireFlint());
		AmmoMechanicsMF.addHandler(new ArrowFirerMF());
		registerTileEntities();
		SmokeMechanics.handler = this;
	}

	protected void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityAnvilMF.class, "MF_Anvil");
		GameRegistry.registerTileEntity(TileEntityCarpenterMF.class, "MF_CarpenterBench");
		GameRegistry.registerTileEntity(TileEntityBombBench.class, "MF_BombBench");
		GameRegistry.registerTileEntity(TileEntityCrossbowBench.class, "MF_CrossbowBench");
		GameRegistry.registerTileEntity(TileEntityBlastFC.class, "MF_BlastChamber");
		GameRegistry.registerTileEntity(TileEntityBlastFH.class, "MF_BlastHeater");
		GameRegistry.registerTileEntity(TileEntityCrucible.class, "MF_Crucible");
		GameRegistry.registerTileEntity(TileEntityChimney.class, "MF_Chimney");
		GameRegistry.registerTileEntity(TileEntityTanningRack.class, "MF_Tanner");
		GameRegistry.registerTileEntity(TileEntityForge.class, "MF_Forge");
		GameRegistry.registerTileEntity(TileEntityBellows.class, "MF_Bellows");
		GameRegistry.registerTileEntity(TileEntityResearch.class, "MF_Research");
		GameRegistry.registerTileEntity(TileEntityTrough.class, "MF_Trough");
		GameRegistry.registerTileEntity(TileEntityBombPress.class, "MF_BombPress");
		GameRegistry.registerTileEntity(TileEntityBloomery.class, "MF_Bloomery");
		GameRegistry.registerTileEntity(TileEntityQuern.class, "MF_Quern");
		GameRegistry.registerTileEntity(TileEntityFirepit.class, "MF_Firepit");
		GameRegistry.registerTileEntity(TileEntityRoast.class, "MF_SpitRoast");
		GameRegistry.registerTileEntity(TileEntityBigFurnace.class, "MF_BigFurnace");
		GameRegistry.registerTileEntity(TileEntityRack.class, "MF_Rack");
		GameRegistry.registerTileEntity(TileEntityAmmoBox.class, "MF_AmmoBox");
		GameRegistry.registerTileEntity(TileEntityWorldGenMarker.class, "MF_WorldGenFlag");
		GameRegistry.registerTileEntity(TileEntityComponent.class, "MF_ComponentTile");
		GameRegistry.registerTileEntity(TileEntityRoad.class, "MF_Road");
	}

	public void preInit() {
	}

	public void registerTickHandlers() {
		FMLCommonHandler.instance().bus().register(new PlayerTickHandlerMF());
		FMLCommonHandler.instance().bus().register(new HungerSystemMF());
		MinecraftForge.EVENT_BUS.register(new EventManagerMF());
		MinecraftForge.EVENT_BUS.register(new CombatMechanics());
		MinecraftForge.EVENT_BUS.register(new MonsterUpgrader());
		MinecraftForge.EVENT_BUS.register(new ArrowHandlerMF());
	}

	public EntityPlayer getClientPlayer() {
		return null;
	}

	@Override
	public void spawnSmoke(World world, double x, double y, double z, int value) {
		for (int a = 0; a < value; a++) {
			float sprayRange = 0.005F;
			float sprayX = (MineFantasyII.random.nextFloat() * sprayRange) - (sprayRange / 2);
			float sprayZ = (MineFantasyII.random.nextFloat() * sprayRange) - (sprayRange / 2);
			float height = 0.001F;
			if (MineFantasyII.random.nextInt(2) == 0) {
				EntitySmoke smoke = new EntitySmoke(world, x, y - 0.5D, z, sprayX, height, sprayZ);
				world.spawnEntityInWorld(smoke);
			}
		}
	}

	public void postInit() {
		EntityListMF.register();
	}

}