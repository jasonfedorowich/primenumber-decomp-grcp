import com.proto.prime.Prime;
import com.proto.prime.PrimeRequest;
import com.proto.prime.PrimeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientTest {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        PrimeServiceGrpc.PrimeServiceBlockingStub stub = PrimeServiceGrpc.newBlockingStub(channel);

        PrimeRequest request = PrimeRequest.newBuilder().setNumber(120).build();
        stub.prime(request).forEachRemaining(u->{
            System.out.println(u.getResult());
        });

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
