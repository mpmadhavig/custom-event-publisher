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

import java.util.Map;

import org.wso2.carbon.identity.event.event.Event;

public class UserEventData {

    private String eventType;
    private String userId;
    private String username;
    private String userStoreDomain;
    private String tenantDomain;
    private Map<String, String> claimsAdded;
    private Map<String, String> claimsRemoved;
    private Map<String, String> claimsUpdated;

    public UserEventData(String eventType, String userId, String username, String userStoreDomain, String tenantDomain, Map<String,
            String> claimsAdded, Map<String, String> claimsRemoved, Map<String, String> claimsUpdated) {

        this.eventType = eventType;
        this.userId = userId;
        this.username = username;
        this.userStoreDomain = userStoreDomain;
        this.tenantDomain = tenantDomain;
        this.claimsAdded = claimsAdded;
        this.claimsRemoved = claimsRemoved;
        this.claimsUpdated = claimsUpdated;
    }

    public static UserEventData builder(Event event) {

        Map<String, Object> eventProperties = event.getEventProperties();

        return new UserEventData(
                event.getEventName(),
                (String) eventProperties.get("USER_ID"),
                (String) eventProperties.get("user-name"),
                (String) eventProperties.get("userstore-domain"),
                (String) eventProperties.get("tenant-domain"),
                (Map<String, String>) eventProperties.get("USER_CLAIMS_ADDED"),
                (Map<String, String>) eventProperties.get("USER_CLAIMS_DELETED"),
                (Map<String, String>) eventProperties.get("USER_CLAIMS_MODIFIED"));
    }

    public String getEventType() {

        return eventType;
    }

    public void setEventType(String eventType) {

        this.eventType = eventType;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }


    public String getUserStoreDomain() {

        return userStoreDomain;
    }

    public void setUserStoreDomain(String userStoreDomain) {

        this.userStoreDomain = userStoreDomain;
    }

    public String getTenantDomain() {

        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {

        this.tenantDomain = tenantDomain;
    }

    public Map<String, String> getClaimsAdded() {

        return claimsAdded;
    }

    public void setClaimsAdded(Map<String, String> claimsAdded) {

        this.claimsAdded = claimsAdded;
    }

    public Map<String, String> getClaimsRemoved() {

        return claimsRemoved;
    }

    public void setClaimsRemoved(Map<String, String> claimsRemoved) {

        this.claimsRemoved = claimsRemoved;
    }

    public Map<String, String> getClaimsUpdated() {

        return claimsUpdated;
    }

    public void setClaimsUpdated(Map<String, String> claimsUpdated) {

        this.claimsUpdated = claimsUpdated;
    }
}
