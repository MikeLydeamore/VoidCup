package voidcup;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid=VoidCup.MODID)
public class VoidCup {
	
	public static final String MODID = "voidcup";
	
	@Instance(MODID)
	public static VoidCup instance;
	
	public static ItemVoidCup voidCup;
	
	@SidedProxy(serverSide="voidcup.CommonProxy", clientSide="voidcup.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		
		voidCup = new ItemVoidCup();
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(voidCup), new Object[] {"x x", "x x"," x ", 'x', "blockGlassGreen"}));
		
		proxy.initModels();
				
	}

}
