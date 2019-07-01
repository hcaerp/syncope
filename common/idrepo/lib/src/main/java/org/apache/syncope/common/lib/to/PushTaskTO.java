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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.syncope.common.lib.jaxb.XmlGenericMapAdapter;

@XmlRootElement(name = "pushTask")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(allOf = { ProvisioningTaskTO.class })
public class PushTaskTO extends ProvisioningTaskTO {

    private static final long serialVersionUID = -2143537546915809018L;

    @JsonProperty(required = true)
    @XmlElement(required = true)
    private String sourceRealm;

    @XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
    private final Map<String, String> filters = new HashMap<>();

    @XmlTransient
    @JsonProperty("@class")
    @Schema(name = "@class", required = true, example = "org.apache.syncope.common.lib.to.PushTaskTO")
    @Override
    public String getDiscriminator() {
        return getClass().getName();
    }

    public String getSourceRealm() {
        return sourceRealm;
    }

    public void setSourceRealm(final String sourceRealm) {
        this.sourceRealm = sourceRealm;
    }

    @JsonProperty
    public Map<String, String> getFilters() {
        return filters;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(sourceRealm).
                append(filters).
                build();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PushTaskTO other = (PushTaskTO) obj;
        return new EqualsBuilder().
                appendSuper(super.equals(obj)).
                append(sourceRealm, other.sourceRealm).
                append(filters, other.filters).
                build();
    }
}
