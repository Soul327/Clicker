package Parts;

import Main.World;

public class Automat extends Part{

	public Automat(World world) {
		super(world);
		name = "Automat";
		y = 40;
		maxTime = 60 * 20;
		//maxStored = (int) world.o2;
	}
	public void tickTime() {
		if(time>0) {
			if(time==1) {
				Part p = world.getPart("Food");
				if(p!=null)
					if(p.stored>=5 & world.o2>0) {
						p.stored-=5;
						world.o2-=1;
					} else
						time = -1;
			}
			time++;
			if(time>maxTime) {
				time = 0;
				stored+=1;
			}
		}
	}
}
