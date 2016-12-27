package helpertools.Com;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class Dummy_Handler implements IMessageHandler<Mirage_Client_Message, IMessage>
{
  public IMessage onMessage(Mirage_Client_Message message, MessageContext ctx) {
    return null;
  }
}