package ferro2000.immersivetech.common;

import ferro2000.immersivetech.ImmersiveTech;
import ferro2000.immersivetech.api.craftings.BoilerRecipes;
import ferro2000.immersivetech.api.craftings.DistillerRecipes;
import ferro2000.immersivetech.api.craftings.SolarTowerRecipes;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
		
	@net.minecraftforge.common.config.Config(modid=ImmersiveTech.MODID)
	public static class ITConfig{
		
		public static Machines machines;
		
		public static class Machines{
			
			/*MULTIBLOCK*/
			/*ENERGY*/
			@Comment({"A modifier to apply to the burn time of steam into the SteamTurbine: (1000 / (burnTime * 2)) * steamTurbine_burnTimeModifier mb/t [steamBurnTime = 50]"})
			public static int steamTurbine_burnTimeModifier = 5;
			@Comment({"The max torque that the Steam Turbine can produce"})
			public static int steamTurbine_maxTorque = 8192;
			@Comment({"The max speed that the Steam Turbine can produce"})
			public static int steamTurbine_maxSpeed = 6144;
			
			@Comment({"The max of Flux that the Alternator can store"})
			public static int alternator_energyStorage = 12000000;
			@Comment({"A modifier to apply to the Flux production of the Alternator ((speed*torque) / modifier)"})
			public static int alternator_RfModifier = 300;
			@Comment({"The max of Flux that the Alternator can output per each energy device connected"})
			public static int alternator_RfPerTick = 8192;
			
			/*BLOCK*/
			/*ENERGY*/
			@Comment({"The Flux per tick the Coke Oven Preheater will consume to speed up the Coke Oven Advanced"})
			public static int cokeOvenPreheater_consumption = 32;
			
			/*RECIPE*/
			@Comment({"A modifier to apply to the time of every Solar Tower recipe"})
			public static int solarTower_timeModifier = 1;
			
			@Comment({"A modifier to apply to the time of every Distiller recipe"})
			public static int distiller_timeModifier = 1;
			@Comment({"A modifier to apply to the energy costs of every Distiller recipe"})
			public static int distiller_energyModifier = 1;
			
			@Comment({"A modifier to apply to the time of every Boiler recipe"})
			public static int boiler_timeModifier = 1;
			@Comment({"A modifier to apply to the burn time of fuel into the Boiler: (1000 / fuelBurnTime) * (4 * boiler_burnTimeModifier) mb/t {fuelBurnTime [biodiesel = 125], [fuel = 375], [diesel = 175]}"})
			public static int boiler_burnTimeModifier = 3;
			
			/*MISC*/
			@Comment({"The minimun distance between the Solar Tower and the Solar Reflectors"})
			public static int solarTower_minRange = 5;
			@Comment({"The max distance between the Solar Tower and the Solar Reflectors"})
			public static int solarTower_maxRange = 10;
			
		}
		
	}
	
	static Configuration config;
	
	public static void preInit(FMLPreInitializationEvent event)
	{

		SolarTowerRecipes.timeModifier = ITConfig.Machines.solarTower_timeModifier;
		
		DistillerRecipes.timeModifier = ITConfig.Machines.distiller_timeModifier;
		DistillerRecipes.energyModifier = ITConfig.Machines.distiller_energyModifier;
		
		BoilerRecipes.timeModifier = ITConfig.Machines.boiler_timeModifier;
		
	}

}
