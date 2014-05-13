package examples;

import java.lang.invoke.*;

public class LittleOrphanLambda {
    public static void main(String[] args) throws Throwable {

        MethodHandles.Lookup me= MethodHandles.lookup();
        MethodType t = MethodType.methodType(void.class);
        MethodType rt = MethodType.methodType(Runnable.class);

        CallSite site = LambdaMetafactory.metafactory(
                me, "run", rt, t, me.findStatic(LittleOrphanLambda.class, "sayHello", t), t);

        MethodHandle factory = site.getTarget();
        Runnable r = (Runnable)factory.invoke();

        r.run();
    }
    private static void sayHello() {
        System.out.println("hello world");
    }
}
