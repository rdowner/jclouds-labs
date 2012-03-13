/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
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
package org.jclouds.vcloud.director.v1_5.features;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jclouds.rest.annotations.BinderParam;
import org.jclouds.rest.annotations.Delegate;
import org.jclouds.rest.annotations.EndpointParam;
import org.jclouds.rest.annotations.ExceptionParser;
import org.jclouds.rest.annotations.JAXBResponseParser;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.binders.BindToXMLPayload;
import org.jclouds.vcloud.director.v1_5.VCloudDirectorMediaType;
import org.jclouds.vcloud.director.v1_5.domain.ControlAccessParams;
import org.jclouds.vcloud.director.v1_5.domain.DeployVAppParams;
import org.jclouds.vcloud.director.v1_5.domain.GuestCustomizationSection;
import org.jclouds.vcloud.director.v1_5.domain.LeaseSettingsSection;
import org.jclouds.vcloud.director.v1_5.domain.MediaInsertOrEjectParams;
import org.jclouds.vcloud.director.v1_5.domain.NetworkConfigSection;
import org.jclouds.vcloud.director.v1_5.domain.NetworkConnectionSection;
import org.jclouds.vcloud.director.v1_5.domain.NetworkSection;
import org.jclouds.vcloud.director.v1_5.domain.OperatingSystemSection;
import org.jclouds.vcloud.director.v1_5.domain.Owner;
import org.jclouds.vcloud.director.v1_5.domain.ProductSectionList;
import org.jclouds.vcloud.director.v1_5.domain.RasdItemsList;
import org.jclouds.vcloud.director.v1_5.domain.RecomposeVAppParams;
import org.jclouds.vcloud.director.v1_5.domain.RelocateParams;
import org.jclouds.vcloud.director.v1_5.domain.RuntimeInfoSection;
import org.jclouds.vcloud.director.v1_5.domain.ScreenTicket;
import org.jclouds.vcloud.director.v1_5.domain.StartupSection;
import org.jclouds.vcloud.director.v1_5.domain.Task;
import org.jclouds.vcloud.director.v1_5.domain.UndeployVAppParams;
import org.jclouds.vcloud.director.v1_5.domain.VApp;
import org.jclouds.vcloud.director.v1_5.domain.VirtualHardwareSection;
import org.jclouds.vcloud.director.v1_5.domain.VmPendingQuestion;
import org.jclouds.vcloud.director.v1_5.domain.VmQuestionAnswer;
import org.jclouds.vcloud.director.v1_5.domain.ovf.RASD;
import org.jclouds.vcloud.director.v1_5.filters.AddVCloudAuthorizationToRequest;
import org.jclouds.vcloud.director.v1_5.functions.ThrowVCloudErrorOn4xx;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author grkvlt@apache.org
 * @see VAppClient
 */
@RequestFilters(AddVCloudAuthorizationToRequest.class)
public interface VAppAsyncClient {

   /**
    * @see VAppClient#getVApp(URI)
    */
   @GET
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<VApp> getVApp(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVApp(URI, VApp)
    */
   @PUT
   @Produces(VCloudDirectorMediaType.VAPP)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVApp(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) VApp vApp);

   /**
    * @see VAppClient#deleteVApp(URI)
    */
   @DELETE
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> deleteVApp(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#consolidateVApp(URI)
    */
   @POST
   @Path("/action/consolidate")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> consolidateVApp(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#controlAccess(URI, ControlAccessParams)
    */
   @POST
   @Path("/action/controlAccess")
   @Produces(VCloudDirectorMediaType.CONTROL_ACCESS)
   @Consumes(VCloudDirectorMediaType.CONTROL_ACCESS)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<ControlAccessParams> controlAccess(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) ControlAccessParams params);

   /**
    * @see VAppClient#deploy(URI, DeployVAppParams)
    */
   @POST
   @Path("/action/deploy")
   @Produces(VCloudDirectorMediaType.DEPLOY_VAPP_PARAMS)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> deploy(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) DeployVAppParams params);

