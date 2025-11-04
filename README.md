# Custom Event Publisher Configuration

## Try Out Instructions

Follow these steps to deploy and configure the custom event publisher:

### Step 1: Build the Project

Build the project to generate the artifact:

```bash
mvn clean install
```

This will generate the JAR file: `target/org.wso2.event.publisher.sample-1.0.0.jar`

### Step 2: Deploy the Artifact

Copy the generated JAR file to the WSO2 Identity Server dropins folder:

```bash
cp target/org.wso2.event.publisher.sample-1.0.0.jar <IS_HOME>/repository/components/dropins/
```

### Step 3: Deploy Event Streams

Copy all event stream definitions to the Identity Server:

```bash
cp <REPO_ROOT>/src/main/resources/streams/* <IS_HOME>/repository/deployment/server/eventstreams/
```

This includes the following stream definitions:
- `org.wso2.is.analytics.stream.GroupEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.GroupUpdateEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgDeleteEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgGroupUpdateEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgPatchEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgRoleEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgRoleUpdateEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.OrgUserEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.RoleEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.RoleUpdateEventData_1.0.0.json`
- `org.wso2.is.analytics.stream.UserEventData_1.0.0.json`

### Step 4: Deploy Event Publishers

Copy all event publisher configurations to the Identity Server:

```bash
cp <REPO_ROOT>/src/main/resources/publishers/* <IS_HOME>/repository/deployment/server/eventpublishers/
```

This includes the following publisher configurations:
- `IsAnalytics-Publisher-http-GroupEventData.xml`
- `IsAnalytics-Publisher-http-GroupUpdateEventData.xml`
- `IsAnalytics-Publisher-http-OrgDeleteEventData.xml`
- `IsAnalytics-Publisher-http-OrgEventData.xml`
- `IsAnalytics-Publisher-http-OrgGroupUpdateEventData.xml`
- `IsAnalytics-Publisher-http-OrgPatchEventData.xml`
- `IsAnalytics-Publisher-http-OrgRoleEventData.xml`
- `IsAnalytics-Publisher-http-OrgRoleUpdateEventData.xml`
- `IsAnalytics-Publisher-http-OrgUserEventData.xml`
- `IsAnalytics-Publisher-http-RoleEventData.xml`
- `IsAnalytics-Publisher-http-RoleUpdateEventData.xml`
- `IsAnalytics-Publisher-http-UserEventData.xml`

### Step 5: Configure deployment.toml

Add the event handler configurations to `<IS_HOME>/repository/conf/deployment.toml`

```toml
[[event_handler]]
name = "userEventPublisher"
subscriptions = [
    "POST_USER_PROFILE_UPDATE",
    "PRE_DELETE_USER_WITH_ID",
    "POST_UNLOCK_ACCOUNT",
    "POST_LOCK_ACCOUNT",
    "POST_DISABLE_ACCOUNT",
    "POST_ENABLE_ACCOUNT",
    "POST_ADD_USER"
]
properties.enable = true

[[event_handler]]
name = "roleEventPublisher"
subscriptions = [
    "POST_ADD_ROLE_V2_EVENT",
    "POST_UPDATE_ROLE_V2_NAME_EVENT",
    "POST_DELETE_ROLE_V2_EVENT",
    "POST_UPDATE_USER_LIST_OF_ROLE_V2_EVENT",
    "POST_UPDATE_GROUP_LIST_OF_ROLE_V2_EVENT",
    "POST_UPDATE_IDP_GROUP_LIST_OF_ROLE_V2_EVENT",
    "POST_UPDATE_PERMISSIONS_FOR_ROLE_V2_EVENT"
]
properties.enable = true

[[event_handler]]
name = "groupEventPublisher"
subscriptions = [
    "POST_ADD_ROLE",
    "POST_DELETE_ROLE",
    "POST_UPDATE_ROLE",
    "POST_UPDATE_ROLE_NAME_EVENT",
    "POST_UPDATE_USER_LIST_OF_ROLE_EVENT",
    "POST_UPDATE_USER_LIST_OF_ROLE"
]
properties.enable = true

[[event_handler]]
name = "orgEventPublisher"
subscriptions = [
    "POST_ADD_ORGANIZATION",
    "POST_UPDATE_ORGANIZATION",
    "POST_PATCH_ORGANIZATION",
    "POST_DELETE_ORGANIZATION"
]
properties.enable = true
```

### Step 6: Restart the Server

Restart the WSO2 Identity Server.

---

## Documentation

For detailed information about the event streams and their data fields, see the [Event Stream Data Reference](EVENT_STREAM_REFERENCE.md).
