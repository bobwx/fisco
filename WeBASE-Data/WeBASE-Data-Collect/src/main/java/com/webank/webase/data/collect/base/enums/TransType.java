/**
 * Copyright 2014-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.data.collect.base.enums;

/**
 * type of transaction.
 *
 * 0:contract deploy, 1:function call
 */
public enum TransType {
    DEPLOY(0), CALL(1);

    private int value;

    TransType(Integer type) {
        this.value = type;
    }

    public int getValue() {
        return this.value;
    }
}
