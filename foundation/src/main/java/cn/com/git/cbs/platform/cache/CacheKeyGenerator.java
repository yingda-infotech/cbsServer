package cn.com.git.cbs.platform.cache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CacheKeyGenerator 
  implements org.springframework.cache.interceptor.KeyGenerator {

    @Override
    public Object generate(final Object target, final Method method, 
      final Object... params) {
        final List<Object> key = new ArrayList<>();
        key.add(method.getDeclaringClass().getName());
        key.add(method.getName());

        for (final Object o : params) {
            key.add(o);
        }
        return key;
    }
}