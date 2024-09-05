package com.jz.test.redistest.config.redisLock;

import com.jz.test.redistest.config.redisLock.annoation.Lock4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sky
 * create  2020-08-13 10:56
 * email sky.li@ixiaoshuidi.com
 */
public class LockKeyGenerator {

    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    public String getKeyName(MethodInvocation invocation, Lock4j lock4j) {
        StringBuilder sb = new StringBuilder(prefix());
        Method method = invocation.getMethod();
        sb.append(method.getDeclaringClass().getName()).append(".").append(method.getName());
        if (lock4j.keys().length > 1 || !"".equals(lock4j.keys()[0])) {
            sb.append(getSpelDefinitionKey(lock4j.keys(), method, invocation.getArguments()));
        }
        return sb.toString();
    }


    private String getSpelDefinitionKey(String[] definitionKeys, Method method, Object[] parameterValues) {
        EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, NAME_DISCOVERER);
        List<String> definitionKeyList = new ArrayList<>(definitionKeys.length);
        for (String definitionKey : definitionKeys) {
            if (definitionKey != null && !definitionKey.isEmpty()) {
                String key = PARSER.parseExpression(definitionKey).getValue(context).toString();
                definitionKeyList.add(key);
            }
        }
        return StringUtils.collectionToDelimitedString(definitionKeyList, ".", "", "");
    }

    private String prefix() {
        return "IECS_LOCK:";
    }

}
