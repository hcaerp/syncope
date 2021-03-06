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
package org.apache.syncope.client.enduser.wizards.any;

import org.apache.syncope.client.ui.commons.wizards.any.AbstractAuxClasses;
import org.apache.syncope.client.ui.commons.wizards.any.AnyWrapper;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.syncope.client.enduser.rest.SyncopeRestClient;
import org.apache.syncope.common.lib.to.AnyTO;
import org.apache.syncope.common.lib.to.AnyTypeClassTO;

public class EnduserAuxClasses extends AbstractAuxClasses {

    private static final long serialVersionUID = 552437609667518888L;

    public <T extends AnyTO> EnduserAuxClasses(final AnyWrapper<T> modelObject, final List<String> anyTypeClasses) {
        super(modelObject, anyTypeClasses);
    }

    @Override
    protected final List<AnyTypeClassTO> listAnyTypecClasses() {
        return new SyncopeRestClient().listAnyTypeClasses().stream().map(name -> {
            AnyTypeClassTO anyTypeClassTO = new AnyTypeClassTO();
            anyTypeClassTO.setKey(name);
            return anyTypeClassTO;
        }).collect(Collectors.toList());
    }
}
