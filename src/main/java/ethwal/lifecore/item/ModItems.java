package ethwal.lifecore.item;

import ethwal.lifecore.Lifecore;
import ethwal.lifecore.item.ThamurHeart;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public final class ModItems {
	private ModItems() {
	}

    public static final ThamurHeart HEART_THAMUR = (ThamurHeart) register("heart_thamur", ThamurHeart::new, new Item.Settings()
			.component(DataComponentTypes.CONSUMABLE, new ConsumableComponent(1, null, null, false, null)));

	public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
		final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lifecore.MOD_ID, path));
		return Items.register(registryKey, factory, settings);
	}

	public static void initialize() {
	}
}
