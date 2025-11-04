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

package org.wso2.event.publisher.sample.publishers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.role.v2.mgt.core.model.Permission;
import org.wso2.event.publisher.sample.util.PublisherUtil;
import org.wso2.event.publisher.sample.models.RoleEventData;
import org.wso2.event.publisher.sample.models.RoleUpdateEventData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.wso2.carbon.identity.event.IdentityEventConstants.Event.*;
import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.*;
import static org.wso2.event.publisher.sample.util.CommonUtil.listToPayload;
import static org.wso2.event.publisher.sample.util.CommonUtil.selectStreamName;

public class RoleEventPublisher extends BaseEventPublisher {

    private static final Log LOG = LogFactory.getLog(RoleEventPublisher.class);

    private static final Set<String> ROLE_CREATE_UPDATE_DELETE_EVENTS = new HashSet<String>() {{
        add(POST_ADD_ROLE_V2_EVENT);
        add(POST_UPDATE_ROLE_V2_NAME_EVENT);
        add(POST_DELETE_ROLE_V2_EVENT);
    }};

    private static final Set<String> ROLE_UPDATE_EVENTS = new HashSet<String>() {{
        add(POST_UPDATE_USER_LIST_OF_ROLE_V2_EVENT);
        add(POST_UPDATE_GROUP_LIST_OF_ROLE_V2_EVENT);
        add(POST_UPDATE_IDP_GROUP_LIST_OF_ROLE_V2_EVENT);
        add(POST_UPDATE_PERMISSIONS_FOR_ROLE_V2_EVENT);
    }};

    public RoleEventPublisher() {
        super(ROLE_EVENT_PUBLISHER, createSupportedEvents());
    }

    private static Set<String> createSupportedEvents() {
        Set<String> events = new HashSet<>();
        events.addAll(ROLE_CREATE_UPDATE_DELETE_EVENTS);
        events.addAll(ROLE_UPDATE_EVENTS);
        return events;
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {
        String eventName = event.getEventName();

        if (ROLE_CREATE_UPDATE_DELETE_EVENTS.contains(eventName)) {
            handleRoleEvent(event);
        } else if (ROLE_UPDATE_EVENTS.contains(eventName)) {
            handleRoleUpdateEvent(event);
        } else {
            logUnsupportedEvent(eventName);
        }
    }

    private void handleRoleEvent(Event event) {
        RoleEventData roleEventData = RoleEventData.builder(event.getEventProperties(), event.getEventName());
        Object[] payloadData = createRoleEventPayload(roleEventData);
        String streamName = selectStreamName(roleEventData.getTenantDomain(),
                ROLE_STREAM_NAME, ORG_ROLE_STREAM_NAME);
        PublisherUtil.publishToAnalytics(payloadData, roleEventData.getTenantDomain(), streamName);
    }

    private void handleRoleUpdateEvent(Event event) {
        RoleUpdateEventData roleUpdateEventData =
                RoleUpdateEventData.builder(event.getEventProperties(), event.getEventName());
        Object[] payloadData = createRoleUpdateEventPayload(roleUpdateEventData);
        String streamName = selectStreamName(roleUpdateEventData.getTenantDomain(),
                ROLE_UPDATE_STREAM_NAME, ORG_ROLE_UPDATE_STREAM_NAME);
        PublisherUtil.publishToAnalytics(payloadData, roleUpdateEventData.getTenantDomain(), streamName);
    }


    private static Object[] createRoleEventPayload(RoleEventData roleEventData) {
        Object[] payloadData = new Object[10];

        payloadData[0] = "role_event_sample_key";
        payloadData[1] = roleEventData.getEventType();
        payloadData[2] = roleEventData.getRoleName();
        payloadData[3] = roleEventData.getRoleId();
        payloadData[4] = roleEventData.getAudienceId();
        payloadData[5] = roleEventData.getTenantDomain();
        payloadData[6] = roleEventData.getUserStoreDomain();
        payloadData[7] = listToPayload(roleEventData.getPermissions());
        payloadData[8] = listToPayload(roleEventData.getUserList());
        payloadData[9] = listToPayload(roleEventData.getGroupList());

        return payloadData;
    }

    private static Object[] createRoleUpdateEventPayload(RoleUpdateEventData roleEventData) {
        Object[] payloadData = new Object[13];

        payloadData[0] = "role_event_sample_key";
        payloadData[1] = roleEventData.getEventType();
        payloadData[2] = roleEventData.getRoleName();
        payloadData[3] = roleEventData.getRoleId();
        payloadData[4] = listToPayload(getPermissionList(roleEventData.getAddedPermissions()));
        payloadData[5] = listToPayload(getPermissionList(roleEventData.getDeletedPermissions()));
        payloadData[6] = listToPayload(roleEventData.getNewGroupIdList());
        payloadData[7] = listToPayload(roleEventData.getDeleteGroupIdList());
        payloadData[8] = listToPayload(roleEventData.getNewUserIdList());
        payloadData[9] = listToPayload(roleEventData.getDeleteUserIdList());
        payloadData[10] = roleEventData.getAudienceId();
        payloadData[11] = roleEventData.getTenantDomain();
        payloadData[12] = roleEventData.getUserStoreDomain();

        return payloadData;
    }

    private static List<String> getPermissionList(List<Permission> permissions) {

        List<String> permissionArray = new ArrayList<>();
        if (permissions == null || permissions.isEmpty()) {
            return permissionArray;
        }
        for (Permission item : permissions) {
            permissionArray.add(item.getName());
        }
        return permissionArray;
    }
}
