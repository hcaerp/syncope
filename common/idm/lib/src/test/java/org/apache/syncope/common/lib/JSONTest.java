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
package org.apache.syncope.common.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;
import org.apache.syncope.common.lib.to.ConnObjectTO;
import org.apache.syncope.common.lib.to.GroupTO;
import org.apache.syncope.common.lib.to.PropagationStatus;
import org.apache.syncope.common.lib.to.ProvisioningResult;
import org.junit.jupiter.api.Test;

public class JSONTest {

    @Test
    public void provisioningResult() throws IOException {
        GroupTO group = new GroupTO();
        group.setName(UUID.randomUUID().toString());
        group.setRealm(SyncopeConstants.ROOT_REALM);
        group.getVirAttrs().add(new Attr.Builder("rvirtualdata").value("rvirtualvalue").build());
        group.getADynMembershipConds().put("USER", "username==a*");

        ProvisioningResult<GroupTO> original = new ProvisioningResult<>();
        original.setEntity(group);

        PropagationStatus status = new PropagationStatus();
        status.setFailureReason("failed");
        status.setBeforeObj(new ConnObjectTO());
        original.getPropagationStatuses().add(status);

        ObjectMapper mapper = new ObjectMapper();

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, original);

        ProvisioningResult<GroupTO> actual = mapper.readValue(writer.toString(),
                new TypeReference<ProvisioningResult<GroupTO>>() {
        });
        assertEquals(original, actual);
    }
}
