package entities.drilling.model.parameters;

public class CrossComputingException extends Exception {
    public CrossComputingException(String message) {
        super("Cross computing exception: parameters schema is invalid");
    }
}
