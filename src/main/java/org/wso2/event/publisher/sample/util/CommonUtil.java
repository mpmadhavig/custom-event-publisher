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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.event.publisher.sample.internal.CustomEventPublisherDataHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.CARBON_SUPER_TENANT;

/**
 * Utility class for common helper methods.
 */
public class CommonUtil {

    private static final Log LOG = LogFactory.getLog(CommonUtil.class);

    private CommonUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Validate if the tenant is active before publishing events.
     *
     * @param tenantDomain Tenant domain to check
     */
    public static void validateActiveTenant(String tenantDomain) {

        boolean isTenantActive;
        try {
            isTenantActive = CustomEventPublisherDataHolder.getInstance().getRealmService().getTenantManager().
                    isTenantActive(IdentityTenantUtil.getTenantId(tenantDomain));
        } catch (Exception e) {
            LOG.debug("Error while checking whether the tenant is active or not: " + tenantDomain, e);
            return;
        }

        if (!isTenantActive) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Skipping event publishing for inactive tenant: " + tenantDomain);
            }
            return;
        }
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

    /**
     * Convert map to JSON string for event payload.
     * Returns null for null or empty maps.
     *
     * @param map Map to convert
     * @return JSON string representation of the map, or null if map is empty
     */
    public static Object mapToKeyValuePairs(java.util.Map<String, String> map) {

        if (map == null || map.isEmpty()) {
            return null;
        }

        StringBuilder json = new StringBuilder("{");
        int index = 0;
        for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
            if (index > 0) {
                json.append(",");
            }
            json.append("\"").append(escapeJson(entry.getKey())).append("\":");
            json.append("\"").append(escapeJson(entry.getValue())).append("\"");
            index++;
        }
        json.append("}");
        return json.toString();
    }

    /**
     * Escape special characters in JSON strings.
     *
     * @param value String to escape
     * @return Escaped string safe for JSON
     */
    private static String escapeJson(String value) {

        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * Get Meta data array.
     *
     * @param tenantDomain Tenant domain.
     * @return Meta data array.
     */
    public static Object[] getMetaDataArray(String tenantDomain) {

        Object[] metaData = new Object[1];
        if (StringUtils.isBlank(tenantDomain)) {
            metaData[0] = CARBON_SUPER_TENANT;
        } else {
            metaData[0] = tenantDomain;
        }

        return metaData;
    }
}

