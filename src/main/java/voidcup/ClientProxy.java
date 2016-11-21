package voidcup;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initModels() {
		VoidCup.voidCup.initModel();
	}

}
