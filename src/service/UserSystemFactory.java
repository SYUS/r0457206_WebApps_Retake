package service;

public class UserSystemFactory {
	private UserSystemFactory() {}
	
	private static class UserSystemFactoryHolder {
		private static final UserSystem INSTANCE = new UserSystem();
	}
	
	public static UserSystem getInstance() {
		return UserSystemFactoryHolder.INSTANCE;
	}
}
