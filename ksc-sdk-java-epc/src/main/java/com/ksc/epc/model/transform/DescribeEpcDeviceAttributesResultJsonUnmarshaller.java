package com.ksc.epc.model.transform;

import com.ksc.epc.model.DescribeEpcDeviceAttributesResult;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;
import com.ksc.transform.ListUnmarshaller;
import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
import com.ksc.epc.model.DeviceAttribute;

/**
 * DescribeEpcDeviceAttributesResult Json Unmarshaller
 */
public class DescribeEpcDeviceAttributesResultJsonUnmarshaller implements Unmarshaller<DescribeEpcDeviceAttributesResult, JsonUnmarshallerContext> {
    @Override
    public DescribeEpcDeviceAttributesResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        DescribeEpcDeviceAttributesResult describeEpcDeviceAttributesResult = new DescribeEpcDeviceAttributesResult();
        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
        token = context.nextToken();
        if (token == VALUE_NULL)
        return null;
        while (true) {
            if (token == null)
            break;
            if (token == FIELD_NAME || token == START_OBJECT) {
                    if (context.testExpression("RequestId", targetDepth)) {
                    context.nextToken();
                            describeEpcDeviceAttributesResult.setRequestId(context.getUnmarshaller(String.class).unmarshall(context));
                    }
                    if (context.testExpression("EpcDeviceAttributeSet", targetDepth)) {
                    context.nextToken();
                    describeEpcDeviceAttributesResult.setEpcDeviceAttributeSet(new ListUnmarshaller<DeviceAttribute>(DeviceAttributeJsonUnmarshaller.getInstance()).unmarshall(context));
                    }
                    if (context.testExpression("NextToken", targetDepth)) {
                    context.nextToken();
                            describeEpcDeviceAttributesResult.setNextToken(context.getUnmarshaller(String.class).unmarshall(context));
                    }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null
                        || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                    }
            }
            token = context.nextToken();
        }
        return describeEpcDeviceAttributesResult;
    }

    private static DescribeEpcDeviceAttributesResultJsonUnmarshaller instance;

    public static DescribeEpcDeviceAttributesResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new DescribeEpcDeviceAttributesResultJsonUnmarshaller();
        return instance;
    }
}
