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
import org.wso2.event.publisher.sample.models.OrgEventData;
import org.wso2.event.publisher.sample.models.OrgPatchEventData;
import org.wso2.event.publisher.sample.util.PublisherUtil;

import java.util.HashSet;
import java.util.Set;

import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.ORG_DELETE_STREAM_NAME;
import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.ORG_EVENT_PUBLISHER;
import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.ORG_PATCH_STREAM_NAME;
import static org.wso2.event.publisher.sample.constants.EventPublisherConstants.ORG_STREAM_NAME;

/**
 * Event publisher to publish organization related events.
 */
public class OrgEventPublisher extends BaseEventPublisher {

    private static final Log LOG = LogFactory.getLog(OrgEventPublisher.class);

    private static final Set<String> ORG_EVENTS = new HashSet<String>() {{
        add("POST_ADD_ORGANIZATION");
        add("POST_PATCH_ORGANIZATION");
        add("POST_DELETE_ORGANIZATION");
    }};
    private static final String POST_ADD_ORGANIZATION = "POST_ADD_ORGANIZATION";
    private static final String POST_DELETE_ORGANIZATION = "POST_DELETE_ORGANIZATION";
    private static final String POST_PATCH_ORGANIZATION = "POST_PATCH_ORGANIZATION";

    public OrgEventPublisher() {

        super(ORG_EVENT_PUBLISHER, ORG_EVENTS);
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        String eventName = event.getEventName();

        if (POST_DELETE_ORGANIZATION.equals(eventName)) {
            handleOrgDeleteEvent(event);
        } else if (POST_PATCH_ORGANIZATION.equals(eventName)) {
            handleOrgPatchEvent(event);
        } else if (POST_ADD_ORGANIZATION.equals(eventName)) {
            handleAddOrgEvent(event);
        } else {
            logUnsupportedEvent(eventName);
        }
    }

    private void handleAddOrgEvent(Event event) {

        OrgEventData orgEventData = OrgEventData.builder(event.getEventProperties(), event.getEventName());
        Object[] payloadData = createOrgEventPayload(orgEventData);
        String publishingDomain = orgEventData.getOrganizationHandle() != null ?
                orgEventData.getOrganizationHandle() : "carbon.super";
        PublisherUtil.publishToAnalytics(payloadData, publishingDomain, ORG_STREAM_NAME);

        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Published organization event: %s for org: %s",
                    event.getEventName(), orgEventData.getOrganizationHandle()));
        }
    }

    private void handleOrgDeleteEvent(Event event) {

        String organizationId = (String) event.getEventProperties().get("ORGANIZATION_ID");
        Object[] payloadData = createOrgDeleteEventPayload(event.getEventName(), organizationId);
        PublisherUtil.publishToAnalytics(payloadData, "carbon.super", ORG_DELETE_STREAM_NAME);

        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Published organization delete event for org ID: %s", organizationId));
        }
    }

    private void handleOrgPatchEvent(Event event) {

        OrgPatchEventData orgPatchEventData =
                OrgPatchEventData.builder(event.getEventProperties(), event.getEventName());
        Object[] payloadData = createOrgPatchEventPayload(orgPatchEventData);
        PublisherUtil.publishToAnalytics(payloadData, "carbon.super", ORG_PATCH_STREAM_NAME);

        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Published organization patch event for org ID: %s with %d operations",
                    orgPatchEventData.getOrganizationId(),
                    orgPatchEventData.getPatchOperations() != null ? orgPatchEventData.getPatchOperations().size() :
                            0));
        }
    }

    private static Object[] createOrgEventPayload(OrgEventData orgEventData) {

        Object[] payloadData = new Object[12];

        payloadData[0] = "org_event_sample_key";
        payloadData[1] = orgEventData.getEventType();
        payloadData[2] = orgEventData.getOrgId();
        payloadData[3] = orgEventData.getOrgName();
        payloadData[4] = orgEventData.getVersion();
        payloadData[5] = orgEventData.getDescription();
        payloadData[6] = orgEventData.getStatus();
        payloadData[7] = orgEventData.getCreatorId();
        payloadData[8] = orgEventData.getCreatorUsername();
        payloadData[9] = orgEventData.getCreatorEmail();
        payloadData[10] = orgEventData.getParentOrgId();
        payloadData[11] = orgEventData.getOrganizationHandle();

        return payloadData;
    }

    private static Object[] createOrgDeleteEventPayload(String eventType, String organizationId) {

        Object[] payloadData = new Object[3];

        payloadData[0] = "org_delete_event_sample_key";
        payloadData[1] = eventType;
        payloadData[2] = organizationId;

        return payloadData;
    }

    private static Object[] createOrgPatchEventPayload(OrgPatchEventData orgPatchEventData) {

        Object[] payloadData = new Object[4];
        payloadData[0] = "org_patch_event_sample_key";
        payloadData[1] = orgPatchEventData.getEventType();
        payloadData[2] = orgPatchEventData.getOrganizationId();

        if (orgPatchEventData.getPatchOperations() != null && !orgPatchEventData.getPatchOperations().isEmpty()) {
            StringBuilder patchOpsJson = new StringBuilder("[");
            for (int i = 0; i < orgPatchEventData.getPatchOperations().size(); i++) {
                if (i > 0) {
                    patchOpsJson.append(",");
                }
                patchOpsJson.append(orgPatchEventData.getPatchOperations().get(i).toString());
            }
            patchOpsJson.append("]");
            payloadData[3] = patchOpsJson;
        } else {
            payloadData[3] = null;
        }

        return payloadData;
    }
}
