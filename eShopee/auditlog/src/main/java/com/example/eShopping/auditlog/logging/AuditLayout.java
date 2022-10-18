package com.example.eShopping.auditlog.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuditLayout extends LayoutBase<ILoggingEvent> {
    private final int BUFFER_SIZE = 256;
    private final int UPPER_LIMIT = 2048;
    private final static char DBL_QUOTE = '"';
    private final static char COMMA = ',';
    private StringBuilder buf = new StringBuilder(BUFFER_SIZE);

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private Pattern MDC_VAR_PATTERN = Pattern.compile("\\@\\{([^}^:-]*)(:-([^}]*)?)?\\}");

    @Override
    public String doLayout(ILoggingEvent event) {
        String parentId = MDC.get("parentSpanId");
        String spanId = MDC.get("spanId");
        if (buf.capacity() > UPPER_LIMIT) {
            buf = new StringBuilder(BUFFER_SIZE);
        } else {
            buf.setLength(0);
        }
        Map<String, String> mdc = event.getMDCPropertyMap();
        buf.append("{");
        try {
            appendKeyValue(buf, "host", InetAddress.getLocalHost().getHostName(), mdc);
        } catch (UnknownHostException e) {

        }
        buf.append(COMMA);
        appendKeyValue(buf, "message", event.getFormattedMessage(), null);
        buf.append(COMMA);
        appendKeyValue(buf, "@timestamp",
                df.format(new Date(event.getTimeStamp())), null);
        buf.append(COMMA);
        appendKeyValue(buf, "logger", event.getLoggerName(), null);
        buf.append(COMMA);
        appendKeyValue(buf, "level", event.getLevel().toString(), null);
        buf.append(COMMA);
        appendKeyValue(buf, "thread", event.getThreadName(), null);
        StackTraceElement[] callerDataArray = event.getCallerData();
        if (callerDataArray != null
                && callerDataArray.length > 0) {
            StackTraceElement immediateCallerData = callerDataArray[0];
            buf.append(COMMA);
            appendKeyValue(buf, "class",
                    immediateCallerData.getClassName(), null);
            buf.append(COMMA);
            appendKeyValue(buf, "method",
                    immediateCallerData.getMethodName(), null);
            buf.append(COMMA);
            appendKeyValue(buf, "line",
                    Integer.toString(immediateCallerData.getLineNumber()),
                    null);
        }
        buf.append(COMMA);
        String traceId = MDC.get("traceId");
        appendKeyValue(buf, "traceId",
                traceId, null);
        buf.append(COMMA);
        appendKeyValue(buf, "spanId",
                spanId, null);
        buf.append(COMMA);
        appendKeyValue(buf, "parentSpanId",
                parentId, null);
        buf.append("}");
        buf.append("\n");
        return buf.toString();
    }

    private void appendKeyValue(StringBuilder buf, String key, String value,
                                Map<String, String> mdc) {
        if (value != null) {
            buf.append(DBL_QUOTE);
            buf.append(escape(key));
            buf.append(DBL_QUOTE);
            buf.append(':');
            buf.append(DBL_QUOTE);
            buf.append(escape(mdcSubst(value, mdc)));
            buf.append(DBL_QUOTE);
        } else {
            buf.append(DBL_QUOTE);
            buf.append(escape(key));
            buf.append(DBL_QUOTE);
            buf.append(':');
            buf.append("null");
        }
    }

    private String mdcSubst(String v, Map<String, String> mdc) {
        if (mdc != null && v != null && v.contains("@{")) {
            Matcher m = MDC_VAR_PATTERN.matcher(v);
            StringBuffer sb = new StringBuffer(v.length());
            while (m.find()) {
                String val = mdc.get(m.group(1));
                if (val == null) {
                    // If a default value exists, use it
                    val = (m.group(3) != null) ? val = m.group(3) : m.group(1) + "_NOT_FOUND";
                }
                m.appendReplacement(sb, Matcher.quoteReplacement(val));
            }
            m.appendTail(sb);
            return sb.toString();
        }
        return v;
    }

    private String escape(String s) {
        if (s == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }// for
        return sb.toString();
    }
}
