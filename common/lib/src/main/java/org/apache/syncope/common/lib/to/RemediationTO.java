/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.common.lib.to;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.syncope.common.lib.BaseBean;
import org.apache.syncope.common.lib.patch.AnyPatch;
import org.apache.syncope.common.lib.types.ResourceOperation;

@XmlRootElement(name = "remediation")
@XmlType
public class RemediationTO extends BaseBean implements EntityTO {

    private static final long serialVersionUID = 3983540425142284975L;

    private String key;

    private String anyType;

    private ResourceOperation operation;

    private AnyTO anyTOPayload;

    private AnyPatch anyPatchPayload;

    private String keyPayload;

    private String error;

    private Date instant;

    private String pullTask;

    private String resource;

    private String remoteName;

    @Override
    public String getKey() {
        return key;
    }

    @PathParam("key")
    @Override
    public void setKey(final String key) {
        this.key = key;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String getAnyType() {
        return anyType;
    }

    public void setAnyType(final String anyType) {
        this.anyType = anyType;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public ResourceOperation getOperation() {
        return operation;
    }

    public void setOperation(final ResourceOperation operation) {
        this.operation = operation;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public AnyTO getAnyTOPayload() {
        return anyTOPayload;
    }

    public void setAnyTOPayload(final AnyTO anyTOPayload) {
        this.anyTOPayload = anyTOPayload;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public AnyPatch getAnyPatchPayload() {
        return anyPatchPayload;
    }

    public void setAnyPatchPayload(final AnyPatch anyPatchPayload) {
        this.anyPatchPayload = anyPatchPayload;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String getKeyPayload() {
        return keyPayload;
    }

    public void setKeyPayload(final String keyPayload) {
        this.keyPayload = keyPayload;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public Date getInstant() {
        return instant == null
                ? null
                : new Date(instant.getTime());
    }

    public void setInstant(final Date instant) {
        this.instant = instant == null
                ? null
                : new Date(instant.getTime());
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String getPullTask() {
        return pullTask;
    }

    public void setPullTask(final String pullTask) {
        this.pullTask = pullTask;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String getResource() {
        return resource;
    }

    public void setResource(final String resource) {
        this.resource = resource;
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)

    public String getRemoteName() {
        return remoteName;
    }

    public void setRemoteName(final String remoteName) {
        this.remoteName = remoteName;
    }
}
