package practice.week14opt2

import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.rules.Rule
import org.apache.spark.sql.catalyst.expressions.Multiply
import org.apache.spark.sql.catalyst.expressions.Literal

object MultiplyOptimizationRule extends Rule[LogicalPlan] {
  
  def apply(plan: LogicalPlan): LogicalPlan = plan transformAllExpressions {
  case Multiply(left,right,false) //remove false parameter
  if right.isInstanceOf[Literal] && right.asInstanceOf[Literal].value.asInstanceOf[Integer] == 1 
    =>
      println("optimization of one applied")
      left
  }
}

//spark.experimental.extraOptimizations = Seq(MultiplyOptimizationRule)