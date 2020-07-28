package toby.querydsl.common.enums;

import java.util.HashMap;

/**
 * @author huangzhongjun
 * @date 2020-7-1
 * @description 
 */
public enum BizContext {

	INSTANCE {

		InheritableThreadLocal<HashMap<String, Object>> threadLocal = new InheritableThreadLocal<HashMap<String, Object>>() {
			
			@Override
			protected HashMap<String, Object> initialValue() {
				return new HashMap<>();
			}
		};

		@Override
		public ThreadLocal<HashMap<String, Object>> getThreadLocal() {

			return threadLocal;
		}
		
		@Override
		public Object getValue(String key) {
			
			return getThreadLocal().get().get(key);
		}

		@Override
		public Object setValue(String key, Object value) {
			
			return getThreadLocal().get().put(key, value);
		}

		@Override
		public HashMap<String, Object> getMap() {
			
			return getThreadLocal().get();
		}

		@Override
		public void clear() {
			
			getMap().clear();
		}

	};

	public abstract ThreadLocal<HashMap<String, Object>> getThreadLocal();
	
	public abstract Object getValue(String key);
	
	public abstract Object setValue(String key, Object value);
	
	public abstract HashMap<String, Object> getMap();
	
	public abstract void clear();

}
