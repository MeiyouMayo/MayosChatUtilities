package meiyou.mayo.mayoschatutilities.listener

import meiyou.mayo.mayoschatutilities.MayosChatUtilities
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.server.ServerStartingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

class ServerEventListener {
    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent?) {
        MayosChatUtilities.LOGGER.info("hello from mayo")
    }

    @SubscribeEvent
    fun onRegisterServerSideCommands(event: RegisterCommandsEvent) {
        val dispatcher = event.dispatcher
    }
}