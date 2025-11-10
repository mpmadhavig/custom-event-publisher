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
import org.wso2.event.publisher.sample.util.CommonUtil;
import org.wso2.event.publisher.sample.util.PublisherUtil;
import org.wso2.event.publisher.sample.models.GroupEventData;
import org.wso2.event.publisher.sample.models.GroupUpdateEventData;

import java.util.HashSet;
import java.util.Set;

import static org.wso2.carbon.identity.event.IdentityEventConstants.Event.*;
import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.*;
import static org.wso2.event.publisher.sample.util.CommonUtil.*;

public class GroupEventPublisher extends BaseEventPublisher {

    private static final Log LOG = LogFactory.getLog(GroupEventPublisher.class);

    private static final Set<String> GROUP_CREATE_DELETE_EVENTS = new HashSet<String>() {{
        add(POST_ADD_ROLE);
        add(POST_DELETE_ROLE);
    }};

    private static final Set<String> GROUP_UPDATE_EVENTS = new HashSet<String>() {{
        add(POST_UPDATE_ROLE);
        add(POST_UPDATE_USER_LIST_OF_ROLE_EVENT);
        add(POST_UPDATE_USER_LIST_OF_ROLE);
    }};

    public GroupEventPublisher() {

        super(GROUP_EVENT_PUBLISHER, createSupportedEvents());
    }

    private static Set<String> createSupportedEvents() {

        Set<String> events = new HashSet<>();
        events.addAll(GROUP_CREATE_DELETE_EVENTS);
        events.addAll(GROUP_UPDATE_EVENTS);
        return events;
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        String eventName = event.getEventName();

        if (GROUP_CREATE_DELETE_EVENTS.contains(eventName)) {
            handleGroupCreateDeleteEvent(event);
        } else if (GROUP_UPDATE_EVENTS.contains(eventName)) {
            handleGroupUpdateEvent(event);
        } else {
            logUnsupportedEvent(eventName);
        }
    }

    private void handleGroupCreateDeleteEvent(Event event) {

        GroupEventData groupEventData = GroupEventData.builder(event.getEventProperties(), event.getEventName());
        CommonUtil.validateActiveTenant(groupEventData.getTenantDomain());
        Object[] payloadData = createGroupEventPayload(groupEventData);
        String streamName = selectStreamName(groupEventData.getTenantDomain(),
                GROUP_STREAM_NAME, ORG_GROUP_STREAM_NAME);
        PublisherUtil.publishToAnalytics(payloadData, groupEventData.getTenantDomain(), streamName);
    }

    private void handleGroupUpdateEvent(Event event) {

        GroupUpdateEventData groupUpdateEventData = GroupUpdateEventData.builder(
                event.getEventProperties(), event.getEventName());
        CommonUtil.validateActiveTenant(groupUpdateEventData.getTenantDomain());
        Object[] payloadData = createUpdateGroupEventPayload(groupUpdateEventData);
        String streamName = selectStreamName(groupUpdateEventData.getTenantDomain(),
                GROUP_UPDATE_STREAM_NAME, ORG_GROUP_UPDATE_STREAM_NAME);
        PublisherUtil.publishToAnalytics(payloadData, groupUpdateEventData.getTenantDomain(), streamName);
    }

    private static Object[] createGroupEventPayload(GroupEventData groupEventData) {

        Object[] payloadData = new Object[8];

        payloadData[0] = "group_event_sample_key";
        payloadData[1] = groupEventData.getEventType();
        payloadData[2] = groupEventData.getGroupName();
        payloadData[3] = groupEventData.getGroupId();
        payloadData[4] = groupEventData.getTenantDomain();
        payloadData[5] = groupEventData.getUserStoreDomain();
        payloadData[6] = listToPayload(groupEventData.getUserList());
        payloadData[7] = System.currentTimeMillis();

        return payloadData;
    }

    private static Object[] createUpdateGroupEventPayload(GroupUpdateEventData groupUpdateEventData) {

        Object[] payloadData = new Object[10];

        payloadData[0] = "group_update_event_sample_key";
        payloadData[1] = groupUpdateEventData.getEventType();
        payloadData[2] = groupUpdateEventData.getTenantDomain();
        payloadData[3] = groupUpdateEventData.getUserStoreDomain();
        payloadData[4] = groupUpdateEventData.getGroupId();
        payloadData[5] = groupUpdateEventData.getGroupName();
        payloadData[6] = groupUpdateEventData.getUpdatedGroupName();
        payloadData[7] = listToPayload(groupUpdateEventData.getAddedUsers());
        payloadData[8] = listToPayload(groupUpdateEventData.getRemovedUsers());
        payloadData[9] = System.currentTimeMillis();

        return payloadData;
    }
}
