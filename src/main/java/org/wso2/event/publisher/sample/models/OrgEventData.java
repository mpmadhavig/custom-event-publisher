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

import org.wso2.carbon.identity.organization.management.service.model.ParentOrganizationDO;
import org.wso2.carbon.identity.organization.management.service.model.TenantTypeOrganization;

import java.util.Map;

/**
 * Data model for organization events.
 */
public class OrgEventData {

    private String eventType;
    private String orgId;
    private String orgName;
    private String version;
    private String description;
    private String status;
    private String creatorId;
    private String creatorUsername;
    private String creatorEmail;
    private String parentOrgId;
    private String organizationHandle;

    public OrgEventData(String eventType, String orgId, String orgName, String version, String description,
                        String status, String creatorId, String creatorUsername, String creatorEmail,
                        String parentOrgId, String organizationHandle) {

        this.eventType = eventType;
        this.orgId = orgId;
        this.orgName = orgName;
        this.version = version;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.creatorEmail = creatorEmail;
        this.parentOrgId = parentOrgId;
        this.organizationHandle = organizationHandle;
    }

    /**
     * Build OrgEventData from event properties.
     * The event property contains an "ORGANIZATION" object with organization details.
     *
     * @param eventProperties Event properties map
     * @param eventType       Event type/name
     * @return OrgEventData instance
     */
    public static OrgEventData builder(Map<String, Object> eventProperties, String eventType) {

        TenantTypeOrganization organization = (TenantTypeOrganization) eventProperties.get("ORGANIZATION");

        if (organization == null) {
            return new OrgEventData(eventType, null, null, null, null, null, null, null, null, null, null);
        }

        String parentOrgId = null;
        ParentOrganizationDO parent = organization.getParent();
        if (parent != null) {
            parentOrgId = parent.getId();
        }

        return new OrgEventData(
                eventType,
                organization.getId(),
                organization.getName(),
                organization.getVersion(),
                organization.getDescription(),
                organization.getStatus(),
                organization.getCreatorId(),
                organization.getCreatorUsername(),
                organization.getCreatorEmail(),
                parentOrgId,
                organization.getOrganizationHandle()
        );
    }

    public String getEventType() {

        return eventType;
    }

    public void setEventType(String eventType) {

        this.eventType = eventType;
    }

    public String getOrgId() {

        return orgId;
    }

    public void setOrgId(String orgId) {

        this.orgId = orgId;
    }

    public String getOrgName() {

        return orgName;
    }

    public void setOrgName(String orgName) {

        this.orgName = orgName;
    }

    public String getVersion() {

        return version;
    }

    public void setVersion(String version) {

        this.version = version;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getCreatorId() {

        return creatorId;
    }

    public void setCreatorId(String creatorId) {

        this.creatorId = creatorId;
    }

    public String getCreatorUsername() {

        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {

        this.creatorUsername = creatorUsername;
    }

    public String getCreatorEmail() {

        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {

        this.creatorEmail = creatorEmail;
    }

    public String getParentOrgId() {

        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {

        this.parentOrgId = parentOrgId;
    }

    public String getOrganizationHandle() {

        return organizationHandle;
    }

    public void setOrganizationHandle(String organizationHandle) {

        this.organizationHandle = organizationHandle;
    }
}
