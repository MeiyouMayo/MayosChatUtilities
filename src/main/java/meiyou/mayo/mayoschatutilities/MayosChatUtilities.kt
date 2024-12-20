package meiyou.mayo.mayoschatutilities;


import meiyou.mayo.commands.ModCommands
import meiyou.mayo.mayoschatutilities.listener.ClientEventListener
import meiyou.mayo.mayoschatutilities.listener.ServerEventListener
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MayosChatUtilities.MODID)
class MayosChatUtilities {

    init {
        //MinecraftForge.EVENT_BUS.register(ServerEventListener())
        MinecraftForge.EVENT_BUS.register(ClientEventListener())
    }

    companion object {
        const val MODID: String = "mayoschatutilities"
        val LOGGER: Logger = LogManager.getLogger()
    }



object EventHandler {
        @SubscribeEvent
        fun registerCommand(event: RegisterCommandsEvent) {
            ModCommands.register(event.dispatcher)
        }
    }


}