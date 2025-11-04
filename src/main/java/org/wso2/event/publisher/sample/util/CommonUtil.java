/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.event.publisher.sample.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.CARBON_SUPER_TENANT;

/**
 * Utility class for common helper methods.
 */
public class CommonUtil {

    private CommonUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Convert array to ArrayList.
     *
     * @param array String array to convert
     * @return ArrayList of strings
     */
    public static List<String> convertToArrayList(String[] array) {
        if (array == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Convert list to string payload, returns null for empty lists.
     *
     * @param list List to convert
     * @return String representation of list or null
     */
    public static Object listToPayload(List<String> list) {
        if (list != null && !list.isEmpty()) {
            return list.toString();
        }
        return null;
    }

    /**
     * Check if tenant domain is organization (not carbon.super).
     *
     * @param tenantDomain Tenant domain to check
     * @return true if organization tenant, false otherwise
     */
    public static boolean isOrganizationTenant(String tenantDomain) {
        return !CARBON_SUPER_TENANT.equals(tenantDomain);
    }

    /**
     * Select stream name based on tenant type.
     *
     * @param tenantDomain  Tenant domain
     * @param defaultStream Default stream name for carbon.super
     * @param orgStream     Organization stream name
     * @return Appropriate stream name
     */
    public static String selectStreamName(String tenantDomain, String defaultStream, String orgStream) {
        return isOrganizationTenant(tenantDomain) ? orgStream : defaultStream;
    }
}

