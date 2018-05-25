package com.trapcodien.scalatest


import net.minecraft.init.Blocks
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import org.apache.logging.log4j.Logger


@Mod(modid = MyMod.MODID, version = MyMod.VERSION, modLanguage = "scala")
object MyMod {
  type PreInitEvent = FMLPreInitializationEvent
  type InitEvent = FMLInitializationEvent

  final val MODID = "scalatest"
  final val VERSION = "0.1.0"

  private var logger: Option[Logger] = None

  @EventHandler
  def preInit(event: PreInitEvent): Unit = {
    logger = Some(event.getModLog)
    logger foreach(_.info("INIT"))
  }

  @EventHandler
  def init(event: InitEvent): Unit = {
    logger foreach(_.info(s"DIRT BLOCK >> ${Blocks.DIRT.toString()}"))
  }
}
