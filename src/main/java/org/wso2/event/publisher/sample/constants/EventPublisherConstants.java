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

package org.wso2.event.publisher.sample.constants;

/**
 * Constants for event publishers.
 */
public class EventPublisherConstants {

    public static final String CARBON_SUPER_TENANT = "carbon.super";

    // Publisher names
    public static final String USER_EVENT_PUBLISHER = "userEventPublisher";
    public static final String GROUP_EVENT_PUBLISHER = "groupEventPublisher";
    public static final String ROLE_EVENT_PUBLISHER = "roleEventPublisher";
    public static final String ORG_EVENT_PUBLISHER = "orgEventPublisher";

    // Stream names
    public static final String ORG_STREAM_NAME = "org.wso2.is.analytics.stream.OrgEventData:1.0.0";
    public static final String ORG_DELETE_STREAM_NAME = "org.wso2.is.analytics.stream.OrgDeleteEventData:1.0.0";
    public static final String ORG_PATCH_STREAM_NAME = "org.wso2.is.analytics.stream.OrgPatchEventData:1.0.0";
    public static final String USER_STREAM_NAME = "org.wso2.is.analytics.stream.UserEventData:1.0.0";
    public static final String ORG_USER_STREAM_NAME = "org.wso2.is.analytics.stream.OrgUserEventData:1.0.0";
    public static final String GROUP_STREAM_NAME = "org.wso2.is.analytics.stream.GroupEventData:1.0.0";
    public static final String ORG_GROUP_STREAM_NAME = "org.wso2.is.analytics.stream.OrgGroupEventData:1.0.0";
    public static final String GROUP_UPDATE_STREAM_NAME = "org.wso2.is.analytics.stream.GroupUpdateEventData:1.0.0";
    public static final String ORG_GROUP_UPDATE_STREAM_NAME = "org.wso2.is.analytics.stream.OrgGroupUpdateEventData:1.0.0";
    public static final String ROLE_STREAM_NAME = "org.wso2.is.analytics.stream.RoleEventData:1.0.0";
    public static final String ORG_ROLE_STREAM_NAME = "org.wso2.is.analytics.stream.OrgRoleEventData:1.0.0";
    public static final String ROLE_UPDATE_STREAM_NAME = "org.wso2.is.analytics.stream.RoleUpdateEventData:1.0.0";
    public static final String ORG_ROLE_UPDATE_STREAM_NAME = "org.wso2.is.analytics.stream.OrgRoleUpdateEventData:1.0.0";

    private EventPublisherConstants() {
        // Private constructor to prevent instantiation
    }
}

