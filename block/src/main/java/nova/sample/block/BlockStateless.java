package nova.sample.block;

import nova.core.block.Block;
import nova.core.component.Category;
import nova.core.component.misc.Collider;
import nova.core.component.renderer.ItemRenderer;
import nova.core.network.Packet;
import nova.core.network.Syncable;

/**
 * Literally, this is a test block.
 * @author Calclavia
 */
public class BlockStateless extends Block implements Syncable {

	public BlockStateless() {
		//components.add(new StaticBlockRenderer(this)).setTexture(NovaBlock.steelTexture); // TODO: Removed
		components.add(new Collider(this));
		components.add(new ItemRenderer(this)); // TODO: Deprecated
		components.add(new Category("buildingBlocks"));

		events.on(RightClickEvent.class).bind(this::onRightClick);
	}

	public void onRightClick(RightClickEvent evt) {
		System.out.println("Sending Packet: 1234");
		NovaBlock.networkManager.sync(this);
	}

	@Override
	public void read(Packet packet) {
		System.out.println("Received packet: " + packet.readInt());
	}

	@Override
	public void write(Packet packet) {
		packet.writeInt(1234);
	}
}
