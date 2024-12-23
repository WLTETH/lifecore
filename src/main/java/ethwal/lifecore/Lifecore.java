package ethwal.lifecore;

import ethwal.lifecore.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.server.MinecraftServer;
import java.util.function.Function;

import java.util.Objects;

import static net.minecraft.item.Items.register;

public class Lifecore implements ModInitializer {
	public static final String MOD_ID = "lifecore";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Heart Change
	public static final Identifier HEART_DELTA = Identifier.of(MOD_ID, "heart_delta");

	public static float heart_delta = 0;

	@Override
	public void onInitialize() {
		ModItems.initialize();
		// try make it work with after_death instead?
		ServerPlayerEvents.AFTER_RESPAWN.register((playerO, playerN, alive) -> {

			addHealth(playerN, -2);
		});

	}

	public static void addHealth(PlayerEntity playerEntity, float value) {
		heart_delta = heart_delta + value;

		EntityAttributeInstance healthAttribute = playerEntity.getAttributeInstance(EntityAttributes.MAX_HEALTH);

		if (healthAttribute.getModifier(HEART_DELTA) == null) {
			EntityAttributeModifier healthModifier = new EntityAttributeModifier(HEART_DELTA, heart_delta, EntityAttributeModifier.Operation.ADD_VALUE);

			playerEntity.getAttributeInstance(EntityAttributes.MAX_HEALTH).addPersistentModifier(healthModifier);

			//playerEntity.sendMessage(Text.literal("Your max health has been reduced by 2. (init if)"));
		} else {
			double currentHealthDelta = healthAttribute.getValue();

			healthAttribute.removeModifier(HEART_DELTA);

			EntityAttributeModifier healthModifier = new EntityAttributeModifier(HEART_DELTA, heart_delta, EntityAttributeModifier.Operation.ADD_VALUE);

			playerEntity.getAttributeInstance(EntityAttributes.MAX_HEALTH).addPersistentModifier(healthModifier);

			//playerEntity.sendMessage(Text.literal("Your max health has been reduced by 2. (else)"));
		}
	}
}