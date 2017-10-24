package com.acollider.repositoryevent.other;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project NioxinAndroid
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerInstance {
}