   /**
    * @see VAppClient#discardSuspendedState(URI)
    */
   @POST
   @Path("/action/discardSuspendedState")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> discardSuspendedState(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#enterMaintenanceMode(URI)
    */
   @POST
   @Path("/action/enterMaintenanceMode")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Void> enterMaintenanceMode(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#exitMaintenanceMode(URI)
    */
   @POST
   @Path("/action/exitMaintenanceMode")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Void> exitMaintenanceMode(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#installVMwareTools(URI)
    */
   @POST
   @Path("/action/installVMwareTools")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> installVMwareTools(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#recomposeVApp(URI, RecomposeVAppParams)
    */
   @POST
   @Path("/action/recomposeVApp")
   @Produces(VCloudDirectorMediaType.RECOMPOSE_VAPP_PARAMS)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> recomposeVApp(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RecomposeVAppParams params);

   /**
    * @see VAppClient#relocate(URI, RelocateParams)
    */
   @POST
   @Path("/action/relocate")
   @Produces(VCloudDirectorMediaType.RELOCATE_VM_PARAMS)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> relocate(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RelocateParams params);

   /**
    * @see VAppClient#undeploy(URI, UndeployVAppParams)
    */
   @POST
   @Path("/action/undeploy")
   @Produces(VCloudDirectorMediaType.UNDEPLOY_VAPP_PARAMS)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> undeploy(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) UndeployVAppParams params);

   /**
    * @see VAppClient#upgradeHardwareVersion(URI)
    */
   @POST
   @Path("/action/upgradeHardwareVersion")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> upgradeHardwareVersion(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getControlAccess(URI)
    */
   @GET
   @Path("/controlAccess")
   @Consumes(VCloudDirectorMediaType.CONTROL_ACCESS)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<ControlAccessParams> getControlAccess(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#powerOff(URI)
    */
   @POST
   @Path("/power/action/powerOff")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> powerOff(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#powerOn(URI)
    */
   @POST
   @Path("/power/action/powerOn")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> powerOn(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#reboot(URI)
    */
   @POST
   @Path("/power/action/powerOff")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> reboot(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#reset(URI)
    */
   @POST
   @Path("/power/action/reset")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> reset(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#shutdown(URI)
    */
   @POST
   @Path("/power/action/shutdown")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> shutdown(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#suspend(URI)
    */
   @POST
   @Path("/power/action/suspend")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> suspend(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getGuestCustomizationSection(URI)
    */
   @GET
   @Path("/guestCustomizationSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<GuestCustomizationSection> getGuestCustomizationSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyGuestCustomizationSection(URI, GuestCustomizationSection)
    */
   @PUT
   @Path("/guestCustomizationSection")
   @Produces(VCloudDirectorMediaType.GUEST_CUSTOMIZATION_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyGuestCustomizationSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) GuestCustomizationSection section);

   /**
    * @see VAppClient#getLeaseSettingsSection(URI)
    */
   @GET
   @Path("/leaseSettingsSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<LeaseSettingsSection> getLeaseSettingsSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyLeaseSettingsSection(URI, LeaseSettingsSection)
    */
   @PUT
   @Path("/leaseSettingsSection")
   @Produces(VCloudDirectorMediaType.LEASE_SETTINGS_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyLeaseSettingsSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) LeaseSettingsSection section);

   /**
    * @see VAppClient#ejectMedia(URI, MediaInsertOrEjectParams)
    */
   @POST
   @Path("/media/action/ejectMedia")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> ejectMedia(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) MediaInsertOrEjectParams mediaParams);


   /**
    * @see VAppClient#insertMedia(URI, MediaInsertOrEjectParams)
    */
   @POST
   @Path("/media/action/insertMedia")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> insertMedia(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) MediaInsertOrEjectParams mediaParams);

   /**
    * @return asynchronous access to {@link Writable} features
    */
   @Delegate
   MetadataAsyncClient.Writable getMetadataClient();

   /**
    * @see VAppClient#getNetworkConfigSection(URI)
    */
   @GET
   @Path("/networkConfigSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<NetworkConfigSection> getNetworkConfigSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyNetworkConfigSection(URI, NetworkConfigSection)
    */
   @PUT
   @Path("/networkConfigSection")
   @Produces(VCloudDirectorMediaType.NETWORK_CONFIG_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyNetworkConfigSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) NetworkConfigSection section);

   /**
    * @see VAppClient#getNetworkConnectionSection(URI)
    */
   @GET
   @Path("/networkConnectionSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<NetworkConnectionSection> getNetworkConnectionSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyNetworkConnectionSection(URI, NetworkConnectionSection)
    */
   @PUT
   @Path("/networkConfigSection")
   @Produces(VCloudDirectorMediaType.NETWORK_CONFIG_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyNetworkConnectionSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) NetworkConnectionSection section);

   /**
    * @see VAppClient#getNetworkSection(URI)
    */
   @GET
   @Path("/networkSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<NetworkSection> getNetworkSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getOperatingSystemSection(URI)
    */
   @GET
   @Path("/operatingSystemSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<OperatingSystemSection> getOperatingSystemSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyOperatingSystemSection(URI, OperatingSystemSection)
    */
   @PUT
   @Path("/operatingSystemSection")
   @Produces(VCloudDirectorMediaType.OPERATING_SYSTEM_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyOperatingSystemSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) OperatingSystemSection section);

   /**
    * @see VAppClient#getOwner(URI)
    */
   @GET
   @Path("/owner")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Owner> getOwner(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyOwner(URI, Owner)
    */
   @PUT
   @Path("/owner")
   @Produces(VCloudDirectorMediaType.OWNER)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyOwner(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) Owner owner);

   /**
    * @see VAppClient#getProductSections(URI)
    */
   @GET
   @Path("/productSections")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<ProductSectionList> getProductSections(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyProductSections(URI, ProductSectionList)
    */
   @PUT
   @Path("/productSections")
   @Produces(VCloudDirectorMediaType.PRODUCT_SECTION_LIST)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyProductSections(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) ProductSectionList sectionList);

   /**
    * @see VAppClient#getPendingQuestion(URI)
    */
   @GET
   @Path("/question")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<VmPendingQuestion> getPendingQuestion(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#answerQuestion(URI, VmQuestionAnswer)
    */
   @PUT
   @Path("/question/action/answer")
   @Produces(VCloudDirectorMediaType.VM_PENDING_ANSWER)
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Void> answerQuestion(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) VmQuestionAnswer answer);

   /**
    * @see VAppClient#getRuntimeInfoSection(URI)
    */
   @GET
   @Path("/runtimeInfoSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RuntimeInfoSection> getRuntimeInfoSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getScreenImage(URI)
    */
   @GET
   @Path("/screen")
   @Consumes(VCloudDirectorMediaType.ANY_IMAGE)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<byte[]> getScreenImage(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getScreenTicket(URI)
    */
   @POST
   @Path("/screen/action/acquireTicket")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<ScreenTicket> getScreenTicket(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getStartupSection(URI)
    */
   @GET
   @Path("/startupSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<StartupSection> getStartupSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyStartupSection(URI, StartupSection)
    */
   @PUT
   @Path("/startupSection")
   @Produces(VCloudDirectorMediaType.STARTUP_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyStartupSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) StartupSection section);

   /**
    * @see VAppClient#getVirtualHardwareSection(URI)
    */
   @GET
   @Path("/virtualHardwareSection")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<VirtualHardwareSection> getVirtualHardwareSection(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVirtualHardwareSection(URI, VirtualHardwareSection)
    */
   @PUT
   @Path("/virtualHardwareSection")
   @Produces(VCloudDirectorMediaType.VIRTUAL_HARDWARE_SECTION)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVirtualHardwareSection(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) VirtualHardwareSection section);

   /**
    * @see VAppClient#getVirtualHardwareSectionCpu(URI)
    */
   @GET
   @Path("/virtualHardwareSection/cpu")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RASD> getVirtualHardwareSectionCpu(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVirtualHardwareSectionCpu(URI, RASD)
    */
   @PUT
   @Path("/virtualHardwareSection/cpu")
   @Produces(VCloudDirectorMediaType.OVF_RASD_ITEM)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVirtualHardwareSectionCpu(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RASD rasd);

   /**
    * @see VAppClient#getVirtualHardwareSectionDisks(URI)
    */
   @GET
   @Path("/virtualHardwareSection/disks")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RasdItemsList> getVirtualHardwareSectionDisks(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVirtualHardwareSectionDisks(URI, RasdItemsList)
    */
   @PUT
   @Path("/virtualHardwareSection/disks")
   @Produces(VCloudDirectorMediaType.OVF_RASD_ITEMS_LIST)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVirtualHardwareSectionDisks(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RasdItemsList rasdItemsList);

   /**
    * @see VAppClient#getVirtualHardwareSectionMedia(URI)
    */
   @GET
   @Path("/virtualHardwareSection/media")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RasdItemsList> getVirtualHardwareSectionMedia(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#getVirtualHardwareSectionMemory(URI)
    */
   @GET
   @Path("/virtualHardwareSection/memory")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RASD> getVirtualHardwareSectionMemory(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVirtualHardwareSectionMemory(URI, RASD)
    */
   @PUT
   @Path("/virtualHardwareSection/memory")
   @Produces(VCloudDirectorMediaType.OVF_RASD_ITEM)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVirtualHardwareSectionMemory(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RASD rasd);

   /**
    * @see VAppClient#getVirtualHardwareSectionNetworkCards(URI)
    */
   @GET
   @Path("/virtualHardwareSection/networkCards")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RasdItemsList> getVirtualHardwareSectionNetworkCards(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVirtualHardwareSectionNetworkCards(URI, RasdItemsList)
    */
   @PUT
   @Path("/virtualHardwareSection/networkCards")
   @Produces(VCloudDirectorMediaType.OVF_RASD_ITEMS_LIST)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVirtualHardwareSectionNetworkCards(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RasdItemsList rasdItemsList);

   /**
    * @see VAppClient#getVirtualHardwareSectionSerialPorts(URI)
    */
   @GET
   @Path("/virtualHardwareSection/serialPorts")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<RasdItemsList> getVirtualHardwareSectionSerialPorts(@EndpointParam URI vAppURI);

   /**
    * @see VAppClient#modifyVirtualHardwareSectionSerialPorts(URI, RasdItemsList)
    */
   @PUT
   @Path("/virtualHardwareSection/serialPorts")
   @Produces(VCloudDirectorMediaType.OVF_RASD_ITEMS_LIST)
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVirtualHardwareSectionSerialPorts(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) RasdItemsList rasdItemsList);

}