package com.lzx.annotation;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lzx
 */
public class IdNumberFormatAnnotationFormatterFactory
        implements AnnotationFormatterFactory<IdNumberFormat> {

    private static final Set<Class<?>> FIELD_TYPES;
    static {
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(4);
        fieldTypes.add(String.class);
        FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
    }


    @Override
    public Set<Class<?>> getFieldTypes() {
        // 返回支持的类型列表
        return FIELD_TYPES;
    }

    @Override
    public Printer<?> getPrinter(IdNumberFormat idNumberFormat, Class<?> aClass) {
        return this.getFormatter(idNumberFormat, aClass);
    }

    @Override
    public Parser<?> getParser(IdNumberFormat idNumberFormat, Class<?> aClass) {
        return this.getFormatter(idNumberFormat, aClass);
    }


    protected Formatter<String> getFormatter(IdNumberFormat annotation, Class<?> fieldType) {
        IdNumberFormatter idNumberFormatter = new IdNumberFormatter();
        return idNumberFormatter;
    }
}
