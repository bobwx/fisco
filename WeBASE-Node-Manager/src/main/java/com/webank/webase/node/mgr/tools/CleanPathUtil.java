/**
 * Copyright 2014-2021 the original author or authors.
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
package com.webank.webase.node.mgr.tools;

/**
 * clean path.
 *
 */
public class CleanPathUtil {

    public static String cleanString(String str) {
        if (str == null) {
            return null;
        }
        String cleanString = "";
        for (int i = 0; i < str.length(); ++i) {
            cleanString += cleanChar(str.charAt(i));
        }
        return cleanString;
    }

    private static char cleanChar(char value) {
        // 0 - 9
        for (int i = 48; i < 58; ++i) {
            if (value == i) {
                return (char) i;
            }
        }
        // 'A' - 'Z'
        for (int i = 65; i < 91; ++i) {
            if (value == i) {
                return (char) i;
            }
        }
        // 'a' - 'z'
        for (int i = 97; i < 123; ++i) {
            if (value == i) {
                return (char) i;
            }
        }
        // other valid characters
        switch (value) {
            case '\\':
                return '\\';
            case '/':
                return '/';
            case ':':
                return ':';
            case '.':
                return '.';
            case '-':
                return '-';
            case '_':
                return '_';
            default:
                return ' ';
        }
    }
}
