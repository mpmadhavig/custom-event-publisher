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

package org.wso2.event.publisher.sample.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkUtils;
import org.wso2.carbon.identity.data.publisher.application.authentication.AuthnDataPublisherUtils;
import org.wso2.event.publisher.sample.internal.CustomEventPublisherDataHolder;

import java.util.Properties;

/**
 * Utility class for payload building and event publishing.
 */
public class PublisherUtil {

    private static final Log LOG = LogFactory.getLog(PublisherUtil.class);

    private PublisherUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Publish event data to analytics.
     *
     * @param payloadData      Payload data array
     * @param publishingDomain Publishing domain
     * @param streamName       Stream name
     */
    public static void publishToAnalytics(Object[] payloadData, String publishingDomain, String streamName) {

        try {
            FrameworkUtils.startTenantFlow(publishingDomain);
            org.wso2.carbon.databridge.commons.Event event = new org.wso2.carbon.databridge.commons
                    .Event(streamName, System.currentTimeMillis(), new Object[0], null, payloadData);
            CustomEventPublisherDataHolder.getInstance().getPublisherService().publish(event);

            if (LOG.isDebugEnabled()) {
                LOG.debug(
                        String.format("Published event to stream '%s' for domain '%s'", streamName, publishingDomain));
            }
        } finally {
            FrameworkUtils.endTenantFlow();
        }
    }

    /**
     * Check if a specific event publisher is enabled.
     *
     * @param eventPublisherName Event publisher name
     * @param moduleProperties   Module properties
     * @return true if enabled, false otherwise
     */
    public static boolean isPublisherEnabled(String eventPublisherName, Properties moduleProperties) {

        if (moduleProperties != null) {
            String handlerEnabled = moduleProperties.getProperty(eventPublisherName + ".enable");
            return Boolean.parseBoolean(handlerEnabled);
        }
        return false;
    }
}

