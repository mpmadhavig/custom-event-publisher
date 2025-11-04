# Event Stream Data Reference

This document provides a comprehensive reference for all event streams and the data fields published in each stream.

- [User Events](#user-events)
    - [UserEventData](#usereventdata)
- [Group Events](#group-events)
    - [GroupEventData](#groupeventdata)
    - [GroupUpdateEventData](#groupupdateeventdata)
- [Role Events](#role-events)
    - [RoleEventData](#roleeventdata)
    - [RoleUpdateEventData](#roleupdateeventdata)
- [Organization Events](#organization-events)
    - [OrgEventData](#orgeventdata)
    - [OrgDeleteEventData](#orgdeleteeventdata)
    - [OrgPatchEventData](#orgpatcheventdata)
    - [OrgUserEventData](#orgusereventdata)
    - [OrgGroupUpdateEventData](#orggroupupdateeventdata)
    - [OrgRoleEventData](#orgroleeventdata)
    - [OrgRoleUpdateEventData](#orgroleupdateeventdata)

---

## User Events

### UserEventData

**Stream Name:** `org.wso2.is.analytics.stream.UserEventData`  
**Version:** 1.0.0  
**Description:** Stream for user-related events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `tenantDomain` | STRING | The tenant domain context |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of user event (e.g., create, update, delete) |
| `userId` | STRING | Unique identifier for the user |
| `username` | STRING | Username of the user |
| `userStoreDomain` | STRING | User store domain |
| `tenantDomain` | STRING | Tenant domain |
| `claimsAdded` | STRING | Claims added to the user |
| `claimsRemoved` | STRING | Claims removed from the user |
| `claimsUpdated` | STRING | Claims updated for the user |
| `timestamp` | LONG | Event timestamp |

---

## Group Events

### GroupEventData

**Stream Name:** `org.wso2.is.analytics.stream.GroupEventData`  
**Version:** 1.0.0  
**Description:** Stream for group-related events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `tenantDomain` | STRING | The tenant domain context |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of group event (e.g., create, delete) |
| `groupName` | STRING | Name of the group |
| `groupID` | STRING | Unique identifier for the group |
| `tenantDomain` | STRING | Tenant domain |
| `userStoreDomain` | STRING | User store domain |
| `userList` | STRING | List of users in the group |
| `timestamp` | LONG | Event timestamp |

---

### GroupUpdateEventData

**Stream Name:** `org.wso2.is.analytics.stream.GroupUpdateEventData`  
**Version:** 1.0.0  
**Description:** Stream for group update events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `tenantDomain` | STRING | The tenant domain context |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of group update event |
| `tenantDomain` | STRING | Tenant domain |
| `userStoreDomain` | STRING | User store domain |
| `groupID` | STRING | Unique identifier for the group |
| `groupName` | STRING | Current name of the group |
| `updatedGroupName` | STRING | New name of the group (if renamed) |
| `addedUsers` | STRING | Users added to the group |
| `removedUsers` | STRING | Users removed from the group |
| `timestamp` | LONG | Event timestamp |

---

## Role Events

### RoleEventData

**Stream Name:** `org.wso2.is.analytics.stream.RoleEventData`  
**Version:** 1.0.0  
**Description:** Stream for role-related events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `tenantDomain` | STRING | The tenant domain context |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of role event (e.g., create, delete) |
| `roleName` | STRING | Name of the role |
| `roleId` | STRING | Unique identifier for the role |
| `audienceId` | STRING | Audience identifier for the role |
| `tenantDomain` | STRING | Tenant domain |
| `userStoreDomain` | STRING | User store domain |
| `permissions` | STRING | Permissions assigned to the role |
| `userList` | STRING | List of users assigned to the role |
| `groupList` | STRING | List of groups assigned to the role |
| `timestamp` | LONG | Event timestamp |

---

### RoleUpdateEventData

**Stream Name:** `org.wso2.is.analytics.stream.RoleUpdateEventData`  
**Version:** 1.0.0  
**Description:** Stream for role update events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `tenantDomain` | STRING | The tenant domain context |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of role update event |
| `roleName` | STRING | Name of the role |
| `roleId` | STRING | Unique identifier for the role |
| `addedPermissions` | STRING | Permissions added to the role |
| `deletedPermissions` | STRING | Permissions removed from the role |
| `newGroupIdList` | STRING | Groups added to the role |
| `deleteGroupIdList` | STRING | Groups removed from the role |
| `newUserIdList` | STRING | Users added to the role |
| `deleteUserIdList` | STRING | Users removed from the role |
| `audienceId` | STRING | Audience identifier for the role |
| `tenantDomain` | STRING | Tenant domain |
| `userStoreDomain` | STRING | User store domain |
| `timestamp` | LONG | Event timestamp |

---

## Organization Events

### OrgEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization-related events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `tenantDomain` | STRING | The tenant domain context |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of organization event (e.g., create) |
| `orgId` | STRING | Unique identifier for the organization |
| `orgName` | STRING | Name of the organization |
| `version` | STRING | Version of the organization |
| `description` | STRING | Description of the organization |
| `status` | STRING | Status of the organization |
| `creatorId` | STRING | ID of the user who created the organization |
| `creatorUsername` | STRING | Username of the organization creator |
| `creatorEmail` | STRING | Email of the organization creator |
| `parentOrgId` | STRING | Parent organization ID (for sub-organizations) |
| `organizationHandle` | STRING | Unique handle for the organization |
| `timestamp` | LONG | Event timestamp |

---

### OrgDeleteEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgDeleteEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization delete events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `orgName` | STRING | Name of the organization |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of event (delete) |
| `organizationId` | STRING | Unique identifier for the deleted organization |
| `timestamp` | LONG | Event timestamp |

---

### OrgPatchEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgPatchEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization patch events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `orgName` | STRING | Name of the organization |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of event (patch) |
| `organizationId` | STRING | Unique identifier for the organization |
| `patchOperations` | STRING | JSON string containing patch operations |
| `timestamp` | LONG | Event timestamp |

---

### OrgUserEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgUserEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization user events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `orgName` | STRING | Name of the organization |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of user event in organization context |
| `userId` | STRING | Unique identifier for the user |
| `username` | STRING | Username of the user |
| `userStoreDomain` | STRING | User store domain |
| `orgName` | STRING | Organization name |
| `claimsAdded` | STRING | Claims added to the user |
| `claimsRemoved` | STRING | Claims removed from the user |
| `claimsUpdated` | STRING | Claims updated for the user |
| `timestamp` | LONG | Event timestamp |

---

### OrgGroupUpdateEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgGroupUpdateEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization group update events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `orgName` | STRING | Name of the organization |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of group update event in organization context |
| `orgName` | STRING | Organization name |
| `userStoreDomain` | STRING | User store domain |
| `groupID` | STRING | Unique identifier for the group |
| `groupName` | STRING | Current name of the group |
| `updatedGroupName` | STRING | New name of the group (if renamed) |
| `addedUsers` | STRING | Users added to the group |
| `removedUsers` | STRING | Users removed from the group |
| `timestamp` | LONG | Event timestamp |

---

### OrgRoleEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgRoleEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization role events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `orgName` | STRING | Name of the organization |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of role event in organization context |
| `roleName` | STRING | Name of the role |
| `roleId` | STRING | Unique identifier for the role |
| `audienceId` | STRING | Audience identifier for the role |
| `orgName` | STRING | Organization name |
| `userStoreDomain` | STRING | User store domain |
| `permissions` | STRING | Permissions assigned to the role |
| `userList` | STRING | List of users assigned to the role |
| `groupList` | STRING | List of groups assigned to the role |
| `timestamp` | LONG | Event timestamp |

---

### OrgRoleUpdateEventData

**Stream Name:** `org.wso2.is.analytics.stream.OrgRoleUpdateEventData`  
**Version:** 1.0.0  
**Description:** Stream for organization role update events

#### Metadata Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `orgName` | STRING | Name of the organization |

#### Payload Fields

| Field Name | Type | Description |
|------------|------|-------------|
| `apiKey` | STRING | API key for authentication |
| `eventType` | STRING | Type of role update event in organization context |
| `roleName` | STRING | Name of the role |
| `roleId` | STRING | Unique identifier for the role |
| `addedPermissions` | STRING | Permissions added to the role |
| `deletedPermissions` | STRING | Permissions removed from the role |
| `newGroupIdList` | STRING | Groups added to the role |
| `deleteGroupIdList` | STRING | Groups removed from the role |
| `newUserIdList` | STRING | Users added to the role |
| `deleteUserIdList` | STRING | Users removed from the role |
| `audienceId` | STRING | Audience identifier for the role |
| `orgName` | STRING | Organization name |
| `userStoreDomain` | STRING | User store domain |
| `timestamp` | LONG | Event timestamp |

---

## Common Field Patterns

### Metadata vs Payload

- **Metadata Fields**: Contextual information used for routing and filtering (e.g., `tenantDomain`, `orgName`)
- **Payload Fields**: Actual event data containing business information

### Common Field Types

- **STRING**: Text data, including JSON-formatted strings for complex objects
- **LONG**: Numeric timestamp values

### Field Naming Conventions

- **ID Fields**: Suffix with `Id` or `ID` (e.g., `userId`, `groupID`)
- **Lists**: Suffix with `List` (e.g., `userList`, `groupList`)
- **Delta Fields**: Prefix with action verb (e.g., `addedUsers`, `removedUsers`, `newGroupIdList`, `deleteGroupIdList`)

---

## Usage Notes

1. **Timestamps**: All streams include a `timestamp` field of type LONG representing the event occurrence time
2. **API Key**: All streams include an `apiKey` field for authentication and authorization
3. **Event Type**: All streams include an `eventType` field to distinguish between different operations (create, update, delete, etc.)
4. **String Lists**: Fields like `userList`, `groupList`, `permissions`, etc. are STRING fields that may contain JSON-formatted arrays
5. **Claims**: User-related streams include three claim fields: `claimsAdded`, `claimsRemoved`, and `claimsUpdated` to track claim modifications

