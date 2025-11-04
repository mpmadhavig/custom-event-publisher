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

import org.wso2.event.publisher.sample.util.CommonUtil;

import java.util.List;
import java.util.Map;

public class GroupEventData {

    private String eventType;
    private String tenantDomain;
    private String userStoreDomain;
    private String groupName;
    private String groupId;
    private List<String> userList;

    public GroupEventData(String eventType, String tenantDomain, String userStoreDomain, String groupName, String groupId,
                          List<String> userList) {

        this.eventType = eventType;
        this.tenantDomain = tenantDomain;
        this.userStoreDomain = userStoreDomain;
        this.groupName = groupName;
        this.groupId = groupId;
        this.userList = userList;
    }

    public static GroupEventData builder(Map<String, Object> eventProperties, String eventType) {

        return new GroupEventData(
                eventType,
                (String) eventProperties.get("tenant-domain"),
                (String) eventProperties.get("userstore-domain"),
                (String) eventProperties.get("role-name"),
                (String) eventProperties.get("role-id"),
                CommonUtil.convertToArrayList((String[]) eventProperties.get("USER_LIST"))
        );
    }

    public String getEventType() {

        return eventType;
    }


    public String getTenantDomain() {

        return tenantDomain;
    }

    public String getGroupName() {

        return groupName;
    }

    public String getGroupId() {

        return groupId;
    }

    public List<String> getUserList() {

        return userList;
    }

    public String getUserStoreDomain() {

        return userStoreDomain;
    }
}
