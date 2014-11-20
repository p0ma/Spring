package entities.drilling.model.dto;

public class PredicateDTO {

    private String name;
    private String left;
    private String right;
    private String logicalOperation;
    private String expression1;
    private String expression2;
    private Long firesOnTrue;
    private Long firesOnFalse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getLogicalOperation() {
        return logicalOperation;
    }

    public void setLogicalOperation(String logicalOperation) {
        this.logicalOperation = logicalOperation;
    }

    public String getExpression1() {
        return expression1;
    }

    public void setExpression1(String expression1) {
        this.expression1 = expression1;
    }

    public String getExpression2() {
        return expression2;
    }

    public void setExpression2(String expression2) {
        this.expression2 = expression2;
    }

    public Long getFiresOnTrue() {
        return firesOnTrue;
    }

    public void setFiresOnTrue(Long firesOnTrue) {
        this.firesOnTrue = firesOnTrue;
    }

    public Long getFiresOnFalse() {
        return firesOnFalse;
    }

    public void setFiresOnFalse(Long firesOnFalse) {
        this.firesOnFalse = firesOnFalse;
    }
}
