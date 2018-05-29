package com.trapcodien.scalatest


import com.trapcodien.scalatest.MyMod.logger
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.Item
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.event.RegistryEvent
import org.apache.logging.log4j.Logger

class FirstBlock extends Block(Material.ROCK) {
    logger foreach (_.info("***************** => instanciate first block"))
}

class CommonProxy {

  def preInit(event: FMLPreInitializationEvent): Unit = {
    logger foreach (_.info("*********** => PRE INIT"))
  }

  def init(event: FMLInitializationEvent): Unit = {
    logger foreach(_.info("********* => INIT"))
  }

  def postInit(event: FMLPostInitializationEvent): Unit = {
    logger foreach(_.info("******** => POST INIT"))
  }


  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    logger foreach (_.info("===================> REGISTER FIRST BLOCK"))
  }

  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    logger foreach (_.info("===================> REGISTER ITEM"))
  }
}


@Mod(modid = MyMod.MODID, version = MyMod.VERSION, modLanguage = "scala", useMetadata = true)
@Mod.EventBusSubscriber(modid = MyMod.MODID)
object MyMod {
  final val MODID = "scalatest"
  final val VERSION = "0.1.0"

  var logger: Option[Logger] = None

  @Mod.Instance
  val instance: MyMod.type = this

  @SidedProxy(clientSide = "com.trapcodien.scalatest.CommonProxy", serverSide = "com.trapcodien.scalatest.CommonProxy")
  var proxy: CommonProxy = new CommonProxy



  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    logger = Some(event.getModLog)
    proxy.preInit(event)
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = proxy.init(event)

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = proxy.postInit(event)

  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = proxy.registerBlocks(event)

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = proxy.registerItems(event)
}
