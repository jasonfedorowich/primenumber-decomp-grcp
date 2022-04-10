package server;

import com.proto.prime.PrimeRequest;
import com.proto.prime.PrimeResponse;
import com.proto.prime.PrimeServiceGrpc;
import io.grpc.stub.StreamObserver;

public class PrimeDecompositionServer extends PrimeServiceGrpc.PrimeServiceImplBase {

    @Override
    public void prime(PrimeRequest request, StreamObserver<PrimeResponse> responseObserver) {
        int number = request.getNumber();
        int k = 2;

        while(number > 1){
            if(number % k == 0){
                responseObserver.onNext(makeResponse(k));
                number /= k;
            }else{
                k++;
            }
        }
        responseObserver.onCompleted();
    }

    private PrimeResponse makeResponse(int k){
        return PrimeResponse.newBuilder().setResult(k).build();
    }


}
