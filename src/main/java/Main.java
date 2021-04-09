import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        if (args.length < 1) {
            System.out.println("java -jar releaseShell.jar [pid]");
            System.exit(0);
        }

        System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        VirtualMachine virtualMachine = VirtualMachine.attach(args[0]);
        virtualMachine.loadAgent(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        virtualMachine.detach();
        System.out.println("Success");
    }
}
