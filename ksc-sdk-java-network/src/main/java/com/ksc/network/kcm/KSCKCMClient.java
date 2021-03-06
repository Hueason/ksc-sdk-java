package com.ksc.network.kcm;

import com.ksc.*;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.AWSCredentialsProvider;
import com.ksc.auth.DefaultAWSCredentialsProviderChain;
import com.ksc.http.DefaultErrorResponseHandler;
import com.ksc.http.ExecutionContext;
import com.ksc.http.HttpResponseHandler;
import com.ksc.http.StaxResponseHandler;
import com.ksc.internal.StaticCredentialsProvider;
import com.ksc.metrics.RequestMetricCollector;
import com.ksc.network.kcm.model.*;
import com.ksc.network.kcm.model.transform.*;
import com.ksc.transform.LegacyErrorUnmarshaller;
import com.ksc.transform.StandardErrorUnmarshaller;
import com.ksc.transform.Unmarshaller;
import com.ksc.util.CredentialUtils;
import com.ksc.util.KscRequestMetrics;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class KSCKCMClient extends KscWebServiceClient implements KSCKCM {
    /**
     * Provider for AWS credentials.
     */
    private AWSCredentialsProvider kscCredentialsProvider;

    /**
     * Default signing name for the service.
     */
    private static final String DEFAULT_SIGNING_NAME = "kcm";

    /**
     * The region metadata service name for computing region endpoints.
     */
    private static final String DEFAULT_ENDPOINT_PREFIX = "kcm";

    /**
     * Client configuration factory providing ClientConfigurations tailored to
     * this client
     */
    protected static final ClientConfigurationFactory configFactory = new ClientConfigurationFactory();

    /**
     * List of exception unmarshallers for all modeled exceptions
     */
    protected final List<Unmarshaller<KscServiceException, Node>> exceptionUnmarshallers = new ArrayList<Unmarshaller<KscServiceException, Node>>();

    /**
     * Constructs a new client to invoke service methods on KCM. A credentials
     * provider chain will be used that searches for credentials in this order:
     * <ul>
     * <li>Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY</li>
     * <li>Java System Properties - aws.accessKeyId and aws.secretKey</li>
     * <li>Instance profile credentials delivered through the KCM metadata
     * service</li>
     * </ul>
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @see DefaultAWSCredentialsProviderChain
     */
    public KSCKCMClient() {
        this(new DefaultAWSCredentialsProviderChain(), configFactory.getConfig());
    }

    /**
     * Constructs a new client to invoke service methods on KCM. A credentials
     * provider chain will be used that searches for credentials in this order:
     * <ul>
     * <li>Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY</li>
     * <li>Java System Properties - aws.accessKeyId and aws.secretKey</li>
     * <li>Instance profile credentials delivered through the KCM metadata
     * service</li>
     * </ul>
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to KSC (ex: proxy settings, retry counts, etc.).
     * @see DefaultAWSCredentialsProviderChain
     */
    public KSCKCMClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    /**
     * Constructs a new client to invoke service methods on KCM using the
     * specified AWS account credentials.
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use when
     *                       authenticating with AWS services.
     */
    public KSCKCMClient(AWSCredentials awsCredentials) {
        this(awsCredentials, configFactory.getConfig());
    }

    /**
     * Constructs a new client to invoke service methods on KCM using the
     * specified AWS account credentials and client configuration options.
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @param awsCredentials      The AWS credentials (access key ID and secret key) to use when
     *                            authenticating with AWS services.
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to KCM (ex: proxy settings, retry counts, etc.).
     */
    public KSCKCMClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.kscCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
        init();
    }

    /**
     * Constructs a new client to invoke service methods on KCM using the
     * specified AWS account credentials provider.
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @param awsCredentialsProvider The AWS credentials provider which will provide credentials to
     *                               authenticate requests with KSC services.
     */
    public KSCKCMClient(AWSCredentialsProvider awsCredentialsProvider) {
        this(awsCredentialsProvider, configFactory.getConfig());
    }

    /**
     * Constructs a new client to invoke service methods on KCM using the
     * specified AWS account credentials provider and client configuration
     * options.
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @param awsCredentialsProvider The AWS credentials provider which will provide credentials to
     *                               authenticate requests with KSC services.
     * @param clientConfiguration    The client configuration options controlling how this client
     *                               connects to KCM (ex: proxy settings, retry counts, etc.).
     */
    public KSCKCMClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(awsCredentialsProvider, clientConfiguration, null);
    }

    /**
     * Constructs a new client to invoke service methods on KCM using the
     * specified KSC account credentials provider, client configuration options,
     * and request metric collector.
     * <p>
     * <p>
     * All service calls made using this new client object are blocking, and
     * will not return until the service call completes.
     *
     * @param awsCredentialsProvider The AWS credentials provider which will provide credentials to
     *                               authenticate requests with KSC services.
     * @param clientConfiguration    The client configuration options controlling how this client
     *                               connects to KCM (ex: proxy settings, retry counts, etc.).
     * @param requestMetricCollector optional request metric collector
     */
    public KSCKCMClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration,
                        RequestMetricCollector requestMetricCollector) {
        super(clientConfiguration, requestMetricCollector);
        this.kscCredentialsProvider = awsCredentialsProvider;
        init();
    }

    @Override
    public CreateCertificateResult createCertificate(CreateCertificateRequest createCertificateRequest) {
        ExecutionContext executionContext = createExecutionContext(createCertificateRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(KscRequestMetrics.Field.ClientExecuteTime);
        Request<CreateCertificateRequest> request = null;
        Response<CreateCertificateResult> response = null;
        try {
            kscRequestMetrics.startEvent(KscRequestMetrics.Field.RequestMarshallTime);
            try {
                request = new CreateCertificateRequestMarshaller().marshall(super
                        .beforeMarshalling(createCertificateRequest));
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(KscRequestMetrics.Field.RequestMarshallTime);
            }

            StaxResponseHandler<CreateCertificateResult> responseHandler = new StaxResponseHandler<CreateCertificateResult>(
                    new CreateCertificateResultStaxUnMarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();

        } finally {

            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DeleteCertificateResult deleteCertificate(DeleteCertificateRequest deleteCertificateRequest) {
        ExecutionContext executionContext = createExecutionContext(deleteCertificateRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(KscRequestMetrics.Field.ClientExecuteTime);
        Request<DeleteCertificateRequest> request = null;
        Response<DeleteCertificateResult> response = null;
        try {
            kscRequestMetrics.startEvent(KscRequestMetrics.Field.RequestMarshallTime);
            try {
                request = new DeleteCertificateRequestMarshaller()
                        .marshall(deleteCertificateRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(KscRequestMetrics.Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeleteCertificateResult> responseHandler = new StaxResponseHandler<DeleteCertificateResult>(
                    new DeleteCertificateResultStaxUnMarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public ModifyCertificateResult modifyCertificate(ModifyCertificateRequest modifyCertificateRequest) {
        ExecutionContext executionContext = createExecutionContext(modifyCertificateRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(KscRequestMetrics.Field.ClientExecuteTime);
        Request<ModifyCertificateRequest> request = null;
        Response<ModifyCertificateResult> response = null;
        try {
            kscRequestMetrics.startEvent(KscRequestMetrics.Field.RequestMarshallTime);
            try {
                request = new ModifyCertificateRequestMarshaller()
                        .marshall(modifyCertificateRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(KscRequestMetrics.Field.RequestMarshallTime);
            }
            StaxResponseHandler<ModifyCertificateResult> responseHandler = new StaxResponseHandler<ModifyCertificateResult>(
                    new ModifyCertificateResultStaxUnMarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DescribeCertificatesResult describeCertificates(DescribeCertificatesRequest describeCertificatesRequest) {
        ExecutionContext executionContext = createExecutionContext(describeCertificatesRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(KscRequestMetrics.Field.ClientExecuteTime);
        Request<DescribeCertificatesRequest> request = null;
        Response<DescribeCertificatesResult> response = null;
        try {
            kscRequestMetrics.startEvent(KscRequestMetrics.Field.RequestMarshallTime);
            try {
                request = new DescribeCertificatesRequestMarshaller()
                        .marshall(describeCertificatesRequest);
                // Binds the request metrics to the current request.
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(KscRequestMetrics.Field.RequestMarshallTime);
            }
            StaxResponseHandler<DescribeCertificatesResult> responseHandler = new StaxResponseHandler<DescribeCertificatesResult>(
                    new DescribeCertificatesResultStaxUnMarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    private void init() {
        exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
        exceptionUnmarshallers.add(new LegacyErrorUnmarshaller());
        setServiceNameIntern(DEFAULT_SIGNING_NAME);
        setEndpointPrefix(DEFAULT_ENDPOINT_PREFIX);
        setEndpoint("http://kcm.cn-beijing-6.api.ksyun.com");
    }

    /**
     * Normal invoke with authentication. Credentials are required and may be
     * overriden at the request level.
     **/
    private <X, Y extends KscWebServiceRequest> Response<X> invoke(Request<Y> request,
                                                                   HttpResponseHandler<KscWebServiceResponse<X>> responseHandler, ExecutionContext executionContext) {

        executionContext.setCredentialsProvider(
                CredentialUtils.getCredentialsProvider(request.getOriginalRequest(), kscCredentialsProvider));

        return doInvoke(request, responseHandler, executionContext);
    }

    /**
     * Invoke the request using the http client. Assumes credentials (or lack
     * thereof) have been configured in the ExecutionContext beforehand.
     **/
    private <X, Y extends KscWebServiceRequest> Response<X> doInvoke(Request<Y> request,
                                                                     HttpResponseHandler<KscWebServiceResponse<X>> responseHandler, ExecutionContext executionContext) {
        request.setEndpoint(endpoint);
        request.setTimeOffset(timeOffset);

        DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);

        return client.execute(request, responseHandler, errorResponseHandler, executionContext);
    }
}
