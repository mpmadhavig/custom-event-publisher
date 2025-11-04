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
import org.wso2.event.publisher.sample.util.PublisherUtil;
import org.wso2.event.publisher.sample.models.UserEventData;

import java.util.HashSet;
import java.util.Set;

import static org.wso2.carbon.identity.event.IdentityEventConstants.Event.*;
import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.*;
import static org.wso2.event.publisher.sample.util.CommonUtil.selectStreamName;

/**
 * Event publisher to publish user related events.
 */
public class UserEventPublisher extends BaseEventPublisher {

    private static final Log LOG = LogFactory.getLog(UserEventPublisher.class);

    private static final Set<String> USER_EVENTS = new HashSet<String>() {{
        add(POST_USER_PROFILE_UPDATE);
        add(PRE_DELETE_USER_WITH_ID);
        add(POST_UNLOCK_ACCOUNT);
        add(POST_LOCK_ACCOUNT);
        add(POST_DISABLE_ACCOUNT);
        add(POST_ENABLE_ACCOUNT);
        add(POST_ADD_USER);
    }};

    public UserEventPublisher() {
        super(USER_EVENT_PUBLISHER, USER_EVENTS);
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {
        if (USER_EVENTS.contains(event.getEventName())) {
            UserEventData userEventData = UserEventData.builder(event);
            Object[] payloadData = createUserEventPayload(userEventData);
            String streamName = selectStreamName(userEventData.getTenantDomain(),
                    USER_STREAM_NAME, ORG_USER_STREAM_NAME);
            PublisherUtil.publishToAnalytics(payloadData, userEventData.getTenantDomain(), streamName);
        } else {
            logUnsupportedEvent(event.getEventName());
        }
    }


    private static Object[] createUserEventPayload(UserEventData userEventData) {
        Object[] payloadData = new Object[9];

        payloadData[0] = "user_event_sample_key";
        payloadData[1] = userEventData.getEventType();
        payloadData[2] = userEventData.getUserId();
        payloadData[3] = userEventData.getUsername();
        payloadData[4] = userEventData.getUserStoreDomain();
        payloadData[5] = userEventData.getTenantDomain();

        // Convert maps to string representations
        if (userEventData.getClaimsAdded() != null && !userEventData.getClaimsAdded().isEmpty()) {
            payloadData[6] = userEventData.getClaimsAdded().toString();
        }
        if (userEventData.getClaimsRemoved() != null && !userEventData.getClaimsRemoved().isEmpty()) {
            payloadData[7] = userEventData.getClaimsRemoved().toString();
        }
        if (userEventData.getClaimsUpdated() != null && !userEventData.getClaimsUpdated().isEmpty()) {
            payloadData[8] = userEventData.getClaimsUpdated().toString();
        }

        return payloadData;
    }
}
