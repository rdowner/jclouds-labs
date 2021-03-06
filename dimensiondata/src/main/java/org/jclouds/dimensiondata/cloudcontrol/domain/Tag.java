/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.dimensiondata.cloudcontrol.domain;

import com.google.auto.value.AutoValue;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.json.SerializedNames;

@AutoValue
public abstract class Tag {

   Tag() {
   }

   public abstract String assetType();

   public abstract String assetId();

   public abstract String datacenterId();

   public abstract String tagKeyId();

   public abstract String tagKeyName();

   @Nullable
   public abstract String value();

   public abstract boolean valueRequired();

   public abstract boolean displayOnReport();

   @SerializedNames({ "assetType", "assetId", "datacenterId", "tagKeyId", "tagKeyName", "value", "valueRequired",
         "displayOnReport" })
   public static Tag create(String assetType, String assetId, String datacenterId, String tagKeyId, String tagKeyName,
         String value, Boolean valueRequired, Boolean displayOnReport) {
      return builder().assetType(assetType).assetId(assetId).datacenterId(datacenterId).tagKeyId(tagKeyId)
            .tagKeyName(tagKeyName).value(value).valueRequired(valueRequired).displayOnReport(displayOnReport).build();
   }

   public abstract Builder toBuilder();

   @AutoValue.Builder
   public abstract static class Builder {
      public abstract Builder assetType(String assetType);

      public abstract Builder assetId(String assetId);

      public abstract Builder datacenterId(String datacenterId);

      public abstract Builder tagKeyId(String tagKeyId);

      public abstract Builder tagKeyName(String tagKeyName);

      public abstract Builder value(String value);

      public abstract Builder valueRequired(boolean valueRequired);

      public abstract Builder displayOnReport(boolean displayOnReport);

      public abstract Tag build();

   }

   public static Builder builder() {
      return new AutoValue_Tag.Builder();
   }

}
