/**
 * Danta AEM Bundle
 *
 * Copyright (C) 2017 Tikal Technologies, Inc. All rights reserved.
 *
 * Licensed under GNU Affero General Public License, Version v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/agpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied;
 * without even the implied warranty of MERCHANTABILITY.
 * See the License for more details.
 */

package danta.core.contextprocessors.concurrentdemo;

import com.google.common.collect.Sets;
import danta.api.ContentModel;
import danta.api.ExecutionContext;
import danta.api.exceptions.ProcessException;
import danta.core.contextprocessors.AbstractCheckComponentCategoryContextProcessor;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.Collections;
import java.util.Set;

import static danta.Constants.LOW_PRIORITY;
import static danta.Constants.PAGE_CATEGORY;

/**
 * A demo context processor for adding properties to content model
 */
@Component
@Service
public abstract class AbstractDemoContextProcessor
        extends AbstractCheckComponentCategoryContextProcessor<ContentModel> {

    private static final Set<String> ANY_OF = Collections.unmodifiableSet(Sets.newHashSet(PAGE_CATEGORY));

    @Override
    public Set<String> anyOf() {
        return ANY_OF;
    }

    @Override
    public int priority() {
        // This processor must be one of the first processors executed.
        return LOW_PRIORITY - 1000;
    }

    /**
     * @param executionContext
     * @param contentModel
     * @throws Exception
     */
    @Override
    public void process(final ExecutionContext executionContext, final ContentModel contentModel)
            throws ProcessException {
        long start = System.currentTimeMillis();
        contentModel.set("page." + this.getClass().getName(), start);
    }
}
