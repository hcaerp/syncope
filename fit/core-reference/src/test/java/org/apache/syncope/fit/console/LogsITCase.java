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
package org.apache.syncope.fit.console;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import org.apache.syncope.client.ui.commons.Constants;
import org.apache.syncope.client.console.pages.Logs;
import org.apache.syncope.common.lib.log.LoggerTO;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.util.visit.IVisit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogsITCase extends AbstractConsoleITCase {

    private static final String CONTAINER_PATH = "body:content:tabbedPanel:panel:loggerContainer";

    @BeforeEach
    public void login() {
        doLogin(ADMIN_UNAME, ADMIN_PWD);
        UTILITY_UI.getTester().clickLink("body:configurationLI:configurationUL:logsLI:logs");
        UTILITY_UI.getTester().assertRenderedPage(Logs.class);
    }

    @Test
    public void readCoreLogs() {
        UTILITY_UI.getTester().clickLink("body:content:tabbedPanel:tabs-container:tabs:0:link");
        UTILITY_UI.getTester().assertComponent(CONTAINER_PATH, WebMarkupContainer.class);

        assertNotNull(searchLog(KEY, CONTAINER_PATH, "io.swagger"));
    }

    @Test
    public void updateCoreLogs() {
        UTILITY_UI.getTester().clickLink("body:content:tabbedPanel:tabs-container:tabs:0:link");
        UTILITY_UI.getTester().assertComponent(CONTAINER_PATH, WebMarkupContainer.class);

        Component result = searchLog(KEY, CONTAINER_PATH, "io.swagger");
        assertNotNull(result);

        UTILITY_UI.getTester().getRequest().addParameter(
                result.getPageRelativePath() + ":fields:1:field:dropDownChoiceField", "6");
        UTILITY_UI.getTester().assertComponent(
                result.getPageRelativePath() + ":fields:1:field:dropDownChoiceField", DropDownChoice.class);
        UTILITY_UI.getTester().executeAjaxEvent(
                result.getPageRelativePath() + ":fields:1:field:dropDownChoiceField", Constants.ON_CHANGE);

        UTILITY_UI.getTester().assertInfoMessages("Operation successfully executed");
    }

    @Test
    public void readConsoleLogs() {
        UTILITY_UI.getTester().assertComponent("body:content:tabbedPanel:tabs-container:tabs:1:link",
                AjaxFallbackLink.class);
        UTILITY_UI.getTester().clickLink("body:content:tabbedPanel:tabs-container:tabs:1:link");
        UTILITY_UI.getTester().assertComponent(CONTAINER_PATH, WebMarkupContainer.class);

        assertNotNull(searchLog(KEY, CONTAINER_PATH, "org.apache.wicket"));
    }

    @Test
    public void updateConsoleLogs() {
        UTILITY_UI.getTester().clickLink("body:content:tabbedPanel:tabs-container:tabs:1:link");
        UTILITY_UI.getTester().assertComponent(CONTAINER_PATH, WebMarkupContainer.class);

        Component result = searchLog(KEY, CONTAINER_PATH, "org.apache.wicket");
        assertNotNull(result);

        UTILITY_UI.getTester().getRequest().addParameter(
                result.getPageRelativePath() + ":fields:1:field:dropDownChoiceField", "6");
        UTILITY_UI.getTester().executeAjaxEvent(
                result.getPageRelativePath() + ":fields:1:field:dropDownChoiceField", Constants.ON_CHANGE);

        UTILITY_UI.getTester().assertInfoMessages("Operation successfully executed");
    }

    private Component searchLog(final String property, final String searchPath, final String key) {
        Component component = UTILITY_UI.getTester().getComponentFromLastRenderedPage(searchPath);

        Component result = component.getPage().
                visitChildren(ListItem.class, (final ListItem<LoggerTO> object, final IVisit<Component> visit) -> {
                    try {
                        if (object.getModelObject() instanceof LoggerTO && PropertyResolver.getPropertyGetter(
                                property, object.getModelObject()).invoke(object.getModelObject()).equals(key)) {
                            visit.stop(object);
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        LOG.error("Error invoke method", ex);
                    }
                });
        return result;
    }
}
