package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "-")
public abstract class PhpNegNode extends PhpUnaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long neg(long val) {
        return Math.negateExact(val);
    }

    @Specialization
    protected double neg(double val) {
        return - val;
    }

    @Override
    public String toString() {
        return "PhpNegNode{" + getValueNode() + "}";
    }
}
