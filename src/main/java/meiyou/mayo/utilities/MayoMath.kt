package meiyou.mayo.utilities

import java.util.*
import kotlin.math.pow

class MayoMath {

    enum class Operator(val op: Char, val operation: (Double, Double) -> Double) : Comparable<Operator> {
        PSt ('(', {_:  Double, _:  Double -> throw IllegalStateException("Tried to use '(' as operator")}), //
        Pow ('^', {v1: Double, v2: Double -> v1.pow(v2) }),
        Div ('/', {v1: Double, v2: Double -> v1 / v2}),
        Mul ('*', {v1: Double, v2: Double -> v1 * v2}),
        Add ('+', {v1: Double, v2: Double -> v1 + v2}),
        Sub ('-', {v1: Double, v2: Double -> v1 - v2}),
        ;

        override fun toString(): String {
            return "" + this.op;
        }


        companion object {
            fun fromChar(c: Char): Operator {
                return when (c) {
                    '(' -> PSt
                    '+' -> Add
                    '-' -> Sub
                    '/' -> Div
                    '*' -> Mul
                    '^' -> Pow
                    else -> throw IllegalArgumentException("$c is not a known operator!")
                }
            }
        }
    }

    companion object {
        fun reduce(string: String): Double = evaluatePostfix(toPostfix(preprocess(string)))

        private fun preprocess(infix: String): String {
            // remove whitespace
            return infix.replace("\\s+".toRegex(), "")
        }

        // (2 + (3 + 2) + (3 + 2))
        fun toPostfix(infix: String): String {
            val postfix: StringBuilder = StringBuilder()
            val operators: Stack<Operator> = Stack()

            val infix = "($infix)"

            var wasLastOperator = false
            for (c in infix) {
                if (c.isDigit() || c == '.') {
                    if (wasLastOperator) {
                        postfix.append(' ')
                        wasLastOperator = false
                    }
                    postfix.append(c)

                } else if (c == '('){
                    operators.push(Operator.PSt)
                    wasLastOperator = true

                } else if (c == ')') {
                    while(operators.isNotEmpty() && operators.peek() != Operator.PSt) {
                        postfix.append(' ')
                        postfix.append(operators.pop())
                    }
                    operators.pop()
                } else {
                    while(operators.isNotEmpty()  && operators.peek() != Operator.PSt && operators.peek() <= Operator.fromChar(c)
                    ) {
                        postfix.append(' ')
                        postfix.append(operators.pop())
                    }
                    operators.push(Operator.fromChar(c))
                    wasLastOperator = true
                }
            }

            while (operators.isNotEmpty()) {
                postfix.append(' ')
                postfix.append(operators.pop())
            }

            return postfix.toString().trim()
        }

        private fun evaluatePostfix(postfix: String): Double {
            val split = postfix.split("\\s+".toRegex()).toMutableList()
            val operands: Stack<Double> = Stack()

            while(split.isNotEmpty()) {
                val iSplit = split.removeFirst()
                when(iSplit[0]) {
                    in Operator.entries.map{ it.op } -> {
                        val v2 = operands.pop()
                        val v1 = operands.pop()

                        val vResult = Operator.fromChar(iSplit[0]).operation(v1, v2)
                        operands.push(vResult)
                    }
                    else -> {
                        operands.push(iSplit.toDouble())
                    }
                }
            }

            assert(operands.size == 1) {"Could not fully reduce expression!"}

            return operands.pop()
        }

        @JvmStatic
        fun JvmReduce(toReduce: String): Double {
            return MayoMath.reduce(toReduce)
        }
    }



}
