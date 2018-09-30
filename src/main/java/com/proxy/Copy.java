import com.zqz.jdkproxy.ITaskService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class TaskService$proxy extends Proxy
        implements ITaskService {
    private static Method m1;
    private static Method m2;
    private static Method m3;
    private static Method m0;

    public TaskService$proxy(InvocationHandler paramInvocationHandler)
            throws {
        super(paramInvocationHandler);
    }

    public final boolean equals(Object paramObject)
            throws {
        try {
            return ((Boolean) this.h.invoke(this, m1, new Object[]{paramObject})).booleanValue();
        } catch (RuntimeException localRuntimeException) {
            throw localRuntimeException;
        } catch (Throwable localThrowable) {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    public final String toString()
            throws {
        try {
            return (String) this.h.invoke(this, m2, null);
        } catch (RuntimeException localRuntimeException) {
            throw localRuntimeException;
        } catch (Throwable localThrowable) {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    public final void doTask()
            throws {
        try {
            this.h.invoke(this, m3, null);
            return;
        } catch (RuntimeException localRuntimeException) {
            throw localRuntimeException;
        } catch (Throwable localThrowable) {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    public final int hashCode()
            throws {
        try {
            return ((Integer) this.h.invoke(this, m0, null)).intValue();
        } catch (RuntimeException localRuntimeException) {
            throw localRuntimeException;
        } catch (Throwable localThrowable) {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            m3 = Class.forName("com.zqz.jdkproxy.ITaskService").getMethod("doTask", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            return;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        } catch (ClassNotFoundException localClassNotFoundException) {
        }
        throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
}

---------------------

        本文来自 朱清震 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/zqz_zqz/article/details/78869000?utm_source=copy