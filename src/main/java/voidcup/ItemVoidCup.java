package voidcup;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVoidCup extends Item {
	
	public ItemVoidCup() {
		super();
		setMaxStackSize(1);
		setRegistryName("voidCup");
		setUnlocalizedName("voidCup");
		this.setCreativeTab(CreativeTabs.TOOLS);
		GameRegistry.register(this);
		
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("voidcup.tooltip"));
	}
	
	//I borrowed this from Botania, and thus this bit is really Vazkii's.
	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		RayTraceResult trace = rayTrace(world, player, true);

		if (trace == null)
			return ActionResult.newResult(EnumActionResult.PASS, stack);
		else {
			if(trace.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos pos = trace.getBlockPos();

				if(!world.isBlockModifiable(player, pos))
					return ActionResult.newResult(EnumActionResult.PASS, stack);

				if(!player.canPlayerEdit(pos, trace.sideHit, stack))
					return ActionResult.newResult(EnumActionResult.PASS, stack);

				IBlockState state = world.getBlockState(pos);
				

				if(state.getBlock() instanceof IFluidBlock || state.getBlock() instanceof BlockLiquid) {
					world.setBlockToAir(pos);
					return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
				}
			}

			return ActionResult.newResult(EnumActionResult.PASS, stack);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("voidcup:itemVoidCup", "type=cup"));
	}

}
