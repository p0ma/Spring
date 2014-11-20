/*
package system.decision.support.logic.operations;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.IComparable;
import entities.drilling.model.parameters.IParameter;

import javax.persistence.*;

@Entity
public class ExpressionWrapper implements IComparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String expression;


    @OneToOne(targetEntity = entities.drilling.model.parameters.Parameter.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "parameter")
    private IParameter parameter;

    public IParameter getParameter() {
        return parameter;
    }

    public void setParameter(IParameter parameter) {
        this.parameter = parameter;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double evaluate() {
        Double val = 0d;
        try {
            val = new ExpressionBuilder(expression).withVariable("p", parameter.getValue()).build().calculate();
        } catch (UnknownFunctionException e) {
            e.printStackTrace();
        } catch (UnparsableExpressionException e) {
            e.printStackTrace();
        } catch (CrossComputingException e) {
            e.printStackTrace();
        }
        return val;
    }

    public String getFullExpression() {
        return expression.replace("p", "(" + parameter.getParameterName() + ")");
    }

    @Override
    public int compareTo(Object object) {
        double val1 = this.evaluate();
        double val2 = ((ExpressionWrapper)object).evaluate();
        if (val1 == val2)
            return 0;
        else if (val1 > val2)
            return 1;
        else
            return -1;
    }

}
*/
