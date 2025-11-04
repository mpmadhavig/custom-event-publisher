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

import java.util.List;
import java.util.Map;

public class RoleEventData {

    private String eventType;
    private String roleName;
    private String roleId;
    private String audienceId;
    private List<String> permissions;
    private List<String> groupList;
    private List<String> userList;
    private String tenantDomain;
    private String userStoreDomain;

    public RoleEventData(String eventType, String roleName, String roleId, String audienceId,
                         List<String> permissions, List<String> groupList, List<String> userList,
                         String tenantDomain, String userStoreDomain) {

        this.eventType = eventType;
        this.roleName = roleName;
        this.roleId = roleId;
        this.audienceId = audienceId;
        this.permissions = permissions;
        this.groupList = groupList;
        this.userList = userList;
        this.tenantDomain = tenantDomain;
        this.userStoreDomain = userStoreDomain;
    }

    public static RoleEventData builder(Map<String, Object> eventProperties, String eventType) {

        return new RoleEventData(
                eventType,
                (String) eventProperties.get("role-name"),
                (String) eventProperties.get("role-id"),
                (String) eventProperties.get("audienceId"),
                (List<String>) eventProperties.get("PERMISSIONS"),
                (List<String>) eventProperties.get("GROUP_LIST"),
                (List<String>) eventProperties.get("USER_LIST"),
                (String) eventProperties.get("tenant-domain"),
                (String) eventProperties.get("userstore-domain")
        );
    }

    public String getAudienceId() {

        return audienceId;
    }

    public void setAudienceId(String audienceId) {

        this.audienceId = audienceId;
    }

    public String getEventType() {

        return eventType;
    }

    public void setEventType(String eventType) {

        this.eventType = eventType;
    }

    public String getRoleName() {

        return roleName;
    }

    public void setRoleName(String roleName) {

        this.roleName = roleName;
    }

    public String getRoleId() {

        return roleId;
    }

    public void setRoleId(String roleId) {

        this.roleId = roleId;
    }

    public List<String> getPermissions() {

        return permissions;
    }

    public void setPermissions(List<String> permissions) {

        this.permissions = permissions;
    }

    public List<String> getGroupList() {

        return groupList;
    }

    public void setGroupList(List<String> groupList) {

        this.groupList = groupList;
    }

    public List<String> getUserList() {

        return userList;
    }

    public void setUserList(List<String> userList) {

        this.userList = userList;
    }

    public String getTenantDomain() {

        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {

        this.tenantDomain = tenantDomain;
    }

    public String getUserStoreDomain() {

        return userStoreDomain;
    }

    public void setUserStoreDomain(String userStoreDomain) {

        this.userStoreDomain = userStoreDomain;
    }
}
