import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.PrimeDecompositionServer;

import java.io.IOException;

public class PrimeServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Server started...");
        Server server = ServerBuilder.forPort(50051)
                .addService(new PrimeDecompositionServer())
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Recieved shutdown request");
            server.shutdown();
            System.out.println("Successfully shutdown");
        }));
        server.awaitTermination();

    }

}
