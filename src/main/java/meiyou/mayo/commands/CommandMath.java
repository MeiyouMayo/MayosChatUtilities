package meiyou.mayo.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meiyou.mayo.utilities.MayoMath;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class CommandMath {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("math")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("toReduce", StringArgumentType.string())
                        .executes(ctx -> {
                            try {
                                String original = StringArgumentType.getString(ctx, "toReduce");
                                String reduction = reduce(original);
                                String message = original.trim() + " = " + reduction;

                                ctx.getSource().sendSystemMessage(Component.literal(message));
                                return 0;
                            } catch (Exception e) {
                                ctx.getSource().sendSystemMessage(Component.literal("Bad expression!"));
                                return 1;
                            }
                        })
                );
    }

    public static String reduce(String toReduce) {
        return "" + MayoMath.JvmReduce(toReduce);

    }
}
