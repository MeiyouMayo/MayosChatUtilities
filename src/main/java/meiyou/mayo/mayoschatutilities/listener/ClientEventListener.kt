package meiyou.mayo.mayoschatutilities.listener

import meiyou.mayo.commands.ModCommands
import net.minecraftforge.client.event.RegisterClientCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

class ClientEventListener {
    @SubscribeEvent
    fun onRegisterClientSideCommands(event: RegisterClientCommandsEvent) {
        val clientSideDispatcher = event.dispatcher

        ModCommands.register(clientSideDispatcher)
    }
}