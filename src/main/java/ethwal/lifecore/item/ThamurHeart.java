package ethwal.lifecore.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ethwal.lifecore.Lifecore;

public class ThamurHeart extends Item {
    public ThamurHeart(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        Lifecore.addHealth(user, 1);

        ItemStack itemStack = user.getStackInHand(hand);

        itemStack.decrement(1);

        return ActionResult.SUCCESS;
    }
}
