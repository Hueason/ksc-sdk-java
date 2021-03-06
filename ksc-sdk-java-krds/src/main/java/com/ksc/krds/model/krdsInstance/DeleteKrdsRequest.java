package com.ksc.krds.model.krdsInstance;

import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.krds.transform.krdsInstance.DeleteKrdsMarshaller;
import com.ksc.model.DryRunSupportedRequest;

import java.io.Serializable;

public class DeleteKrdsRequest extends KscWebServiceRequest
        implements Serializable, Cloneable, DryRunSupportedRequest<DeleteKrdsRequest> {

    private String DBInstanceIdentifier;
    private String RequestId;

    public String getDBInstanceIdentifier() {
        return DBInstanceIdentifier;
    }

    public void setDBInstanceIdentifier(String DBInstanceIdentifier) {
        this.DBInstanceIdentifier = DBInstanceIdentifier;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public Request<DeleteKrdsRequest> getDryRunRequest() {
        Request<DeleteKrdsRequest> request = new DeleteKrdsMarshaller().marshall(this);
        request.addParameter("DryRun", Boolean.toString(true));
        return request;
    }
}