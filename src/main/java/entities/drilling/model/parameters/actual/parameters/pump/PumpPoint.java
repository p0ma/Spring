package entities.drilling.model.parameters.actual.parameters.pump;

public class PumpPoint {
    private int turn;
    private double pressure;

    public PumpPoint(int turn, double pressure) {
        this.turn = turn;
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
