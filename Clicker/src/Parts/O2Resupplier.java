package Parts;

import Main.World;

public class O2Resupplier extends Part{
	public O2Resupplier(World world) {
		super(world);
		name = "O2 Resupplier";
		y = 40;
		maxTime = 60 * 20;
		//maxStored = (int) world.o2;
	}
}
