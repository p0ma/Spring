package entities.drilling.parameters;

public class CrossComputingException extends RuntimeException {


    public CrossComputingException(String message) {
        super("Cross computing exception: parameters schema is invalid");
        //super("Cross computing exception: parameters schema is invalid");
    }
}
