/**
 * Copyright (C) 2011 Akiban Technologies Inc.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.UnaryArithmeticOperatorNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * This node represents a unary arithmetic operator
 *
 */

public class UnaryArithmeticOperatorNode extends UnaryOperatorNode
{
    public static enum OperatorType {
        PLUS("+", "plus"), 
        MINUS("-", "minus"), 
        SQRT("SQRT", "sqrt"), 
        ABSOLUTE("ABS/ABSVAL", "absolute");

        String operator, methodName;
        OperatorType(String operator, String methodName) {
            this.operator = operator;
            this.methodName = methodName;
        }
    }
    private OperatorType operatorType;
    
    /**
     * Initializer for a UnaryArithmeticOperatorNode
     *
     * @param operand The operand of the node
     */
    public void init(Object operand) throws StandardException {
        switch(getNodeType()) {
        case NodeTypes.UNARY_PLUS_OPERATOR_NODE:
            operatorType = OperatorType.PLUS;
            break;
        case NodeTypes.UNARY_MINUS_OPERATOR_NODE:
            operatorType = OperatorType.MINUS;
            break;
        case NodeTypes.SQRT_OPERATOR_NODE:
            operatorType = OperatorType.SQRT;
            break;
        case NodeTypes.ABSOLUTE_OPERATOR_NODE:
            operatorType = OperatorType.ABSOLUTE;
            break;
        default:
            assert false : "init for UnaryArithmeticOperator called with wrong nodeType = " + getNodeType();
            break;
        }
        init(operand, operatorType.operator, operatorType.methodName);
    }
        
    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        UnaryArithmeticOperatorNode other = (UnaryArithmeticOperatorNode)node;
        this.operatorType = other.operatorType;
    }

}
