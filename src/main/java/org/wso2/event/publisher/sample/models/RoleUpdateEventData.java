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

import org.wso2.carbon.identity.role.v2.mgt.core.model.Permission;

import java.util.List;
import java.util.Map;

public class RoleUpdateEventData {

    private String eventType;
    private String roleName;
    private String roleId;
    private String audienceId;
    private List<Permission> addedPermissions;
    private List<Permission> deletedPermissions;
    private List<String> newGroupIdList;
    private List<String> deleteGroupIdList;
    private List<String> newUserIdList;
    private List<String> deleteUserIdList;
    private String tenantDomain;
    private String userStoreDomain;

    public RoleUpdateEventData(String eventType, String roleName, String roleId, String audienceId,
                               List<Permission> addedPermissions, List<Permission> deletedPermissions,
                               List<String> newGroupIdList, List<String> deleteGroupIdList,
                               List<String> newUserIdList, List<String> deleteUserIdList,
                               String tenantDomain, String userStoreDomain) {

        this.eventType = eventType;
        this.roleName = roleName;
        this.roleId = roleId;
        this.audienceId = audienceId;
        this.addedPermissions = addedPermissions;
        this.deletedPermissions = deletedPermissions;
        this.deleteGroupIdList = deleteGroupIdList;
        this.newUserIdList = newUserIdList;
        this.newGroupIdList = newGroupIdList;
        this.deleteUserIdList = deleteUserIdList;
        this.tenantDomain = tenantDomain;
        this.userStoreDomain = userStoreDomain;
    }

    public static RoleUpdateEventData builder(Map<String, Object> eventProperties, String eventType) {

        return new RoleUpdateEventData(
                eventType,
                (String) eventProperties.get("role-name"),
                (String) eventProperties.get("role-id"),
                (String) eventProperties.get("audienceId"),
                (List<Permission>) eventProperties.get("ADDED_PERMISSIONS"),
                (List<Permission>) eventProperties.get("DELETED_PERMISSIONS"),
                (List<String>) eventProperties.get("NEW_GROUP_ID_LIST"),
                (List<String>) eventProperties.get("DELETE_GROUP_ID_LIST"),
                (List<String>) eventProperties.get("NEW_USER_ID_LIST"),
                (List<String>) eventProperties.get("DELETE_USER_ID_LIST"),
                (String) eventProperties.get("tenant-domain"),
                (String) eventProperties.get("userstore-domain")
        );
    }

    public String getEventType() {

        return eventType;
    }

    public String getRoleId() {

        return roleId;
    }

    public String getRoleName() {

        return roleName;
    }

    public String getAudienceId() {

        return audienceId;
    }

    public List<Permission> getAddedPermissions() {

        return addedPermissions;
    }

    public List<Permission> getDeletedPermissions() {

        return deletedPermissions;
    }

    public List<String> getNewGroupIdList() {

        return newGroupIdList;
    }

    public List<String> getDeleteGroupIdList() {

        return deleteGroupIdList;
    }

    public List<String> getNewUserIdList() {

        return newUserIdList;
    }

    public List<String> getDeleteUserIdList() {

        return deleteUserIdList;
    }


    public String getTenantDomain() {

        return tenantDomain;
    }

    public String getUserStoreDomain() {

        return userStoreDomain;
    }
}
