/*
   Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo.charts.shared.core.types;

import java.util.List;

import com.ait.lienzo.shared.core.types.EnumWithValue;
import com.ait.tooling.nativetools.client.primitive.NFastStringMap;

public enum AxisType implements EnumWithValue
{
    CATEGORY("category"), NUMBER("number"), DATE("date");

    private final String                          m_value;

    private static final NFastStringMap<AxisType> LOOKUP_MAP = Statics.build(AxisType.values());

    private AxisType(String value)
    {
        m_value = value;
    }

    @Override
    public final String getValue()
    {
        return m_value;
    }

    @Override
    public final String toString()
    {
        return m_value;
    }

    public static final AxisType lookup(String key)
    {
        return Statics.lookup(key, LOOKUP_MAP, null);
    }

    public static final List<String> getKeys()
    {
        return Statics.getKeys(AxisType.values());
    }

    public static final List<AxisType> getValues()
    {
        return Statics.getValues(AxisType.values());
    }
}
