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

package org.wso2.event.publisher.sample.models;

import org.wso2.carbon.identity.organization.management.service.model.PatchOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data model for organization patch events.
 */
public class OrgPatchEventData {

    private String eventType;
    private String organizationId;
    private List<PatchOperationData> patchOperations;

    public OrgPatchEventData(String eventType, String organizationId, List<PatchOperationData> patchOperations) {
        this.eventType = eventType;
        this.organizationId = organizationId;
        this.patchOperations = patchOperations;
    }

    /**
     * Build OrgPatchEventData from event properties.
     *
     * @param eventProperties Event properties map
     * @param eventType       Event type/name
     * @return OrgPatchEventData instance
     */
    public static OrgPatchEventData builder(Map<String, Object> eventProperties, String eventType) {
        String organizationId = (String) eventProperties.get("ORGANIZATION_ID");
        List<PatchOperation> patchOperations = (List<PatchOperation>) eventProperties.get("PATCH_OPERATIONS");

        List<PatchOperationData> patchOperationDataList = new ArrayList<>();
        if (patchOperations != null) {
            for (PatchOperation operation : patchOperations) {
                patchOperationDataList.add(new PatchOperationData(
                        operation.getOp(),
                        operation.getPath(),
                        operation.getValue()
                ));
            }
        }

        return new OrgPatchEventData(eventType, organizationId, patchOperationDataList);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<PatchOperationData> getPatchOperations() {
        return patchOperations;
    }

    public void setPatchOperations(List<PatchOperationData> patchOperations) {
        this.patchOperations = patchOperations;
    }

    /**
     * Inner class to represent patch operation data.
     */
    public static class PatchOperationData {
        private String op;
        private String path;
        private String value;

        public PatchOperationData(String op, String path, String value) {
            this.op = op;
            this.path = path;
            this.value = value;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{\"op\":\"" + op + "\",\"path\":\"" + path + "\",\"value\":\"" + value + "\"}";
        }
    }
}

