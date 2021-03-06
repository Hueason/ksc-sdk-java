package com.ksc.network.vpc.transform.peering;

import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.network.vpc.model.peering.CreateVpcPeeringConnectionRequest;
import com.ksc.transform.Marshaller;
import com.ksc.util.StringUtils;

public class CreateVpcPeeringConnectionRequestMarshaller implements
        Marshaller<Request<CreateVpcPeeringConnectionRequest>, CreateVpcPeeringConnectionRequest> {

    @Override
    public Request<CreateVpcPeeringConnectionRequest> marshall(CreateVpcPeeringConnectionRequest createVpcPeeringConnectionRequest) {
        if (createVpcPeeringConnectionRequest == null) {
            throw new KscClientException("Invalid argument passed to marshall(...)");
        }

        Request<CreateVpcPeeringConnectionRequest> request = new DefaultRequest<CreateVpcPeeringConnectionRequest>(
                createVpcPeeringConnectionRequest, "vpc");
        request.addParameter("Action", "CreateVpcPeeringConnection");
        String version = createVpcPeeringConnectionRequest.getVersion();
        if (org.apache.commons.lang.StringUtils.isBlank(version)) {
            version = "2016-03-04";
        }
        request.addParameter("Version", version);
        request.setHttpMethod(HttpMethodName.GET);

        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getVpcId())) {
            request.addParameter("VpcId", createVpcPeeringConnectionRequest.getVpcId());
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getRegion())) {
            request.addParameter("Region", createVpcPeeringConnectionRequest.getRegion());
        }
        if (createVpcPeeringConnectionRequest.getBandWidth() != null) {
            request.addParameter("BandWidth", String.valueOf(createVpcPeeringConnectionRequest.getBandWidth()));
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getPeerAccountId())) {
            request.addParameter("PeerAccountId", createVpcPeeringConnectionRequest.getPeerAccountId());
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getPeerRegion())) {
            request.addParameter("PeerRegion", createVpcPeeringConnectionRequest.getPeerRegion());
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getPeerVpcId())) {
            request.addParameter("PeerVpcId", createVpcPeeringConnectionRequest.getPeerVpcId());
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getPeeringName())) {
            request.addParameter("PeeringName", createVpcPeeringConnectionRequest.getPeeringName());
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getChargeType())) {
            request.addParameter("ChargeType", createVpcPeeringConnectionRequest.getChargeType());
        }
        if (!StringUtils.isNullOrEmpty(createVpcPeeringConnectionRequest.getProjectId())) {
            request.addParameter("ProjectId", createVpcPeeringConnectionRequest.getProjectId());
        }

        return request;
    }
}