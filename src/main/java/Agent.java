import javassist.*;
import utils.ServerDetector;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Agent {
    public static void agentmain(String args, Instrumentation inst) throws NotFoundException, IOException, CannotCompileException, UnmodifiableClassException, ClassNotFoundException {
        Class<?>[] cLasses = inst.getAllLoadedClasses();
        String targetClass = "javax.servlet.http.HttpServlet";
        if (ServerDetector.isWebLogic()) {
            targetClass = "weblogic.servlet.internal.ServletStubImpl";
        }

        Class[] classes = inst.getAllLoadedClasses();
        for (Class clazz : classes) {
            if (clazz.getName().equals(targetClass)) {
                System.out.println("found: " + targetClass);
                ClassPool cPool = ClassPool.getDefault();
                ClassClassPath classPath = new ClassClassPath(clazz);
                cPool.insertClassPath((ClassPath)classPath);
                CtClass cClass = cPool.get(clazz.getName());
                inst.redefineClasses(new ClassDefinition[] { new ClassDefinition(clazz, cClass.toBytecode()) });
                System.out.println("release ok!");
//                if (cClass.isFrozen()) {
//                    inst.redefineClasses(new ClassDefinition[] { new ClassDefinition(clazz, cClass.toBytecode()) });
//                    System.out.println("release ok!");
//                } else {
//                    System.out.println("no change");
//                }
            }
        }
    }
}
