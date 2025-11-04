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
import org.wso2.carbon.identity.base.IdentityRuntimeException;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.event.publisher.sample.util.PublisherUtil;

import java.util.Set;

/**
 * Base event handler with common functionality.
 */
public abstract class BaseEventPublisher extends AbstractEventHandler {

    private static final Log LOG = LogFactory.getLog(BaseEventPublisher.class);

    private final String publisherName;
    private final Set<String> supportedEvents;

    protected BaseEventPublisher(String publisherName, Set<String> supportedEvents) {

        this.publisherName = publisherName;
        this.supportedEvents = supportedEvents;
    }

    @Override
    public String getName() {

        return publisherName;
    }

    @Override
    public boolean canHandle(MessageContext messageContext) throws IdentityRuntimeException {

        if (!PublisherUtil.isPublisherEnabled(publisherName, this.configs.getModuleProperties())) {
            return false;
        }
        return super.canHandle(messageContext);
    }

    /**
     * Check if the event is supported by this publisher.
     *
     * @param eventName Event name to check
     * @return true if supported, false otherwise
     */
    protected boolean isEventSupported(String eventName) {

        return supportedEvents.contains(eventName);
    }

    /**
     * Log error for unsupported events.
     *
     * @param eventName Event name that cannot be handled
     */
    protected void logUnsupportedEvent(String eventName) {

        LOG.error(String.format("Event '%s' cannot be handled by %s", eventName, publisherName));
    }
}

