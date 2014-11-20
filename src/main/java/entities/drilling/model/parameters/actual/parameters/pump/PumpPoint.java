package entities.drilling.model.parameters.actual.parameters.pump;

/**
 * Created with IntelliJ IDEA.
 * User: Damager1
 * Date: 14.11.14
 * Time: 8:19
 * To change this template use File | Settings | File Templates.
 */
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
