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
package org.jclouds.dimensiondata.cloudcontrol.features;

import com.google.common.collect.FluentIterable;
import org.jclouds.dimensiondata.cloudcontrol.domain.CustomerImage;
import org.jclouds.dimensiondata.cloudcontrol.domain.OsImage;
import org.jclouds.dimensiondata.cloudcontrol.internal.BaseDimensionDataCloudControlApiLiveTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Test(groups = "live", testName = "ServerImageApiLiveTest", singleThreaded = true)
public class ServerImageApiLiveTest extends BaseDimensionDataCloudControlApiLiveTest {

   @Test
   public void testListOsImages() {
      FluentIterable<OsImage> osImages = api().listOsImages().concat();
      assertNotNull(osImages);
      for (OsImage osImage : osImages) {
         assertNotNull(osImage);
      }
   }

   @Test
   public void testListCustomerImages() {
      FluentIterable<CustomerImage> customerImages = api().listCustomerImages().concat();
      assertNotNull(customerImages);
      for (CustomerImage customerImage : customerImages) {
         assertNotNull(customerImage);
      }
   }

   private ServerImageApi api() {
      return api.getServerImageApi();
   }

}
